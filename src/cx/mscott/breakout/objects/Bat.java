package cx.mscott.breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bat {

	private static final int BAT_HEIGHT = 20;
	
	/** Maximum X position */
	private int maxX;
	
	/** Current X position */
	private int currentX;

	/** Current position / bounds of the bat */
	private Rectangle currentPosition = new Rectangle();
	
	private int offsetX;
	
	
	public Bat(int bottom, int maxX, int offsetX, int offsetY) {
		this.maxX = maxX;
		this.currentX = maxX / 2;

		this.currentPosition.x = this.currentX;
		this.currentPosition.width = 100;
		this.currentPosition.y = bottom - BAT_HEIGHT;
		this.currentPosition.height = BAT_HEIGHT;
		
		this.currentPosition.translate(offsetX, offsetY);
		
		this.offsetX = offsetX;
	}
	
	public void move(int deltaX) {
		currentX += deltaX;
		if (currentX + currentPosition.width >= maxX) {
			currentX = maxX - currentPosition.width;
		} else if (currentX < 0) {
			currentX = 0;
		}
		currentPosition.x = currentX + offsetX;
	}
	
	public Rectangle getBounds() {
		
		return currentPosition;
	}
	
	public void repaint(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(currentPosition.x, currentPosition.y,
				currentPosition.width, currentPosition.height);
	}
}
