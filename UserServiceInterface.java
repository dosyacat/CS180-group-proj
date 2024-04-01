/**
 * Interface for UserService.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public interface UserServiceInterface {

    boolean checkSecurity(String account, String password);

    boolean checkBlocked(User user1, User user2);

    boolean checkFriend(User user1, User user2);

    boolean existFriendRequest(User user1, User user2);

    boolean checkMessagePrivacySetting(User user);

    boolean checkMessageReceive(User user1, User user2);

    int checkMessageCount(User user1, User user2);
}
