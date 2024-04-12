import java.io.*;
import java.util.HashMap;

public class Information {
    public static User readUser(String userName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("UserFile"));
        String line;
        User user = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.split(" ")[0].equals(userName)) {
                String[] userSplit = line.split(" ");
                boolean messagePrivacySetting = Boolean.parseBoolean(userSplit[4]);
                user = new User(userSplit[0], userSplit[1], userSplit[2], userSplit[3], messagePrivacySetting);
            }
        }
        return user;
    }

    public static void writeUser(User user) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserFile", true));
        bufferedWriter.append(user.getUsername() + " " + user.getPassword() +
                " " + user.getEmail() + " " + user.getBio() + " " + user.isMessagePrivacySettings());
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    public static HashMap<String, User> readUser() {
        HashMap<String, User> userHashMap1 = new HashMap();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("UserFile"));
            String line = null;
            User user = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userSplit = line.split(" ");
                boolean messagePrivacySetting = Boolean.parseBoolean(userSplit[4]);
                user = new User(userSplit[0], userSplit[1], userSplit[2], userSplit[3], messagePrivacySetting);
                userHashMap1.put(userSplit[0], user);
            }
            if (user == null) return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userHashMap1;
    }

    public static void readMessage() {

    }

    public static void writeMessage() {

    }

}





