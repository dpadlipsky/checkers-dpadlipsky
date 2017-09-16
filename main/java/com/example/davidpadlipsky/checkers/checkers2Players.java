package com.example.davidpadlipsky.checkers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class checkers2Players extends AppCompatActivity {

    space start = new space (-1,-1);
    TextView testTxt;
    private int buttonCounter = 0;
    gameBoard game = new gameBoard();
    private int xVal1, xVal2;
    private int yVal1,yVal2;
    private int turnNumber;
    private int delay = 300;
    private boolean doubleJump = false;
    int jumplessTurns=0;
    int gameOver=0;
    int len=0;
    PossibleMove moveArr[]=new PossibleMove[5];
    TextView player1Score;
    TextView player2Score;
    space dj = new space(-1,-1);
    ImageView imgArr[][] = new ImageView[8][8];
    Button butt01;// = (Button)findViewById(R.id.b01);
    Button butt03;// = (Button)findViewById(R.id.b03);
    Button butt05;// = (Button)findViewById(R.id.b05);
    Button butt07;// = (Button)findViewById(R.id.b07);
    Button butt10;// = (Button)findViewById(R.id.b10);
    Button butt12;// = (Button)findViewById(R.id.b12);
    Button butt14;// = (Button)findViewById(R.id.b14);
    Button butt16;// = (Button)findViewById(R.id.b16);
    Button butt21;// = (Button)findViewById(R.id.b21);
    Button butt23;// = (Button)findViewById(R.id.b23);
    Button butt25;// = (Button)findViewById(R.id.b25);
    Button butt27;// = (Button)findViewById(R.id.b27);
    Button butt30;// = (Button)findViewById(R.id.b30);
    Button butt32;// = (Button)findViewById(R.id.b32);
    Button butt34;// = (Button)findViewById(R.id.b34);
    Button butt36;// = (Button)findViewById(R.id.b36);
    Button butt41;// = (Button)findViewById(R.id.b41);
    Button butt43 ;//= (Button)findViewById(R.id.b43);
    Button butt45 ;//= (Button)findViewById(R.id.b45);
    Button butt47;// = (Button)findViewById(R.id.b47);
    Button butt50;// = (Button)findViewById(R.id.b50);
    Button butt52;// = (Button)findViewById(R.id.b52);
    Button butt54;// = (Button)findViewById(R.id.b54);
    Button butt56;// = (Button)findViewById(R.id.b56);
    Button butt61;// = (Button)findViewById(R.id.b61);
    Button butt63 ;//= (Button)findViewById(R.id.b63);
    Button butt65 ;//= (Button)findViewById(R.id.b65);
    Button butt67;// = (Button)findViewById(R.id.b67);
    Button butt70 ;//= (Button)findViewById(R.id.b70);
    Button butt72 ;//= (Button)findViewById(R.id.b72);
    Button butt74 ;//= (Button)findViewById(R.id.b74);
    Button butt76;// = (Button)findViewById(R.id.b76);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.checkers2player);
        testTxt = (TextView)findViewById(R.id.TESTTXT);
        //testTxt.setText("HELLO");
        butt01 = (Button)findViewById(R.id.b01);
        butt03 = (Button)findViewById(R.id.b03);
        butt05 = (Button)findViewById(R.id.b05);
        butt07 = (Button)findViewById(R.id.b07);
        butt10 = (Button)findViewById(R.id.b10);
        butt12 = (Button)findViewById(R.id.b12);
        butt14 = (Button)findViewById(R.id.b14);
        butt16 = (Button)findViewById(R.id.b16);
        butt21 = (Button)findViewById(R.id.b21);
        butt23 = (Button)findViewById(R.id.b23);
        butt25 = (Button)findViewById(R.id.b25);
        butt27 = (Button)findViewById(R.id.b27);
        butt30 = (Button)findViewById(R.id.b30);
        butt32 = (Button)findViewById(R.id.b32);
        butt34 = (Button)findViewById(R.id.b34);
        butt36 = (Button)findViewById(R.id.b36);
        butt41 = (Button)findViewById(R.id.b41);
        butt43 = (Button)findViewById(R.id.b43);
        butt45 = (Button)findViewById(R.id.b45);
        butt47 = (Button)findViewById(R.id.b47);
        butt50 = (Button)findViewById(R.id.b50);
        butt52 = (Button)findViewById(R.id.b52);
        butt54 = (Button)findViewById(R.id.b54);
        butt56 = (Button)findViewById(R.id.b56);
        butt61 = (Button)findViewById(R.id.b61);
        butt63 = (Button)findViewById(R.id.b63);
        butt65 = (Button)findViewById(R.id.b65);
        butt67 = (Button)findViewById(R.id.b67);
        butt70 = (Button)findViewById(R.id.b70);
        butt72 = (Button)findViewById(R.id.b72);
        butt74 = (Button)findViewById(R.id.b74);
        butt76 = (Button)findViewById(R.id.b76);

        butt01.setOnClickListener (onClickListener);
        butt03.setOnClickListener (onClickListener);
        butt05.setOnClickListener (onClickListener);
        butt07.setOnClickListener (onClickListener);
        butt10.setOnClickListener (onClickListener);
        butt12.setOnClickListener (onClickListener);
        butt14.setOnClickListener (onClickListener);
        butt16.setOnClickListener (onClickListener);
        butt21.setOnClickListener (onClickListener);
        butt23.setOnClickListener (onClickListener);
        butt25.setOnClickListener (onClickListener);
        butt27.setOnClickListener (onClickListener);
        butt30.setOnClickListener (onClickListener);
        butt32.setOnClickListener (onClickListener);
        butt34.setOnClickListener (onClickListener);
        butt36.setOnClickListener (onClickListener);
        butt41.setOnClickListener (onClickListener);
        butt43.setOnClickListener (onClickListener);
        butt45.setOnClickListener (onClickListener);
        butt47.setOnClickListener (onClickListener);
        butt50.setOnClickListener (onClickListener);
        butt52.setOnClickListener (onClickListener);
        butt54.setOnClickListener (onClickListener);
        butt56.setOnClickListener (onClickListener);
        butt61.setOnClickListener (onClickListener);
        butt63.setOnClickListener (onClickListener);
        butt65.setOnClickListener (onClickListener);
        butt67.setOnClickListener (onClickListener);
        butt70.setOnClickListener (onClickListener);
        butt72.setOnClickListener (onClickListener);
        butt74.setOnClickListener (onClickListener);
        butt76.setOnClickListener (onClickListener);
        imgArr[1][0]=(ImageView) findViewById(R.id.im01);
        imgArr[3][0]=(ImageView) findViewById(R.id.im03);
        imgArr[5][0]=(ImageView) findViewById(R.id.im05);
        imgArr[7][0]=(ImageView) findViewById(R.id.im07);
        imgArr[0][1]=(ImageView) findViewById(R.id.im10);
        imgArr[2][1]=(ImageView) findViewById(R.id.im12);
        imgArr[4][1]=(ImageView) findViewById(R.id.im14);
        imgArr[6][1]=(ImageView) findViewById(R.id.im16);
        imgArr[1][2]=(ImageView) findViewById(R.id.im21);
        imgArr[3][2]=(ImageView) findViewById(R.id.im23);
        imgArr[5][2]=(ImageView) findViewById(R.id.im25);
        imgArr[7][2]=(ImageView) findViewById(R.id.im27);
        imgArr[0][3]=(ImageView) findViewById(R.id.im30);
        imgArr[2][3]=(ImageView) findViewById(R.id.im32);
        imgArr[4][3]=(ImageView) findViewById(R.id.im34);
        imgArr[6][3]=(ImageView) findViewById(R.id.im36);
        imgArr[1][4]=(ImageView) findViewById(R.id.im41);
        imgArr[3][4]=(ImageView) findViewById(R.id.im43);
        imgArr[5][4]=(ImageView) findViewById(R.id.im45);
        imgArr[7][4]=(ImageView) findViewById(R.id.im47);
        imgArr[0][5]=(ImageView) findViewById(R.id.im50);
        imgArr[2][5]=(ImageView) findViewById(R.id.im52);
        imgArr[4][5]=(ImageView) findViewById(R.id.im54);
        imgArr[6][5]=(ImageView) findViewById(R.id.im56);
        imgArr[1][6]=(ImageView) findViewById(R.id.im61);
        imgArr[3][6]=(ImageView) findViewById(R.id.im63);
        imgArr[5][6]=(ImageView) findViewById(R.id.im65);
        imgArr[7][6]=(ImageView) findViewById(R.id.im67);
        imgArr[0][7]=(ImageView) findViewById(R.id.im70);
        imgArr[2][7]=(ImageView) findViewById(R.id.im72);
        imgArr[4][7]=(ImageView) findViewById(R.id.im74);
        imgArr[6][7]=(ImageView) findViewById(R.id.im76);
        game.reset();
        resetBoard();
//        butt01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(0,1);
//                buttonCounter++;
//            }
//        });
//        butt03.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(0,3);
//                buttonCounter++;
//
//            }
//        });
//        butt05.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(0,5);
//                buttonCounter++;
//
//            }
//        });
//        butt07.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(0,7);
//                buttonCounter++;
//
//            }
//        });
//        butt10.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(1,0);
//                buttonCounter++;
//
//            }
//        });
//        butt12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(1,2);
//                buttonCounter++;
//
//            }
//        });
//        butt14.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(1,4);
//                buttonCounter++;
//
//            }
//        });
//        butt16.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(1,6);
//                buttonCounter++;
//
//            }
//        });
//        butt21.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(2,1);
//                buttonCounter++;
//
//            }
//        });
//        butt23.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(2,3);
//                buttonCounter++;
//
//            }
//        });
//        butt25.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(2,5);
//                buttonCounter++;
//
//            }
//        });
//        butt27.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(2,7);
//                buttonCounter++;
//
//            }
//        });
//        butt30.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(3,0);
//            }
//        });
//        butt32.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(3,2);
//            }
//        });
//        butt34.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(3,4);
//            }
//        });
//        butt36.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(3,6);
//            }
//        });
//        butt41.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(4,1);
//            }
//        });
//        butt43.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(4,3);
//            }
//        });
//        butt45.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(4,5);
//            }
//        });
//        butt47.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(4,7);
//            }
//        });
//        butt50.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(5,0);
//            }
//        });
//        butt52.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(5,2);
//            }
//        });
//        butt54.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(5,4);
//            }
//        });
//        butt56.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(5,6);
//            }
//        });
//        butt61.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(6,1);
//            }
//        });
//        butt63.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(6,3);
//            }
//        });
//        butt65.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(6,5);
//            }
//        });
//        butt67.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(6,7);
//            }
//        });
//        butt70.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(7,0);
//            }
//        });
//        butt72.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(7,2);
//            }
//        });
//        butt74.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(7,4);
//            }
//        });
//        butt76.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start = new space(7,6);
//            }
//        });
//        while (true)
//        {
//            testTxt.setText("!!!!!");
//
//        }
        player1Score = (TextView)findViewById(R.id.player1score);
        player2Score = (TextView)findViewById(R.id.player2score);



    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Timer buttonTimer = new Timer();
            switch (view.getId()) {
                case R.id.b01:
                    start = new space(0, 1);
                    butt01.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt01.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b03:
                    start = new space(0, 3);
                    butt03.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt03.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b05:
                    start = new space(0, 5);
                    butt05.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt05.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b07:
                    start = new space(0, 7);
                    butt07.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt07.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b10:
                    start = new space(1, 0);
                    butt10.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt10.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b12:
                    start = new space(1, 2);
                    butt12.setEnabled(false);
                    buttonTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    butt12.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b14:
                    start = new space(1, 4);
                    butt14.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt14.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b16:
                    start = new space(1, 6);
                    butt16.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt16.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b21:
                    start = new space(2, 1);
                    butt21.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt21.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b23:
                    start = new space(2, 3);
                    butt23.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt23.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b25:
                    start = new space(2, 5);
                    butt25.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt25.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b27:
                    start = new space(2, 7);
                    butt27.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt27.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b30:
                    start = new space(3, 0);
                    butt30.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt30.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b32:
                    start = new space(3, 2);
                    butt32.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt32.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b34:
                    start = new space(3, 4);
                    butt34.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt34.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b36:
                    start = new space(3, 6);
                    butt36.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt36.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b41:
                    start = new space(4, 1);
                    butt41.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt41.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b43:
                    start = new space(4, 3);
                    butt43.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt43.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b45:
                    start = new space(4, 5);
                    butt45.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt45.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b47:
                    start = new space(4, 7);
                    butt47.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt47.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b50:
                    start = new space(5, 0);
                    butt50.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt50.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b52:
                    start = new space(5, 2);
                    butt52.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt52.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b54:
                    start = new space(5, 4);
                    butt54.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt54.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b56:
                    start = new space(5, 6);
                    butt56.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt56.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b61:
                    start = new space(6, 1);
                    butt61.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt61.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b63:
                    start = new space(6, 3);
                    butt63.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt63.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b65:
                    start = new space(6, 5);
                    butt65.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt65.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b67:
                    start = new space(6, 7);
                    butt67.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt67.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b70:
                    start = new space(7, 0);
                    butt70.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt70.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b72:
                    start = new space(7, 2);
                    butt72.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt72.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b74:
                    start = new space(7, 4);
                    butt74.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt74.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;
                case R.id.b76:
                    start = new space(7, 6);
                    butt76.setEnabled(false);

                    buttonTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    butt76.setEnabled(true);
                                }
                            });
                        }
                    }, delay);
                    break;

            }

            if (start.getxVal() == xVal1 && start.getyVal() == yVal1) {
                for (int i =0; i <len; i++){
                    imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(R.drawable.ic_action_name2);
                }
                xVal1=-1;
                yVal1=-1;
            } else if (turnNumber % 2 == 0) {
                if (game.pieceX(start.getxVal(), start.getyVal()) || game.pieceXKing(start.getxVal(), start.getyVal())) {
                    if (xVal1!=-1) {
                        for (int i = 0; i < len; i++) {
                            imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(R.drawable.ic_action_name2);
                        }
                    }
                    len = game.xPossibleMove(moveArr, start.getxVal(), start.getyVal());
                    for (int i = 0; i < len; i++) {
                        imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(R.drawable.blankborder);
                    }
                    xVal1 = start.getxVal();
                    yVal1 = start.getyVal();
                }
                else{
                    for (int i = 0; i < len; i++){
                        if (start.getxVal()==moveArr[i].xVal2&&start.getyVal()==moveArr[i].yVal2){
                            xVal2= start.getxVal();
                            yVal2= start.getyVal();
                            if (xVal1!=-1)
                                gameDeterminate();
                        }
                    }
                }
            } else if (turnNumber%2==1) {
                if (game.pieceO(start.getxVal(), start.getyVal()) || game.pieceOKing(start.getxVal(), start.getyVal())) {
                    if (xVal1!=-1) {
                        for (int i = 0; i < len; i++) {
                            imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(R.drawable.ic_action_name2);
                        }
                        xVal1=-1;
                        yVal1=-1;
                    }
                    len = game.oPossibleMove(moveArr, start.getxVal(), start.getyVal());
                    for (int i = 0; i < len; i++) {
                        imgArr[moveArr[i].xVal2][moveArr[i].yVal2].setImageResource(R.drawable.blankborder);
                    }
                    xVal1 = start.getxVal();
                    yVal1 = start.getyVal();
                }
                else{
                    for (int i = 0; i < len; i++){
                        if (start.getxVal()==moveArr[i].xVal2&&start.getyVal()==moveArr[i].yVal2){
                            xVal2= start.getxVal();
                            yVal2= start.getyVal();
                            if (xVal1!=-1)
                                gameDeterminate();
                        }
                    }
                }
            }
        }
    };
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
                resetCounter();
            } else if (turnNumber % 2 == 1) {
                oTurn();
                resetCounter();
            }
        }
    }
    private void xTurn(){
        if(doubleJump) {
            if(xVal1 == dj.getxVal()&&yVal1==dj.getyVal()){
                if(game.validJumpX(xVal1, yVal1, xVal2, yVal2)){
                    testTxt.setText("Player 2's Turn!");
                    game.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                    updateBoard();
                    if(game.doubleJumpX(xVal2,yVal2)){
                        doubleJump=true;
                        testTxt.setText("You can even jump again!");
                        dj = new space(yVal2,xVal2);
                    }
                    else{
                        turnNumber++;
                        jumplessTurns++;
                        doubleJump = false;
                    }
                    start = new space();
                    xVal1=-1;
                }
            }
        }
        else{
            if (game.validMoveX(xVal1, yVal1, xVal2, yVal2)&&!game.xForcedJump()){
                testTxt.setText("Player 2's Turn!");
                game.updateGameboard(xVal1, yVal1, xVal2, yVal2, false);
                turnNumber++;
                jumplessTurns++;
                updateBoard();
                start = new space();
                xVal1=-1;
            }
            else if (game.validJumpX(xVal1, yVal1, xVal2, yVal2)){
                testTxt.setText("Player 2's Turn!");
                game.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                updateBoard();
                jumplessTurns=0;
                doubleJump=false;
                if(game.doubleJumpX(xVal2,yVal2)){

                    doubleJump=true;
                    testTxt.setText("You may jump again!");
                    dj = new space(yVal2,xVal2);
                }
                else{
                    turnNumber++;
                    doubleJump = false;
                    jumplessTurns++;
                }
                start = new space();
                xVal1=-1;
            }
            else if (game.xForcedJump())
            {
                testTxt.setText("You must jump!");
            }
            else {
                testTxt.setText("X INVALID MOVE!");
            }
        }

    }
    private void oTurn(){
        if(doubleJump) {
            if(xVal1 == dj.getxVal()&&yVal1==dj.getyVal()){
                if(game.validJumpO(xVal1, yVal1, xVal2, yVal2)){
                    testTxt.setText("Player 1's Turn!");
                    game.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                    updateBoard();
                    if(game.doubleJumpX(xVal2,yVal2)){
                        doubleJump=true;
                        testTxt.setText("You can even jump again!");
                        dj = new space(yVal2,xVal2);
                    }
                    else{
                        turnNumber++;
                        jumplessTurns++;
                        doubleJump = false;
                    }
                    start = new space();
                    xVal1=-1;
                }
            }
        }
        else{
            if (game.validMoveO(xVal1, yVal1, xVal2, yVal2)&&!game.oForcedJump()){
                testTxt.setText("Player 1's Turn!");
                game.updateGameboard(xVal1, yVal1, xVal2, yVal2, false);
                turnNumber++;
                jumplessTurns++;
                updateBoard();
                start = new space();
                xVal1=-1;
                xVal2=-1;
            }
            else if (game.validJumpO(xVal1, yVal1, xVal2, yVal2)){
                testTxt.setText("Player 1's Turn!");
                game.updateGameboard(xVal1, yVal1, xVal2, yVal2, true);
                updateBoard();
                jumplessTurns=0;
                if(game.doubleJumpO(xVal2,yVal2)){

                    doubleJump=true;
                    testTxt.setText("You may jump again!");
                    dj = new space(yVal2,xVal2);
                }
                else{
                    turnNumber++;
                    jumplessTurns++;
                    doubleJump = false;
                }
                start = new space();
                xVal1=-1;
                xVal2=-1;
            }
            else if (game.oForcedJump())
            {
                testTxt.setText("You must jump!");
            }
            else {
                testTxt.setText("O INVALID MOVE!");
            }
        }

    }
    private void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.arr[i][j] == 'x') {
                    if (xVal2==j&&yVal2==i){
                        imgArr[j][i].setImageResource(R.drawable.redcircleborder);

                    }
                    else
                        imgArr[j][i].setImageResource(R.drawable.ic_action_name);
                } else if (game.arr[i][j] == 'o') {
                    if (xVal2==j&&yVal2==i){
                        imgArr[j][i].setImageResource(R.drawable.blackcircleborder);

                    }else
                    imgArr[j][i].setImageResource(R.drawable.ic_action_name1);
                } else if (game.arr[i][j] == ' ') {
                    imgArr[j][i].setImageResource(R.drawable.ic_action_name2);
                } else if (game.arr[i][j] == 'X') {
                    if (xVal2==j&&yVal2==i){
                        imgArr[j][i].setImageResource(R.drawable.redxborder);

                    } else
                    imgArr[j][i].setImageResource(R.drawable.redx);
                } else if (game.arr[i][j] == 'O') {
                    if (xVal2==j&&yVal2==i){
                        imgArr[j][i].setImageResource(R.drawable.blackxborder);

                    } else
                    imgArr[j][i].setImageResource(R.drawable.blackx);
                }
            }
        }
    }
    private void updateBoard() {
        game.updateKings();
        resetBoard();
        player1Score.setText(Integer.toString(game.xCounter()));
        player2Score.setText(Integer.toString(game.oCounter()));
        game.setGameOver(turnNumber, jumplessTurns);
        gameOver = game.gameOverFlag;
        if (gameOver!=0) {
            if (gameOver==1) {
                testTxt.setText("Player 1 Wins!");
            } else if (gameOver==2)
                testTxt.setText("Player 2 Wins!");
            else if (gameOver==3) {
                testTxt.setText("It's a Tie!");
            }
        }
    }
}
