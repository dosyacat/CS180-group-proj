import java.io.Serializable;

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
