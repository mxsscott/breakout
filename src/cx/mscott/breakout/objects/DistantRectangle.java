package cx.mscott.breakout.objects;

import java.awt.Rectangle;

public class DistantRectangle implements Comparable<DistantRectangle> {
	private double distance;
	private Rectangle rectangle;
	
	public DistantRectangle(double distance, Rectangle rectangle) {
		this.distance = distance;
		this.rectangle = rectangle;
	}

	@Override
	public int compareTo(DistantRectangle arg0) {
		double difference = this.distance - arg0.distance;
		
		if (difference < 0.0) {
			return -1;
		} else if (difference == 0.0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public double getDistance() {
		return distance;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
}
