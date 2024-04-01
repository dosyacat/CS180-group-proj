import java.util.HashMap;
/**
 * The DataBase class manages the storage and retrieval of user data.
 */

public class DataBase{
    // HashMap to store users with their usernames as keys
    private static HashMap<String, User> userHashMap = new HashMap<>();
    //Adds a user to the database.
    public static void add(User user) {
        userHashMap.put(user.getUsername(), user);
    }
    //Checks if the provided account and password match any user in the database.
    public static boolean check(String account, String password) {
        User user = userHashMap.get(account);
        if (user == null) return false;
        if (!(user.getPassword().equals(password))) return false;
        return true;
    }
    //Displays information about all users in the database and allows viewing their profile pictures
    public static void userInformation() {
        System.out.println("Here is the information for our users!");
        int i = 1;
        for (User user : userHashMap.values()) {
            System.out.println("User"+ i + ": " + user.toString());
            System.out.println();
            i++;
        }
        while (true) {
            System.out.println("Do you want to view user profile picture?");
            System.out.println("Enter N to exit, Enter Y to view");
            char answer = Input.readSelection();
            if (answer == 'N') return;
            System.out.println("Whose profile picture do you want to check");
            String username = Input.readString(20, false);
            while (DataBase.findUser(username) == null) {
                System.out.println("User not found");
                System.out.println("Enter N to exit, Enter Y to try it again.");
                char answer1 = Input.readSelection();
                if (answer1 == 'N') return;
                username = Input.readString(20, false);
            }
            DataBase.findUser(username).showProfilePicture();
        }
    }
    //Finds a user in the database by username.
    public static User findUser(String username) {
        return userHashMap.get(username);
    }

}
