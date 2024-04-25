import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendMenu extends JDialog {
    private User user;
    private UserService userService;

    public FriendMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;

        setTitle("Friends Menu");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Friends Menu (" + user.getUsername() + ")");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel);

        JButton showFriendsButton = new JButton("Show Friends List");
        showFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.showFriendList();
            }
        });
        mainPanel.add(showFriendsButton);

        JButton addFriendButton = new JButton("Add a Friend");
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.AddFriend();
            }
        });
        mainPanel.add(addFriendButton);

        JButton removeFriendButton = new JButton("Remove a Friend");
        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.removeFriend();
            }
        });
        mainPanel.add(removeFriendButton);

        JButton blockFriendButton = new JButton("Block a Friend");
        blockFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.blockFriend();
            }
        });
        mainPanel.add(blockFriendButton);

        JButton unblockFriendButton = new JButton("Unblock a Friend");
        unblockFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.unBlockFriend();
            }
        });
        mainPanel.add(unblockFriendButton);

        add(mainPanel);
        setVisible(true);
    }
}
