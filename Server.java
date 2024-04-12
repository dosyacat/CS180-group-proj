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
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                Message message = (Message) ois.readObject();

                switch (message.getMessageType()) {
                    case Message.Message_SIGNUP_CLIENT :
                        String userName = ((Message) ois.readObject()).getContent();
                        if (DataBase.findUser(userName) != null) {
                            Message message1 = new Message();
                            message1.setMessageType(Message.Message_SIGNUP_FAIL);
                            oos.writeObject(message1);
                        } else {
                            Message message1 = new Message();
                            message1.setMessageType(Message.Message_SIGNUP_SUCCESSFUL);
                            oos.writeObject(message1);
                            User u = (User) ois.readObject();
                            DataBase.add(u);
                        }
                        break;
                    case Message.Message_LOGIN_CLIENT:
                        User u = (User) ois.readObject();
                        Message message1 = new Message();
                        if (DataBase.check(u.getUsername(), u.getPassword())) {
                            message1.setMessageType(Message.Message_LOGIN_SUCCESSFUL);
                            oos.writeObject(message1);
                            ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getUsername());
                            serverConnectClientThread.start();
                        } else {
                            message1.setMessageType(Message.Message_LOGIN_FAIL);
                            oos.writeObject(message1);
                        }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
