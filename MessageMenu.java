import javax.swing.*;
import java.awt.*;
public class MessageMenu extends JFrame {
    public final static Color lightGreen = new Color(152, 251, 152);
    public MessageMenu(UserService userService) {
        setTitle("Messages");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(lightGreen);

        JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(welcomeLabel);
        northPanel.setBackground(lightGreen);
        mainPanel.add(northPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(lightGreen);

        buttonPanel.add(Box.createVerticalStrut(50));

        JButton sendMessage = new JButton("Send a Message");
        sendMessage.addActionListener(e -> {
            String receiver = JOptionPane.showInputDialog(mainPanel, "Who do you want to send the message to?");
            String message = JOptionPane.showInputDialog(mainPanel, "Enter the message");
            String flag = userService.sendMessage(receiver, message);
            if (flag.equals(Message.Message_GENERALMESSAGE_SERVER_FAIL1)) {
                JOptionPane.showMessageDialog(mainPanel, "Sending messages failed! Receiver not found.", "Error!", JOptionPane.ERROR_MESSAGE);
            } else if (flag.equals(Message.Message_GENERALMESSAGE_SERVER_FAIL2)) {
                JOptionPane.showMessageDialog(mainPanel, "Sending messages failed! " +
                        "The user has their settings adjusted to only receive messages from friends!", "Error!", JOptionPane.ERROR_MESSAGE);
            } else if (flag.equals(Message.Message_GENERALMESSAGE_SERVER_FAIL3)) {
                JOptionPane.showMessageDialog(mainPanel, "Sending messages failed! " +
                        "You can't message someone who has blocked you", "Error!", JOptionPane.ERROR_MESSAGE);
            } else if (flag.equals(Message.Message_GENERALMESSAGE_SERVER_SUCCESSFUL)) {
                JOptionPane.showMessageDialog(mainPanel, "Sent Successfully!", "Sent!", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(mainPanel, "Some error occured!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(sendMessage);
        JButton viewMessages = new JButton("Check Received Messages");
        viewMessages.addActionListener(e -> {
            userService.checkReceiveMessages();
        });
        buttonPanel.add(viewMessages);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}
