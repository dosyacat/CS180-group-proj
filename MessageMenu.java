import javax.swing.*;
import java.awt.*;

public class MessageMenu extends JFrame {
    public final static Color lightGreen = new Color(152, 251, 152);

    public MessageMenu(UserService userService) {
        setTitle("Messages Menu");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 10, 10));  // 使用网格布局
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(lightGreen);

        JLabel titleLabel = new JLabel("Message Menu", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        mainPanel.add(titleLabel);

        JButton sendMessage = new JButton("Send a Message");
        sendMessage.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        sendMessage.addActionListener(e -> {
            userService.sendMessage(mainPanel);
        });
        mainPanel.add(sendMessage);

        JButton viewMessages = new JButton("Check Received Messages");
        viewMessages.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        viewMessages.addActionListener(e -> {
            userService.checkReceiveMessages(mainPanel);
        });
        mainPanel.add(viewMessages);

        JButton deleteMessages = new JButton("Delete Messages!");
        deleteMessages.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        deleteMessages.addActionListener( e -> {
            userService.deleteMessages(mainPanel);
        });
        mainPanel.add(deleteMessages);

        add(mainPanel);
        setVisible(true);
    }
}
