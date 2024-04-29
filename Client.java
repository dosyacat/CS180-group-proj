/**
 * Client class - Contains the Main Method and runs the code
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 29, 2024
 */
public class Client implements ClientInterface {
    
    private String key = "";
    private UserService userService = new UserService();
    public static void main(String[] args) {
        new LoginMenu();
    }

}
