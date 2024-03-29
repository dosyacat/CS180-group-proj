import java.util.ArrayList;
import java.util.HashMap;

public class MessageDataBase {

    private ArrayList<Message> ArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<Message>> messageHashMap = new HashMap<>();

    public void addMessage(Message message) {
        ArrayList.add(message);
        messageHashMap.put(message.getReceiver(), ArrayList);
    }


    public ArrayList<Message> getArrayList() {
        return ArrayList;
    }

    public void setArrayList(ArrayList<Message> sendArrayList) {
        this.ArrayList = sendArrayList;
    }

    public HashMap<String, ArrayList<Message>> getMessageHashMap() {
        return messageHashMap;
    }

    public void setMessageHashMap(HashMap<String, ArrayList<Message>> messageHashMap) {
        this.messageHashMap = messageHashMap;
    }
}
