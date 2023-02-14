package io.github.ust.quantil.artifacttemplates.qiskitruntime;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

public class FileHandler {

    protected static String downloadFile(URI uri) throws IOException {
        final URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("Accept", "application/octet-stream");

        try (final InputStream input = connection.getInputStream()) {
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        }
    }
}
