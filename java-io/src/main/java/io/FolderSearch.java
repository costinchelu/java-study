package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FolderSearch {

    public static void main(String[] args) throws IOException {
        searchFor(".java");
    }

    public static void searchFor(String fileExtension) throws IOException {
        Path path = Paths.get("C:\\WORK");
        long minSize = 1_000;
        try (var s = Files.find(path, 6,
                (p, a) ->
                        a.isRegularFile()
                                && p.toString().endsWith(fileExtension)
                                && a.size() > minSize)) {
            s.forEach(System.out::println);
        }
    }
}
