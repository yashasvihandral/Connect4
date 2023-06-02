import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Driver {
    private JFrame frame;

    public Driver() {
        frame = new JFrame("Connect 4 Game");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(frame.getSize());
        frame.add(new MultiDraw(frame.getSize()));
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String... argv) {
        new Driver();
    }

    public static class MultiDraw extends JPanel implements MouseListener {
        int startX = 10;
        int startY = 10;
        int cellSize = 60;
        int turn = 2;
        int rows = 6;
        int cols = 7;
        boolean winner = false;
        boolean draw = false;
        String color = "";

        Color[][] grid = new Color[rows][cols];
        private Clip backgroundMusic;

        public MultiDraw(Dimension dimension) {
            setSize(dimension);
            setPreferredSize(dimension);
            addMouseListener(this);
            initializeGrid();
            playBackgroundMusic();
        }

		private void initializeGrid() {
            int x = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (x % 2 == 0) {
                        grid[row][col] = Color.white;
                    } else {
                        grid[row][col] = Color.white;
                    }
                    x++;
                }
            }
        }

		private void playBackgroundMusic() {
			try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("Cat2.au"));
                backgroundMusic = AudioSystem.getClip();
                backgroundMusic.open(audioInputStream);
                backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
			
		}
		
		private void stopBackgroundMusic() {
            if (backgroundMusic != null && backgroundMusic.isRunning()) {
                backgroundMusic.stop();
                backgroundMusic.close();
            }
        }
		
        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            Dimension d = getSize();
            g2.setColor(new Color(0, 0, 255));
            g2.fillRect(0, 0, d.width, d.height);
            startX = 0;
            startY = 0;

            
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    g2.setColor(grid[row][col]);
                    g2.fillOval(startX, startY, cellSize, cellSize);
                    g2.setColor(Color.black);
                    g2.drawOval(startX, startY, cellSize, cellSize);
                    startX += cellSize;
                }
                startY += cellSize;
                startX = 0;
            }

            g2.setColor(new Color(255, 255, 255));
            if (winner) {
                g2.drawString("WINNER - " + color, 450, 20);
                reset();
            } else if (draw) {
                g2.drawString("DRAW", 450, 20);
                reset();
            } else {
                if (turn % 2 == 0)
                    g2.drawString("Red's Turn", 450, 20);
                else
                    g2.drawString("Yellow's Turn", 450, 20);
            }
        }

        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (!winner && !draw) {
                if (x < (cellSize * grid[0].length) && y < (cellSize * grid.length)) {
                    int clickedRow = y / cellSize;
                    int clickedCol = x / cellSize;

                    clickedRow = dropPiece(clickedCol);

                    if (clickedRow != -1) {
                        if (turn % 2 == 0) {
                            grid[clickedRow][clickedCol] = Color.red;
                            color = "RED";
                        } else {
                            grid[clickedRow][clickedCol] = Color.yellow;
                            color = "Yellow";
                        }
                        turn++;
                        if (checkForWinner(clickedCol, clickedRow, grid[clickedRow][clickedCol])) {
                            winner = true;
                        } else if (checkForDraw()) {
                            draw = true;
                        }
                    }
                }
                repaint();
            }
        }

        public int dropPiece(int col) {
            int row = grid.length - 1;

            while (row >= 0) {
                if (grid[row][col].equals(Color.white)) {
                    return row;
                }
                row--;
            }

            return -1;
        }

        public boolean checkForWinner(int col, int row, Color c) {
            int count = 1;

            // Check west and east
            int xStart = col;
            xStart--;
            while (xStart >= 0) {
                if (grid[row][xStart].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                xStart--;
            }

            xStart = col;
            xStart++;
            while (xStart < grid[0].length) {
                if (grid[row][xStart].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                xStart++;
            }

            // Check north and south
            count = 1;
            int yStart = row;
            yStart--;
            while (yStart >= 0) {
                if (grid[yStart][col].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                yStart--;
            }

            yStart = row;
            yStart++;
            while (yStart < grid.length) {
                if (grid[yStart][col].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                yStart++;
            }

            // Check northwest and southeast
            count = 1;
            yStart = row;
            xStart = col;
            xStart--;
            yStart--;
            while (yStart >= 0 && xStart >= 0) {
                if (grid[yStart][xStart].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                yStart--;
                xStart--;
            }

            yStart = row;
            yStart++;
            xStart = col;
            xStart++;
            while (yStart < grid.length && xStart < grid.length) {
                if (grid[yStart][xStart].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                yStart++;
                xStart++;
            }

            // Check southwest and northeast
            count = 1;
            yStart = row;
            xStart = col;
            xStart--;
            yStart++;
            while (yStart < grid.length && xStart >= 0) {
                if (grid[yStart][xStart].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                yStart++;
                xStart--;
            }

            yStart = row;
            yStart--;
            xStart = col;
            xStart++;
            while (yStart >= 0 && xStart < grid[0].length) {
                if (grid[yStart][xStart].equals(c)) {
                    count++;
                } else {
                    break;
                }
                if (count == 4)
                    return true;
                yStart--;
                xStart++;
            }

            return false;
        }

        public boolean checkForDraw() {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col].equals(Color.white)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void reset() {
            initializeGrid();
            turn = 2;
            winner = false;
            draw = false;
        }

        // Unused MouseListener methods
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
    }
}
