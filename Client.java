/**
 * Client class - Contains the Main Method and runs the code
 *
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 15, 2024
 */
public class Client implements ClientInterface {
    
    private String key = "";
    private UserService userService = new UserService();
    public static void main(String[] args) {
        new LoginMenu();
    }
    //Main menu for user interaction
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
                    messagesMenu(user);
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

    private void messagesMenu(User user) {
        boolean flag = true;
        while (flag) {
            System.out.println("\t\t\t\t\tWelcome to your Messages Menu!");
            System.out.println("\t\t\t\t1 Sent Messages");
            System.out.println("\t\t\t\t2 Received Message");
            System.out.println("\t\t\t\t3 Delete Messages");
            System.out.println("\t\t\t\t9 Exit");
            String operation = Input.readString(1, false);
            switch (operation) {
                case "1" :
                    userService.sendMessage();
                    break;
                case "2" :
                    userService.checkReceiveMessages();
                    break;
                case "3" :
                    userService.deleteMessages();
                    break;
                case "9" :
                    System.out.println("Exit!");
                    return;
                default:
                    System.out.println("Invalid input! Enter again!");
            }
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
