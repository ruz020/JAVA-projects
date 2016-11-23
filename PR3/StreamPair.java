/** A class that encrypts/decrypts
 * @author Rui Zhang
 * @Emial  ruz020@eng.ucsd.edu
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class StreamPair 
{

	InputStream filein;
	OutputStream fileout;


	public StreamPair(String infile, String outfile) throws IOException
	{
		if (infile != "-" && infile != "") {
		  	filein = new FileInputStream(infile);
		}

		else {
			filein = System.in;
		}

	
		if (outfile != "-" && outfile != "") {
			fileout = new FileOutputStream(outfile);
		}
			
		else {
			fileout = System.out;
		}
	}

	public InputStream getInput()
	{
		return filein;
	}

	/** get a reference to this stream pair's output stream
	 * @return  reference to the output stream
	 */
	public OutputStream getOutput()
	{
		return fileout;
	}

	/** close both streams. Should not cause an error if invoked multiple times
	 */
	public void close()
	{
		try
		{
			filein.close();
			fileout.close();
		}
		catch (IOException e)
		{	
		}
	}
}
// vim: ts=4:sw=4:tw=78
