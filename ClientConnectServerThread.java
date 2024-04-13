import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                switch (message.getMessageType()) {
                    case Message.Message_USERVIEW_SERVER :
                        HashMap<String, User> userHashMap = (HashMap<String, User>) ois.readObject();
                        userInformation(userHashMap);
                        break;
                    case Message.Message_USESEARCH_SERVER :
                        User user = (User) ois.readObject();
                        System.out.println(user);
                        break;
                    case Message.Message_EDIT_USERNAME_SERVER_FAIL :
                        System.out.println("This username is already taken!");
                        break;
                    case Message.Message_EDIT_USERNAME_SERVER_SUCCESSFUL :
                        System.out.println("Username has been changed.");
                        System.out.println(
                                "Please proceed with caution " +
                                        "and use the newly assigned username for login purposes.");
                        break;
                    case Message.Message_EDIT_EMAIL_SERVER :
                        System.out.println("Email has been changed.");
                        break;
                    case Message.Message_EDIT_PASSWORD_SUCCESSFUL:
                        Message message1 = new Message();
                        System.out.println("Password has been changed.");
                        break;
                    case Message.Message_EDIT_PASSWORD_FAIL:
                        System.out.println("The current password is not correct!");
                        break;
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
