import java.util.ArrayList;
import java.util.HashMap;

public class MessageDataBase {

    private ArrayList<Message> sendArrayList = new ArrayList<>();
    private ArrayList<Message> receiveArrayList = new ArrayList<>();
    private HashMap<String, ArrayList<Message>> messageHashMap = new HashMap<>();


    public void addSendMessage(Message message) {
        sendArrayList.add(message);
        messageHashMap.put(message.getReceiver(), sendArrayList);
    }

    public void addReceiveMessage(Message message) {
        receiveArrayList.add(message);
        messageHashMap.put(message.getSender(), receiveArrayList);
    }

    public ArrayList<Message> getSendArrayList() {
        return sendArrayList;
    }

    public void setSendArrayList(ArrayList<Message> sendArrayList) {
        this.sendArrayList = sendArrayList;
    }

    public ArrayList<Message> getReceiveArrayList() {
        return receiveArrayList;
    }

    public void setReceiveArrayList(ArrayList<Message> receiveArrayList) {
        this.receiveArrayList = receiveArrayList;
    }

    public HashMap<String, ArrayList<Message>> getMessageHashMap() {
        return messageHashMap;
    }

    public void setMessageHashMap(HashMap<String, ArrayList<Message>> messageHashMap) {
        this.messageHashMap = messageHashMap;
    }
}
