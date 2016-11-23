/*
 *  Name: Rui Zhang 
 *  Email ruz020@ucsd.edu
 * */
import java.util.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.awt.event.MouseEvent;  
import java.awt.event.MouseMotionListener; 

public class SnakeGame extends JFrame implements Runnable,ActionListener,ChangeListener
{   
	private final int FRAMEWIDTH = 400;
	private final int FRAMEHEIGHT = 400;
	private boolean active = true;  
	private int lowSpeed = 10;
	private int changeSpeed = 20;
	private int highSpeed = 150; 
	private static final int SPEEDDIV=100; 
	private	GraphicsGrid grid;
	private GameGrid mysnake;
	private SnakeMover mover;
	private int scoreInt = 0;
	private int highScoreInt = 0;
	private static int width = 400;
	private static int height = 400;
	private static int pixelSize =10 ;
	private int countMove = 0;

	private JLabel   scoreTitle;
	private	JLabel scoreLabel;
	private	JLabel highScoreTitle;
	private JLabel   highScore;
	private	JLabel gameoverLabel;
	private JButton  newGame;
	private	JButton reset;
	private	JSlider slider;
	private JPanel south = new JPanel();
	private JPanel north = new JPanel();

	public void begin()
	{   
		newGame.addActionListener(this);
		reset.addActionListener(this);
		slider.addChangeListener(this);
	}

	// set layout for the JFrame
	public void addLayout( int width, int height, int pixel, GameGrid snake, GraphicsGrid gr) 
	{
		grid = gr;
		mysnake = snake;

		// build the buttons and the slider needed
		scoreTitle = new JLabel ("Score: ");
		scoreLabel = new JLabel ("".format("%8d",scoreInt));
		highScoreTitle = new JLabel ("High Score: ");
		highScore = new JLabel ("".format("%8d",highScoreInt));
		gameoverLabel = new JLabel(" ");
		newGame = new JButton("New Game");
		reset = new JButton("Reset");
		slider = new JSlider(lowSpeed, highSpeed, changeSpeed);

		setLayout(new BorderLayout());

		Container contentPane = getContentPane();
		contentPane.add(grid, BorderLayout.CENTER);
		contentPane.add(north, BorderLayout.NORTH);
		contentPane.add(south, BorderLayout.SOUTH);

		north.setLayout(new GridLayout(2,3));
		north.add(scoreTitle);
		north.add(scoreLabel);

		north.add(gameoverLabel);
		north.add(highScoreTitle);
		north.add(highScore);


		south.setLayout(new FlowLayout());
		south.add(newGame);
		south.add(reset);
		south.add(slider);

		reset.addActionListener(this);
		slider.addChangeListener(this);
		newGame.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		grid.requestFocusInWindow();


		addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) 
				{
				new Thread(mover).start();
				System.out.println("mouse click");
				}

				@Override
				public void mouseEntered(MouseEvent e) {
				}

				@Override
				public void mouseExited(MouseEvent e) {
				}

				@Override
				public void mousePressed(MouseEvent e) {
				}

				@Override
				public void mouseReleased(MouseEvent e) {
				}

		});
	}

	private void adjustSpeed()
	{
		int speed = slider.getValue();
		int newSpeed = (scoreInt/SPEEDDIV + 1)*lowSpeed; 
		newSpeed = Math.min(newSpeed, highSpeed);
		slider.setValue(Math.max(newSpeed,speed));
	}


	private void setMoverSpeed()
	{
		int speed = slider.getValue();
		System.out.format("Speed Slider: %d\n", speed);
		if (mover != null)
		{
			speed = ((highSpeed - speed)/lowSpeed) + 1;
			mover.setSpeed(2 * speed);
		}
		grid.requestFocusInWindow();
	} 

	public void stateChanged (ChangeEvent evt)
	{
		setMoverSpeed();
	}


	private static void usageMessage()
	{
		System.out.println("width - Integer width of the playing grid in pixels");
		System.out.println("height - Integer height of the playing grid in pixels");
		System.out.println("segmentsize - Integer size of each snake segment in pixels");
        System.out.println("");
    	System.out.println("defaults: width = 400, height = 400, segmentsize = 10");
		System.exit(1);
	}

	public void gameStart(  int r, int c, int pixel)
	{
		Coord head = new Coord (r/pixel/2, 0);
		GraphicsGrid gGrid = new GraphicsGrid( r, c, pixel, head);
		gGrid.setPreferredSize(new Dimension( r,c));
		GameGrid snake = new GameGrid(r/pixel, c/pixel, head, gGrid);
		Coord direction = snake.getDirection();
		if(FRAMEWIDTH > r)
			this.setPreferredSize(new Dimension( FRAMEWIDTH, c+150));
		else
			this.setPreferredSize(new Dimension( r+10, c+150));
		scoreInt = 0;         
		
		mover = new SnakeMover( snake, direction, this);
		addLayout(r, c, pixel, snake, gGrid); 
		gGrid.addKeyListener(mover);
		this.addKeyListener(mover);
		setMoverSpeed();

	}

	public void moveSnake()
	{
		if (++countMove >= 10)
		{
			mysnake.addObstacle();
			countMove = 0;
		}

	}


	public void moveSuccess()
	{     
		if (!active) return;
		moveSnake();
		int oldscore = scoreInt;
		scoreInt += 10;  
		if (scoreInt >= highScoreInt)
			highScoreInt = scoreInt;
		scoreLabel.setText("".format("%8d",scoreInt));
		highScore.setText("".format("%8d",highScoreInt));
		adjustSpeed();
	}

	public void moveFail()
	{
		active = false;
		gameoverLabel.setText("GAME OVER!");
	}



	public void run() {
	}

	public void actionPerformed( ActionEvent event)
	{
		if(event.getSource() == newGame)
		{   south.removeAll();
			north.removeAll();
			this.remove(grid);
			mover.stopMover();
			active = true;
			gameStart(this.getWidth ()-10, this.getHeight()-150, pixelSize);
			grid.repaint();
		}
		if(event.getSource() == reset )
		{
			highScore.setText("".format("%8d",highScoreInt=0));
			scoreLabel.setText("".format("%8d", scoreInt=0));
			highScoreInt = 0;
			scoreInt = 0;
			//if(mover != null)
			mover.stopMover();
			active = false;
			gameoverLabel.setText("GAME OVER!");
			slider.setValue(lowSpeed);
		}

	}	

	// ADD NEW 
	public int getGridWidth()
	{
		return grid.getWidth();
	}

	public int getGridHeight()
	{
		return grid.getHeight();
	}    

	public static void main(String[] a)
	{
		if(  a.length > 0 && a[0].equals("help") ) 
			usageMessage();
		else if ( a.length > 0 && a.length == 3)
		{  
			try
			{	
				width = Integer.parseInt( a[0] );
				height = Integer.parseInt( a[1] );
				pixelSize = Integer.parseInt( a[2] );
			}catch(NumberFormatException e)
			{
				usageMessage();
			}
		}
		else if (a.length > 0 && a.length != 3)
			usageMessage();

		if( (width <= 0 || height <= 0 || pixelSize <= 0) || pixelSize > width || pixelSize > height )
			usageMessage();

		new SnakeGame().gameStart(width, height, pixelSize);
	}

}


// vim: ts=4:sw=4:tw=70

