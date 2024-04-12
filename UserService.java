import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * UserService Class - Facilitates the User Experience by providing various functions
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public class UserService {

    private User u = new User();
    private Socket socket;

    public boolean checkSecurity(String account, String password) {
        boolean b = false;
        u.setUsername(account);
        u.setPassword(password);
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);

            ObjectInput ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            if (message.getContent().equals("Success!!")) {
                ClientConnectServerThread clientConnectServerThread
                        = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                return true;
            } else {
                socket.close();
                return false;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkBlocked(User user1, User user2) {
        if (user1 == null || user2 == null) return false;
        return user1.getFriendDataBase().getBlockedFriendHashMap().containsKey(user2.getUsername()) ||
                user2.getFriendDataBase().getBlockedFriendHashMap().containsKey(user1.getUsername());
    }

    public boolean checkFriend(User user1, User user2) {
        if (user1 == null || user2 == null) return false;
        return user1.getFriendDataBase().getFriendHashMap().containsKey(user2.getUsername()) &&
                user2.getFriendDataBase().getFriendHashMap().containsKey(user1.getUsername());
    }

    public boolean existFriendRequest(User user1, User user2) {
        if (user1 == null || user2 == null) return false;
        return user1.getFriendDataBase().getRequestFriendHashMap().containsKey(user2.getUsername());
    }

    // check user MessagePrivacy status
    public boolean chechMessagePrivacySetting(User user) {
        try {
            return user.isMessagePrivacySettings();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // check if user2 has sent messages to user1
    public boolean checkMessageReceive(User user1, User user2) {
        return  (user1.getMessageDataBase().getReceiveMessageHashMap().containsKey(user2.getUsername()));
    }

    //check how many messages user2 has sent to user1
    public int checkMessageCount(User user1, User user2) {
        return user1.getMessageDataBase().getReceiveMessageHashMap().get(user2.getUsername()).size();
    }

}
