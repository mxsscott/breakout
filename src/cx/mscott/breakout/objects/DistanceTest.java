package cx.mscott.breakout.objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class DistanceTest {

	@Test
	public void testHashCode() {
		Distance x = new Distance(5,8);
		Distance y = new Distance(7,6);
		assertNotEquals(x.hashCode(), y.hashCode());
	}

	@Test
	public void testDistance() {
		Distance x = new Distance();
		assertEquals(0, x.getDeltaX());
		assertEquals(0, x.getDeltaY());
	}

	@Test
	public void testDistanceDistance() {
		Distance x = new Distance(10, 15);
		Distance y = new Distance(x);
		assertEquals(10, y.getDeltaX());
		assertEquals(15, y.getDeltaY());
	}

	@Test
	public void testDistanceIntInt() {
		Distance x = new Distance(10, 15);
		assertEquals(10, x.getDeltaX());
		assertEquals(15, x.getDeltaY());
	}

	@Test
	public void testGetDeltaX() {
		Distance x = new Distance(20, 15);
		assertEquals(20, x.getDeltaX());
	}

	@Test
	public void testGetDeltaY() {
		Distance x = new Distance(10, 67);
		assertEquals(67, x.getDeltaY());
	}

	@Test
	public void testGetDeltas() {
		Distance x = new Distance(76, 25);
		Distance x2 = x.getDeltas();
		assertEquals(76, x2.getDeltaX());
		assertEquals(25, x2.getDeltaY());
	}

	@Test
	public void testSetDeltasDistance() {
		Distance x = new Distance();
		Distance x2 = new Distance(25, 28);
		x.setDeltas(x2);
		assertEquals(25, x.getDeltaX());
		assertEquals(28, x.getDeltaY());
	}

	@Test
	public void testSetDeltasIntInt() {
		Distance x = new Distance();
		x.setDeltas(85, 19);
		assertEquals(85, x.getDeltaX());
		assertEquals(19, x.getDeltaY());
	}

	@Test
	public void testEqualsObject() {
		Distance x = new Distance();
		Distance y = new Distance();
		
		assertEquals(true, x.equals(y));
		
		y.setDeltas(5, 2);
		
		assertEquals(false, x.equals(y));
		
		x.setDeltas(5, 2);
		
		assertEquals(true, x.equals(y));
	}

	@Test
	public void testToString() {
		Distance x = new Distance();
		assertEquals("Distance [dx=0,dy=0]", x.toString());
		
		x.setDeltas(4, 56);
		assertEquals("Distance [dx=4,dy=56]", x.toString());
	}

}
