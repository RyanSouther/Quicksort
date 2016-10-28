
// -------------------------------------------------------------------------
/**
 *  A linked list to store data. It has a fixed length
 *  @param <T> the data format
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class List<T>
{
    /**
     *head of the list
     */
    Qnode<T> head;
    /**
     *tail of the list
     */
    Qnode<T> tail;


    /**
     * Create a new List object.
     * @param size size of the list
     */
    public List(int size)
    {
        head = new Qnode<T>(null);
        tail = new Qnode<T>(null);
        head.setNext(tail);
        tail.setPrev(head);

        int i = 0;
        while (i < size)
        {
            Qnode<T> temp = new Qnode<T>(null);
            tail.getPrev().setNext(temp);
            temp.setPrev(tail.getPrev());
            temp.setNext(tail);
            tail.setPrev(temp);
            i++;
        }

    }
    // ----------------------------------------------------------
    /**
     * Adds or promotes data to the list
     * @param data data of new node to add
     * @return null if data added, the data removed if there is any
     */
    public T addOrPromo(T data)
    {
        if (data == null)
        {
            return insert(data);
        }
        Qnode temp = head;
        temp = temp.getNext();
        while (temp != tail  && temp.getData() != null)
        {
            if (temp.getData().equals(data))
            {
                movehead(temp);
                return null;
            }
            temp = temp.getNext();
        }
        return insert(data);
    }

    // ----------------------------------------------------------
    /**
     * Insert data
     * @param data the data to insert
     * @return the node removed
     */
    public T insert(T data)
    {
        //insert node
        Qnode<T> node = new Qnode<T>(data);
        node.setPrev(head);
        node.setNext(head.getNext());
        node.getNext().setPrev(node);
        head.setNext(node);

        //pop off last node

        @SuppressWarnings("unchecked")
        Qnode<T> popped = tail.getPrev();
        popped.getPrev().setNext(tail);
        tail.setPrev(popped.getPrev());

        return popped.getData();

    }

    // ----------------------------------------------------------
    /**
     * remove data from list
     * @param data to look for and remove
     */
    public void remove(T data)
    {
        Qnode node = head;
        node = node.getNext();
        while (node != tail)
        {
            if (data.equals(node.getData()))
            {
                //cut out the node
                node.getPrev().setNext(node.getNext());
                node.getNext().setPrev(node.getPrev());

                Qnode<T> temp = new Qnode<T>(null);
                temp.setNext(tail);
                temp.setPrev(tail.getPrev());
                temp.getPrev().setNext(temp);
                tail.setPrev(temp);

                return;
            }
            node = node.getNext();
        }
    }
    /**
     * Move data to the head
     * @param node to move to the head
     */
    private void movehead(Qnode node)
    {
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());

        //set node's pointers
        node.setPrev(head);
        node.setNext(head.getNext());

        //adjust pointers
        node.getNext().setPrev(node);
        head.setNext(node);
    }



}
