package cx.mscott.breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import cx.mscott.breakout.objects.Ball;
import cx.mscott.breakout.objects.Bat;
import cx.mscott.breakout.objects.Block;
import cx.mscott.breakout.objects.Bounceable;
import cx.mscott.breakout.objects.InvisibleRectangle;

class GraphicsCanvas extends JPanel implements ActionListener {
	
	private static GraphicsCanvas theInstance;
	
	public static GraphicsCanvas getInstance() {
		if (theInstance == null) {
			theInstance = new GraphicsCanvas();
		}
		return theInstance;
	}
	
	private static final long serialVersionUID = -1659288567154587140L;

	/** Game loop runs at 20ms */
	private final static int GAME_LOOP_SPEED = 20;
	
	/** Game screen width */
	private final static int SCREEN_WIDTH = 800;

	/** Game screen height */
	private final static int SCREEN_HEIGHT = 700;
	
	/** Game area width */
	private final static int GAME_AREA_WIDTH = 750;
	
	/** Game zone border */
	private final static int BORDER = (SCREEN_WIDTH - GAME_AREA_WIDTH) / 2;
	
	/** Header height */
	private final static int HEADER_HEIGHT = 45;
	
	/** Game area height */
	private final static int GAME_AREA_HEIGHT = SCREEN_HEIGHT - HEADER_HEIGHT - BORDER;
	
	/** Game loop timer */
	private Timer timer;
	
	/** Active keys */
	private Set<Integer> keys = new HashSet<Integer>();
	
	/** Font for game title */
	private Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	
	/** Font for score */
	private Font scoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	
	/** Player's bat */
	private Bat bat;
	
	/** Player's ball */
	private Ball ball;
	
	/** Blocks */
	private List<Block> blocks;

	/** Game state */
	private GameState gameState;
	
	/** Game cycle */
	private int gameCycle;
	
	private GraphicsCanvas() {
		gameState = GameState.getInstance();
		bat = new Bat(GAME_AREA_HEIGHT - 30, GAME_AREA_WIDTH);
		bat.setOffset(BORDER, HEADER_HEIGHT);

		ball = new Ball(GAME_AREA_WIDTH, GAME_AREA_HEIGHT);
		ball.setOffset(BORDER, HEADER_HEIGHT);
		ball.setX(bat.getCurrentX() + bat.getWidth() / 2 - Ball.DEFAULT_BALL_SIZE / 2);
		ball.setY(GAME_AREA_HEIGHT - 30 - Bat.BAT_HEIGHT - Ball.DEFAULT_BALL_SIZE - 1);
		ball.setDirection(Math.PI * 1.75);
		
		blocks = new ArrayList<Block>();
		blocks.add(new Block(Block.WIDTH * 0, 30));
		blocks.add(new Block(Block.WIDTH * 1, 30));
		blocks.add(new Block(Block.WIDTH * 2, 30));
		blocks.add(new Block(Block.WIDTH * 3, 30));
		blocks.add(new Block(Block.WIDTH * 4, 30));
		blocks.add(new Block(Block.WIDTH * 5, 30));
		blocks.add(new Block(Block.WIDTH * 6, 30));
		blocks.add(new Block(Block.WIDTH * 7, 30));
		blocks.add(new Block(Block.WIDTH * 8, 30));
		blocks.add(new Block(Block.WIDTH * 9, 30));
		blocks.add(new Block(Block.WIDTH * 10, 30));
		blocks.add(new Block(Block.WIDTH * 11, 30));
		blocks.add(new Block(Block.WIDTH * 12, 30));
		blocks.add(new Block(Block.WIDTH * 13, 30));
		blocks.add(new Block(Block.WIDTH * 14, 30));
		blocks.add(new Block(Block.WIDTH * 0, 60));
		blocks.add(new Block(Block.WIDTH * 1, 60));
		blocks.add(new Block(Block.WIDTH * 2, 60));
		blocks.add(new Block(Block.WIDTH * 3, 60));
		blocks.add(new Block(Block.WIDTH * 4, 60));
		blocks.add(new Block(Block.WIDTH * 5, 60));
		blocks.add(new Block(Block.WIDTH * 6, 60));
		blocks.add(new Block(Block.WIDTH * 7, 60));
		blocks.add(new Block(Block.WIDTH * 8, 60));
		blocks.add(new Block(Block.WIDTH * 9, 60));
		blocks.add(new Block(Block.WIDTH * 10, 60));
		blocks.add(new Block(Block.WIDTH * 11, 60));
		blocks.add(new Block(Block.WIDTH * 12, 60));
		blocks.add(new Block(Block.WIDTH * 13, 60));
		blocks.add(new Block(Block.WIDTH * 14, 60));
		for (Block block : blocks) {
			block.setOffset(BORDER, HEADER_HEIGHT);
		}
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setBackground(Color.black);
		
		// Listen for key presses
		addKeyListener(new KeyAdapter() {
		    public void keyPressed(KeyEvent e) {
		    	keys.add(e.getKeyCode());
		    }
		    
		    public void keyReleased(KeyEvent e) {
		    	keys.remove(e.getKeyCode());
		    }
        });
		
		timer = new Timer(GAME_LOOP_SPEED, this);
		timer.start(); 
	}
	
	/**
	 * Action handler. Usually a game loop timer firing.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		gameCycle++;

		// Look at keys every game cycles
		if (gameCycle % 3 == 0) {
			if (keys.contains(KeyEvent.VK_Q)) {
				repaint(bat.getGraphicsBounds());
				bat.move(-10);
				repaint(bat.getGraphicsBounds());
			}
			if (keys.contains(KeyEvent.VK_P)) {
				repaint(bat.getGraphicsBounds());
				bat.move(10);
				repaint(bat.getGraphicsBounds());
			}
		}
		
		List<Bounceable> rectangles = new ArrayList<Bounceable>();

		// Top of game area
		rectangles.add(new InvisibleRectangle(-100, -100, GAME_AREA_WIDTH + 200, 100));
		// Left of game area
		rectangles.add(new InvisibleRectangle(-100, -100, 100, GAME_AREA_HEIGHT + 200));
		// Right of game area
		rectangles.add(new InvisibleRectangle(GAME_AREA_WIDTH, -100, 100, GAME_AREA_HEIGHT + 200));
		// Bottom of game area
		rectangles.add(new InvisibleRectangle(-100, GAME_AREA_HEIGHT, GAME_AREA_WIDTH + 200, 100));
				
		rectangles.add(bat);
		for (Block block : blocks) {
			if (block.getLives() > 0) {
				rectangles.add(block);
			}
		}
		
		repaint(ball.getGraphicsBounds());
		ball.move(rectangles);
		repaint(ball.getGraphicsBounds());
		for (Block block : blocks) {
			if (block.isGraphicsDirty()) {
				repaint(block.getGraphicsBounds());
			}
		}
	}

	/**
	 * Paint the game screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Header
		g.setColor(Color.WHITE);
		g.setFont(scoreFont);
		g.drawString("Score: " + gameState.getScore(), 10, 35);
		g.drawString("Lives: " + gameState.getLives(), SCREEN_WIDTH - 130, 35);
		g.setFont(titleFont);
		g.drawString("BREAKOUT!", SCREEN_WIDTH / 2 - 118, 35);
        g.setColor(Color.GREEN);
        g.drawRect(BORDER-1, HEADER_HEIGHT-1, GAME_AREA_WIDTH + 1, GAME_AREA_HEIGHT + 1);
        g.setColor(Color.WHITE);
        g.fillRect(BORDER, HEADER_HEIGHT, GAME_AREA_WIDTH, GAME_AREA_HEIGHT);

        // Game area graphics...
        bat.repaint(g);
        ball.repaint(g);
        for (Block block : blocks) {
        	block.repaint(g);
        }
	}
	
	/** Invalidate the score area */
	private void repaintScore() {
		repaint(0, 0, SCREEN_WIDTH / 2 - 118, HEADER_HEIGHT);
	}
	
	/** Invalidate the lives area */
	private void repaintLives() {
		repaint(SCREEN_WIDTH / 2 + 118, 0, SCREEN_WIDTH, HEADER_HEIGHT);
	}
	
	/**
	 * Ensure we can capture keyboard focus.
	 */
	@Override
	public boolean isFocusable() {
		return true;
	}

	/**
	 * Define size of game window.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);
	}
}
