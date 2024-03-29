import java.util.*;
/**


 */
public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static char readChar() {
        String str = readKeyBoard(1);
        return str.charAt(0);
    }

    public static int readInt(int limit) {
        int n;
        for (; ; ) {
            String str = readKeyBoard(limit);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("It is not an integer, enter again!");
            }
        }
        return n;
    }

    public static String readString(int limit) {
        String str = readKeyBoard(limit);
        return str;
    }

    public static String readString() {
        return readKeyBoard();
    }

    public static String readString(int limit, boolean space) {
        return readKeyBoard(limit, space);
    }


    public static String readEmail(int limit, boolean blankReturn) {
        String email = readKeyBoard(limit, blankReturn);
        boolean flag = true;
        while (!(flag = email.contains("@"))) {
            System.out.println("Invalid email. Please include an '@' in the email address.");
            email = readString(limit, blankReturn);
        }
        return email;
    }



    private static String readKeyBoard(int limit) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty() || line.length() > limit) {
                System.out.print("The length must be less than or equal to " + limit + " please enter again!");
                continue;
            }
            break;
        }
        return line;
    }

    private static String readKeyBoard() {
        String line = "";
        line = scanner.nextLine();
        return line;
    }

    private static String readKeyBoard(int limit, boolean blankReturn) {

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else {
                    System.out.println("Cannot enter nothing, please enter again");
                    continue;
                }
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.println("The length must be less than or equal to " + limit + "\n please enter again");
                continue;
            }
            break;
        }

        return line;
    }

    public static char readSelection() {
        System.out.print("Please enter Y or N:");
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Invalid input! please enter again");
            }
        }
        return c;
    }





}
