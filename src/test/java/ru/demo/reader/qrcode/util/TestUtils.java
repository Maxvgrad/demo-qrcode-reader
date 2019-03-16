package ru.demo.reader.qrcode.util;

import javax.validation.constraints.NotNull;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {

    private TestUtils() {}

    public static byte[] readBytes(@NotNull String path) {
        try {
            Path path0 = Paths.get(ClassLoader.getSystemResource(path).toURI());
            return Files.readAllBytes(path0);
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
