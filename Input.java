
import java.util.*;
   /**
 * The Input class provides methods for reading user input from the console.
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong  , Kaustubh Mathur
 * @version April 1, 2024
 */
public class Input {

    private static Scanner scanner = new Scanner(System.in);

    // Reads a single character from the console input.
    public static char readChar() {
        String str = readKeyBoard(1);
        return str.charAt(0);
    }

    //Prompts the user to press Enter key to exit.
    public static void pressAnyKeyToExit() {
        System.out.println("Press Enter key to exit...");
        scanner.nextLine();
        System.out.println("Exit!");
    }

    //Reads an integer from the console input within the specified limit.
    public static int readInt(int limit) {
        int n;
        for (; ; ) {
            String str = readKeyBoard(limit);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.print("It is not an integer, enter again:");
            }
        }

        return n;
    }
    //Reads an integer from the console input within the specified range.
    public static int readRange(int min, int max) {
        int n;
        for (; ; ) {
            String str = readKeyBoard();
            try {
                n = Integer.parseInt(str);
                if (n < min || n > max) {
                    System.out.println("This number must be greater than or equal to " + min
                            + " and be less than or equal to " + max + ", enter again:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("It is not an integer, enter again:");
            }
        }
        return n;
    }



    //Reads a string from the console input within the specified limit.
    public static String readString(int limit) {
        String str = readKeyBoard(limit);
        return str;
    }
    //Reads a string from the console input.
    public static String readString() {
        return readKeyBoard();
    }
    //Reads a string from the console input within the specified limit and allows or disallows spaces.
    public static String readString(int limit, boolean space) {
        return readKeyBoard(limit, space);
    }

    //Reads an email address from the console input within the specified limit and allows or disallows blank return.
    public static String readEmail(int limit, boolean blankReturn) {
        String email = readKeyBoard(limit, blankReturn);
        boolean flag = email.contains("@");
        while (!flag) {
            System.out.println("Invalid email. Please include an '@' in the email address, enter again:");
            email = readString(limit, blankReturn);
            flag = email.contains("@");
        }
        return email;
    }



    private static String readKeyBoard(int limit) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty() || line.length() > limit) {
                System.out.print("The length must be less than or equal to " + limit + " please enter again:");
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
                    System.out.println("Cannot enter nothing, please enter again:");
                    continue;
                }
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.println("The length must be less than or equal to " + limit + "\n please enter again:");
                continue;
            }
            break;
        }

        return line;
    }
    //Reads a single character ('Y' or 'N') from the console input
    public static char readSelection() {
        System.out.print("Please enter Y or N:");
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Invalid input! please enter again:");
            }
        }
        return c;
    }

    private static String readMax(int limit, boolean blankReturn) {

        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else {
                    System.out.println("Cannot enter nothing, please enter again:");
                    continue;
                }
            }

            if (line.length() < 1 || line.length() > limit) {
                System.out.println("The length must be less than or equal to " + limit + "\n please enter again:");
                continue;
            }
            break;
        }

        return line;
    }
}
