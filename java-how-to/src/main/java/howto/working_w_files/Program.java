package howto.working_w_files;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;


public class Program {

    public static void main(String[] args) {

        setAFilePath();

        ListToWrite list = new ListToWrite();

        System.out.println("Writing file");
        WriteFile.writeFile(list.getStringList());

        System.out.println("Reading file");
        List<String> listFromFile = ReadFile.readFile();
        for(String line : listFromFile) {
            System.out.println(line);
        }
    }

    public static void setAFilePath() {
        // Relative path of the project:
        Path path = FileSystems.getDefault().getPath("");
        // absolute path
        System.out.println("Absolute path : " + path.toAbsolutePath());
    }
}