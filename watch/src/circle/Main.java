package circle;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel{
	
	private static final int XPOINT = 200;
	private static final int YPOINT = 200;
	
	private static final int STEP = 360;
	double stepSecond = 2 * Math.PI / STEP;
	
	MyPanel(){
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		double startPoint = -1 * Math.PI / 2;
		for(int i = 0; i < STEP; i++) {
			int dx = (int)(Math.cos(startPoint) * 30 + XPOINT);
			int dy = (int)(Math.sin(startPoint) * 30 + YPOINT);
			startPoint += stepSecond;
			g.drawLine(dx, dy, dx, dy);
		}
		repaint();
	}
	
}

class MyFrame extends JFrame{
	
	MyFrame(){
		setSize(800, 600);
		getContentPane().add(new MyPanel());
	}
}

public class Main {
	public static void main(String[] args) {
		JFrame frame = new MyFrame();
		frame.setVisible(true);
	}
}
