import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * The Server class represents a server application that listens for client connections
 * and handles client requests in separate threads.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 29, 2024
 */

public class Server implements ServerInterface {
    private ServerSocket ss = null;
    // Main method to start the server
    public static void main(String[] args) {
        new Server();
    }
    // Constructor to initialize the server
    public Server() {
        try {
            System.out.println("Server is listening");
            ss = new ServerSocket(9999);
            while (true) {
                Socket socket = ss.accept();

                new ClientHandlerThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Inner class to handle client requests in a separate thread
    private class ClientHandlerThread extends Thread implements ServerInterface  {
        private Socket socket;

        public ClientHandlerThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        // Run method to handle client requests
        public void run() {
            // Display message indicating successful connection
            System.out.println(this.getName() + " Connect successful! " + socket.toString());


            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                // Read the message sent by the client
                Message message = (Message) ois.readObject();

                // Handle different types of client messages
                switch (message.getMessageType()) {
                    case Message.Message_SIGNUP_CLIENT:
                        String userName = ((Message) ois.readObject()).getContent();
                        if (DataBase.findUser(userName) != null) {
                            Message message1 = new Message();
                            message1.setMessageType(Message.Message_SIGNUP_FAIL);
                            oos.writeObject(message1);
                        } else {
                            Message message1 = new Message();
                            message1.setMessageType(Message.Message_SIGNUP_SUCCESSFUL);
                            oos.writeObject(message1);
                            User u = (User) ois.readObject();
                            DataBase.add(u);
                        }
                        oos.close();
                        ois.close();
                        System.out.println(this.getName() + " Stop!");
                        socket.close();
                        return;
                    case Message.Message_LOGIN_CLIENT:
                        User u = (User) ois.readObject();
                        Message message1 = new Message();
                        if (DataBase.check(u.getUsername(), u.getPassword())) {
                            message1.setMessageType(Message.Message_LOGIN_SUCCESSFUL);
                            oos.writeObject(message1);
                            oos.flush();
                            User user = Information.readUser(u.getUsername());
                            oos.writeObject(user);
                            ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUsername());
                            serverConnectClientThread.start();
                        } else {
                            message1.setMessageType(Message.Message_LOGIN_FAIL);
                            oos.writeObject(message1);
                            // Close streams and socket after handling client request
                            oos.close();
                            ois.close();
                            socket.close();
                            System.out.println(this.getName() + " Stop!");
                        }
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error handling client " + this.getName() + ": " + e.getMessage());
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("Error closing socket for client " + this.getName() + ": " + ex.getMessage());
                }
            }
        }
    }
}
