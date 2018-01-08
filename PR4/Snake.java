/* 
 *  Name: Rui Zhang 
 *  Email ruz020@ucsd.edu
 * */
import java.util.*;

public class Snake{

	private Coord direction = new Coord (0,1); 
	private ArrayList<Coord> snakeArray  = new ArrayList<Coord> ();
	private int snakeLength = 1;
	private Coord head;

	/* constructor 
	 * set the initial head coordinate
	 * set the snakeArray with only one head
	 */ 
	public Snake ( Coord initial, int maxlength)
	{
		head = new Coord (initial);
		snakeArray.add(head);		
	}


	public int getLength()
	{
		return snakeLength;	
	}

	public Coord getHead()
	{
		return head;
	}

	public Coord getTail()
	{
		return snakeArray.get(snakeArray.size()-1);
	}


	/* Move function: check if the action now is move.
	 * If so, move the snake(body and head)
	 */ 
	public boolean move ( int x, int y)
	{ 

		if(direction.getR() != x && direction.getC()!= y)
			return false;

		for(int i=snakeArray.size()-1 ; i >0; i--)
		{              
			snakeArray.set( i, snakeArray.get(i-1) );
		}

		snakeArray.set( 0, new Coord (snakeArray.get(0).getR()+ x,snakeArray.get(0).getC()+ y) ); 
		head = snakeArray.get(0);

		return true;
	}


	/* Grow function: check if the action now is grow.
	 * If so, grow the snake
	 */ 
	public boolean grow( int x, int y)
	{ 
		if(!( ( x== 1 && y == 0 ) || (x == 0 && y == 1 ) || ( x== -1 && y == 0 ) || (x == 0 && y == -1 ) ) || (direction.getR() == Math.abs(x)  && direction.getC() == Math.abs(y)))
			return false;

		direction = new Coord (x, y);
		snakeArray.add(1, snakeArray.get(0));
		snakeArray.set(0,  new Coord (snakeArray.get(0).getR() + x,
		snakeArray.get(0).getC() + y) );
		head = snakeArray.get(0);
		snakeLength++;

		return true;

	}

	/* Intersect function: check if the input coordinate intersect with the snake.
	*/ 
	public boolean intersects (Coord w, boolean checkHead)
	{
		int i = 0;
		if (! checkHead) i = 1;
		for( ; i < snakeArray.size(); i++ )
		{
			if (w.equals( snakeArray.get(i))) return true; 
		}
		return false; 
	}

	public ArrayList<Coord> getSegments()
	{
		return snakeArray;
	}
}
