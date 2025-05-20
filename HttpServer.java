//Name: William Malone
//ID: 1604564

import java.net.*;
import java.io.*;
import java.util.*;

public class HttpServer {
    public static void main(String args[])
    {
    //Set port number the socket will be using 
    int port = 51590;

    try (ServerSocket serverSocket = new ServerSocket(port)) 
    {
        while (true) 
        {
            //Wait for a new client connection
            Socket client = serverSocket.accept();
            HttpServerSession session = new HttpServerSession(client);

            /* the start method causes the thread to run */
            session.start();
        }
        //Error handling
        } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }
    }
}
