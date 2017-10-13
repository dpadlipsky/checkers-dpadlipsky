package com.example.davidpadlipsky.checkers;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by davidpadlipsky on 9/14/17.
 */

public class AI {
    private int MAX = 100;
    int turnsCompleted = 0;
    //int len;
    String TAG = "AI";

    private void updateBoardO(gameBoard temp, int xVal1t, int yVal1t, int xVal2t, int yVal2t, boolean jump) {
        temp.updateGameboard(xVal1t, yVal1t, xVal2t, yVal2t, jump);
        temp.updateKings();
        int xVal1;
        int yVal1;
        int xVal2 = xVal2t;
        int yVal2 = yVal2t;
        if (jump) {
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
    }

    private void updateBoardX(gameBoard temp, int xVal1t, int yVal1t, int xVal2t, int yVal2t, boolean jump) {
        temp.updateGameboard(xVal1t, yVal1t, xVal2t, yVal2t, jump);
        int xVal1;
        int yVal1;
        int xVal2 = xVal2t;
        int yVal2 = yVal2t;
        if (jump) {
            while (temp.doubleJumpX(xVal2, yVal2)) {
                PossibleMove djo1 = doubleJumpX(temp, xVal2, yVal2);
                xVal1 = djo1.xVal1;
                yVal1 = djo1.yVal1;
                xVal2 = djo1.xVal2;
                yVal2 = djo1.yVal2;
                temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                temp.updateKings();
            }
        }
        temp.updateKings();
    }

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

    double Max(double alpha, double beta, int depthleft, gameBoard game) {
        gameBoard temp = new gameBoard();
        PossibleMove arr[] = new PossibleMove[MAX];
        PossibleMove one = new PossibleMove();
        double best;
        if (depthleft == 0) {
            one.OcalcGameState(game);
            return one.gameState;
        }
        int len = oPossibleMoves(game, arr);
        best = alpha;
        outLoop:
        {
            for (int i = 0; i < len; i++) {
                game.clone(temp);
                updateBoardO(temp, arr[i].xVal1, arr[i].yVal1, arr[i].xVal2, arr[i].yVal2, arr[i].jump);
                double score = min(alpha, beta, depthleft - 1, temp);
//                best = Math.max(best, score);
                if (score > best) {
                    best = score;
                }
                if (beta <= best) {
                    break outLoop;
                }
            }
        }
        return best;
    }

    double min(double alpha, double beta, int depthleft, gameBoard game) {
        gameBoard temp = new gameBoard();
        PossibleMove arr[] = new PossibleMove[MAX];
        PossibleMove one = new PossibleMove();
        double best;
        if (depthleft == 0) {
            one.OcalcGameState(game);
            return one.gameState;
        }
        int len = xPossibleMoves(game, arr);
        best = beta;
        outLoop:
        {
            for (int i = 0; i < len; i++) {
                game.clone(temp);
                updateBoardX(temp, arr[i].xVal1, arr[i].yVal1, arr[i].xVal2, arr[i].yVal2, arr[i].jump);
                double score = Max(alpha, beta, depthleft - 1, temp);
//                best = Math.min(best, score);
                if (score < best) {
                    best = score;
                }
                if (best <= alpha) {
                    break outLoop;
                }
            }
        }
        return best;
    }

    //    double minMax(double alpha, double beta, int depthleft, gameBoard game, boolean player1) {
//        gameBoard temp = new gameBoard();
//        PossibleMove arr[] = new PossibleMove[MAX];
//        PossibleMove one = new PossibleMove();
//        double best;
//        if (depthleft == 0) {
//            one.OcalcGameState(game);
//            return one.gameState;
//        }
//        if(player1) {
//            int len = oPossibleMoves(game, arr);
//            best = alpha;
//            outLoop:
//            {
//                for (int i = 0; i < len; i++) {
//                    game.clone(temp);
//                    updateBoardO(temp, arr[i].xVal1, arr[i].yVal1, arr[i].xVal2, arr[i].yVal2, arr[i].jump);
//                    double score = minMax(alpha, beta, depthleft - 1, temp, false);
//                    best = Math.max(best, score);
//                    if (beta <= best) {
//                        break outLoop;
//                    }
//                }
//            }
//        }
//        else {
//            int len = xPossibleMoves(game, arr);
//            best = beta;
//            outLoop:
//            {
//                for (int i = 0; i < len; i++) {
//                    game.clone(temp);
//                    updateBoardX(temp, arr[i].xVal1, arr[i].yVal1, arr[i].xVal2, arr[i].yVal2, arr[i].jump);
//                    double score = minMax(alpha, beta, depthleft - 1, temp, true);
//                    best = Math.min(best, score);
//                    if (best <= alpha) {
//                        break outLoop;
//                    }
//                }
//            }
//        }
//        return best;
//    }
    public PossibleMove determineFastMove(int depth, gameBoard game) throws InterruptedException {
        turnsCompleted = 0;
        PossibleMove arr[] = new PossibleMove[MAX];
        int lenO = oPossibleMoves(game, arr);
        PossibleMove bestMoves[] = new PossibleMove[lenO];
        int len = 0;
        PossibleMove best = new PossibleMove();
        best.gameState = -Double.MAX_VALUE;
        for (int i = 0; i < lenO; i++) {
            backGroundAI pass = new backGroundAI(game, arr[i], depth);
            backGroundOneMove test = new backGroundOneMove();
            test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pass, null, null);
        }
//            gameBoard temp = new gameBoard();
//            PossibleMove one = arr[i];
//            game.clone(temp);
//            temp.updateGameboard(one.xVal1, one.yVal1, one.xVal2, one.yVal2, one.jump);
//            updateBoardX(temp, one.xVal1, one.yVal1, one.xVal2, one.yVal2, one.jump);
//            arr[i].gameState= min(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depth, temp);
        while (turnsCompleted < lenO) {
              Log.v("AI", "WAITING");
        }
        //  Thread.sleep(100);
        for (int i = 0; i < lenO; i++) {
            if (arr[i].gameState > best.gameState) {
                best.gameState = arr[i].gameState;
                bestMoves[0] = arr[i];
                len = 1;
            } else if (arr[i].gameState == best.gameState) {
                bestMoves[len] = arr[i];
                len++;
            }
        }
        Random rand = new Random();
        int n = rand.nextInt(len);
//        Log.v(TAG,"AFTER MOVE FOUND");
        return bestMoves[n];
    }

    public PossibleMove determineSuggestedX(int depth, gameBoard game) {
        turnsCompleted = 0;
        PossibleMove arr[] = new PossibleMove[MAX];
        int lenX = xPossibleMoves(game, arr);
        PossibleMove bestMoves[] = new PossibleMove[lenX];
        int len = 0;
        PossibleMove best = new PossibleMove();
        best.gameState = Double.MAX_VALUE;
//        if (lenX == 0){
//            return new PossibleMove(1,1,1,1,false);
//        }
        for (int i = 0; i < lenX; i++) {
            backGroundAI pass = new backGroundAI(game, arr[i], 7);
            backGroundOneMoveX test = new backGroundOneMoveX();
            test.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pass, null, null);
        }
        while (turnsCompleted < lenX) {
            Log.v("AI", "WAITING IN X");
        }
        for (int i = 0; i < lenX; i++) {
            if (arr[i].gameState < best.gameState) {
                best.gameState = arr[i].gameState;
                bestMoves[0] = arr[i];
                len = 1;
            } else if (arr[i].gameState == best.gameState) {
                bestMoves[len] = arr[i];
                len++;
            }
        }
        Random rand = new Random();
        int n = rand.nextInt(len);
        return bestMoves[n];
    }

//    private double alphaBetaMax(double alpha, double beta, int depthleft, gameBoard game) {
//        if (depthleft == 0) {
//            PossibleMove one = new PossibleMove();
//            one.OcalcGameState(game);
//            return one.gameState;
//        }
//        double score;
//        double best = (-1 * Double.MAX_VALUE);
//        gameBoard temp = new gameBoard();
//        game.clone(temp);
//        if (game.oForcedJump()) {
//            outerLoop:
//            {
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        for (int k = 0; k < 8; k++) {
//                            for (int l = 0; l < 8; l++) {
//                                if (game.validJumpO(i, j, k, l)) {
//                                    game.clone(temp);
//                                    updateBoardO(temp, i, j, k, l, true);
//                                    score = alphaBetaMin(alpha, beta, depthleft - 1, temp);
//                                    best = Math.max(score, best);
//                                    alpha = Math.max(alpha, best);
//                                    Log.v(TAG, "X");
//                                    Log.v(TAG, Double.toString(best));
//                                    if (best == Double.MAX_VALUE){
//                                        Log.v(TAG, "TESTINGAGAIN "+score);
//
//                                    }
//                                    if (beta <= alpha) {
//                                        Log.v(TAG, "TESTING");
//                                        break outerLoop;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            outerLoop:
//            {
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        for (int k = 0; k < 8; k++) {
//                            for (int l = 0; l < 8; l++) {
//                                if (game.validMoveO(i, j, k, l)) {
//                                    game.clone(temp);
//                                    updateBoardO(temp, i, j, k, l, false);
//                                    score = alphaBetaMin(alpha, beta, depthleft - 1, temp);
//                                    best = Math.max(score, best);
//                                    alpha = Math.max(alpha, best);
//                                    Log.v(TAG, "X");
//                                    Log.v(TAG, Double.toString(best));
//                                    if (best == Double.MAX_VALUE){
//                                        Log.v(TAG, "TESTINGAGAIN "+score);
//                                    }
//
//                                    if (beta <= alpha) {
//                                        Log.v(TAG, "TESTING");
//                                        break outerLoop;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return best;
//    }
//
//    private double alphaBetaMin(double alpha, double beta, int depthleft, gameBoard game) {
//        if (depthleft == 0) {
//            PossibleMove one = new PossibleMove();
//            one.OcalcGameState(game);
//            return one.gameState;
//        }
//        double score = Double.MAX_VALUE;
//        gameBoard temp = new gameBoard();
//        game.clone(temp);
//        double best = Double.MAX_VALUE;
//        if (game.oForcedJump()) {
//            outerLoop:
//            {
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        for (int k = 0; k < 8; k++) {
//                            for (int l = 0; l < 8; l++) {
//                                if (game.validJumpX(i, j, k, l)) {
//                                    game.clone(temp);
//                                    updateBoardX(temp, i, j, k, l, true);
//                                    score = alphaBetaMax(alpha, beta, depthleft - 1, temp);
//                                    best = Math.min(best, score);
//                                    Log.v(TAG, "O");
//                                    Log.v(TAG, Double.toString(best));
//                                    beta = Math.min(beta, best);
//                                    if (best == Double.MAX_VALUE){
//                                        Log.v(TAG, "TESTINGAGAIN "+score);
//                                    }
//                                    if (beta <= alpha) {
//                                        Log.v(TAG, "TESTING");
//                                        break outerLoop;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return best;
//        } else {
//            outerLoop:
//            {
//                for (int i = 0; i < 8; i++) {
//                    for (int j = 0; j < 8; j++) {
//                        for (int k = 0; k < 8; k++) {
//                            for (int l = 0; l < 8; l++) {
//                                if (game.validMoveX(i, j, k, l)) {
//                                    game.clone(temp);
//                                    updateBoardX(temp, i, j, k, l, false);
//                                    score = alphaBetaMax(alpha, beta, depthleft - 1, temp);
//                                    best = Math.min(best, score);
//                                    Log.v(TAG, "O");
//                                    Log.v(TAG, Double.toString(best));
//                                    beta = Math.min(beta, best);
//                                    if (best == Double.MAX_VALUE){
//                                        Log.v(TAG, "TESTINGAGAIN "+score);
//                                    }
//                                    if (beta <= alpha) {
//                                        Log.v(TAG, "TESTING");
//                                        break outerLoop;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        return best;
//    }


    double alphaBetaMax( double alpha, double beta, int depthleft, gameBoard game ) {
        gameBoard temp = new gameBoard();
        PossibleMove arr[] = new PossibleMove[MAX];
        PossibleMove one = new PossibleMove();
        if ( depthleft == 0 ) {
            one.OcalcGameState(game);
            return one.gameState;
        }
        int len = oPossibleMoves(game, arr);
        if (len == 0){

            return -1000;
        }
        for (int i =0; i < len; i ++) {
            game.clone(temp);
            temp.updateGameboard(arr[i].xVal1, arr[i].yVal1, arr[i].xVal2, arr[i].yVal2, arr[i].jump);
            temp.updateKings();
            int xVal1;
            int yVal1;
            int xVal2 = arr[i].xVal2;
            int yVal2 = arr[i].yVal2;
            if (arr[i].jump) {
                while (temp.doubleJumpO(xVal2, yVal2)) {
                    PossibleMove djo1 = doubleJump(temp, xVal2, yVal2);
                    xVal1 = djo1.xVal1;
                    yVal1 = djo1.yVal1;
                    xVal2 = djo1.xVal2;
                    yVal2 = djo1.yVal2;
                    temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                }
            }
            temp.updateKings();
            double score = alphaBetaMin( alpha, beta, depthleft - 1, temp);
            if( score >= beta )
                return beta;   // fail hard beta-cutoff
            if( score > alpha )
                alpha = score; // alpha acts like max in MiniMax
        }
        return alpha;
    }

    double alphaBetaMin( double alpha, double beta, int depthleft, gameBoard game ) {
        gameBoard temp = new gameBoard();
        PossibleMove arr[] = new PossibleMove[MAX];
        PossibleMove one = new PossibleMove();
        if ( depthleft == 0 ) {
            one.OcalcGameState(game);
            return (one.gameState);
        }
        int len = xPossibleMoves(game, arr);
        if (len == 0){
            return 1000;
        }
        for (int i =0; i <len; i++) {
            game.clone(temp);
            temp.updateGameboard(arr[i].xVal1, arr[i].yVal1, arr[i].xVal2, arr[i].yVal2, arr[i].jump);
            //temp.updateKings();
            int xVal1;
            int yVal1;
            int xVal2 = arr[i].xVal2;
            int yVal2 = arr[i].yVal2;
            if (arr[i].jump) {
                while (temp.doubleJumpX(xVal2, yVal2)) {
                    PossibleMove djo1 = doubleJumpX(temp, xVal2, yVal2);
                    xVal1 = djo1.xVal1;
                    yVal1 = djo1.yVal1;
                    xVal2 = djo1.xVal2;
                    yVal2 = djo1.yVal2;
                    temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                }
            }
            temp.updateKings();
            double score = alphaBetaMax( alpha, beta, depthleft - 1, temp );
            if( score <= alpha )
                return alpha; // fail hard alpha-cutoff
            if( score < beta )
                beta = score; // beta acts like min in MiniMax
        }
        return beta;
    }
    private class backGroundOneMove extends AsyncTask<backGroundAI, Void, Void> {
        @Override
        protected Void doInBackground(backGroundAI... params) {
            gameBoard game = params[0].game;
            int depth = params[0].depth;
            gameBoard temp = new gameBoard();
            PossibleMove one = params[0].one;
//            game.clone(temp);
//            updateBoardO(temp, one.xVal1, one.yVal1, one.xVal2, one.yVal2, one.jump);
            game.clone(temp);
            temp.updateGameboard(one.xVal1, one.yVal1, one.xVal2, one.yVal2, one.jump);
            int xVal1;
            int yVal1;
            int xVal2 = one.xVal2;
            int yVal2 = one.yVal2;
            if (one.jump) {
                while (temp.doubleJumpO(xVal2, yVal2)) {
                    PossibleMove djo1 = doubleJump(temp, xVal2, yVal2);
                    xVal1 = djo1.xVal1;
                    yVal1 = djo1.yVal1;
                    xVal2 = djo1.xVal2;
                    yVal2 = djo1.yVal2;
                    temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                }
            }
            temp.updateKings();
            one.gameState = alphaBetaMin(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depth, temp);
            return null;
        }
        @Override
        protected void onPostExecute(Void noth) {
            turnsCompleted++;
        }

    }

    private class backGroundOneMoveX extends AsyncTask<backGroundAI, Void, Void> {

        @Override
        protected Void doInBackground(backGroundAI... params) {
            gameBoard game = params[0].game;
            int depth = params[0].depth;
            gameBoard temp = new gameBoard();
            PossibleMove one = params[0].one;
//            game.clone(temp);
//            updateBoardO(temp, one.xVal1, one.yVal1, one.xVal2, one.yVal2, one.jump);
            game.clone(temp);
            temp.updateGameboard(one.xVal1, one.yVal1, one.xVal2, one.yVal2, one.jump);
            int xVal1;
            int yVal1;
            int xVal2 = one.xVal2;
            int yVal2 = one.yVal2;
            if (one.jump) {
                while (temp.doubleJumpX(xVal2, yVal2)) {
                    PossibleMove djo1 = doubleJumpX(temp, xVal2, yVal2);
                    xVal1 = djo1.xVal1;
                    yVal1 = djo1.yVal1;
                    xVal2 = djo1.xVal2;
                    yVal2 = djo1.yVal2;
                    temp.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                }
            }
            temp.updateKings();
            one.gameState = alphaBetaMax(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depth, temp);
            return null;
        }

        @Override
        protected void onPostExecute(Void noth) {
            turnsCompleted++;
        }

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