import java.util.ArrayList;
import java.util.HashMap;
/**
 * Interface for UserMessageDataBase.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */


public interface UserMessageDataBaseInterface {

    public void addSendMessage(Message message);

    public void addReceiveMessage(Message message);

    public void showSendMessages();

    public void showReceiveMessages();

    public void deleteMessage(String name, int messageNumber);

    public ArrayList<Message> getReceiveArrayList();

    public void setReceiveArrayList(ArrayList<Message> receiveArrayList);

    public ArrayList<Message> getSentArrayList();

    public void setSentArrayList(ArrayList<Message> sentArrayList);

    public HashMap<String, ArrayList<Message>> getSentMessageHashMap();

    public HashMap<String, ArrayList<Message>> getReceiveMessageHashMap();

    public void setReceiveMessageHashMap(HashMap<String, ArrayList<Message>> receiveMessageHashMap);

    public int getSentMessagesCount();

    public void setSentMessagesCount(int sentMessagesCount);

    public int getReceiveMessagesCount();

    public void setReceiveMessagesCount(int receiveMessagesCount);
}
