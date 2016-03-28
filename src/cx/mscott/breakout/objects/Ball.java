package cx.mscott.breakout.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball {
	private static final int BALL_SIZE = 20;
	
	private int maxX;
	private int maxY;
	private int offsetX;
	private int offsetY;
	
	private int xPos = 10;
    private int yPos = 10;
    private int deltaX = 3;
    private int deltaY = 3;

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

	public void move(Bat bat1) {
		if (yPos + deltaY < 0)
			deltaY = -deltaY;
		else if (yPos + deltaY + BALL_SIZE >= maxY)
			deltaY = -deltaY;

		if (xPos + deltaX < 0)
			deltaX = -deltaX;
		else if (xPos + deltaX + BALL_SIZE >= maxX)
			deltaX = -deltaX;

		xPos += deltaX;
		yPos += deltaY;
	}

}
