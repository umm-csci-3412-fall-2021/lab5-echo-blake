package echoserver;
import java.net.*;
import java.io.*;

public class EchoServer {

   public static final int portNumber = 6013;

   public static void main(String[] args) {
       try {
           ServeSocket socket = new ServerSocket(portNumber);

           while (true) {
               Socket client = socket.accept();
               System.out.println("Got a request!");

               OutputStream output = client.getOutputStream();
               output.write(System.out);

               client.close();
           }
           
       } catch (IOException ioe) {
         System.out.println("We caught an unexpected exception");
         System.err.println(ioe);
       }
   } 
}