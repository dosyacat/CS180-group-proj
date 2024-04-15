import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UserService Class - Facilitates the User Experience by providing various functions
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 15, 2024
 */

public class UserService implements UserServiceInterface {

    private User u = new User();
    private Socket socket;
    //Method to sign in
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
                return u;
            } else {
                socket.close();
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //Method to SignUp for new user
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
            System.out.println("You have signed up successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to view User Information
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
                ConcurrentHashMap<String, User> userHashMap = (ConcurrentHashMap<String, User>) ois.readObject();
                userInformation(userHashMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to display User Information
    public void userInformation(ConcurrentHashMap<String, User> userHashMap) {
        System.out.println("Here is the information for our users!");
        int i = 1;
        for (User user : userHashMap.values()) {
            System.out.println("User" + i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }
    //Method to search for a user
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
                if (user == null) {
                    System.out.println("The user you search didn't exist!");
                } else {
                    System.out.println(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to LogOut
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
    //Method to change and modify username
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
    //Method to modify eMail
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
    //Method to modify password
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
    //Method to modify Bio
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

    public void changeMessagesPrivacy() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = new Message();
            message.setMessageType(Message.Message_CHANGEPRIVACY_CLIENT);
            oos.writeObject(message);
            oos.flush();
            u.setMessagePrivacySettings(!u.isMessagePrivacySettings());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to add friend
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
            message.setMessageType(Message.Message_ADDFRIEND_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_ADDFRIEND_SERVER_SUCCESSFUL)) {
                System.out.println("Add successfully");
            } else if (message1.getMessageType().equals(Message.Message_ADDFRIEND_SERVER_FAIL_1)) {
                System.out.println("The user you want to add doesn't exist");
            } else if (message1.getMessageType().equals(Message.Message_ADDFRIEND_SERVER_FAIL_2)) {
                System.out.println("The user you have added!");
            } else if (message1.getMessageType().equals(Message.Message_ADDFRIEND_SERVER_FAIL_3)) {
                System.out.println("You are blocked by the user!");
            } else if (message1.getMessageType().equals(Message.Message_ADDFRIEND_SERVER_FAIL_4)) {
                System.out.println("Sorry, you have to unblock him before you can add him as a friend!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to remove friend
    public void removeFriend() {
        System.out.println("Who do you want to remove?");
        String username = Input.readString(20, false);

        if (u.getUsername().equals(username)) {
            System.out.println("You cannot remove yourself!");
            return;
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_REMOVEFRIEND_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_REMOVEFRIEND_SERVER_SUCCESSFUL)) {
                System.out.println("Remove successfully");
            } else if (message1.getMessageType().equals(Message.Message_REMOVEFRIEND_SERVER_FAIL)) {
                System.out.println("The user is not your friend");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to block friend
    public void blockFriend() {
        System.out.println("Who do you want to block?");
        String username = Input.readString(20, false);

        if (u.getUsername().equals(username)) {
            System.out.println("You cannot block yourself!");
            return;
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_BLOCKFRIEND_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_BLOCKFRIEND_SERVER_SUCCESSFUL)) {
                System.out.println("BLOCK successfully!");
            } else if (message1.getMessageType().equals(Message.Message_BLOCKFRIEND_SERVER_FAIL)) {
                System.out.println("The User doesn't exist");
            } else if (message1.getMessageType().equals(Message.Message_BLOCKFRIEND_SERVER_FAIL_2)) {
                System.out.println("Sorry, you have to delete him as a friend before you can block him!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to unblock friend
    public void unBlockFriend() {
        System.out.println("Who do you want to unblock?");
        String username = Input.readString(20, false);

        if (u.getUsername().equals(username)) {
            System.out.println("You cannot unblock yourself!");
            return;
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_UNBLOCKFRIEND_CLIENT);
            message.setContent(username);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_UNBLOCKFRIEND_SERVER_SUCCESSFUL)) {
                System.out.println("UNBLOCK successfully!");
            } else if (message1.getMessageType().equals(Message.Message_UNBLOCKFRIEND_SERVER_FAIL_1)) {
                System.out.println("The user doesn't exit!");
            } else if (message1.getMessageType().equals(Message.Message_UNBLOCKFRIEND_SERVER_FAIL_2)) {
                System.out.println("You didn't block the user yet!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method check friend list
    public void showFriendList() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_SHOWFRIEND_CLIENT);
            oos.writeObject(message);
            oos.flush();
            ArrayList<String> arrayList = (ArrayList) ois.readObject();
            if (arrayList.isEmpty()) {
                System.out.println("You have not added any friends yet.");
            } else {
                System.out.println(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //method to send messages
    public void sendMessage() {
        System.out.println("Who do you want to send?");
        String receiver = Input.readString(20);
        System.out.println("What do you want to send?");
        String content = Input.readString(100000);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = MessageService.sendMessage(u.getUsername(), receiver, content);
            message.setMessageType(Message.Message_GENERALMESSAGE_CLIENT);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_GENERALMESSAGE_SERVER_FAIL1)) {
                System.out.println("Sending messages failed! Receiver not found.");
            } else if (message1.getMessageType().equals(Message.Message_GENERALMESSAGE_SERVER_FAIL2)) {
                System.out.println("Sending messages failed! " +
                        "The user has their settings adjusted to only receive messages from friends!");
            } else if (message1.getMessageType().equals(Message.Message_GENERALMESSAGE_SERVER_FAIL3)) {
                System.out.println("Sending messages failed! " +
                        "You can't message someone who has blocked you");
            } else if (message1.getMessageType().equals(Message.Message_GENERALMESSAGE_SERVER_SUCCESSFUL)) {
                System.out.println("Sent successful!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to check received message
    public void checkReceiveMessages() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_CHECKMESSAGE_CLIENT);
            oos.writeObject(message);
            oos.flush();

            ArrayList<Message> arrayList = (ArrayList<Message>) ois.readObject();
            if (arrayList == null || arrayList.isEmpty()) System.out.println("You haven't received any messages yet!");
            else {
                for (Message message1 : arrayList) {
                    System.out.println(message1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to Delete Message
    public void deleteMessages() {
        System.out.println("Whose messages do you want to delete?");
        String sender = Input.readString(20);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = new Message();
            message.setMessageType(Message.Message_DELETEMESSAGE_CLIENT);
            message.setContent(sender);
            oos.writeObject(message);
            oos.flush();

            Message message1 = (Message) ois.readObject();
            if (message1.getMessageType().equals(Message.Message_DELETEMESSAGE_SERVER_FAIL)) {
                System.out.println("Delete messages failed! The user not found.");
            } else {
                System.out.println("Messages has deleted");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
