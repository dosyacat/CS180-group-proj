
import java.util.Date;
/**
 *  The MessageService class provides methods for sending messages and picture messages.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public class MessageService {
    /* Sends a text message from the sender to the receiver with the given content.
       sender   The username of the sender
       receiver The username of the receiver
       content  The content of the message */

    public static void sendMessage(String sender, String receiver, String content) {
        /*
        // Find the sender user from the database
        User senderUser = DataBase.findUser(sender);
        
        // Find the receiver user from the database
        User receiverUser = DataBase.findUser(receiver);

        // Create a new message object
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        // Set the message time to the current time
        String time = new Date().toString();
        message.setMessageTime(time);
        System.out.println("At " + time + ",You send to " + receiver + " a message.");
        // Add the message to sender's sent messages and receiver's received messages
        senderUser.addSendMessage(message);
        receiverUser.addReceiveMessage(message);
    }
    //Sends a picture message from the sender to the receiver.
    public static void sendPictureMessage(String sender, String receiver) {
        // Find the sender user from the database
        User senderUser = DataBase.findUser(sender);
        // Find the receiver user from the database
        User receiverUser = DataBase.findUser(receiver);

        System.out.println("Image upload manager has opened, Please check all programs in your computer.");
        // Upload the picture
        byte[] pictureBytess = Picture.uploadPicture();
        while (pictureBytess == null) {
            System.out.println("Invalid picture");
            System.out.println("Enter N to exit, Enter Y to try again.");
            char answer = Input.readSelection();
            if (answer == 'N') return;
            pictureBytess = Picture.uploadPicture();
        }
        // Create a new picture message object
        PictureMessage pictureMessage = new PictureMessage();
        pictureMessage.setSender(sender);
        pictureMessage.setReceiver(receiver);
        // Set the message time to the current time
        String time = new Date().toString();
        pictureMessage.setMessageTime(time);
        pictureMessage.setImage(pictureBytess);
        System.out.println("At " + time + ",You send to " + receiver + " a picture.");
        // Add the picture message to sender's sent pictures and receiver's received pictures
        senderUser.addSendPicture(pictureMessage);
        receiverUser.addReceivePicture(pictureMessage);
    }

}

         */
    }
}
