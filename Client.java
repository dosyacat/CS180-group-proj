/**
 * Client class - Contains the Main Method and runs the code
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public class Client {
    
    private String key = "";
    private UserService userService = new UserService();

    public static void main(String[] args) {
        new Client().mainMenu();
    }
    //Main menu for user interaction
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
    // Method for user sign up
    private void userSignUp() {
        userService.userSignUp();
    }
    //Method for User SignIn
    private void userSignin() {
        System.out.print("Please enter your account:");
        String account = Input.readString();
        System.out.print("Please enter your password:");
        String password = Input.readString();
        //Checking user security credentials
        User user = userService.userSignIn(account, password);
        if (user != null) {
            userMenu(user);
        } else {
            System.out.println("The account or password is not correct!");
        }
    }
    //Method for user menu
    private void userMenu(User user) {
        String userName = user.getUsername();
        System.out.println("==================== Welcome " + userName + " !   =====================");

        while (true) {

            System.out.println("\t\t\t\t\tUser Menu (" + userName + ")");
            System.out.println("\t\t\t\t 1 User viewer");
            System.out.println("\t\t\t\t 2 Messages Menu");
            System.out.println("\t\t\t\t 3 User search");
            System.out.println("\t\t\t\t 4 Friends Menu");
            System.out.println("\t\t\t\t 5 Setting");
            System.out.println("\t\t\t\t 8 Exit to Login interface");
            System.out.println("\t\t\t\t 9 Exit");

            key = Input.readString();
            switch (key) {
                case "1":
                    userService.userView();
                    break;

                case "2":
                    break;

                case "3":
                    userService.userSearch();
                    break;

                case "4":
                    friendMenu(user);
                    break;

                case "5":
                    settingMenu(user);
                    break;

                case "8":
                    userService.logout();
                    return;

                case "9":
                    userService.logout();
                    System.exit(0);

                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }

    //Method for Message Menu
    /*
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
    //Method for check messages the user have sent.
    private void checkSendMessages(User user) {
        user.getMessageDataBase().showSendMessages();
    }

    //Method for user to send pictures.
    /*
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

    //Method for check pirctures the user received
    private void checkReceivePictures(User user) {
        user.getMessageDataBase().showReceivePictures();
    }

    //Methods for check messages the user received.
    private void checkReceiveMessages(User user) {
        user.getMessageDataBase().showReceiveMessages();
    }
    //Method to send messages
    private void sendMessages(User user) {
        while (true) {
            System.out.println("Send private message");
            System.out.println("Who do you want to send?");
            String receiver = Input.readString(20);

            if (DataBase.findUser(receiver) == null) {
                System.out.print("Sending messages failed!");
                System.out.println("Receiver not found");
                //Option to exit or to try again
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
    //Method to Delete Messages
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
            } else break;
        }
        System.out.println("Which one do you want to delete?");
        int count = userService.checkMessageCount(user, DataBase.findUser(name));
        int answer = Input.readRange(1, count);
        user.deleteMessage(name, answer);
    }

     */
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
            System.out.println("\t\t\t\t 9 Exit to User Menu");
            String operation = Input.readString(1);
            switch (operation) {
                case "1":
                    userService.showFriendList();
                    break;

                case "2":
                    userService.AddFriend();
                    break;

                case "3":
                    userService.removeFriend();
                    break;

                case "4":
                    userService.blockFriend();
                    break;

                case "5":
                    userService.unBlockFriend();
                    break;

                case "9":
                    return;
            }
        }
    }



    //Method for Setting Menu
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
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }
    //Method to set and edit message privacy settings
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
                    userService.changeMessagesPrivacy();
                    break;
                case "9" :
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }
    //Method to display the profile menu and furthermore call the required method based on the user's choice
    private void profilesMenu(User user) {
        while (true) {
            user.profileInformation();
            System.out.println("\t\t\t\t 1 Edit Profiles");
            System.out.println("\t\t\t\t 9 Exit");
            String operation1 = Input.readString(1);
            switch (operation1) {
                case "1":
                    editProfilesMenu(user);
                    break;
                case "9":
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
                   userService.editUserName();
                   break;

                case "2":
                    userService.editEmail();
                    break;

                case "3":
                    userService.editBio();
                    break;

                case "4" :
                    userService.editPassword();
                    break;
                case "9" :
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
        }
    }
}
