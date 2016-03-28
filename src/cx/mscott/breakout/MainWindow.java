package cx.mscott.breakout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 4957723156493300694L;

	public MainWindow() {
        System.out.println("Created GUI on EDT? "+
                SwingUtilities.isEventDispatchThread());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new GraphicsCanvas());
		pack();
		setVisible(true);
	}

}
