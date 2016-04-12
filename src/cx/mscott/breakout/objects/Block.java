package cx.mscott.breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Sprite implements Bounceable, Drawable, Hitable {
	
	public static final int WIDTH = 50;
	public static final int HEIGHT = 20;
	
	/**
	 * Left X coordinate.
	 */
	private int x;
	
	/**
	 * Top Y coordinate.
	 */
	private int y;
	
	/**
	 * Number of hits this block can sustain.
	 */
	private int lives;
	
	/**
	 * Do we need to repaint the block?
	 */
	private boolean graphicsDirty;
	
	/**
	 * Create new block. 
	 * @param x Left X coordinate.
	 * @param y Top Y coordinate.
	 */
	public Block(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.lives = 1;
		this.graphicsDirty = false;
	}
	
	/**
	 * Create new block. 
	 * @param x Left X coordinate.
	 * @param y Top Y coordinate.
	 */
	public Block(int x, int y, int lives) {
		super();
		this.x = x;
		this.y = y;
		this.lives = lives;
		this.graphicsDirty = false;
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	@Override
	public void repaint(Graphics g) {
		if (lives > 0) {
			g.setColor(Color.YELLOW);
			g.fillRect(x + offset.x, y + offset.y, WIDTH, HEIGHT);
			g.setColor(Color.DARK_GRAY);
			g.drawRect(x + offset.x, y + offset.y, WIDTH-1, HEIGHT-1);
		}
		graphicsDirty = false;
	}

	@Override
	public void hit() {
		System.out.println("Ouch says " + toString());
		lives--;
		graphicsDirty = true;
	}

	public int getLives() {
		return lives;
	}
	
	@Override
	public boolean isGraphicsDirty() {
		return graphicsDirty;
	}
}
