
// -------------------------------------------------------------------------
/**
 *  This class keeps track of our data
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class Statistics
{
    /**
     * the amount of time it takes to run
     */
    public static long time = 0;
    /**
     * the number of times we hit in cache
     */
    public static int cachehits = 0;
    /**
     * the number of times we read from disk
     */
    public static int diskreads = 0;
    /**
     * the number of times we write to disk
     */
    public static int diswrites = 0;
    /**
     * the name of the file we're sorting
     */
    public static String filename;
}
