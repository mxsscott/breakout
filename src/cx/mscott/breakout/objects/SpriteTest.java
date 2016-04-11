package cx.mscott.breakout.objects;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Test;

public class SpriteTest {

	@Test
	public void testSprite() {
		Sprite sprite = new Sprite();
		assertNotNull(sprite);
	}

	@Test
	public void testGetOffset() {
		Sprite sprite = new Sprite();
		assertEquals(0, sprite.getOffset().x);
		assertEquals(0, sprite.getOffset().y);
	}

	@Test
	public void testSetOffsetIntInt() {
		Sprite sprite = new Sprite();
		sprite.setOffset(10, 12);
		assertEquals(10, sprite.getOffset().x);
		assertEquals(12, sprite.getOffset().y);
	}

	@Test
	public void testSetOffsetPoint() {
		Sprite sprite = new Sprite();
		sprite.setOffset(new Point(5, 17));
		assertEquals(5, sprite.getOffset().x);
		assertEquals(17, sprite.getOffset().y);
	}

	@Test
	public void testGetBounds() {
		Sprite sprite = new Sprite();
		Rectangle bounds = sprite.getBounds();
		assertNotNull(bounds);
		assertEquals(0, bounds.x);
		assertEquals(0, bounds.y);
		assertEquals(1, bounds.width);
		assertEquals(1, bounds.height);
		
		sprite.setOffset(15,  12);
		bounds = sprite.getBounds();
		assertEquals(0, bounds.x);
		assertEquals(0, bounds.y);
		assertEquals(1, bounds.width);
		assertEquals(1, bounds.height);
	}

	@Test
	public void testGetGraphicsBounds() {
		Sprite sprite = new Sprite();
		Rectangle bounds = sprite.getGraphicsBounds();
		assertNotNull(bounds);
		assertEquals(0, bounds.x);
		assertEquals(0, bounds.y);
		assertEquals(1, bounds.width);
		assertEquals(1, bounds.height);
		
		sprite.setOffset(15,  12);
		bounds = sprite.getGraphicsBounds();
		assertEquals(15, bounds.x);
		assertEquals(12, bounds.y);
		assertEquals(1, bounds.width);
		assertEquals(1, bounds.height);
	}

}
