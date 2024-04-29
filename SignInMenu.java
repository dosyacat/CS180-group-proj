import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Class for creating SignIn Menu
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 29, 2024
 */

public class SignInMenu extends JDialog implements SignInInterface {
    private UserService userService = new UserService();
    private JTextField accountField;
    private JPasswordField passwordField;
    private JButton signInButton;
    private JButton backButton;

    public SignInMenu(Frame owner) {
        super(owner, "Sign In", true);

        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(LoginMenu.lightGreen);
        panel.setLayout(new BorderLayout());
        this.setContentPane(panel);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(LoginMenu.lightGreen);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(30));

        JLabel accountLabel = new JLabel("Please enter your account:");
        accountLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        centerPanel.add(accountLabel);

        accountField = new JTextField(20);
        accountField.setMaximumSize(new Dimension(Integer.MAX_VALUE, accountField.getPreferredSize().height));
        centerPanel.add(accountField);
        centerPanel.add(Box.createVerticalStrut(50));

        JLabel passwordLabel = new JLabel("Please enter your password:");
        passwordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        centerPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        centerPanel.add(passwordField);
        centerPanel.add(Box.createVerticalStrut(50));

        signInButton = new JButton("Sign In");
        signInButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                User user = userService.userSignIn(account, password);
                if (user != null) {
                    SignInMenu.this.dispose();
                    new MainMenu(user, userService);
                } else {
                    JOptionPane.showMessageDialog(SignInMenu.this,
                            "The account or password is not correct!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backButton = new JButton("Back to Login");
        backButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel southPanel = new JPanel();
        southPanel.add(signInButton);
        southPanel.add(backButton);
        southPanel.setBackground(LoginMenu.lightGreen);

        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
