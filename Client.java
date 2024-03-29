import java.sql.SQLOutput;

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
                                        if (!(MessageService.sendMessage(account, receiver, content))) {
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
                                case "8" :
                                    menu = false;
                                    break;
                                case "9":
                                    System.out.println("Exit!");
                                    menu = false;
                                    login = false;
                                    break;
                                default:
                                    System.out.println("Invalid input! try it again!");
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

}

