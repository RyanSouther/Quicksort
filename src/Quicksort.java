import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;


// -------------------------------------------------------------------------
/**
 *  Our main file.  It uses the Quicksort algorithm to sort data
 *  through buffers.
 *
 *  @author Ryan Souther
 *  @version Nov 6, 2014
 */
public class Quicksort
{


    // ----------------------------------------------------------
    /**
     * Our main file that sorts a file and prints stats
     * @param args our 3 inputs
     */
    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.out.println("Wrong number of inputs");

        }

        String file = args[0];
        int buffs = Integer.parseInt(args[1]);
        String output = args[2];
        Statistics.filename = args[0];

        BufferAccess pool = null;
        pool = new BufferAccess(buffs, file);
        long start = System.currentTimeMillis();
        sortIt(pool, 0, pool.size() - 1);
        System.out.println(System.currentTimeMillis() - start);
        pool.flush();
        long end = System.currentTimeMillis();
        end = end - start;
        Statistics.time = end;
        File stats = new File(output);
        try
        {
            stats.createNewFile();
            FileWriter statfile = new FileWriter(stats, true);
            BufferedWriter outwrite = new BufferedWriter(statfile);
            outwrite.write("Sort on " + Statistics.filename);
            outwrite.write("\nCache Hits: " + Statistics.cachehits);
            outwrite.write("\nDisk Reads: " + Statistics.diskreads);
            outwrite.write("\nDisk Writes: " + Statistics.diswrites);
            outwrite.write("\nTime is " + Statistics.time);

            outwrite.close();
        }
        catch (IOException e)
        {
            // Something bad happened
            e.printStackTrace();
        }
        System.out.println("Time to run: " + end);

    }

    /**
     * Sort the data via quicksort
     * @param pool the pool to sort
     * @param l left bound
     * @param r right bound
     */
    private static void sortIt(BufferAccess pool, int l, int r)
    {
        if (l < r)
        {

             //if (r - l <= 8) { insSort(pool, l, r); }

            int index = partition(pool, l, r);
            if (l < index - 1)
            {
                sortIt(pool, l, index - 1);
            }
            if (r > index)
            {
                sortIt(pool, index, r);
            }
        }

    }

    /**
     * Partition the pool to find the partition
     * @param pool to partition
     * @param left bound
     * @param right bound
     * @return The pivot location
     */
    private static int partition(BufferAccess pool, int left, int right)
    {
        int l = left;
        int r = right;
        //int tmp;
        short pivot = pool.get((left + right) / 2);
        while (l <= r)
        {
            while (pool.get(l) < pivot)
            {
                l++;
            }
            while (pool.get(r) > pivot)
            {
                r--;
            }
            if (l <= r)
            {
                //long t1 = System.nanoTime();

                pool.swap(l, r);
                //long t2 = System.nanoTime();
                //System.out.println(t2 - t1);

                l++;

                r--;

            }
        }
        return l;
    }
    /**
    private static void insSort(BufferAccess pool, int left, int right)
    {
        for (int i = left + 1; i < right; i++)
        {
            short val = pool.get(i);
            int j = i - 1;
            while (j >= left && val < pool.get(j))
            {
                pool.swap(i, j);
                j--;
            }
        }
    }*/
}
