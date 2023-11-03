package sec20b_tcpservermultipleclients;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(5000)) {

            while(true) {
                new Echoer(serverSocket.accept()).start();
                /* an equivalent of this would be:

                Socket socket = serverSocket.accept();
                Echoer echoer = new Echoer(socket);
                echoer.start();


                    we're starting a new thread every time we accept a connection
                * */
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
