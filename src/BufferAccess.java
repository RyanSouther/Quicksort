import java.nio.ByteBuffer;
import java.io.File;


// -------------------------------------------------------------------------
/**
 *  Accesses the buffer pool and does all necessary functions.
 *  Makes life easier for access.
 *  Based on code outlined in
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class BufferAccess
{
    private static final int REC_SIZE = 4;
    private static final int BLOCK_SIZE = 4096;

    private BufferPool pool;

    // ----------------------------------------------------------
    /**
     * Create a new BufferAccess object.
     * @param numbuffs the number of buffers
     * @param name the filename
     */
    public BufferAccess(int numbuffs, String name)
    {
        File file = new File(name);
        pool = new BufferPool(file, numbuffs, BLOCK_SIZE);
    }

    // ----------------------------------------------------------
    /**
     * Get a record in the buffer array
     * @param index the location of the record
     * @return the short of the record
     */
    public short get(int index)
    {
        int block =  (index * REC_SIZE) / BLOCK_SIZE;

        int offset = (index * REC_SIZE) % BLOCK_SIZE;
        //new method
        byte[] temp = pool.getbufarray(block, offset, REC_SIZE / 2);
        short value = ByteBuffer.wrap(temp).getShort();
        //old method
        //byte[] temp = pool.getbuffer(block).read();
        //short value = ByteBuffer.wrap(temp).getShort(offset);
        return value;
    }
    // ----------------------------------------------------------
    /**
     * Swap two records
     * @param index1 the area of the first rec
     * @param index2 the area of the second rec
     */
    public void swap(int index1, int index2)
    {
        /*
        int block1 = (index1 * REC_SIZE) / BLOCK_SIZE;
        int offset1 = (index1 * REC_SIZE) % BLOCK_SIZE;
        int block2 = (index2 * REC_SIZE) / BLOCK_SIZE;
        int offset2 = (index2 * REC_SIZE) % BLOCK_SIZE;
        if (block1 != block2)
        {
            byte[] rec1 = new byte[REC_SIZE];
            BufferADT buf1 = pool.getbuffer(block1);
            BufferADT buf2 = pool.getbuffer(block2);
            byte[] buffer;
            byte[] buffer2;
            buffer = buf1.read();
            buffer2 = buf2.read();
            System.arraycopy(buffer, offset1, rec1, 0, REC_SIZE);

            //System.arraycopy(buffer2, offset2, rec2, 0, REC_SIZE);
            // Put record 1 in buffer for old record 2
            System.arraycopy(buffer2, offset2, buffer, offset1, REC_SIZE);
            //buf2.write(buffer2);
            // Put record 2 in buffer for old record 1
            //buffer = buf1.read();
            System.arraycopy(rec1, 0, buffer2, offset2, REC_SIZE);
            buf1.write(buffer);
            buf2.write(buffer2);
        }
        else
        {
            byte[] rec1 = new byte[REC_SIZE];
            BufferADT buf1 = pool.getbuffer(block1);
            byte[] buffer;
            buffer = buf1.read();
            System.arraycopy(buffer, offset1, rec1, 0, REC_SIZE);
            System.arraycopy(buffer, offset2, buffer, offset1, REC_SIZE);
            System.arraycopy(rec1, 0, buffer, offset2, REC_SIZE);
            buf1.write(buffer);
        }
        */
        int block1 = (index1 * REC_SIZE) / BLOCK_SIZE;
        int offset1 = (index1 * REC_SIZE) % BLOCK_SIZE;
        int block2 = (index2 * REC_SIZE) / BLOCK_SIZE;
        int offset2 = (index2 * REC_SIZE) % BLOCK_SIZE;

        byte[] rec1 = pool.getbufarray(block1, offset1, REC_SIZE);
        byte[] rec2 = pool.getbufarray(block2, offset2, REC_SIZE);
        pool.setbufarray(rec1, block2, offset2, REC_SIZE);
        pool.setbufarray(rec2, block1, offset1, REC_SIZE);



    }

    // ----------------------------------------------------------
    /**
     * Get the size of the pool
     * @return the size of the buffer pool
     */
    public int size()
    {
        return (pool.size() * (BLOCK_SIZE / REC_SIZE));
    }
    // ----------------------------------------------------------
    /**
     * Flush all the buffers
     */
    public void flush()
    {
        pool.flush();
    }

}
