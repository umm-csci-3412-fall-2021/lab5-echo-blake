package echoserver;
import java.net.*;
import java.io.*;
import java.net.Socket;
import java.rmi.ConnectException;
import java.io.InputStream;
import java.io.OutputStream;

public class EchoClient {

    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        server = args[0];

        try {
            Socket echoSocket = new Socket(server, portNumber);
            
            // Create both standard inputs/outputs as well as socket stream inputs/outputs
            InputStream socketInput = echoSocket.getInputStream();
            InputStream stdInput = System.in;
            OutputStream socketOutput = echoSocket.getOutputStream();
            OutputStream stdOutput = System.out;

            int data;
            // Continuously reads bytes from standard input until that input ends
            while ((data = stdInput.read()) != -1) {
                
                // Sends/Writes bytes from standard input to server through the socket
                socketOutput.write(data);
                socketOutput.flush();

                // Reads bytes from server through the socket and prints them out
                data = socketInput.read();
                stdOutput.write(data);
                stdOutput.flush();
            }

            echoSocket.shutdownOutput();

            echoSocket.close();

        } catch (ConnectException ce) {
          System.out.println("We were unable to connect to " + server);
          System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
          System.out.println("We caught an unexpected exception");
          System.err.println(ioe);
        }
    }
}