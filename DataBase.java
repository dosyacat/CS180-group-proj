
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The DataBase class manages the storage and retrieval of user data.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 15, 2024
 */


public class DataBase implements Serializable, DataBaseInterface {


    // HashMap to store users with their usernames as keys
    private static ConcurrentHashMap<String, User> userHashMap = Information.readUser();

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

    public static ConcurrentHashMap<String, User> getUserHashMap() {
        return userHashMap;
    }

    //Finds a user in the database by username.
    public static User findUser(String userName) {
        userHashMap = Information.readUser();
        return userHashMap.get(userName);
    }

    public static void editUserName(String oldUserName, String newUserName) {
        User user = userHashMap.get(oldUserName);
        user.setUsername(newUserName);
        Information.writeUser(userHashMap);
    }
    public static void editEmail(String userName, String email) {
        User user = userHashMap.get(userName);
        user.setEmail(email);
        Information.writeUser(userHashMap);
    }
    public static void editBio(String userName, String bio) {
        User user = userHashMap.get(userName);
        user.setBio(bio);
        Information.writeUser(userHashMap);
    }

    public static void editPassword(String userName, String password) {
        User user = userHashMap.get(userName);
        user.setPassword(password);
        Information.writeUser(userHashMap);
    }
    public static void changePrivacy(String userName) {
        User user = userHashMap.get(userName);
        user.setMessagePrivacySettings(!user.isMessagePrivacySettings());
        Information.writeUser(userHashMap);
    }
    public static void addFriend(String userName1, String userName2) {
        User user1 = DataBase.findUser(userName1);
        User user2 = DataBase.findUser(userName2);
        synchronized (user1) {
            synchronized (user2) {
                ArrayList<String> friendArrayList1 = user1.getFriendArrayList();
                ArrayList<String> friendArrayList2 = user2.getFriendArrayList();
                friendArrayList1.add(userName2);
                friendArrayList2.add(userName1);
                user1.setFriendArrayList(friendArrayList1);
                user2.setFriendArrayList(friendArrayList2);
            }
        }
        Information.writeFriend(userName1, userName2, true);
    }

    public static void removeFriend(String userName1, String userName2) {
        User user1 = DataBase.findUser(userName1);
        User user2 = DataBase.findUser(userName2);
        synchronized (user1) {
            synchronized (user2) {
                ArrayList<String> user1FriendArrayList = user1.getFriendArrayList();
                ArrayList<String> user2FriendArrayList = user2.getFriendArrayList();
                user2FriendArrayList.remove(userName1);
                user1FriendArrayList.remove(userName2);
                user1.setFriendArrayList(user1FriendArrayList);
                user2.setFriendArrayList(user2FriendArrayList);
            }
        }
        Information.writeFriend(userName1, userName2, false);
    }
    public static void blockFriend(String userName1, String userName2) {
        User user1 = DataBase.findUser(userName1);
        User user2 = DataBase.findUser(userName2);
        ArrayList<String> user1BlockArrayList = user1.getBlockArrayList();
        if (!user1BlockArrayList.contains(userName2)) {
            user1BlockArrayList.add(userName2);
            user1.setBlockArrayList(user1BlockArrayList);
        }
        Information.blockFriend(userName1, userName2, true);
    }

    public static void unBlockFriend(String userName1, String userName2) {
        User user1 = DataBase.findUser(userName1);
        User user2 = DataBase.findUser(userName2);
        ArrayList<String> user1BlockArrayList = user1.getBlockArrayList();
        if (user1BlockArrayList.contains(userName2)) {
            user1BlockArrayList.remove(userName2);
            user1.setBlockArrayList(user1BlockArrayList);
        }
        Information.blockFriend(userName1, userName2, false);
    }

}
