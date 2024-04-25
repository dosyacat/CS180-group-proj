import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FriendMenu extends JPanel {
    private User user;
    private UserService userService;

    public FriendMenu(User user, UserService userService) {
        this.user = user;
        this.userService = userService;

        setLayout(new GridLayout(6, 1));

        JLabel titleLabel = new JLabel("Friends Menu (" + user.getUsername() + ")");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        JButton showFriendsButton = new JButton("Show Friends List");
        showFriendsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.showFriendList();
            }
        });
        add(showFriendsButton);

        JButton addFriendButton = new JButton("Add a Friend");
        addFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.AddFriend();
            }
        });
        add(addFriendButton);

        JButton removeFriendButton = new JButton("Remove a Friend");
        removeFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.removeFriend();
            }
        });
        add(removeFriendButton);

        JButton blockFriendButton = new JButton("Block a Friend");
        blockFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.blockFriend();
            }
        });
        add(blockFriendButton);

        JButton unblockFriendButton = new JButton("Unblock a Friend");
        unblockFriendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userService.unBlockFriend();
            }
        });
        add(unblockFriendButton);
    }
}
