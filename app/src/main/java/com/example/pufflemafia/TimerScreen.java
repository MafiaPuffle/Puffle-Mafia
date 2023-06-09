package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.data.Time;
import com.example.pufflemafia.app.data.Timer;
import com.example.pufflemafia.app.data.TimerManager;
import com.example.pufflemafia.app.game.SoundManager;

public class TimerScreen extends CustomAppCompatActivityWrapper {

    private Button fiveMinutesButton;
    private Button fourMinutesButton;
    private Button threeMinutesButton;
    private Button thirtySecondsButton;
    private TextView TimerNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);

        configureTimerTextView();
        configureTimerButtons();
        configureBackButton();
        configureResetButton();
    }

    private void configureTimerTextView(){
        TimerNumbers = findViewById(R.id.TimerNumbers);

        TimerManager.onUpdate.AddListener(new IListener<Time>() {
            @Override
            public void Response() {

            }

            @Override
            public void Response(Time time) {
                TimerNumbers.setText(time.minute + ":" + time.second);
            }
        });
    }

    private void configureTimerButtons(){
        ToggleButton pauseButton = findViewById(R.id.PausePlay);
        pauseButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean paused) {
                if(paused){
                    SoundManager.playSfx("Click");
                    TimerManager.Pause();
                }
                else{
                    SoundManager.playSfx("Click");
                    TimerManager.Resume();
                }
            }
        });

        fiveMinutesButton = findViewById(R.id.FiveMinutes);
        fiveMinutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(5,0));
                TimerManager.Play();
                pauseButton.setChecked(false);
                Log.d("TimerScreen","Starting 5 minute timer");
            }
        });
        fourMinutesButton = findViewById(R.id.FourMinutes);
        fourMinutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(4,0));
                TimerManager.Play();
                pauseButton.setChecked(false);
            }
        });
        threeMinutesButton = findViewById(R.id.ThreeMinutes);
        threeMinutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(3,0));
                TimerManager.Play();
                pauseButton.setChecked(false);
            }
        });
        thirtySecondsButton = findViewById(R.id.ThirtySeconds);
        thirtySecondsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(0,30));
                TimerManager.Play();
                pauseButton.setChecked(false);
            }
        });
    }

    private void configureResetButton(){
        Button resetButton = findViewById(R.id.Reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.Restart();
            }
        });
    }

    private void configureBackButton(){
        Button backButton = findViewById(R.id.BackToMainMafiaScreen);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }
}