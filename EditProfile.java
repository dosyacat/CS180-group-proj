import javax.swing.*;
import java.awt.*;

public class EditProfile extends JDialog {
    private User user;
    private UserService userService;

    public EditProfile(User user, UserService userService) {
        this.setTitle("Edit Profile");
        this.user = user;
        this.userService = userService;
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonPanel.setBackground(LoginMenu.lightGreen);

        JLabel label = new JLabel("What do you want to chane?");
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);
        northPanel.setBackground(LoginMenu.lightGreen);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LoginMenu.lightGreen);
        mainPanel.add(northPanel, BorderLayout.NORTH);

        JButton usernameButton = new JButton("Edit Username");
        usernameButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        usernameButton.addActionListener(e -> {
            userService.editUserName();
        });
        buttonPanel.add(usernameButton);

        JButton passwordButton = new JButton("Edit Password");
        passwordButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        passwordButton.addActionListener(e -> {
           userService.editPassword();
        });
        buttonPanel.add(passwordButton);

        JButton emailButton = new JButton("Edit Email");
        emailButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        emailButton.addActionListener(e -> {
            userService.editEmail();
        });
        buttonPanel.add(emailButton);

        JButton bioButton = new JButton("Edit Bio");
        bioButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        bioButton.addActionListener(e -> {
            userService.editBio();
        });
        buttonPanel.add(bioButton);

        JButton messageSettingButton = new JButton("Edit Message Privacy");
        messageSettingButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        messageSettingButton.addActionListener(e -> {
            userService.changeMessagesPrivacy();
            JOptionPane.showMessageDialog(null,
                    "Change successful", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        buttonPanel.add(messageSettingButton);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);

    }
}
