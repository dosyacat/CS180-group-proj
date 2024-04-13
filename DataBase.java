

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
  /**
 * The DataBase class manages the storage and retrieval of user data.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 1, 2024
 */
 

public class DataBase implements Serializable {


    // HashMap to store users with their usernames as keys
    private static HashMap<String, User> userHashMap = Information.readUser();

    //Adds a user to the database.
    public static void add(User user) {
        Information.writeUser(user);
        userHashMap = Information.readUser();
    }
    //Checks if the provided account and password match any user in the database.
    public static boolean check(String account, String password) {
        User user = null;
        try {
            user = Information.readUser(account);
        } catch (Exception e) {
            System.out.println("Reading user error");
        }
        if (user == null) return false;
        return user.getPassword().equals(password);
    }

    public static HashMap<String, User> getUserHashMap() {
          return userHashMap;
    }

    //Finds a user in the database by username.
    public static User findUser(String userName) {
        if (userHashMap == null) return null;
        return userHashMap.get(userName);
    }

    public static void editUserName(String oldUserName, String newUserName) {
        User user = userHashMap.get(oldUserName);
        user.setUsername(newUserName);
        Information.writeUser(userHashMap);
        userHashMap = Information.readUser();
    }
    public static void editEmail(String userName, String email) {
        User user = userHashMap.get(userName);
        user.setEmail(email);
        Information.writeUser(userHashMap);
        userHashMap = Information.readUser();
    }

    public static void editPassword(String userName, String password) {
        User user = userHashMap.get(userName);
        user.setPassword(password);
        Information.writeUser(userHashMap);
        userHashMap = Information.readUser();
    }

}
