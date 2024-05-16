package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class Readers {

    public static void main(String[] args) {
        var reader = new BufferedReader(new StringReader("239"));
        System.out.println(readNumber(reader));
    }

    private static int readNumber(BufferedReader reader) {
        try (reader) {
            var line = reader.readLine();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            System.out.println("Not a number");
            return -1;
        } catch (IOException ioe) {
            System.out.println("File not found");
            return -2;
        }
    }
}
