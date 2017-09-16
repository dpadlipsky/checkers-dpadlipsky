package com.example.davidpadlipsky.checkers;

import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by davidpadlipsky on 9/14/17.
 */

public class AI {
    private int MAX = 1000;
    //int len;

    public int oPossibleMoves(gameBoard game, PossibleMove arr[]) {
        int len = 0;

        // gameBoard temp;
        //PossibleMove arr[MAX];
        if (game.oForcedJump()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (game.validJumpO(i, j, k, l) && len < MAX) {
                                arr[len] = new PossibleMove(i, j, k, l, true);
                                len++;
                                //possible.push(one);
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (game.validMoveO(i, j, k, l) && len < MAX) {
                                arr[len] = new PossibleMove(i, j, k, l, false);
                                len++;
                            }
                        }
                    }
                }
            }
        }
        return len;
    }

    public int xPossibleMoves(gameBoard game, PossibleMove arr[]) {
        int len = 0;
        if (game.xForcedJump()) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (game.validJumpX(i, j, k, l) && len < MAX) {
                                arr[len] = new PossibleMove(i, j, k, l, true);
                                len++;
                                //possible.push(one);
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if (game.validMoveX(i, j, k, l) && len < MAX) {
                                arr[len] = new PossibleMove(i, j, k, l, false);
                                len++;
                            }
                        }
                    }
                }
            }
        }
        return len;
    }

    public PossibleMove selectMove(gameBoard game) {
        PossibleMove arr[] = new PossibleMove[MAX];
        int len = oPossibleMoves(game, arr);
        Random rand = new Random();

        int n = rand.nextInt(len);
        return arr[n];
    }

    public PossibleMove doubleJump(gameBoard game, int l1, int n1) {
        int xVal1 = -1;
        int yVal1 = -1;
        int xVal2 = -1;
        int yVal2 = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.validJumpO(l1, n1, i, j)) {
                    xVal1 = l1;
                    yVal1 = n1;
                    xVal2 = i;
                    yVal2 = j;
                    break;
                }

            }
        }
        PossibleMove one = new PossibleMove(xVal1, yVal1, xVal2, yVal2, true);
        return one;
    }

    public PossibleMove doubleJumpX(gameBoard game, int l1, int n1) {
        int xVal1 = -1;
        int yVal1 = -1;
        int xVal2 = -1;
        int yVal2 = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.validJumpX(l1, n1, i, j)) {
                    xVal1 = l1;
                    yVal1 = n1;
                    xVal2 = i;
                    yVal2 = j;
                    break;
                }

            }
        }
        PossibleMove one = new PossibleMove(xVal1, yVal1, xVal2, yVal2, true);
        return one;
    }

    public PossibleMove findBest(gameBoard game) {
        PossibleMove bestMoves[] = new PossibleMove[100];
        PossibleMove oTurn1[] = new PossibleMove[MAX];
        int len = 0;
        int o1len = oPossibleMoves(game, oTurn1);
        gameBoard temp = new gameBoard();
        //temp = game;
        game.clone(temp);
        PossibleMove best4 = new PossibleMove();
        best4.gameState = -999;
        for (int i = 0; i < o1len; i++) {
            //cout << oTurn1[0].xVal1<<endl<<oTurn1[0].yVal1<<endl;
            //cout << "i = "<<i<<endl;
            game.clone(temp);
            temp.updateGameboard(oTurn1[i].xVal1, oTurn1[i].yVal1, oTurn1[i].xVal2, oTurn1[i].yVal2, oTurn1[i].jump);
            temp.updateKings();
            int xVal1;
            int yVal1;
            int xVal2 = oTurn1[i].xVal2;
            int yVal2 = oTurn1[i].yVal2;
            if (oTurn1[i].jump) {
                while (temp.doubleJumpO(xVal2, yVal2)) {
                    PossibleMove djo1 = doubleJump(temp, xVal2, yVal2);
                    xVal1 = djo1.xVal1;
                    yVal1 = djo1.yVal1;
                    xVal2 = djo1.xVal2;
                    yVal2 = djo1.yVal2;
                    temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                    temp.updateKings();
                }
            }
            PossibleMove xTurn1[] = new PossibleMove[MAX];
            int x1len = xPossibleMoves(temp, xTurn1);
            gameBoard temp2 = new gameBoard();
            PossibleMove best3 = new PossibleMove();
            best3.gameState = 999;
            for (int j = 0; j < x1len; j++) {
                temp.clone(temp2);
                temp2.updateGameboard(xTurn1[j].xVal1, xTurn1[j].yVal1, xTurn1[j].xVal2, xTurn1[j].yVal2, xTurn1[j].jump);
                temp2.updateKings();
                if (xTurn1[j].jump) {
                    xVal2 = xTurn1[j].xVal2;
                    yVal2 = xTurn1[j].yVal2;
                    while (temp2.doubleJumpX(xVal2, yVal2)) {
                        PossibleMove djo1 = doubleJumpX(temp2, xVal2, yVal2);
                        xVal1 = djo1.xVal1;
                        yVal1 = djo1.yVal1;
                        xVal2 = djo1.xVal2;
                        yVal2 = djo1.yVal2;
                        temp2.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                        temp2.updateKings();
                    }
                }
                PossibleMove oTurn2[] = new PossibleMove[MAX];
                int o2len = oPossibleMoves(temp2, oTurn2);
                gameBoard temp3 = new gameBoard();
                PossibleMove best2 = new PossibleMove();
                best2.gameState = -999;
                for (int k = 0; k < o2len; k++) {
                    temp2.clone(temp3);
                    temp3.updateGameboard(oTurn2[k].xVal1, oTurn2[k].yVal1, oTurn2[k].xVal2, oTurn2[k].yVal2, oTurn2[k].jump);
                    temp3.updateKings();

                    if (oTurn2[k].jump) {
                        xVal2 = oTurn2[k].xVal2;
                        yVal2 = oTurn2[k].yVal2;
                        while (temp3.doubleJumpO(xVal2, yVal2)) {
                            PossibleMove djo1 = doubleJump(temp3, xVal2, yVal2);
                            xVal1 = djo1.xVal1;
                            yVal1 = djo1.yVal1;
                            xVal2 = djo1.xVal2;
                            yVal2 = djo1.yVal2;
                            temp3.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                            temp3.updateKings();
                        }
                    }
                    PossibleMove xTurn2[] = new PossibleMove[MAX];
                    int x2len = xPossibleMoves(temp3, xTurn2);
                    gameBoard temp4 = new gameBoard();
                    PossibleMove best1 = new PossibleMove();
                    best1.gameState = 999;
                    for (int l = 0; l < x2len; l++) {
                        //cout << "l = "<<l<<endl;
                        temp3.clone(temp4);
                        temp4.updateGameboard(xTurn2[l].xVal1, xTurn2[l].yVal1, xTurn2[l].xVal2, xTurn2[l].yVal2, xTurn2[l].jump);
                        temp4.updateKings();
                        if (xTurn2[l].jump) {
                            xVal2 = xTurn2[l].xVal2;
                            yVal2 = xTurn2[l].yVal2;
                            while (temp4.doubleJumpX(xVal2, yVal2)) {
                                PossibleMove djo1 = doubleJumpX(temp4, xVal2, yVal2);
                                xVal1 = djo1.xVal1;
                                yVal1 = djo1.yVal1;
                                xVal2 = djo1.xVal2;
                                yVal2 = djo1.yVal2;
                                temp4.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                                temp4.updateKings();
                            }
                        }
                        xTurn2[l].OcalcGameState(temp4);
                        if (xTurn2[l].gameState <= best1.gameState) {
                            best1.gameState = xTurn2[l].gameState;
                            // cout<<"TURN NUMBER "<<l<<":"<<endl;
                            //cout << xTurn2[l].gameState<<endl;
                            //     best = l;
                        }
                    }
                    //  cout << best1.gameState<<endl;
                    oTurn2[k].gameState = best1.gameState;
                    if (oTurn2[k].gameState >= best2.gameState) {
                        best2.gameState = oTurn2[k].gameState;
                    }
                }
                xTurn1[j].gameState = best2.gameState;
                if (xTurn1[j].gameState <= best3.gameState) {
                    best3.gameState = xTurn1[j].gameState;
                }
            }
            oTurn1[i].gameState = best3.gameState;
            //cout << oTurn1[i].gameState<<endl;
            if (oTurn1[i].gameState > best4.gameState) {
                best4.gameState = oTurn1[i].gameState;
                bestMoves[0] = oTurn1[i];
                len = 1;
            } else if (oTurn1[i].gameState == best4.gameState) {
                bestMoves[len] = oTurn1[i];
                len++;
            }
        }
        Random rand = new Random();
        int n = rand.nextInt(len);
        return bestMoves[n];
    }
    public PossibleMove findBest3Turns(gameBoard game) {
        PossibleMove bestMoves[] = new PossibleMove[100];
        PossibleMove oTurn1[] = new PossibleMove[MAX];
        int len = 0;
        int o1len = oPossibleMoves(game, oTurn1);
        gameBoard temp = new gameBoard();
        //temp = game;
        game.clone(temp);
        PossibleMove best4 = new PossibleMove();
        best4.gameState = -999;
        for (int i = 0; i < o1len; i++) {
            //cout << oTurn1[0].xVal1<<endl<<oTurn1[0].yVal1<<endl;
            //cout << "i = "<<i<<endl;
            game.clone(temp);
            temp.updateGameboard(oTurn1[i].xVal1, oTurn1[i].yVal1, oTurn1[i].xVal2, oTurn1[i].yVal2, oTurn1[i].jump);
            temp.updateKings();
            int xVal1;
            int yVal1;
            int xVal2 = oTurn1[i].xVal2;
            int yVal2 = oTurn1[i].yVal2;
            if (oTurn1[i].jump) {
                while (temp.doubleJumpO(xVal2, yVal2)) {
                    PossibleMove djo1 = doubleJump(temp, xVal2, yVal2);
                    xVal1 = djo1.xVal1;
                    yVal1 = djo1.yVal1;
                    xVal2 = djo1.xVal2;
                    yVal2 = djo1.yVal2;
                    temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                    temp.updateKings();
                }
            }
            PossibleMove xTurn1[] = new PossibleMove[MAX];
            int x1len = xPossibleMoves(temp, xTurn1);
            gameBoard temp2 = new gameBoard();
            PossibleMove best3 = new PossibleMove();
            best3.gameState = 999;
            for (int j = 0; j < x1len; j++) {
                temp.clone(temp2);
                temp2.updateGameboard(xTurn1[j].xVal1, xTurn1[j].yVal1, xTurn1[j].xVal2, xTurn1[j].yVal2, xTurn1[j].jump);
                temp2.updateKings();
                if (xTurn1[j].jump) {
                    xVal2 = xTurn1[j].xVal2;
                    yVal2 = xTurn1[j].yVal2;
                    while (temp2.doubleJumpX(xVal2, yVal2)) {
                        PossibleMove djo1 = doubleJumpX(temp2, xVal2, yVal2);
                        xVal1 = djo1.xVal1;
                        yVal1 = djo1.yVal1;
                        xVal2 = djo1.xVal2;
                        yVal2 = djo1.yVal2;
                        temp2.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                        temp2.updateKings();
                    }
                }
                PossibleMove oTurn2[] = new PossibleMove[MAX];
                int o2len = oPossibleMoves(temp2, oTurn2);
                gameBoard temp3 = new gameBoard();
                PossibleMove best2 = new PossibleMove();
                best2.gameState = -999;
                for (int k = 0; k < o2len; k++) {
                    temp2.clone(temp3);
                    temp3.updateGameboard(oTurn2[k].xVal1, oTurn2[k].yVal1, oTurn2[k].xVal2, oTurn2[k].yVal2, oTurn2[k].jump);
                    temp3.updateKings();

                    if (oTurn2[k].jump) {
                        xVal2 = oTurn2[k].xVal2;
                        yVal2 = oTurn2[k].yVal2;
                        while (temp3.doubleJumpO(xVal2, yVal2)) {
                            PossibleMove djo1 = doubleJump(temp3, xVal2, yVal2);
                            xVal1 = djo1.xVal1;
                            yVal1 = djo1.yVal1;
                            xVal2 = djo1.xVal2;
                            yVal2 = djo1.yVal2;
                            temp3.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                            temp3.updateKings();
                        }
                    }
                    PossibleMove xTurn2[] = new PossibleMove[MAX];
                    int x2len = xPossibleMoves(temp3, xTurn2);
                    gameBoard temp4 = new gameBoard();
                    PossibleMove best1 = new PossibleMove();
                    best1.gameState = 999;
                    for (int l = 0; l < x2len; l++) {
                        //cout << "l = "<<l<<endl;
                        temp3.clone(temp4);
                        temp4.updateGameboard(xTurn2[l].xVal1, xTurn2[l].yVal1, xTurn2[l].xVal2, xTurn2[l].yVal2, xTurn2[l].jump);
                        temp4.updateKings();
                        if (xTurn2[l].jump) {
                            xVal2 = xTurn2[l].xVal2;
                            yVal2 = xTurn2[l].yVal2;
                            while (temp4.doubleJumpX(xVal2, yVal2)) {
                                PossibleMove djo1 = doubleJumpX(temp4, xVal2, yVal2);
                                xVal1 = djo1.xVal1;
                                yVal1 = djo1.yVal1;
                                xVal2 = djo1.xVal2;
                                yVal2 = djo1.yVal2;
                                temp4.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                                temp4.updateKings();
                            }
                        }
                        PossibleMove oTurn3[] = new PossibleMove[MAX];
                        int o3len = oPossibleMoves(temp4, oTurn3);
                        gameBoard temp5 = new gameBoard();
                        PossibleMove best0 = new PossibleMove();
                        best0.gameState = -999;
                        for (int m = 0; m < o3len; m++) {
                            //cout << "l = "<<l<<endl;
                            temp4.clone(temp5);
                            temp5.updateGameboard(oTurn3[m].xVal1, oTurn3[m].yVal1, oTurn3[m].xVal2, oTurn3[m].yVal2, oTurn3[m].jump);
                            temp5.updateKings();
                            if (oTurn3[m].jump) {
                                xVal2 = oTurn3[m].xVal2;
                                yVal2 = oTurn3[m].yVal2;
                                while (temp5.doubleJumpX(xVal2, yVal2)) {
                                    PossibleMove djo1 = doubleJumpX(temp5, xVal2, yVal2);
                                    xVal1 = djo1.xVal1;
                                    yVal1 = djo1.yVal1;
                                    xVal2 = djo1.xVal2;
                                    yVal2 = djo1.yVal2;
                                    temp5.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                                    temp5.updateKings();
                                }
                            }
                            PossibleMove xTurn3[] = new PossibleMove[MAX];
                            int x3len = xPossibleMoves(temp5, xTurn3);
                            gameBoard temp6 = new gameBoard();
                            PossibleMove best01 = new PossibleMove();
                            best01.gameState = 999;
                            for (int n = 0; n < x3len; n++) {
                                //cout << "l = "<<l<<endl;
                                temp5.clone(temp6);
                                temp6.updateGameboard(xTurn3[n].xVal1, xTurn3[n].yVal1, xTurn3[n].xVal2, xTurn3[n].yVal2, xTurn3[n].jump);
                                temp6.updateKings();
                                if (xTurn3[n].jump) {
                                    xVal2 = xTurn3[n].xVal2;
                                    yVal2 = xTurn3[n].yVal2;
                                    while (temp6.doubleJumpX(xVal2, yVal2)) {
                                        PossibleMove djo1 = doubleJumpX(temp6, xVal2, yVal2);
                                        xVal1 = djo1.xVal1;
                                        yVal1 = djo1.yVal1;
                                        xVal2 = djo1.xVal2;
                                        yVal2 = djo1.yVal2;
                                        temp6.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                                        temp6.updateKings();
                                    }
                                }
                                xTurn3[n].OcalcGameState(temp6);
                                if (xTurn3[n].gameState <= best01.gameState) {
                                    best01.gameState = xTurn3[n].gameState;
                                }
                            }
                            oTurn3[m].gameState = best01.gameState;
                            if (oTurn3[m].gameState >= best0.gameState) {
                                best0.gameState = oTurn3[m].gameState;
                            }
                        }
                        xTurn2[l].gameState = best0.gameState;
                        if (xTurn2[l].gameState <= best1.gameState) {
                            best1.gameState = xTurn2[l].gameState;
                            // cout<<"TURN NUMBER "<<l<<":"<<endl;
                            //cout << xTurn2[l].gameState<<endl;
                            //     best = l;
                        }
                    }
                    //  cout << best1.gameState<<endl;
                    oTurn2[k].gameState = best1.gameState;
                    if (oTurn2[k].gameState >= best2.gameState) {
                        best2.gameState = oTurn2[k].gameState;
                    }
                }
                xTurn1[j].gameState = best2.gameState;
                if (xTurn1[j].gameState <= best3.gameState) {
                    best3.gameState = xTurn1[j].gameState;
                }
            }
            oTurn1[i].gameState = best3.gameState;
            //cout << oTurn1[i].gameState<<endl;
            if (oTurn1[i].gameState > best4.gameState) {
                best4.gameState = oTurn1[i].gameState;
                bestMoves[0] = oTurn1[i];
                len = 1;
            } else if (oTurn1[i].gameState == best4.gameState) {
                bestMoves[len] = oTurn1[i];
                len++;
            }
        }
        Random rand = new Random();
        int n = rand.nextInt(len);
        return bestMoves[n];
    }
}
//    public PossibleMove findBest(gameBoard game)
//    {
//        PossibleMove oTurn1[] = new PossibleMove[MAX];
//        int o1len = oPossibleMoves(game, oTurn1);
//        gameBoard temp = new gameBoard();
//        game.clone(temp);
//        return oTurn1[0];
//
//    }
//}