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
    public void testProfileInformation() {
        User user = new User("user", "password");
        user.setEmail("user@example.com");
        user.setBio("This is a test bio");
        String expected = "username : user\nemail : user@example.com\nbio : This is a test bio";
        assertEquals(expected, user.toString());
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

    @Test
    public void testSendMessage() {

        String sender = "Alice";
        String receiver = "Bob";
        String content = "Hello, Bob!";
        Message message = MessageService.sendMessage(sender, receiver, content);
        assertNotNull("Message should not be null", message);
        assertEquals("Sender should match", sender, message.getSender());
        assertEquals("Receiver should match", receiver, message.getReceiver());
        assertEquals("Content should match", content, message.getContent());
    }

    @Test
    public void testSendMessageWithDifferentSenderAndReceiver() {

        String sender = "Alice";
        String receiver = "Bob";
        String content = "Hi, Bob!";
        Message message = MessageService.sendMessage(sender, receiver, content);
        assertNotNull("Message should not be null", message);
        assertNotEquals("Sender and receiver should not be the same", sender, message.getReceiver());
    }

    @Test
    public void testSendMessageWithEmptyContent() {

        String sender = "Brown";
        String receiver = "Peter";
        String content = "";
        Message message = MessageService.sendMessage(sender, receiver, content);
        assertNotNull("Message should not be null", message);
        assertEquals("Content should be empty", "", message.getContent());
    }
    @Test
    public void testGetSetSender() {
        Message message = new Message();
        String sender = "Eve";
        message.setSender(sender);
        assertEquals("Sender should match", sender, message.getSender());
    }

    @Test
    public void testGetSetReceiver() {
        Message message = new Message();
        String receiver = "Charlie";
        message.setReceiver(receiver);
        assertEquals("Receiver should match", receiver, message.getReceiver());
    }

    @Test
    public void testGetSetContent() {
        Message message = new Message();
        String content = "Hey, Charlie!";
        message.setContent(content);
        assertEquals("Content should match", content, message.getContent());
    }

    @Test
    public void testGetSetMessageTime() {
        Message message = new Message();
        String messageTime = "2024-04-15 12:00:00";
        message.setMessageTime(messageTime);
        assertEquals("Message time should match", messageTime, message.getMessageTime());
    }

    @Test
    public void testGetSetMessageType() {
        Message message = new Message();
        String messageType = Message.Message_LOGIN_CLIENT;
        message.setMessageType(messageType);
        assertEquals("Message type should match", messageType, message.getMessageType());
    }

    private Message message1;
    private Message message2;

    @Before
    public void setUp() {
        message1 = new Message("Eve", "Charlie", "Hey, Charlie! How are you?", "2024-04-15 12:00:00");
        message2 = new Message("Charlie", "Eve", "Hi, Eve! I'm good, thanks.", "2024-04-15 12:00:00");
    }

    @Test
    public void testDeleteMessage() {
        MessageDataBase.addMessage(message1);
        MessageDataBase.addMessage(message2);
        MessageDataBase.deleteMessage("Charlie", "Eve");
        ArrayList<Message> messagesForCharlie = MessageDataBase.checkMessage("Charlie");
        ArrayList<Message> messagesForEve = MessageDataBase.checkMessage("Eve");
        assertNotNull("Message list for Charlie should not be null", messagesForCharlie);
        assertNotNull("Message list for Eve should not be null", messagesForEve);
        assertEquals("Number of messages for Charlie should be 0 after deletion", 0, messagesForCharlie.size());
        assertEquals("Number of messages for Eve should be 1 after deletion", 1, messagesForEve.size());
        assertEquals("Message content for Eve should still match", "Hi, Eve! I'm good, thanks.", messagesForEve.get(0).getContent());
    }

    //user test

    @Test
    public void testCreateUserWithMinimumInfo() {
        User user = new User("username", "password");
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getBio());
    }

    @Test
    public void testCreateUserWithFullInfo() {
        User user = new User("username", "password", "user@example.com", "This is a bio", true);
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("user@example.com", user.getEmail());
        assertEquals("This is a bio", user.getBio());
        assertTrue(user.isMessagePrivacySettings());
    }

    @Test
    public void testSetAndGetProfilePicture() {
        User user = new User();
        byte[] picture = new byte[]{1, 2, 3, 4, 5}; // Example picture bytes
        user.setPicture(picture);
        assertArrayEquals(picture, user.getPicture());
    }

    @Test
    public void testAddAndFindFriends() {
        User user = new User();
        user.getFriendArrayList().add("friend1");
        assertTrue(user.findFriend("friend1"));
        assertFalse(user.findFriend("friend2"));
    }

    @Test
    public void testAcceptFriendRequest() {
        User user = new User();
        User friend = new User("friend1", "password");
        user.acceptFriendRequest(friend);
        assertTrue(user.findFriend("friend1"));
    }



    @Test
    public void testSetAndGetMessagePrivacySettings() {
        User user = new User();
        user.setMessagePrivacySettings(false);
        assertFalse(user.isMessagePrivacySettings());
    }









}
