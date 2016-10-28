
import java.io.FileNotFoundException;
import java.io.File;
import java.io.RandomAccessFile;


// -------------------------------------------------------------------------
/**
 *  A pool of buffers that keeps track of all buffers.
 *  buffers automatically flush and fill as needed.
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class BufferPool implements BufferPoolADT
{
    private int numbuffs;
    private int buffsize;
    private Buffer[] bufpool;
    private RandomAccessFile file;
    private List<Buffer> list;
    // ----------------------------------------------------------
    /**
     * Create a new BufferPool object.
     * @param file source file for data
     * @param poolsize size of the pool
     * @param buffsize size of the buffers
     */
    public BufferPool(File file, int poolsize, int buffsize)
    {
        this.numbuffs = (int)file.length() / buffsize;
        this.buffsize = buffsize;
        try
        {
            this.file = new RandomAccessFile(file, "rw");
        }
        catch (FileNotFoundException e)
        {
            // Something wrong with the file
            e.printStackTrace();
        }

        bufpool = new Buffer[(int) file.length() / buffsize];

        list = new List<Buffer>(poolsize);

    }
    /*
     * Retrieve a buffer
     * @param index location of buffer
     * @return the buffer
     */
    /*public BufferADT getbuffer(int index)
    {
        if (bufpool[index] == null)
        {

            if (index < numbuffs)
            {
                bufpool[index] =
                    new Buffer(index * buffsize, buffsize, this, file);
            }
            else
            {

                return null;
            }
        }

        return bufpool[index];
    }*/

    /**
     * Get the size of the pool
     * @return size
     */
    public int size()
    {
        return numbuffs;
    }
    /**
     * Flush the pool, and all its buffers
     */
    public void flush()
    {
        int i = 0;
        while (i < numbuffs)
        {
            bufpool[i].flush();
            i++;
        }
    }

    // ----------------------------------------------------------
    /**
     * The pool marks the buffer as used and promotes it up the list,
     * flushes if necessary
     * @param buff the buffer to mark as used
     */
    public void used(Buffer buff)
    {
        Buffer temp = list.addOrPromo(buff);
        if (temp != null)
        {
            temp.flush();
        }
    }


    // ----------------------------------------------------------
    /**
     * get the byte array of just the data we need
     * @param block which block the data is in
     * @param index index of data within the block
     * @param recsize size of the record
     * @return a byte array of the data
     */
    public byte[] getbufarray(int block, int index, int recsize)
    {
        if (bufpool[block] == null)
        {

            if (block < numbuffs)
            {
                bufpool[block] =
                    new Buffer(block * buffsize, buffsize, this, file);
            }
            else
            {

                return null;
            }
        }

        return bufpool[block].getval(index, recsize);
    }
    // ----------------------------------------------------------
    /**
     * Set some data in a block
     * @param indata input data
     * @param block block to edit
     * @param index place to edit
     * @param recsize size to edit
     */
    public void setbufarray(byte[] indata, int block, int index, int recsize)
    {

        while (numbuffs <= block)
        {
            expand();
        }
        if (bufpool[block] == null)
        {

            if (block < numbuffs)
            {
                bufpool[block] =
                    new Buffer(block * buffsize, buffsize, this, file);
            }
            else
            {

                return;
            }
        }
        bufpool[block].setval(indata, index, recsize);
    }
    // ----------------------------------------------------------
    /**
     * Expand the bufferpool if necessary
     */
    public void expand()
    {
        numbuffs++;
        Buffer[] newpool;
        newpool = new Buffer[numbuffs];
        int i = 0;
        while (i < numbuffs - 1)
        {
            newpool[i] = bufpool[i];
            i++;
        }
        bufpool = newpool;

    }

}
