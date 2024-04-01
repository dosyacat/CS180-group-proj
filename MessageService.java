import java.util.Date;

public class MessageService {

    public static void sendMessage(String sender, String receiver, String content) {

        User senderUser = DataBase.findUser(sender);

        User receiverUser = DataBase.findUser(receiver);

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        String time = new Date().toString();
        message.setMessageTime(time);
        System.out.println("At " + time + ",You send to " + receiver + " a message.");
        senderUser.addSendMessage(message);
        receiverUser.addReceiveMessage(message);
    }

    public static void sendPictureMessage(String sender, String receiver) {
        User senderUser = DataBase.findUser(sender);
        User receiverUser = DataBase.findUser(receiver);

        System.out.println("Image upload manager has opened, Please check all programs in your computer.");
        byte[] pictureBytess = Picture.uploadPicture();
        while (pictureBytess == null) {
            System.out.println("Invalid picture");
            System.out.println("Enter N to exit, Enter Y to try again.");
            char answer = Input.readSelection();
            if (answer == 'N') return;
            pictureBytess = Picture.uploadPicture();
        }
        PictureMessage pictureMessage = new PictureMessage();
        pictureMessage.setSender(sender);
        pictureMessage.setReceiver(receiver);
        String time = new Date().toString();
        pictureMessage.setMessageTime(time);
        pictureMessage.setImage(pictureBytess);
        System.out.println("At " + time + ",You send to " + receiver + " a picture.");
        senderUser.addSendPicture(pictureMessage);
        receiverUser.addReceivePicture(pictureMessage);
    }

}