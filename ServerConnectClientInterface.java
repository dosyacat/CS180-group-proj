import java.net.Socket;

public interface ServerConnectClientInterface {

    /**
     * Gets the socket connected to the client.
     *
     * @return The socket connected to the client.
     */
    Socket getSocket();
    public void setSocket(Socket socket);
    public String getUserName();
    public void setUserName(String userName);
    public void run();

}
