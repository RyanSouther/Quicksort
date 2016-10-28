import student.TestCase;
// -------------------------------------------------------------------------
/**
 *  Testing the Qnode data structure
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class QnodeTest extends TestCase
{
    private Qnode<String> node1;
    private Qnode<String> node2;
    private Qnode<String> node3;

    /**
     * Create some new nodes for each test method.
     */
    public void setUp()
    {
        node1 = new Qnode<String>("node1");
        node2 = new Qnode<String>("node2");
        node3 = new Qnode<String>("node3");
    }

    // ----------------------------------------------------------
    /**
     * Test joining nodes
     */
    public void testjoin()
    {
        node1.setNext(node2);
        node2.setNext(node3);
        node2.setPrev(node1);
        node3.setPrev(node2);
        node3.setNext(null);
        assertEquals(node1.getNext(), node2);
        assertEquals(node2.getPrev(), node1);
        assertEquals(node2.getNext(), node3);
        assertEquals(node3.getNext(), null);
    }

    // ----------------------------------------------------------
    /**
     * Make sure they hold data correctly
     */
    public void testdata()
    {

        assertEquals(node1.getData(), "node1");
        assertEquals(node2.getData(), "node2");
        assertNull(node1.getNext());
        assertNull(node2.getPrev());
    }
}
