//author:  Rui Zhang
import java.util.Scanner;
public class MatrixExample
{
	public static void main(String args[])
	{
		int rows, cols;
		double matrix[][];
		Scanner parser = new Scanner(System.in);


		System.out.format ("Enter # rows: ");
		rows = parser.nextInt();
		System.out.format ("Enter # cols: ");
		cols = parser.nextInt();

		matrix = new double[rows][cols];
		for (int i = 0; i < rows; i ++)
			for (int j = 0; j < cols; j++)
				matrix[i][j] = Math.random()*1000.00;

		System.out.format("Printing Randomly generated %2d X %2d Matrix\n",
				rows,cols);
		printMatrix(matrix);

		System.out.format("Search for first entry below a threshold\n");
		System.out.format ("Enter Threshold Search Value: ");
		double target = parser.nextDouble();
		int i, j = 0;
		boolean found = false;
		for (i = 0; i < rows; i ++)
		{
			for (j = 0; j < cols; j++)
				if (found = (matrix[i][j] < target)) 
					break;
			if (found) break;
		}

		if ( !(i==rows) ) // Found the first one
			System.out.format("(%d,%d) has value %12.5g\n",i,j,
							matrix[i][j]); 
		else
			System.out.format("No values below Threshold (%12.5g)!\n", target);

	}			
	private static void printMatrix(double [][] mat)
	{
		for (int i = 0; i < mat.length; i ++)
		{
			String row = new String("");
			for (int j = 0; j < mat[i].length; j++)
				row += new String().format("%12.5g  ",mat[i][j]);
			System.out.format("%s\n", row);
		}
	}
}
			
