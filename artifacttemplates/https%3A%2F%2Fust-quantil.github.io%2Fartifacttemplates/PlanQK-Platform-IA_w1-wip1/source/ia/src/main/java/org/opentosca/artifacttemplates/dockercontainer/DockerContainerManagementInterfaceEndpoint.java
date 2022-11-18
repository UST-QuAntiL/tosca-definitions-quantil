package org.opentosca.artifacttemplates.dockercontainer;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

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
import static org.opentosca.artifacttemplates.dockercontainer.FileHandler.getFile;
import static org.opentosca.artifacttemplates.dockercontainer.FileHandler.getUrl;

import de.stoneone.planqk.api.invoker.ApiClient;

@Endpoint
public class DockerContainerManagementInterfaceEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(DockerContainerManagementInterfaceEndpoint.class);

    @PayloadRoot(namespace = DockerContainerConstants.NAMESPACE_URI, localPart = "createRequest")
    public void create(@RequestPayload CreateRequest request, MessageContext messageContext) {
        LOG.info("Create request received!");

        SoapUtil.logHeaders(messageContext);
        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);

        String apiKey = "";  // TODO: get api key
        ApiClient apiClient = new ApiClient("apiKey", apiKey);
        apiClient.setFeignBuilder(apiClient.getFeignBuilder().decoder(new CustomDecoder(apiClient.getObjectMapper())));

        ServicePlatformServicesApi servicesApi = apiClient.buildClient(ServicePlatformServicesApi.class);

        String serviceName = "Test service";  // TODO: get service name
        String type = "MANAGED";
        String description = "service description";  // TODO: get service description
        File userCode = new File(""); // TODO: get user code file
        File apiDefinition = new File("");  // TODO: get api definition file
        String quantumBackend = "NONE";

        ServiceDto service = servicesApi.createService(serviceName, type, quantumBackend, description, null, null, userCode, apiDefinition);

        InvokeResponse invokeResponse = new InvokeResponse();

        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }

    @PayloadRoot(namespace = DockerContainerConstants.NAMESPACE_URI, localPart = "terminateRequest")
    public void terminate(@RequestPayload TerminateRequest request, MessageContext messageContext) {
        LOG.info("Terminate request received");

        SoapUtil.logHeaders(messageContext);
        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);
        InvokeResponse invokeResponse = new InvokeResponse();

        // Send response
        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }
}
