import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String bio;
    private MessageDataBase sendMessageDataBase = new MessageDataBase();
    private MessageDataBase receiveMessageDataBase = new MessageDataBase();
    private UserFriendDataBase friendDataBase = new UserFriendDataBase();
    private boolean messagePrivacySettings = true;

    public void setFriendDataBase(UserFriendDataBase friendDataBase) {
        this.friendDataBase = friendDataBase;
    }

    public boolean isMessagePrivacySettings() {
        return messagePrivacySettings;
    }

    public void setMessagePrivacySettings(boolean messagePrivacySettings) {
        this.messagePrivacySettings = messagePrivacySettings;
    }

    public void addFriend(User user) {
        if (user.getFriendDataBase().getBlockedFriendHashMap().get(this.username) != null) {
            System.out.println("You've been blocked by them, so you can't add them as a friend.");
            return;
        }
        if (friendDataBase.addFriend(user))
            user.getFriendDataBase().getRequestFriendHashMap().put(this.getUsername(), this);
    }

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

    public void blockFriend(User user) {
        friendDataBase.blockFriend(user);
    }

    public void unBlockFriend(User user) {
        friendDataBase.unBlockFriend(user);
    }

    public void acceptFriendRequest(User user) {
        friendDataBase.acceptFriendRequest(user);
        user.getFriendDataBase().acceptFriendRequest(this);
    }

    public void declineFriendRequest(User user) {
        friendDataBase.declineFriendRequest(user);
    }

    public UserFriendDataBase getFriendDataBase() {
        return friendDataBase;
    }

    public void addSendMessage(Message message) {
        sendMessageDataBase.addMessage(message);
    }

    public void addReceiveMessage(Message message) {
        receiveMessageDataBase.addMessage(message);
    }

    public MessageDataBase getSendMessageDataBase() {
        return sendMessageDataBase;
    }

    public void setSendMessageDataBase(MessageDataBase sendMessageDataBase) {
        this.sendMessageDataBase = sendMessageDataBase;
    }

    public MessageDataBase getReceiveMessageDataBase() {
        return receiveMessageDataBase;
    }

    public void setReceiveMessageDataBase(MessageDataBase receiveMessageDataBase) {
        this.receiveMessageDataBase = receiveMessageDataBase;
    }

    public ArrayList<Message> getSendMessageArrayList() {
        return sendMessageDataBase.getArrayList();
    }

    public ArrayList<Message> getReceiveArrayList() {
        return receiveMessageDataBase.getArrayList();
    }

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = null;
        this.bio = null;
    }

    public User(String username, String password, String email, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "username : " + username +
                "\nemail : " + email +
                "\nbio : " + bio;
    }

    public void profileInformation() {
        System.out.println("=========== " + username +"=============");
        System.out.println("Username : " + username +
                "\nEmail : " + email +
                "\nBio : " + bio);
    }




}
