package cx.mscott.breakout.objects;

import java.io.Serializable;

public class Distance implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -3757657921745760177L;

	/**
	 * Delta of X coordinate.
	 */
	public int deltaX;
	
	/**
	 * Delta of Y coordinate.
	 */
	public int deltaY;

	/**
	 * Construct distance of 0.
	 */
	public Distance() {
		this(0, 0);
	}
	
	/**
	 * Construct as a copy of another {@link Distance}.
	 * @param distance
	 */
	public Distance(Distance distance) {
		this(distance.deltaX, distance.deltaY);
	}
	
	/**
	 * Construct a distance from delta of X and Y.
	 * @param deltaX Delta of X coordinate.
	 * @param deltaY Delta of Y coordinate.
	 */
	public Distance(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
	
	/**
	 * Get delta X.
	 * @return delta X.
	 */
	public int getDeltaX() {
		return deltaX;
	}
	
	/**
	 * Get delta Y.
	 * @return delta Y.
	 */
	public int getDeltaY() {
		return deltaY;
	}
	
	/**
	 * Get a copy of the deltas.
	 * @return copy of deltas.
	 */
	public Distance getDeltas() {
		return new Distance(deltaX, deltaY);
	}
	
	/**
	 * Set this object to deltas of another {@link Distance}.
	 * @param other another {@link Distance}.
	 */
	public void setDeltas(Distance other) {
		this.deltaX = other.deltaX;
		this.deltaY = other.deltaY;
	}

	/**
	 * Set this object's deltas.
	 * @param deltaX New delta X.
	 * @param deltaY New delta Y.
	 */
	public void setDeltas(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	/**
	 * Compare two objects for equality.
	 * 
	 * @return true if both objects are {@link Distance} objects
	 * and have the same delta X and delta Y.
	 */
	public boolean equals(Object o) {
		if (o instanceof Distance) {
			Distance other = (Distance)o;
			if (other.deltaX == this.deltaX) {
				if (other.deltaY == this.deltaY) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Hash code.
	 */
	public int hashCode() {
		return deltaX * 10000 + deltaY;
	}
	
	/**
	 * String representation of this object.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Distance [dx=")
			.append(deltaX)
			.append(",dy=")
			.append(deltaY)
			.append("]");
		return sb.toString();
	}
}
