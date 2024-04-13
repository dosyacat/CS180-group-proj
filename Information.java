import java.io.*;
import java.util.HashMap;

public class Information {
    public static void clearFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


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
        bufferedReader.close();
        return user;
    }
    
    public static void writeUser(User user) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserFile", true));
            bufferedWriter.append(user.getUsername() + " " + user.getPassword() +
                    " " + user.getEmail() + " " + user.getBio() + " " + user.isMessagePrivacySettings());
            bufferedWriter.newLine();
            bufferedWriter.close();

            BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("FriendsFile", true));
            bufferedWriter1.append(user.getUsername() + ",");
            bufferedWriter1.newLine();
            bufferedWriter1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            if (user == null) return new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userHashMap1;
    }


    public static void writeUser(HashMap<String, User> userHashMap) {
        clearFile("UserFile");
        for (User user : userHashMap.values()) {
            writeUser(user);
        }
    }

    public static void addFriends() {

    }

    public static void readMessage() {

    }

    public static void writeMessage() {

    }

}





