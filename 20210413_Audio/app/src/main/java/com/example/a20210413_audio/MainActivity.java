package com.example.a20210413_audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    public static final int DELAY_MILLIS = 500;
    MediaPlayer mp;

    SeekBar sekPlayer;
    Button btnPlayer;
    EditText edtUrl;

    Handler timerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------
        sekPlayer = findViewById(R.id.sekPlayer);
        btnPlayer = findViewById(R.id.btnPlayer);
        edtUrl = findViewById(R.id.edtUrl);
        //------------------------------------------
        btnPlayer.setOnClickListener(this);
        //-----------------------------------------
        timerHandler = new Handler(Looper.getMainLooper());
        //---------------------------------------
        sekPlayer.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp1) {
        Log.i("XXX", "PREPARED");
        mp.start();
        sekPlayer.setProgress(0);
        engegarFilActualitzacio();
    }

    private void engegarFilActualitzacio() {
        timerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sekPlayer.setMax(mp.getDuration());
                sekPlayer.setProgress(mp.getCurrentPosition());
                if(mp.isPlaying()) {
                    timerHandler.postDelayed(this, DELAY_MILLIS);
                }
            }
        }, DELAY_MILLIS);
    }

    @Override
    public void onClick(View v) {

        String url = edtUrl.getText().toString();
        try{
            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(url);
            mp.setOnPreparedListener(this);
            mp.prepareAsync();

        }catch (Exception ex){
            Log.e("XXX", "Error reproduint el so.",ex);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mp.pause();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mp.seekTo(sekPlayer.getProgress());
        mp.start();
        engegarFilActualitzacio();
    }
}