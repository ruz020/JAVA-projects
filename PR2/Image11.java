/* Support some simple manipulation of a BufferedImage
 * Keep track of initial color values of an image
 * Compute color negatives of rectangular subregions
 * Restore image to its initial value
 * provide array of rgb values of the current state
 * return size of image
 */
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import java.awt.image.BufferedImage; 

public class Image11 
{
	private BufferedImage myImage;
	int [][]rgb_array; 


	public Image11(BufferedImage img)  
	{
		myImage = img;
		rgb_array = this.getPixels(); 
	}


	public int [][] getPixels()
	{
		int i,j;
		int width = myImage.getWidth(); 
		int height = myImage.getHeight();
		int[][] rgbArray = new int[width][height]; 
		for (i = 0; i < width; i++){
			for (j = 0; j < height; j++){
				rgbArray[i][j] = myImage.getRGB(i,j);
			}
		}
		return rgbArray;
	}


	public int [][] getInitialPixels()
	{
		return rgb_array;
	}
	

	public boolean negative(int x, int y, int width, int height)
	{
		// find condition that will return false 
		if( x < 0 || y <0 || x >= myImage.getWidth() || y >= myImage.getHeight()|| width+x > myImage.getWidth() || height+y > myImage.getHeight() )
			return false;
		for (int i = x; i < x + width; i++){
			for (int j = y; j < y + height; j++){
				int rgb = myImage.getRGB(i,j);
				int nrgb = (0x00FFFFFF - (rgb | 0xFF000000)) | (rgb & 0xFF000000);
				myImage.setRGB(i,j,nrgb);
			}
		}
		return true; 
	}

	/** compute the color negative of the whole current image state
	 * @return true if successful, false otherwise
	 */
	public boolean negative()
	{
	 	for (int i = 0 ;i< myImage.getWidth() ; i++)
		{
			for(int j = 0; j < myImage.getHeight() ; j++)
			{
			int rgb = myImage.getRGB(i,j); // retrieve the pixel at i,
			int nRGB = (0x00FFFFFF - (rgb | 0xFF000000)) |(rgb & 0xFF000000); // negative of rgb
			myImage.setRGB(i,j,nRGB);
			}
			
		}
		return true; 
	}
	
	/** Restore the current state of the image back to its orginal 
	 *  state
	 */
	public void restoreOriginal()
	{
		int width = myImage.getWidth();
		int height = myImage.getHeight();
		for (int i = 0; i < width; i++){
			for (int j = 0; j < height; j++){
				myImage.setRGB(i,j,rgb_array[i][j]);
			}
		}
		
	}

	/** Return the width and height of the image 
	 *  @return array where index=0 is width, index=1 height 
	 */
	public int [] getSize()
	{
		int width = myImage.getWidth();
		int height = myImage.getHeight();
		int[] size_rray = new int[2];  
		size_rray[0] = width;
		size_rray[1] = height;
		return (int []) size_rray;
	}
}
// vim: ts=4:sw=4:tw=78
