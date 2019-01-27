package watch;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


class MyPanel extends JPanel{

	public static final long serialVersionUID = 1L;
	public static final int XORIGIN = 340;
	public static final int YORIGIN = 40;
	public static final int REDIUS = 600;
	public static final double PI = 3.1415926535;
	public static final int MAX_HOUR = 60 * 60 * 12;
	public static final int MAX_MINUTE = 60 * 60;
	public static final int MAX_SECOND = 60;
	public static final int CX = XORIGIN + REDIUS / 2;
	public static final int CY = YORIGIN + REDIUS / 2;

	private Image image;
	
	MyPanel(){
		setBackground(Color.magenta);
		URL url = this.getClass().getResource("/image/backgroundImage.jpg");
		image = new ImageIcon(url).getImage();
	}

	

	void setTimeDraw(Graphics g) {
		double startPoint = -1 * (PI / 2);
		double stepMinute = 2 * PI / MAX_MINUTE;
		double stepSecond = 2 * PI / MAX_SECOND;
		double stepHour = 2 * PI / MAX_HOUR;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
		String time = format.format(date);
		//System.out.println(format.format(date));
		int hour = Integer.parseInt(time.substring(0, 2));
		int minute = Integer.parseInt(time.substring(3, 5));
		int second = Integer.parseInt(time.substring(6, 8));
		
		g.setColor(Color.black);
		Stroke stroke = new BasicStroke(2f);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(stroke);
		
		startPoint += stepSecond * second;
		int secondX = (int)((REDIUS / 2 - 60) * Math.cos(startPoint));
		int secondY = (int)((REDIUS / 2 - 60) * Math.sin(startPoint));
		g.drawLine(CX, CY, CX + secondX, CY + secondY);
		
		stroke = new BasicStroke(7f);
		g2.setStroke(stroke);
		
		startPoint = -1 * (PI / 2);
		startPoint += stepMinute * second + stepMinute * minute * 60;
		int minuteX = (int)((REDIUS / 2 - 20) * Math.cos(startPoint));
		int minuteY = (int)((REDIUS / 2 - 20) * Math.sin(startPoint));
		g.drawLine(CX, CY, CX + minuteX, CY + minuteY);
		
		startPoint = -1 * (PI / 2);
		startPoint += stepHour * second + stepHour * minute * 60 + stepHour * hour * 60 * 60;
		//System.out.println(startPoint);
		int hourX = (int)((REDIUS / 2 - 100) * Math.cos(startPoint));
		int hourY = (int)((REDIUS / 2 - 100) * Math.sin(startPoint));
		g.drawLine(CX, CY, CX + hourX, CY + hourY);
		
		startPoint = -1 * (PI / 2);
		stroke = new BasicStroke(1f);
		g.setColor(Color.black);
		g2.setStroke(stroke);
		for(int i = 0; i < MAX_SECOND; i++) {
			int w = 0;
			if(i % 5 == 0) {
				stroke = new BasicStroke(2f);
				g2.setStroke(stroke);
			} else {
				stroke = new BasicStroke(1f);
				g2.setStroke(stroke);
				w = 15;
			}
			int sx = (int)((REDIUS / 2 - 30) * Math.cos(startPoint));
			int sy = (int)((REDIUS / 2 - 30) * Math.sin(startPoint));
			int ex = (int)((REDIUS / 2 - w) * Math.cos(startPoint));
			int ey = (int)((REDIUS / 2 - w) * Math.sin(startPoint));
			g.drawLine(CX + sx, CY + sy, CX + ex, CY + ey);
			startPoint += stepSecond;
			int tx = (int)((REDIUS / 2 - 50) * Math.cos(startPoint));
			int ty = (int)((REDIUS / 2 - 50) * Math.sin(startPoint));
			if((i+1) % 5 == 0) {
				g.setFont(new Font("메이플스토리", Font.BOLD, 20));
				g.setColor(Color.black);
				g.drawString(((i+1)/5)+"", CX + tx - 5, CY + ty + 5);
			}
		}
		g.setColor(Color.BLUE);
		g.setFont(new Font("메이플스토리", Font.ITALIC, 20));
		g.drawString("[glam◈home]", 700, 345);
	}

	

	@Override

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		rendering((Graphics2D)g);
		g.setColor(Color.white);
		g.fillOval(XORIGIN, YORIGIN, REDIUS, REDIUS);
		setTimeDraw(g);
		
		Stroke stroke = new BasicStroke(10f);
		Graphics2D g2 = (Graphics2D)g;

		g2.setStroke(stroke);
		g.setColor(Color.GREEN);
		g.drawOval(XORIGIN, YORIGIN, REDIUS, REDIUS);
		
		repaint();
	}
	
	private void rendering(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	
}


class MyFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	MyFrame(){
		setSize(1280, 720);
		setTitle("아날로그 시계");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		MyPanel panel1 = new MyPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(0,0,1280,720);
		add(panel1);
	}
	
}

 

public class Main {
	public static void main(String[] args) {
		JFrame frame = new MyFrame();
		frame.setVisible(true);
	}
}


