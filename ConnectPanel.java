/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Hi Mr.David, I just wanted to remind you that last week we talked about me take home quiz
//and how I confused the wall with the wolverine start position. Just a reminder before you grade the quiz.
 
/**
 *
 * @author Piet
 */
public class ConnectPanel extends JFrame {
    
   //------------------------------------------------------------
   // Name-constants to represent the seeds and cell contents  
    public static final int EMPTY = 0;  
    public static final int PLAYER1 = 1;  
    public static final int PLAYER2 = 2;  
   
    // Name-constants to represent the various states of the game  
    public static final int PLAYING = 0;  
   
    // The game board and the game status  
    // note: make all these fields private    
    public int currentState = 1;  // the current state of the game, set initially equal to 1 (to start a new game)  
    public int currentPlayer; // the current player (PLAYER1 or PLAYER2)  
    public int Rows = 7, Columns = 8, AmountToWin = 4;  
    public int[][] board = new int[Rows][Columns];  
    public Scanner input = new Scanner(System.in); // the input Scanner
    MouseAdapter me;
    ArcsPanel[][] arcs;
    
  
    
 
   
   //------------------------------------------------------------
   
    public ConnectPanel(){  
       initGame();
       arcs = new ArcsPanel[Rows][Columns];
       me = new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent me) {
             ArcsPanel panel = (ArcsPanel) me.getSource();
             int y = panel.column;
             int x = availableRow(y);
             if (x == -1) return;
              
             board[x][y] = currentPlayer;
             if (currentPlayer == PLAYER1) currentPlayer = PLAYER2;
             else currentPlayer = PLAYER1; 
             arcs[x][y].repaint();
             checkForResult(x, y);
          }
       };
       JPanel p1 = new JPanel();  
       p1.setLayout(new arcsLayout(Rows, Columns)); 
   
       for (int i=0; i<Rows; i++) { 
           for (int j = 0; j < Columns; j++) {
              arcs[i][j] = new ArcsPanel(i, j);
              p1.add(arcs[i][j]);  
              arcs[i][j].addMouseListener(me);
           }
       }  
       add(p1, BorderLayout.CENTER); 
         
       this.pack();
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
       this.setVisible(true);
    } 
     
   //------------------------------------------------------------
    public void initGame() {       
        for (int row = 0; row < Rows; ++row) {  
            for (int col = 0; col < Columns; ++col) {  
                board[row][col] = EMPTY;  // all cells empty  
            }  
        }  
        currentState = PLAYING; // ready to play  
        currentPlayer = PLAYER1;  // player 1 plays first  
    }  
     
   //------------------------------------------------------------
    private void checkForResult(int row, int col) {
    	//check for 4 across
    			for(int row = 0; row<arcs.length; row++){
    				for (int col = 0;col < arcs[0].length - 3;col++){
    					if (arcs[row][col] == currentPlayer   && 
    						arcs[row][col+1] == currentPlayer &&
    						arcs[row][col+2] == currentPlayer &&
    						arcs[row][col+3] == currentPlayer){
    						return;
    					}
    				}			
    			}
    			//check for 4 up and down
    			for(int row = 0; row < arcs.length - 3; row++){
    				for(int col1 = 0; col1 < arcs[0].length; col1++){
    					if (arcs[row][col1] == currentPlayer   && 
    						arcs[row+1][col1] == currentPlayer &&
    						arcs[row+2][col1] == currentPlayer &&
    						arcs[row+3][col1] == currentPlayer){
    						return true;
    					}
    				}
    			}
    			//check upward diagonal
    			for(int row = 3; row < arcs.length; row++){
    				for(int col = 0; col < arcs[0].length - 3; col++){
    					if (arcs[row][col] == currentPlayer   && 
    						arcs[row-1][col+1] == currentPlayer &&
    						arcs[row-2][col+2] == currentPlayer &&
    						arcs[row-3][col+3] == currentPlayer){
    						return true;
    					}
    				}
    			}
    			//check downward diagonal
    			for(int row = 0; row < arcs.length - 3; row++){
    				for(int col = 0; col < arcs[0].length - 3; col++){
    					if (arcs[row][col] == currentPlayer   && 
    						arcs[row+1][col+1] == currentPlayer &&
    						arcs[row+2][col+2] == currentPlayer &&
    						grid[row+3][col+3] == currentPlayer){
    						return true;
    					}
    				}
    			}
    			return false;
    }
     
   //------------------------------------------------------------
   /**finds the first empty space in a column starting at the bottom.*/ 
    public int availableRow(int col){   
        for( int row = Rows -1; row >= 0; row--){  
            if(board[row][col] == EMPTY){  
                return row;              
            }  
        }
        return -1;    
    }    
     
   //------------------------------------------------------------
    class ArcsPanel extends JPanel {   
       int row, column;
       public ArcsPanel(int r, int c) {
          row = r;
          column = c;
          this.setBackground(Color.BLACK);
          this.setPreferredSize(new Dimension(100, 100));  // try commenting out this line!
       }
   
   //------------------------------------------------------------
       public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            Color c = 
                board[row][column] == EMPTY   ? Color.WHITE :
                board[row][column] == PLAYER1 ? Color.RED   :
                                                Color.GREEN;
            g.setColor(c);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.fillOval(10, 10, 90, 90);  
            g2d.dispose();
        }  
    } // end of nested class ArcsPanel 
     
   //------------------------------------------------------------
 
}  // end of class ConnectPanel