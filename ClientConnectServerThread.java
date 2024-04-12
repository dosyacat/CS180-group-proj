import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

public class ClientConnectServerThread extends Thread {
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public synchronized void run() {
        while (true) {

            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                if (message.getMessageType().equals(Message.Message_USERVIEW_SERVER)) {
                    HashMap<String, User> userHashMap = (HashMap<String, User>) ois.readObject();
                    userInformation(userHashMap);
                }
                if (message.getMessageType().equals(Message.Message_USESEARCH_SERVER)) {
                    User user = (User) ois.readObject();
                    System.out.println(user);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void userInformation(HashMap<String, User> userHashMap) {
        System.out.println("Here is the information for our users!");
        int i = 1;
        for (User user : userHashMap.values()) {
            System.out.println("User" + i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

}
