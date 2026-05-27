/**
 *   █▀▀ █▄ █ █▀█ █ █ █ █▄ ▄█ ▄▀▄ █▄ █ 
 *   ▄██ █ ▀█ █▄█ ▀▄▀▄▀ █ ▀ █ █▀█ █ ▀█ 
 *
 *  this is the file where the actual snowman stuff is lol
 */
 
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class Snowman {
    // variables
    public JFrame frame;
    private JLabel imageLabel;
    
    private int phase; // int between 0-5
    
    // function
    void updateSnowmanPhase(int newPhase) {
        if (newPhase > 5 || newPhase < -1) { 
            return; // doesn't work if its an invalid phase number
        } else if (MyProgram.guesses.checkIfWordFound()) {
            WarningLabel temp = new WarningLabel(5); System.out.println("win!"); return;
        }
        
        this.phase = newPhase;
        this.imageLabel.setIcon(new ImageIcon("images/snowman/snowman-" + this.phase + ".jpg"));
        if (newPhase == 0) {
            WarningLabel temp = new WarningLabel(4); 
        }
        
        // phase down btw. you unbuild the snowman.
    }
    
    // accessors, mutators
    int getPhase() { return this.phase; }
    JFrame getFrame() { return this.frame; }
    JLabel getImageLabel() { return this.imageLabel; }
    
    void setPhase(int phase) { this.phase = phase; }
    void setFrame(JFrame frame) { this.frame = frame; }
    void setImageLabel(JLabel imgLabel) { this.imageLabel = imgLabel; }
    
    // constructor
    public Snowman() {
        this.phase = 5; // snowman maxed out !!!
        
        this.frame = new JFrame();
        this.frame.setLocation(30, 53);
        this.frame.setSize(170, 260);
        
        this.imageLabel = new JLabel();
        this.frame.add(this.imageLabel);
        this.imageLabel.setBounds(0, 0, 170, 260); // draw in the snowman forms later.
        this.imageLabel.setIcon(new ImageIcon("images/snowman/snowman-5.jpg"));
        
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }
}
