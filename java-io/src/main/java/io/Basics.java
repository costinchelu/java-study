package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Basics {

    private static final String PATH1 = "C:\\WORK\\in-out\\ocp-io\\testfile1.txt";
    private static final String CHILD11 = "C:\\WORK\\in-out\\ocp-io";
    private static final String CHILD12 = "testfile1.txt";
    private static final String PATH2 = "java-io\\src\\main\\resources\\test1.txt";
    private static final String PATH3 = "C:\\WORK\\in-out";

    public static void main(String[] args) {

        // getting properties
        System.out.println(System.getProperty("file.separator"));
        System.out.println(FileSystems.getDefault().getSeparator());


        /*
        A uniform resource identifier (URI) is a string of characters that identifies a resource.
        It begins with a schema that indicates the resource type, followed by a path value such as
        file:// for local file systems and http://, https://, and ftp:// for remote file systems.
         */
        // creating/getting paths
        Path path1 = Path.of(PATH1);
        Path path2 = Paths.get(PATH2);
        Path path3 = FileSystems.getDefault().getPath(PATH3);
        Path path4 = Path.of("C:\\WORK\\in-out\\ocp-io");

        System.out.println(path4.resolve("testfile1.txt"));
        System.out.println("PATH1 - exists: " + Files.exists(path1));
        System.out.println("PATH1 - get filename: " + path1.getFileName());
        System.out.println("Is PATH1 absolute: " + path1.isAbsolute());
        System.out.println("Absolute path for path2: " + path2.getParent().normalize().toAbsolutePath());
        try {
            System.out.println(Paths.get(".").toRealPath());
            System.out.println(Paths.get(".").toAbsolutePath());
        } catch (IOException ignored) {}


        // loading files
        File folder1 = new File(PATH3);
        File file2 = new File(PATH2);
        File file3 = new File(CHILD11, CHILD12);
        File file4 = new File(folder1, CHILD12);



        // File and Path both reference locations on disk, so we can convert between them
        File file = new File(PATH3);
        Path nowPath = file.toPath();
        File backToFile = nowPath.toFile();


        io();

        nio();

        printPathInformation(path1);
    }

    public static void io() {
        var file = new File(PATH2);
        if (file.exists()) {
            System.out.println("Absolute Path of file var: " + file.getAbsolutePath());
            System.out.println("Is file a Directory: " + file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());
            if (file.isFile()) {
                System.out.println("Size: " + file.length());
                System.out.println("Last Modified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    System.out.println(" " + subfile.getName());
                }
            }
        }
    }

    public static void nio() {
        var path = Path.of(PATH1);
        if (Files.exists(path)) {
            System.out.println("Absolute Path: " + path.toAbsolutePath());
            System.out.println("Is Directory: " + Files.isDirectory(path));
            System.out.println("Parent Path: " + path.getParent());
            try {
                if (Files.isRegularFile(path)) {
                    System.out.println("Size: " + Files.size(path));
                    System.out.println("Last Modified: " + Files.getLastModifiedTime(path));
                } else {
                    try (Stream<Path> stream = Files.list(path)) {
                        stream.forEach(p -> System.out.println(" " + p.getFileName()));
                    }
                }
            } catch (IOException ignored) {}
        }
    }

    public static void printPathInformation(Path path) {
        System.out.println("Filename is: " + path.getFileName());
        System.out.println(" Root is: " + path.getRoot());
        Path currentParent = path;
        while((currentParent = currentParent.getParent()) != null)
            System.out.println(" Current parent is: " + currentParent);
        System.out.println();
    }
}
