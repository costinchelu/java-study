package howto.working_w_files;


import java.util.List;


public class Program {

    public static void main(String[] args) {
        System.out.println("Writing file");
        WriteFile.writeFile(FileUtil.STRING_LIST);

        System.out.println("Reading file");
        List<String> listFromFile = ReadFile.readFile();
        for(String line : listFromFile) {
            System.out.println(line);
        }
    }
}