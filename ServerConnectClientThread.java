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

                switch (message.getMessageType()) {

                    case Message.Message_USERVIEW_CLIENT: {
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_USERVIEW_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        oos.writeObject(DataBase.getUserHashMap());
                        oos.flush();
                        break;
                    }

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

                    case Message.Message_EXIT: {
                        System.out.println(message.getSender() + "Exit!");
                        socket.close();
                        return;
                    }

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

                    case Message.Message_EDIT_EMAIL_CLIENT: {
                        String email = message.getContent();
                        DataBase.editEmail(this.userName, email);
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_EDIT_EMAIL_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        break;
                    }

                    case Message.Message_EDIT_BIO_CLIENT: {
                        String bio = message.getContent();
                        DataBase.editBio(this.userName, bio);
                        Message message1 = new Message();
                        message1.setMessageType(Message.Message_EDIT_BIO_SERVER);
                        oos.writeObject(message1);
                        oos.flush();
                        break;
                    }

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
                    case Message.Message_SHOWFRIEND_CLIENT: {
                        User user = DataBase.findUser(userName);
                        oos.writeObject(user.getFriendArrayList());
                        oos.flush();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(this.userName + " disconnect.");
                return;
            }
        }
    }
}
