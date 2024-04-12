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
                System.out.println(socket.toString());
                System.out.println("Server is connecting with " + userName);
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = (Message) ois.readObject();
                if (message.getMessageType().equals(Message.Message_USERVIEW_CLIENT)) {
                    Message message1 = new Message();
                    message1.setMessageType(Message.Message_USERVIEW_SERVER);
                    oos.writeObject(DataBase.getUserHashMap());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
