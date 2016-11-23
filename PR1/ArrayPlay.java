/****************************************************
 * @name:   Rui Zhang
 * @email:  ruz020@eng.ucsd.edu
 ****************************************************/
import java.util.Scanner;
public class ArrayPlay
{
	public static void main(String[] args) {
		int size = 0;
		boolean run = true; 
		boolean arrayExist = false;
		IntArray11 myarray = null;
		Scanner scan = new Scanner(System.in);
		while (run)
		{
			System.out.print(">");
			String input = scan.nextLine();
			String [] command = input.split("\\s+");
			int l = command.length;
			if( command[0].toLowerCase().equals("new") )
			{
				arrayExist = true;
				if(l == 1) // new
				{
					myarray = new IntArray11();
					System.out.println("OK");
				}
				else if (l == 2 && Integer.parseInt(command[1]) >= 0 )
				{
					size = Integer.parseInt(command[1]);
					myarray = new IntArray11(size);
					System.out.println("OK");
				}
				else if(l > 2) // new e1 e2 e3......
				{
					int[] inputArray = new int[l - 1];
					for (int i = 0;i< l - 1;i++) inputArray[i] = Integer.parseInt(command[i + 1]);
					myarray = new IntArray11(inputArray);	
					System.out.println("OK");
				}
				else System.out.println("BAD INPUT");
			}
			else if (command[0].toLowerCase().equals("exit")) // exit 
			{
				return ;
			}
			else if( arrayExist && command[0].toLowerCase().equals("print")) {
				String printArray = "";
				printArray = myarray.toString();
				if (l == 1) {
					System.out.println(printArray);
					System.out.println("OK");
				}
				else if (l == 2 && Integer.parseInt(command[1]) >= 0 && Integer.parseInt(command[1]) < myarray.getNelem()) {
					int index = Integer.parseInt(command[1]);
					int element = myarray.getElement(index);
					System.out.println( element );
					System.out.println("OK");
				}
				else System.out.println("BAD INPUT");
			}
			else if(arrayExist && command[0].toLowerCase().equals("delete") && l == 2)
			{
				int index = Integer.parseInt(command[1]);
				if (index >=0 && index < myarray.getNelem()){
					myarray.delete(index);
					System.out.println("OK");
				}
				else System.out.println("BAD INPUT");				
			}
			else if (arrayExist && command[0].toLowerCase().equals("insert") && l == 3 )
			{
				if(Integer.parseInt(command[1]) >= 0 && Integer.parseInt(command[1]) <= myarray.getNelem())
				{
					int index = Integer.parseInt(command[1]);
					int element = Integer.parseInt(command[2]);
					myarray.insert(index,element);
					System.out.println("OK");
				}
				else System.out.println("BAD INPUT");
			}
			else if(arrayExist && command[0].toLowerCase().equals("reverse") && l == 1)
			{
				myarray.reverse();
				System.out.println("OK");
			}
			else if (arrayExist && command[0].toLowerCase().equals("reverse") && l == 3 && Integer.parseInt(command[1]) >= 0 && Integer.parseInt(command[1]) < myarray.getNelem() && Integer.parseInt(command[2]) >= 0 && Integer.parseInt(command[2]) < myarray.getNelem()) {
				int start = Integer.parseInt(command[1]);
				int end = Integer.parseInt(command[2]);
				myarray.reverse(start,end);
				System.out.println("OK");
			}
			else if (arrayExist && command[0].toLowerCase().equals("set") && l == 3 && Integer.parseInt(command[1]) >= 0 && Integer.parseInt(command[1]) < myarray.getNelem() ){
				int index = Integer.parseInt(command[1]);
				int element = Integer.parseInt(command[2]);
				myarray.setElement(index,element);
				System.out.println("OK");
			}
			else if (arrayExist && command[0].toLowerCase().equals("size") && l == 1)
			{
				System.out.println(myarray.getNelem());
				System.out.println("OK");
			}
			else if (arrayExist && command[0].toLowerCase().equals("swap") && l == 3 && Integer.parseInt(command[1]) >= 0 && Integer.parseInt(command[1]) < myarray.getNelem() && Integer.parseInt(command[2]) >= 0 && Integer.parseInt(command[2]) < myarray.getNelem()){
				int index1 = Integer.parseInt(command[1]);
				int index2 = Integer.parseInt(command[2]);
				myarray.swap(index1,index2);
				System.out.println("OK");
			}
			else System.out.println("BAD INPUT");		
		}		
	}
}
