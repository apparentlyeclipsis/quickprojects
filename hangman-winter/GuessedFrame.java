/**
 *   █▀▀ █ █ █▀▀ █▀▀ █▀▀ █▀▀ █▀▄ 
 *   █▄█ █▄█ ██▄ ▄██ ▄██ ██▄ █▄▀   
 * 
 *  the class where the all the guessed stuff is handled
 *  i did not know what to call this class
 */
 
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

class GuessedFrame {
    // variables
    public JFrame inWordFrame;
    private JPanel uiLayout;
    
    public JLabel[] letterBoxes;
    
    public JFrame incorrectFrame; // for letters that didn't work
    private JLabel iCLabel;
    
    public LinkedList<Character> guessed;
    public boolean[] charactersFound;
    
    // functions
    void updateGuessedLetters(char letter) {
        for (int i=0; i<guessed.size(); i++) {
            if (letter == guessed.get(i)) {
                WarningLabel temp = new WarningLabel(3);
                return; // please dont continue!!!!
            }
        }
        
        guessed.add(letter);
        
        boolean foundLetter = false;
        for (int i=0; i<letterBoxes.length; i++) {
            if (letter == MyProgram.word.charAt(i)) {
                letterBoxes[i].setIcon(new ImageIcon("images/characters/char-" + String.valueOf(letter) + ".png"));   
                foundLetter = true;
                charactersFound[i] = true;
            }
        }
        checkIfWordFound();
        
        if (!foundLetter) {
            if (iCLabel.getText().equals("wrong letters here :(")) {
                iCLabel.setText("");
            }
            MyProgram.snowman.updateSnowmanPhase(MyProgram.snowman.getPhase() - 1);
            iCLabel.setText(iCLabel.getText() + String.valueOf(letter));
        }
    }
    
    boolean checkIfWordFound() {
        boolean found = true;
        for (int i=0; i<charactersFound.length; i++) {
            if (found != charactersFound[i]) { found = false; break; }
        }

        if (found) { 
            WarningLabel temp = new WarningLabel(5); // call victory label
        }
        return found;
    }
    
    // accessors, mutators
    JFrame getIWFrame() { return this.inWordFrame; }
    JFrame getICFrame() { return this.incorrectFrame; }
    JLabel getICLabel() { return this.iCLabel; }
    JPanel getUILayout() { return this.uiLayout; }
    LinkedList<Character> getGuessed() { return this.guessed; }
    boolean[] getCharactersFound() { return this.charactersFound; }
    JLabel[] getLetterBoxes() { return this.letterBoxes; }
    
    void setIWFrame(JFrame iw) { this.inWordFrame = iw; }
    void setICFrame(JFrame ic) { this.incorrectFrame = ic; }
    void setICLabel(JLabel icl) { this.iCLabel = icl; }
    void setUILayout(JPanel uiLayout) { this.uiLayout = uiLayout; }
    void setGuessed(LinkedList<Character> newSet) { this.guessed = newSet; }
    void setCharactersFound(boolean[] cf) { this.charactersFound = cf; }
    void setLetterBoxes(JLabel[] lb) { this.letterBoxes = lb; }
    
    
    // constructor
    public GuessedFrame(int letters) {
        guessed = new LinkedList<Character>();
        charactersFound = new boolean[letters];
        
        // in word frame setup
        this.inWordFrame = new JFrame();
        this.inWordFrame.setLocation(220, 130);
        this.inWordFrame.setSize(420, 60);
        
        this.uiLayout = new JPanel();
        this.inWordFrame.add(this.uiLayout);
        this.uiLayout.setBounds(5, 5, 410, 50); // letter objects should be 45x40
        
        letterBoxes = new JLabel[letters];
        for (int i=0; i<letters; i++) {
            letterBoxes[i] = new JLabel();
            charactersFound[i] = false; // not found yet
            this.uiLayout.add(letterBoxes[i]);
            letterBoxes[i].setIcon(new ImageIcon("images/characters/char-empty.png"));
            
            // now figure out how to connect these to the input listeners
        }
        
        this.inWordFrame.setLayout(null);
        this.inWordFrame.setVisible(true);
        
        // incorrect letter frame setup
        this.incorrectFrame = new JFrame();
        this.incorrectFrame.setLocation(220, 90);
        this.incorrectFrame.setSize(180, 30);
        
        this.iCLabel = new JLabel();
        this.incorrectFrame.add(this.iCLabel);
        this.iCLabel.setBounds(10, 0, 160, 30);
        // this.bgLabelIC.setIcon(new ImageIcon(""));
        this.iCLabel.setText("wrong letters here :(");
        
        this.incorrectFrame.setLayout(null);
        this.incorrectFrame.setVisible(true);
    }
}
