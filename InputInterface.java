public interface InputInterface {

    /**
     * Reads a single character from the console input.
     *
     * @return The character read from the console.
     */
    public static  char  readChar()
    {
        return '-';
    }
    public static void pressAnyKeyToExit()
    {
        return;
    }
    public static int readInt(int limit)
    {
        return 1;
    }
    public static int readRange(int min, int max)
    {
        return 1;
    }
    public static String readString()
    {
        return null;
    }
    public static String readString(int limit, boolean space)
    {
        return null;
    }
   public static String readEmail(int limit, boolean blankReturn)
   {
       return null;
   }
    public static char readSelection()
    {
        return '-';
    }
}
