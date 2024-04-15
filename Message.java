import java.io.Serializable;

/**
 * The Message class represents a message in the messaging system.
 * Implements Serializable to allow objects of this class to be serialized.
 * Implements Interface.SuperMessage to provide message-related functionality.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 15, 2024
 */
public class Message implements Serializable, MessageInterface {

    public final static String Message_SIGNUP_CLIENT = "0";
    public final static String Message_SIGNUP_Server = "1";
    public final static String Message_SIGNUP_SUCCESSFUL = "2";
    public final static String Message_SIGNUP_FAIL = "3";
    public final static String Message_LOGIN_SUCCESSFUL = "4";
    public final static String Message_LOGIN_CLIENT = "5";
    public final static String Message_LOGIN_SERVER = "9";
    public final static String Message_LOGIN_FAIL = "6";
    public final static String Message_USERVIEW_CLIENT = "7";
    public final static String Message_USERVIEW_SERVER = "8";
    public final static String Message_USESEARCH_CLIENT = "9";
    public final static String Message_USESEARCH_SERVER = "10";
    public final static String Message_EXIT = "11";
    public final static String Message_EDIT_USERNAME_CLIENT = "12";
    public final static String Message_EDIT_USERNAME_SERVER_FAIL = "13";
    public final static String Message_EDIT_USERNAME_SERVER_SUCCESSFUL = "14";
    public final static String Message_EDIT_EMAIL_CLIENT = "15";
    public final static String Message_EDIT_EMAIL_SERVER = "16";
    public final static String Message_EDIT_BIO_CLIENT = "20";
    public final static String Message_EDIT_BIO_SERVER = "21";
    public final static String Message_EDIT_PASSWORD_CLIENT = "17";
    public final static String Message_EDIT_PASSWORD_FAIL = "18";
    public final static String Message_EDIT_PASSWORD_SUCCESSFUL = "19";
    public final static String Message_ADDFRIEND_CLIENT = "22";
    public final static String Message_ADDFRIEND_SERVER_FAIL_1 = "23";
    public final static String Message_ADDFRIEND_SERVER_FAIL_2 = "28";
    public final static String Message_ADDFRIEND_SERVER_FAIL_3 = "38";
    public final static String Message_ADDFRIEND_SERVER_FAIL_4 = "41";
    public final static String Message_ADDFRIEND_SERVER_SUCCESSFUL = "24";
    public final static String Message_REMOVEFRIEND_CLIENT = "25";
    public final static String Message_REMOVEFRIEND_SERVER_FAIL = "26";
    public final static String Message_REMOVEFRIEND_SERVER_SUCCESSFUL = "27";
    public final static String Message_BLOCKFRIEND_CLIENT = "30";
    public final static String Message_BLOCKFRIEND_SERVER_FAIL = "31";
    public final static String Message_BLOCKFRIEND_SERVER_FAIL_2 = "36";
    public final static String Message_BLOCKFRIEND_SERVER_SUCCESSFUL = "32";
    public final static String Message_UNBLOCKFRIEND_CLIENT = "37";
    public final static String Message_UNBLOCKFRIEND_SERVER_FAIL_1 = "33";
    public final static String Message_UNBLOCKFRIEND_SERVER_FAIL_2 = "35";
    public final static String Message_UNBLOCKFRIEND_SERVER_SUCCESSFUL = "34";
    public final static String Message_SHOWFRIEND_CLIENT = "39";
    public final static String Message_SHOWFRIEND_SERVER = "40";
    public final static String Message_CHANGEPRIVACY_CLIENT= "42";
    public final static String Message_CHANGEPRIVACY_SERVER = "43";
    public final static String Message_GENERALMESSAGE_CLIENT = "45";
    public final static String Message_GENERALMESSAGE_SERVER_FAIL1 = "47";
    public final static String Message_GENERALMESSAGE_SERVER_FAIL2 = "48";
    public final static String Message_GENERALMESSAGE_SERVER_FAIL3 = "49";
    public final static String Message_GENERALMESSAGE_SERVER_SUCCESSFUL = "50";
    public final static String Message_CHECKMESSAGE_CLIENT = "51";
    public final static String Message_CHECKMESSAGE_SERVER = "52";
    public final static String Message_DELETEMESSAGE_CLIENT = "54";
    public final static String Message_DELETEMESSAGE_SERVER_FAIL = "55";

    private static final long serialVersionUID = 1L;
    private String sender;
    private String receiver;
    private String content;
    private String messageTime;
    private String messageType;

    public Message() {

    }

    public Message(String sender, String receiver, String content, String messageTime) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.messageTime = messageTime;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

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
