package day04;

import java.net.*;
import java.io.*;
import java.util.*;

public class ServerMain {

   public static void main(String[] args) throws IOException {

      Thread threadName = new Thread();
      // Set the default port to 3000
      int port = 3000;
      if (args.length > 0)
         port = Integer.parseInt(args[0]);

      // Create a server port, it's a TCP, name is server
      ServerSocket server = new ServerSocket(port);
    
      //While loop to make sure that server never terminates
      while (true) {
      // Wait for an incoming connection, because .accept() only listens for connection, it auto connects via accept when client tries to connect
      // Once connected from the client, we get a socket
      System.out.printf("[%s]Waiting for connection on port %d\n", threadName, port);
      Socket sock = server.accept(); // very essential, accepts connection, cant accept a 2nd connection if server is busy with 1st one
        //once accepted, this line creates a sock dedicated to the connecting client
        //another client can connect, where each sock object represents a client. We only need one accept.
        //First client to connect will be the first to be processed by the server

      System.out.println("Got a new connection");

      // Get the input stream, open the input stream first while client does the reverse
      // Safer to do this way
      InputStream is = sock.getInputStream();
      Reader reader = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(reader);

      // Get the output stream
      OutputStream os = sock.getOutputStream();
      Writer writer = new OutputStreamWriter(os);
      BufferedWriter bw = new BufferedWriter(writer);

      // Read from the client
      // If 1st client types nothing, server will wait while 2nd client is stuck at waiting line
      // Basically, the first to connect may not be the first to give an input, so above situation may occur
      // To deal with this we can use threads or other methods

      String fromClient = br.readLine();

      System.out.printf(">>> CLIENT: %s\n", fromClient);

      // Process the data
      fromClient = (new Date()).toString() + " " + fromClient.toUpperCase();

      // Write result back to client
      bw.write(fromClient);
      bw.newLine();
      bw.flush();
      os.flush();

      os.close();
      is.close();
      sock.close();

            }

        }
        
    }
//Main thread
//How to have multiple clients? Have multiple threads
//Threads are uncontrollable, running wild
//Threads need to know entry point, to know where to start running (look for public void run(){})
//How do we guarantee an object has public void run? -> create a "runnable" interface containing a method "runnable"
//Every thread will be different depending on what we want it to do


// version to let us continuously take in inputs
// package day04;

// import java.io.*;
// import java.net.*;
// import java.util.*;

// public class ServerMain {

//     public static void main(String[] args) throws IOException {
//         int port = 3000;
//         if (args.length > 0)
//             port = Integer.parseInt(args[0]);

//         ServerSocket server = new ServerSocket(port);
//         System.out.printf("Server started on port %d\n", port);

//         // Continuous loop to accept clients
//         while (true) {
//             Socket sock = server.accept(); // Wait for a connection
//             System.out.println("Got a new connection");

//             // Handle client connection in a new thread
//             new Thread(() -> handleClient(sock)).start();
//         }
//     }

//     private static void handleClient(Socket sock) {
//         try {
//             InputStream is = sock.getInputStream();
//             Reader reader = new InputStreamReader(is);
//             BufferedReader br = new BufferedReader(reader);

//             OutputStream os = sock.getOutputStream();
//             Writer writer = new OutputStreamWriter(os);
//             BufferedWriter bw = new BufferedWriter(writer);

//             String fromClient;
//             while ((fromClient = br.readLine()) != null) { // Keep reading from the client
//                 System.out.printf(">>> CLIENT: %s\n", fromClient);
//                 String response = (new Date()).toString() + " " + fromClient.toUpperCase();
//                 bw.write(response);
//                 bw.newLine();
//                 bw.flush();
//             }
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             try {
//                 sock.close(); // Close the socket when done
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//     }
// }
