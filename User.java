import java.io.Serializable;
import java.util.ArrayList;

/**
 * The User class represents a user in the messaging system.
 * Implements Serializable to allow objects of this class to be serialized.
 * Implements Interface.UserInterface to provide user-related functionality.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String email;
    private String bio;
    private UserMessageDataBase messageDataBase = new UserMessageDataBase();
    private boolean messagePrivacySettings = true;
    private byte[] picture;
    private ArrayList<String> friendArrayList = new ArrayList<>();
    private ArrayList<String> blockArrayList = new ArrayList<>();

    public ArrayList<String> getFriendArrayList() {
        return friendArrayList;
    }

    public void setFriendArrayList(ArrayList<String> friendArrayList) {
        this.friendArrayList = friendArrayList;
    }

    public UserMessageDataBase getMessageDataBase() {
        return messageDataBase;
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

    // Method to display blocked friends
    /*
    public void blockedFriendsInfromation() {
        if (blockedFriendArrayList.isEmpty()) {
            System.out.println();
            System.out.println("Your blocked friends list is empty now!");
            return;
        }
        System.out.println("===================Blocked Friends List================");
        int i = 1;
        for (User user : blockedFriendArrayList.values()) {
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

     */


    //Sets the message database for the user.
    public void setMessageDataBase(UserMessageDataBase messageDataBase) {
        this.messageDataBase = messageDataBase;
    }
    //Gets the profile picture of the user.
    public byte[] getPicture() {
        return picture;
    }
    //Set the profile picture of the user
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    //Displays the profile picture of the user.
    public void showProfilePicture() {
        if (picture == null) {
            System.out.println("The user haven't set profile picture!");
            return;
        }
        Picture.displayPicture(picture);
    }
    //Uploads a profile picture for the user.
    public void uploadProfilePicture() {
        byte[] pictureBytes = Picture.uploadPicture();
        while (pictureBytes == null) {
            System.out.println("Invalid picture");
            System.out.println("Enter N to exit, Enter Y to try again.");
            char answer = Input.readSelection();
            if (answer == 'N') return;
            pictureBytes = Picture.uploadPicture();
        }
        this.setPicture(pictureBytes);
        System.out.println("Upload Successfully! You can view your profile picture now!");
    }

    //Gets message privacy settings
    public boolean isMessagePrivacySettings() {
        return messagePrivacySettings;
    }
    //sets message privacy settings
    public void setMessagePrivacySettings(boolean messagePrivacySettings) {
        this.messagePrivacySettings = messagePrivacySettings;
    }

    //Method to add friend to the database
    /*
    public void addFriend(User user) {

        if (user.getFriendDataBase().getBlockedFriendHashMap().get(this.username) != null) {
            System.out.println("You've been blocked by them, so you can't add them as a friend.");
            return;
        }
        if (friendDataBase.addFriend(user))
            user.getFriendDataBase().getRequestFriendHashMap().put(this.getUsername(), this);
    }


    //Removes a friend
    public void removeFriend(User user) {
        if (!this.remove1(user)) return;
        user.remove2(this);
    }

    private boolean remove1(User user) {
        return (friendDataBase.removeFriend(user));
    }

    private void remove2(User user) {
        friendDataBase.removeFriend(user);
    }
    //Method to block a specific User
    public void blockFriend(User user) {
        friendDataBase.blockFriend(user);
    }
    //Method to Unblock a specific user
    public void unBlockFriend(User user) {
        friendDataBase.unBlockFriend(user);
    }
    //Method to accept friend requests
    public void acceptFriendRequest(User user) {
        friendDataBase.acceptFriendRequest(user);
        user.getFriendDataBase().acceptFriendRequest(this);
    }
    //Method to declinefriend requests
    public void declineFriendRequest(User user) {
        friendDataBase.declineFriendRequest(user);
    }


    //Adds the sent picture to the message database of the sender
    public void addSendPicture(PictureMessage pictureMessage) {
        messageDataBase.addSendPicture(pictureMessage);
    }
    //Adds the sent picture to the message database of the receiver
    public void addReceivePicture(PictureMessage pictureMessage) {
        messageDataBase.addReceivePicture(pictureMessage);
    }
    //Adds the sent message to the message database of the sender
    public void addSendMessage(Message message) {
        messageDataBase.addSendMessage(message);
    }
    //Adds the sent message to the message database of the receiver
    public void addReceiveMessage(Message message) {
        messageDataBase.addReceiveMessage(message);
    }
    //Deletes a specific message from the database
    public void deleteMessage(String name, int messageNumber) {
        messageDataBase.deleteMessage(name, messageNumber);
    }

    //Gets message database
    public UserMessageDataBase getMessageDataBase() {
        return messageDataBase;
    }

     */
    //Default Constructor
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
