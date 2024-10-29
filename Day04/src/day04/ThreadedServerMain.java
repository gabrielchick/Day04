package day04;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedServerMain {

    public static void main(String[] args) throws IOException{


    //Create a thread pool, fixed size that does not grow or shrink
    //We have 2 workers, so we have 3 cores in total if we add on the main method
    ExecutorService thrPool = Executors.newFixedThreadPool(2);

    String threadName = Thread.currentThread().getName(); //illutarte whch thread is handlign which command
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
//Create the work, sock will be passed into ClientHandler.java
ClientHandler handler = new ClientHandler(sock);
//Pass the work to the worker
thrPool.submit(handler);
        }

    }

}



//Thread pool are the workers to help us run stuff. If not enough then server stuck at readnig/writing before allowing connection to connect
//Just like the main program, threads from this pool must finish their own task before being able to help somewhere else
//Pros of using 2 threads per socket(aka client): more responsive as one can read and one can write, but more resources needed