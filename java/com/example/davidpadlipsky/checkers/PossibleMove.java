package com.example.davidpadlipsky.checkers;

/**
 * Created by davidpadlipsky on 9/14/17.
 */

public class PossibleMove {
    int xVal1;
    int yVal1;
    int xVal2, yVal2;
    double gameState;
    boolean jump;
    PossibleMove(){

    }
    PossibleMove(int x1, int y1, int x2, int y2, boolean jp){
        xVal1=x1;
        yVal1=y1;
        xVal2=x2;
        yVal2 = y2;
        jump = jp;
    }
    public void OcalcGameState(gameBoard game){
        gameState=(game.oCounter()+(.5*game.oCounterKing()))-
                (game.xCounter()+(.5*game.xCounterKing()));
//        gameState=game.oCounter()-game.xCounter();
//        double gs=0;
//        for (int i =0;i<8;i++){
//            for(int j=0;j<8;j++){
//                if (game.pieceX(i,j))
//            }
//        }
    }
}
