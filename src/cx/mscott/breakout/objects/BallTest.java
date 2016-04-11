package cx.mscott.breakout.objects;

import static org.junit.Assert.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cx.mscott.breakout.Physics;

public class BallTest {

	@Test
	public void testBall() {
		Ball ball = new Ball(200, 300);
		assertNotNull(ball);
	}
	
	@Test
	public void testGetDirection() {
		Ball ball = new Ball(200, 300);
		assertEquals(0.0, ball.getDirection(), 0.00001);
	}
	
	@Test
	public void testSetDirection() {
		Ball ball = new Ball(200, 300);
		ball.setDirection(Math.PI);
		assertEquals(Math.PI, ball.getDirection(), 0.00001);
	}
	
	@Test
	public void testGetX() {
		Ball ball = new Ball(200, 300);
		assertEquals(10, ball.getX());
	}
	
	@Test
	public void testSetX() {
		Ball ball = new Ball(200, 300);

		ball.setX(9);
		assertEquals(10, ball.getX());
		
		ball.setX(10);
		assertEquals(10, ball.getX());
		
		ball.setX(30);
		assertEquals(30, ball.getX());
		
		ball.setX(189);
		assertEquals(189, ball.getX());
		
		ball.setX(190);
		assertEquals(190, ball.getX());
		
		ball.setX(191);
		assertEquals(190, ball.getX());
	}

	@Test
	public void testGetY() {
		Ball ball = new Ball(200, 300);
		assertEquals(10, ball.getY());
	}
	
	@Test
	public void testSetY() {
		Ball ball = new Ball(200, 300);
	
		ball.setY(-1);
		assertEquals(10, ball.getY());
		
		ball.setY(10);
		assertEquals(10, ball.getY());
		
		ball.setY(11);
		assertEquals(11, ball.getY());
		
		ball.setY(50);
		assertEquals(50, ball.getY());
		
		ball.setY(270);
		assertEquals(270, ball.getY());
		
		ball.setY(289);
		assertEquals(289, ball.getY());
		
		ball.setY(290);
		assertEquals(290, ball.getY());
		
		ball.setY(291);
		assertEquals(290, ball.getY());
	}
	
	@Test
	public void testGetRadius() {
		Ball ball = new Ball(200, 300);
		assertEquals(Ball.DEFAULT_BALL_SIZE, ball.getRadius());
	}
	
	@Test
	public void testSetRadius() {
		Ball ball = new Ball(200, 300);
		ball.setRadius(20);
		assertEquals(20, ball.getRadius());
	}
	
	@Test
	public void testGetSpeed() {
		Ball ball = new Ball(200, 300);
		assertEquals(Ball.DEFAULT_SPEED, ball.getSpeed());
	}
	
	@Test
	public void testSetSpeed() {
		Ball ball = new Ball(200, 300);
		ball.setSpeed(15);
		assertEquals(15, ball.getSpeed());
	}

	@Test
	public void testGetBounds() {
		Ball ball = new Ball(200, 300);
		Rectangle bounds = ball.getBounds();
		
		assertEquals(0, bounds.x);
		assertEquals(0, bounds.y);
		assertEquals(ball.getRadius() * 2, bounds.width);
		assertEquals(ball.getRadius() * 2, bounds.height);
		
		ball.setOffset(30, 40);
		bounds = ball.getBounds();
		assertEquals(0, bounds.x);
		assertEquals(0, bounds.y);
		assertEquals(ball.getRadius() * 2, bounds.width);
		assertEquals(ball.getRadius() * 2, bounds.height);

		ball.setX(15);
		ball.setY(20);
		bounds = ball.getBounds();
		assertEquals(5, bounds.x);
		assertEquals(10, bounds.y);
		assertEquals(ball.getRadius() * 2, bounds.width);
		assertEquals(ball.getRadius() * 2, bounds.height);
	}

	@Test
	public void testMove() {
		Ball ball = new Ball(200, 300);
		ball.setDirection(Physics.HALF_PI / 2.0);
		ball.setSpeed(10);
		ball.setX(100);
		ball.setY(100);
		
		List<Rectangle> boxes = new ArrayList<Rectangle>();
		// Top of game area
		boxes.add(new Rectangle(-100, -100, 400, 100));
		// Left of game area
		boxes.add(new Rectangle(-100, -100, 100, 500));
		// Right of game area
		boxes.add(new Rectangle(200, -100, 100, 500));
		// Bottom of game area
		boxes.add(new Rectangle(-100, 300, 400, 100));
				
		ball.move(boxes);
		assertEquals(107, ball.getX());
		assertEquals(107, ball.getY());

		ball.setSpeed(20);

		ball.move(boxes);
		assertEquals(121, ball.getX());
		assertEquals(121, ball.getY());

		ball.move(boxes);
		assertEquals(135, ball.getX());
		assertEquals(135, ball.getY());

		ball.move(boxes);
		assertEquals(149, ball.getX());
		assertEquals(149, ball.getY());

		ball.move(boxes);
		assertEquals(163, ball.getX());
		assertEquals(163, ball.getY());

		ball.move(boxes);
		assertEquals(177, ball.getX());
		assertEquals(177, ball.getY());

		// Boing off right
		ball.move(boxes);
		assertEquals(189, ball.getX());
		assertEquals(191, ball.getY());
		
		ball.setSpeed(40);
		ball.move(boxes);
		assertEquals(161, ball.getX());
		assertEquals(219, ball.getY());
		
		ball.move(boxes);
		assertEquals(133, ball.getX());
		assertEquals(247, ball.getY());
		
		ball.move(boxes);
		assertEquals(105, ball.getX());
		assertEquals(275, ball.getY());
		
		// Boing off bottom
		ball.move(boxes);
		assertEquals(77, ball.getX());
		assertEquals(277, ball.getY());
		
		ball.move(boxes);
		assertEquals(49, ball.getX());
		assertEquals(249, ball.getY());
		
		ball.move(boxes);
		assertEquals(21, ball.getX());
		assertEquals(221, ball.getY());

		// Boing off left
		ball.move(boxes);
		assertEquals(27, ball.getX());
		assertEquals(193, ball.getY());
	}
}
