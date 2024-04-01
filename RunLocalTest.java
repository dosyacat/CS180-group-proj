import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

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
    public void testCreateNewUser(){
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
    public void testAddUser() {
        User user = new User("ryangosling", "literallyMe", "Test@User.com", "test" );
        DataBase.add(user);
        assertEquals(user, DataBase.findUser("ryangosling"));
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
    public void testCheckMessageReceive() {
        UserService userService = new UserService();
        User user1 = new User();
        User user2 = new User();
        user1.setUsername("user1");
        user2.setUsername("user2");

        assertFalse(userService.checkMessageReceive(user1, user2));
        assertFalse(userService.checkMessageReceive(user1, user2));

        user1.getMessageDataBase().getReceiveMessageHashMap().put(user2.getUsername(), new ArrayList<>());
        assertTrue(userService.checkMessageReceive(user1, user2));
    }

    @Test
    public void testProfileInformation() {
        User user = new User("user", "password");
        user.setEmail("user@example.com");
        user.setBio("This is a test bio");
        String expected = "username : user\nemail : user@example.com\nbio : This is a test bio";
        assertEquals(expected, user.toString());
    }
}
