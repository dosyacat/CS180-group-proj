import java.sql.SQLOutput;

@SuppressWarnings("all")
public class Client {

    private String key = "";
    private UserService userService = new UserService();

    public static void main(String[] args) {
        new Client().mainMenu();
    }

    public void mainMenu() {
        while (true) {
            System.out.println("=========Welcome Login========");
            System.out.println("\t\t 1 Sign In");
            System.out.println("\t\t 2 Sign Up");
            System.out.println("\t\t 9 Exit");
            System.out.print("Please enter your choice:");

            key = Input.readString(1);
            switch (key) {
                case "1":
                    userSignin();
                    break;

                case "2":
                    userSignUp();
                    break;

                case "9":
                    return;

                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }

    private void userSignUp() {
        System.out.println("Please enter your Username, which will be limited to 20 digits");
        String username = Input.readString(20, false);
        while (DataBase.findUser(username) != null) {
            System.out.println("This username is already taken! Enter again!");
            username = Input.readString(20, false);
        }

        System.out.println("Please enter your password, which will be limited to 20 digits");
        String password = Input.readString(20, false);
        System.out.println("Please enter your email");
        String email = Input.readEmail(30, false);
        System.out.println("Please enter your bio, which will be limited to 100 words");
        String bio = Input.readString(100);

        User user = new User(username, password, email, bio);
        DataBase.add(user);
        System.out.println("You have signed up successfully! And you can upload a picture as your profile at Setting!");
    }

    private void userSignin() {
        System.out.print("Please enter your account:");
        String account = Input.readString();
        System.out.print("Please enter your password:");
        String password = Input.readString();
        if (userService.checkSecurity(account, password)) {
            userMenu(account);
        } else {
            System.out.println("The account or password is not correct!");
        }
    }

    private void userMenu(String account) {
        System.out.println("==================== Welcome " + account + " !   =====================");

        while (true) {
            User user = DataBase.findUser(account);
            System.out.println("\t\t\t\t\tUser Menu (" + account + ")");
            System.out.println("\t\t\t\t 1 User viewer");
            System.out.println("\t\t\t\t 2 Messages Menu");
            System.out.println("\t\t\t\t 3 User search");
            System.out.println("\t\t\t\t 4 Friends Munu");
            System.out.println("\t\t\t\t 5 Setting");
            System.out.println("\t\t\t\t 8 Exit to Login interface");
            System.out.println("\t\t\t\t 9 Exit");

            key = Input.readString();
            switch (key) {
                case "1":
                    DataBase.userInformation();
                    break;

                case "2":
                    messagesMenu(user);
                    break;

                case "3":
                    userSearch();
                    break;

                case "4":
                    friendMenu(user);
                    break;

                case "5":
                    settingMenu(user);
                    break;

                case "8":
                    return;

                case "9":
                    System.out.println("Exit!");
                    return;

                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }


    private void messagesMenu(User user) {
        boolean flag = true;
        while (flag) {
            System.out.println("\t\t\t\t\tWelcome to your Messages Menu!");
            System.out.println("\t\t\t\t1 Check Messages Sent");
            System.out.println("\t\t\t\t2 Check Messages Received");
            System.out.println("\t\t\t\t3 Sent Messages");
            System.out.println("\t\t\t\t4 Delete Messages Sent");
            System.out.println("\t\t\t\t5 Send Picture Message");
            System.out.println("\t\t\t\t6 Check Picture Messages Received");
            System.out.println("\t\t\t\t9 Exit");
            String operation = Input.readString(1, false);
            switch (operation) {
                case "1" :
                    checkSendMessages(user);
                    break;
                case "2" :
                    checkReceiveMessages(user);
                    break;
                case "3" :
                    sendMessages(user);
                    break;
                case "4" :
                    deleteMessages(user);
                    break;
                case "5" :
                    sendPictureMessages(user);
                    break;
                case "6" :
                    checkReceivePictures(user);
                    break;
                case "9" :
                    System.out.println("Exit!");
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }

    }

    private void checkSendMessages(User user) {
        user.getMessageDataBase().showSendMessages();
    }

    private void sendPictureMessages(User user) {
        while (true) {
            System.out.println("Send private message");
            System.out.println("Who do you want to send?");
            String receiver = Input.readString(20);

            if (DataBase.findUser(receiver) == null) {
                System.out.print("Sending messages failed!");
                System.out.println("Receiver not found");
                System.out.println("Enter Y to exit, Enter N to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') break;
                continue;
            }

            if (!userService.chechMessagePrivacySetting(DataBase.findUser(receiver))) {
                System.out.print("Sending messages failed!");
                System.out.println("The other person has their settings " +
                        "adjusted to only receive messages from friends!");
                System.out.println("Enter Y to exit, Enter N to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') break;
                continue;
            }

            if (userService.checkBlocked(user, DataBase.findUser(receiver))) {
                System.out.println("Sending messages failed! You can't message someone who has blocked you " +
                        "or someone you have blocked.");
                System.out.println("Enter Y to exit, Enter N to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') break;
                continue;
            }

            if (receiver.equals(user.getUsername()))
                System.out.println("You are sending messages to yourself!");
            MessageService.sendPictureMessage(user.getUsername(), receiver);
            break;

        }
    }

    private void checkReceivePictures(User user) {
        user.getMessageDataBase().showReceivePictures();
    }

    private void checkReceiveMessages(User user) {
        user.getMessageDataBase().showReceiveMessages();
    }

    private void sendMessages(User user) {
        while (true) {
            System.out.println("Send private message");
            System.out.println("Who do you want to send?");
            String receiver = Input.readString(20);

            if (DataBase.findUser(receiver) == null) {
                System.out.print("Sending messages failed!");
                System.out.println("Receiver not found");
                System.out.println("Enter Y to exit, Enter N to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') break;
                continue;
            }

            if (!userService.chechMessagePrivacySetting(DataBase.findUser(receiver))) {
                System.out.print("Sending messages failed!");
                System.out.println("The other person has their settings " +
                        "adjusted to only receive messages from friends!");
                System.out.println("Enter Y to exit, Enter N to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') break;
                continue;
            }

            if (userService.checkBlocked(user, DataBase.findUser(receiver))) {
                System.out.println("Sending messages failed! You can't message someone who has blocked you " +
                        "or someone you have blocked.");
                System.out.println("Enter Y to exit, Enter N to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') break;
                continue;
            }

            if (receiver.equals(user.getUsername()))
                System.out.println("You are sending messages to yourself!");
            System.out.println("What do you want to send?");
            String content = Input.readString(100000);
            MessageService.sendMessage(user.getUsername(), receiver, content);
            break;
        }
    }

    private void deleteMessages(User user) {
        user.getMessageDataBase().showReceiveMessages();
        if (user.getMessageDataBase().getReceiveMessageHashMap().isEmpty())
            return;
        String name = null;
        while (true) {
            System.out.println("Whose message do you want to delete?");
            name = Input.readString(15, false);

            if (DataBase.findUser(name) == null) {
                System.out.println("Delete messages failed! The user didn't exist!" );
                System.out.println("Enter Y/y to exit, Enter N/n to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') return;
                continue;
            }

            if (!userService.checkMessageReceive(user, DataBase.findUser(name))) {
                System.out.println("Delete messages failed! The user didn't send you messages yet");
                System.out.println("Enter Y/y to exit, Enter N/n to try it again.");
                char answer = Input.readSelection();
                if (answer == 'Y') return;
            }
            else break;
        }
        System.out.println("Which one do you want to delete?");
        int count = userService.checkMessageCount(user, DataBase.findUser(name));
        int answer = Input.readRange(1, count);
        user.deleteMessage(name, answer);
    }

    private void userSearch() {
        while (true) {
            System.out.println("Who are you searching for?");
            String username = Input.readString(20, false);
            User user1 = DataBase.findUser(username);
            if (user1 == null)
                System.out.println("The user you are looking for does not exist.");
            else {
                System.out.println(user1.toString());
                System.out.println("Do you want to view the user profile picture?");
                System.out.println("Enter N to exit, Enter Y to view");
                char answer = Input.readSelection();
                if (answer == 'N') return;
                else user1.showProfilePicture();
            }
            System.out.println("Enter N to Exit, Enter Y to Continue.");
            char answer1 = Input.readSelection();
            if (answer1 == 'N') return;
        }
    }

    private void friendMenu(User user) {
        System.out.println("Welcome to your Friends Menu!");
        boolean flag = true;
        while (flag) {
            System.out.println("\t\t\t\t\tFriends Menu (" + user.getUsername() + ")");
            System.out.println("\t\t\t\t 1 Show Friends List");
            System.out.println("\t\t\t\t 2 Add a Friend");
            System.out.println("\t\t\t\t 3 Remove a Friend");
            System.out.println("\t\t\t\t 4 Block a Friend");
            System.out.println("\t\t\t\t 5 Unblock a Friend");
            System.out.println("\t\t\t\t 6 Blocked Friends List");
            System.out.println("\t\t\t\t 7 Friend requests");
            System.out.println("\t\t\t\t 9 Exit to User Menu");
            String operation = Input.readString(1);
            switch (operation) {
                case "1":
                    user.getFriendDataBase().friendsInformation();
                    Input.pressAnyKeyToExit();
                    break;

                case "2":
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to add?");
                            String username = Input.readString(20, false);
                            while (DataBase.findUser(username) == null) {
                                System.out.println("The user you want to add does not exist.");
                                System.out.println("Enter Y to exit, Enter N to try again.");
                                char answer = Input.readSelection();
                                if (answer == 'Y') return;
                                System.out.println("Who do you want to add?");
                                username = Input.readString(20, false);
                            }
                            user.addFriend(DataBase.findUser(username));
                        }
                    }.judge();
                    break;

                case "3":
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to remove?");
                            String username = Input.readString(20, false);
                            while (!userService.checkFriend(user, DataBase.findUser(username))) {
                                System.out.println("The user you want to remove does not exist in your friend list");
                                System.out.println("Enter Y to exit, Enter N to try again.");
                                char answer = Input.readSelection();
                                if (answer == 'Y') return;
                                System.out.println("Who do you want to remove?");
                                username = Input.readString(20, false);
                            }
                            user.removeFriend(DataBase.findUser(username));
                        }
                    }.judge();
                    break;

                case "4":
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to block?");
                            String username = Input.readString(20, false);
                            while (DataBase.findUser(username) == null) {
                                System.out.println("The user you want to block does not exist.");
                                System.out.println("Enter Y to exit, Enter N to try again.");
                                char answer = Input.readSelection();
                                if (answer == 'Y') return;
                                System.out.println("Who do you want to block?");
                                username = Input.readString(20, false);
                            }
                            user.blockFriend(DataBase.findUser(username));
                        }
                    }.judge();
                    break;

                case "5":
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to unblock?");
                            String username = Input.readString(20, false);
                            while (!userService.checkBlocked(user, DataBase.findUser(username))) {
                                System.out.println("The user you want to unblock does not exist in your blocked list");
                                System.out.println("Enter Y to exit, Enter N to try again.");
                                char answer = Input.readSelection();
                                if (answer == 'Y') return;
                                System.out.println("Who do you want to unblock?");
                                username = Input.readString(20, false);
                            }
                            user.unBlockFriend(DataBase.findUser(username));
                        }
                    }.judge();
                    break;
                case "6":
                    user.getFriendDataBase().blockedFriendsInfromation();
                    Input.pressAnyKeyToExit();
                    break;


                case "7":
                    boolean flag1 = true;
                    while (flag1) {
                        user.getFriendDataBase().requestFriendsInformation();
                        System.out.println("Enter 1 to Accept a friend request");
                        System.out.println("Enter 2 to Decline a friend request");
                        System.out.println("Enter 9 to Exit Friend Request List");
                        String answer = Input.readString(1, false);
                        switch (answer) {
                            case "1":
                                new Object() {
                                    void judge() {
                                        System.out.println("Who do you want to Accept?");
                                        String username = Input.readString(20, false);
                                        while (!userService.existFriendRequest(user, DataBase.findUser(username))) {
                                            System.out.println("The user you want to Accept " +
                                                    "does not exist in your Friend Request List");
                                            System.out.println("Enter Y to exit, Enter N to try again.");
                                            char answer = Input.readSelection();
                                            if (answer == 'Y') return;
                                            System.out.println("Who do you want to Accept?");
                                            username = Input.readString(20, false);
                                        }
                                        user.acceptFriendRequest(DataBase.findUser(username));
                                        System.out.println("Accept Successfully!");
                                    }
                                }.judge();
                                break;

                            case "2":
                                new Object() {
                                    void judge() {
                                        System.out.println("Who do you want to Decline?");
                                        String username = Input.readString(20, false);
                                        while (!userService.existFriendRequest(user, DataBase.findUser(username))) {
                                            System.out.println("The user you want to Decline " +
                                                    "does not exist in your Friend Request List");
                                            System.out.println("Enter Y to exit, Enter N to try again.");
                                            char answer = Input.readSelection();
                                            if (answer == 'Y') return;
                                            System.out.println("Who do you want to Decline?");
                                            username = Input.readString(20, false);
                                        }
                                        user.declineFriendRequest(DataBase.findUser(username));
                                        System.out.println("Decline Successfully!");
                                    }
                                }.judge();
                                break;

                            case "9":
                                System.out.println("Exit!");
                                flag1 = false;
                                break;
                            default:
                                System.out.println("Invalid input! Enter again!");
                        }
                    }
                    break;
                case "9":
                    return;
            }
        }
    }

    private void settingMenu(User user) {
        System.out.println("Welcome to the Setting!");
        while (true) {
            System.out.println("\t\t\t\t\tSetting Menu (" + user.getUsername() + ")");
            System.out.println("\t\t\t\t 1 Profiles");
            System.out.println("\t\t\t\t 2 Messages setting");
            System.out.println("\t\t\t\t 9 Exit to User Menu");
            String operation = Input.readString(1);
            switch (operation) {
                case "1":
                    profilesMenu(user);
                    break;
                case "2" :
                    messagesPrivacyMenu(user);
                    break;
                case "9" :
                    System.out.println("Exit!");
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }

    private void messagesPrivacyMenu(User user) {
        while (true) {
            if (user.isMessagePrivacySettings())
                System.out.println("The current status is set to allow all users to send you messages.");
            else
                System.out.println("The current status is set to allow only your friends to send you messages.");
            System.out.println("1 Change the Message Privacy Setting");
            System.out.println("9 Exit");
            String operation = Input.readString(1);
            switch (operation) {
                case "1" :
                    user.setMessagePrivacySettings(!user.isMessagePrivacySettings());
                    break;
                case "9" :
                    System.out.println("Exit!");
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }

    private void profilesMenu(User user) {
        while (true) {
            user.profileInformation();
            System.out.println("\t\t\t\t 1 Edit Profiles");
            System.out.println("\t\t\t\t 2 Edit Profiles Picture");
            System.out.println("\t\t\t\t 9 Exit");
            String operation1 = Input.readString(1);
            switch (operation1) {
                case "1":
                    editProfilesMenu(user);
                    break;
                case "2" :
                    editProfilesPicture(user);
                    break;
                case "9":
                    System.out.println("Exit!");
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }

    private void editProfilesPicture(User user) {
        while (true) {
        System.out.println("\t\t\t\t 1 Show Current Profile Picture");
        System.out.println("\t\t\t\t 2 Upload a new Profile Picture");
        System.out.println("\t\t\t\t 9 Exit");
        String operation1 = Input.readString(1);
        switch (operation1) {
            case "1":
                user.showProfilePicture();
                break;
            case "2":
                System.out.println("Image upload manager has opened, Please check all programs in your computer.");
                user.uploadProfilePicture();
                break;
            case "9":
                System.out.println("Exit!");
                return;
            default:
                System.out.println("Invalid input! Enter again!");
            }
        }
    }




    private void editProfilesMenu(User user) {
        while (true) {
            System.out.println("What do you want to change?");
            System.out.println("\t\t\t\t 1 Username");
            System.out.println("\t\t\t\t 2 Email");
            System.out.println("\t\t\t\t 3 Bio");
            System.out.println("\t\t\t\t 4 Password");
            System.out.println("\t\t\t\t 9 Exit");
            String operation2 = Input.readString(2);
            switch (operation2) {
                case "1":
                    System.out.println("Please enter your Username, which will be limited to 20 digits");
                    String username = Input.readString(20, false);

                    while (DataBase.findUser(username) != null && !(username.equals(user.getUsername()))) {
                        System.out.println("This username is already taken! Enter again!");
                        System.out.println("Enter your original name if you do not wish to make changes.");
                        username = Input.readString(20, false);
                    }
                    user.setUsername(username);
                    System.out.println("Username has been changed.");
                    System.out.println(
                            "Please proceed with caution " +
                                    "and use the newly assigned username for login purposes.");
                    break;

                case "2":
                    System.out.println("Please enter your email");
                    String email = Input.readEmail(30, false);
                    user.setEmail(email);
                    System.out.println("Email has been changed.");
                    break;

                case "3":
                    System.out.println("Please enter your bio, which will be limited to 100 words");
                    String bio = Input.readString(100);
                    user.setBio(bio);
                    System.out.println("Bio has been changed.");
                    break;

                case "4" :
                    new Object() {
                        void judge() {
                            System.out.println("Please enter your current password!");
                            String currentPassword = Input.readString(20, false);
                            while (!userService.checkSecurity(user.getUsername(), currentPassword)) {
                                System.out.println("The password is not correct!");
                                System.out.println("Enter Y to exit, Enter N to try again.");
                                char answer = Input.readSelection();
                                if (answer == 'Y') return;
                                System.out.println("Please enter your current password!");
                                currentPassword = Input.readString(20, false);
                            }
                            System.out.println("Please enter your new password!");
                            String password = Input.readString(20, false);
                            user.setPassword(password);
                            System.out.println("Password has been changed.");
                            System.out.println(
                                    "Please proceed with caution and " +
                                            "use the newly assigned password for login purposes.");
                        }
                    }.judge();
                    break;
                case "9" :
                    System.out.println("Exit!");
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }
}
