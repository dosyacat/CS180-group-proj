/**
 * Interface SuperMessage
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public interface SuperMessage {

    String getSender();
    void setSender(String sender);

    String getReceiver();
    void setReceiver(String receiver);

    String getMessageTime();
    void setMessageTime(String messageTime);

}
