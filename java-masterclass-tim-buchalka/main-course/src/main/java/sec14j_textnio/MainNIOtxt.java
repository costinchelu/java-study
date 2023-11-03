package sec14j_textnio;

/*
*
* CHANNELS = is the DataSource we're reading from and writing to (file, socket, etc)
* BUFFERS = is the container for the block of data that we want to read or write
*       they can only hold one type of data (are typed)
* SELECTORS = they allow a single thread to manage the I/O for multiple channels
*/


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MainNIOtxt {
    public static void main(String[] args) {
        try {
//            FileInputStream file = new FileInputStream("JavaNIOtxtfile.txt");
//            FileChannel channel = file.getChannel();
//                                    // FileChannel in this case is opened for reading (because we have a FileInputStream)
//
            Path dataPath = FileSystems.getDefault().getPath("JavaNIOtxtfile.txt");

            //to write a txt file sequentially:
            Files.write(dataPath, "Line 4\nLine 5\n".getBytes("UTF-8"), StandardOpenOption.APPEND);
                                      // we are converting the String with Bytes type (because the method requires bytes)
                                      // .APPEND is optional. But if the file doesn't exists, it will create one. If file exists, the method will truncate it

                                      // to read a txt file sequentially:
            List<String> lines = Files.readAllLines(dataPath);      //UTF-8 is assumed. But can be changed
            for(String line : lines) {
                System.out.println(line);
            }
        }

        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
