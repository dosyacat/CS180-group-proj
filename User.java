import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String bio;
    private MessageDataBase sendMessageDataBase = new MessageDataBase();
    private MessageDataBase receiveMessageDataBase = new MessageDataBase();

    public void addSendMessage(Message message) {
        sendMessageDataBase.addSendMessage(message);
    }

    public void addReceiveMessage(Message message) {
        receiveMessageDataBase.addReceiveMessage(message);
    }


    public MessageDataBase getSendMessageDataBase() {
        return sendMessageDataBase;
    }

    public void setSendMessageDataBase(MessageDataBase sendMessageDataBase) {
        this.sendMessageDataBase = sendMessageDataBase;
    }

    public MessageDataBase getReceiveMessageDataBase() {
        return receiveMessageDataBase;
    }

    public void setReceiveMessageDataBase(MessageDataBase receiveMessageDataBase) {
        this.receiveMessageDataBase = receiveMessageDataBase;
    }

    public ArrayList<Message> getSendMessageArrayList() {
        return sendMessageDataBase.getSendArrayList();
    }

    public ArrayList<Message> getReceiveArrayList() {
        return receiveMessageDataBase.getReceiveArrayList();
    }

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = null;
        this.bio = null;
    }

    public User(String username, String password, String email, String bio) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "username : " + username +
                "\nemail : " + email +
                "\nbio : " + bio;
    }
}
