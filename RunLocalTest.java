import org.junit.Assert;
import org.junit.Test;


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
            System.out.println("Client failed to run");
        }
    }

    @Test
    public void testCreateNewUser(){
        User user = new User(userName, password, email, bio);

        Assert.assertEquals("Username do not match", userName, user.getUsername());
        Assert.assertEquals("Emails should match!", email, user.getEmail());
        Assert.assertEquals("Passwords should match!", password, user.getPassword());

        Assert.assertEquals("Bios should match!", bio, user.getBio());
    }

    @Test
    public void



}
