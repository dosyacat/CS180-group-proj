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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {

        System.out.println("Server is connecting with " + userName);
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Message message = (Message) ois.readObject();

                if (message.getMessageType().equals(Message.Message_USERVIEW_CLIENT)) {
                    Message message1 = new Message();
                    message1.setMessageType(Message.Message_USERVIEW_SERVER);
                    oos.writeObject(message1);
                    oos.flush();
                    oos.writeObject(DataBase.getUserHashMap());
                    oos.flush();
                }

                if (message.getMessageType().equals(Message.Message_USESEARCH_CLIENT)) {

                    User user = DataBase.findUser(message.getContent());
                    Message message1 = new Message();
                    message1.setMessageType(Message.Message_USESEARCH_SERVER);
                    oos.writeObject(message1);
                    oos.flush();
                    oos.writeObject(user);
                    oos.flush();
                }

                if (message.getMessageType().equals(Message.Message_EXIT)) {
                    System.out.println(message.getSender() + "Exit!");
                    socket.close();
                    return;
                }

                if (message.getMessageType().equals(Message.Message_EDIT_USERNAME_CLIENT)) {
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
                }

                if (message.getMessageType().equals(Message.Message_EDIT_EMAIL_CLIENT)) {
                    String email = message.getContent();
                    DataBase.editEmail(this.userName, email);
                    Message message1 = new Message();
                    message1.setMessageType(Message.Message_EDIT_EMAIL_SERVER);
                    oos.writeObject(message1);
                    oos.flush();

                }

                if (message.getMessageType().equals(Message.Message_EDIT_BIO_CLIENT)) {
                    String bio = message.getContent();
                    DataBase.editBio(this.userName, bio);
                    Message message1 = new Message();
                    message1.setMessageType(Message.Message_EDIT_BIO_SERVER);
                    oos.writeObject(message1);
                    oos.flush();
                }

                if (message.getMessageType().equals(Message.Message_EDIT_PASSWORD_CLIENT)) {
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
                }


            } catch (Exception e) {
                System.out.println(this.userName + "disconnect.");
                return;
            }
        }
    }
}
