import java.util.ArrayList;
import java.util.HashMap;
 /**
 * Interface for MessageData.java
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */

public interface MessageDataBaseInterface {

    public void addMessage(Message message);

    public ArrayList<Message> getArrayList();

    public void setArrayList(ArrayList<Message> sendArrayList);

    public HashMap<String, ArrayList<Message>> getMessageHashMap();

    public void setMessageHashMap(HashMap<String, ArrayList<Message>> messageHashMap);
}
