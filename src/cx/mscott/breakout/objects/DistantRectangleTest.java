package cx.mscott.breakout.objects;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

public class DistantRectangleTest {

	@Test
	public void testDistantRectangle() {
		DistantRectangle dr = new DistantRectangle(5.0, new Rectangle(1, 2, 3, 4));
		assertNotNull(dr);
	}

	@Test
	public void testCompareTo() {
		DistantRectangle dr1 = new DistantRectangle(5.0, new Rectangle(1, 2, 3, 4));
		DistantRectangle dr2 = new DistantRectangle(6.0, new Rectangle(1, 2, 3, 4));

		assertEquals(-1, dr1.compareTo(dr2));
		assertEquals(0, dr1.compareTo(dr1));
		assertEquals(0, dr2.compareTo(dr2));
		assertEquals(1, dr2.compareTo(dr1));
	}

	@Test
	public void testGetDistance() {
		DistantRectangle dr = new DistantRectangle(5.0, new Rectangle(1, 2, 3, 4));
		assertEquals(5.0, dr.getDistance(), 0.0001);
	}

	@Test
	public void testGetRectangle() {
		DistantRectangle dr = new DistantRectangle(5.0, new Rectangle(1, 2, 3, 4));
		assertEquals(1, dr.getRectangle().x);
		assertEquals(2, dr.getRectangle().y);
		assertEquals(3, dr.getRectangle().width);
		assertEquals(4, dr.getRectangle().height);
	}

}
