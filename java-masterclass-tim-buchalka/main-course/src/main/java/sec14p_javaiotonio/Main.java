package sec14p_javaiotonio;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

// mapping IO to NIO methods

public class Main {
    public static void main(String[] args) {

        // getting path with IO
        File file = new File("C:\\Users\\BeaST\\Desktop\\Udemy (Tim Buchalka)\\JavaNIOcopyExamples\\mapIO.txt");
        Path convertedPath = file.toPath();
        System.out.println("convertedPath = " + convertedPath);

        File parent = new File("C:\\Users\\BeaST\\Desktop\\Udemy (Tim Buchalka)\\JavaNIOcopyExamples");
        File resolvedFile = new File(parent, "mapIO.txt");
        System.out.println(resolvedFile.toPath());

        resolvedFile = new File("C:\\Users\\BeaST\\Desktop\\Udemy (Tim Buchalka)\\JavaNIOcopyExamples" + "\\mapIO.txt");
        System.out.println(resolvedFile.toPath());

        // NIO version
        Path parentPath = Paths.get("C:\\Users\\BeaST\\Desktop\\Udemy (Tim Buchalka)\\JavaNIOcopyExamples");
        Path childRelativePath = Paths.get("mapIO.txt");
        System.out.println(parentPath.resolve(childRelativePath));



        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("Working directory = " + workingDirectory.getAbsolutePath());

        System.out.println("_____print Dir1 contents using list() _______");

        File dir2File = new File(workingDirectory, "\\JavaNIOcopyExamples\\Dir2");
        String[] dir2Contents = dir2File.list();
        for(int i = 0; i < dir2Contents.length; i++) {
            System.out.println("i = " + i + ": " + dir2Contents[i]);
                        // only 1 level deep
        }

        // equivalent method
        System.out.println("____print Dir2 contents using listFiles()____");
        File[] dir2Files = dir2File.listFiles();
        for(int i = 0; i < dir2Files.length; i++) {
            System.out.println("i = " + i + ": " + dir2Files[i].getName());
        }
    }
}

/*
                USE JAVA.NIO WHEN WORKING WITH A FILE SYSTEM AND JAVA.IO.STREAMS WHEN READING AND WRITING FILE CONTENTS
 */