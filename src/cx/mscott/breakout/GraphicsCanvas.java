package cx.mscott.breakout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class GraphicsCanvas extends JPanel implements ActionListener {
	private static final long serialVersionUID = -1659288567154587140L;

	private int width;
	private int height;
	
	public GraphicsCanvas() {
		width = 100;
		height = 200;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isFocusable() {
		return true;
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
}
