package org.opentosca.artifacttemplates.dockercontainer;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import de.stoneone.planqk.api.ServicePlatformServicesApi;
import de.stoneone.planqk.api.model.ServiceDto;
import org.apache.commons.io.FileUtils;
import org.opentosca.artifacttemplates.OpenToscaHeaders;
import org.opentosca.artifacttemplates.SoapUtil;
import org.opentosca.artifacttemplates.dockercontainer.feign.CustomDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import static org.opentosca.artifacttemplates.dockercontainer.FileHandler.downloadFile;

import de.stoneone.planqk.api.invoker.ApiClient;

@Endpoint
public class DockerContainerManagementInterfaceEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(DockerContainerManagementInterfaceEndpoint.class);

    @PayloadRoot(namespace = DockerContainerConstants.NAMESPACE_URI, localPart = "createRequest")
    public void create(@RequestPayload CreateRequest request, MessageContext messageContext) {
        LOG.info("Create request received!");

        SoapUtil.logHeaders(messageContext);
        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);

        String userCodeURL = null;

        for (var namespaces : openToscaHeaders.deploymentArtifacts().entrySet()) {
            for (var da : namespaces.getValue().entrySet()) {
                if (da.getKey().equals("user_code.zip")) {
                    userCodeURL = da.getValue();
                }
            }
        }

        if (userCodeURL == null) {
            LOG.error("no DA found with file name user_code.zip");

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setResult("Error: no DA found with file name user_code.zip");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        LOG.info("found DA with file name user_code.zip");
        LOG.info("downloading user code");
        File userCode = downloadFile(userCodeURL);

        if (userCode == null) {
            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setResult("Error: could not download user_code.zip");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        LOG.info("size of downloaded user code: {}", userCode.length());

        String apiDefinitionURL = null;

        for (var namespaces : openToscaHeaders.deploymentArtifacts().entrySet()) {
            for (var da : namespaces.getValue().entrySet()) {
                if (da.getKey().equals("api_definition.json")) {
                    apiDefinitionURL = da.getValue();
                }
            }
        }

        File apiDefinition = null;

        if (apiDefinitionURL == null) {
            LOG.info("Optional DA with file name api_definition.json not found.");
        } else {
            LOG.info("Optional DA with file name api_definition.json found.");
            LOG.info("downloading api_definition.json");
            apiDefinition = downloadFile(apiDefinitionURL);

            if (apiDefinition == null) {
                LOG.info("could not download api_definition.json");
            } else {
                LOG.info("size of downloaded api_definition.json: {}", apiDefinition.length());
            }
        }

        String apiKey = request.getPlanqkApiKey();

        if (apiKey.equals("")) {
            LOG.error("API key is empty");

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setResult("Error: API key is empty");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        ApiClient apiClient = new ApiClient("apiKey", apiKey);
        apiClient.setFeignBuilder(apiClient.getFeignBuilder().decoder(new CustomDecoder(apiClient.getObjectMapper())));

        ServicePlatformServicesApi servicesApi = apiClient.buildClient(ServicePlatformServicesApi.class);

        String serviceName = request.getServiceName();
        String type = "MANAGED";
        String description = request.getServiceDescription();
        String quantumBackend = request.getQuantumBackend();

        ServiceDto service = servicesApi.createService(
                serviceName,
                type,
                quantumBackend,
                description,
                null,
                null,
                null,
                null,
                null,
                userCode,
                apiDefinition);

        InvokeResponse invokeResponse = new InvokeResponse();
        invokeResponse.setMessageID(openToscaHeaders.messageId());
        invokeResponse.setResult("finished");
        assert service.getId() != null;
        invokeResponse.setServiceID(service.getId().toString());

        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }

    @PayloadRoot(namespace = DockerContainerConstants.NAMESPACE_URI, localPart = "terminateRequest")
    public void terminate(@RequestPayload TerminateRequest request, MessageContext messageContext) {
        LOG.info("Terminate request received");

        SoapUtil.logHeaders(messageContext);
        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);

        String apiKey = request.getPlanqkApiKey();

        if (apiKey.equals("")) {
            LOG.error("API key is empty");

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Error: API key is empty");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        String serviceId = request.getServiceID();

        if (serviceId.equals("")) {
            LOG.error("Service ID is empty");

            InvokeResponse invokeResponse = new InvokeResponse();
            invokeResponse.setMessageID(openToscaHeaders.messageId());
            invokeResponse.setError("Error: Service ID is empty");

            SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
            return;
        }

        ApiClient apiClient = new ApiClient("apiKey", apiKey);
        apiClient.setFeignBuilder(apiClient.getFeignBuilder().decoder(new CustomDecoder(apiClient.getObjectMapper())));

        ServicePlatformServicesApi servicesApi = apiClient.buildClient(ServicePlatformServicesApi.class);
        servicesApi.deleteService(UUID.fromString(serviceId), null);

        InvokeResponse invokeResponse = new InvokeResponse();
        invokeResponse.setMessageID(openToscaHeaders.messageId());
        invokeResponse.setResult("finished");

        // Send response
        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }
}
