import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
/**
 * Class for creating SignIn Menu
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 29, 2024
 */

public class SignUpMenu extends JDialog implements SignUpInterface {
    private Socket socket;
    private JPanel panel;
    private JTextField usernameField, passwordField, emailField, bioField;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public SignUpMenu(Frame owner) {
        super(owner, "Sign Up", true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(owner);

        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            Message message = new Message();
            message.setMessageType(Message.Message_SIGNUP_CLIENT);
            oos.writeObject(message);

            signUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void signUp() {
        panel = new JPanel(new GridBagLayout());
        panel.setBackground(LoginMenu.lightGreen);
        this.setContentPane(panel);
        addUsernameField();
        this.setVisible(true);
    }

    private void addUsernameField() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Please enter your Username:"), constraints);

        constraints.gridy = 1;
        usernameField = new JTextField(20);
        panel.add(usernameField, constraints);

        constraints.gridy = 2;
        JButton confirmUsernameButton = new JButton("Confirm");
        panel.add(confirmUsernameButton, constraints);

        confirmUsernameButton.addActionListener(e -> {
            String username = usernameField.getText();
            if (checkUsername(username)) {
                panel.removeAll();
                addPasswordField(username);
                panel.revalidate();
                panel.repaint();
            } else {
                usernameField.requestFocusInWindow();
            }
        });
    }

    private boolean checkUsername(String username) {
        if (username.isEmpty() || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username cannot be empty!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (username.contains(" ")) {
            JOptionPane.showMessageDialog(this,
                    "Username cannot contain space!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Message message = new Message();
        message.setContent(username);
        try {
            oos.writeObject(message);
            Message response = (Message) ois.readObject();
            if (response.getMessageType().equals(Message.Message_SIGNUP_FAIL)) {
                JOptionPane.showMessageDialog(this,
                        "This username is already taken!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    private void addPasswordField(String username) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Please enter your Password:"), constraints);

        constraints.gridy = 1;
        passwordField = new JTextField(20);
        panel.add(passwordField, constraints);

        constraints.gridy = 2;
        JButton confirmPasswordButton = new JButton("Next");
        panel.add(confirmPasswordButton, constraints);

        confirmPasswordButton.addActionListener(e -> {
            String password = passwordField.getText();
            if (password == null || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Password cannot be empty. Please enter your password.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                passwordField.requestFocusInWindow();
            } else {
                panel.removeAll();
                addEmailField(username, password);
                panel.revalidate();
                panel.repaint();
            }
        });
    }

    private void addEmailField(String username, String password) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Please enter your Email:"), constraints);

        constraints.gridy = 1;
        emailField = new JTextField(20);
        panel.add(emailField, constraints);

        constraints.gridy = 2;
        JButton confirmEmailButton = new JButton("Next");
        panel.add(confirmEmailButton, constraints);

        confirmEmailButton.addActionListener(e -> {
            String email = emailField.getText();
            panel.removeAll();
            addBioField(username, password, email);
            panel.revalidate();
            panel.repaint();
        });
    }

    private void addBioField(String username, String password, String email) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(new JLabel("Please enter your bio:"), constraints);

        constraints.gridy = 1;
        bioField = new JTextField(20);
        panel.add(bioField, constraints);

        constraints.gridy = 2;
        JButton confirmBioButton = new JButton("Sign Up!");
        panel.add(confirmBioButton, constraints);

        confirmBioButton.addActionListener(e -> {
            String bio = bioField.getText();
            User user = new User(username, password, email, bio);
            try {
                oos.writeObject(user);
                JOptionPane.showMessageDialog(this, "You have signed up successfully!");
                this.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
