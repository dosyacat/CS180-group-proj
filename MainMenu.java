import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Class for creating Main Menu
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 29, 2024
 */

public class MainMenu extends JDialog implements MainMenuInterface {
    private JPanel mainPanel;
    private UserService userService;
    private User user;

    public MainMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;
        setTitle("Main Menu");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("Welcome," + user.getUsername() + "!");
        label.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(label);
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);

        mainPanel.setBackground(LoginMenu.lightGreen);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        buttonPanel.setBackground(LoginMenu.lightGreen);

        JButton viewUserButton = new JButton("User Viewer");
        viewUserButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        viewUserButton.addActionListener(new UserViewListener());
        buttonPanel.add(viewUserButton);

        JButton searchUserButton = new JButton("User Search");
        searchUserButton.addActionListener(new UserSearchListener());
        searchUserButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        buttonPanel.add(searchUserButton);

        JButton messageMenuButton = new JButton("Messages Menu");
        messageMenuButton.addActionListener(new MessageMenuListener());
        messageMenuButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        buttonPanel.add(messageMenuButton);

        JButton friendMenuButton = new JButton("Friends Menu");
        friendMenuButton.addActionListener(new FriendMenuListener());
        friendMenuButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        buttonPanel.add(friendMenuButton);

        JButton settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        settingsButton.addActionListener(new SettingMenuListener());
        buttonPanel.add(settingsButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        logoutButton.addActionListener(new LogoutListener());
        buttonPanel.add(logoutButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private class UserViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userService.userView();
        }
    }

    private class UserSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userService.userSearch();
        }
    }

    private class MessageMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new MessageMenu(userService);
        }
    }

    private class FriendMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FriendMenu(user, userService);
        }
    }

    private class SettingMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new SettingMenu(user, userService);
        }
    }

    private class LogoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            userService.logout();
            dispose();
        }
    }
}
