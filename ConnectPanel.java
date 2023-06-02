 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaranch;
 
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
    private JPanel messagePanel;
    

   
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
    public static void main(String[] args) {  
        // Initialize the game-board and current status 
        new ConnectPanel();
    } 
   
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
       p1.setLayout(new GridLayout(Rows, Columns)); 
   
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
        // Check for horizontal win
        int count = 1; // Counter for consecutive discs of the same color
        int r = row;
        int c = col;

        // Check to the left
        while (c > 0 && board[r][c - 1] == board[row][col]) {
            count++;
            c--;
        }

        // Check to the right
        c = col;
        while (c < Columns - 1 && board[r][c + 1] == board[row][col]) {
            count++;
            c++;
        }

        if (count >= AmountToWin) {
            // Horizontal win
            System.out.println("Player " + currentPlayer + " wins horizontally!");
            currentState = PLAYING;
            return;
        }

        // Check for vertical win
        count = 1;
        r = row;
        c = col;

        // Check below
        while (r < Rows - 1 && board[r + 1][c] == board[row][col]) {
            count++;
            r++;
        }

        if (count >= AmountToWin) {
            // Vertical win
            System.out.println("Player " + currentPlayer + " wins vertically!");
            currentState = PLAYING;
            return;
        }

        // Check for diagonal win (top-left to bottom-right)
        count = 1;
        r = row;
        c = col;

        // Check top-left
        while (r > 0 && c > 0 && board[r - 1][c - 1] == board[row][col]) {
            count++;
            r--;
            c--;
        }

        // Check bottom-right
        r = row;
        c = col;
        while (r < Rows - 1 && c < Columns - 1 && board[r + 1][c + 1] == board[row][col]) {
            count++;
            r++;
            c++;
        }

        if (count >= AmountToWin) {
            // Diagonal win (top-left to bottom-right)
            System.out.println("Player " + currentPlayer + " wins diagonally (top-left to bottom-right)!");
            currentState = PLAYING;
            return;
        }

        // Check for diagonal win (top-right to bottom-left)
        count = 1;
        r = row;
        c = col;

        // Check top-right
        while (r > 0 && c < Columns - 1 && board[r - 1][c + 1] == board[row][col]) {
            count++;
            r--;
            c++;
        }

        // Check bottom-left
        r = row;
        c = col;
        while (r < Rows - 1 && c > 0 && board[r + 1][c - 1] == board[row][col]) {
            count++;
            r++;
            c--;
        }

        if (count >= AmountToWin) {
            // Diagonal win (top-right to bottom-left)
            System.out.println("Player " + currentPlayer + " wins diagonally (top-right to bottom-left)!");
            currentState = PLAYING;
            return;
        }

        // Check for a draw
        boolean isDraw = true;
        for (int i = 0; i < Rows; i++) {
            for (int j = 0; j < Columns; j++) {
                if (board[i][j] == EMPTY) {
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) {
                break;
            }
        }

        if (isDraw) {
            // Draw
            System.out.println("It's a draw!");
            currentState = PLAYING;
            return;
        }

        // Continue the game
        currentState = PLAYING;
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