import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Testing the bufferAccess class
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class BufferAccessTest extends TestCase
{
    /**
     * our pool we're going to test
     */
    BufferAccess pool;
    /**
     * Set up the pool and initialize
     */
    public void setUp()
    {
        pool = new BufferAccess(1, "testfiles/testfile.txt");
    }

    // ----------------------------------------------------------
    /**
     * Test Getting calues from the pool
     */
    public void testGet()
    {
        assertEquals(pool.get(0), 8102);
        assertEquals(pool.get(1), 13896);
        assertEquals(pool.get(1025), 2561);
    }

    // ----------------------------------------------------------
    /**
     * Test swapping around buffers
     */
    public void testSwap()
    {
        assertEquals(pool.get(0), 8102);
        assertEquals(pool.get(1), 13896);
        pool.swap(0, 1);
        assertEquals(pool.get(1), 8102);
        assertEquals(pool.get(0), 13896);
        pool.swap(0, 1);
        pool.swap(0, 1025);
        assertEquals(pool.get(1025), 8102);
        assertEquals(pool.get(0), 2561);
        pool.swap(0, 1025);
        assertEquals(pool.get(0), 8102);
        assertEquals(pool.get(1025), 2561);
        pool.flush();
    }
    // ----------------------------------------------------------
    /**
     * Test getting the size of the pool
     */
    public void testsize()
    {
        assertEquals(pool.size(), 2048);
    }
}
