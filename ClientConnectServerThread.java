import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
/**
 * The ClientConnectServerThread class represents a thread that connects the client to the server.
 * It handles communication between the client and the server.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 15, 2024
 */


public class ClientConnectServerThread extends Thread {
    private Socket socket;
    //Parameterized Constructor
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }
    //Getter Method for the socket
    public Socket getSocket() {
        return socket;
    }

    @Override
    public synchronized void run() {
        /*while (true) {

            try {
                ObjectInputStream ois = new ObjectInputStream();
                Message message = (Message) ois.readObject();
                switch (message.getMessageType()) {
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
                    case Message.Message_EDIT_BIO_SERVER :
                        System.out.println("Bio has been changed.");
                        break;
                    case Message.Message_EDIT_PASSWORD_SUCCESSFUL:
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

    }
         */


    }
}
