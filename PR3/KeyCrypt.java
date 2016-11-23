/** A class that encrypts/decrypts
 * @author Rui Zhang
 * @Emial  ruz020@eng.ucsd.edu
 */
import java.io.IOException;
import java.io.*;

public class KeyCrypt extends CryptStream{

	byte key_byte = 0;

	KeyCrypt (StreamPair theStreams, String Key){

		super(theStreams);
		byte [] key_byte_array = Key.getBytes();

		for (int i = 0; i<key_byte_array.length;i++)
			key_byte += key_byte_array[i];
	}

	protected byte [] cryptData( byte [] data, int len){
		
		byte [] key_encrypted = new byte[len];
		for (int i = 0; i < len; i++)
		    key_encrypted[i] = (byte)(data[i]^key_byte);
		
		return key_encrypted;
	}

	protected byte [] decryptData( byte [] data,int len){
		
		byte [] key_decrypted = new byte[len];
		for (int i = 0; i < len; i++)
		    key_decrypted[i] = (byte)(data[i]^key_byte);
		
		return key_decrypted;
	}
}