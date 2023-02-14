package io.github.ust.quantil.artifacttemplates.qiskitruntime;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ust.quantil.artifacttemplates.OpenToscaHeaders;
import io.github.ust.quantil.artifacttemplates.SoapUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import static io.github.ust.quantil.artifacttemplates.qiskitruntime.FileHandler.downloadFile;


@Endpoint
public class QiskitRuntimeManagementInterfaceEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(QiskitRuntimeManagementInterfaceEndpoint.class);

    private static String getDAFile(String daUrl, OpenToscaHeaders openToscaHeaders) throws IOException {
        String userCode = downloadFile(URI.create(daUrl));

        if (userCode == null) {
            throw new FileNotFoundException("Error: could not download DA " + daUrl);
        } else {
            return userCode;
        }
    }

    /**
     *
     * @param IBMQ_TOKEN IBMQ-API-TOKEN
     * @return Session Access token
     */
    private String getAccessToken(String IBMQ_TOKEN) throws RestClientResponseException {
        LOG.debug("Start getting access token");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://auth.quantum-computing.ibm.com/api/users/loginWithToken");


        HttpEntity<?> entity = new HttpEntity<>(
                "{\"apiToken\": \""+IBMQ_TOKEN+"\"}",
                headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                String.class);

        List<String> setCookieHeader = response.getHeaders().get("set-cookie");
        String accessToken = setCookieHeader.get(0).split("access_token=")[1].split("\\.")[0].substring(4);
        LOG.info("Got IBMQ access token: {}", accessToken);

        return accessToken;
    }

    private String getRuntimeProgramData(String runtimeProgram, String metaData) throws JsonProcessingException {
        String encodedRuntimeProgram = new String(Base64.encodeBase64(runtimeProgram.getBytes(StandardCharsets.UTF_8)));
        LOG.debug("Encoded runtime program: {}", encodedRuntimeProgram);

        // Put runtime program into data
        ObjectMapper mapper = new ObjectMapper();

        String runtimeCreateRequestDataJson = null;
        Map<String, String> runtimeCreateRequestData = mapper.readValue(metaData, Map.class);
        runtimeCreateRequestData.put("data", encodedRuntimeProgram);
        runtimeCreateRequestDataJson = mapper.writeValueAsString(runtimeCreateRequestData);

        LOG.debug("New request data: {}", runtimeCreateRequestDataJson);

        return runtimeCreateRequestDataJson;
    }

    /**
     *
     * @param runtimeProgramDataJson Create request json data
     * @param accessToken Access token obtained from login
     * @return ID of created runtime program
     * @throws RestClientResponseException
     * @throws JsonProcessingException
     */
    private String uploadRuntimeProgram(String runtimeProgramDataJson, String accessToken) throws RestClientResponseException, JsonProcessingException {
        LOG.debug("Start uploading runtime program");

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

//        headers={
//                "User-Agent": "python-requests/2.28.0",
//                "Accept-Encoding": "gzip, deflate",
//                "Accept": "*/*",
//                "Connection": "keep-alive",
//                "X-Qx-Client-Application": "qiskit/0.39.2",
//                "X-Access-Token": access_token,
//    },

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Access-Token", accessToken);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://runtime-us-east.quantum-computing.ibm.com/programs");


        HttpEntity<?> entity = new HttpEntity<>(
                runtimeProgramDataJson,
                headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                String.class);

        LOG.debug("Runtime Program Create Response: {}", response.toString());

        // Parse to get runtime program id
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> runtimeCreateResponseData = mapper.readValue(response.getBody(), Map.class);

        LOG.debug("Got runtime program id: {}", runtimeCreateResponseData.get("id"));

        return runtimeCreateResponseData.get("id");
    }

    @PayloadRoot(namespace = QiskitRuntimeConstants.NAMESPACE_URI, localPart = "createRequest")
    public void create(@RequestPayload CreateRequest request, MessageContext messageContext) {
        LOG.info("Create request received!");

        SoapUtil.logHeaders(messageContext);
        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);

        String runtimeProgramUrl = null;
        String metaDataUrl = null;

        for (var namespaces : openToscaHeaders.deploymentArtifacts().entrySet()) {
            for (var da : namespaces.getValue().entrySet()) {
                if (da.getKey().equals("runtime_program.py")) {
                    runtimeProgramUrl = da.getValue();
                }
                if (da.getKey().equals("meta.json")) {
                    metaDataUrl = da.getValue();
                }
            }
        }

        if (runtimeProgramUrl == null || metaDataUrl == null) {
            LOG.error("at least one DA is missing: runtime_program.py and meta.json are required");

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Error: at least one DA is missing: runtime_program.py and meta.json are required");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        LOG.info("found both DAs: runtime_program.py, meta.json");
        LOG.info("downloading runtime program");
        String runtimeProgram, metaData = null;
        try {
            runtimeProgram = getDAFile(runtimeProgramUrl, openToscaHeaders);
            metaData = getDAFile(metaDataUrl, openToscaHeaders);
        } catch (IOException e) {
            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError(e.getMessage());

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        LOG.info("size of downloaded runtime program: {}", runtimeProgram.length());

        String apiKey = request.getIBMQTOKEN();

        if (apiKey.equals("")) {
            LOG.error("API key is empty");

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Error: API key is empty");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        String token = null;
        try {
            token = getAccessToken(apiKey);
        } catch(RestClientResponseException err) {
            LOG.error("Login with IBMQ failed: {}", err.getMessage());

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Login with IBMQ failed" + err.getMessage());

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        // Encode runtime program base64
        String runtimeProgramDataJson = null;
        try {
            runtimeProgramDataJson = getRuntimeProgramData(runtimeProgram, metaData);
        } catch (JsonProcessingException e) {
            LOG.error("Error when processing runtime program json: {}", e.getMessage());

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Error when processing runtime program json" + e.getMessage());

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        String programID = null;
        try {
            programID = uploadRuntimeProgram(runtimeProgramDataJson, token);
        } catch (RestClientResponseException e) {
            LOG.error("Uploading to IBMQ failed: {}", e.getMessage());

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Uploading to IBMQ failed: " + e.getMessage());

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        } catch (JsonProcessingException e) {
            LOG.error("Error when parsing runtime create response: {}", e.getMessage());

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Error when parsing runtime create response: " + e.getMessage());

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        InvokeResponse invokeResponse = new InvokeResponse();
        invokeResponse.setMessageID(openToscaHeaders.messageId());
        invokeResponse.setResult("finished");
        invokeResponse.setRuntimeProgramID(programID);

        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }

    @PayloadRoot(namespace = QiskitRuntimeConstants.NAMESPACE_URI, localPart = "terminateRequest")
    public void terminate(@RequestPayload TerminateRequest request, MessageContext messageContext) {
        LOG.info("Terminate request received");

        SoapUtil.logHeaders(messageContext);
        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);

        InvokeResponse invokeResponse = new InvokeResponse();
        invokeResponse.setMessageID(openToscaHeaders.messageId());
        invokeResponse.setResult("finished");

        // Send response
        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }
}
