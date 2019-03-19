package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView text;
    int turn = 0;

    public void update(long secondsLeft){

        text = (TextView) findViewById(R.id.timertext);
        long minutes = secondsLeft / 60;
        long seconds = secondsLeft % 60;
        if(seconds / 10 < 1){
            text.setText(minutes + " : 0" + seconds);
        }
        else {
            text.setText(minutes + " : " + seconds);
        }

    }
    public void play(View view){
        Button but = findViewById(R.id.button);
        if(turn == 0){
            seekBar.setEnabled(false);
            but.setText("STOP");
            turn = 1;
            global_counter = new CountDownTimer(seekBar.getProgress() * 1000 + 100,1000){
                public void onTick(long milliseconds){
                    Log.i("millisecond value", "" + (milliseconds/1000));
                    long count = milliseconds / 1000;
                    update(count);
                }
                public void onFinish(){
                    text.setText("0:00");
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mp.start();
                }
            };
            global_counter.start();
            // timer starts

        }else{
             seekBar.setEnabled(true);
             but.setText("GO!");
             text.setText("0 : 30");
             seekBar.setProgress(30);
             turn = 0;
             global_counter.cancel();
        }
    }
    CountDownTimer global_counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // work on the seekbar
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
