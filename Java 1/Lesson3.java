/**
 * Java. Level 1. Lesson 3. Homework: Tic-tac-toe in a procedural style
 * Objective: To improve the source code of the game
 * Modification the source code:
 * 1. The size of the playing field is designed as a constant
 * 2. The game can be interrupted by entering a negative coordinates
 * 3. In checkWin() function used cycles
 * 
 * @author Sergey Iryupin
 * @version 17 June 2016
 */
import java.util.Scanner;
import java.util.Random;

public class Lesson3 {

    static final int field_size = 3;
    static char[][] field = new char[field_size][field_size];
    static final char dotHuman = 'x';
    static final char dotAI = 'o';
    static final char dotEmpty = 0xB7;

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String args[]) {

        initField();
        printField();

        while (true) { 
            if (!turnHuman()) {
                System.out.println("::Game aborted");
                break;
            }
            printField();
            if (isFieldFull()) {
                System.out.println("::Draw");
                break;
            }
            if (checkWin(dotHuman)) {
                System.out.println("::You won!");
                break;
            }
            System.out.println("A computer:");
            turnAI();
            printField();
            if (isFieldFull()) {
                System.out.println("::Draw");
                break;
            }
            if (checkWin(dotAI)) {
                System.out.println("::Computer won!");
                break;
            }
        }
    }

    /**
     * Initialize the playing field
     */
    public static void initField() {
        for (int y = 0; y < field_size; y++) {
            for (int x = 0; x < field_size; x++) {
                field[x][y] = dotEmpty;
            }
        }
    }

    /**
     * Displays of the playing field
     */
    public static void printField() {
       for (int y = 0; y <= field_size; y++) {
           System.out.print(y);
       }
       System.out.println();
       for (int y = 0; y < field_size; y++) {
           System.out.print(y + 1);
           for (int x = 0; x < field_size; x++) {
               System.out.print(field[x][y]);
           }
           System.out.println();
       }
    }

    /**
     * Player Turn - Human
     */
    public static boolean turnHuman() {
        int x, y;
        do {
            System.out.print("Your turn X Y (1.."+field_size+")\n>");
            x = sc.nextInt();
            y = sc.nextInt();
            if (x < 0 || y < 0) { // interruption of the game
                return false;
            }
        } while (!isCellBusy(x - 1, y - 1, dotEmpty));
        field[x - 1][y - 1] = dotHuman;
        return true;
    }

    /**
     * Player Turn - Computer
     */
    public static void turnAI() {
		int x, y;
        do {
            x = rand.nextInt(field_size);
            y = rand.nextInt(field_size);
        } while (!isCellBusy(x, y, dotEmpty));
        field[x][y] = dotAI;
    }

    /**
     * Check - is the cell at coordinates free?
     */
    public static boolean isCellBusy(int _x, int _y, char _ch) {
        if(_x < 0 || _y < 0 || _x > (field_size - 1) || _y > (field_size - 1))
            return false;
        if (field[_x][_y] == _ch)
            return true;
        return false;
    }

    /**
     * Checking for a draw: whole field is filled?
     */
    public static boolean isFieldFull() {
        for (int y = 0; y < field_size; y++) {
            for (int x = 0; x < field_size; x++) {
                if (field[x][y] == dotEmpty)
                    return false;
            }
        }
        return true;
    }

    /**
     * Checking victory
     */
    public static boolean checkWin(char _xo) {
        // checking horizontals / verticals
        for (int i = 0; i < field_size; i++) {
            if (field[i][0] == _xo && field[i][1] == _xo && field[i][2] == _xo) return true;
            if (field[0][i] == _xo && field[1][i] == _xo && field[2][i] == _xo) return true;
        }
        // checking diagonals
        if(field[0][0] == _xo && field[1][1] == _xo && field[2][2] == _xo) return true;
        if(field[2][0] == _xo && field[1][1] == _xo && field[0][2] == _xo) return true;
        return false;
    }
}