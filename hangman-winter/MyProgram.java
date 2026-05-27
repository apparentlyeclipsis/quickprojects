/**
 *   █▀▀ █▄ █ █▀█ █ █ █ █▄ ▄█ ▄▀▄ █▄ █
 *   ▄██ █ ▀█ █▄█ ▀▄▀▄▀ █ ▀ █ █▀█ █ ▀█ 
 * 
 *  they gave me access to gui's (AND OBJECTS)
 *  and now we're peaking
 * 
 *  hangman but its winter themed because of the budget
 *  cuts (demonetization is not something i can deal with rn)
 */

import java.util.*;
import java.io.*;

class MyProgram {
    // variables
    public static Background bgFrame;
    public static InputFrame input;
    public static GuessedFrame guesses;
    public static Snowman snowman;
    public static ResetGame resetButton;
    
    public static String word;
    public static boolean inputLocked;
    
    // functions
    static String selectWord() {
        try {
            Scanner wordScanner = new Scanner(new File("words.txt"));
            Random randomizer = new Random();
        
            String selectedWord = "";
            int wordNumber = randomizer.nextInt(1870); // since the file is a set # of lines i can just use a constant
        
            for (int i=0; i<wordNumber; i++) { // scroll down a randomized amount of lines
                wordScanner.nextLine();
            }
        
            selectedWord = wordScanner.nextLine().toLowerCase();
        
            System.out.println("find: " + selectedWord);
            return selectedWord;
        } catch (Exception e) {
            System.out.println("E/find: fourier");
            return "fourier"; // default word if error occurs
        }
    }
    
    public static void gameHandler() {
        // reset all frames and content
        bgFrame = null;
        input = null;
        guesses = null;
        snowman = null;
        resetButton = null;
        
        word = selectWord();
        inputLocked = false;
        
        bgFrame = new Background();
        input = new InputFrame();
        guesses = new GuessedFrame(word.length());
        snowman = new Snowman();
        resetButton = new ResetGame();
    }
    
    public static void main(String[] args) {
        gameHandler(); // start new game
    }
}
