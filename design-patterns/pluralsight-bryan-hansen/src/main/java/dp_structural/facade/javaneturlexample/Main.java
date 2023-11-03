package dp_structural.facade.javaneturlexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        try {
            URL url = new URL("http", "pluralsight.com", 80, "/authors/bryan-hansen");

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
- make an API easier to use
- reduce dependencies on outside code
- simplify the interface or client usage
- usually a refactoring pattern

Example:

java.net.URL

 */