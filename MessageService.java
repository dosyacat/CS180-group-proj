
import java.util.Date;
/**
 *  The MessageService class provides methods for sending messages and picture messages.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 15, 2024
 */
public class MessageService implements MessageServiceInterface {
    /* Sends a text message from the sender to the receiver with the given content.
       sender   The username of the sender
       receiver The username of the receiver
       content  The content of the message */

    public static Message sendMessage(String sender, String receiver, String content) {

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        String time = new Date().toString();
        message.setMessageTime(time);
        return message;
    }

}
