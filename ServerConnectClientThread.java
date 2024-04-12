import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread {
    private Socket socket;
    private String userName;

    public ServerConnectClientThread(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Server is connecting with" + userName);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
