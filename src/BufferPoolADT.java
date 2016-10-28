
// -------------------------------------------------------------------------
/**
 *  A buffer pool that keeps track of our buffers
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public interface BufferPoolADT
{
    // ----------------------------------------------------------
    /**
     * Retrieve a buffer
     * @param index location of buffer
     * @return the buffer
     */
    //public abstract BufferADT getbuffer(int index);

    // ----------------------------------------------------------
    /**
     * Get the size of the pool
     * @return size
     */
    public abstract int size();

    // ----------------------------------------------------------
    /**
     * Flush the pool, writing changes to disk
     */
    public abstract void flush();

}
