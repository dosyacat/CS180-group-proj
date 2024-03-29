import java.sql.SQLOutput;

@SuppressWarnings("all")
public class Client {
    private boolean menu = true;
    private boolean login = true;
    private String key = "";
    private UserService userService = new UserService(); // For sign in or sign up;

    public void mainMenu() {
        while (login) {

            System.out.println("=========Welcome Login========");
            System.out.println("\t\t 1 Sign In");
            System.out.println("\t\t 2 Sign Up");
            System.out.println("\t\t 9 Exit");
            System.out.print("Please enter your choice:");

            key = Input.readString(1);
            switch (key) {
                case "1" : {
                    System.out.print("Please enter your account:");
                    String account = Input.readString();
                    System.out.print("Please enter your password:");
                    String password = Input.readString();
                    if (userService.checkUser(account, password)) {
                        System.out.println("==================== Welcome " + account + " !   =====================");
                        menu = true;
                        while (menu) {
                            User user = DataBase.findUser(account);
                            System.out.println("\t\t\t\t\tUser Menu (" + account + ")");
                            System.out.println("\t\t\t\t 1 User viewer");
                            System.out.println("\t\t\t\t 2 Send private messages");
                            System.out.println("\t\t\t\t 3 Check received messages");
                            System.out.println("\t\t\t\t 4 User search");
                            System.out.println("\t\t\t\t 5 Friends List");
                            System.out.println("\t\t\t\t 8 Exit to Login interface");
                            System.out.println("\t\t\t\t 9 Exit");
                            key = Input.readString();
                            switch (key) {

                                case "1":
                                    DataBase.userInformation();
                                    break;

                                case "2":
                                    while (true) {
                                        System.out.println("Send private message");
                                        System.out.println("Who do you want to send?");
                                        String receiver = Input.readString(20);

                                        if (receiver.equals(account))
                                            System.out.println("You are sending messages to yourself!");
                                        System.out.println("What do you want to send?");
                                        String content = Input.readString(100000);

                                        if (userService.checkBlocked(user,DataBase.findUser(receiver))) {
                                            System.out.println("You can't message someone who has blocked you " +
                                                    "or someone you have blocked.");
                                            System.out.println("Do you want to exit?");
                                            System.out.println("Enter Y to exit, Enter N to try it again.");
                                            char answer = Input.readSelection();
                                            if (answer == 'Y') break;
                                            continue;
                                        }

                                        if (!(MessageService.sendMessage(account, receiver, content))) {
                                            System.out.println("Receiver not found");
                                            System.out.println("Do you want to exit?");
                                            System.out.println("Enter Y to exit, Enter N to try it again.");
                                            char answer = Input.readSelection();
                                            if (answer == 'Y') break;
                                            continue;
                                        }
                                        break;
                                    }
                                    break;

                                case "3":
                                    if (user.getReceiveArrayList().isEmpty()) {
                                        System.out.println("You haven't received any messages yet.");
                                    } else {
                                        System.out.println("There are the messages you've received!");
                                        System.out.println(user.getReceiveArrayList());
                                    }
                                    break;

                                case "4" :
                                    System.out.println("Who are you searching for?");
                                    String answer = Input.readString(20, false);
                                    User user1 = DataBase.findUser(answer);
                                    if (user1 == null)
                                        System.out.println("The user you are looking for does not exist.");
                                    else System.out.println(user1.toString());
                                    break;

                                case "5" :
                                    friendList(user);
                                    break;

                                case "8" :
                                    menu = false;
                                    break;

                                case "9":
                                    System.out.println("Exit!");
                                    menu = false;
                                    login = false;
                                    break;

                                default:
                                    System.out.println("Invalid input! Enter again!");
                            }
                        }
                    } else {
                        System.out.println("The account or password is not correct!");
                    }
                    break;
                }
                case "2" : {
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
                    System.out.println("You have signed up successfully!");
                    break;
                }

                case "9" :
                    login = false;
                    break;
            }

        }

    }

    private void friendList(User user) {
        System.out.println("Welcome to your friends list!");
        boolean flag = true;
        while(flag) {
            System.out.println("\t\t\t\t\tFriends List (" + user.getUsername() + ")");
            System.out.println("\t\t\t\t 1 Show Friends List");
            System.out.println("\t\t\t\t 2 Add a Friend");
            System.out.println("\t\t\t\t 3 Remove a Friend");
            System.out.println("\t\t\t\t 4 Block a Friend");
            System.out.println("\t\t\t\t 5 Unblock a Friend");
            System.out.println("\t\t\t\t 6 Friend requests");
            System.out.println("\t\t\t\t 8 Exit to User Menu");
            String operation = Input.readString(1);
            switch (operation) {
                case "1":
                    user.getFriendDataBase().friendsInformation();
                    break;

                case "2":
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to add?");
                            String username = Input.readString(20, false);
                            while (DataBase.findUser(username) == null) {
                                System.out.println("The user you want to add does not exist.");
                                System.out.println("Do you want to exit?");
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

                case "3" :
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to remove?");
                            String username = Input.readString(20, false);
                            while (!userService.checkFriend(user, DataBase.findUser(username))) {
                                System.out.println("The user you want to remove does not exist in your friend list");
                                System.out.println("Do you want to exit?");
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

                case "4" :
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to block?");
                            String username = Input.readString(20, false);
                            while (DataBase.findUser(username) == null) {
                                System.out.println("The user you want to block does not exist.");
                                System.out.println("Do you want to exit?");
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

                case "5" :
                    new Object() {
                        void judge() {
                            System.out.println("Who do you want to unblock?");
                            String username = Input.readString(20, false);
                            while (!userService.checkBlocked(user, DataBase.findUser(username))) {
                                System.out.println("The user you want to unblock does not exist in your block list");
                                System.out.println("Do you want to exit?");
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

                case "6" :
                    boolean flag1 = true;
                    while (flag1) {
                        user.getFriendDataBase().requestFriendHashMapInformation();
                        System.out.println("Enter 1 to Accept a friend request");
                        System.out.println("Enter 2 to Decline a friend request");
                        System.out.println("Enter 3 to Exit Friend Request List");
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
                                            System.out.println("Do you want to exit?");
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
                                            System.out.println("Do you want to exit?");
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

                            case "3":
                                flag1 = false;
                                break;
                            default:
                                System.out.println("Invalid input! Enter again!");
                        }
                    }
                    break;
                case "8" :
                    return;
            }


        }
    }

}
