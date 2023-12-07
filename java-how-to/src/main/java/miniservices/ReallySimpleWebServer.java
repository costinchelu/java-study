package miniservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class ReallySimpleWebServer {

    public static void main(String[] args) {
        System.out.println("Listening for connection on port 8088 " +
                "\n (use browser to connect localhost:8088 or use telnet 127.0.0.1 8088)");

        while (true) {
            try (final ServerSocket server = new ServerSocket(8088);
                 Socket client = server.accept();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {

                String line = reader.readLine();
                while (!line.isEmpty()) {
                    System.out.println(line);
                    line = reader.readLine();
                }

                Date today = new Date();
                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + today;
                client.getOutputStream().write(httpResponse.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
