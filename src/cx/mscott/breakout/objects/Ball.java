package cx.mscott.breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	public final int BALL_SIZE = 20;
	
	private int maxX;
	private int maxY;
	private int offsetX;
	private int offsetY;
	
	private int xPos = 10;
    private int yPos = 10;
    private int deltaX = 3;
    private int deltaY = -3;

    public Ball(int maxX, int maxY, int offsetX, int offsetY) {
    	this.maxX = maxX;
    	this.maxY = maxY;
    	this.offsetX = offsetX;
    	this.offsetY = offsetY;
    }
    
    public void setX(int xPos){ 
        this.xPos = xPos;
    }

    public void setY(int yPos){
        this.yPos = yPos;
    }

    public void repaint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(xPos + offsetX, yPos + offsetY, BALL_SIZE, BALL_SIZE);
    }

	public Rectangle getBounds() {
		return new Rectangle(
				xPos + offsetX,
				yPos + offsetY,
				BALL_SIZE,
				BALL_SIZE);
	}

	public void move(Bat bat) {
		// Bounce off the sides
		if (yPos + deltaY < 0)
			deltaY = -deltaY;
		else if (yPos + deltaY + BALL_SIZE >= maxY)
			deltaY = -deltaY;

		if (xPos + deltaX < 0)
			deltaX = -deltaX;
		else if (xPos + deltaX + BALL_SIZE >= maxX)
			deltaX = -deltaX;

		Rectangle new_bounds = getBounds();
		new_bounds.translate(deltaX, deltaY);

		// Bounce off bat
		Rectangle bat_bounds = bat.getBounds();
		if (bat_bounds.intersects(new_bounds)) {
			if (xPos + BALL_SIZE < bat_bounds.x) {
				deltaX = -Math.abs(deltaX);
			} else if (xPos >= bat_bounds.x + bat_bounds.width) {
				deltaX = Math.abs(deltaX);
			} else if (yPos + BALL_SIZE <= bat_bounds.y) {
				deltaY = -Math.abs(deltaY);
			}
		}
		
		xPos += deltaX;
		yPos += deltaY;
	}

}
