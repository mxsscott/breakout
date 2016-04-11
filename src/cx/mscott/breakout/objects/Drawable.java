package cx.mscott.breakout.objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Drawable {
	public void repaint(Graphics g);
	
	public Rectangle getGraphicsBounds();
}
