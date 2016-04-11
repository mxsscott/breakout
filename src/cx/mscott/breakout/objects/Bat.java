package cx.mscott.breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bat extends Sprite implements Drawable {

	public static final int BAT_HEIGHT = 20;
	public static final int BAT_DEFAULT_WIDTH = 100;
	
	/** Maximum X position */
	private int maxX;
	
	/** Current X position */
	private int currentX;

	/** Current Y position */
	private int currentY;

	/** Current width */
	private int currentWidth;
	
	public Bat(int bottom, int maxX) {
		super();
		
		this.maxX = maxX;
		this.currentX = maxX / 2;
		this.currentY = bottom - BAT_HEIGHT;
		this.currentWidth = BAT_DEFAULT_WIDTH;
	}
	
	/**
	 * Current X position of the bat.
	 * @return untranslated left edge of the bat.
	 */
	public int getCurrentX() {
		return currentX;
	}
	
	/**
	 * Width of the bat.
	 * @return
	 */
	public int getWidth() {
		return currentWidth;
	}
	
	public void move(int deltaX) {
		currentX += deltaX;
		if (currentX + currentWidth >= maxX) {
			currentX = maxX - currentWidth;
		} else if (currentX < 0) {
			currentX = 0;
		}
	}

	@Override
	public Rectangle getBounds() {
		Rectangle bounds = new Rectangle(currentX, currentY, currentWidth, BAT_HEIGHT);
		return bounds;
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(currentX + offset.x,
				currentY + offset.y,
				currentWidth,
				BAT_HEIGHT);
	}
}
