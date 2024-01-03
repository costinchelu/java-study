package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyWithStream {

    private static final String PATH1 = "C:\\WORK\\in-out\\ocp-io\\testfile1.txt";
    private static final String PATH2 = "C:\\WORK\\in-out\\ocp-io\\testfile2.txt";
    private static final String CHILD12 = "testfile1.txt";

    public static void main(String[] args) {

        try (var is = new FileInputStream(PATH1)) {
            // write to a file
            Files.copy(is, Paths.get(PATH2), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Paths.get(PATH2), System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
