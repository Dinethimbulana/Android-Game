package com.example.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class Levels extends AppCompatActivity {

    //private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_levels);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {}

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.harry2);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        game1();
    }

    private Button button;
    private void game1(){

        button = (Button) findViewById(R.id.game1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Wobble).duration(500).playOn((Button)findViewById(R.id.game1));
                Intent intent = new Intent(Levels.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}

