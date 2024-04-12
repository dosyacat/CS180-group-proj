import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket ss = null;

    public static void main(String[] args) {
        new Server();
    }

    public Server() {
        try {
            System.out.println("Server is listing");
            ss = new ServerSocket(9999);
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Connect successful!");
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream ops = new ObjectOutputStream(socket.getOutputStream());
                User u = (User) ois.readObject();
                Message message = new Message();
                if (DataBase.check(u.getUsername(), u.getPassword())) {
                    message.setContent("Success!!");
                    ops.writeObject(message);
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUsername());
                    serverConnectClientThread.start();
                }
                else {
                    message.setContent("Fail!!");
                    ops.writeObject(message);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
