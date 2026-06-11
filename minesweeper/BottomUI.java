/**
 *   █ █ ▀█▀ 
 *   █▄█ ▄█▄ 
 * 
 *  ui design is my passion
 *  this class handles mode swapping, and game resetting.
 *  this is essentially a helper class, in function.
 * 
 */

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BottomUI {
    // variables
    JFrame frame;
    JButton flagMode;
    JButton resetGame;
    JLabel flagCount;
    
    // accessors, mutators
    JFrame getFrame() { return this.frame; }
    JButton getFlagModeButton() { return this.flagMode; }
    JButton getResetButton() { return this.resetGame; }
    
    // helper functions
    void changeFlagMode() {
        MyProgram.flagMode = !MyProgram.flagMode;
        // changes flag mode. if enabled, disable, else enable.
        this.flagMode.setIcon(new ImageIcon("images/buttons/swap-modes-"+MyProgram.flagMode+".jpg"));
    }
    
    public void gameEndItem(boolean victory) {
        // if true, you won. if false, you lost.
        JFrame parentFrame = new JFrame();
        JButton labelButton = new JButton();
        
        parentFrame.setBounds(480, 240, 192, 144);
        parentFrame.add(labelButton);
        labelButton.setBounds(0, 0, 192, 144);
        
        if (victory) { // basically just the notification you get at the end of a ame
            labelButton.setIcon(new ImageIcon("images/notifs/game-win-notif.jpg"));
        } else {
            labelButton.setIcon(new ImageIcon("images/notifs/game-loss-notif.jpg"));
        }
        
        labelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                parentFrame.setVisible(false);
                // if you click on it. it disappears.
                // id prefer to use .dispose() here but like.
                // not allowed to.
            }
        });
        
        parentFrame.setLayout(null);
        parentFrame.setVisible(true);
        
        // everything in here is temporary.
        // since its only used for this one thing it doesnt
        // make sense to have it class-wide
    }
    
    // constructor
    public BottomUI() {
        this.frame = new JFrame();
        this.frame.setBounds(10, 404, 672, 70); 
        // origin 10 px below game board
        
        this.flagMode = new JButton();
        this.frame.add(this.flagMode);
        this.flagMode.setBounds(10, 10, 300, 50);
        this.flagMode.setIcon(new ImageIcon("images/buttons/swap-modes-false.jpg")); // revealing squares by default
        this.flagMode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                changeFlagMode();
            }
        });
        
        this.resetGame = new JButton();
        this.frame.add(this.resetGame);
        this.resetGame.setBounds(512, 10, 150, 50);
        this.resetGame.setIcon(new ImageIcon("images/buttons/resetgame.jpg"));
        this.resetGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MyProgram.game();
            }
        });
        
        this.flagCount = new JLabel();
        this.frame.add(this.flagCount);
        this.flagCount.setBounds(330, 10, 182, 50); // 182px is total width of flag count area
        this.flagCount.setText(MyProgram.mines + " mines");
        
        
        
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }
}
