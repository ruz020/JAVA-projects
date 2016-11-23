/*
 *  FileName Coord.java
 *
 *  Name: Rui Zhang 
 *  Email ruz020@ucsd.edu
 * */

import java.awt.*;
public class Coord extends Point{
	private int row;
	private int column;
	private Point p;

	 
	public Coord(int r, int c) 
	{   
		row = r;
		column = c;
		p = new Point(r,c);
	}

	public Coord( Coord initial)
	{
		row = initial.getR();
		column = initial.getC();
		p = new Point(initial.getPoint());
	}

	/* Getpoint function: get the point coordinate
	 * return type: Point	 
	 */	
	public Point getPoint()
	{
		return p;
	} 

	public int getR()
	{
		return row;
	}

	public int getC()
	{
		return column;
	}

	/* Equals function: check if two coordinate are the same
	 * return type: boolean 
	 */	
	public boolean equals(Coord other)
	{
		if(this.row == other.getR() && this.column == other.getC())
			return true;
		else 
			return false;
	}

}
