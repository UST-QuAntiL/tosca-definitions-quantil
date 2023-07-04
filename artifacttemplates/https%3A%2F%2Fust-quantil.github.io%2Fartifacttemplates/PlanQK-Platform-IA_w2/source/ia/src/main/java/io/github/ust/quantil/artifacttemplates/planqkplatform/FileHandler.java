package io.github.ust.quantil.artifacttemplates.planqkplatform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;

import com.google.common.io.Files;

public class FileHandler {

    protected static File downloadFile(final String url) {
        try {
            final URI uri = new URI(url);

            final String[] pathSplit = uri.getRawPath().split("/");
            final String fileName = pathSplit[pathSplit.length - 1];

            final File tempDir = Files.createTempDir();
            final File tempFile = new File(tempDir, fileName);

            downloadFile(uri, tempFile);
            return tempFile;
        } catch (final URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected static void downloadFile(URI uri, File tempFile) throws IOException {
        final URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("Accept", "application/octet-stream");

        try (final InputStream input = connection.getInputStream()) {
            final byte[] buffer = new byte[4096];
            int n;

            try (final OutputStream output = new FileOutputStream(tempFile)) {
                while ((n = input.read(buffer)) != -1) {
                    output.write(buffer, 0, n);
                }
            }
        }
    }
}
