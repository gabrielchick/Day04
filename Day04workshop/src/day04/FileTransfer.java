package day04;

import java.io.*;

public class FileTransfer {

    public static void main(String[] args) throws IOException {
        if (args.length <= 0) {
            System.err.println("Missing file name");
            System.exit(-1);
        }

        // Create a file object with the input argument
        File file = new File(args[0]);

        // File name
        String fileName = file.getName();
        // Size
        long fileSize = file.length();

        System.out.printf("Transfering file %s\n", fileName);

        SendFile sendFile = new SendFile("localhost", Constants.PORT, file);
        sendFile.send();
    }
}






    //copy and paste myFile from FileCopy client to FileServer server under the same name e.g. myfile via dataoutputstream
    //1st we send over file name, writeUTF()
    //2nd we send over file size, writeLong()
    //3rd we send over bytes, write(byte[])
    //4th we close the client


    