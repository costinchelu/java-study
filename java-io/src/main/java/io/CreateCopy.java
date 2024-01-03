package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class CreateCopy {

    private static final String PATH1 = "C:\\WORK\\in-out\\ocp-io\\testDir";
    private static final String CHILD11 = "C:\\WORK\\in-out\\ocp-io";
    private static final String CHILD12 = "testfile1.txt";

    public static void main(String[] args) {
        Path path1 = Path.of(PATH1);
        try {
            Files.createDirectories(path1);
            Files.copy(
                    Path.of(CHILD11).resolve(Path.of(CHILD12)),
                    path1.resolve(CHILD12),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        copyPath(Path.of("C:\\WORK\\C"), Path.of("C:\\WORK\\in-out\\newC"));
    }

    /*
    copy a whole structure (folder + files)
    The method first copies the path, whether a file or a directory. If it is a directory,
    only a shallow copy is performed.
    Next, it checks whether the path is a directory and, if it is, performs
    a recursive copy of each of its elements.
    the JVM will not follow symbolic links when using the list() method.
     */
    public static void copyPath(Path source, Path target) {
        try {
            Files.copy(source, target);
            if (Files.isDirectory(source))
                try (Stream<Path> s = Files.list(source)) {
                    s.forEach(p -> copyPath(p, target.resolve(p.getFileName())));
                }
        } catch(IOException ignored) {}
    }
}
