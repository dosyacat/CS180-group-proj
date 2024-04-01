public interface InputInterface {

    // Reads a single character from the console input.
    public static char readChar() {
        return '-';
    }

    //Prompts the user to press Enter key to exit.
    public static void pressAnyKeyToExit() {
        // Implementation goes here
    }

    //Reads an integer from the console input within the specified limit.
    public static int readInt(int limit) {
        return 0;
    }

    //Reads an integer from the console input within the specified range.
    public static int readRange(int min, int max) {
        return 0;
    }

    //Reads a string from the console input within the specified limit.
    public static String readString(int limit) {
        return null;
    }

    //Reads a string from the console input.
    public static String readString() {
        return null;
    }

    //Reads a string from the console input within the specified limit and allows or disallows spaces.
    public static String readString(int limit, boolean space) {
        return null;
    }

    //Reads an email address from the console input within the specified limit and allows or disallows blank return.
    public static String readEmail(int limit, boolean blankReturn) {
        return null;
    }

    //Reads a single character ('Y' or 'N') from the console input
    public static char readSelection() {
        return '-';
    }

}
