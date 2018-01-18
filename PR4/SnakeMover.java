/*
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

public class SnakeMover implements Runnable, KeyListener
{

	private int timeCount = 0;
	private static final int MINTICK = 1; //fast
	private static final int MAXTICK = 20;//slow
	private int ticks = MAXTICK; 
	private GameGrid grid;
	private Coord direction;
	private boolean moveOK = true;
	private SnakeGame sGame;

	public SnakeMover(GameGrid theGrid, Coord initial,SnakeGame game)
	{   
		System.out.println("SnakeMover in");
		direction = initial;
		grid = theGrid;
		sGame = game;
		System.out.println("snakeMover direction"+ direction.getR() +" "+ direction.getC());
	}

	public void stopMover()
	{
		moveOK = false;  
		System.out.println("snakeMover false");
	}	


	public synchronized void run()
	{        

		while (moveOK)
		{
			if (timeCount++ >= ticks)
			{       
				moveOK = grid.moveSnake(direction.getR(), direction.getC());	
				if (moveOK) sGame.moveSnake();
				timeCount = 0; 
			}
			try { TimeUnit.MILLISECONDS.sleep(50);}
			catch(InterruptedException e){};

		}
		sGame.moveFail();
	}

   
	public void setSpeed(int tickSpeed)
	{
		// control speed
		if (tickSpeed < MINTICK ) tickSpeed = MINTICK;
		if (tickSpeed > MAXTICK) tickSpeed = MAXTICK;	
		ticks = tickSpeed;
	}


	// KeyListener Interface
	public void keyPressed(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{       
		Coord nd = direction;
		char cmd = e.getKeyChar();
		if (!moveOK) return; // don't move
	
	        if ( cmd == 'j')
		{  
		    if(direction.getR() == 0)
		    {
			if (grid.moveSnake(direction.getC(),direction.getR())) 
			{
				grid.drawGrid(); 
				moveOK = true;
			}
			else
				moveOK = false;
	   	    }
		    else if(direction.getC() == 0)
	            {
			if(grid.moveSnake(direction.getC(), 0-direction.getR()))
			{
				grid.drawGrid(); 			
				moveOK = true;
			}
			else 
				moveOK = false;
	     	    }
		}
		else if ( cmd == 'l')
		{  
	 	    if(direction.getR() == 0)
		    { 
			if(grid.moveSnake(0-direction.getC(),direction.getR()))
			{	
				grid.drawGrid(); 
				moveOK = true;
			}
			else
				moveOK = false;
		    }  
	  	    else if(direction.getC() == 0){
			if(grid.moveSnake(direction.getC(),direction.getR()))
			{ 
				grid.drawGrid();
				moveOK =true;   
			}
			else
				moveOK = false;
		     }
		}
	
		direction = grid.getDirection();
		timeCount = 0;
		if(moveOK) sGame.moveSuccess();
	}
} 

