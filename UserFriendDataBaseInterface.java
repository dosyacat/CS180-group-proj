import java.util.HashMap;
/**
 * Interface for UserFriendDataBase.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public interface UserFriendDataBaseInterface {

    public HashMap<String, User> getBlockedFriendHashMap();

    public void setBlockedFriendHashMap(HashMap<String, User> blockedFriendHashMap);

    public HashMap<String, User> getRequestFriendHashMap();

    public void setRequestFriendHashMap(HashMap<String, User> requestFriendHashMap);

    public HashMap<String, User> getFriendHashMap();

    public  void setFriendHashMap(HashMap<String, User> friendHashMap);

    public User findFriend(String username);

    public void acceptFriendRequest(User user);

    public void declineFriendRequest(User user);

    public void blockedFriendsInfromation();

    public void requestFriendsInformation();

    public void friendsInformation();

    public boolean addFriend(User user);

    public boolean removeFriend(User user);

    public void blockFriend(User user);

    public void unBlockFriend(User user);
}
