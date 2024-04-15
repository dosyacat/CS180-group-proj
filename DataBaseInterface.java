import java.util.HashMap;

// Interface for database.java
public interface DataBaseInterface {

    // Adds a user to the database
    static void add(User user) {
    }

    // Checks if the provided account and password match any user in the database
    static boolean check(String account, String password) {
        return false;
    }

    // Displays information about all users in the database and allows viewing their profile pictures
    static void userInformation() {
    }

    // Finds a user in the database by username
    static User findUser(String username) {
        return null;
    }
}
