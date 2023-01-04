package tictactoe;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    //static int xCounter = 0;
    //static int oCounter = 0;
    //static int xoDifference;x
    static int _Count = 9;
    static boolean xWon = false;
    static boolean oWon = false;
    static int fMove;
    static int sMove;
    static boolean isOk;

    // Methods
    public static void printGrid(char[][] elements) {
        System.out.println("---------");

        System.out.print("|");
        System.out.print(" " + elements[0][0]);
        System.out.print(" " + elements[0][1]);
        System.out.print(" " + elements[0][2] + " ");
        System.out.println("|");

        System.out.print("|");
        System.out.print(" " + elements[1][0]);
        System.out.print(" " + elements[1][1]);
        System.out.print(" " + elements[1][2] + " ");
        System.out.println("|");

        System.out.print("|");
        System.out.print(" " + elements[2][0]);
        System.out.print(" " + elements[2][1]);
        System.out.print(" " + elements[2][2] + " ");
        System.out.println("|");

        System.out.println("---------");

    }

    /*public static void countElements(String elements) {
        for (int i = 0; i < 9 ; i++) {
            if (elements.charAt(i) == 'X') {
                xCounter += 1;
            }
            if (elements.charAt(i) == 'O') {
                oCounter += 1;
            }
            xoDifference = abs(xCounter - oCounter);
        }
    }*/

    public static void analyzeGame(char[][] elements) {

        whoWon(0, 0, 0, 1, 0, 2, elements); // first row
        whoWon(1, 0, 1, 1, 1, 2, elements); // second row
        whoWon(2, 0, 2, 1, 2, 2, elements); // third row
        whoWon(0, 0, 1, 0, 2, 0, elements); // first column
        whoWon(0, 1, 1, 1, 2, 1, elements); // second column
        whoWon(0, 2, 1, 2, 2, 2, elements); // third column
        whoWon(0, 0, 1, 1, 2, 2, elements); // first diagonal
        whoWon(0, 2, 1, 1, 2, 0, elements); // second diagonal

        if (xWon) {
            System.out.println("X wins");
        } else if (oWon) {
            System.out.println("O wins");
        } else if (_Count == 0) {
            System.out.println("Draw");
        }
    }

    public static void whoWon(int a1, int a2, int b1, int b2, int c1, int c2, char[][] elements) {
        if (elements[a1][a2] == elements[b1][b2] &&
            elements[b1][b2] == elements[c1][c2]) {
            if (elements[c1][c2] == 'X') {
                xWon = true;
            }
            if (elements[c1][c2] == 'O') {
                oWon = true;
            }
        }
    }
    public static void move(char[][] gridArray, char e) {
        isOk = true;
        while (isOk) {
            String fInput = scanner.next();
            if (fInput.matches("\\d+")) {
                fMove = Integer.parseInt(fInput) - 1;
            } else {
                System.out.println("You should enter numbers!");
            }

            String sInput = scanner.next();
            if (fInput.matches("\\d+")) {
                sMove = Integer.parseInt(sInput) - 1;
            } else {
                System.out.println("You should enter numbers!");
            }

            if (fMove > 2 || fMove < 0 || sMove > 2 || sMove < 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (gridArray[fMove][sMove] == ' ') {
                gridArray[fMove][sMove] = e;
                _Count -= 1;
                isOk = false;
            } else if (gridArray[fMove][sMove] == 'X' ||
                    gridArray[fMove][sMove] == 'O') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
        }
    }
    public static void main(String[] args) {


        //create empty grid
        char[][] gridArray = new char[3][3];

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                gridArray[j][i] = ' ';
            }
        }

        // prints empty grid
        printGrid(gridArray);


        while (!xWon || !oWon) {
            move(gridArray, 'X');
            printGrid(gridArray);
            analyzeGame(gridArray);
            if (xWon || oWon) {
                return;
            }
            move(gridArray, 'O');
            printGrid(gridArray);
            analyzeGame(gridArray);
            if (xWon || oWon) {
                return;
            }
        }

        //printGrid(userInput);

    }
}
