package com.example.davidpadlipsky.checkers;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class mainMenu extends AppCompatActivity {

    Button oneplayer;
    Button twoplayer;
    Button options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);
        twoplayer = (Button)findViewById(R.id.Hard);
        twoplayer.setOnClickListener(onClickListener);
        oneplayer = (Button)findViewById(R.id.oneplayer);
        oneplayer.setOnClickListener(onClickListener);
        options = (Button)findViewById(R.id.options);
        options.setOnClickListener(onClickListener);
//        View decorView = getWindow().getDecorView();
//// Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
//// Remember that you should never show the action bar if the
//// status bar is hidden, so hide that too if necessary.
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();

    }

    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.oneplayer:{

                    startOnePlayer();
                    break;
                }
                case R.id.Hard:{
//                    Intent intent = new Intent(this, checkers2Players.class);
//                    startActivity(intent);
                    startTwoPlayer();
                    break;
                }
                case R.id.options:{
                    startOptions();
                    break;
                }
            }
        }
    };
    private void startOnePlayer(){
        Intent intent = new Intent(this, difficultyMenu.class);
        startActivity(intent);
    }

    private void startTwoPlayer(){
        Intent intent = new Intent(this, checkers2Players.class);
        startActivity(intent);
    }

    private void startOptions(){
        Intent intent = new Intent(this, options.class);
        startActivity(intent);
    }

    {

    }
}
