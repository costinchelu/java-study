package howto.working_w_files;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;

public class WriteFile {

    public static final String IO_PATH = "lista.txt";

    public static void writeFile(List<String> listToWrite) {

        FileWriter fWriter = null;

        try {
            fWriter = new FileWriter(IO_PATH);

            for (String line : listToWrite) {
                fWriter.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            if (fWriter != null) {
                try {
                    fWriter.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: " + e.getMessage());
                }
            }
        }
    }
}
