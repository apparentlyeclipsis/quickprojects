/**
 *   █▄▄ █▀█ ▄▀▄ █▀█ █▀▄ 
 *   █▄█ █▄█ █▀█ █▀▄ █▄▀ 
 * 
 *  board games? how about board-of-landmines-games
 *  this class contains all the code used in handling the
 *  actual game board
 * 
 */

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Board {
    // 14x8?
    
    // variables
    JFrame frame;
    JPanel uiLayout;
    
    JButton[][] squares;
    boolean[][] revealed; // has the square been. seen?
    int[][] isMine; // represents if its a mine, flagged, both, or neither
    
    // accessors, mutators
    JFrame getFrame() { return this.frame; }
    JPanel getLayout() { return this.uiLayout; }
    
    JButton getButton(int x, int y) { return this.squares[x][y]; }
    boolean getReveal(int x, int y) { return this.revealed[x][y]; }
    int getMineValue(int x, int y) {
        if (x < 0 || x > 13 || y < 0 || y > 7) { return -1; } 
        // return invalid if out of bounds.
        
        return this.isMine[x][y];
    }
    
    void setFrame(JFrame frame) { this.frame = frame; }
    void setLayout(JPanel uiLayout) { this.uiLayout = uiLayout; }
    
    void setButton(int x, int y, JButton button) { this.squares[x][y] = button; }
    void setReveal(int x, int y, boolean revealed) { this.revealed[x][y] = revealed; }
    void setMineValue(int x, int y, int mineValue) { this.isMine[x][y] = mineValue; }
    
    // helper functions
    int selectIfMine() {
        if (MyProgram.mines >= 17) {
            return 0;
            // do nothing if atleast 15% (17 squares) of the board is already mines
        }
        
        Random randomizer = new Random();
        int selectedNumber = randomizer.nextInt(100);
        if (selectedNumber <= 15) {
            MyProgram.mines += 1;
            return 1; // 1 if mine, 2 if flagged, 3 if both
            // randomizer to generate that 15% of a mine
        }
        
        return 0;
    }
    
    void revealSquare(int x, int y, boolean isDark, boolean isBFS) {
        if (x < 0 || x > 13 || y < 0 || y > 7) { return; } // if out of bounds, stop
        if (getReveal(x, y)) { return; } // do nothing if square already revealed
        
        if (MyProgram.flagMode) {
            // if in flagging mode, do other things
            if (getMineValue(x, y) == 2 || getMineValue(x, y) == 3) {
                // if the square is flagged
                squares[x][y].setIcon(new ImageIcon("images/squares/u-"+isDark+".jpg"));
            } else {
                // if the square isnt flaggeed
                squares[x][y].setIcon(new ImageIcon("images/squares/f-"+isDark+".jpg"));
            }

            if (getMineValue(x, y) == 0) { setMineValue(x, y, 2); } // if not flagged, not mine
            else if (getMineValue(x, y) == 1) { setMineValue(x, y, 3); } // if not flagged, mine
            else if (getMineValue(x, y) == 2) { setMineValue(x, y, 0); } // if flagged, not mine
            else if (getMineValue(x, y) == 3) { setMineValue(x, y, 1); } // if flagged, mine

            
            return; // make sure only flag mode stuff is done
        }
        
        // these conditions here have to be lower otherwise they break flagging and bfs
        if (getMineValue(x, y) == 1) { loss(); return; } // if its a mine
        if ((getMineValue(x, y) == 2 || getMineValue(x, y) == 3) && !isBFS) { return; }
        // ignore attempt to reveal if square is flagged, provided you're outside of bfs search
        
        // counts nearby squares, to tell you how many mines are nearby
        int nearbyMines = 0;
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if (!(j == i && j == 0)) {
                    // makes sure its not the initial square
                    if (getMineValue(x+i, y+j) == 1 || getMineValue(x+i, y+j) == 3) {
                        // if the square is a mine, or a flagged mine
                        nearbyMines += 1;
                    }
                }
            }
        }
        
        // replace this square's image with the # of nearby mines.
        squares[x][y].setIcon(new ImageIcon("images/squares/"+nearbyMines+"-"+isDark+".jpg"));
        setReveal(x, y, true); // set the square as revealed, so its ignored by future bfs call
        if (getMineValue(x, y) == 2) { setMineValue(x, y, 0); } 
        // if it was a flagged non-mine make it no longer flagged
        if (winCheck()) {
            MyProgram.ui.gameEndItem(true);
            return;
            // don't do anything else if you won
        }
            
        if (nearbyMines > 0) {
            // if theres a mine nearby, don't continue bfs run
            return; 
        }
        
        // bfs repeat calls, necessary for the big reveal you get sometimes.
        // this chunk of code tends to result in a lag spike.
        revealSquare(x-1, y, !isDark, true); // check mine to left
        revealSquare(x-1, y-1, isDark, true); // check mine left, up
        revealSquare(x-1, y+1, isDark, true); // check mine left, down
        
        revealSquare(x+1, y, !isDark, true); // check mine to right
        revealSquare(x+1, y-1, isDark, true); // check mine right, up
        revealSquare(x+1, y+1, isDark, true); // check mine right, down
        
        revealSquare(x, y-1, !isDark, true); // check mine above
        revealSquare(x, y+1, !isDark, true); // check mine below
    }
    
    void loss() {
        // lock input, since you've now lost.
        MyProgram.inputLocked = true;
        
        // scan every square and reveal mines. follow same checker pattern as previously established.
        boolean isDark = true;
        for (int i=0; i<14; i++) {
            for (int j=0; j<8; j++) {
                if (getMineValue(i, j) == 1) {
                    squares[i][j].setIcon(new ImageIcon("images/squares/m-"+isDark+".jpg"));
                } else if (getMineValue(i, j) == 2) { // if its a false flag
                    squares[i][j].setIcon(new ImageIcon("images/squares/x-"+isDark+".jpg"));
                }
                isDark = !isDark;
            }
            isDark = !isDark;
        }
        
        MyProgram.ui.gameEndItem(false);
    }
    
    boolean winCheck() {
        // scan over every square, check if you won
        for (int x=0; x<14; x++) {
            for (int y=0; y<8; y++) {
                // check along each position in the grid
                    if (getMineValue(x, y) == 0 && !(getReveal(x, y))) {
                        // if square isnt a mine, and is unrevealed, you havent won yet.
                        return false;
                    }
            }
        }
        
        // since you now won, you don't want to accidentally die
        MyProgram.inputLocked = true;
        return true;
    }
    
    // constructors
    public Board() {
        this.squares = new JButton[14][8];
        this.revealed = new boolean[14][8];
        this.isMine = new int[14][8];
        
        this.frame = new JFrame();
        this.frame.setBounds(10, 10, 672, 384);
        
        this.uiLayout = new JPanel(new GridLayout(8, 14)); // 14x8 grid, 48x48 squares
        this.frame.add(uiLayout);
        this.uiLayout.setBounds(0, 0, 672, 384);
        
        boolean isDark = true; // to alternate btwn darker and lighter squares
        // both the initial image icons since they'll be used so often
        ImageIcon unrevealLight = new ImageIcon("images/squares/u-false.jpg");
        ImageIcon unrevealDark = new ImageIcon("images/squares/u-true.jpg");
        for (int j=0; j<8; j++) {
            for (int i=0; i<14; i++) {
                squares[i][j] = new JButton();
                this.uiLayout.add(squares[i][j]);
                this.revealed[i][j] = false;
                this.isMine[i][j] = selectIfMine(); // 15% of the board should be mines (17)
                if (isDark) { // if its on a darkened square (traditional checkered pattern)
                    squares[i][j].setIcon(unrevealDark);
                } else {
                    squares[i][j].setIcon(unrevealLight);
                } 
                
                int assignedX = i; // these 3 variables are used to get around a java behaviour,
                int assignedY = j; // where variables must be determined as 'final' on compile
                boolean squareDarkened = isDark;
                squares[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (MyProgram.inputLocked) { return; } // if input is locked, do nothing.
                        
                        // the one you clicked isnt part of bfs
                        revealSquare(assignedX, assignedY, squareDarkened, false);
                    }
                });
                
                isDark = !isDark; // first time to alternate on column
            }
            isDark = !isDark; // second time to swap  over row, otherwise lines of color
        }
        
        // 
        
        this.frame.setVisible(true);
    }
}
