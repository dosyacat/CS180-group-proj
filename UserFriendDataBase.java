import java.util.HashMap;

public class UserFriendDataBase {
    private HashMap<String, User> friendHashMap = new HashMap<>();
    private HashMap<String, User> requestFriendHashMap = new HashMap<>();
    private HashMap<String, User> blockedFriendHashMap = new HashMap<>();

    public HashMap<String, User> getBlockedFriendHashMap() {
        return blockedFriendHashMap;
    }

    public void setBlockedFriendHashMap(HashMap<String, User> blockedFriendHashMap) {
        this.blockedFriendHashMap = blockedFriendHashMap;
    }

    public HashMap<String, User> getRequestFriendHashMap() {
        return requestFriendHashMap;
    }

    public void setRequestFriendHashMap(HashMap<String, User> requestFriendHashMap) {
        this.requestFriendHashMap = requestFriendHashMap;
    }

    public HashMap<String, User> getFriendHashMap() {
        return friendHashMap;
    }

    public void setFriendHashMap(HashMap<String, User> friendHashMap) {
        this.friendHashMap = friendHashMap;
    }

    public User findFriend(String username) {
        return friendHashMap.get(username);
    }

    public void acceptFriendRequest(User user) {
        friendHashMap.put(user.getUsername(), user);
        requestFriendHashMap.remove(user.getUsername());
    }

    public void declineFriendRequest(User user) {
        requestFriendHashMap.remove(user.getUsername());
    }

    public void requestFriendHashMapInformation() {
        if (requestFriendHashMap.isEmpty()) {
            System.out.println();
            System.out.println("Your friend request list is empty now!");
            return;
        }
        System.out.println("===================Friend Request List================");
        int i = 1;
        for (User user : requestFriendHashMap.values()) {
            System.out.println("Friend "+ i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

    public void friendsInformation() {
        if (friendHashMap.isEmpty()) {
            System.out.println();
            System.out.println("Your friend list is empty now!");
            return;
        }
        System.out.println("===================Friend List================");
        int i = 1;
        for (User user : friendHashMap.values()) {
            System.out.println("Friend "+ i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

    public boolean addFriend(User user) {
        if (friendHashMap.get(user.getUsername()) != null) {
            System.out.println(user.getUsername() + " is already your friend, you can't add again.");
            return false;
        }
        return true;
    }

    public boolean removeFriend(User user) {
        if (friendHashMap.get(user.getUsername()) == null) {
            System.out.println(user.getUsername() + " is not your friend!");
            return false;
        }
        friendHashMap.remove(user.getUsername());
        return true;
    }

    public void blockFriend(User user) {
        if (blockedFriendHashMap.get(user.getUsername()) != null) {
            System.out.println("You have blocked " + user.getUsername() + ". Cannot block again.");
            return;
        }
        blockedFriendHashMap.put(user.getUsername(), user);
        System.out.println("Block " + user.getUsername() + "successfully!");
    }

    public void unBlockFriend(User user) {
        if (blockedFriendHashMap.get(user.getUsername()) == null) {
            System.out.println("Them is not in your block list!");
            return;
        }
        blockedFriendHashMap.remove(user.getUsername());
        System.out.println("unBlock " + user.getUsername() + "successfully!");
    }

}
