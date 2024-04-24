import org.w3c.dom.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JDialog dialog = new JDialog();
        dialog.setTitle("User Information");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(LoginMenu.lightGreen);
        userHashMap.values().forEach(user -> {
            JLabel userInfo = new JLabel("User: " + user.getUsername() + ", Email: " + user.getEmail());
            userInfo.setFont(new Font("Times New Roman", Font.PLAIN, 25));
            panel.add(userInfo);
        });
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBackground(LoginMenu.lightGreen);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    //Method to search for a user
    public void userSearch() {
        JDialog dialog = new JDialog();
        dialog.setTitle("User Search");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LoginMenu.lightGreen);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(LoginMenu.lightGreen);

        JTextField textField = new JTextField(20);

        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(100, 50));
        searchPanel.add(textField);
        searchPanel.add(searchButton);

        JTextArea textArea = new JTextArea(70, 90);
        textArea.setEditable(false);
        textArea.setBackground(LoginMenu.lightGreen);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);

        dialog.add(panel);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    String username = textField.getText();
                    Message message = new Message();
                    message.setMessageType(Message.Message_USESEARCH_CLIENT);
                    message.setContent(username);
                    oos.writeObject(message);
                    oos.flush();
                    Message message1 = (Message) ois.readObject();
                    if (message1.getMessageType().equals(Message.Message_USESEARCH_SERVER)) {
                        User user = (User) ois.readObject();
                        if (user == null) {
                            textArea.setText("The user you search didn't exist!");
                        } else {
                            textArea.setText("User: " + user.getUsername() + "\nEmail: " + user.getEmail()
                            + "\nBio: " + user.getBio());
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        dialog.setVisible(true);
    }

    public void logout() {
        Message message = new Message();
        message.setMessageType(Message.Message_EXIT);
        message.setSender(u.getUsername());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to change and modify username
    public void editUserName() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Username");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LoginMenu.lightGreen);
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        inputPanel.add(textField);
        inputPanel.add(submitButton);
        inputPanel.setBackground(LoginMenu.lightGreen);

        JTextArea feedbackArea = new JTextArea(5, 30);

        feedbackArea.setBackground(LoginMenu.lightGreen);
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(feedbackArea, BorderLayout.CENTER);
        dialog.add(panel);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newUsername = textField.getText();
                if (newUsername.length() > 20) {
                    feedbackArea.setText("Username must be limited to 20 characters.");
                    return;
                }
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Message message = new Message();
                    message.setMessageType(Message.Message_EDIT_USERNAME_CLIENT);
                    message.setContent(newUsername);
                    oos.writeObject(message);
                    oos.flush();

                    Message response = (Message) ois.readObject();
                    switch (response.getMessageType()) {
                        case Message.Message_EDIT_USERNAME_SERVER_FAIL:
                            feedbackArea.setText("This username is already taken!");
                            break;
                        case Message.Message_EDIT_USERNAME_SERVER_SUCCESSFUL:
                            u.setUsername(newUsername);
                            JOptionPane.showMessageDialog(dialog, "Username has been changed.\n" +
                                    "Please use the newly assigned username for login purposes.");
                            dialog.dispose();
                            break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        dialog.setVisible(true);
    }

    //Method to modify eMail
    public void editEmail() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Email");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(LoginMenu.lightGreen);
        inputPanel.setBackground(LoginMenu.lightGreen);
        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        inputPanel.add(textField);
        inputPanel.add(submitButton);

        JTextArea feedbackArea = new JTextArea(5, 30);
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        feedbackArea.setBackground(LoginMenu.lightGreen);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(feedbackArea, BorderLayout.CENTER);
        dialog.add(panel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = textField.getText();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Message message = new Message();
                    message.setMessageType(Message.Message_EDIT_EMAIL_CLIENT);
                    message.setContent(email);
                    oos.writeObject(message);
                    oos.flush();
                    Message message1 = (Message) ois.readObject();
                    if (message1.getMessageType().equals(Message.Message_EDIT_EMAIL_SERVER)) {
                        u.setEmail(email);
                        JOptionPane.showMessageDialog(dialog, "Email been changed!");
                        dialog.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        dialog.setVisible(true);
    }
    //Method to modify password
    public void editPassword() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Password");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.setBackground(LoginMenu.lightGreen);
        inputPanel.setBackground(LoginMenu.lightGreen);

        JLabel passwordPrompt = new JLabel("Please enter your current password:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        inputPanel.add(passwordPrompt);
        inputPanel.add(passwordField);
        inputPanel.add(nextButton);

        JTextArea feedbackArea = new JTextArea(5, 30);
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        feedbackArea.setBackground(LoginMenu.lightGreen);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(feedbackArea, BorderLayout.CENTER);
        dialog.add(panel);

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    char[] currentPassword = passwordField.getPassword();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                    Message message = new Message();
                    message.setMessageType(Message.Message_EDIT_PASSWORD_CLIENT);
                    message.setContent(new String(currentPassword));
                    oos.writeObject(message);
                    oos.flush();

                    Message message1 = (Message) ois.readObject();
                    if (message1.getMessageType().equals(Message.Message_EDIT_PASSWORD_SUCCESSFUL)) {
                        feedbackArea.setText("Current password verified. Please enter your new password.");
                        passwordPrompt.setText("Please enter your new password:");
                        passwordField.setText("");
                        nextButton.setText("Submit");
                        nextButton.removeActionListener(this); // Remove the current action listener
                        nextButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent ev) {
                                try {
                                    char[] newPassword = passwordField.getPassword();
                                    Message message2 = new Message();
                                    message2.setContent(new String(newPassword));
                                    oos.writeObject(message2);
                                    oos.flush();
                                    u.setPassword(new String(newPassword));
                                    JOptionPane.showMessageDialog(dialog, "Password has been changed!");
                                    dialog.dispose();

                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                    } else {
                        feedbackArea.setText("The current password is not correct.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        dialog.setVisible(true);
    }
    //Method to modify Bio
    public void editBio() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Edit Bio");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(900, 500);
        dialog.setLocationRelativeTo(null);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        panel.setBackground(LoginMenu.lightGreen);
        inputPanel.setBackground(LoginMenu.lightGreen);
        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        inputPanel.add(textField);
        inputPanel.add(submitButton);

        JTextArea feedbackArea = new JTextArea(5, 30);
        feedbackArea.setBackground(LoginMenu.lightGreen);
        feedbackArea.setEditable(false);
        feedbackArea.setFont(new Font("Times New Roman", Font.PLAIN, 15));

        feedbackArea.setBackground(LoginMenu.lightGreen);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(feedbackArea, BorderLayout.CENTER);
        dialog.add(panel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String bio= textField.getText();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Message message = new Message();
                    message.setMessageType(Message.Message_EDIT_BIO_CLIENT);
                    message.setContent(bio);
                    oos.writeObject(message);
                    oos.flush();
                    Message message1 = (Message) ois.readObject();
                    if (message1.getMessageType().equals(Message.Message_EDIT_BIO_SERVER)) {
                        u.setBio(bio);
                        JOptionPane.showMessageDialog(dialog, "bio been changed!");
                        dialog.dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        dialog.setVisible(true);
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
