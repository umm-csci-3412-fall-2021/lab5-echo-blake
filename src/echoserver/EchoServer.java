package echoserver;
import java.net.*;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.OutputStream;

public class EchoServer {

   public static final int portNumber = 6013;

   public static void main(String[] args) {
       try {
           ServeSocket socket = new ServerSocket(portNumber);

           // Runs continuously
           while (true) {
               
               // Opens the client socket
               Socket client = socket.accept();
               System.out.println("Got a request!");

               // Create socket input and output streams
               InputStream socketInput = client.getInputStream();
               OutputStream socketOutput = client.getOutputStream();

               int data;
               // Continuously reads bytes from socket input stream until there are no more 
               while ((data = socketInput.read()) != -1) {
                   
                    // Writes byte back to the client
                    socketOutput.write(data);
                    socketOutput.flush();
               }

               client.shutDownOutput();
               
               client.close();
           }
           
       } catch (IOException ioe) {
         System.out.println("We caught an unexpected exception");
         System.err.println(ioe);
       }
   } 
}