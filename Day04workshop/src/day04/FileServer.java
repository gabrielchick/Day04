package day04;

import java.io.*;
import java.net.*;

public class FileServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(Constants.PORT);

        System.out.printf("File server started on port %d\n", Constants.PORT);

        while (true) {
            Socket sock = server.accept();
            System.out.printf("New file upload\n");

            ReceiveFile recvFile = new ReceiveFile(sock);
            recvFile.receive();
        }
    }
}


 //copy over myFile from FileCopy client under the same name e.g. myfile via datainputstream
    //1st we read file name, readUTF()
    //2nd we read file size, readLong()
    //3rd we read bytes, read(byte[])
    //4th we close the client