
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


import static org.junit.Assert.*;


public class RunLocalTest {
    private String userName = "Braden_Edey";
    private String password = "purdue123";
    private String email = "Bedey@purdue.edu";
    private String bio = "I am a pro basketball player";


    @Test
    public void testClientRun() {
        System.out.println("Testing if the client runs...");

        try {
            Client client = new Client();
            System.out.println("Client runs!");
        } catch (Exception e) {
            throw new AssertionError("Client failed to run!");
        }
    }

    @Test
    public void testCreateNewUser() {
        User user = new User(userName, password, email, bio);

        assertEquals("Username do not match", userName, user.getUsername());
        assertEquals("Emails should match!", email, user.getEmail());
        assertEquals("Passwords should match!", password, user.getPassword());

        assertEquals("Bios should match!", bio, user.getBio());
    }

    @Test
    public void testSetAndGetSender() {
        Message message = new Message();
        message.setSender("Malachi");
        assertEquals("Malachi", message.getSender());

    }

    @Test
    public void testToString() {
        Message message = new Message();
        message.setSender("Emma");
        message.setReceiver("Ryan");
        message.setContent("Hello, Ryan!");
        message.setMessageTime("2024-04-01 10:00:00");
        String expected = "\nMessage : " +
                "\nsender : Emma, receiver :Ryan" +
                "\ncontent : Hello, Ryan!" +
                "\nmessageTime : 2024-04-01 10:00:00";
        assertEquals(expected, message.toString());
    }


    @Test
    public void testAddMessage() {
        MessageDataBase messageDataBase = new MessageDataBase();
        Message message = new Message();
        message.setSender("Alice");
        message.setReceiver("Dosya");
        message.setContent("Hello, bro!");
        message.setMessageTime("2024-04-01 10:00:00");

        messageDataBase.addMessage(message);

        ArrayList<Message> messages = messageDataBase.getArrayList();
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0));

        HashMap<String, ArrayList<Message>> messageHashMap = messageDataBase.getMessageHashMap();
        assertTrue(messageHashMap.containsKey("Dosya"));
        assertEquals(1, messageHashMap.get("Dosya").size());
        assertEquals(message, messageHashMap.get("Dosya").get(0));
    }


    @Test
    public void testBlockFriend() {
        UserFriendDataBase userFriendDataBase = new UserFriendDataBase();
        User user = new User("BladeRunner", "password", "Test@User.com", "Test");

        userFriendDataBase.blockFriend(user);
        assertTrue(userFriendDataBase.getBlockedFriendHashMap().containsKey("BladeRunner"));
    }

    @Test
    public void testUnblockFriend() {
        UserFriendDataBase userFriendDataBase = new UserFriendDataBase();
        User user = new User("Kowalski", "password", "Test@User.com", "Test");
        userFriendDataBase.blockFriend(user);

        userFriendDataBase.unBlockFriend(user);
        assertFalse(userFriendDataBase.getBlockedFriendHashMap().containsKey("Kowalski"));
    }


    @Test
    public void testProfileInformation() {
        User user = new User("user", "password");
        user.setEmail("user@example.com");
        user.setBio("This is a test bio");
        String expected = "username : user\nemail : user@example.com\nbio : This is a test bio";
        assertEquals(expected, user.toString());
    }


    private UserFriendDataBase userFriendDataBase;

    @Before
    public void setUp() {
        userFriendDataBase = new UserFriendDataBase();
    }

    @Test
    public void testFindFriend() {
        User user = new User("testUser", "Test User");
        userFriendDataBase.getFriendHashMap().put(user.getUsername(), user);
        assertEquals(user, userFriendDataBase.findFriend("testUser"));
    }

    @Test
    public void testAcceptFriendRequest() {
        User user = new User("testUser", "Test User");
        userFriendDataBase.getRequestFriendHashMap().put(user.getUsername(), user);
        userFriendDataBase.acceptFriendRequest(user);
        assertTrue(userFriendDataBase.getFriendHashMap().containsKey("testUser"));
        assertFalse(userFriendDataBase.getRequestFriendHashMap().containsKey("testUser"));
    }

    @Test
    public void testDeclineFriendRequest() {
        User user = new User("testUser", "Test User");
        userFriendDataBase.getRequestFriendHashMap().put(user.getUsername(), user);
        userFriendDataBase.declineFriendRequest(user);
        assertFalse(userFriendDataBase.getRequestFriendHashMap().containsKey("testUser"));
    }


    @Test
    public void testRemoveFriend() {
        User user = new User("testUser", "Test User");
        userFriendDataBase.getFriendHashMap().put(user.getUsername(), user);
        assertTrue(userFriendDataBase.removeFriend(user));
        assertFalse(userFriendDataBase.removeFriend(user));
    }


    @Test
    public void testUnBlockFriend() {
        User user = new User("testUser", "Test User");
        userFriendDataBase.getBlockedFriendHashMap().put(user.getUsername(), user);
        userFriendDataBase.unBlockFriend(user);
        assertFalse(userFriendDataBase.getBlockedFriendHashMap().containsKey("testUser"));
    }


    //server test cases

    @Test
    public void testServerStartup() {

        Thread serverThread = new Thread(() -> {
            new Server();
        });
        serverThread.start();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Thread was interrupted: " + e.getMessage());
        }


        try (Socket socket = new Socket("localhost", 9999)) {
            assertTrue(socket.isConnected());
        } catch (IOException e) {
            fail("Failed to connect to the server: " + e.getMessage());
        }

        serverThread.interrupt();
    }

    @Test
    public void testServerConnectClientThread() {
        // Start the server in a separate thread
        Thread serverThread = new Thread(() -> {
            new Server();
        });
        serverThread.start();

        // starting serv
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            fail("Thread was interrupted: " + e.getMessage());
        }

        // connecting a client
        try (Socket socket = new Socket("localhost", 9999)) {
            assertTrue(socket.isConnected());

            ServerConnectClientThread clientThread = new ServerConnectClientThread(socket, "testUser");
            clientThread.start();

            // wait for a short period to ensure the client thread runs
            Thread.sleep(1000);

            // check if the client thread is alive
            assertTrue(clientThread.isAlive());
        } catch (IOException | InterruptedException e) {
            fail("Exception occurred: " + e.getMessage());
        }

        // stopping server thread
        serverThread.interrupt();
    }


    @Test
    public void testWriteUser() {
        // assuming user creation and writing works correctly
        User user = new User("newuser", "password", "newuser@example.com", "This is a bio", true);
        Information.writeUser(user);
        User readUser = Information.readUser("newuser");
        assertNotNull(readUser);
        assertEquals("newuser", readUser.getUsername());
    }


    @Test
    public void testMessage_USERVIEW_CLIENT() {
        //there's a user named "testuser" in the database
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_USERVIEW_CLIENT);
            oos.writeObject(message);
            oos.flush();

            Message response = (Message) ois.readObject();
            assertEquals(Message.Message_USERVIEW_SERVER, response.getMessageType());

            ConcurrentHashMap<String, User> userHashMap = (ConcurrentHashMap<String, User>) ois.readObject();
            assertNotNull(userHashMap.get("testuser"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testMessage_USESEARCH_CLIENT() {

        Socket socket = null;
        try {
            socket = new Socket("localhost", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = new Message();
            message.setMessageType(Message.Message_USESEARCH_CLIENT);
            message.setContent("testuser");
            oos.writeObject(message);
            oos.flush();

            Message response = (Message) ois.readObject();
            assertEquals(Message.Message_USESEARCH_SERVER, response.getMessageType());

            User user = (User) ois.readObject();
            assertNotNull(user);
            assertEquals("testuser", user.getUsername());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
