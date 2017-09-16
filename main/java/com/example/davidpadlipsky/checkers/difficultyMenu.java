package com.example.davidpadlipsky.checkers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class difficultyMenu extends AppCompatActivity {

    Button easy;
    Button hard;
    Button extreme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_difficulty_menu);
        easy = (Button)findViewById(R.id.Easy);
        easy.setOnClickListener(onClickListener);
        hard = (Button)findViewById(R.id.Hard);
        hard.setOnClickListener(onClickListener);
        extreme = (Button)findViewById(R.id.extreme);
        extreme.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.Easy:
                    openEasyMode();
                    break;
                case R.id.Hard:
                    openHardMode();
                    break;
                case R.id.extreme:
                    openExtremeMode();
                    break;
            }
        }
    };

    private void openEasyMode(){
        Intent intent = new Intent(this, checkersOneplayerEasy.class);
        startActivity(intent);
    }

    private void openHardMode(){
        Intent intent = new Intent(this, onePlayerCheckersHard.class);
        startActivity(intent);
    }

    private void openExtremeMode(){
        Intent intent = new Intent(this, OnePlayerVeryHard.class);
        startActivity(intent);
    }
}
