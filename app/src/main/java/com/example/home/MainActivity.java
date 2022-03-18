package com.example.home;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
//import android.view.WindowManager;
import android.widget.Button;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private boolean isMute;
    private Button mute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e) {}

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.harry1);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        findViewById(R.id.tap).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Levels.class));
                mediaPlayer.stop();
            }
        });


        findViewById(R.id.mute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mediaPlayer.pause();
            }
        });
    }

}