import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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

    public static synchronized void writeUser(User user) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("UserFile", true));
            bufferedWriter.append(user.getUsername() + " " + user.getPassword() +
                    " " + user.getEmail() + " " + user.getBio() + " " + user.isMessagePrivacySettings());
            bufferedWriter.newLine();
            bufferedWriter.close();

            BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter("FriendFile", true));
            bufferedWriter1.append(user.getUsername() + ",,");
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

                    String[] friend = friends[1].split(" ");
                    ArrayList<String> friendArrayList = (friend.length == 1 && friend[0].isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(friend));

                    String[] blockFriend = friends[2].split(" ");
                    ArrayList<String> blockFriendArrayList = (blockFriend.length == 1 && blockFriend[0].isEmpty())
                            ? new ArrayList<>() : new ArrayList<>(Arrays.asList(blockFriend));

                    user.setFriendArrayList(friendArrayList);
                    user.setBlockArrayList(blockFriendArrayList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void writeFriend(String userName1, String userName2, boolean flag) {
        List<String> newLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("FriendFile"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts[0].equals(userName2)) {
                    String[] friends = parts[1].split(" ");
                    List<String> friendsList = new ArrayList<>(Arrays.asList(friends));

                    if (friendsList.size() == 1 && friendsList.getFirst().isEmpty()) {
                        friendsList.clear();
                    }

                    if (!friendsList.contains(userName1) && flag) {
                        friendsList.add(userName1);
                    }

                    if (friendsList.contains(userName1) && !flag) {
                        friendsList.remove(userName1);
                    }

                    parts[1] = String.join(" ", friendsList);
                    line = String.join(",", parts);
                }

                if (parts[0].equals(userName1)) {
                    String[] friends = parts[1].split(" ");
                    List<String> friendsList = new ArrayList<>(Arrays.asList(friends));

                    if (friendsList.size() == 1 && friendsList.getFirst().isEmpty()) {
                        friendsList.clear();
                    }
                    if (!friendsList.contains(userName2) && flag) {
                        friendsList.add(userName2);
                    }

                    if (friendsList.contains(userName2) && !flag) {
                        friendsList.remove(userName2);
                    }

                    parts[1] = String.join(" ", friendsList);
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

    public static synchronized void blockFriend(String userName1, String userName2, boolean flag) {
        List<String> newLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("FriendFile"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts[0].equals(userName1)) {

                    String[] blockFriends = parts[2].split(" ");
                    List<String> blockFriendsList = new ArrayList<>(Arrays.asList(blockFriends));

                    if (blockFriendsList.size() == 1 && blockFriendsList.getFirst().isEmpty()) {
                        blockFriendsList.clear();
                    }

                    if (!blockFriendsList.contains(userName2) && flag) {
                        blockFriendsList.add(userName2);
                    }

                    if (blockFriendsList.contains(userName2) && !flag) {
                        blockFriendsList.remove(userName2);
                    }

                    parts[2] = String.join(" ", blockFriendsList);
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

    public static ConcurrentHashMap<String, User> readUser() {
        ConcurrentHashMap<String, User> userHashMap1 = new ConcurrentHashMap<>();
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
            if (user == null) return new ConcurrentHashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userHashMap1;
    }


    public static synchronized void writeUser(ConcurrentHashMap<String, User> userHashMap) {
        clearFile("UserFile");
        clearFile("FriendFile");
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





