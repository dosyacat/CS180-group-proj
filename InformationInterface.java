import java.util.concurrent.ConcurrentHashMap;

public interface InformationInterface {
    static void clearFile(String filePath) {}
    static User readUser(String userName) {return null; }
    static void writeUser(User user) {}
    static void readUserFriendDataBase(User user) {}
    static void writeFriend(String userName1, String userName2, boolean flag) {}
    static void blockFriend(String userName1, String userName2, boolean flag) {}
    static ConcurrentHashMap<String, User> readUser() { return null; }
    static void writeUser(ConcurrentHashMap<String, User> userHashMap) {}

}
