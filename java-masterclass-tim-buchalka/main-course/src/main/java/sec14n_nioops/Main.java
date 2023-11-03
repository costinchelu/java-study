package sec14n_nioops;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {
    public static void main(String[] args) {

        try {
            // COPY FILES
            Path sourceFile = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "file1copy.txt");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
            // the optional third parameter to the copy method, let's us copy a file even if that file already exists
            // it will overwrite the file

            sourceFile = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "Dir1");
            copyFile = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "Dir4");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
                        // with this, Dir4 was created, but no actual files where copied

            // MOVE FILES
            Path fileToMove = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "file1copy.txt");
            Path destination = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "Dir1", "file1copy.txt");
            Files.move(fileToMove, destination);
                         // we have to specify the full path to the destination

            // RENAME FILES
            Path fileToRename = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "file1.txt");
            Path renamedFile = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "file2.txt");
            Files.move(fileToRename, renamedFile);

            // DELETE FILES
            Path fileToDelete = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "Dir1", "file1copy.txt");
            Files.deleteIfExists(fileToDelete);
                        // to delete directories they have to be empty

            // CREATE AN EMPTY FILE
            Path fileToCreate = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "file2.txt");
            Files.createFile(fileToCreate);

            // CREATE AN EMPTY FOLDER
            Path dirToCreate = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "Dir5");
            Files.createDirectory(dirToCreate);

            // CREATE A (SOME) EMPTY FOLDER(S) IN A TREE STRUCTURE
            Path dirsToCreate = FileSystems.getDefault().getPath("JavaNIOcopyExamples\\Dir2\\Dir21\\Dir22\\Dir23\\Dir24");
            Files.createDirectories(dirsToCreate);

            // GET FILE ATTRIBUTES (METADATA)
            Path filePath = FileSystems.getDefault().getPath("JavaNIOcopyExamples", "Dir1\\file1.txt");
            long size = Files.size(filePath);
            System.out.println("Size of file1.txt = " + size);  // gets file size
            System.out.println("Last modified: " + Files.getLastModifiedTime(filePath));

            // All ATTRIBUTES
            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);  // returns an instance that implements the basic file attributes interface
            System.out.println("SIZE: " + attrs.size());
            System.out.println("LAST MODIFIED: " + attrs.lastModifiedTime());
            System.out.println("CREATED: " + attrs.creationTime());
            System.out.println("IS A DIRECTORY: " + attrs.isDirectory());
            System.out.println("IS A REGULAR FILE: " + attrs.isRegularFile());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
