import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * UserService Class - Facilitates the User Experience by providing various functions
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public class UserService {

    private User u = new User();
    private Socket socket;
    private ClientConnectServerThread clientConnectServerThread;

    public User userSignIn(String account, String password) {
        u.setUsername(account);
        u.setPassword(password);
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_LOGIN_CLIENT);
            oos.writeObject(message);

            oos.writeObject(u);

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_LOGIN_SUCCESSFUL)) {
                u = (User) ois.readObject();
                clientConnectServerThread = new ClientConnectServerThread(socket);
                clientConnectServerThread.start();
                return u;
            } else {
                socket.close();
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void userSignUp() {
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = new Message();
            message.setMessageType(Message.Message_SIGNUP_CLIENT);
            oos.writeObject(message);

            System.out.println("Please enter your Username, which will be limited to 20 digits");
            String username = Input.readString(20, false);

            Message message2 = new Message();
            message2.setContent(username);
            oos.writeObject(message2);

            Message message1 = (Message) ois.readObject();

            if (message1.getMessageType().equals(Message.Message_SIGNUP_FAIL)) {
                System.out.println("This username is already taken!");
                return;
            }

            System.out.println("Please enter your password, which will be limited to 20 digits");
            String password = Input.readString(20, false);
            System.out.println("Please enter your email");
            String email = Input.readEmail(30, false);
            System.out.println("Please enter your bio, which will be limited to 100 words");
            String bio = Input.readString(100);
            //Creating a new user object and adding it to database
            User user = new User(username, password, email, bio);
            oos.writeObject(user);
            System.out.println("You have signed up successfully! And you can upload a picture as your profile at Setting!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userView() {
        try {
            Message message = new Message();
            message.setMessageType(Message.Message_USERVIEW_CLIENT);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_USERVIEW_SERVER)) {
                HashMap<String, User> userHashMap = (HashMap<String, User>) ois.readObject();
                userInformation(userHashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void userSearch() {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("Who are you searching for?");
            String username = Input.readString(20, false);
            Message message = new Message();
            message.setMessageType(Message.Message_USESEARCH_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();
            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_USESEARCH_SERVER)) {
                User user = (User) ois.readObject();
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        Message message = new Message();
        message.setMessageType(Message.Message_EXIT);
        message.setSender(u.getUsername());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(message);
            System.out.println(u.getUsername() + "Exit!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editUserName() {
        System.out.println("Please enter your new Username, which will be limited to 20 digits");
        String username = Input.readString(20, false);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = new Message();
            message.setMessageType(Message.Message_EDIT_USERNAME_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();
            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_EDIT_USERNAME_SERVER_FAIL)) {
                System.out.println("This username is already taken!");
            } else if (message1.getMessageType().equals(Message.Message_EDIT_USERNAME_SERVER_SUCCESSFUL)) {
                System.out.println("Username has been changed.");
                System.out.println(
                        "Please proceed with caution " +
                                "and use the newly assigned username for login purposes.");
                u.setUsername(username);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editEmail() {
        System.out.println("Please enter your email");
        String email = Input.readEmail(30, false);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_EDIT_EMAIL_CLIENT);
            message.setContent(email);
            oos.writeObject(message);
            oos.flush();
            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_EDIT_EMAIL_SERVER)) {
                System.out.println("Email has been changed.");
                u.setEmail(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editPassword() {
        System.out.println("Please enter your current password!");
        String currentPassword = Input.readString(20, false);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_EDIT_PASSWORD_CLIENT);
            message.setContent(currentPassword);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_EDIT_PASSWORD_SUCCESSFUL)) {
                System.out.println("Please enter your new password!");
                String password = Input.readString(20, false);
                Message message2 = new Message();
                message2.setContent(password);
                oos.writeObject(message2);
                System.out.println("Password has been changed!");
                u.setPassword(password);
            } else {
                System.out.println("The current password is not correct!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void editBio() {
        System.out.println("Please enter your bio, which will be limited to 100 words");
        String bio = Input.readString(100);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Message message = new Message();
            message.setMessageType(Message.Message_EDIT_BIO_CLIENT);
            message.setContent(bio);
            oos.writeObject(message);
            oos.flush();
            u.setBio(bio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AddFriend() {
        System.out.println("Who do you want to add?");
        String username = Input.readString(20, false);
        if (u.getUsername().equals(username)) {
            System.out.println("You cannot add yourself!");
            return;
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_REQUESTFRIEND_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_REQUESTFRIEND_SERVER_SUCCESSFUL)) {
                System.out.println("Add successfully");
            } else if (message1.getMessageType().equals(Message.Message_REQUESTFRIEND_SERVER_FAIL)) {
                System.out.println("The user you want to add doesn't exist or you have been blocked by they");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFriend() {
        System.out.println("Who do you want to remove?");
        String username = Input.readString(20, false);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_REMOVEFRIEND_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_REQUESTFRIEND_SERVER_SUCCESSFUL)) {
                System.out.println("Remove successfully");
            } else if (message1.getMessageType().equals(Message.Message_REMOVEFRIEND_SERVER_FAIL)) {
                System.out.println("The user you want to add doesn't exist or they are not your friend");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
