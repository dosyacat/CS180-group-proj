import java.io.*;
import java.util.*;

public class Information {
    public static void clearFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User readUser(String userName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("UserFile"));
            String line;
            User user = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(" ")[0].equals(userName)) {
                    String[] userSplit = line.split(" ");
                    boolean messagePrivacySetting = Boolean.parseBoolean(userSplit[4]);
                    user = new User(userSplit[0], userSplit[1], userSplit[2], userSplit[3], messagePrivacySetting);
                    readUserFriendDataBase(user);
                }
            }
            bufferedReader.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeUser(User user) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserFile", true));
            bufferedWriter.append(user.getUsername() + " " + user.getPassword() +
                    " " + user.getEmail() + " " + user.getBio() + " " + user.isMessagePrivacySettings());
            bufferedWriter.newLine();
            bufferedWriter.close();

            BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("FriendFile", true));
            bufferedWriter1.append(user.getUsername() + ",,,");
            bufferedWriter1.newLine();
            bufferedWriter1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readUserFriendDataBase(User user) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("FriendFile"))) {
            String line = null;
            String userName = user.getUsername();
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(userName)) {
                    String[] friends = line.split(",", -1);

                    String[] requestFriend = friends[1].split(" ");
                    ArrayList<String> requestFriendArrayList = (requestFriend.length == 1 && requestFriend[0].isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(requestFriend));

                    String[] friend = friends[2].split(" ");
                    ArrayList<String> friendArrayList = (friend.length == 1 && friend[0].isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(friend));

                    String[] blockFriend = friends[3].split(" ");
                    ArrayList<String> blockFriendArrayList = (blockFriend.length == 1 && blockFriend[0].isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(blockFriend));

                    user.setRequestFriendArrayList(requestFriendArrayList);
                    user.setFriendArrayList(requestFriendArrayList);
                    user.setBlockedFriendArrayList(requestFriendArrayList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFriend(String userName1, String userName2) {
        List<String> newLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("FriendFile"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts[0].equals(userName2)) {
                    String[] requestFriends = parts[1].split(" ");
                    List<String> requestFriendsList = new ArrayList<>(Arrays.asList(requestFriends));

                    if (requestFriendsList.size() == 1 && requestFriendsList.getFirst().isEmpty()) {
                        requestFriendsList.clear();
                    }
                    if (!requestFriendsList.contains(userName1)) {
                        requestFriendsList.add(userName1);
                    }

                    parts[1] = String.join(" ", requestFriendsList);
                    line = String.join(",", parts);
                }
                newLines.add(line);
            }
        } catch (Exception e) {
                e.printStackTrace();
        }

        clearFile("FriendFile");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("FriendFile"))) {
                for (String newLine : newLines) {
                    bufferedWriter.write(newLine);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
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
                readUserFriendDataBase(user);
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





