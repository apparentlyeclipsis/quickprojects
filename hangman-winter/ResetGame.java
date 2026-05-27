/**
 *   █▀█ █▀▀ █▀▀ █▀▀ ▀█▀ 
 *   █▀▄ ██▄ ▄██ ██▄  █  
 * 
 *  here you can new game
 */
 
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class ResetGame {
    // variables
    private JFrame frame;
    private JButton button;
    
    // accessors, mutators
    JFrame getFrame() { return this.frame; }
    JButton getButton() { return this.button; }
    
    void setFrame(JFrame frame) { this.frame = frame; }
    void setButton(JButton button) { this.button = button; }
    
    // constructor
    public ResetGame() {
        this.frame = new JFrame();
        this.frame.setLocation(550, 20);
        this.frame.setSize(90, 40);
        
        this.button = new JButton();
        this.frame.add(this.button);
        this.button.setBounds(0, 0, 90, 40);
        this.button.setIcon(new ImageIcon("images/newgame-bt.png"));
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                MyProgram.gameHandler(); // start new game
            }
        });
        
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        
        // i just learned i accidentally made the same warning twice. hell.
    }
}
