import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import student.TestCase;


// -------------------------------------------------------------------------
/**
 *  Test the quicksort function
 *
 *  @author Ryan Souther ryan1992
 *  @version Nov 6, 2014
 */
public class QuickSortTest extends TestCase
{

    // ----------------------------------------------------------
    /**
     * Testing the main function.  However we can only test for so much
     */
    public void testMain()
    {
        InputStream instream = null;
        OutputStream outstream = null;

        try
        {
            File afile = new File("testfiles/testfile.txt");
            File bfile = new File("testfiles/testfilecopy.txt");
            instream = new FileInputStream(afile);
            outstream = new FileOutputStream(bfile);
            byte[] buffer = new byte[1024];
            int length;
            // copy the file content in bytes
            while ((length = instream.read(buffer)) > 0)
            {

                outstream.write(buffer, 0, length);

            }

            instream.close();
            outstream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        String[] args = new String[3];
        args[0] = "testfiles/testfilecopy.txt";
        args[1] = "1";
        args[2] = "testfiles/output.txt";
        Quicksort.main(args);
        assertTrue(systemOut().getHistory().contains("Time to run: "));

    }

}
