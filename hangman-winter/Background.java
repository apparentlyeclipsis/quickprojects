/**
 *   █▄▄ ▄▀▄ █▀▀ █▄▀ █▀▀ █▀█ █▀█ █ █ █▄ █ █▀▄ 
 *   █▄█ █▀█ █▄▄ █ █ █▄█ █▀▄ █▄█ █▄█ █ ▀█ █▄▀ 
 * 
 *  the class where the background base label frame is
 *  there's literally nothing important about this frame
 */
 
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class Background {
    // variables
    public JFrame frame;
    private JLabel label;
    
    // accessors, mutators
    JFrame getFrame() { return this.frame; }
    JLabel getLabel() { return this.label; }
    
    void setFrame(JFrame frame) { this.frame = frame; }
    void setLabel(JLabel label) { this.label = label; }
    
    // constructor
    public Background() {
        this.frame = new JFrame();
        this.frame.setSize(640, 360);
        this.frame.setLocation(10, 10); // offset 10px from top and left
        
        this.label = new JLabel();
        this.label.setBounds(0, 0, 640, 360);
        this.label.setIcon(new ImageIcon("images/background.jpg"));
        frame.add(label);
        
        this.frame.setVisible(true);
        this.frame.setLayout(null);
    }
}
