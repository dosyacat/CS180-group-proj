import java.io.Serializable;
import java.util.ArrayList;
/**
 * The User class represents a user in the messaging system.
 * Implements Serializable to allow objects of this class to be serialized.
 * Implements UserInterface to provide user-related functionality.
 */

public class User implements Serializable, UserInterface {
    private String username;
    private String password;
    private String email;
    private String bio;
    private UserMessageDataBase messageDataBase = new UserMessageDataBase();
    private UserFriendDataBase friendDataBase = new UserFriendDataBase();
    private boolean messagePrivacySettings = true;
    private byte[] picture;
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


    //Sets the friend database for the user.
    public void setFriendDataBase(UserFriendDataBase friendDataBase) {
        this.friendDataBase = friendDataBase;
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

    public UserFriendDataBase getFriendDataBase() {
        return friendDataBase;
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
    //Default Constructor
    public User() {}
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
        System.out.println("=========== " + username +"=============");
        System.out.println(this.toString());
    }
}
