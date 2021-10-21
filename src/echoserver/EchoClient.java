package echoserver;
import java.net.*;
import java.io.*;

public class EchoClient {

    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        server = args[0];

        try {
            Socket echoSocket = new Socket(server, portNumber);
            InputStream input = echoSocket.getInputStream();
            String reader = input.read(System.in);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (ConnectException ce) {
          System.out.println("We were unable to connect to " + server);
          System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
          System.out.println("We caught an unexpected exception");
          System.err.println(ioe);
        }
    }
}