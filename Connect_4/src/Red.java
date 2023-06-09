

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Red {

	private int x; // the x position of Car
	private int y; // the y position of Car
	private int vx; // the movement of car
	private int vy; // the movement of the car in the y direction
	private String fileName;
	private Image img;// the image of the Car
	private int width;
	private int height;

	public Red(String fileName, int startX, int startY) {
		// assignment statements for attributes

		x = startX;
		y = startY;
		width = 50;
		height = 50;
		//vx = -1;
		//vy = 0;

		img = getImage(fileName);
		init(x, y);

	}

	public Red(String fileName) {
		// assignment statements for attributes

		x = 100;
		y = 100;
		//vx = 0;
		width = 20;
		height = 0;

		img = getImage(fileName);
		init(x, y);

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
			URL imageURL = Red.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage("Cat Coin Red.JPG");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
}
