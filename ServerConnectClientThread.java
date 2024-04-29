import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * The ServerConnectClientThread class represents a thread that handles communication 
 * between the server and a specific client.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 29, 2024
 */

public class ServerConnectClientThread extends Thread implements ServerConnectClientInterface {
    private Socket socket;
    private String userName;
    //Constructor
    public ServerConnectClientThread(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }
    //Getter Method for socket
    public Socket getSocket() {
        return socket;
    }
    //Setter Method for Socket
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    //Getter menthod UserName
    public String getUserName() {
        return userName;
    }
    //Setter menthod UserName
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    //Function to run
    public void run() {

        System.out.println("Server is connecting with " + userName);
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = (Message) ois.readObject();

                // Handle different types of client messages
                switch (message.getMessageType()) {
                    // Process user view request from the client
                    case Message.Message_USERVIEW_CLIENT: {
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_USERVIEW_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        oos.writeObject(DataBase.getUserHashMap());
                        oos.flush();
                        break;
                    }
                    // Process user search request from the client
                    case Message.Message_USESEARCH_CLIENT: {
                        User user = DataBase.findUser(message.getContent());
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_USESEARCH_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        oos.writeObject(user);
                        oos.flush();
                        break;
                    }
                    // Handle client exit request
                    case Message.Message_EXIT: {
                        System.out.println(message.getSender() + "Exit!");
                        socket.close();
                        return;
                    }
                    // Process username edit request from the client
                    case Message.Message_EDIT_USERNAME_CLIENT: {
                        String userName = message.getContent();
                        if (DataBase.findUser(userName) != null) {
                            Message message1 = new Message();
                            message1.setMessageType(Message.Message_EDIT_USERNAME_SERVER_FAIL);
                            oos.writeObject(message1);
                            oos.flush();
                        } else {
                            Message message1 = new Message();
                            message1.setMessageType(Message.Message_EDIT_USERNAME_SERVER_SUCCESSFUL);
                            DataBase.editUserName(this.userName, userName);
                            this.userName = userName;
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    // Process email edit request from the client
                    case Message.Message_EDIT_EMAIL_CLIENT: {
                        String email = message.getContent();
                        DataBase.editEmail(this.userName, email);
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_EDIT_EMAIL_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        break;
                    }
                    // Process Bio edit request from the clien
                    case Message.Message_EDIT_BIO_CLIENT: {
                        String bio = message.getContent();
                        DataBase.editBio(this.userName, bio);
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_EDIT_BIO_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        break;
                    }
                    // Process password edit request from the clien
                    case Message.Message_EDIT_PASSWORD_CLIENT: {
                        String currentPassword = message.getContent();
                        Message message1 = new Message();

                        if (DataBase.check(this.userName, currentPassword)) {
                            message1.setMessageType(Message.Message_EDIT_PASSWORD_SUCCESSFUL);
                            oos.writeObject(message1);
                            oos.flush();
                            Message message2 = (Message) ois.readObject();
                            String password = message2.getContent();
                            DataBase.editPassword(this.userName, password);
                        } else {
                            message1.setMessageType(Message.Message_EDIT_PASSWORD_FAIL);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    // Process add friend request from the client
                    case Message.Message_ADDFRIEND_CLIENT: {
                        String userName2 = message.getContent();
                        User user2 = DataBase.findUser(userName2);
                        Message message1 = new Message();
                        User user = DataBase.findUser(this.userName);
                        if (DataBase.findUser(userName2) == null) {
                            message1.setMessageType(Message.Message_ADDFRIEND_SERVER_FAIL_1);
                            oos.writeObject(message1);
                            oos.flush();
                        } else if (user.getFriendArrayList().contains(userName2)) {
                            message1.setMessageType(Message.Message_ADDFRIEND_SERVER_FAIL_2);
                            oos.writeObject(message1);
                            oos.flush();
                        } else if (user2.getBlockArrayList().contains(userName)) {
                            message1.setMessageType(Message.Message_ADDFRIEND_SERVER_FAIL_3);
                            oos.writeObject(message1);
                            oos.flush();
                        } else if (user.getBlockArrayList().contains(userName2)) {
                            message1.setMessageType(Message.Message_ADDFRIEND_SERVER_FAIL_4);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        else {
                            message1.setMessageType(Message.Message_ADDFRIEND_SERVER_SUCCESSFUL);
                            DataBase.addFriend(this.userName, userName2);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    // Process remove friend request from the client
                    case Message.Message_REMOVEFRIEND_CLIENT: {
                        String userName2 = message.getContent();
                        Message message1 = new Message();
                        User user = DataBase.findUser(this.userName);
                        if (!user.getFriendArrayList().contains(userName2)) {
                            message1.setMessageType(Message.Message_REMOVEFRIEND_SERVER_FAIL);
                            oos.writeObject(message1);
                            oos.flush();
                        } else {
                            message1.setMessageType(Message.Message_REMOVEFRIEND_SERVER_SUCCESSFUL);
                            DataBase.removeFriend(this.userName, userName2);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    // Process block friend request from the client
                    case Message.Message_BLOCKFRIEND_CLIENT: {
                        String userName2 = message.getContent();
                        Message message1 = new Message();
                        User user = DataBase.findUser(this.userName);

                        if (DataBase.findUser(userName2) == null) {
                            message1.setMessageType(Message.Message_BLOCKFRIEND_SERVER_FAIL);
                            oos.writeObject(message1);
                            oos.flush();
                        } else if (user.getFriendArrayList().contains(userName2)) {
                            message1.setMessageType(Message.Message_BLOCKFRIEND_SERVER_FAIL_2);
                            oos.writeObject(message1);
                            oos.flush();
                        } else {
                            message1.setMessageType(Message.Message_BLOCKFRIEND_SERVER_SUCCESSFUL);
                            DataBase.blockFriend(this.userName, userName2);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    case Message.Message_UNBLOCKFRIEND_CLIENT: {
                        String userName2 = message.getContent();
                        Message message1 = new Message();
                        User user = DataBase.findUser(this.userName);
                        if (DataBase.findUser(userName2) == null) {
                            message1.setMessageType(Message.Message_UNBLOCKFRIEND_SERVER_FAIL_1);
                            oos.writeObject(message1);
                            oos.flush();
                        }

                        if (!user.getBlockArrayList().contains(userName2)) {
                            message1.setMessageType(Message.Message_UNBLOCKFRIEND_SERVER_FAIL_2);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        else {
                            message1.setMessageType(Message.Message_UNBLOCKFRIEND_SERVER_SUCCESSFUL);
                            DataBase.unBlockFriend(this.userName, userName2);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    // Process show friend request from the client
                    case Message.Message_SHOWFRIEND_CLIENT: {
                        User user = DataBase.findUser(userName);
                        oos.writeObject(user.getFriendArrayList());
                        oos.flush();
                        break;
                    }
                    // Process change privacy request from the client
                    case Message.Message_CHANGEPRIVACY_CLIENT: {
                        DataBase.changePrivacy(this.userName);
                        break;
                    }
                    // Process general message request from the client
                    case Message.Message_GENERALMESSAGE_CLIENT: {
                        User user = DataBase.findUser(this.userName);
                        String receiver = message.getReceiver();
                        Message message1 = new Message();
                        if (DataBase.findUser(receiver) == null) {
                            message1.setMessageType(Message.Message_GENERALMESSAGE_SERVER_FAIL1);
                            oos.writeObject(message1);
                            oos.flush();
                        } else if (!DataBase.findUser(receiver).isMessagePrivacySettings()
                                && !user.getFriendArrayList().contains(receiver)) {
                            message1.setMessageType(Message.Message_GENERALMESSAGE_SERVER_FAIL2);
                            oos.writeObject(message1);
                            oos.flush();
                        } else if (DataBase.findUser(receiver).getBlockArrayList().contains(this.userName)) {
                            message1.setMessageType(Message.Message_GENERALMESSAGE_SERVER_FAIL3);
                            oos.writeObject(message1);
                            oos.flush();
                        } else {
                            MessageDataBase.addMessage(message);
                            message1.setMessageType(Message.Message_GENERALMESSAGE_SERVER_SUCCESSFUL);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                        break;
                    }
                    // Process check message request from the client
                    case Message.Message_CHECKMESSAGE_CLIENT: {
                        oos.writeObject(MessageDataBase.checkMessage(this.userName));
                        oos.flush();
                        break;
                    }
                    // Process delete message request from the client
                    case Message.Message_DELETEMESSAGE_CLIENT: {
                        String sender = message.getContent();
                        Message message1 = new Message();
                        if (DataBase.findUser(sender) == null) {
                            message1.setMessageType(Message.Message_DELETEMESSAGE_SERVER_FAIL);
                            oos.writeObject(message1);
                            oos.flush();
                            return;
                        }
                        boolean f = MessageDataBase.deleteMessage(this.userName, sender);
                        if (!f) {
                            message1.setMessageType(Message.Message_DELETEMESSAGE_SERVER_FAIL);
                            oos.writeObject(message1);
                            oos.flush();
                        } else {
                            message1.setMessageType(Message.Message_DELETEMESSAGE_SERVER_SUCCESSFUL);
                            oos.writeObject(message1);
                            oos.flush();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(this.userName + " disconnect.");
                e.printStackTrace();
                return;
            }
        }
    }
}
