import java.util.ArrayList;
import java.util.HashMap;
/**
 * The MessageDataBase class manages the storage and retrieval of messages.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public class MessageDataBase implements MessageDataBaseInterface {
    // ArrayList to store all messages
    private ArrayList<Message> arrayList = new ArrayList<>();
    // HashMap to store messages grouped by receiver
    private HashMap<String, ArrayList<Message>> messageHashMap = new HashMap<>();
    //Adds a message to the database.
    public void addMessage(Message message) {
        // Add the message to the ArrayList
        arrayList.add(message);
        messageHashMap.put(message.getReceiver(), arrayList);
    }

    //Gets the ArrayList containing all messages.

    public ArrayList<Message> getArrayList() {
        return arrayList;
    }
    //Sets the ArrayList containing all messages.
    public void setArrayList(ArrayList<Message> sendArrayList) {
        this.arrayList = sendArrayList;
    }
    //Gets the HashMap containing messages grouped by receiver.
    public HashMap<String, ArrayList<Message>> getMessageHashMap() {
        return messageHashMap;
    }
    //Sets the HashMap containing messages grouped by receiver.
    public void setMessageHashMap(HashMap<String, ArrayList<Message>> messageHashMap) {
        this.messageHashMap = messageHashMap;
    }
}
