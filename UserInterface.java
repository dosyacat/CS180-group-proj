import java.io.Serializable;

public interface UserInterface extends Serializable {

    public boolean isMessagePrivacySettings();

    public void setMessagePrivacySettings(boolean messagePrivacySettings);

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
