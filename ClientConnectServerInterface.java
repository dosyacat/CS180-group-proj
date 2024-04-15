import java.net.Socket;

/**
 *
 */

public interface ClientConnectServerInterface {

    //Retrieves the socket associated with this client connection.

    Socket getSocket();

    //Runs the client-server communication thread.
    void run();
}
