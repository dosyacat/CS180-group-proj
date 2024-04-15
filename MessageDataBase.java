import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The MessageDataBase class manages the storage and retrieval of messages.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public class MessageDataBase {
    // ArrayList to store all messages
    private static ArrayList<Message> arrayList = new ArrayList<>();
    // HashMap to store messages grouped by receiver
    private static ConcurrentHashMap<String, ArrayList<Message>> messageHashMap = Information.readMessage();
    //Adds a message to the database.

    public ArrayList<Message> getArrayList() {
        return arrayList;
    }
    //Sets the ArrayList containing all messages.
    public void setArrayList(ArrayList<Message> sendArrayList) {
        this.arrayList = sendArrayList;
    }
    //Gets the HashMap containing messages grouped by receiver.
    public ConcurrentHashMap<String, ArrayList<Message>> getMessageHashMap() {
        return messageHashMap;
    }
    //Sets the HashMap containing messages grouped by receiver.

    public void setMessageHashMap(ConcurrentHashMap<String, ArrayList<Message>> messageHashMap) {
        this.messageHashMap = messageHashMap;
    }

    public static void addMessage(Message message) {
        messageHashMap.computeIfAbsent(message.getReceiver(), k -> new ArrayList<>()).add(message);
        Information.addMessage(message);
    }
    public static ArrayList<Message> checkMessage(String userName) {
        return messageHashMap.get(userName);
    }

}
