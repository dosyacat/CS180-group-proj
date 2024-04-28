import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ConversationBetweenUsers extends JFrame {
    public final static Color lightGreen = new Color(152, 251, 152);
    public ConversationBetweenUsers(ArrayList<Message> messages) {
        setTitle("Conversation");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(lightGreen);
        JLabel welcomeLabel = new JLabel("Conversation between " + messages.getFirst().getSender() + " and " + messages.getFirst().getReceiver(), JLabel.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(welcomeLabel);
        northPanel.setBackground(lightGreen);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        JTextArea textArea = new JTextArea("", 20, 30);
        textArea.setEditable(false);
        for (Message m : messages) {
            textArea.append(m.getSender() + ": " + m.getContent() + "\n");
        }
        JScrollPane scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainPanel.add(scrollPane);
        add(mainPanel);
        setVisible(true);
    }
}
