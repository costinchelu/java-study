package sec14o_nioreaddirectory;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;

// Read existing directory contents, separators, temp files, file stores, file trees operations

public class Main {
    public static void main(String[] args) {

        DirectoryStream.Filter<Path> filter =
                new DirectoryStream.Filter<Path>() {
                    public boolean accept(Path path) throws IOException {
                        return Files.isRegularFile(path);
                    }
                };
                // this filter will return only files (not directories)

        DirectoryStream.Filter<Path> filterFiles = p -> Files.isRegularFile(p);
                // same thing as the inline before but in a lambda expressions

        Path directory = FileSystems.getDefault().getPath("JavaNIOcopyExamples" + File.separator + "Dir2");
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(directory, filter)) {
            for(Path file: contents) {
                System.out.println(file.getFileName());
                                // Only returns the first level of the tree (direct descendants)
                                // instead using filter we can use a glob syntax to set a filter for file names or extensions
            }
        }
        catch (IOException | DirectoryIteratorException e) {
            System.out.println(e.getMessage());
        }


        // for determining separator ( \ on Windows / on UNIX):
        String separator = File.separator;
        System.out.println(separator);
                // or
        separator = FileSystems.getDefault().getSeparator();
        System.out.println(separator);
        // it would be a good idea to never hardcode a file separator

        // creating a temporary file:
        try {
            Path tempFile = Files.createTempFile("myapp", ".appext");
            System.out.println("Temporary file path = " + tempFile.toAbsolutePath());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }


        // Getting the file stores (from the default file systems)
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store : stores) {
            System.out.println("Volume name/Drive letter: " + store);
            System.out.println("Volume name: " + store.name());
        }

        System.out.println("____Print drive letters____");
        Iterable<Path> rootPath = FileSystems.getDefault().getRootDirectories();
        for(Path path : rootPath) {
            System.out.println(path);       // will print all drive letters
        }


        // traversing the tree:
        System.out.println("______Walking Tree for Dir2_____");
        Path dir2Path = FileSystems.getDefault().getPath("JavaNIOcopyExamples" + File.separator + "Dir2");
        try {
            Files.walkFileTree(dir2Path, new PrintNames());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        // copy contents of a tree
        System.out.println("______Copy Dir2 to Dir4/Dir2Copy_____");
        Path copyPath = FileSystems.getDefault().getPath("JavaNIOcopyExamples" + File.separator + "Dir4" +
                File.separator + "Dir2Copy");
        try {
            Files.walkFileTree(dir2Path, new CopyFiles(dir2Path, copyPath));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}


/*
GLOB SYNTAX

*.java	Matches a path that represents a file name ending in .java

*.*	Matches file names containing a dot

*.{java,class}	Matches file names ending with .java or .class

foo.?	Matches file names starting with foo. and a single character extension



 The * character matches zero or more characters of a name component without crossing directory boundaries.

 The ** characters matches zero or more characters crossing directory boundaries.

 The ? character matches exactly one character of a name component.

 The backslash character (\) is used to escape characters that would otherwise be interpreted as special characters. The expression \\ matches a single backslash and "\{" matches a left brace for example.

 The [ ] characters are a bracket expression that match a single character of a name component out of a set of characters. For example, [abc] matches "a", "b", or "c". The hyphen (-) may be used to specify a range so [a-z] specifies a range that matches from "a" to "z" (inclusive). These forms can be mixed so [abce-g] matches "a", "b", "c", "e", "f" or "g". If the character after the [ is a ! then it is used for negation so [!a-c] matches any character except "a", "b", or "c".

 Within a bracket expression the *, ? and \ characters match themselves. The (-) character matches itself if it is the first character within the brackets, or the first character after the ! if negating.

 The { } characters are a group of subpatterns, where the group matches if any subpattern in the group matches. The "," character is used to separate the subpatterns. Groups cannot be nested.

 Leading period/dot characters in file name are treated as regular characters in match operations. For example, the "*" glob pattern matches file name ".login". The Files.isHidden(java.nio.file.Path) method may be used to test whether a file is considered hidden.

 All other characters match themselves in an implementation dependent manner. This includes characters representing any name-separators.

 The matching of root components is highly implementation-dependent and is not specified.
 */



