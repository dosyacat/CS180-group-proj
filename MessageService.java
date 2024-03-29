import java.util.Date;

public class MessageService {

    public static boolean sendMessage(String sender, String receiver, String content) {

        User senderUser = DataBase.findUser(sender);

        User receiverUser = DataBase.findUser(receiver);
        if (receiverUser == null) {
            return false;
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        String time = new Date().toString();
        message.setMessageTime(time);
        System.out.println("At " + time + ",You send to " + receiver + " : " + content);
        senderUser.addSendMessage(message);
        receiverUser.addReceiveMessage(message);
        return true;
    }

}