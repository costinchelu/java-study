package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteFiles {

    private static final String PATH1 = "C:\\WORK\\in-out\\ocp-io\\toDelete.txt";
    private static final String PATH2 = "C:\\WORK\\in-out\\ocp-io\\toDelete";

    public static void main(String[] args) {
        try {
            // file delete
            Files.delete(Path.of(PATH1));

            // delete empty folder
            Files.deleteIfExists(Paths.get(PATH2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
