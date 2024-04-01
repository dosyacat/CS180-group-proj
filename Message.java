import java.io.Serializable;
/**
 * The Message class represents a message in the messaging system.
 * Implements Serializable to allow objects of this class to be serialized.
 * Implements SuperMessage to provide message-related functionality.
 */

public class Message implements Serializable, SuperMessage {

    private String sender;
    private String receiver;
    private String content;
    private String messageTime;
    //Gets the sender of the message.
    public String getSender() {
        return sender;
    }
    //Sets the sender of the message
    public void setSender(String sender) {
        this.sender = sender;
    }
    //Gets the receiver of the message
    public String getReceiver() {
        return receiver;
    }
    //Sets the receiver of the message
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    //Gets Message Content
    public String getContent() {
        return content;
    }
    //Sets Message Content
    public void setContent(String content) {
        this.content = content;
    }
    //Gets Message Time
    public String getMessageTime() {
        return messageTime;
    }
    //Sets Message Time
    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    //Returns a string representation of the message.
    public String toString() {
        return "\nMessage : " +
                "\nsender : " + sender +
                ", receiver :" + receiver +
                "\ncontent : " + content +
                "\nmessageTime : " + messageTime;
    }
}
