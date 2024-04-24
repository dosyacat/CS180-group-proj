import java.io.Serializable;
/**
 * Interface for User Class
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 15, 2024
 */

public interface UserInterface extends Serializable {

    public boolean isMessagePrivacySettings();

    public void setMessagePrivacySettings(boolean messagePrivacySettings);

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
