/*
 *  FileName GameGrid.java
 *
 *  Name: Rui Zhang 
 *  Email ruz020@ucsd.edu
 * */

import java.util.*;

public class GameGrid{

	private int row;
	private int column;
	private char gridArray[][];
	private ArrayList<Coord> obsArray = new ArrayList<Coord> ();
	private GraphicsGrid canvas;
	private Coord head;
	private Coord direction = new Coord (0,1);
	private Snake snake;
	private Random myRandom;

	public GameGrid(int r, int c,Coord initial , GraphicsGrid aCanvas)
	{
		row = r;
		column = c;
		canvas = aCanvas;
		head = initial;
		gridArray = new char[r][c];
		snake = new Snake(initial,r*c); 
		myRandom = new Random();
	}

	public boolean moveSnake( int x, int y)
	{       
		if(this.gameOver()){ 
			System.out.println("GameOver"); 
			return false;
		}
		boolean find = false;
		ArrayList<Coord> tempCoordArray = new ArrayList<Coord>();
		for(int i =0; i<getSegments().size();i++)
		{
			tempCoordArray.add( snake.getSegments().get(i));
		}

		// if direction is same as before, it is move
		if(direction.getR() == x && direction.getC() == y)
		{  
			snake.move(x,y);
			head = snake.getHead();
			find = true;
		}

		// if the new direction is vertical with the present direction, it is grow
		else if( ( Math.abs (direction.getR())== 1 && direction.getC() == 0) && ( x == 0 && Math.abs(y) == 1) )
		{   
			snake.grow(x,y);
			head = snake.getHead();
			direction = new Coord (x, y);
			find = true;
		}

		// if the new direction is vertical with the present direction, it is grow
		else if (( Math.abs (direction.getC())== 1 && direction.getR() == 0) && ( y == 0 && Math.abs(x) == 1) )
		{       
			snake.grow(x,y);
			head = snake.getHead();
			direction = new Coord (x, y);
			find = true;
		}
		else 
		{  
			find = false;
		}  
		if(!find) return false;
		
		if( this.gameOver())
		{ 
            		canvas.fillCell(tempCoordArray, obsArray);
			return false;
        	}
		else 
		{       
			canvas.fillCell(snake.getSegments(), obsArray);
			return true;
		}

	}

	public void drawGrid() //for testing
	{           

		for(int i= 0 ; i < column ; i++)
		{
			for(int j = 0; j < row; j ++){
				gridArray[j][i]='.';
			}
		}

		ArrayList<Coord> array = snake.getSegments();
		
		for(Coord coord : array)
		{   
			Coord tempC = coord;
			gridArray[tempC.getR()][tempC.getC()] = '#';
		}
		for(Coord coord : obsArray)
		{   
			Coord tempC = coord;
			gridArray[tempC.getR()][tempC.getC()] = '*';
		}

		gridArray[head.getR()][head.getC()] = 'H';

		// print out the image of snake on the screen for testing
		for(int i= 0 ; i < column ; i++)
		{
			for(int j = 0; j < row; j ++)
		        System.out.print(gridArray[j][i]);
			System.out.println("");

		}

		System.out.println("next");
	}

	private boolean gameOver()
	{
		Coord head = snake.getHead();
		if (head.getR() < 0 || head.getR() >= row)
			return true; 
		if (head.getC() < 0 || head.getC() >= column)
			return true;
		if (snake.intersects(head, false))
			return true;

		boolean hitObs = false;
		for(int i =0 ; i < obsArray.size(); i++ )
		{
			if (head.equals( obsArray.get(i)))  hitObs = true;
		}
		if (hitObs) return true;   

		return false; 
	}

	public Coord getDirection()
	{
		return direction;
	}

	public ArrayList<Coord> getSegments()
	{
		return snake.getSegments();
	}

	// add obstacle function
	public void addObstacle()
	{
		int i,j;
		boolean obstacle = false;
		int count = 0;
		int MAX = 50 ;
		do 
		{       
			i = myRandom.nextInt(row);
			j = myRandom.nextInt(column);
			if (gridArray[i][j] == '.')
			{
				obstacle = true;
				obsArray.add( new Coord(i, j));
			}
			count++;
		}
		while ( !obstacle && count < MAX); 
	}

	public ArrayList<Coord> getObsArray()
	{
		return obsArray;
	}

}
