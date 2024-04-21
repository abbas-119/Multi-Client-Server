import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    Socket clientSocket;
    int clientID;
    Database dBase;
    //declare variables
    //Constructor
    public ClientHandler (Socket socket, int clientId, Database db) {
        //complete the constructor
        clientSocket=socket;
        clientID=clientId;
        dBase=db;
    }

    public void run() {
        try {
            System.out.println("ClientHandler started...");
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            String clientMessage;
            while (!(clientMessage = inFromClient.readLine()).equals("stop")) {
                System.out.println("Client sent the artist name " + clientMessage);
                int titlesNum=dBase.getTitles(clientMessage);
                outToClient.println("Number of titles: " + titlesNum + " records found");
            }
            System.out.println("Client " + clientID + " has disconnected");
            outToClient.println("Connection closed, Goodbye!");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        /*System.out.println("ClientHandler started...");
              Create I/O streams to read/write data, PrintWriter and BufferedReader
              Receive messages from the client and send replies, until the user types "stop"
                  System.out.println("Client sent the artist name " + clientMessage);
                  Request the number of titles from the db
                  Send reply to Client:
                  outToClient.println("Number of titles: " + titlesNum + " records found");

              System.out.println("Client " + clientId + " has disconnected");
              outToClient.println("Connection closed, Goodbye!");
              Close I/O streams and socket*/
    }
}
