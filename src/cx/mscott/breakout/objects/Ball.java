package cx.mscott.breakout.objects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.PriorityQueue;

import cx.mscott.breakout.Physics;
import cx.mscott.breakout.Physics.Result;

public class Ball extends Sprite implements Drawable {
	/**
	 * Default ball radius.
	 */
	public static final int DEFAULT_BALL_SIZE = 10;

	/**
	 * Default speed.
	 */
	public static final int DEFAULT_SPEED = 5;
	
	/**
	 * Width and height of ball area.
	 */
	private Dimension area;

    /**
     * Ball direction in radians
     */
    private double direction;

	/**
	 * Centre point of ball.
	 * 
	 * Must be <code>0 + radius &lt;= position.x &lt; area.width - radius</code>.
	 * Must be <code>0 + radius &lt;= position.y &lt; area.height - radius</code>.
	 */
	private Coordinate position;
	
	/**
	 * Ball radius
	 */
	private int radius;
	
	/**
	 * Ball speed.
	 */
    private int speed;
    
    /**
     * Create ball.
     * @param width Width of game area.
     * @param height Height of game area.
     */
    public Ball(int width, int height) {
    	super();
    	this.area = new Dimension(width, height);
    	this.direction = 0.0;
    	this.position = new Coordinate(10, 10);
    	this.radius = DEFAULT_BALL_SIZE;
    	this.speed = DEFAULT_SPEED;
    }
    
	/**
	 * Get ball direction.
	 * @return ball direction.
	 */
	public double getDirection() {
		return direction;
	}
	
	/**
	 * Set ball direction.
	 * @param direction ball direction.
	 */
	public void setDirection(double direction) {
		this.direction = direction;
	}

    /**
     * Get current X position of centre of ball.
     * @return current X position (not adjusted by offset)
     */
    public int getX() {
    	return position.x;
    }
    
    /**
     * Set X position of centre of ball.
     * 
     * X position is kept within the bounds of <code>0 + radius &lt;= xPos
     * &lt; width - radius</code>
     * 
     * @param xPos desired X position.
     */
    public void setX(int xPos) {
    	position.x = Math.max(0 + radius, Math.min(xPos, area.width - radius));
    }
    
    /**
     * Get current Y position of centre of ball.
     * @return current Y position (not adjusted by offset)
     */
    public int getY() {
    	return position.y;
    }

    /**
     * Set Y position of centre of ball.
     * 
     * Y position is kept within the bounds of <code>0 + radius &lt;= yPos
     * &lt; width - radius</code>
     * 
     * @param yPos desired Y position.
     */
    public void setY(int yPos) {
        position.y = Math.max(0 + radius, Math.min(yPos, area.height - radius));
    }

    /**
     * Get ball radius.
     * @return ball radius.
     */
	public int getRadius() {
		return radius;
	}
	
	/**
	 * Set ball radius.
	 * @param radius New ball radius.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Get ball speed.
	 * @return ball speed.
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Set ball speed.
	 * @param speed ball speed.
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
    /**
     * Bounds of ball.
     * @return
     */
    @Override
	public Rectangle getBounds() {
		return new Rectangle(
				position.x - radius,
				position.y - radius,
				radius * 2,
				radius * 2);
	}
	
	/**
     * Repaint ball
     * @param g Graphics context to paint on.
     */
    public void repaint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(position.x + offset.x - radius,
        		position.y + offset.y - radius,
        		radius * 2,
        		radius * 2);
    }

	public void move(List<Rectangle> bounds) {
		double distance_left_to_travel = speed;
		
		while (distance_left_to_travel > 0.0) {
			// Convert speed and direction into deltas.
			PriorityQueue<Result> x = new PriorityQueue<Physics.Result>();
		
			for (Rectangle bound : bounds) {
				Result result = Physics.intersectsRectangle(
						position,
						radius,
						direction,
						distance_left_to_travel,
						bound);
				if (result != null) {
					x.add(result);
				}
			}
			
			if (x.isEmpty()) {
				// No bounces
				Distance delta = Physics.getBallMovement(direction, distance_left_to_travel);
				position.x += delta.deltaX;
				position.y += delta.deltaY;
				distance_left_to_travel = 0.0;
			} else {
				Result first_bounce = x.remove();
				position = first_bounce.getBouncePoint();
				direction = first_bounce.getNewAngle();
				distance_left_to_travel -= first_bounce.getDistanceUsed();
			}
		}
	}
}
