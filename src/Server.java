import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        System.out.println("Server is running");
        int ClientId=0;
        Database db=new Database();
        if (!db.establishDBConnection()){
            System.out.println("DB connection fail, stopping.");
        }
        else{
            System.out.println("Server is now connected to DB");
        }
        while(true){
            Socket clientSocket = serverSocket.accept();
            ClientId ++;
            System.out.println("Client " + ClientId + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
            ClientHandler clientHandler = new ClientHandler(clientSocket, ClientId, db);
            new Thread(clientHandler).start();
        }
        /*Open the server socket
          System.out.println("Server is running" );

          Create a Database object and check the connection with establishDBConnection():
          If the db connection fails, print:
              System.out.println("DB connection fail, stopping.");
          else, print:
              System.out.println("Server is now connected to DB");

              Continuously listen for client requests
                  Accept new connection and create the client socket
                  Increment clientId. The clientId is not reassigned once used.
                  Display clientId and IP address:
                  System.out.println("Client " + clientId + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
                  Create a new client handler object and start the thread*/
    }
}

