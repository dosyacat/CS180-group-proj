public interface UserServiceInterface {

    boolean checkSecurity(String account, String password);

    boolean checkBlocked(User user1, User user2);

    boolean checkFriend(User user1, User user2);

    boolean existFriendRequest(User user1, User user2);

    boolean checkMessagePrivacySetting(User user);

    boolean checkMessageReceive(User user1, User user2);

    int checkMessageCount(User user1, User user2);
}
