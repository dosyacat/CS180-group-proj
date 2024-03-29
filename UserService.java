

public class UserService {

    private User user = new User();

    public boolean checkUser(String account, String password) {
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



}
