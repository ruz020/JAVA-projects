/** A class that encrypts/decrypts
 * @author Rui Zhang
 * Emial  ruz020@eng.ucsd.edu
 */
import java.io.IOException;
import java.io.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public abstract class CryptStream
{

	private StreamPair streams;
	private OutputStream fileout;
	private InputStream  filein;

	public CryptStream(StreamPair theStreams)
	{
		streams = theStreams;
	}

	/** Encrypt data in the byte array
	 * @param data - the data to encrypt
	 * @param len - how many bytes in the array to encrypt
	 * @return a byte array with data encrypted. length may not be equal to
	 * input
	 */
	abstract protected byte [] cryptData( byte [] data, int len); 

	/** Decrypt data in the byte array
	 * @param data - the data to decrypt
	 * @param len - how many bytes in the array to decrypt
	 * @return a byte array with data decrypted. length may not be equal to
	 * input
	 */
	abstract protected byte [] decryptData( byte [] data,int len);

	/** encrypt the input stream, and write to the output stream of 
     * of the StreamPair 
	*  @return number of bytes in output stream
	*/
	public final int encrypt()
	{
		InputStream is = streams.getInput();

		try {
			int counter = 0;
			while (counter ==0){
				counter = is.available();
			}
			byte [] dataToEncrypt = new byte[counter];
			is.read(dataToEncrypt);

			// begin encrypt
			byte [] encryptedData = cryptData(dataToEncrypt, dataToEncrypt.length);

			try {

				streams.getOutput().write(encryptedData, 0, encryptedData.length);
			}

			catch(IOException e){
			}
			
			return encryptedData.length;
		}
		catch (IOException e){
			return 0;		
		}		
	}


	/** decrypt the input stream, and write to the output stream of 
     * of the StreamPair 
	*  @return number of bytes in output stream
	*/
	public final int decrypt()
	{
		InputStream is = streams.getInput();

		try {
			int counter = 0;
			while (counter ==0){
				counter = is.available();
			}
			byte [] DataToDecrypt = new byte[counter];
			is.read(DataToDecrypt);

			// begin decrypt
			byte [] decryptedData;
			decryptedData = decryptData(DataToDecrypt, DataToDecrypt.length);
			
			try {

				 streams.getOutput().write(decryptedData, 0, decryptedData.length);
			}

			catch(IOException e){
			}
			
			return decryptedData.length;
		}
		

		catch (IOException e){
			return 0;		
		}
		
		
	}
}
// vim: ts=4:sw=4:tw=78
