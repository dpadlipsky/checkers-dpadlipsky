package com.example.davidpadlipsky.checkers;

/**
 * Created by davidpadlipsky on 9/21/17.
 */

public class backGroundAI {
    gameBoard game;
    PossibleMove one;
    int depth;
    public backGroundAI(gameBoard temp, PossibleMove temp1, int dp){
        game = temp;
        one = temp1;
        depth = dp;
    }
}
