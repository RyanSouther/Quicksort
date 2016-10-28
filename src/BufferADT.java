
// -------------------------------------------------------------------------
/**
 *  ADT for a buffer class
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public interface BufferADT
{
    // ----------------------------------------------------------
    /**
     * get the data read from the buffer
     * @return data
     */
    public abstract byte[] read();
    // ----------------------------------------------------------
    /**
     * Write data to disk
     * @param data to write
     */
    public abstract void write(byte[] data);
    // ----------------------------------------------------------
    /**
     * Clear the buffer and write to disk
     */
    public abstract void flush();
    // ----------------------------------------------------------
    /**
     * Return size of buffer
     * @return size
     */
    public abstract int size();

}
