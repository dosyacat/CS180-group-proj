import java.io.Serializable;

public class Message implements Serializable {

    private String sender;
    private String receiver;
    private String content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "Message : " +
                "sender : " + sender +
                ", receiver :" + receiver +
                "\ncontent : " + content +
                "\nmessageTime : " + messageTime;
    }
}
