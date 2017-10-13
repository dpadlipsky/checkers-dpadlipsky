package com.example.davidpadlipsky.checkers;

/**
 * Created by davidpadlipsky on 9/19/17.
 */

public class undoMove {
    int xVal1;
    int yVal1;
    int xVal2;
    int yVal2;
    boolean jump;
    char jumpPiece;
    int turnNumber;
    boolean king;
    undoMove(int tNum,int x1, int y1, int x2, int y2, boolean jp, char pieceJ, boolean kg){
        xVal1=x1;
        yVal1=y1;
        xVal2=x2;
        yVal2=y2;
        jump=jp;
        turnNumber=tNum;
        jumpPiece=pieceJ;
        king = kg;
    }
    undoMove(int tNum,int x1, int y1, int x2, int y2, boolean jp, boolean kg){
        xVal1=x1;
        yVal1=y1;
        xVal2=x2;
        yVal2=y2;
        jump=jp;
        turnNumber=tNum;
        king = kg;
    }

}
