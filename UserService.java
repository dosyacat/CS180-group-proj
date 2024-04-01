    /**
 * UserService Class - Facilitates the User Experience by providing various functions
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public class UserService {

    public boolean checkSecurity(String account, String password) {
        return DataBase.check(account, password);
    }

    public boolean checkBlocked(User user1, User user2) {
        if (user1 == null || user2 == null) return false;
        return user1.getFriendDataBase().getBlockedFriendHashMap().containsKey(user2.getUsername()) ||
                user2.getFriendDataBase().getBlockedFriendHashMap().containsKey(user1.getUsername());
    }

    public boolean checkFriend(User user1, User user2) {
        if (user1 == null || user2 == null) return false;
        return user1.getFriendDataBase().getFriendHashMap().containsKey(user2.getUsername()) &&
                user2.getFriendDataBase().getFriendHashMap().containsKey(user1.getUsername());
    }

    public boolean existFriendRequest(User user1, User user2) {
        if (user1 == null || user2 == null) return false;
        return user1.getFriendDataBase().getRequestFriendHashMap().containsKey(user2.getUsername());
    }

    // check user MessagePrivacy status
    public boolean chechMessagePrivacySetting(User user) {
        try {
            return user.isMessagePrivacySettings();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // check if user2 has sent messages to user1
    public boolean checkMessageReceive(User user1, User user2) {
        return  (user1.getMessageDataBase().getReceiveMessageHashMap().containsKey(user2.getUsername()));
    }

    //check how many messages user2 has sent to user1
    public int checkMessageCount(User user1, User user2) {
        return user1.getMessageDataBase().getReceiveMessageHashMap().get(user2.getUsername()).size();
    }

}
