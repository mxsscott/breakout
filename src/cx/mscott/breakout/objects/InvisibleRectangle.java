package cx.mscott.breakout.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

public class InvisibleRectangle extends Rectangle implements Bounceable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6354362291600699573L;

	public InvisibleRectangle() {
		super();
	}

	public InvisibleRectangle(Rectangle r) {
		super(r);
	}

	public InvisibleRectangle(Point p) {
		super(p);
	}

	public InvisibleRectangle(Dimension d) {
		super(d);
	}

	public InvisibleRectangle(int width, int height) {
		super(width, height);
	}

	public InvisibleRectangle(Point p, Dimension d) {
		super(p, d);
	}

	public InvisibleRectangle(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
}
