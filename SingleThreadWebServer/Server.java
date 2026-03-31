import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void run() throws IOException{
        int port = 8010;
            ServerSocket socket = new ServerSocket(port);        
            socket.setSoTimeout(10000); 
            while(true){
                System.out.println("Server is Listening on port" + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from the client"+acceptedConnection.getRemoteSocketAddress());
                // PrintWriter is used to write formatted text to output streams like files and console or sockets (from server to client )
                // bufferedreader is used to read the text effiecently especially from input streams like keyboards or files 
                // when the connection is made the data from clients to server is transfered in bytes 
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
                // System.out.println("Response from the Client is " + fromClient.readLine());
                toClient.println("Hello from the Server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();
            }

    }
    public static void main(String[] args){
        Server server = new Server();
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// to run in terminal run javac Server.java then java Server.java and in other terminal javac Client.java then java Client.java