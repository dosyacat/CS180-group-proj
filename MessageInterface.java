import java.io.Serializable;
/**
 * Interface for Message Class
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 15, 2024
 */

public interface MessageInterface extends Serializable {

    public String getSender();

    public void setSender(String sender);

    public String getReceiver();

    public void setReceiver(String receiver);

    public String getContent();

    public void setContent(String content);

    public String getMessageTime();

    public void setMessageTime(String messageTime);

    public String toString();

}
