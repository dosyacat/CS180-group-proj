import java.io.Serializable;
import java.util.HashMap;
/**
 * UserFriendDataBase Class - To store information of friends
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public class UserFriendDataBase implements Serializable {
    // HashMap to store friends
    private HashMap<String, User> friendHashMap = new HashMap<>();
    // HashMap to store friend requests
    private HashMap<String, User> requestFriendHashMap = new HashMap<>();
    // HashMap to store blocked friends
    private HashMap<String, User> blockedFriendHashMap = new HashMap<>();

    // Getter and setter for blockedFriendHashMap
    public HashMap<String, User> getBlockedFriendHashMap() {
        return blockedFriendHashMap;
    }

    public void setBlockedFriendHashMap(HashMap<String, User> blockedFriendHashMap) {
        this.blockedFriendHashMap = blockedFriendHashMap;
    }

    // Getter and setter for requestFriendHashMap
    public HashMap<String, User> getRequestFriendHashMap() {
        return requestFriendHashMap;
    }

    public void setRequestFriendHashMap(HashMap<String, User> requestFriendHashMap) {
        this.requestFriendHashMap = requestFriendHashMap;
    }

    // Getter and setter for friendHashMap
    public HashMap<String, User> getFriendHashMap() {
        return friendHashMap;
    }

    public void setFriendHashMap(HashMap<String, User> friendHashMap) {
        this.friendHashMap = friendHashMap;
    }

    // Method to find a friend by username
    public User findFriend(String username) {
        return friendHashMap.get(username);
    }

    // Method to accept a friend request
    public void acceptFriendRequest(User user) {
        friendHashMap.put(user.getUsername(), user);
        requestFriendHashMap.remove(user.getUsername());
    }

    // Method to decline a friend request
    public void declineFriendRequest(User user) {
        requestFriendHashMap.remove(user.getUsername());
    }

    // Method to display blocked friends
    public void blockedFriendsInfromation() {
        if (blockedFriendHashMap.isEmpty()) {
            System.out.println();
            System.out.println("Your blocked friends list is empty now!");
            return;
        }
        System.out.println("===================Blocked Friends List================");
        int i = 1;
        for (User user : blockedFriendHashMap.values()) {
            System.out.println("User " + i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

    // Method to display friend requests
    public void requestFriendsInformation() {
        if (requestFriendHashMap.isEmpty()) {
            System.out.println();
            System.out.println("Your friend request list is empty now!");
            return;
        }
        System.out.println("===================Friend Request List================");
        int i = 1;
        for (User user : requestFriendHashMap.values()) {
            System.out.println("User " + i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

    // Method to display friends
    public void friendsInformation() {
        if (friendHashMap.isEmpty()) {
            System.out.println();
            System.out.println("Your friend list is empty now!");
            return;
        }
        System.out.println("===================Friend List================");
        int i = 1;
        for (User user : friendHashMap.values()) {
            System.out.println("Friend " + i + ": " + user.toString());
            System.out.println();
            i++;
        }
    }

    // Method to add a friend
    public boolean addFriend(User user) {
        if (friendHashMap.get(user.getUsername()) != null) {
            System.out.println(user.getUsername() + " is already your friend, you can't add again.");
            return false;
        }
        return true;
    }

    // Method to remove a friend
    public boolean removeFriend(User user) {
        if (friendHashMap.get(user.getUsername()) == null) {
            System.out.println(user.getUsername() + " is not your friend!");
            return false;
        }
        friendHashMap.remove(user.getUsername());
        return true;
    }

    // Method to block a friend
    public void blockFriend(User user) {
        if (blockedFriendHashMap.get(user.getUsername()) != null) {
            System.out.println("You have blocked " + user.getUsername() + ". Cannot block again.");
            return;
        }
        blockedFriendHashMap.put(user.getUsername(), user);
        System.out.println("Block " + user.getUsername() + " successfully!");
    }

    // Method to unblock a friend
    public void unBlockFriend(User user) {
        if (blockedFriendHashMap.get(user.getUsername()) == null) {
            System.out.println("Them is not in your block list!");
            return;
        }
        blockedFriendHashMap.remove(user.getUsername());
        System.out.println("unBlock " + user.getUsername() + " successfully!");
    }
}
