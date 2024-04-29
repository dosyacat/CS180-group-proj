public interface InputInterface {
/**
 * Interface for Input Class
 * <p>Purdue University -- CS18000 -- Spring 2024</p>
 *
 * @author Yuhan Zeng, Yeldos Zhumakyn, Shresthi Srivastava, Bryce Wong , Kaustubh Mathur
 * @version April 29, 2024
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
