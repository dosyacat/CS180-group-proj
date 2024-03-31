import java.io.Serializable;

public interface UserInterface extends Serializable {

    public void setFriendDataBase(UserFriendDataBase friendDataBase);

    public boolean isMessagePrivacySettings();

    public void setMessagePrivacySettings(boolean messagePrivacySettings);

    public void addFriend(User user);

    public void removeFriend(User user);

    public void blockFriend(User user);

    public void unBlockFriend(User user);

    public void acceptFriendRequest(User user);

    public void declineFriendRequest(User user);
    public void addSendMessage(Message message);

    public void addReceiveMessage(Message message);

    public void deleteMessage(String name, int messageNumber);

    public UserMessageDataBase getMessageDataBase();

    public String getUsername();

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getEmail();

    public void setEmail(String email);

    public String getBio();

    public void setBio(String bio);

    public String toString();

    public void profileInformation();
}
