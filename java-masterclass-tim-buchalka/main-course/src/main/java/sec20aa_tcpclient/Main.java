package sec20aa_tcpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    try(Socket socket = new Socket("localhost", 5000)) {

	        // to set a timer on the connection:
            socket.setSoTimeout(10000);
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do {
                System.out.println("Enter string to be echoed (press \"exit\" to finish): ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);
                if(!echoString.equals("exit")) {
                    response = echoes.readLine();
                    System.out.println(response);
                }
            } while(!echoString.equals("exit"));

        } catch (SocketTimeoutException e) {
            System.out.println("10 sec passed.");
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}

/*
THIS SHOULD BE RUN FROM ANOTHER PROJECT(ANOTHER WINDOW) PARALLEL WITH THE SERVER - SEC20A OR SEC20B

* Instead of the localhost string we can use "127.0.0.1"
*
* We have to specify the same port number as the server because the server is listening on port 5000
* */
