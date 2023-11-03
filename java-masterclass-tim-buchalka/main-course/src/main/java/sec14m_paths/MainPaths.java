package sec14m_paths;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Paths

public class MainPaths {
    public static void main(String[] args) {

        Path workingPath = FileSystems.getDefault().getPath("WorkingDirectoryFile.txt");
                                // we can just specify the relative path because the file which we are interested in
                                // is located in the working directory
        printFile(workingPath);

        Path otherPath = FileSystems.getDefault().getPath("files", "SubdirectoryFile.txt");
        printFile(otherPath);

        //this will throw an exception if the file is not present at location: C:\Users\BeaST\Desktop\OutThere.txt
        otherPath = Paths.get("C:\\Users\\BeaST\\Desktop\\OutThere.txt");
        printFile(otherPath);

        otherPath = Paths.get(".");
        System.out.println(otherPath.toAbsolutePath());
                                    // prints the absolute path
                                    // without Paths.get(".") this will print the relative path
                                    // (the last path it was associated with)

        // in this example ".", "..", "files" are redundant. It is something like: default directory / subfolder "files" / back up to default directory
        Path path2 = FileSystems.getDefault().getPath(".", "files", "..", "files", "SubdirectoryFile.txt");
        System.out.println(path2.normalize().toAbsolutePath());
        printFile(path2.normalize());
                            // when we get a path from a user, it's a good idea to normalize it


        // Path doesn't understand the file system so we can point it to an nonexistent file:
        Path path3 = FileSystems.getDefault().getPath("fileThatDoesntExists.txt");
        System.out.println(path3.toAbsolutePath());
                // it will actually print full path
                // no exceptions will be thrown
                // exceptions will appear if we want to make some i/o operations

        Path path4 = Paths.get("C:\\AFolder", "noFile.txt");
        System.out.println(path4.toAbsolutePath());

        // it is a good idea to check if a file or directory exists
        otherPath = FileSystems.getDefault().getPath("files");
        System.out.println("(otherPath)Exists = " + Files.exists(otherPath));
        System.out.println("(path4)Exist = " + Files.exists(path4));
        // we can check also Files.isReadable(), Files.isWriteable(), Files.isExecutable()
    }

    private static void printFile(Path path) {
        try(BufferedReader fileReader = Files.newBufferedReader(path)) {

            String line;
            while((line = fileReader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
