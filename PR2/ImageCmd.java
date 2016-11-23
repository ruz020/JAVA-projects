import java.util.Scanner;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import java.lang.Character;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ImageCmd{

	public static void main(String[] args) {
		boolean run = true; 
		boolean ImageExist = false;
		Scanner scan = new Scanner(System.in);
		BufferedImage img = null;
		ArrayList<ImageRecord> al = new ArrayList<ImageRecord>();
		
		while (run)
		{		
			System.out.print(">");
			String input = scan.nextLine();
			String [] command = input.split("\\s+");
			int l = command.length;
			String filename;

			if( l == 2 && command[0].toLowerCase().equals("load") )
			{
				ImageExist = true;
				filename = command[1];
				try 
				{
					img = ImageIO.read(new File(filename));
				}
				catch (IOException e) 
				{
					System.exit(-1);
				}

				Image11 myImage = new Image11(img);
				DisplayImage myDisplay = new DisplayImage(img);
				ImageRecord myRecord = new ImageRecord(command[1],myDisplay,myImage);

				al.add(myRecord);
				System.out.println("OK");
			}
			else if (command[0].toLowerCase().equals("exit"))
			{
				System.out.println("OK");
				System.exit(-1);
			}
			else if( l == 1 && command[0].toLowerCase().equals("list") )
			{
				for (int i = 0; i < al.size();i++)
				{
					System.out.println(i + ": " + ((al.get(i)).filename));
				}
				System.out.println("OK");
			}
			
			else if( ImageExist && l == 1 && command[0].toLowerCase().equals("current") )
			{
				System.out.println(al.size() - 1);
				System.out.println("OK");
			}
			else if( ImageExist && l == 1 && command[0].toLowerCase().equals("size") )
			{
				System.out.println(al.get(al.size() - 1).image.getSize()[0] + " " + al.get(al.size() - 1).image.getSize()[1]);
				System.out.println("OK");
			}
			else if( ImageExist && l == 1 && command[0].toLowerCase().equals("display") )
			{
				al.get(al.size() - 1).window.display();
				System.out.println("OK");
			}
			
			else if( ImageExist && l == 1 && command[0].toLowerCase().equals("close") )
			{
				al.get(al.size() - 1).window.closeWindow();
				System.out.println("OK");
			}
			
			else if( ImageExist && l == 1 && command[0].toLowerCase().equals("negative") )
			{
				al.get(al.size() - 1).image.negative();
				System.out.println("OK");
			}
			
			else if( ImageExist && l == 1 && command[0].toLowerCase().equals("restore") )
			{
				al.get(al.size() - 1).image.restoreOriginal();
				System.out.println("OK");
			}
			
			else if (!command[0].toLowerCase().equals("load") && l >=2)
			{
				boolean invalidValue = false;
				for (int i = 1; i < l; i++)
				{	
					for (int j = command[i].length();--j>=0;){    
					   if (!Character.isDigit(command[i].charAt(j)))
					   {  
						    System.out.println("BAD INPUT");
							invalidValue = true;
							break;
					   }  
					}  
					
				}
				
				if (invalidValue) {}
				else if( ImageExist && l == 5 && command[0].toLowerCase().equals("negative") )
				{
					
					if (true == al.get(al.size() - 1).image.negative(Integer.parseInt(command[1]),Integer.parseInt(command[2]),Integer.parseInt(command[3]),Integer.parseInt(command[4])))
					    {
					    	al.get(al.size() - 1).image.negative(Integer.parseInt(command[1]),Integer.parseInt(command[2]),Integer.parseInt(command[3]),Integer.parseInt(command[4]));
					    	System.out.println("OK");
					    }
					if (false == al.get(al.size() - 1).image.negative(Integer.parseInt(command[1]),Integer.parseInt(command[2]),Integer.parseInt(command[3]),Integer.parseInt(command[4])))
						{
							System.out.println("BAD INPUT");
						}
				}

				else if( ImageExist && l == 2 && command[0].toLowerCase().equals("close") )
				{
					al.get(Integer.parseInt(command[1])).window.closeWindow();
					System.out.println("OK");
				}

				else if( ImageExist && l == 2 && command[0].toLowerCase().equals("display") )
				{
					al.get(Integer.parseInt(command[1])).window.display();
					System.out.println("OK");
				}

				else if( ImageExist && l == 2 && command[0].toLowerCase().equals("switch") )
				{
					if(Integer.parseInt(command[1]) < al.size())
					{
					   al.get(al.size() - 1).image = al.get(Integer.parseInt(command[1])).image;
					   System.out.println("OK");
					}
					else System.out.println("BAD INPUT");
				}
			}
			else  System.out.println("BAD INPUT");
		
		}

	}	
}

class ImageRecord {
	String filename;
	DisplayImage window;
	Image11 image;

	public ImageRecord (String f, DisplayImage win, Image11 img){
		filename = new String(f);
		window = win;
		image = img;
	}
}
