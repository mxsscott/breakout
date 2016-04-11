package cx.mscott.breakout.objects;

import java.awt.Point;

public class Coordinate extends Point {
	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 1555754205257605126L;

	public Coordinate(int x, int y) {
		super(x, y);
	}

	public boolean isAbove(Point other) {
		return this.y < other.y;
	}

	public boolean isBelow(Point other) {
		return this.y > other.y;
	}

	public boolean isLeftOf(Point other) {
		return this.x < other.x;
	}

	public boolean isRightOf(Point other) {
		return this.x > other.x;
	}

	public Coordinate moveUp(int deltaY) {
		return new Coordinate(x, y - deltaY);
	}

	public Coordinate moveDown(int deltaY) {
		return new Coordinate(x, y + deltaY);
	}

	public Coordinate moveLeft(int deltaX) {
		return new Coordinate(x - deltaX, y);
	}

	public Coordinate moveRight(int deltaX) {
		return new Coordinate(x + deltaX, y);
	}
}
