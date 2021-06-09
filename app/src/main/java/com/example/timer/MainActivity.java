package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.controls.templates.ControlButton;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar timer;
    TextView textView;
    Button ControllerButton;
    CountDownTimer countDownTimer;
    boolean flage=true;
    public void updateTimer(int progress){
        int min=(int) progress/60;
        int sec=progress-(min*60);
        if(min<10){
            if(sec>=10) {
                textView.setText("0" + min + ":" + sec);
            }
            else{
                textView.setText("0"+min+":"+"0"+sec);
            }
        }
        else{
            if(sec>=10) {
                textView.setText(min + ":" + sec);
            }
            else{
                textView.setText(min+":"+"0"+sec);
            }
        }
    }
    public void controlTimer(View view){
        if(flage){
            timer.setEnabled(false);
            countDownTimer=new CountDownTimer(timer.getProgress()*1000 , 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }
                @Override
                public void onFinish() {
                    timer.setEnabled(true);
                    MediaPlayer player = MediaPlayer.create(getApplicationContext(), R.raw.sound);
                    player.start();
                    ControllerButton.setText("Go");
                    flage=true;
                }
            }.start();
            ControllerButton.setText("Stop");
            flage=false;
        }
        else{
            ControllerButton.setText("Go");
            countDownTimer.cancel();
            updateTimer(0);
            timer.setProgress(0);
            timer.setEnabled(true);
            flage=true;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=(SeekBar) findViewById(R.id.timer);
        textView=(TextView)findViewById(R.id.timertextView);
        ControllerButton=(Button)findViewById(R.id.button);
        timer.setMax(600);
        timer.setProgress(30);
        timer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);

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