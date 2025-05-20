//Name: William Malone
//ID: 1604564

import java.net.*;
import java.io.*;
import java.util.*;

public class HttpServerSession extends Thread{

    //Declare socket
    private Socket s;

    //Constructor 
    public HttpServerSession(Socket s)
    {
        this.s = s;
    }

    //Calling serversession.start() will make this code run 
    //Type into browser http://localhost:51590/ or localhost:51590 to connect 
    public void run()
    {
        try{
            //Declare variables 
            HttpServerRequest request = new HttpServerRequest();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedOutputStream OutputStream = new BufferedOutputStream(s.getOutputStream());
            String line;

            //Loop through each incoming line and process it 
            while ((line = read.readLine()) != null && !line.equals("")) 
            {
                //Process line
                request.process(line);
            }

            //Put hostname and file into variables 
            String getFile = request.getFile();
            String getHost = request.getHost();

            //declare a byte array of a fixed size
            byte[] buffer = new byte[1024];  // 1KB buffer size

            //Turn file string into file object
            File file = new File(getFile);
            //Get a file path from host and file name 
            String filePath = getHost + "/" + getFile;
            
            if(file != null && getFile != "")
            {
                //Sucessful response 
                try{
                    //Message to send to browser to indicate sucessful response
                    println(OutputStream, "HTTP/1.1 200 OK"); // Response Header
                    println(OutputStream, ""); //Sepperate header from body
                    
                    //Open the file with a FileInputStream
                    FileInputStream inputStream = new FileInputStream(filePath);

                    System.out.println("Debugging - " + inputStream.toString() + " " + getFile + " " + getHost);

                    //Data to send to browser
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) 
                        {
                        OutputStream.write(buffer, 0, bytesRead);
                        }

                    inputStream.close();
                }
                //Unsucessful response - file not found 
                catch(FileNotFoundException e)
                {
                    println(OutputStream, "HTTP/1.1 404 Not Found\r\n\r\n404 " + getFile + " Not Found"); // Response Header
                }
                //Manually send output stream 
                OutputStream.flush();
            }   
            //Flush output stream and close socket
            s.close();
        }
        //Error handeling
        catch(Exception e)
        {
            System.err.println("Exception: " + e);
        }     
    }

    //Override println method 
    private boolean println(BufferedOutputStream bos, String s)
    {
        String news = s + "\r\n";
        byte[] array = news.getBytes();
        try {
            bos.write(array, 0, array.length);
        } catch(IOException e) {
            return false;
        }
        return true;
    }

}
