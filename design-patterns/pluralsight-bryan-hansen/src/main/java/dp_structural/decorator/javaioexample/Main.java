package dp_structural.decorator.javaioexample;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        // everiday example:

        File file = new File("./output.txt");
        try {
            file.createNewFile();

            // fileOutputStream = decorator
            OutputStream outputStream = new FileOutputStream(file);

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeChars("text");

            dataOutputStream.close();
            outputStream.close();

            // DataOutputStream -> FileOutputStream -> OutputStream
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

/*
- wrapper
- add behavior without affecting others
- single responsability principle
- inheritance based

Examples:
java.io.inputStream
UI components
 */