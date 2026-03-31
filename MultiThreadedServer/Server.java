
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server{
// we used consumer interface we want to perform some action wihtout returning anything
    public Consumer<Socket>getConsumer(){
        return (clientSocket)->{
            try(PrintWriter toSocket = new PrintWriter(clientSocket.getOutputStream(),true)) {
                toSocket.println("Hello from the server " + clientSocket.getInetAddress());
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
    public static void main(String[] args){
        int port = 8010;
        Server server =  new Server();
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(60000);      
            System.out.println("Server Listening on port " + port);
            while(true){
                Socket clientSocket = serverSocket.accept();

                // we are performing multithreaded server 
                // so create and start a new thread for each client we use Thread functionality
                Thread thread = new Thread(()->server.getConsumer().accept(clientSocket));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


// (clientSocket) -> { ... }

// is internally converted by Java to:

// new Consumer<Socket>() {
//     public void accept(Socket clientSocket) {
//         // your logic
//     }
// };

// true in printwritter -->used for auto flush of the data 