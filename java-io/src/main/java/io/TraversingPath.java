package io;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TraversingPath {

    public static void main(String[] args) {
        var o = new TraversingPath();
        try {
            long size = o.getPathSize(Paths.get("C:\\WORK"));
            System.out.format("Total Size: %.2f megabytes", (size / 1000000.0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    public long getPathSize(Path source) throws IOException {
        try (Stream<Path> s = Files.walk(source)) {
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }
}
