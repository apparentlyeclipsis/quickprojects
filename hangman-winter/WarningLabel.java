/**
 *   █ █ █ ▄▀▄ █▀█ █▄ █ ▀█▀ █▄ █ █▀▀ 
 *   ▀▄▀▄▀ █▀█ █▀▄ █ ▀█ ▄█▄ █ ▀█ █▄█ 
 * 
 *  im literally egoist ts bluelock now
 */

import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
 
class WarningLabel {
    // variables
    private JFrame frame;
    private JButton button;
    
    // accessors, mutators
    JFrame getFrame() { return this.frame; }
    JButton getButton() { return this.button; }
    
    void setFrame(JFrame frame) { this.frame = frame; }
    void setButton(JButton button) { this.button = button; }
    
    // constructor
    public WarningLabel(int errorNumber) {
        this.frame = new JFrame();
        this.frame.setLocation(270, 310);
        this.frame.setSize(360, 40);
        
        this.button = new JButton();
        this.frame.add(this.button);
        this.button.setBounds(0, 0, 360, 40);
        this.button.setIcon(new ImageIcon("images/warning-labels/warning-"+errorNumber+".png"));
        this.button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                getFrame().dispose(); // get rid of the jframe
                getFrame().repaint();
            }
        });
        
        this.frame.setLayout(null);
        this.frame.setVisible(true);
        
        /**
         *   notes:
         * 
         *  warning #1 - string is too long or too short (not 1 character long)
         *          #2 - string contains numbers or punctuation (need to replace image)
         *          #3 - if the letter was already guessed atleast once
         *          #4 - you lost so input is now locked
         *          #5 - victory (so input is now locked)
         * 
         */
    }
}
