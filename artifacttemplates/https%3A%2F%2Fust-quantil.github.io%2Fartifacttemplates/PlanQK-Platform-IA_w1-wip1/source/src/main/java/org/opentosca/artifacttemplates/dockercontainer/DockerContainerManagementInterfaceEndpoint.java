package org.opentosca.artifacttemplates.dockercontainer;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.opentosca.artifacttemplates.OpenToscaHeaders;
import org.opentosca.artifacttemplates.SoapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import static org.opentosca.artifacttemplates.dockercontainer.FileHandler.downloadFile;
import static org.opentosca.artifacttemplates.dockercontainer.FileHandler.getFile;
import static org.opentosca.artifacttemplates.dockercontainer.FileHandler.getUrl;

@Endpoint
public class DockerContainerManagementInterfaceEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(DockerContainerManagementInterfaceEndpoint.class);

    @PayloadRoot(namespace = DockerContainerConstants.NAMESPACE_URI, localPart = "createRequest")
    public void create(@RequestPayload CreateRequest request, MessageContext messageContext) {
        LOG.info("Create request received!");

        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);
        InvokeResponse invokeResponse = new InvokeResponse();

        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }

    @PayloadRoot(namespace = DockerContainerConstants.NAMESPACE_URI, localPart = "terminateRequest")
    public void terminate(@RequestPayload TerminateRequest request, MessageContext messageContext) {
        LOG.info("Terminate request received");

        OpenToscaHeaders openToscaHeaders = SoapUtil.parseHeaders(messageContext);
        InvokeResponse invokeResponse = new InvokeResponse();

        // Send response
        SoapUtil.sendSoapResponse(invokeResponse, InvokeResponse.class, openToscaHeaders.replyTo());
    }
}
