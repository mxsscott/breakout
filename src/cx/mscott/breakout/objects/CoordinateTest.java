package cx.mscott.breakout.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoordinateTest {
	
	@Test
	public void testIsAbove() {
		Coordinate a = new Coordinate(10, 5);
		Coordinate b = new Coordinate(10, 7);
		Coordinate c = new Coordinate(10, 7);
		
		assertTrue(a.isAbove(b));
		assertFalse(b.isAbove(a));
		assertFalse(b.isAbove(c));
	}

	@Test
	public void testIsBelow() {
		Coordinate a = new Coordinate(10, 5);
		Coordinate b = new Coordinate(10, 7);
		Coordinate c = new Coordinate(10, 7);
		
		assertFalse(a.isBelow(b));
		assertTrue(b.isBelow(a));
		assertFalse(b.isBelow(c));
	}

	@Test
	public void testIsLeftOf() {
		Coordinate a = new Coordinate(1, 5);
		Coordinate b = new Coordinate(3, 5);
		Coordinate c = new Coordinate(3, 5);
		
		assertTrue(a.isLeftOf(b));
		assertFalse(b.isLeftOf(a));
		assertFalse(b.isLeftOf(c));
	}

	@Test
	public void testIsRightOf() {
		Coordinate a = new Coordinate(1, 5);
		Coordinate b = new Coordinate(3, 5);
		Coordinate c = new Coordinate(3, 5);
		
		assertFalse(a.isRightOf(b));
		assertTrue(b.isRightOf(a));
		assertFalse(b.isRightOf(c));
	}

	@Test
	public void testMoveUp() {
		Coordinate a = new Coordinate(1,9);
		Coordinate b = a.moveUp(5);
		
		assertTrue(b.isAbove(a));
		assertEquals(4, b.y);
	}

	@Test
	public void testMoveDown() {
		Coordinate a = new Coordinate(1,9);
		Coordinate b = a.moveDown(4);
		
		assertTrue(b.isBelow(a));
		assertEquals(13, b.y);
	}

	@Test
	public void testMoveLeft() {
		Coordinate a = new Coordinate(12,9);
		Coordinate b = a.moveLeft(5);
		
		assertTrue(b.isLeftOf(a));
		assertEquals(7, b.x);
	}

	@Test
	public void testMoveRight() {
		Coordinate a = new Coordinate(1,9);
		Coordinate b = a.moveRight(4);
		
		assertTrue(b.isRightOf(a));
		assertEquals(5, b.x);
	}
}
