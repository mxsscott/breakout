package cx.mscott.breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;

import cx.mscott.breakout.objects.Ball;
import cx.mscott.breakout.objects.Bat;

public class GraphicsCanvas extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1659288567154587140L;

	/** Game loop runs at 20ms */
	private final static int GAME_LOOP_SPEED = 20;
	
	/** Game screen width */
	private final static int SCREEN_WIDTH = 800;

	/** Game screen height */
	private final static int SCREEN_HEIGHT = 700;
	
	/** Header height */
	private final static int HEADER_HEIGHT = 45;
	
	/** Game zone border */
	private final static int BORDER = 5;
	
	/** Game area width */
	private final static int GAME_AREA_WIDTH = SCREEN_WIDTH - BORDER * 2;
	
	/** Game area height */
	private final static int GAME_AREA_HEIGHT = SCREEN_HEIGHT - HEADER_HEIGHT - BORDER;
	
	/** Game loop timer */
	private Timer timer;
	
	/** Active keys */
	private Set<Integer> keys = new HashSet<Integer>();
	
	/** Game state */
	private GameState gameState;

	/** Font for game title */
	private Font titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	
	/** Font for score */
	private Font scoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	
	/** Player's bat */
	private Bat bat;
	
	/** Player's ball */
	private Ball ball;

	/** Game cycle */
	private int gameCycle;
	
	public GraphicsCanvas() {
		gameState = new GameState();
		bat = new Bat(GAME_AREA_HEIGHT - 30, GAME_AREA_WIDTH, BORDER, HEADER_HEIGHT);
		ball = new Ball(GAME_AREA_WIDTH, GAME_AREA_HEIGHT, BORDER, HEADER_HEIGHT);
		ball.setX(bat.getCurrentX() + bat.getWidth() / 2 - ball.BALL_SIZE / 2);
		ball.setY(GAME_AREA_HEIGHT - 30 - bat.BAT_HEIGHT - ball.BALL_SIZE - 1);
		
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
				repaint(bat.getBounds());
				bat.move(-10);
				repaint(bat.getBounds());
			}
			if (keys.contains(KeyEvent.VK_P)) {
				repaint(bat.getBounds());
				bat.move(10);
				repaint(bat.getBounds());
			}
		}
		
		repaint(ball.getBounds());
		ball.move(bat);
		repaint(ball.getBounds());
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
