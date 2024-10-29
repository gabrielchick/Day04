package day04;

import java.io.*;
import java.net.*;

public class ClientMain {

   public static void main(String[] args) throws IOException {

      // Set the default port to 3000
      int port = 3000;
      if (args.length > 0)
         port = Integer.parseInt(args[0]);

      // Create a connection to the server
      // Connect to the port on the server
      // localhost - 127.0.0.1
      System.out.println("Connecting to the server");
      //new socket where we want to connect to localhost (local machine) at the specified port
      Socket sock = new Socket("localhost", port);

      System.out.println("Connected!");

      Console cons = System.console(); //while loop here if want to keep going
      // Write a message to the server
      String theMessage = cons.readLine("input:");

      // Get the output stream
      OutputStream os = sock.getOutputStream();
      Writer writer = new OutputStreamWriter(os);
      BufferedWriter bw = new BufferedWriter(writer);

      // Get the input stream
      InputStream is = sock.getInputStream();
      Reader reader = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(reader);

      // Write the message out
      bw.write(theMessage);
      bw.newLine();
      bw.flush();
      os.flush();

      // Read the result from the server
      String fromServer = br.readLine();

      System.out.printf(">>> SERVER: %s\n", fromServer);

      os.close();
      is.close();
      sock.close();
       
   }
   
}


//version to continuously take input
// package day04;

// import java.io.*;
// import java.net.*;

// public class ClientMain {

//    public static void main(String[] args) throws IOException {

//       // Set the default port to 3000
//       int port = 3000;
//       if (args.length > 0)
//          port = Integer.parseInt(args[0]);

//       // Create a connection to the server
//       System.out.println("Connecting to the server");
//       Socket sock = new Socket("localhost", port);
//       System.out.println("Connected!");

//       // Get the output and input streams
//       OutputStream os = sock.getOutputStream();
//       Writer writer = new OutputStreamWriter(os);
//       BufferedWriter bw = new BufferedWriter(writer);

//       InputStream is = sock.getInputStream();
//       Reader reader = new InputStreamReader(is);
//       BufferedReader br = new BufferedReader(reader);

//       Console cons = System.console();
//       String theMessage;

//       // Use a loop to continuously take input
//       while (true) {
//          theMessage = cons.readLine("input (type 'exit' to quit): ");

//          // Exit condition
//          if ("exit".equalsIgnoreCase(theMessage)) {
//             break; // Exit the loop if the user types 'exit'
//          }

//          // Write the message out
//          bw.write(theMessage);
//          bw.newLine();
//          bw.flush();
//          os.flush();

//          // Read the result from the server
//          String fromServer = br.readLine();
//          System.out.printf(">>> SERVER: %s\n", fromServer);
//       }

//       // Close resources
//       os.close();
//       is.close();
//       sock.close();
//    }
// }