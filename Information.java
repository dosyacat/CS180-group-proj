import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * This class provides methods to interact with user, friend, and message data stored in files.
 * It includes methods to read, write, and manipulate user and friend information.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 29, 2024
 */

public class Information implements InformationInterface {
    public static void clearFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, false);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Method to read user information from the UserFile
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
    // Method to write user information to the UserFile and friend information to the FriendFile
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
    // Method to read user's friend list and block list from the FriendFile
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
    // Method to write friend information to the FriendFile
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
    // Method to block or unblock a friend
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

    //Method to add messages
    public static synchronized void addMessage(Message message) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("MessageFile", true));
            String line = String.join("\t", message.getReceiver(), message.getSender(), message.getMessageTime());
            bufferedWriter.append(line);
            bufferedWriter.newLine();
            bufferedWriter.append(message.getContent());
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Method to remove messages
    public static synchronized void removeMessage(String receiver, String sender) {
        List<String> newLines = new ArrayList<>();
        boolean skip = false;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("MessageFile"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (skip) {
                    skip = false;
                    continue;
                }
                String[] parts = line.split("\\t", -1);
                if (parts[0].equals(receiver) && parts[1].equals(sender)) {
                    skip = true;
                    continue;
                }
                newLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        clearFile("MessageFile");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("MessageFile"))) {
            for (String newLine : newLines) {
                bufferedWriter.write(newLine);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static ConcurrentHashMap<String, ArrayList<Message>> readMessage() {
        ConcurrentHashMap<String, ArrayList<Message>> userHashMap1 = new ConcurrentHashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("MessageFile"));
            String line = null;
            Message message = null;
            while ((line = bufferedReader.readLine()) != null) {
                String contentLine = bufferedReader.readLine();
                if (contentLine == null) break;
                String[] parts = line.split("\\t", -1);
                if (parts.length >= 3) {
                    String receiver = parts[0];
                    String sender = parts[1];
                    String messageTime = parts[2];
                    message = new Message(sender, receiver, contentLine, messageTime);
                    userHashMap1.computeIfAbsent(message.getReceiver(), k -> new ArrayList<>()).add(message);
                }
            }
            if (message == null) return new ConcurrentHashMap<String, ArrayList<Message>>();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return userHashMap1;
    }
}
