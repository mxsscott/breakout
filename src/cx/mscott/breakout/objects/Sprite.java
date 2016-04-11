package cx.mscott.breakout.objects;

import java.awt.Point;
import java.awt.Rectangle;

public class Sprite {
	/**
	 * Offset to apply to sprite position when drawing.
	 */
	protected Point offset;

	/**
	 * Construct a new sprite with graphics translation offset 0,0
	 */
	public Sprite() {
		offset = new Point(0,0);
	}

    /**
     * Get graphics offset.
     */
    public Point getOffset() {
    	return new Point(offset);
    }

    /**
     * Set graphics offset.
     * @param x new X offset.
     * @param y new Y offset.
     */
    public void setOffset(int x, int y) {
    	offset.x = x;
    	offset.y = y;
    }

    /**
     * Set graphics offset.
     * @param offset New offset.
     */
    public void setOffset(Point offset) {
    	this.offset = offset;
    }

    /**
     * Get untranslated rectangle that specifies the bounds of the sprite.
     * @return bounds of the sprite.
     */
	public Rectangle getBounds() {
		return new Rectangle(0, 0, 1, 1);
	}
	
	/**
	 * Get translated rectangle that specifies the bounds of the sprite.
	 * @return bounds of the sprite, translated to graphics offset.
	 */
	public Rectangle getGraphicsBounds() {
		Rectangle bounds = getBounds();
		bounds.translate(offset.x, offset.y);
		return bounds;
	}
}
