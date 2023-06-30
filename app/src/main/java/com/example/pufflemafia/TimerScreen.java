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
    private Button fiveSecondsButton;
    private ToggleButton pauseButton;
    private TextView TimerNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_screen);
//        makeTimerNotification();

        configureTimerButtons();
        configureTimerTextView();
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

        try{
            Timer timer = TimerManager.getCurrentTimer();
            TimerNumbers.setText(timer.getCurrentTime().minute + ":" + timer.getCurrentTime().second);
        }catch (Exception ignored){

        }

        if(TimerManager.isTimerGoing == true){
            updateToggleButton(false);
        }
        else{
            updateToggleButton(true);
        }
    }

    private void configureTimerButtons(){
        pauseButton = findViewById(R.id.PausePlay);
        pauseButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean paused) {
                if(paused){
                    SoundManager.playSfx("Click");
                    TimerManager.Pause();
                    pauseButton.setBackgroundResource(R.drawable.red_rectangle);
                }
                else{
                    SoundManager.playSfx("Click");
                    TimerManager.Resume();
                    pauseButton.setBackgroundResource(R.drawable.green_rectangle);
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
                updateToggleButton(false);
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
                updateToggleButton(false);
            }
        });
        threeMinutesButton = findViewById(R.id.ThreeMinutes);
        threeMinutesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(3,0));
                TimerManager.Play();
                updateToggleButton(false);
            }
        });
        thirtySecondsButton = findViewById(R.id.ThirtySeconds);
        thirtySecondsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(0,5));
                TimerManager.Play();
                updateToggleButton(false);
            }
        });

        fiveSecondsButton = findViewById(R.id.fiveSeconds);
        fiveSecondsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                TimerManager.setCurrentTimer(new Timer(0,5));
                TimerManager.Play();
                updateToggleButton(false);
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

    private void updateToggleButton(Boolean value) {
        pauseButton.setChecked(value);
        int sfxBackgroundResId = value ? R.drawable.red_rectangle : R.drawable.green_rectangle;
        pauseButton.setBackgroundResource(sfxBackgroundResId);
    }

}