import java.net.Socket;
/**
 * Interface for ServerConnectClientThread Class
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 15, 2024
 */

public interface ServerConnectClientInterface {
    Socket getSocket();
    public void setSocket(Socket socket);
    public String getUserName();
    public void setUserName(String userName);
    public void run();

}
