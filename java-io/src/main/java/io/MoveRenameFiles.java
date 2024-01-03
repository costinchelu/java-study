package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class MoveRenameFiles {

    private static final String PATH1 = "C:\\WORK\\in-out\\ocp-io\\testfile1.txt";
    private static final String PATH2 = "C:\\WORK\\in-out\\ocp-io\\testDir\\testfile2.txt";
    private static final String PATH3 = "C:\\WORK\\in-out\\ocp-io\\testDir\\testfile3.txt";

    public static void main(String[] args) {
        try {
            // moving a file
            Files.move(Path.of(PATH1), Path.of(PATH2), StandardCopyOption.REPLACE_EXISTING);

            // renaming works as move
            Files.move(Path.of(PATH2), Path.of(PATH3), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
