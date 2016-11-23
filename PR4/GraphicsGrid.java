/*  FileName SnakeMover.java
 *
 *  Name: Rui Zhang 
 *  Email ruz020@ucsd.edu
 * */

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

public class GraphicsGrid extends JPanel
{
	private ArrayList<Coord> trackSnake =  new ArrayList<Coord> ();
	private ArrayList<Coord> trackObs = new ArrayList<Coord>();
	private int width = 400;
	private int height = 400;
	private int pixels =10;  
	private Coord head;
	private ArrayList<Coord> emptyArray = new ArrayList<Coord> ();

	public GraphicsGrid( int wid, int hei, int pix, Coord h) 
	{       
		width = wid;
		height = hei;
		pixels = pix;
		head = h; //head of the snake
		trackSnake.add(head);
	}

	public GraphicsGrid(){};	

	@Override
	protected synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		int frameWidth = this.getWidth();
		int frameHeight = this.getHeight();
		int widthInt = width/pixels*pixels;
		int heightInt = height/pixels*pixels;
		// the empty edge part 
		int relWidth = (frameWidth - widthInt)/2;
		int relHeight = (frameHeight - heightInt)/2;
		try
		{
	        for (int i = 1; i< trackSnake.size(); i++) {       
				Coord coord = trackSnake.get(i);
				int cellX = (coord.getR() * pixels) + relWidth;
				int cellY = (coord.getC() * pixels) + relHeight;
				g.setColor(Color.GREEN);
				g.fillRect(cellX, cellY, pixels, pixels);
	  	     }   
			int cellR = (trackSnake.get(0).getR() * pixels) + relWidth;
			int cellC = (trackSnake.get(0).getC() * pixels) + relHeight;			
			g.setColor(Color.RED);
			g.fillRect(cellR, cellC, pixels, pixels);

			for (Coord coord : trackObs) 
			{
				int cellX = (coord.getR() * pixels) + relWidth;
				int cellY = (coord.getC() * pixels) + relHeight;
				g.setColor(Color.BLUE);
				g.fillRect(cellX, cellY, pixels, pixels);
			}
		} catch(Exception e ){}

		g.setColor(Color.BLACK);
		g.drawRect(relWidth, relHeight, pixels*(int)Math.floor(this.width/this.pixels), pixels*(int)Math.floor(this.height/this.pixels));

		for (int i = 0; i < pixels*(int)Math.floor(this.width/this.pixels); i += pixels) 
		{
		   g.drawLine(i + relWidth, relHeight, i + relWidth, 
           pixels*(int)Math.floor(this.height/this.pixels) + relHeight);
		}

		for (int i = 0; i < pixels*(int)Math.floor(this.height/this.pixels); i += pixels) 
		{
		   g.drawLine( relWidth, i + relHeight, 
           pixels*(int)Math.floor(this.width/this.pixels) + relWidth,
           i + relHeight);
		}

	}

	public synchronized void fillCell(ArrayList<Coord> sarray, ArrayList<Coord> obs)
	{
		trackSnake = sarray;
		trackObs = obs;
		repaint();
	}

	public synchronized void clearCell() 
	{         
		trackSnake = emptyArray;
		repaint();
	}

}
