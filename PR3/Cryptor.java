/** A class that encrypts/decrypts
 * @author Rui Zhang
 * @Emial  ruz020@eng.ucsd.edu
 */
import java.io.*;

public class Cryptor {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		StreamPair mystreampair = null;
		CryptStream mycryptstream = null;
		boolean run = true; 
		boolean e_flag = false;
		boolean d_flag = false;
		int l = args.length;
		String infile = "", outfile = "";
		String algorithm = "plain";
		String key = "";
		CryptStream encryptor;

		if (args.length >0 && args[0].equals("help")){
			System.out.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
			System.out.println("       algorithms: plain rot13 key");
			System.exit(-1);
		}
		
		if (args.length % 2 == 1){
			System.out.println("Invalid: Number of arguments should be even");
			System.out.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
			System.out.println("       algorithms: plain rot13 key");
			System.exit(-1);

		}

		for (int i = 0; i < args.length - 1;i+=2){
			switch(args[i]){
				case "-i":
					infile = args[i + 1];
					break;	
				case "-o":
					outfile = args[i + 1];
					break;
				case "-d":
					algorithm = args[i + 1];
					d_flag = true;
					e_flag = false;
					//e_d_flag = true;
					break;
				case "-e":
					algorithm = args[i + 1];
					e_flag = true;
					d_flag = false;
					break;
				case "-k":
					key = args[i + 1];
					break;
				default:
					System.out.println("Unknown flag " + args[i]);
					System.out.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
					System.out.println("       algorithms: plain rot13 key");
					System.exit(-1);
					break;
			}
		}

		try {

			mystreampair = new StreamPair(infile,outfile);
		}
		catch (Exception e){
			System.out.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
			System.out.println("       algorithms: plain rot13 key");
			System.exit(-1);			
		}
		
		switch(algorithm){
			case "plain":
				mycryptstream = new PlainCrypt(mystreampair);
				break;
			
			case "rot13":
				mycryptstream = new Rot13Crypt(mystreampair);
				break;
			case "key":
			 	mycryptstream = new KeyCrypt(mystreampair, key);
			 	break;
			default:
				System.out.println("Unknown algorithm " + algorithm);
				System.out.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
				System.out.println("       algorithms: plain rot13 key");
				System.exit(-1);
		}
		
		if(e_flag == true && d_flag == false){
			mycryptstream.encrypt();
		}
		if(e_flag == false && d_flag == true){
			mycryptstream.decrypt();
		}

		if(e_flag == false && d_flag == false){
			mycryptstream.encrypt();
		}			
		
	}
}