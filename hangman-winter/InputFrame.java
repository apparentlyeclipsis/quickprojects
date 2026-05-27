/**
 *   ▀█▀ █▄ █ █▀█ █ █ ▀█▀ 
 *   ▄█▄ █ ▀█ █▀▀ █▄█  █   
 * 
 *  the class where the all the user input is handled
 *  this name actually makes sense but i still dont like it
 */
 
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class InputFrame {
    // variables
    public JFrame frame;
    private JTextField inputField;
    private JLabel bgLabel; // for backgrounds, this should always be at the bottom, initialized first (z-index)
    private JButton submitButton;
    
    // helper functions
    void callWarningLabel(int errorNumber) {
        WarningLabel temp = new WarningLabel(errorNumber);
    }
    
    // accessors, mutators
    JFrame getFrame() { return this.frame; }
    JTextField getInputField() { return this.inputField; }
    JLabel getBackgroundLabel() { return this.bgLabel; }
    JButton getSubmitButton() { return this.submitButton; }
    
    void setFrame(JFrame frame) { this.frame = frame; }
    void setInputField(JTextField inputField) { this.inputField = inputField; }
    void setBackgroundLabel(JLabel bgLabel) { this.bgLabel = bgLabel; }
    void setSubmitButton(JButton submitButton) { this.submitButton = submitButton; }
    
    // constructor
    public InputFrame() {
        this.frame = new JFrame();
        this.frame.setLocation(220, 200);
        this.frame.setSize(420, 60);
        
        this.bgLabel = new JLabel();
        this.frame.add(this.bgLabel);
        this.bgLabel.setBounds(0, 0, 420, 60);
        this.bgLabel.setIcon(new ImageIcon("images/input-area-bg.png"));
        
        this.submitButton = new JButton();
        this.frame.add(this.submitButton);
        this.submitButton.setBounds(270, 10, 140, 40);
        this.submitButton.setIcon(new ImageIcon("images/submit-bt.png"));
        this.submitButton.setBorderPainted(false); // sometimes you have to hover over the button and idk why
        this.submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // getInputField().getText()
                String textFound = getInputField().getText().toLowerCase();
                getInputField().setText(""); // reset text field
                
                if (MyProgram.guesses.checkIfWordFound()) { callWarningLabel(5); return; }
                if (MyProgram.inputLocked) { callWarningLabel(4); return; }
                if (textFound.length() != 1) { callWarningLabel(1); return; }
                if (textFound.matches("\\p{Punct}") || textFound.matches("\\d")) { callWarningLabel(2); return; } // if considered a punctuation or numeric character, no no work
                
                MyProgram.guesses.updateGuessedLetters(textFound.charAt(0));
            }
        });
        
        this.inputField = new JTextField();
        this.frame.add(this.inputField);
        this.inputField.setBounds(20, 15, 240, 30);
        
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }
}
