

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Yellow {

	private int x; 
	private int y; 
	private int vx; 
	private int vy; 
	private String fileName;
	private Image img;
	private int width;
	private int height;

	public Yellow(String fileName, int startX, int startY) {
		

		x = startX;
		y = startY;
		width = 50;
		height = 50;
		vx = -1;
		vy = 0;

		img = getImage(fileName);
		init(x, y);

	}

	public Yellow(String fileName) {
		// assignment statements for attributes

		x = 0;
		y = 0;
		vx = 0;
		width = 50;
		height = 50;

		img = getImage(fileName);
		init(x, y);

	}

	// getters and setters

	

	public void setHeight(int height) {
		
	}

	public void move() {
		
		}

	

	private AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Yellow.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
