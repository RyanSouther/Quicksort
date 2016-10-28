
// -------------------------------------------------------------------------
/**
 *  A node that stores data for a linked list
 *  @param <T>
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class Qnode<T>
{
    private Qnode<T> next;
    private Qnode<T> prev;
    private T data;

    // ----------------------------------------------------------
    /**
     * Create a new Qnode object.
     * @param data data to store in this node
     */
    public Qnode(T data)
    {
        this.data = data;
    }

    // ----------------------------------------------------------
    /**
     * Get data stored in this node
     * @return data
     */
    public T getData()
    {
        return data;
    }

    // ----------------------------------------------------------
    /**
     * Get the next node
     * @return the next node
     */
    public Qnode getNext()
    {
        return next;
    }

    // ----------------------------------------------------------
    /**
     * Set the next node
     * @param next the next node in the list
     */
    public void setNext(Qnode next)
    {
        this.next = next;
    }

    // ----------------------------------------------------------
    /**
     * Get the previous node
     * @return the prev node
     */
    public Qnode getPrev()
    {
        return prev;
    }

    // ----------------------------------------------------------
    /**
     * Set the previous node
     * @param prev the previous node
     */
    public void setPrev(Qnode prev)
    {
        this.prev = prev;
    }


}
