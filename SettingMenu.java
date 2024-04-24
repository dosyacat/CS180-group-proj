import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingMenu extends JDialog {
    private User user;
    private UserService userService;
    JLabel usernameLabel;
    JLabel emailLabel;
    JLabel bioLabel;
    JLabel messageSettingLabel;

    public SettingMenu(User user, UserService userService) {
        this.setTitle("Setting Menu");
        this.user = user;
        this.userService = userService;
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(LoginMenu.lightGreen);

        JLabel label = new JLabel("Welcome to the Setting!");
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));

        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);
        northPanel.setBackground(LoginMenu.lightGreen);
        northPanel.setPreferredSize(new Dimension(0, 200));
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.setBackground(LoginMenu.lightGreen);

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 24);
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setBackground(LoginMenu.lightGreen);
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));

        usernameLabel = new JLabel("Username: " + user.getUsername());
        usernameLabel.setFont(labelFont);
        userInfoPanel.add(usernameLabel);

        emailLabel = new JLabel("Email: " + user.getEmail());
        emailLabel.setFont(labelFont);
        userInfoPanel.add(emailLabel);

        bioLabel = new JLabel("Bio: " + user.getBio());
        bioLabel.setFont(labelFont);
        userInfoPanel.add(bioLabel);

        String messagePrivacy;
        if (user.isMessagePrivacySettings())
            messagePrivacy = "Allow all users to send you messages.";
        else
            messagePrivacy = "Allow only your friends to send you messages.";
        messageSettingLabel = new JLabel("Message Setting: " + messagePrivacy);
        messageSettingLabel.setFont(labelFont);
        userInfoPanel.add(messageSettingLabel);

        mainPanel.add(userInfoPanel, BorderLayout.CENTER);

        JButton profilesButton = new JButton("Edit Profiles");
        profilesButton.setFont(labelFont);
        profilesButton.addActionListener((e) -> {
            EditProfile editProfile = new EditProfile(user, userService);
            editProfile.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshUserInfo();
                }
            });
        });

        buttonPanel.add(profilesButton);
        buttonPanel.setPreferredSize(new Dimension(0, 200));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }

    public void refreshUserInfo() {
        usernameLabel.setText("Username: " + user.getUsername());
        emailLabel.setText("Email: " + user.getEmail());
        bioLabel.setText("Bio: " + user.getBio());

        String messagePrivacy;
        if (user.isMessagePrivacySettings())
            messagePrivacy = "Allow all users to send you messages.";
        else
            messagePrivacy = "Allow only your friends to send you messages.";

        messageSettingLabel.setText("Message Setting: " + messagePrivacy);
    }
}

