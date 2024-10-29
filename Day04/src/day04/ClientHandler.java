package day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.util.Date;

//Work for a thread to perform
public class ClientHandler implements Runnable {

    private final Socket sock;
//need to pass the freshly created socket from the server   
    public ClientHandler(Socket s){
        sock = s;
    }

    //Entry point for the thread
    @Override //cannot use throw here as it will alter the signature, use try and catch
    public void run(){

        String threadName = Thread.currentThread().getName();

        try{
         // Get the input stream ,open the input stream first while client does the reverse
        //safer this way
      InputStream is = sock.getInputStream();
      Reader reader = new InputStreamReader(is); //differ based on the type of input we read
      BufferedReader br = new BufferedReader(reader); //e.g. this is for lines, could be bytes 

      // Get the output stream
      OutputStream os = sock.getOutputStream();
      Writer writer = new OutputStreamWriter(os);
      BufferedWriter bw = new BufferedWriter(writer);

      // Read from the client
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
        } catch (IOException ex){
            //Exception handler
            ex.printStackTrace();
        }
    }
    
}


//this is the work, now we need workers to help run this