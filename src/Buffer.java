//import java.util.Arrays;
import java.io.IOException;
import java.io.RandomAccessFile;
// -------------------------------------------------------------------------
/**
 *  A buffer that stores blocks
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class Buffer implements BufferADT
{
    private int loc;
    private int size;
    private byte[] data;
    private boolean dirtybit;
    private boolean load;
    private BufferPool pool;
    private RandomAccessFile file;

    // ----------------------------------------------------------
    /**
     * Create a new Buffer object.
     * @param loc location of buffer
     * @param size of buffer
     * @param parent parent pool
     * @param file where data is stored
     */
    public Buffer(int loc, int size, BufferPool parent, RandomAccessFile file)
    {
        this.loc = loc;
        this.size = size;
        pool = parent;
        this.file = file;
        dirtybit = false;
        load = false;

    }
    /**
     * get the data read from the buffer
     * @return data
     */
    public byte[] read()
    {

        pool.used(this);
        if (!load)
        {
            getDiskData();
            Statistics.diskreads++;
        }
        else
        {
            Statistics.cachehits++;
        }

        return data.clone();
    }
    /**
     * Write data to disk
     * @param data2 to write
     */
    public void write(byte[] data2)
    {
        pool.used(this);
        this.data = data2.clone();
        dirtybit = true;
        load = true;

    }
    /**
     * Clear the buffer and write to disk
     */
    public void flush()
    {
        if (dirtybit)
        {
            writeDiskData();
        }


        load = false;
        data = null;
    }
    /**
     * Return size of buffer
     * @return size
     */
    public int size()
    {
        return size;
    }

    /**
     * load data from the disk
     */
    private void getDiskData()
    {
        try
        {
            data = new byte[size];
            file.seek(loc);
            file.read(data);
        }
        catch (IOException e)
        {
            //something wrong with the file
            e.printStackTrace();
        }
        dirtybit = false;
        load = true;

    }
    /**
     * Write new data to the disk
     */
    private void writeDiskData()
    {
        try
        {
            file.seek(loc);
            file.write(data);
            Statistics.diswrites++;
        }
        catch (IOException e)
        {
          //something wrong with the file
            e.printStackTrace();
        }
        dirtybit = false;

    }
    // ----------------------------------------------------------
    /**
     * get a value from the data in this buffer
     * @param index position of the data
     * @param length how much data to read
     * @return a byte array of the data
     */
    public byte[] getval(int index, int length)
    {

        pool.used(this);
        if (!load)
        {
            getDiskData();
            Statistics.diskreads++;
        }
        else
        {
            Statistics.cachehits++;
        }
        //System.out.println(load);
        //return Arrays.copyOfRange( data, index, index + 2);
        byte[] value = new byte[length];
        int i = 0;
        while (i < length)
        {
            value[i] = data[index + i];
            i++;
        }
        //value[0] = data[index];
        //System.out.println(index + 1);
        //value[1] = data[index + 1];
        //System.out.println(index);
        return value;
    }
    // ----------------------------------------------------------
    /**
     * change data in the array
     * @param indata input data
     * @param index position of input data
     * @param length length of input data
     */
    public void setval(byte[] indata, int index, int length)
    {
        pool.used(this);
        if (!load)
        {
            getDiskData();
            Statistics.diskreads++;
        }
        else
        {
            Statistics.cachehits++;
        }
        int i = 0;
        while (i < length)
        {
            data[index + i] = indata[i];
            i++;
        }
        dirtybit = true;
    }

}
