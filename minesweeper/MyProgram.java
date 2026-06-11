/**
 *   █▄ ▄█ ▀█▀ █▄ █ █▀▀ █▀▀ █ █ █ █▀▀ █▀▀ █▀█ █▀▀ █▀█ 
 *   █ ▀ █ ▄█▄ █ ▀█ ██▄ ▄██ ▀▄▀▄▀ ██▄ ██▄ █▀▀ ██▄ █▀▄ 
 * 
 *  here/ we sweep/ ze mines/
 *  a 14x8 game of minesweeper, where approx. 15% of
 *  the board is mines. good luck.
 */

public class MyProgram {
    // variables
    public static boolean flagMode; // if true, clicking a square will flag it
    public static int mines;
    public static boolean inputLocked; // if true, game is over
    
    static Board board; // the classes
    static BottomUI ui;
    
    // functions
    public static void game() {
        board = null;
        ui = null;
        
        flagMode = false; // by default youre revealing mines
        mines = 0;
        inputLocked = false;
        
        board = new Board();
        ui = new BottomUI();
    }
    
    public static void main(String[] args) {
        game();
    }
}
