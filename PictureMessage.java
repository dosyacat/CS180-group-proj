import java.io.Serializable;

public class PictureMessage implements Serializable, SuperMessage {
    private String sender;
    private String receiver;
    private byte[] image;
    private String messageTime;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        Picture.displayPicture(image);
        return "\nMessage : " +
                "\nsender : " + sender +
                ", receiver :" + receiver +
                "\nmessageTime : " + messageTime;
    }
}
