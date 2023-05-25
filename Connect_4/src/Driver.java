


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

	
	String lost1 = "U LOSE";
	String win1 = "U WON";
	
	int screen_width = 900;
	int screen_height = 935;
	
	Music hop = new Music("src//hop//cat.mp3",false); 

	// instantiate object log
	int[][] yellow = new int[6][7];

	
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

		//g.setFont(font);
		//g.setColor(Color.white);
		//g.drawString(("Lives:") + Integer.toString(my_variable), 0, 80);
	    //g.setFont(font2);

		// paint sprites for carss
	/*	for (int i = 0; i < yellow1.length; i++) {
			yellow1[i].paint(g);
			
			
		
		}
		for (int i = 0; i < yellow2.length; i++) {
			yellow2[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow3.length; i++) {
			yellow3[i].paint(g);
			
		}
		
		

		for (int i = 0; i < yellow4.length; i++) {
			yellow4[i].paint(g);
			
		}
		

		for (int i = 0; i < yellow5.length; i++) {
			yellow5[i].paint(g);
			
		}
		

		for (int i = 0; i < yellow6.length; i++) {
			yellow6[i].paint(g);
			
		}
		

		for (int i = 0; i < yellow7.length; i++) {
			yellow7[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow8.length; i++) {
			yellow8[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow9.length; i++) {
			yellow9[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow10.length; i++) {
			yellow10[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow11.length; i++) {
			yellow11[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow12.length; i++) {
			yellow12[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow13.length; i++) {
			yellow13[i].paint(g);
			
		}
		for (int i = 0; i < yellow14.length; i++) {
			yellow14[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow15.length; i++) {
			yellow15[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow16.length; i++) {
			yellow16[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow17.length; i++) {
			yellow17[i].paint(g);
			
		}
		

		for (int i = 0; i < yellow18.length; i++) {
			yellow18[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow19.length; i++) {
			yellow19[i].paint(g);
			
		}
		
		for (int i = 0; i < yellow20.length; i++) {
			yellow20[i].paint(g);
			
		}
		

		for (int i = 0; i < yellow21.length; i++) {
			yellow21[i].paint(g);
			
		}
		
		for (int i = 0; i < red1.length; i++) {
			red1[i].paint(g);
			
			
		
		}
		for (int i = 0; i < red2.length; i++) {
			red2[i].paint(g);
			
		}
		
		for (int i = 0; i < red3.length; i++) {
			red3[i].paint(g);
			
		}
		
		

		for (int i = 0; i < red4.length; i++) {
			red4[i].paint(g);
			
		}
		

		for (int i = 0; i < red5.length; i++) {
			red5[i].paint(g);
			
		}
		

		for (int i = 0; i < red6.length; i++) {
			red6[i].paint(g);
			
		}
		

		for (int i = 0; i < red7.length; i++) {
			red7[i].paint(g);
			
		}
		
		for (int i = 0; i < red8.length; i++) {
			red8[i].paint(g);
			
		}
		
		for (int i = 0; i < red9.length; i++) {
			red9[i].paint(g);
			
		}
		
		for (int i = 0; i < red10.length; i++) {
			red10[i].paint(g);
			
		}
		
		for (int i = 0; i < red11.length; i++) {
			red11[i].paint(g);
			
		}
		
		for (int i = 0; i < red12.length; i++) {
			red12[i].paint(g);
			
		}
		
		for (int i = 0; i < red13.length; i++) {
			red13[i].paint(g);
			
		}
		for (int i = 0; i < red14.length; i++) {
			red14[i].paint(g);
			
		}
		
		for (int i = 0; i < red15.length; i++) {
			red15[i].paint(g);
			
		}
		
		for (int i = 0; i < red16.length; i++) {
			red16[i].paint(g);
			
		}
		
		for (int i = 0; i < red17.length; i++) {
			red17[i].paint(g);
			
		}
		

		for (int i = 0; i < red18.length; i++) {
			red18[i].paint(g);
			
		}
		
		for (int i = 0; i < red19.length; i++) {
			red19[i].paint(g);
			
		}
		
		for (int i = 0; i < red20.length; i++) {
			red20[i].paint(g);
			
		}
		

		for (int i = 0; i < red21.length; i++) {
			red21[i].paint(g);
			
		}*/
	}

	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);

	//
	public void update() {

		
		
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
		f.setTitle("Connect4");
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this); 


		for (int r = 0; r < yellow.length; r++) {	
			for (int c = 0; c < yellow[0].length; c++) {
				
				
			}
			
		}
		
		
		
		
		// Add background
		bg = new Background("Background.JPG");
		
		
		

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

