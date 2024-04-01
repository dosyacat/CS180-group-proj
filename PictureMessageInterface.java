/**
 * Interface for PictureMessage.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public interface PictureMessageInterface {

    // Gets the sender of the picture message.
    public String getSender();

    // Sets the sender of the picture message.
    public void setSender(String sender);

    // Gets the receiver of the picture message.
    public String getReceiver();

    // Sets the receiver of the picture message.
    public void setReceiver(String receiver);

    // Gets the image data of the picture message.
    public byte[] getImage();

    // Sets the image data of the picture message.
    public void setImage(byte[] image);

    // Gets the time of the picture message being sent.
    public String getMessageTime();

    // Sets the time of the picture message being sent.
    public void setMessageTime(String messageTime);

    // Returns a string representation of the picture message.
    // Displays the picture.
    @Override
    public String toString();
}
