import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Simple test of statistics
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class StatisticsTest extends TestCase
{
    // ----------------------------------------------------------
    /**
     * Make sure values are able to be accessed
     */
    public void testvalues()
    {
        Statistics.filename = null;
        assertEquals(Statistics.filename, null);
        @SuppressWarnings("unused")
        Statistics thing;
    }


}
