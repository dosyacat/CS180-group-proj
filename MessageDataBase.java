import java.util.ArrayList;
import java.util.HashMap;
/**
 * The MessageDataBase class manages the storage and retrieval of messages.
 */

public class MessageDataBase {
    // ArrayList to store all messages
    private ArrayList<Message> ArrayList = new ArrayList<>();
    // HashMap to store messages grouped by receiver
    private HashMap<String, ArrayList<Message>> messageHashMap = new HashMap<>();
    //Adds a message to the database.
    public void addMessage(Message message) {
        // Add the message to the ArrayList
        ArrayList.add(message);
        messageHashMap.put(message.getReceiver(), ArrayList);
    }

    //Gets the ArrayList containing all messages.

    public ArrayList<Message> getArrayList() {
        return ArrayList;
    }
    //Sets the ArrayList containing all messages.
    public void setArrayList(ArrayList<Message> sendArrayList) {
        this.ArrayList = sendArrayList;
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
