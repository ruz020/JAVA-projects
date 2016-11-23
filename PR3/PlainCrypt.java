/** A class that encrypts/decrypts
 * @author Rui Zhang
 * @Emial  ruz020@eng.ucsd.edu
 */
public class PlainCrypt extends CryptStream{

	public PlainCrypt(StreamPair theStreams)
	{
		super(theStreams);
	}

	protected byte [] cryptData( byte [] data, int len){
		return data;
	}

	protected byte [] decryptData( byte [] data,int len){
		return data;
	}
}