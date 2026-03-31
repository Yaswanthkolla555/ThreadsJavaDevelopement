
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{

    public void run() throws UnknownHostException,IOException{
        int port = 8010;
        // inetaddress 
        InetAddress address = InetAddress.getByName("localhost");
        Socket server = new Socket(address,port);
        PrintWriter toServer = new PrintWriter(server.getOutputStream());
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
        toServer.println("Hello from the client" + server.getLocalSocketAddress());
        String line = fromServer.readLine();
        System.out.println("Response from the server is " + line);
        toServer.close();
        fromServer.close();
        server.close();
    }
    public static void main(String[] args) {
         try {
             Client client = new Client();
             client.run();
         } catch (Exception e) {
            e.printStackTrace();
         }
    }
}