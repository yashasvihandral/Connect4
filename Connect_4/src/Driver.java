
//https://github.com/domingodavid/froggerEclipse

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener {

	int screen_width = 900;
	int screen_height = 935;
	//Froggy froggy;
	Music hop = new Music("src//hop//cat.mp3",false); 

	// instantiate object log
	Yellow[] carsquirrel = new Yellow[10];
	Yellow[] carsquirrel2 = new Yellow[10];
	Yellow[] carsquirrel3 = new Yellow[10];
	Yellow[] carsquirrel4 = new Yellow[10];
	Red[] carcandycane = new Red[10];
	Red[] carcandycane2 = new Red[10];
	Red[] carmushroom = new Red[10];
	Red[] carmushroom2 = new Red[10];
	

	Background bg;
	int my_variable = 3; // example

	String lose = "";
	String win = "";
	String lost = "";

	// ****************************paint
	// method******************************************
	public void paint(Graphics g) {

		super.paintComponent(g);
		bg.paint(g);

		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(("Lives:") + Integer.toString(my_variable), 0, 80);
		g.setFont(font2);

		// paint sprites for carss
		for (int i = 0; i < redsquirrel.length; i++) {
			redsquirrel[i].paint(g);
			
			
		
		}
		for (int i = 0; i < carsquirrel2.length; i++) {
			redsquirrel2[i].paint(g);
			
			if(froggy.collided( carsquirrel2[i].getX(),carsquirrel2[i].getY(),carsquirrel2[i].getWidth(),carsquirrel2[i].getHeight())) {
				my_variable = my_variable - 1; 
				froggy.setX(400); 
				froggy.setY(800); 
				}
			
			
		}
		
		for (int i = 0; i < carsquirrel3.length; i++) {
			carsquirrel3[i].paint(g);
			
			if(froggy.collided( carsquirrel3[i].getX(),carsquirrel3[i].getY(),carsquirrel3[i].getWidth(),carsquirrel3[i].getHeight())) {
				my_variable = my_variable - 1; 
				froggy.setX(400); 
				froggy.setY(800); 
				}
		}
		
		for (int i = 0; i < carsquirrel4.length; i++) {
			carsquirrel4[i].paint(g);
			
			if(froggy.collided( carsquirrel4[i].getX(),carsquirrel4[i].getY(),carsquirrel4[i].getWidth(),carsquirrel4[i].getHeight())) {
				my_variable = my_variable - 1; 
				froggy.setX(400); 
				froggy.setY(800); 
				}
		}


	for (int i = 0; i < carcandycane.length; i++) {
			carcandycane[i].paint(g);
		}
		
		for (int i = 0; i < carmushroom.length; i++) {
			carmushroom[i].paint(g);
			
		}
		for (int i = 0; i < carmushroom2.length; i++) {
			carmushroom2[i].paint(g);
		}
		
		
		for (int i = 0; i < carcandycane2.length; i++) {
			carcandycane2[i].paint(g);
		}
		
		// paint and update froggy
		froggy.paint(g);

		// car one
		g.drawString(lost, 0, 50);
		if (my_variable <= 0 ) {
			lose = " u lost";
			g.drawString(lose, 700, 50);
		}

		// resetting
		if ( froggy.getY() <= 70) {
			win = "u won!";
			g.drawString(win, 700, 50);
		}

	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);

	//
	public void update() {

		froggy.move();

		// car two
		for (int i = 0; i < carsquirrel.length; i++) {

			carsquirrel[i].setVx(-2);
			carsquirrel[i].move();
			carsquirrel2[i].setVx(-2);
			carsquirrel2[i].move();
			carsquirrel3[i].setVx(-8);
			carsquirrel3[i].move();
			
			carsquirrel4[i].setVx(-2);
			carsquirrel4[i].move();
			
			carcandycane[i].setVx(-4);
			carcandycane[i].move();
			carcandycane2[i].setVx(-4);
			carcandycane2[i].move();
			
			carmushroom[i].setVx(-10);
			carmushroom[i].move();
			carmushroom2[i].setVx(-10);
			carmushroom2[i].move();
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	/* *
	 * Used to setup all of the objects on the screen
	 */
	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Frogger");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this); //listen for keypresses on this frame

		// sprite instantiation
		//froggy = new Froggy("willy.png");

		for (int i = 0; i < carsquirrel.length; i++) {

			carsquirrel[i] = new Car("Yellow.JPG", i * 250 + 400, 200);
			
			carcandycane[i] = new Car("candycane.png", i * 300 + 300, 120);
			
			carmushroom[i] = new Car("mushroom.png", i * 350 + 300, 260);
			
            carsquirrel2[i] = new Car("squirrel.png", i * 250 + 400, 670);
            
           carsquirrel3[i] = new Car("squirrel.png", i * 250 + 400, 480);
            
            carsquirrel4[i] = new Car("squirrel.png", i * 250 + 400, 370);
			
			carcandycane2[i] = new Car("candycane.png", i * 300 + 300, 600);
			
			carmushroom2[i] = new Car("mushroom.png", i * 350 + 300, 730);
			
		}

		// leave room here to instantiate other rows of obstacles
		// aka other cars, bulldozers, trucks, turtules, logs etc
		
		
		
		
		// Add background
		bg = new Background("Background.JPG");
		
		
		// do not add to frame, call paint on
		// these objects in paint method

		f.add(this);
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	@Override
	public void keyPressed(KeyEvent e) {
		

		


		


	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

