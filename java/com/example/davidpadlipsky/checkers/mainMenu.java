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
        setContentView(R.layout.activity_main_menu);
        twoplayer = (Button)findViewById(R.id.Hard);
        twoplayer.setOnClickListener(onClickListener);
        oneplayer = (Button)findViewById(R.id.oneplayer);
        oneplayer.setOnClickListener(onClickListener);
        options = (Button)findViewById(R.id.options);
        options.setOnClickListener(onClickListener);
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
}
