import java.util.Date;

public interface MessageServiceInterface {

    /* Sends a text message from the sender to the receiver with the given content.
       sender   The username of the sender
       receiver The username of the receiver
       content  The content of the message */
    public static void sendMessage(String sender, String receiver, String content) {
        // Method signature, no implementation in interface
    }

    //Sends a picture message from the sender to the receiver.
    public static void sendPictureMessage(String sender, String receiver) {
        // Method signature, no implementation in interface
    }

}
