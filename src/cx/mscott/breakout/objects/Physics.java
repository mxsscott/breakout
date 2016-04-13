package cx.mscott.breakout.objects;

import java.awt.Point;
import java.awt.Rectangle;

public class Physics {
	public static double HALF_PI = Math.PI * 0.5;
	public static double TWO_PI = Math.PI * 2.0;
	
	public static double ANGLE_RIGHT = 0.0;
	public static double ANGLE_DOWN = HALF_PI;
	public static double ANGLE_LEFT = Math.PI;
	public static double ANGLE_UP = Math.PI * 1.5;
	
	public static int NO_INTERSECT = Integer.MIN_VALUE;
	public static double NO_DISTANCE = Double.NaN;
	
	public static class Result implements Comparable<Result> {
		private double newAngle;
		private double distanceUsed;
		private Coordinate bouncePoint;
		
		public Result(double newAngle, double distanceUsed, Coordinate bouncePoint) {
			this.newAngle = newAngle;
			this.distanceUsed = distanceUsed;
			this.bouncePoint = bouncePoint;
		}
		
		public double getNewAngle() {
			return newAngle;
		}
		
		public double getDistanceUsed() {
			return distanceUsed;
		}
		
		public Coordinate getBouncePoint() {
			return bouncePoint;
		}

		@Override
		public int compareTo(Result arg0) {
			if (distanceUsed < arg0.distanceUsed)
				return -1;
			else if (distanceUsed > arg0.distanceUsed)
				return 1;

			return 0;
		}
	}

	/**
	 * Compute the delta-X and delta-Y of a ball travelling at <code>speed</speed>
	 * and in direction <code>angle</code>
	 * @param angle Angle of travel in radians.
	 * @param speed Speed of travel in units
	 * @return width and height travelled.
	 */
	public static Distance getBallMovement(double angle, double speed) {
		int delta_x = (int)Math.round(speed * Math.cos(angle));
		int delta_y = (int)Math.round(speed * Math.sin(angle));
		return new Distance(delta_x, delta_y);
	}

	/**
	 * Compute angle of travel if we bounce off a horizontal plane.
	 * @param angle Original angle of travel
	 * @return Horizontally reflected angle of travel
	 */
	public static double bounceAngleHPlane(double angle) {
		// % is a little weird (to me) in Java, since it is
		// happy with the value -2PI < x < PI.
		// So we have to %, then + then % to ensure the number is positive.
		return (((TWO_PI - angle) % TWO_PI) + TWO_PI) % TWO_PI;
	}

	/**
	 * Compute angle of travel if we bounce off a vertical plane.
	 * @param angle Original angle of travel
	 * @return Vertically reflected angle of travel
	 */
	public static double bounceAngleVPlane(double angle) {
		// % is a little weird (to me) in Java, since it is
		// happy with the value -2PI < x < PI.
		// So we have to %, then + then % to ensure the number is positive.
		return (((Math.PI - angle) % TWO_PI) + TWO_PI) % TWO_PI;
	}

	public static double distanceToIntersectionVPlane(Coordinate point, double angle, int planeX) {
		// If point is right of the plane and travelling right, it can't intersect.
		if (point.isRightOf(new Point(planeX, 0)) && isRight(angle)) {
			return NO_DISTANCE;
		}
		
		// If point is left of the plane and travelling left, it can't intersect.
		if (point.isLeftOf(new Point(planeX, 0)) && isLeft(angle)) {
			return NO_DISTANCE;
		}
		
		int distanceX = planeX - point.x;
		double cos = Math.cos(angle);

		if (-0.01 < cos && cos < 0.01) {
			return NO_DISTANCE;
		}
		
		return distanceX / Math.cos(angle);
	}
	
	public static double distanceToIntersectionHPlane(Coordinate point, double angle, int planeY) {
		// If point is above the plane and travelling up, it can't intersect.
		if (point.isAbove(new Point(0, planeY)) && isUpwards(angle)) {
			return NO_DISTANCE;
		}
		
		// If point is completely below the plane and travelling down, it can't intersect.
		if (point.isBelow(new Point(0, planeY)) && isDownwards(angle)) {
			return NO_DISTANCE;
		}
		
		int distanceY = planeY - point.y;
		double sin = Math.sin(angle);
		
		if (-0.01 < sin && sin < 0.01) {
			return NO_DISTANCE;
		}
		
		return distanceY / Math.sin(angle);
	}
	
	public static int pointOfIntersectionHPlane(Coordinate point, double angle, int planeY) {
		// If point is above the plane and travelling up, it can't intersect.
		if (point.isAbove(new Point(0, planeY)) && isUpwards(angle)) {
			return NO_INTERSECT;
		}
		
		// If point is below the plane and travelling down, it can't intersect.
		if (point.isBelow(new Point(0, planeY)) && isDownwards(angle)) {
			return NO_INTERSECT;
		}
		
		int distanceY = planeY - point.y;
		double tan = Math.tan(angle);
		
		if (-0.01 < tan && tan < 0.01) {
			return NO_INTERSECT;
		}
		
		return (int)Math.round(point.x + distanceY / tan);
	}

	public static int pointOfIntersectionVPlane(Coordinate point, double angle, int planeX) {
		// If point is right of the plane and travelling right, it can't intersect.
		if (point.isRightOf(new Point(planeX, 0)) && isRight(angle)) {
			return NO_INTERSECT;
		}
		
		// If point is left of the plane and travelling left, it can't intersect.
		if (point.isLeftOf(new Point(planeX, 0)) && isLeft(angle)) {
			return NO_INTERSECT;
		}
		
		int distanceX = planeX - point.x;
		double tan = Math.tan(angle);
		if (Math.abs(tan) > 100000) {
			return NO_INTERSECT;
		}
		
		return (int)Math.round(point.y + distanceX * Math.tan(angle));
	}
	
	private static boolean isLeft(double angle) {
		return angle > ANGLE_DOWN && angle < ANGLE_UP;
	}

	private static boolean isRight(double angle) {
		return angle < ANGLE_DOWN || angle > ANGLE_UP;
	}

	private static boolean isDownwards(double angle) {
		return angle > ANGLE_RIGHT && angle < ANGLE_LEFT;
	}

	private static boolean isUpwards(double angle) {
		return angle > ANGLE_LEFT && angle < TWO_PI;
	}

	private static boolean isHorizontal(double angle) {
		return angle == ANGLE_LEFT || angle == ANGLE_RIGHT;
	}

	private static boolean isVertical(double angle) {
		return angle == ANGLE_DOWN || angle == ANGLE_UP;
	}

	public static Physics.Result intersectsRectangle(Coordinate ball, int ballRadius, double angle, double speed, Rectangle rectangle) {
		// Enlarge the rectangle by the ball radius
		Rectangle bigger = (Rectangle)rectangle.clone();
		bigger.grow(ballRadius, ballRadius);
		
		// First, cull off the easy cases
		if ((isDownwards(angle) || isHorizontal(angle)) && ball.y >= (bigger.y + bigger.height)) {
			// heading down and we're below the rectangle
			return null;
		} else if ((isLeft(angle) || isVertical(angle)) && ball.x <= bigger.x) {
			// heading left and we're left of the rectangle
			return null;
		} else if ((isUpwards(angle) || isHorizontal(angle)) && ball.y <= bigger.y) {
			// heading up and we're above the rectangle
			return null;
		} else if ((isRight(angle) || isVertical(angle)) && ball.x >= (bigger.x + bigger.width)) {
			// heading right and we're right of the rectangle
			return null;
		}
		
		// The ball might intersection with the rectangle.
		int hIntersectTop = pointOfIntersectionHPlane(ball, angle, bigger.y);
		int hIntersectBottom = pointOfIntersectionHPlane(ball, angle, bigger.y + bigger.height);
		int vIntersectLeft = pointOfIntersectionVPlane(ball, angle, bigger.x);
		int vIntersectRight = pointOfIntersectionVPlane(ball, angle, bigger.x + bigger.width);
		
		// Intersect with top?
		if (hIntersectTop != NO_INTERSECT) {
			if (bigger.x <= hIntersectTop && hIntersectTop <= bigger.x+bigger.width) {
				double distance = distanceToIntersectionHPlane(ball, angle, bigger.y);
				if (speed >= distance)
					return new Result(bounceAngleHPlane(angle), distance, new Coordinate(hIntersectTop, bigger.y));
			}
		}
		
		// Intersect with bottom?
		if (hIntersectBottom != NO_INTERSECT) {
			if (bigger.x <= hIntersectBottom && hIntersectBottom <= bigger.x+bigger.width) {
				double distance = distanceToIntersectionHPlane(ball, angle, bigger.y + bigger.height);
				if (speed >= distance)
					return new Result(bounceAngleHPlane(angle), distance, new Coordinate(hIntersectBottom, bigger.y + bigger.height));
			}
		}
		
		// Intersect with left?
		if (vIntersectLeft != NO_INTERSECT) {
			if (bigger.y <= vIntersectLeft && vIntersectLeft <= bigger.y+bigger.height) {
				double distance = distanceToIntersectionVPlane(ball, angle, bigger.x);
				if (speed >= distance)
					return new Result(bounceAngleVPlane(angle), distance, new Coordinate(bigger.x, vIntersectLeft));
			}
		}
		
		// Intersect with right?
		if (vIntersectRight != NO_INTERSECT) {
			if (bigger.y <= vIntersectRight && vIntersectRight <= bigger.y+bigger.height) {
				double distance = distanceToIntersectionVPlane(ball, angle, bigger.x + bigger.width);
				if (speed >= distance)
					return new Result(bounceAngleVPlane(angle), distance, new Coordinate(bigger.x + bigger.width, vIntersectRight));
			}
		}
		
		// Missed!
		return null;
	}
}
