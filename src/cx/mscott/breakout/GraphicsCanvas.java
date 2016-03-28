package cx.mscott.breakout;

import java.awt.Color;
import java.awt.Dimension;
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

public class GraphicsCanvas extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1659288567154587140L;

	/** Game loop runs at 20ms */
	private final static int GAME_LOOP_SPEED = 20;
	
	/** Game screen width */
	private final static int SCREEN_WIDTH = 400;

	/** Game screen height */
	private final static int SCREEN_HEIGHT = 400;
	
	/** Game loop timer */
	private Timer timer;
	
	/** Active keys */
	private Set<Integer> keys = new HashSet<Integer>();
	
	public GraphicsCanvas() {
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
		// TODO Auto-generated method stub

	}

	/**
	 * Paint the game screen.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// TODO Game graphics go here...
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
