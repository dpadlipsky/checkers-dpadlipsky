package com.example.davidpadlipsky.checkers;

import android.util.Log;

import java.util.Stack;

/**
 * Created by davidpadlipsky on 9/13/17.
 */

public class gameBoard {
    public char arr[][] = new char[8][8];
    int gameOverFlag;
    private static final String TAG = "OnePlayerCheckers";

    gameBoard() {
        gameOverFlag = 0;
    }

    public void reset() {
        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                arr[m][n] = ' ';
            }
        }
        for (int n = 0; n < 4; n++) {
            arr[7][(2 * n)] = 'x';
            arr[5][(2 * n)] = 'x';
            arr[1][(2 * n)] = 'o';
        }
        for (int n = 1; n < 5; n++) {
            arr[0][2 * n - 1] = 'o';
            arr[2][2 * n - 1] = 'o';
            arr[6][2 * n - 1] = 'x';
        }
        for (int n = 0; n < 4; n++) {
            for (int m = 0; m < 4; m++) {
                arr[2 * m][2 * n] = (char) '#';//Char code 219
            }
        }
        for (int n = 1; n < 5; n++) {
            for (int m = 1; m < 5; m++) {
                arr[2 * m - 1][2 * n - 1] = (char) '#';
            }
        }
    }

    gameBoard(char array[][]) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                array[i][j] = arr[i][j];
            }
        }
    }

    public boolean pieceEmpty(int xVal, int yVal) {
        if (arr[yVal][xVal] == ' ') {
            return true;
        } else
            return false;
    }

    public boolean pieceX(int xVal, int yVal) {
        if (arr[yVal][xVal] == 'x') {
            return true;
        } else
            return false;
    }

    public boolean pieceO(int xVal, int yVal) {
        if (arr[yVal][xVal] == 'o') {
            return true;
        } else
            return false;
    }

    public boolean pieceXKing(int xVal, int yVal) {
        if (arr[yVal][xVal] == 'X') {
            return true;
        } else
            return false;
    }

    public boolean pieceOKing(int xVal, int yVal) {
        if (arr[yVal][xVal] == 'O') {
            return true;
        } else
            return false;
    }

    public boolean validMoveUp(int xVal1, int yVal1, int xVal2, int yVal2) {
        if ((xVal1 - 1 == xVal2 || xVal1 + 1 == xVal2) && yVal1 - 1 == yVal2 && pieceEmpty(xVal2, yVal2))
            return true;
        else
            return false;
    }

    public boolean validMoveDown(int xVal1, int yVal1, int xVal2, int yVal2) {
        if ((xVal1 - 1 == xVal2 || xVal1 + 1 == xVal2) && yVal1 + 1 == yVal2 && pieceEmpty(xVal2, yVal2))
            return true;
        else
            return false;
    }

    public boolean validJumpUp(int xVal1, int yVal1, int xVal2, int yVal2) {
        if ((xVal1 - 2 == xVal2 || xVal1 + 2 == xVal2) && yVal1 - 2 == yVal2 && pieceEmpty(xVal2, yVal2))
            return true;
        else
            return false;
    }

    public boolean validJumpDown(int xVal1, int yVal1, int xVal2, int yVal2) {
        if ((xVal1 - 2 == xVal2 || xVal1 + 2 == xVal2) && yVal1 + 2 == yVal2 && pieceEmpty(xVal2, yVal2))
            return true;
        else
            return false;
    }

    public boolean validMoveX(int xVal1, int yVal1, int xVal2, int yVal2) {
        if (xVal2 > 7 || xVal2 <0 ||yVal2>7||yVal2<0){
            return false;
        }
        else {
            if (validMoveUp(xVal1, yVal1, xVal2, yVal2) && pieceX(xVal1, yVal1))
                return true;
            else if (pieceXKing(xVal1, yVal1) && (validMoveUp(xVal1, yVal1, xVal2, yVal2) || validMoveDown(xVal1, yVal1, xVal2, yVal2)))
                return true;
            else
                return false;
        }
    }

    public boolean validMoveO(int xVal1, int yVal1, int xVal2, int yVal2) {
        if (xVal2 > 7 || xVal2 <0 ||yVal2>7||yVal2<0){
            return false;
        }
        else {
            if (validMoveDown(xVal1, yVal1, xVal2, yVal2) && pieceO(xVal1, yVal1))
                return true;
            else if (pieceOKing(xVal1, yVal1) && (validMoveUp(xVal1, yVal1, xVal2, yVal2) || validMoveDown(xVal1, yVal1, xVal2, yVal2)))
                return true;
            else
                return false;
        }
    }

    public boolean validJumpX(int xVal1, int yVal1, int xVal2, int yVal2) {
        if (xVal2 > 7 || xVal2 <0 ||yVal2>7||yVal2<0){
            return false;
        }
        else {
            if (validJumpUp(xVal1, yVal1, xVal2, yVal2) && pieceX(xVal1, yVal1) && (pieceO((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2) || pieceOKing((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)))
                return true;
            else if ((validJumpUp(xVal1, yVal1, xVal2, yVal2) || validJumpDown(xVal1, yVal1, xVal2, yVal2)) && pieceXKing(xVal1, yVal1) && (pieceO((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2) || pieceOKing((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)))
                return true;
            else
                return false;
        }
    }

    public boolean validJumpO(int xVal1, int yVal1, int xVal2, int yVal2) {
        if (xVal2 > 7 || xVal2 <0 ||yVal2>7||yVal2<0){
            return false;
        }
        else {
            if (validJumpDown(xVal1, yVal1, xVal2, yVal2) && pieceO(xVal1, yVal1) && (pieceX((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2) || pieceXKing((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)))
                return true;
            else if ((validJumpUp(xVal1, yVal1, xVal2, yVal2) || validJumpDown(xVal1, yVal1, xVal2, yVal2)) && pieceOKing(xVal1, yVal1) && (pieceX((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2) || pieceXKing((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)))
                return true;
            else
                return false;
        }
    }

    public void updateGameboard(int xVal1, int yVal1, int xVal2, int yVal2, boolean ifJump) {
        if (pieceX(xVal1, yVal1)) {
            arr[yVal1][xVal1] = ' ';
            arr[yVal2][xVal2] = 'x';
        } else if (pieceO(xVal1, yVal1)) {
            arr[yVal1][xVal1] = ' ';
            arr[yVal2][xVal2] = 'o';
        } else if (pieceXKing(xVal1, yVal1)) {
            arr[yVal1][xVal1] = ' ';
            arr[yVal2][xVal2] = 'X';
        } else if (pieceOKing(xVal1, yVal1)) {
            arr[yVal1][xVal1] = ' ';
            arr[yVal2][xVal2] = 'O';
        }

        if (ifJump) {
            arr[(yVal1 + yVal2) / 2][(xVal1 + xVal2) / 2] = ' ';
        }
    }

    private boolean forcedJumpUp(char ch1, char ch2) {
        boolean forcedJump = false;
        int oneUp;
        int twoUp;
        int oneLeft, oneRight, twoLeft, twoRight;
        for (int n = 2; n < 8; n++) {
            for (int m = 0; m < 6; m++) {
                oneUp = n - 1;
                twoUp = n - 2;
                oneLeft = m - 1;
                oneRight = m + 1;
                twoLeft = m - 2;
                twoRight = m + 2;
                if ((arr[n][m] == ch1) && (((arr[oneUp][oneRight] == ch2 || arr[oneUp][oneRight] == Character.toUpperCase(ch2)) &&
                        arr[twoUp][twoRight] == ' '))) {
                    forcedJump = true;

                }
            }
            for (int m = 2; m < 8; m++) {
                oneUp = n - 1;
                twoUp = n - 2;
                oneLeft = m - 1;
                oneRight = m + 1;
                twoLeft = m - 2;
                twoRight = m + 2;
                if ((arr[n][m] == ch1) && ((arr[oneUp][oneLeft] == ch2 || arr[oneUp][oneLeft] == Character.toUpperCase(ch2)) &&
                        arr[twoUp][twoLeft] == ' ')) {
                    forcedJump = true;

                }

            }
        }
        return forcedJump;
    }

    private boolean forcedJumpDown(char ch1, char ch2) {
        boolean forcedJump = false;
        int oneDown;
        int twoDown;
        int oneLeft, oneRight, twoLeft, twoRight;
        for (int n = 0; n < 6; n++) {
            for (int m = 0; m < 6; m++) {
                oneDown = n + 1;
                twoDown = n + 2;
                oneRight = m + 1;
                twoRight = m + 2;

                if ((arr[n][m] == ch1 && (((arr[oneDown][oneRight] == ch2 || arr[oneDown][oneRight] == Character.toUpperCase(ch2)) &&
                        arr[twoDown][twoRight] == ' ')))) {
                    forcedJump = true;
                }
            }
            for (int m = 2; m < 8; m++) {
                oneDown = n + 1;
                twoDown = n + 2;
                oneLeft = m - 1;
                oneRight = m + 1;
                twoLeft = m - 2;
                twoRight = m + 2;

                if ((arr[n][m] == ch1 && ((arr[oneDown][oneLeft] == ch2 || arr[oneDown][oneLeft] == Character.toUpperCase(ch2)) &&
                        arr[twoDown][twoLeft] == ' '))) {
                    forcedJump = true;
                }
            }
        }


        return forcedJump;
    }

    ;

    public boolean xForcedJump() {
        boolean forcedJump = false;
        if (forcedJumpUp('x', 'o')) {
            forcedJump = true;
        }
        if (forcedJumpUp('X', 'o') || forcedJumpDown('X', 'o')) {
            forcedJump = true;
        }
        return forcedJump;
    }

    public boolean oForcedJump() {
        boolean forcedJump = false;
        if (forcedJumpDown('o', 'x')) {
            forcedJump = true;
        }
        if (forcedJumpDown('O', 'x') || forcedJumpUp('O', 'x')) {
            forcedJump = true;
        }
        return forcedJump;
    }

    public int xCounter() {
        int xCount = 0;
        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                if (arr[n][m] == 'x' || arr[n][m] == 'X') {
                    xCount = xCount + 1;
                }

            }
        }
        return (xCount);
    }

    public int xCounterKing() {
        int xCount = 0;
        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                if (arr[n][m] == 'X') {
                    xCount = xCount + 1;
                }

            }
        }
        return (xCount);
    }

    public int oCounter() {
        int oCount = 0;
        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                if (arr[n][m] == 'o' || arr[n][m] == 'O') {
                    oCount = oCount + 1;
                }

            }
        }
        return (oCount);
    }

    public int oCounterKing() {
        int oCount = 0;
        for (int n = 0; n < 8; n++) {
            for (int m = 0; m < 8; m++) {
                if (arr[n][m] == 'O') {
                    oCount = oCount + 1;
                }

            }
        }
        return (oCount);
    }

    private int gameOver(int turnNumber, int jumplessTurns) {
        PossibleMove arr[] = new PossibleMove[100];
        AI aiMoveChecker = new AI();
        if (xCounter() == 0) {
            return 2;
        } else if (oCounter() == 0) {
            return 1;
        } else if (turnNumber % 2 == 0) {
            int xs = aiMoveChecker.xPossibleMoves(this, arr);
            if (xs == 0) {
                return 2;
            } else if (jumplessTurns >= 80) {
                return 3;
            } else {
                return 0;
            }
        } else if (turnNumber % 2 == 1) {
            int os = aiMoveChecker.oPossibleMoves(this, arr);
            if (os == 0) {
                return 1;
            } else if (jumplessTurns >= 80) {
                return 3;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public void setGameOver(int turnNumber, int jumplessTurns) {
        gameOverFlag = gameOver(turnNumber, jumplessTurns);
    }

    public void updateKings() {
        for (int i = 0; i < 8; i = i + 2) {
            if (pieceO(i, 7)) {
                arr[7][i] = 'O';
            }
            if (pieceX(i + 1, 0)) {
                arr[0][i + 1] = 'X';
            }
        }
    }

    private boolean doubleJumpDown(int xVal1, int yVal1, char ch1, char ch2) {
        boolean doubleJump = false;
        if (xVal1 < 6) {
            if (yVal1 < 6) {
                if ((arr[yVal1][xVal1] == ch1 && (((arr[yVal1 + 1][xVal1 + 1] == ch2 || arr[yVal1 + 1][xVal1 + 1] == Character.toUpperCase(ch2)) &&
                        arr[yVal1 + 2][xVal1 + 2] == ' ')))) {
                    doubleJump = true;
                }
            }
        }
        if (xVal1 > 1) {
            if (yVal1 < 6) {
                if ((arr[yVal1][xVal1] == ch1 && (((arr[yVal1 + 1][xVal1 - 1] == ch2 || arr[yVal1 + 1][xVal1 - 1] == Character.toUpperCase(ch2)) &&
                        arr[yVal1 + 2][xVal1 - 2] == ' ')))) {
                    doubleJump = true;
                }
            }
        }
        return doubleJump;
    }

    private boolean doubleJumpUp(int xVal1, int yVal1, char ch1, char ch2) {
        boolean doubleJump = false;
        if (xVal1 < 6) {
            if (yVal1 > 1) {
                if ((arr[yVal1][xVal1] == ch1 && (((arr[yVal1 - 1][xVal1 + 1] == ch2 || arr[yVal1 - 1][xVal1 + 1] == Character.toUpperCase(ch2)) &&
                        arr[yVal1 - 2][xVal1 + 2] == ' ')))) {
                    doubleJump = true;
                }
            }
        }
        if (xVal1 > 1) {
            if (yVal1 > 1) {
                if ((arr[yVal1][xVal1] == ch1 && (((arr[yVal1 - 1][xVal1 - 1] == ch2 || arr[yVal1 - 1][xVal1 - 1] == Character.toUpperCase(ch2)) &&
                        arr[yVal1 - 2][xVal1 - 2] == ' ')))) {
                    doubleJump = true;
                }
            }
        }
        return doubleJump;
    }

    public boolean doubleJumpX(int xVal1, int yVal1) {
        if (pieceX(xVal1, yVal1) && doubleJumpUp(xVal1, yVal1, 'x', 'o')) {
            return true;
        } else if (pieceXKing(xVal1, yVal1) && (doubleJumpUp(xVal1, yVal1, 'X', 'o') || doubleJumpDown(xVal1, yVal1, 'X', 'o'))) {
            return true;
        } else
            return false;
    }

    public boolean doubleJumpO(int xVal1, int yVal1) {
        if (pieceO(xVal1, yVal1) && doubleJumpDown(xVal1, yVal1, 'o', 'x')) {
            return true;
        } else if (pieceOKing(xVal1, yVal1) && (doubleJumpUp(xVal1, yVal1, 'O', 'x') || doubleJumpDown(xVal1, yVal1, 'O', 'x'))) {
            return true;
        } else
            return false;
    }

    public void clone(gameBoard temp) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                temp.arr[i][j] = arr[i][j];
            }
        }
    }

    public int xPossibleMove(PossibleMove arr[],int xVal1, int yVal1) {
        int len = 0;
        if (!xForcedJump()) {
            if (validMoveX(xVal1, yVal1, xVal1 + 1, yVal1 + 1)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 1, yVal1 + 1, false);
                len++;
            }
            if ((validMoveX(xVal1, yVal1, xVal1 + 1, yVal1 - 1))) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 1, yVal1 - 1, false);
                len++;

            }
            if ((validMoveX(xVal1, yVal1, xVal1 - 1, yVal1 - 1))) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 1, yVal1 - 1, false);
                len++;

            }
            if ((validMoveX(xVal1, yVal1, xVal1 - 1, yVal1 + 1))) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 1, yVal1 + 1, false);
                len++;

            }
        } else {
            if (validJumpX(xVal1, yVal1, xVal1 + 2, yVal1 + 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 2, yVal1 + 2, true);
                len++;
            }
            if (validJumpX(xVal1, yVal1, xVal1 - 2, yVal1 + 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 2, yVal1 + 2, true);
                len++;
            }
            if (validJumpX(xVal1, yVal1, xVal1 + 2, yVal1 - 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 2, yVal1 - 2, true);
                len++;
            }
            if (validJumpX(xVal1, yVal1, xVal1 - 2, yVal1 - 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 2, yVal1 - 2, true);
                len++;
            }

        }
        return len;
    }
    public int oPossibleMove(PossibleMove arr[],int xVal1, int yVal1) {
        int len = 0;
        if (!oForcedJump()) {
            if (validMoveO(xVal1, yVal1, xVal1 + 1, yVal1 + 1)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 1, yVal1 + 1, false);
                len++;
            }
            if ((validMoveO(xVal1, yVal1, xVal1 + 1, yVal1 - 1))) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 1, yVal1 - 1, false);
                len++;

            }
            if ((validMoveO(xVal1, yVal1, xVal1 - 1, yVal1 - 1))) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 1, yVal1 - 1, false);
                len++;

            }
            if ((validMoveO(xVal1, yVal1, xVal1 - 1, yVal1 + 1))) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 1, yVal1 + 1, false);
                len++;

            }
        } else {
            if (validJumpO(xVal1, yVal1, xVal1 + 2, yVal1 + 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 2, yVal1 + 2, true);
                len++;
            }
            if (validJumpO(xVal1, yVal1, xVal1 - 2, yVal1 + 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 2, yVal1 + 2, true);
                len++;
            }
            if (validJumpO(xVal1, yVal1, xVal1 + 2, yVal1 - 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 + 2, yVal1 - 2, true);
                len++;
            }
            if (validJumpO(xVal1, yVal1, xVal1 - 2, yVal1 - 2)) {
                arr[len] = new PossibleMove(xVal1, yVal1, xVal1 - 2, yVal1 - 2, true);
                len++;
            }

        }
        return len;
    }
    public boolean undoMoves (Stack<undoMove> undoMoveStack){
        if (!undoMoveStack.empty()) {

            int turnNum1 = undoMoveStack.peek().turnNumber;
            int turnNum2= turnNum1;
            while (turnNum1 == turnNum2) {
                undoMove move1 = undoMoveStack.pop();
                int xVal1 = move1.xVal1;
                int xVal2 = move1.xVal2;
                int yVal1 = move1.yVal1;
                int yVal2 = move1.yVal2;
                if (pieceX(xVal2, yVal2)) {
                    arr[yVal2][xVal2] = ' ';
                    arr[yVal1][xVal1] = 'x';
                } else if (pieceO(xVal2, yVal2)) {
                    arr[yVal2][xVal2] = ' ';
                    arr[yVal1][xVal1] = 'o';
                } else if (pieceXKing(xVal2, yVal2)) {
                    arr[yVal2][xVal2] = ' ';
                    arr[yVal1][xVal1] = 'X';
                } else if (pieceOKing(xVal2, yVal2)) {
                    arr[yVal2][xVal2] = ' ';
                    arr[yVal1][xVal1] = 'O';
                }

                if (move1.jump) {
                    arr[(yVal1 + yVal2) / 2][(xVal1 + xVal2) / 2] = move1.jumpPiece;
                }
                if (move1.king){
                    if (pieceXKing(xVal1, yVal1)) {
                        arr[yVal1][xVal1] = 'x';
                    } else if (pieceOKing(xVal1, yVal1)) {
                        arr[yVal1][xVal1] = 'o';
                    }
                }
                if (undoMoveStack.empty()) {
                    Log.v(TAG, "BROKE");
                    break;
                }
                else {
                    turnNum2 = undoMoveStack.peek().turnNumber;
                }

            }

            return true;
        }
        else {
            Log.v(TAG, "EQUALS 0");
            return false;
        }
    }

}

