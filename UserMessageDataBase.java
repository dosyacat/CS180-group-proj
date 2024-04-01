import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * UserMessageDataBase Class - Stores the information about all the messages sent, received and deleted
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public class UserMessageDataBase {

    private int sentMessagesCount = 0;
    private ArrayList<Message> allSentMessages = new ArrayList<>();
    private ArrayList<Message> sentArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<Message>> sentMessageHashMap = new HashMap<>();

    private int receiveMessagesCount = 0;
    private ArrayList<Message> allReceiveMessages = new ArrayList<>();
    private ArrayList<Message> receiveArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<Message>> receiveMessageHashMap = new HashMap<>();

    private ArrayList<PictureMessage> sentPictureArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<PictureMessage>> sentPictureHashMap = new HashMap<>();

    private ArrayList<PictureMessage> receivePictureArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<PictureMessage>> receivePictureHashMap = new HashMap<>();

    public void addReceivePicture(PictureMessage pictureMessage) {
        receiveMessagesCount++;
        receivePictureArrayList = receivePictureHashMap.get(pictureMessage.getReceiver());
        if (receivePictureArrayList == null) receivePictureArrayList = new ArrayList<>();
        receivePictureArrayList.add(pictureMessage);
        receivePictureHashMap.put(pictureMessage.getSender(), receivePictureArrayList);
    }

    public void addSendPicture(PictureMessage pictureMessage) {
        sentMessagesCount++;
        sentPictureArrayList = sentPictureHashMap.get(pictureMessage.getReceiver());
        if (sentPictureArrayList == null) sentPictureArrayList = new ArrayList<>();
        sentPictureArrayList.add(pictureMessage);
        sentPictureHashMap.put(pictureMessage.getReceiver(), sentPictureArrayList);
    }

    public void showReceivePictures() {
        if (receivePictureHashMap.isEmpty()) {
            System.out.println("You haven't received any pictures!");
            return;
        }
        int i = 1;
        int j = 1;
        System.out.println("===================Received Messages================");
        for (Map.Entry<String, ArrayList<PictureMessage>> entry : receivePictureHashMap.entrySet()) {
            System.out.println(i + " " + entry.getKey());
            for (PictureMessage pictureMessage : entry.getValue()) {
                System.out.println(j + pictureMessage.toString());
                j++;
            }
            i++;
            j = 1;
        }
    }




    public void addSendMessage(Message message) {
        sentMessagesCount++;
        allReceiveMessages.add(message);
        sentArrayList = sentMessageHashMap.get(message.getReceiver());
        if (sentArrayList == null) sentArrayList = new ArrayList<>();
        sentArrayList.add(message);
        sentMessageHashMap.put(message.getReceiver(), sentArrayList);
    }

    public void addReceiveMessage(Message message) {
        receiveMessagesCount++;
        allReceiveMessages.add(message);
        receiveArrayList = receiveMessageHashMap.get(message.getSender());
        if (receiveArrayList == null) receiveArrayList = new ArrayList<>();
        receiveArrayList.add(message);
        receiveMessageHashMap.put(message.getSender(), receiveArrayList);
    }

    public void showSendMessages() {
        if (sentMessageHashMap.isEmpty()) {
            System.out.println("You haven't sent any text messages!");
            return;
        }

        int i = 1;
        int j = 1;
        System.out.println("===================Sent Messages================");
        for (Map.Entry<String, ArrayList<Message>> entry : sentMessageHashMap.entrySet()) {
            System.out.println(i + " " + entry.getKey());
            for (Message message : entry.getValue()) {
                System.out.println(j + message.toString());
                j++;
            }
            i++;
            j = 1;
        }
    }

    public void showReceiveMessages() {
        if (receiveMessageHashMap.isEmpty()) {
            System.out.println("You haven't received any text messages!");
            return;
        }
        int i = 1;
        int j = 1;
        System.out.println("===================Received Messages================");
        for (Map.Entry<String, ArrayList<Message>> entry : receiveMessageHashMap.entrySet()) {
            System.out.println(i + " " + entry.getKey());
            for (Message message : entry.getValue()) {
                System.out.println(j + message.toString());
                j++;
            }
            i++;
            j = 1;
        }
    }

    public void deleteMessage(String name, int messageNumber) {
        Message message = receiveMessageHashMap.get(name).get(messageNumber - 1);

        String sender = message.getSender();
        receiveArrayList = receiveMessageHashMap.get(sender);
        receiveArrayList.remove(message);
        receiveMessagesCount--;


        if (receiveArrayList.isEmpty()) {
            receiveMessageHashMap.remove(sender);
        } else {
            receiveMessageHashMap.put(sender, receiveArrayList);
        }
        System.out.println("You have deleted the message!");
    }

    public ArrayList<Message> getReceiveArrayList() {
        return receiveArrayList;
    }

    public void setReceiveArrayList(ArrayList<Message> receiveArrayList) {
        this.receiveArrayList = receiveArrayList;
    }

    public ArrayList<Message> getSentArrayList() {
        return sentArrayList;
    }

    public void setSentArrayList(ArrayList<Message> sentArrayList) {
        this.sentArrayList = sentArrayList;
    }

    public HashMap<String, ArrayList<Message>> getSentMessageHashMap() {
        return sentMessageHashMap;
    }

    public void setSentMessageHashMap(HashMap<String, ArrayList<Message>> sentMessageHashMap) {
        this.sentMessageHashMap = sentMessageHashMap;
    }

    public HashMap<String, ArrayList<Message>> getReceiveMessageHashMap() {
        return receiveMessageHashMap;
    }

    public void setReceiveMessageHashMap(HashMap<String, ArrayList<Message>> receiveMessageHashMap) {
        this.receiveMessageHashMap = receiveMessageHashMap;
    }

    public int getSentMessagesCount() {
        return sentMessagesCount;
    }

    public void setSentMessagesCount(int sentMessagesCount) {
        this.sentMessagesCount = sentMessagesCount;
    }

    public int getReceiveMessagesCount() {
        return receiveMessagesCount;
    }

    public void setReceiveMessagesCount(int receiveMessagesCount) {
        this.receiveMessagesCount = receiveMessagesCount;
    }
}
