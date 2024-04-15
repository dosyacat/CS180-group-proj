import java.net.Socket;
/**
 * Interface for ClientConnectServerThread Class
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 15, 2024
 */

public interface ClientConnectServerInterface {

    //Retrieves the socket associated with this client connection.

    Socket getSocket();

    //Runs the client-server communication thread.
    void run();
}
