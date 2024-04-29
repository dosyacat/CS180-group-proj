import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Class for creating Login Menu
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 29, 2024
 */

public class LoginMenu extends JFrame implements LoginInterface {

    private JButton signInButton;
    private JButton signUpButton;
    public final static Color lightGreen = new Color(152, 251, 152);

    public LoginMenu() {
        setTitle("Welcome!");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        signInButton.setMargin(new Insets(10, 20, 10, 20));
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignInMenu(LoginMenu.this);
            }
        });
        buttonPanel.add(signInButton);

        buttonPanel.add(Box.createVerticalStrut(30));

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        signUpButton.setMargin(new Insets(10, 20, 10, 20));
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpMenu(LoginMenu.this);
            }
        });
        buttonPanel.add(signUpButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }
}
