import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Testing the List
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class ListTest extends TestCase
{
    /**
     * The list we're going to test
     */
    List<String> l1;
    /**
     * Set up the variables
     */
    public void setUp()
    {
        l1 = new List<String>(3);
    }

    // ----------------------------------------------------------
    /**
     * Testing insertion and deletion
     */
    public void testinsert()
    {
        l1.insert("hello");
        assertEquals(l1.head.getNext().getData(), "hello");
        l1.insert("d2");
        assertEquals(l1.head.getNext().getData(), "d2");
        assertEquals(l1.head.getNext().getNext().getData(), "hello");
        l1.addOrPromo("goodbye");
        assertEquals(l1.head.getNext().getData(), "goodbye");
        assertEquals(l1.head.getNext().getNext().getData(), "d2");
        l1.remove("goodbye");
        l1.remove("hello");
        assertEquals(l1.head.getNext().getData(), "d2");
    }

    // ----------------------------------------------------------
    /**
     * Test the add or promo ability of the software
     */
    public void testAddOrPromo()
    {
        l1.addOrPromo("goodbye");
        l1.addOrPromo("hello");
        assertEquals(l1.head.getNext().getData(), "hello");
        assertEquals(l1.head.getNext().getNext().getData(), "goodbye");
        l1.addOrPromo("goodbye");
        assertEquals(l1.head.getNext().getData(), "goodbye");
        assertEquals(l1.head.getNext().getNext().getData(), "hello");
        assertEquals(l1.head.getNext().getNext().getNext().getData(), null);
        l1.addOrPromo("d1");
        l1.addOrPromo("d2");
        assertEquals(l1.head.getNext().getData(), "d2");
        assertEquals(l1.head.getNext().getNext().getData(), "d1");
        assertEquals(l1.head.getNext().getNext().getNext().getData(),
            "goodbye");
        assertEquals(l1.addOrPromo(null), "goodbye");
    }




}
