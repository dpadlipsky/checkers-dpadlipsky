package com.example.davidpadlipsky.checkers;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class OnePlayerCheckers extends AppCompatActivity {
    private static final String TAG = "OnePlayerCheckers";
    space start = new space(-1, -1);
    TextView testTxt;
    private int buttonCounter = 0;
    gameBoard game = new gameBoard();
    private int xVal1, xVal2;
    int screenHeight;
    private int yVal1, yVal2;
    private int xVal1S, xVal2S;
    private int yVal1S, yVal2S;
    private int turnNumber;
    private int delay = 300;
    private boolean doubleJump = false;
    TextView player1Score;
    TextView player2Score;
    int jumplessTurns = 0;
    int len = 0;
    int movesAhead;
    PossibleMove moveArr[] = new PossibleMove[5];
    private int gameOver = 0;
    space dj = new space(-1, -1);
    ImageView imgArr[][] = new ImageView[8][8];
    Button btnArr[][] = new Button[8][8];
    Button undoButt;
    Button suggestMoveButt;
    int viewsLoaded = 0;
    int suggestInProgress=0;
    TextView p1text;
    TextView p2text;
    ImageView top;
    ImageView bot;
    ImageView left;
    boolean abc = true;
    ImageView right;
    TableLayout buttons;
    TableLayout pics;
//    OturnTest oTurnBack = new OturnTest();
//    fixTextView fixBackground = new fixTextView();
//    sugestMove sMove = new sugestMove();
    Stack<undoMove> undoMoveStack = new Stack<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_one_player_checkers);
        testTxt = (TextView) findViewById(R.id.TESTTXT);
        //testTxt.setText("HELLO");
        p1text = (TextView) findViewById(R.id.p1text);
        p2text = (TextView) findViewById(R.id.p2text);
        undoButt = (Button) findViewById(R.id.undoButton);
        btnArr[1][0] = (Button) findViewById(R.id.b01);
        btnArr[3][0] = (Button) findViewById(R.id.b03);
        btnArr[5][0] = (Button) findViewById(R.id.b05);
        btnArr[7][0] = (Button) findViewById(R.id.b07);
        btnArr[0][1] = (Button) findViewById(R.id.b10);
        btnArr[2][1] = (Button) findViewById(R.id.b12);
        btnArr[4][1] = (Button) findViewById(R.id.b14);
        btnArr[6][1] = (Button) findViewById(R.id.b16);
        btnArr[1][2] = (Button) findViewById(R.id.b21);
        btnArr[3][2] = (Button) findViewById(R.id.b23);
        btnArr[5][2] = (Button) findViewById(R.id.b25);
        btnArr[7][2] = (Button) findViewById(R.id.b27);
        btnArr[0][3] = (Button) findViewById(R.id.b30);
        btnArr[2][3] = (Button) findViewById(R.id.b32);
        btnArr[4][3] = (Button) findViewById(R.id.b34);
        btnArr[6][3] = (Button) findViewById(R.id.b36);
        btnArr[1][4] = (Button) findViewById(R.id.b41);
        btnArr[3][4] = (Button) findViewById(R.id.b43);
        btnArr[5][4] = (Button) findViewById(R.id.b45);
        btnArr[7][4] = (Button) findViewById(R.id.b47);
        btnArr[0][5] = (Button) findViewById(R.id.b50);
        btnArr[2][5] = (Button) findViewById(R.id.b52);
        btnArr[4][5] = (Button) findViewById(R.id.b54);
        btnArr[6][5] = (Button) findViewById(R.id.b56);
        btnArr[1][6] = (Button) findViewById(R.id.b61);
        btnArr[3][6] = (Button) findViewById(R.id.b63);
        btnArr[5][6] = (Button) findViewById(R.id.b65);
        btnArr[7][6] = (Button) findViewById(R.id.b67);
        btnArr[0][7] = (Button) findViewById(R.id.b70);
        btnArr[2][7] = (Button) findViewById(R.id.b72);
        btnArr[4][7] = (Button) findViewById(R.id.b74);
        btnArr[6][7] = (Button) findViewById(R.id.b76);
        pics = (TableLayout)findViewById(R.id.imagelayout);
        buttons = (TableLayout) findViewById(R.id.buttonlayout);
        for (int i = 0; i < 8; i=i+2){
            btnArr[1][i].setOnClickListener(onClickListener);
            btnArr[3][i].setOnClickListener(onClickListener);
            btnArr[5][i].setOnClickListener(onClickListener);
            btnArr[7][i].setOnClickListener(onClickListener);
            btnArr[0][i+1].setOnClickListener(onClickListener);
            btnArr[2][i+1].setOnClickListener(onClickListener);
            btnArr[4][i+1].setOnClickListener(onClickListener);
            btnArr[6][i+1].setOnClickListener(onClickListener);
        }
        suggestMoveButt = (Button)findViewById(R.id.suggestMove);

        suggestMoveButt.setOnClickListener(onClickListener1);
        undoButt.setOnClickListener(onClickListener2);
        imgArr[1][0] = (ImageView) findViewById(R.id.im01);
        imgArr[3][0] = (ImageView) findViewById(R.id.im03);
        imgArr[5][0] = (ImageView) findViewById(R.id.im05);
        imgArr[7][0] = (ImageView) findViewById(R.id.im07);
        imgArr[0][1] = (ImageView) findViewById(R.id.im10);
        imgArr[2][1] = (ImageView) findViewById(R.id.im12);
        imgArr[4][1] = (ImageView) findViewById(R.id.im14);
        imgArr[6][1] = (ImageView) findViewById(R.id.im16);
        imgArr[1][2] = (ImageView) findViewById(R.id.im21);
        imgArr[3][2] = (ImageView) findViewById(R.id.im23);
        imgArr[5][2] = (ImageView) findViewById(R.id.im25);
        imgArr[7][2] = (ImageView) findViewById(R.id.im27);
        imgArr[0][3] = (ImageView) findViewById(R.id.im30);
        imgArr[2][3] = (ImageView) findViewById(R.id.im32);
        imgArr[4][3] = (ImageView) findViewById(R.id.im34);
        imgArr[6][3] = (ImageView) findViewById(R.id.im36);
        imgArr[1][4] = (ImageView) findViewById(R.id.im41);
        imgArr[3][4] = (ImageView) findViewById(R.id.im43);
        imgArr[5][4] = (ImageView) findViewById(R.id.im45);
        imgArr[7][4] = (ImageView) findViewById(R.id.im47);
        imgArr[0][5] = (ImageView) findViewById(R.id.im50);
        imgArr[2][5] = (ImageView) findViewById(R.id.im52);
        imgArr[4][5] = (ImageView) findViewById(R.id.im54);
        imgArr[6][5] = (ImageView) findViewById(R.id.im56);
        imgArr[1][6] = (ImageView) findViewById(R.id.im61);
        imgArr[3][6] = (ImageView) findViewById(R.id.im63);
        imgArr[5][6] = (ImageView) findViewById(R.id.im65);
        imgArr[7][6] = (ImageView) findViewById(R.id.im67);
        imgArr[0][7] = (ImageView) findViewById(R.id.im70);
        imgArr[2][7] = (ImageView) findViewById(R.id.im72);
        imgArr[4][7] = (ImageView) findViewById(R.id.im74);
        imgArr[6][7] = (ImageView) findViewById(R.id.im76);
        game.reset();
        top = (ImageView)findViewById(R.id.top);
        bot = (ImageView)findViewById(R.id.bottom);
        right = (ImageView)findViewById(R.id.right);
        left = (ImageView)findViewById(R.id.left);
        resetBoard();
        player1Score = (TextView) findViewById(R.id.player1score);
        player2Score = (TextView) findViewById(R.id.player2score);
        Intent intent = getIntent();
        movesAhead = intent.getIntExtra("movesAhead", 0);




    }

    @Override
    protected void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int screenHeight = width/8;
        for (int i = 0; i < 8; i=i+2){
            btnArr[1][i].getLayoutParams().height=screenHeight;
            btnArr[1][i].getLayoutParams().width=screenHeight;
            btnArr[3][i].getLayoutParams().height=screenHeight;
            btnArr[3][i].getLayoutParams().width=screenHeight;
            btnArr[5][i].getLayoutParams().height=screenHeight;
            btnArr[5][i].getLayoutParams().width=screenHeight;
            btnArr[7][i].getLayoutParams().height=screenHeight;
            btnArr[7][i].getLayoutParams().width=screenHeight;
            i++;
            btnArr[0][i].getLayoutParams().height=screenHeight;
            btnArr[0][i].getLayoutParams().width=screenHeight;
            btnArr[2][i].getLayoutParams().height=screenHeight;
            btnArr[2][i].getLayoutParams().width=screenHeight;
            btnArr[4][i].getLayoutParams().height=screenHeight;
            btnArr[4][i].getLayoutParams().width=screenHeight;
            btnArr[6][i].getLayoutParams().height=screenHeight;
            btnArr[6][i].getLayoutParams().width=screenHeight;
            i--;
        }
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(screenHeight,screenHeight);
        for (int i = 0; i < 8; i=i+2){
            imgArr[1][i].getLayoutParams().height=screenHeight;
            imgArr[1][i].getLayoutParams().width=screenHeight;
            imgArr[3][i].getLayoutParams().height=screenHeight;
            imgArr[3][i].getLayoutParams().width=screenHeight;
            imgArr[5][i].getLayoutParams().height=screenHeight;
            imgArr[5][i].getLayoutParams().width=screenHeight;
            imgArr[7][i].getLayoutParams().height=screenHeight;
            imgArr[7][i].getLayoutParams().width=screenHeight;
            i++;
            imgArr[0][i].getLayoutParams().height=screenHeight;
            imgArr[0][i].getLayoutParams().width=screenHeight;
            imgArr[2][i].getLayoutParams().height=screenHeight;
            imgArr[2][i].getLayoutParams().width=screenHeight;
            imgArr[4][i].getLayoutParams().height=screenHeight;
            imgArr[4][i].getLayoutParams().width=screenHeight;
            imgArr[6][i].getLayoutParams().height=screenHeight;
            imgArr[6][i].getLayoutParams().width=screenHeight;
            i--;

        }
        undoButt.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        undoButt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        imgArr[7][6].getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        imgArr[7][6].getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        testTxt.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        testTxt.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        player1Score.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        player1Score.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        player2Score.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        player2Score.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        p2text.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        p2text.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        p1text.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        p1text.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        top.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        top.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        bot.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        bot.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        right.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        right.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        left.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener()
                {
                    @Override
                    public void onGlobalLayout()
                    {
                        // Now you may get the left/top/etc.
                        viewsLoaded++;
                        // Optionally remove the listener so future layouts don't change the value
                        left.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
        if (abc) {
            fixTextView test = new fixTextView();
            test.execute(null, null, null);
        }
    }
    private View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            undoTheMove();
        }
    };

    private View.OnClickListener onClickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (gameOver==0&&suggestInProgress==0) {
                sugestMove test = new sugestMove();
                test.execute(null, null, null);
                suggestInProgress++;
            }
        }
    };
    //
    @Override
    protected void onResume(){
        super.onResume();
        if (!abc) {
            for (int i = 0; i < 8; i = i + 2) {
                btnArr[1][i].getLayoutParams().height = screenHeight;
                btnArr[1][i].getLayoutParams().width = screenHeight;
                btnArr[3][i].getLayoutParams().height = screenHeight;
                btnArr[3][i].getLayoutParams().width = screenHeight;
                btnArr[5][i].getLayoutParams().height = screenHeight;
                btnArr[5][i].getLayoutParams().width = screenHeight;
                btnArr[7][i].getLayoutParams().height = screenHeight;
                btnArr[7][i].getLayoutParams().width = screenHeight;
                i++;
                btnArr[0][i].getLayoutParams().height = screenHeight;
                btnArr[0][i].getLayoutParams().width = screenHeight;
                btnArr[2][i].getLayoutParams().height = screenHeight;
                btnArr[2][i].getLayoutParams().width = screenHeight;
                btnArr[4][i].getLayoutParams().height = screenHeight;
                btnArr[4][i].getLayoutParams().width = screenHeight;
                btnArr[6][i].getLayoutParams().height = screenHeight;
                btnArr[6][i].getLayoutParams().width = screenHeight;
                i--;
            }
            for (int i = 0; i < 8; i = i + 2) {
                imgArr[1][i].getLayoutParams().height = screenHeight;
                imgArr[1][i].getLayoutParams().width = screenHeight;
                imgArr[3][i].getLayoutParams().height = screenHeight;
                imgArr[3][i].getLayoutParams().width = screenHeight;
                imgArr[5][i].getLayoutParams().height = screenHeight;
                imgArr[5][i].getLayoutParams().width = screenHeight;
                imgArr[7][i].getLayoutParams().height = screenHeight;
                imgArr[7][i].getLayoutParams().width = screenHeight;
                i++;
                imgArr[0][i].getLayoutParams().height = screenHeight;
                imgArr[0][i].getLayoutParams().width = screenHeight;
                imgArr[2][i].getLayoutParams().height = screenHeight;
                imgArr[2][i].getLayoutParams().width = screenHeight;
                imgArr[4][i].getLayoutParams().height = screenHeight;
                imgArr[4][i].getLayoutParams().width = screenHeight;
                imgArr[6][i].getLayoutParams().height = screenHeight;
                imgArr[6][i].getLayoutParams().width = screenHeight;
                i--;

            }
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
//        sMove.cancel(true);
//        oTurnBack.cancel(true);
//        fixBackground.cancel(true);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.b01: {
                    start = new space(0, 1);
                    break;
                }
                case R.id.b03: {
                    start = new space(0, 3);
                    break;
                }
                case R.id.b05: {
                    start = new space(0, 5);
                    break;
                }
                case R.id.b07: {
                    start = new space(0, 7);
                    break;
                }
                case R.id.b10: {
                    start = new space(1, 0);
                    break;
                }
                case R.id.b12: {
                    start = new space(1, 2);
                    break;
                }
                case R.id.b14: {
                    start = new space(1, 4);
                    break;
                }
                case R.id.b16: {
                    start = new space(1, 6);
                    break;
                }
                case R.id.b21: {
                    start = new space(2, 1);
                    break;
                }
                case R.id.b23: {
                    start = new space(2, 3);
                    break;
                }
                case R.id.b25: {
                    start = new space(2, 5);
                    break;
                }
                case R.id.b27: {
                    start = new space(2, 7);
                    break;
                }
                case R.id.b30: {
                    start = new space(3, 0);
                    break;
                }
                case R.id.b32: {
                    start = new space(3, 2);
                    break;
                }
                case R.id.b34: {
                    start = new space(3, 4);
                    break;
                }
                case R.id.b36: {
                    start = new space(3, 6);
                    break;
                }
                case R.id.b41: {
                    start = new space(4, 1);
                    break;
                }
                case R.id.b43: {
                    start = new space(4, 3);
                    break;
                }
                case R.id.b45: {
                    start = new space(4, 5);
                    break;
                }
                case R.id.b47: {
                    start = new space(4, 7);
                    break;
                }
                case R.id.b50: {
                    start = new space(5, 0);
                    break;
                }
                case R.id.b52: {
                    start = new space(5, 2);
                    break;
                }
                case R.id.b54: {
                    start = new space(5, 4);
                    break;
                }
                case R.id.b56: {
                    start = new space(5, 6);
                    break;
                }
                case R.id.b61: {
                    start = new space(6, 1);
                    break;
                }
                case R.id.b63: {
                    start = new space(6, 3);
                    break;
                }
                case R.id.b65: {
                    start = new space(6, 5);
                    break;
                }
                case R.id.b67: {
                    start = new space(6, 7);
                    break;
                }
                case R.id.b70: {
                    start = new space(7, 0);
                    break;
                }
                case R.id.b72: {
                    start = new space(7, 2);
                    break;
                }
                case R.id.b74: {
                    start = new space(7, 4);
                    break;
                }
                case R.id.b76: {
                    start = new space(7, 6);
                    break;
                }
            }
            if (start.getxVal() == xVal1 && start.getyVal() == yVal1) {
                for (int i = 0; i < len; i++) {
                    imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(android.R.color.transparent);
                    btnArr[moveArr[i].xVal2][moveArr[i].yVal2].setBackgroundResource(R.color.tile);
                    btnArr[moveArr[i].xVal1][moveArr[i].yVal1].setBackgroundResource(R.color.tile);

                }
                xVal1 = -1;
                yVal1 = -1;
            } else if (turnNumber % 2 == 0) {
                if (game.pieceX(start.getxVal(), start.getyVal()) || game.pieceXKing(start.getxVal(), start.getyVal())) {

                    if (xVal1 != -1) {
                        for (int i = 0; i < len; i++) {
//                            imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(android.R.color.transparent);
                            btnArr[moveArr[i].xVal2][moveArr[i].yVal2].setBackgroundResource(R.color.tile);
                            btnArr[moveArr[i].xVal1][moveArr[i].yVal1].setBackgroundResource(R.color.tile);


                        }
                    }
                    len = game.xPossibleMove(moveArr, start.getxVal(), start.getyVal());
                    for (int i = 0; i < len; i++) {
//                        btnArr[moveArr[i].xVal2][moveArr[i].yVal2].setBackgroundColor();
                        btnArr[moveArr[i].xVal2][moveArr[i].yVal2].setBackgroundResource(R.color.updateTileBlack);
//                        btnArr[moveArr[i].xVal1][moveArr[i].yVal1].setBackgroundResource(R.color.highlight);


                    }
                    xVal1 = start.getxVal();
                    yVal1 = start.getyVal();
                } else {
                    for (int i = 0; i < len; i++) {
                        if (start.getxVal() == moveArr[i].xVal2 && start.getyVal() == moveArr[i].yVal2) {
                            xVal2 = start.getxVal();
                            yVal2 = start.getyVal();
                            if (xVal1 != -1)
                                gameDeterminate();
                        }
                    }
                }

            }
        }
    };


    public void undoTheMove() {
        if (turnNumber%2==0) {
            if (game.undoMoves(undoMoveStack)) {
                turnNumber--;
            }
            if (game.undoMoves(undoMoveStack)) {
                turnNumber--;
            }
        }
        if (turnNumber%2==1){
            if (game.undoMoves(undoMoveStack)) {
                turnNumber--;
            }
        }
        updateBoard();

    }

    private void resetCounter() {
        buttonCounter = 0;
    }

    private void gameDeterminate() {
        if (gameOver != 0) {
            if (gameOver == 1) {
                testTxt.setText("Player 1 Wins!");
            } else if (gameOver == 2)
                testTxt.setText("Player 2 Wins!");
            else if (gameOver == 3) {
                testTxt.setText("It's a Tie!");
            }
        } else {
            if (turnNumber % 2 == 0) {
                xTurn();
            }
            OturnTest test = new OturnTest();
            test.execute(null, null, null);
        }

    }

    private void addToUndoArr(boolean jp) {
        boolean king = false;
        if (yVal2 == 0) {
            if (game.pieceX(xVal1, yVal1)) {
                Log.v(TAG, "KING=TRUE");
                king = true;
            }
        } else if (yVal2 == 7) {
            if (game.pieceO(xVal1, yVal1)) {
                Log.v(TAG, "KING=TRUE");
                king = true;
            }
        }
        if (jp == true) {
            undoMove move1 = new undoMove(1,1,1,1,1,false,king);
            if (game.pieceO((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)) {
                move1 = new undoMove(turnNumber, xVal1, yVal1, xVal2, yVal2, true, 'o', king);
            } else if (game.pieceX((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)) {
                move1 = new undoMove(turnNumber, xVal1, yVal1, xVal2, yVal2, true, 'x', king);
            } else if (game.pieceXKing((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)) {
                move1 = new undoMove(turnNumber, xVal1, yVal1, xVal2, yVal2, true, 'X', king);
            } else if (game.pieceOKing((xVal1 + xVal2) / 2, (yVal1 + yVal2) / 2)) {
                move1 = new undoMove(turnNumber, xVal1, yVal1, xVal2, yVal2, true, 'O', king);
            }
            undoMoveStack.push(move1);
        } else {
            undoMoveStack.push(new undoMove(turnNumber, xVal1, yVal1, xVal2, yVal2, false, king));
        }
    }


    private void xTurn() {
        if (doubleJump) {
            if (xVal1 == dj.getxVal() && yVal1 == dj.getyVal()) {
                if (game.validJumpX(xVal1, yVal1, xVal2, yVal2)) {
                    testTxt.setText("Computer's Turn!");
                    addToUndoArr(true);
                    game.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                    if (game.doubleJumpX(xVal2, yVal2)) {
                        doubleJump = true;
                        testTxt.setText("You can even jump again!");
                        dj = new space(yVal2, xVal2);
                    } else {
                        turnNumber++;
                        jumplessTurns++;
                        doubleJump = false;
                    }
                    game.updateKings();
                    updateBoard();
                    start = new space();
                    xVal1 = -1;
                }
            }
        } else {
            if (game.validMoveX(xVal1, yVal1, xVal2, yVal2) && !game.xForcedJump()) {
                testTxt.setText("Computer's Turn!");
                addToUndoArr(false);
                game.updateGameboard(xVal1, yVal1, xVal2, yVal2, false);
                turnNumber++;
                jumplessTurns++;
                if (game.pieceX(xVal1,yVal1)){
                    jumplessTurns=0;
                }
                game.updateKings();
                updateBoard();
                start = new space();
                xVal1 = -1;
            } else if (game.validJumpX(xVal1, yVal1, xVal2, yVal2)) {
                testTxt.setText("Computer's Turn!");
                addToUndoArr(true);
                game.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                jumplessTurns = 0;
                doubleJump = false;
                if (game.doubleJumpX(xVal2, yVal2)) {

                    doubleJump = true;
                    testTxt.setText("You may jump again!");
                    dj = new space(yVal2, xVal2);
                } else {
                    turnNumber++;
                    jumplessTurns++;
                    doubleJump = false;
                }
                game.updateKings();
                updateBoard();
                start = new space();
                xVal1 = -1;
            } else if (game.xForcedJump()) {
                testTxt.setText("You must jump!");
            } else {
                testTxt.setText("X INVALID MOVE!");
            }
        }
//        testTxt.setText("len :"+undoMoveArr.len);


    }

    private void resetBoard() {

        for (int i = 0; i < 8; i=i+2){
            btnArr[1][i].setBackgroundResource(R.color.tile);
            btnArr[3][i].setBackgroundResource(R.color.tile);
            btnArr[5][i].setBackgroundResource(R.color.tile);
            btnArr[7][i].setBackgroundResource(R.color.tile);
            btnArr[0][i+1].setBackgroundResource(R.color.tile);
            btnArr[2][i+1].setBackgroundResource(R.color.tile);
            btnArr[4][i+1].setBackgroundResource(R.color.tile);
            btnArr[6][i+1].setBackgroundResource(R.color.tile);
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.arr[i][j] == 'x') {
                    if (xVal2 == j && yVal2 == i) {
                        btnArr[j][i].setBackgroundResource(R.color.updateTileBlack);
                    }
                    imgArr[j][i].setImageResource(R.drawable.bluecircle);
                } else if (game.arr[i][j] == 'o') {
                    if (xVal2 == j && yVal2 == i) {
                        btnArr[j][i].setBackgroundResource(R.color.updateTileRed);
                    }
                    imgArr[j][i].setImageResource(R.drawable.darkbluecircle);
                } else if (game.arr[i][j] == ' ') {

                    imgArr[j][i].setImageResource(android.R.color.transparent);

                } else if (game.arr[i][j] == 'X') {
                    if (xVal2 == j && yVal2 == i) {
                        btnArr[j][i].setBackgroundResource(R.color.updateTileBlack);
                    }
                    imgArr[j][i].setImageResource(R.drawable.bluex);

                } else if (game.arr[i][j] == 'O') {
                    if (xVal2 == j && yVal2 == i) {
                        btnArr[j][i].setBackgroundResource(R.color.updateTileRed);


                    }
                    imgArr[j][i].setImageResource(R.drawable.darkbluex);

                }
            }

        }

    }

    private void updateBoard() {
        resetBoard();
        player1Score.setText(Integer.toString(game.xCounter()));
        player2Score.setText(Integer.toString(game.oCounter()));
        game.setGameOver(turnNumber, jumplessTurns);
        gameOver = game.gameOverFlag;
//        if(movesAhead == 5){
//            if (game.xCounter()+game.oCounter()<=12){
//                movesAhead = 10;
//            }
//        }
        if (gameOver != 0) {
            if (gameOver == 1) {
                testTxt.setText("Player 1 Wins!");
            } else if (gameOver == 2)
                testTxt.setText("Player 2 Wins!");
            else if (gameOver == 3) {
                testTxt.setText("It's a Tie!");
            }
        }
    }

    private void oTurn() throws InterruptedException {
        AI ai1 = new AI();

        if (doubleJump) {
            PossibleMove move1 = ai1.doubleJump(game, xVal2, yVal2);
            xVal1 = move1.xVal1;
            yVal1 = move1.yVal1;
            xVal2 = move1.xVal2;
            yVal2 = move1.yVal2;
            boolean jump = move1.jump;
            addToUndoArr(true);
            game.updateGameboard(xVal1, yVal1, xVal2, yVal2, jump);
//            updateBoard();
//            testTxt.setText("Player 1's Turn!");
            if (game.doubleJumpO(xVal2, yVal2)) {

                doubleJump = true;
//                testTxt.setText("You may jump again!");
                dj = new space(yVal2, xVal2);
            } else {
                turnNumber++;
                jumplessTurns++;
                doubleJump = false;
                game.updateKings();
            }
            start = new space();
            xVal1 = -1;
        } else {
//            testTxt.setText("Let me calculate my move!");
            PossibleMove move1 = ai1.determineFastMove(movesAhead, game);
            xVal1 = move1.xVal1;
            yVal1 = move1.yVal1;
            xVal2 = move1.xVal2;
            yVal2 = move1.yVal2;
            boolean jump = move1.jump;
            if (jump) {
                addToUndoArr(true);
            } else {
                addToUndoArr(false);
            }
            if (game.pieceO(xVal1,yVal1)){
                jumplessTurns=0;
            }
            game.updateGameboard(xVal1, yVal1, xVal2, yVal2, jump);
//            testTxt.setText("Player 1's Turn!");
//            updateBoard();
            if (jump) {
                jumplessTurns = 0;
                if (game.doubleJumpO(xVal2, yVal2)) {

                    doubleJump = true;
//                    testTxt.setText("You may jump again!");
                    dj = new space(yVal2, xVal2);
                } else {
                    turnNumber++;
                    game.updateKings();
                    jumplessTurns++;
                    doubleJump = false;
                }
            } else {
                turnNumber++;
                game.updateKings();
                jumplessTurns++;
                doubleJump = false;
            }
            start = new space();
            xVal1 = -1;
        }
        //updateBoard();
//        testTxt.setText("len :"+undoMoveArr.len);
    }

    private void suggestMoveUpdate() {
        resetBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                btnArr[xVal2S][yVal2S].setBackgroundResource(R.color.lightGreen);
                if (game.pieceX(xVal1S, yVal1S))

                {
                    btnArr[xVal1S][yVal1S].setBackgroundResource(R.color.lightGreen);
                } else if (game.pieceO(xVal1S, yVal1S)) {
                    btnArr[xVal1S][yVal1S].setBackgroundResource(R.color.lightGreen);
                } else if (game.pieceXKing(xVal1S, yVal1S)) {
                    btnArr[xVal1S][yVal1S].setBackgroundResource(R.color.lightGreen);
                } else if (game.pieceOKing(xVal1S, yVal1S)) {
                    btnArr[xVal1S][yVal1S].setBackgroundResource(R.color.lightGreen);
                }
            }
        }
    }
    private class fixTextView extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            testTxt.setText("Player 1's Turn!");
            abc = false;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            while (viewsLoaded!=11){
                Log.v(TAG, "ViewsLoading");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void noth) {

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            float width = displayMetrics.widthPixels;
            float height = displayMetrics.heightPixels;

            float textSize = (float)(width*.019);
            p1text.setTextSize(textSize);
            p2text.setTextSize(textSize);
            player1Score.setTextSize(textSize);
            player2Score.setTextSize(textSize);
            undoButt.setTextSize((float)(textSize*.9));
            suggestMoveButt.setTextSize((float)(textSize*.9));
            testTxt.setTextSize(textSize);

            int textHeight = (int)(height*.03);
            p1text.setY((float)textHeight);
            p2text.setY((float)textHeight);

            int textBuffer = (int)(width*.03);
            p1text.setX(p1text.getX()+textBuffer);
            p2text.setX(p2text.getX()-textBuffer);
            float bannerWidth = (float)(height*.01);

            float scoreOffset = (float)(.01* height)+p1text.getHeight();
            player1Score.setY(p1text.getY()+scoreOffset);
            player2Score.setY(p2text.getY()+scoreOffset);
            player1Score.setX(p1text.getX());
            player2Score.setX(player2Score.getX()-textBuffer);

            float boardOffset = (float)((player1Score.getY()+player1Score.getHeight())+(height * .08));
            pics.setY(boardOffset);
            buttons.setY(boardOffset);



            ViewGroup.LayoutParams params = buttons.getLayoutParams();
            params.width = (int)(width-(4*bannerWidth));
            params.height= (int)(width-(3*bannerWidth));

            buttons.setLayoutParams(params);
            pics.setLayoutParams(params);
//            ViewGroup.LayoutParams banners = buttons.getLayoutParams();
//            banners.width = (int)width;
//            banners.height = (int)bannerWidth;
//            top.setLayoutParams(banners);
//            bot.setLayoutParams(banners);
//            banners.width = (int)bannerWidth;
//            banners.height = (int)width;
//            left.setLayoutParams(banners);
//            right.setLayoutParams(banners);
            top.getLayoutParams().height=(int)bannerWidth;
            top.getLayoutParams().width = (int)width;
            bot.getLayoutParams().height=(int)bannerWidth;
            bot.getLayoutParams().width = (int)width;
            right.getLayoutParams().width =(int)bannerWidth;
            right.getLayoutParams().height = (int)(width-bannerWidth);
            left.getLayoutParams().width =(int)bannerWidth;
            left.getLayoutParams().height = (int)(width-bannerWidth);
            top.setY(boardOffset-bannerWidth);
            bot.setY(boardOffset+(width-3*bannerWidth));
            right.setY(boardOffset-bannerWidth);
            left.setY(boardOffset-bannerWidth);
            top.setImageResource(R.color.border);
            right.setImageResource(R.color.border);
            left.setImageResource(R.color.border);
            bot.setImageResource(R.color.border);
            screenHeight = (int)(width-3*bannerWidth)/8;
            for (int i = 0; i < 8; i=i+2){
                btnArr[1][i].getLayoutParams().height=screenHeight;
                btnArr[1][i].getLayoutParams().width=screenHeight;
                btnArr[3][i].getLayoutParams().height=screenHeight;
                btnArr[3][i].getLayoutParams().width=screenHeight;
                btnArr[5][i].getLayoutParams().height=screenHeight;
                btnArr[5][i].getLayoutParams().width=screenHeight;
                btnArr[7][i].getLayoutParams().height=screenHeight;
                btnArr[7][i].getLayoutParams().width=screenHeight;
                i++;
                btnArr[0][i].getLayoutParams().height=screenHeight;
                btnArr[0][i].getLayoutParams().width=screenHeight;
                btnArr[2][i].getLayoutParams().height=screenHeight;
                btnArr[2][i].getLayoutParams().width=screenHeight;
                btnArr[4][i].getLayoutParams().height=screenHeight;
                btnArr[4][i].getLayoutParams().width=screenHeight;
                btnArr[6][i].getLayoutParams().height=screenHeight;
                btnArr[6][i].getLayoutParams().width=screenHeight;
                i--;
            }
            for (int i = 0; i < 8; i=i+2){
                imgArr[1][i].getLayoutParams().height=screenHeight;
                imgArr[1][i].getLayoutParams().width=screenHeight;
                imgArr[3][i].getLayoutParams().height=screenHeight;
                imgArr[3][i].getLayoutParams().width=screenHeight;
                imgArr[5][i].getLayoutParams().height=screenHeight;
                imgArr[5][i].getLayoutParams().width=screenHeight;
                imgArr[7][i].getLayoutParams().height=screenHeight;
                imgArr[7][i].getLayoutParams().width=screenHeight;
                i++;
                imgArr[0][i].getLayoutParams().height=screenHeight;
                imgArr[0][i].getLayoutParams().width=screenHeight;
                imgArr[2][i].getLayoutParams().height=screenHeight;
                imgArr[2][i].getLayoutParams().width=screenHeight;
                imgArr[4][i].getLayoutParams().height=screenHeight;
                imgArr[4][i].getLayoutParams().width=screenHeight;
                imgArr[6][i].getLayoutParams().height=screenHeight;
                imgArr[6][i].getLayoutParams().width=screenHeight;
                i--;

            }
            int arrI[] = new int[2];
            int arrB[] = new int[2];
            int hI = bot.getHeight();
            bot.getLocationOnScreen(arrI);
            undoButt.getLocationOnScreen(arrB);
            int tI = testTxt.getHeight();
            int midway = ((arrI[1]+arrB[1]+ hI - (tI/2))/2);
//            testTxt.setY(midway);
            testTxt.setText("Player 1's Turn!");

            testTxt.setY(midway);
//            int arrI[] = new int[2];
//            int arrB[] = new int[2];
//            int hI = imgArr[7][6].getHeight();
//            int sbI = undoButt.getHeight();
//            imgArr[7][6].getLocationOnScreen(arrI);
//            undoButt.getLocationOnScreen(arrB);
//            int tI = testTxt.getHeight();
//            int midway = ((arrI[1]+arrB[1]+hI+(tI/2))/2);
////            testTxt.setY(midway);
            testTxt.setText("Player 1's Turn!");
            abc = false;




        }
    }

    private class sugestMove extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute(){
            testTxt.setText("One Moment...");
        }
        @Override
        protected Void doInBackground(Void... voids) {
            AI ai1 = new AI();
            PossibleMove move1 = ai1.determineSuggestedX(movesAhead, game);
            xVal1S = move1.xVal1;
            yVal1S = move1.yVal1;
            xVal2S = move1.xVal2;
            yVal2S = move1.yVal2;
            return null;
        }
        @Override
        protected void onCancelled(){
            suggestInProgress--;
        }
        @Override
        protected void onPostExecute(Void noth) {
            testTxt.setText("Best move found!");
            suggestMoveUpdate();
            suggestInProgress--;
        }
    }

    private class OturnTest extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            testTxt.setText("Calculating...");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (turnNumber % 2 == 1 && gameOver == 0) {
                try {
                    oTurn();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resetCounter();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void noth) {
            testTxt.setText("Player 1's Turn!");
            updateBoard();
        }
    }
}
