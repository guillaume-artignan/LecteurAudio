package com.example.lectureaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnBufferingUpdateListener {

    private MediaPlayer mPlayer;
    private ProgressBar progress;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlayer = MediaPlayer.create(this, R.raw.pokemon);
        mPlayer.setOnBufferingUpdateListener(this);

        progress = findViewById(R.id.progression);
        progress.setMax(mPlayer.getDuration());
        progress.setProgress(0);
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                if(mPlayer.isPlaying()){
                    int position = mPlayer.getCurrentPosition();
                    progress.setProgress(position);
                }else{

                }

            }

        },0,1000);
    }

    public void play(View v){
        mPlayer.start();

    }

    public void stop(View v){
        mPlayer.stop();
        mPlayer = MediaPlayer.create(this, R.raw.pokemon);
        ProgressBar progress = findViewById(R.id.progression);
        progress.setProgress(mPlayer.getCurrentPosition());
    }

    public void pause(View v){
        mPlayer.pause();
        progress.setProgress(mPlayer.getCurrentPosition());
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        progress.setProgress(mPlayer.getCurrentPosition());
    }
}
