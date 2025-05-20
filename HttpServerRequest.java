//Name: William Malone
//ID: 1604564

import java.io.Console;

class HttpServerRequest
{
    //Declare variables 
    private String file = null;
    private String host = null;
    private boolean done = false;
    private int line = 0;

    //Declare methods 
    public boolean isDone() { return done; }
    public String getFile() { return file; }
    public String getHost() { return host; }

    public void process(String in)
    {
	/*
	 * process the line, setting 'done' when HttpServerSession should
	 * examine the contents of the request using getFile and getHost
     * 
	 */

     try {
        //Split incoming strings into parts and put into array 
        String parts[] = in.split(" ");
        String filename = parts[1].substring(1);

        //Get the filename from get line
        if(parts[0].compareTo("GET") == 0)
        {
            file = filename;
            if(parts[1].endsWith("/") || parts[1].isEmpty())
            {
                file += "index.html";
            }
        }
        //Get the hostname from the host line
        else if(parts[0].startsWith("Host:"))
        {
            host = parts[1];
        }
        //All other lines are handeled with this else statement
        else
        {
            //System.out.println(in);
            
        }


        line++;
        //Error handeling
     } catch (Exception e) {
        System.err.println("Exception: " + e);
     }
        

    }
}