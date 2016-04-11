package cx.mscott.breakout.objects;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

public class BatTest {

	@Test
	public void testGetCurrentX() {
		{
			Bat bat = new Bat(0, 300);
			assertEquals(150, bat.getCurrentX());
		}
		{
			Bat bat = new Bat(10, 360);
			assertEquals(180, bat.getCurrentX());
		}
	}

	@Test
	public void testGetWidth() {
		{
			Bat bat = new Bat(10, 300);
			assertEquals(Bat.BAT_DEFAULT_WIDTH, bat.getWidth());
		}
	}

	@Test
	public void testMove() {
		Bat bat = new Bat(10, 360);
		assertEquals(180, bat.getCurrentX());
		
		bat.move(-10);
		assertEquals(170, bat.getCurrentX());
		
		bat.move(-100);
		assertEquals(70, bat.getCurrentX());

		bat.move(-100);
		assertEquals(0, bat.getCurrentX());

		bat.move(200);
		assertEquals(200, bat.getCurrentX());

		bat.move(100);
		assertEquals(260, bat.getCurrentX());
	}

	@Test
	public void testGetBounds() {
		Bat bat = new Bat(30, 360);
		Rectangle bounds = bat.getBounds();
		assertEquals(180, bat.getCurrentX());
		assertEquals(180, bounds.x);
		assertEquals(30 - Bat.BAT_HEIGHT, bounds.y);
		assertEquals(Bat.BAT_DEFAULT_WIDTH, bounds.width);
		assertEquals(Bat.BAT_HEIGHT, bounds.height);
		
		bat.move(-10);
		bounds = bat.getBounds();
		assertEquals(170, bat.getCurrentX());
		assertEquals(170, bounds.x);
		assertEquals(30 - Bat.BAT_HEIGHT, bounds.y);
		assertEquals(Bat.BAT_DEFAULT_WIDTH, bounds.width);
		assertEquals(Bat.BAT_HEIGHT, bounds.height);
	}
}
