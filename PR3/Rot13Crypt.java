/** A class that encrypts/decrypts
 * @author Rui Zhang
 * @Emial  ruz020@eng.ucsd.edu
 */
import java.io.IOException;
import java.io.*;

public class Rot13Crypt extends CryptStream{

	// call the constructor of its superclass
	public Rot13Crypt(StreamPair theStreams)
	{
		super(theStreams);
	}

	//implement the abstract function
	protected byte [] cryptData( byte [] data, int len){

		byte [] rot13_encrypted = new byte [len];
		for(int i=0;i<len;i++)
		{
			rot13_encrypted[i] = ((byte)((data[i] + (byte)13)%256));
		}
		
		return rot13_encrypted;
	}

	protected byte [] decryptData( byte [] data,int len){
		
		byte [] rot13_decrypted = new byte [len];

		for(int i=0;i<len;i++)
		{
			rot13_decrypted[i] = (byte)(data[i] - (byte)13 + (byte)256);
		}
		
		return rot13_decrypted;
	}
}