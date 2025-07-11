import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for creating Friends Menu
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 * </p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong ,
 *         Kaustubh Mathur
 * @version April 29, 2024
 */

public class FriendMenu extends JDialog implements FriendsMenuInterface {
    private User user;
    private UserService userService;

    // TODO: add an exit button
    public FriendMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;

        setTitle("Friends Menu");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Friends Menu (" + user.getUsername() + ")");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        mainPanel.add(titleLabel);

        JButton showFriendsButton = new JButton("Show Friends List");
        showFriendsButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        showFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.showFriendList(mainPanel);
            }
        });
        mainPanel.add(showFriendsButton);

        JButton addFriendButton = new JButton("Add a Friend");
        addFriendButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.AddFriend(mainPanel);
            }
        });
        mainPanel.add(addFriendButton);

        JButton removeFriendButton = new JButton("Remove a Friend");
        removeFriendButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.removeFriend(mainPanel);
            }
        });
        mainPanel.add(removeFriendButton);

        JButton blockFriendButton = new JButton("Block an User");
        blockFriendButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        blockFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.blockFriend(mainPanel);
            }
        });
        mainPanel.add(blockFriendButton);

        JButton unblockFriendButton = new JButton("Unblock a Friend");
        unblockFriendButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        unblockFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.unBlockFriend(mainPanel);
            }
        });
        mainPanel.add(unblockFriendButton);

        add(mainPanel);
        setVisible(true);
    }
}
