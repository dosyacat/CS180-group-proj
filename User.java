import java.io.Serializable;
import java.util.ArrayList;

/**
 * The User class represents a user in the messaging system.
 * Implements Serializable to allow objects of this class to be serialized.
 * Implements Interface.UserInterface to provide user-related functionality.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 29, 2024
 */

public class User implements Serializable, UserInterface {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String email;
    private String bio;
    private boolean messagePrivacySettings = true;
    private ArrayList<String> friendArrayList = new ArrayList<>();
    private ArrayList<String> blockArrayList = new ArrayList<>();

    public ArrayList<String> getFriendArrayList() {
        return friendArrayList;
    }

    public void setFriendArrayList(ArrayList<String> friendArrayList) {
        this.friendArrayList = friendArrayList;
    }
    public ArrayList<String> getBlockArrayList() {
        return blockArrayList;
    }

    public void setBlockArrayList(ArrayList<String> blockArrayList) {
        this.blockArrayList = blockArrayList;
    }

    // Method to find a friend by username
    public boolean findFriend(String username) {
        return friendArrayList.contains(username);
    }

    // Method to accept a friend request
    public void acceptFriendRequest(User user) {
        friendArrayList.add(user.getUsername());
    }

    //Uploads a profile picture for the user.

    //Gets message privacy settings
    public boolean isMessagePrivacySettings() {
        return messagePrivacySettings;
    }
    //sets message privacy settings
    public void setMessagePrivacySettings(boolean messagePrivacySettings) {
        this.messagePrivacySettings = messagePrivacySettings;
    }

    public User() { }
    //Parameterized Constructor 
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = null;
        this.bio = null;
    }
    //Parameterized Constructor 
    public User(String username, String password, String email, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
    }

    public User(String username,
                String password,
                String email,
                String bio,
                boolean messagePrivacySettings) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.messagePrivacySettings = messagePrivacySettings;
    }

    //Gets Username
    public String getUsername() {
        return username;
    }
    //Sets Username
    public void setUsername(String username) {
        this.username = username;
    }
    //Gets Password
    public String getPassword() {
        return password;
    }
    //Sets Password
    public void setPassword(String password) {
        this.password = password;
    }
    //Gets Email
    public String getEmail() {
        return email;
    }
    //Sets Email
    public void setEmail(String email) {
        this.email = email;
    }
    //Gets User's Bio
    public String getBio() {
        return bio;
    }

    //Sets User's Bio
    public void setBio(String bio) {
        this.bio = bio;
    }
    // Method to display user information
    @Override
    public String toString() {
        return "username : " + username +
                "\nemail : " + email +
                "\nbio : " + bio;
    }
    //Method to display profile information
    public void profileInformation() {
        System.out.println("=========== " + username + "=============");
        System.out.println(this.toString());
    }
}
