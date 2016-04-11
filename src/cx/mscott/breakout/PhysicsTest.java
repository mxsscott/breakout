package cx.mscott.breakout;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.Test;

import cx.mscott.breakout.Physics.Result;
import cx.mscott.breakout.objects.Coordinate;
import cx.mscott.breakout.objects.Distance;

public class PhysicsTest {
	public static final double RIGHT = 0.0;
	public static final double DOWN = Math.PI * 0.5;
	public static final double LEFT = Math.PI;
	public static final double UP = Math.PI * 1.5;
	
	public static final double ANGLE_DELTA = 0.00001;
	public static final double DISTANCE_DELTA = 0.00001;

	@Test
	public void testGetBallMovement() {
		Distance mvmt = Physics.getBallMovement(DOWN, 5);
		assertEquals(0, mvmt.deltaX);
		assertEquals(5, mvmt.deltaY);

		mvmt = Physics.getBallMovement(RIGHT, 5);
		assertEquals(5, mvmt.deltaX);
		assertEquals(0, mvmt.deltaY);

		mvmt = Physics.getBallMovement(LEFT, 5);
		assertEquals(-5, mvmt.deltaX);
		assertEquals(0, mvmt.deltaY);

		mvmt = Physics.getBallMovement(UP, 5);
		assertEquals(0, mvmt.deltaX);
		assertEquals(-5, mvmt.deltaY);

		mvmt = Physics.getBallMovement(Math.toRadians(29.0), 5);
		assertEquals(4, mvmt.deltaX);
		assertEquals(2, mvmt.deltaY);
	}

	@Test
	public void testBounceAngleHPlane() {
		assertEquals(RIGHT,                Physics.bounceAngleHPlane(RIGHT),          ANGLE_DELTA);
		assertEquals(Physics.TWO_PI - 1.0, Physics.bounceAngleHPlane(1.0),            ANGLE_DELTA);
		assertEquals(UP,                   Physics.bounceAngleHPlane(DOWN),           ANGLE_DELTA);
		assertEquals(Physics.TWO_PI - 2.0, Physics.bounceAngleHPlane(2.0),            ANGLE_DELTA);
		assertEquals(Physics.TWO_PI - 3.0, Physics.bounceAngleHPlane(3.0),            ANGLE_DELTA);
		assertEquals(LEFT,                 Physics.bounceAngleHPlane(LEFT),           ANGLE_DELTA);
		assertEquals(Physics.TWO_PI - 4.0, Physics.bounceAngleHPlane(4.0),            ANGLE_DELTA);
		assertEquals(Physics.TWO_PI - 5.0, Physics.bounceAngleHPlane(5.0),            ANGLE_DELTA);
		assertEquals(DOWN,                 Physics.bounceAngleHPlane(UP),             ANGLE_DELTA);
		assertEquals(Physics.TWO_PI - 6.0, Physics.bounceAngleHPlane(6.0),            ANGLE_DELTA);
		assertEquals(RIGHT,                Physics.bounceAngleHPlane(Physics.TWO_PI), ANGLE_DELTA);
	}

	@Test
	public void testBounceAngleVPlane() {
		assertEquals(LEFT,                Physics.bounceAngleVPlane(RIGHT),          ANGLE_DELTA);
		assertEquals(Math.PI - 1.0,       Physics.bounceAngleVPlane(1.0),            ANGLE_DELTA);
		assertEquals(DOWN,                Physics.bounceAngleVPlane(DOWN),           ANGLE_DELTA);
		assertEquals(Math.PI - 2.0,       Physics.bounceAngleVPlane(2.0),            ANGLE_DELTA);
		assertEquals(Math.PI - 3.0,       Physics.bounceAngleVPlane(3.0),            ANGLE_DELTA);
		assertEquals(RIGHT,               Physics.bounceAngleVPlane(LEFT),           ANGLE_DELTA);
		assertEquals(3.0 * Math.PI - 4.0, Physics.bounceAngleVPlane(4.0),            ANGLE_DELTA);
		assertEquals(3.0 * Math.PI - 5.0, Physics.bounceAngleVPlane(5.0),            ANGLE_DELTA);
		assertEquals(UP,                  Physics.bounceAngleVPlane(UP),             ANGLE_DELTA);
		assertEquals(3.0 * Math.PI - 6.0, Physics.bounceAngleVPlane(6.0),            ANGLE_DELTA);
		assertEquals(LEFT,                Physics.bounceAngleVPlane(Physics.TWO_PI), ANGLE_DELTA);
	}

	@Test
	public void testDistanceToIntersectionVPlane() {
		// A ball heading directly up will not intersect.
		assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), UP, 15), DISTANCE_DELTA);

		// A ball heading directly down will not intersect.
		assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), DOWN, 15), DISTANCE_DELTA);

		{
			// A ball heading directly left will intersect a plane to the left.
			assertEquals(4.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), LEFT, 6), DISTANCE_DELTA);
		
			// A ball heading directly left will not intersect a plane to the right.
			assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), LEFT, 15), DISTANCE_DELTA);
		}
		
		{
			// A ball heading directly right will not intersect a plane to the left.
			assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), RIGHT, 5), DISTANCE_DELTA);
		
			// A ball heading directly right will intersect a plane on the right.
			assertEquals(4.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), RIGHT, 14), DISTANCE_DELTA);
		}
		
		// A ball heading at approx 53 degrees will hit a plane 3 squares right at 4 squares below.
		assertEquals(5.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(53.1301), 13), DISTANCE_DELTA);

		// A ball heading at approx 127 degrees will hit a plane 3 squares left at 4 squares below.
		assertEquals(5.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(126.8699), 7), DISTANCE_DELTA);
		
		// A ball heading at approx 233 degrees will hit a plane 3 squares left at 4 squares above.
		assertEquals(5.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(233.1301), 7), DISTANCE_DELTA);

		// A ball heading at approx 217 degrees will hit a plane 4 squares left at 3 squares above.
		assertEquals(5.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(216.8699), 6), DISTANCE_DELTA);

		// A ball heading at approx 307 degrees will hit a plane 3 squares right at 4 squares above.
		assertEquals(5.0, Physics.distanceToIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(306.8699), 13), DISTANCE_DELTA);
	}

	@Test
	public void testPointOfIntersectionVPlane() {
		// A ball heading directly up will not intersect.
		assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), UP, 15));

		// A ball heading directly down will not intersect.
		assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), DOWN, 15));

		{
			// A ball heading directly left will intersect a plane to the left, at the same Y value.
			assertEquals(10, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), LEFT, 5));
		
			// A ball heading directly left will not intersect a plane to the right.
			assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), LEFT, 15));
		}
		
		{
			// A ball heading directly right will not intersect a plane to the left.
			assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), RIGHT, 5));
		
			// A ball heading directly right will intersect a plane on the right, at the same Y value.
			assertEquals(10, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), RIGHT, 15));
		}
		
		// A ball heading at approx 53 degrees will hit a plane 3 squares right at 4 squares below.
		assertEquals(14, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(53.0), 13));

		// A ball heading at approx 127 degrees will hit a plane 3 squares left at 4 squares below.
		assertEquals(14, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(127.0), 7));
		
		// A ball heading at approx 233 degrees will hit a plane 3 squares left at 4 squares above.
		assertEquals(6, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(233.0), 7));

		// A ball heading at approx 217 degrees will hit a plane 4 squares left at 3 squares above.
		assertEquals(7, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(217.0), 6));

		// A ball heading at approx 307 degrees will hit a plane 3 squares right at 4 squares above.
		assertEquals(6, Physics.pointOfIntersectionVPlane(new Coordinate(10, 10), Math.toRadians(307.0), 13));
	}

	@Test
	public void testDistanceToIntersectionHPlane() {
		assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), RIGHT, 15), DISTANCE_DELTA);
		assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), LEFT, 15), DISTANCE_DELTA);

		{
			// A ball heading directly down will intersect a plane below.
			assertEquals(4.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), DOWN, 14), DISTANCE_DELTA);
		
			// A ball heading directly down will not intersect a plane above.
			assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), DOWN, 5), DISTANCE_DELTA);
		}
		
		{
			// A ball heading directly up will not intersect a plane below.
			assertEquals(Physics.NO_DISTANCE, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), UP, 15), DISTANCE_DELTA);
		
			// A ball heading directly up will intersect a plane above.
			assertEquals(4.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), UP, 6), DISTANCE_DELTA);
		}
		
		// A ball heading at approx 53 degrees.
		assertEquals(5.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(53.1301), 14), DISTANCE_DELTA);

		// A ball heading at approx 127 degrees.
		assertEquals(5.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(126.8699), 14), DISTANCE_DELTA);
		
		// A ball heading at approx 233 degrees.
		assertEquals(5.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(233.1301), 6), DISTANCE_DELTA);

		// A ball heading at approx 217 degrees.
		assertEquals(5.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(216.8699), 7), DISTANCE_DELTA);

		// A ball heading at approx 307 degrees.
		assertEquals(5.0, Physics.distanceToIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(306.8699), 6), DISTANCE_DELTA);
	}

	@Test
	public void testPointOfIntersectionHPlane() {
		// A ball heading directly right will not intersect.
		assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), RIGHT, 15));

		// A ball heading directly left will not intersect.
		assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), LEFT, 15));

		{
			// A ball heading directly down will intersect a plane below, at the same X value.
			assertEquals(10, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), DOWN, 15));
		
			// A ball heading directly down will not intersect a plane above.
			assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), DOWN, 5));
		}
		
		{
			// A ball heading directly up will not intersect a plane below.
			assertEquals(Physics.NO_INTERSECT, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), UP, 15));
		
			// A ball heading directly up will intersect a plane above, at the same X value.
			assertEquals(10, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), UP, 5));
		}
		
		// A ball heading at approx 53 degrees will hit a plane 4 squares below at 3 squares to the right.
		assertEquals(13, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(53.0), 14));

		// A ball heading at approx 127 degrees will hit a plane 4 squares below at 3 squares to the left.
		assertEquals(7, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(127.0), 14));
		
		// A ball heading at approx 233 degrees will hit a plane 4 squares above at 3 squares to the left
		assertEquals(7, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(233.0), 6));

		// A ball heading at approx 217 degrees will hit a plane 3 squares above at 4 squares to the left
		assertEquals(6, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(217.0), 7));

		// A ball heading at approx 307 degrees will hit a plane 4 squares above at 3 squares to the right.
		assertEquals(13, Physics.pointOfIntersectionHPlane(new Coordinate(10, 10), Math.toRadians(307.0), 6));
	}

	@Test
	public void testIntersectsRectangle() {
		Coordinate ball = new Coordinate(5, 1);
		int ballRadius = 1;
		double angle = Physics.ANGLE_DOWN;
		{
			Rectangle rectangle = new Rectangle(2, 10, 12, 5);
		
			// Too slow to hit
			assertNull(Physics.intersectsRectangle(ball, ballRadius, angle, 1.0, rectangle));
			assertNull(Physics.intersectsRectangle(ball, ballRadius, angle, 7.9, rectangle));

			// Fast enough to hit
			assertNotNull(Physics.intersectsRectangle(ball, ballRadius, angle, 8.0, rectangle));
			assertNotNull(Physics.intersectsRectangle(ball, ballRadius, angle, 8.1, rectangle));
		}
		{
			Rectangle rectangle = new Rectangle(-100, -100, 100, 500);
			angle = Math.PI * 0.75;
			assertNotNull(Physics.intersectsRectangle(ball, ballRadius, angle, 10.0, rectangle));
		}
	}

}
