import java.util.HashMap;

public class DataBase {
    private static HashMap<String, User> userHashMap = new HashMap<>();

    public static void add(User user) {
        userHashMap.put(user.getUsername(), user);
    }

    public static boolean check(String account, String password) {
        User user = userHashMap.get(account);
        if (user == null) return false;
        if (!(user.getPassword().equals(password))) return false;
        return true;
    }

    public static void userInformation() {
        System.out.println("Here is the information for our users!");
        int i = 1;
        for (User user : userHashMap.values()) {
            System.out.println("User"+ i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

    public static User findUser(String username) {
        return userHashMap.get(username);
    }


}
