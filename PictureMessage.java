import java.io.Serializable;
/**
 * The PictureMessage class represents a message containing an image.
 * Implements Serializable to allow objects of this class to be serialized.
 * Implements SuperMessage to provide message-related functionality.
 */

public class PictureMessage implements Serializable, SuperMessage {
    private String sender;
    private String receiver;
    private byte[] image;
    private String messageTime;
    
    //Gets the sender of the picture message.
    public String getSender() {
        return sender;
    }
    //Sets the sender of the picture message
    public void setSender(String sender) {
        this.sender = sender;
    }
    //Gets the receievr of the picture message
    public String getReceiver() {
        return receiver;
    }
    //Sets the receiver of the picture message
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    // Gets the image data of the picture message.
    public byte[] getImage() {
        return image;
    }
    // Sets the image data of the picture message.
    public void setImage(byte[] image) {
        this.image = image;
    }
    //Gets the time of the picture message being sent 
    public String getMessageTime() {
        return messageTime;
    }
    //Sets the time of the picture message being sent
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }
    // Returns a string representation of the picture message.
    // Displays the picture.
    @Override
    public String toString() {
        Picture.displayPicture(image);
        return "\nMessage : " +
                "\nsender : " + sender +
                ", receiver :" + receiver +
                "\nmessageTime : " + messageTime;
    }
}
