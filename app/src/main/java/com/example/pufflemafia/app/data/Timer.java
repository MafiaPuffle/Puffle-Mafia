package com.example.pufflemafia.app.data;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.pufflemafia.app.Event;

public class Timer {

    private CountDownTimer countDownTimer;
    private Time startingTime;
    private Time currentTime;
    public Time getCurrentTime(){return currentTime;}
    private Boolean isTimerGoing;
    public Event<Boolean> onFinish;
    public Event<Time> onUpdate;

    public void Start(){
        countDownTimer.start();
        isTimerGoing = true;
        Log.d("Timer","Starting a timer");
    }

    public void Pause(){
        countDownTimer.cancel();
        isTimerGoing = false;
    }
    public void Resume(){
        countDownTimer = builder(currentTime);
        countDownTimer.start();
        isTimerGoing = true;
    }

    public void Restart(){
        countDownTimer.cancel();
        countDownTimer = builder(startingTime);
        countDownTimer.start();
        isTimerGoing = true;
    }

    public void Stop(){
        countDownTimer.cancel();
        isTimerGoing = false;
    }

    public Timer(int totalMinuets, int totalSeconds){

        onFinish = new Event<Boolean>();
        onUpdate = new Event<Time>();
        startingTime = new Time(totalMinuets, totalSeconds);
        currentTime = new Time(totalMinuets, totalSeconds);
        isTimerGoing = false;

        countDownTimer = builder(startingTime);
    }

    private CountDownTimer builder(Time time){
        return new CountDownTimer((time.minute * 60000) + (time.second * 1000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime.second = (int) millisUntilFinished / 1000;
                currentTime.minute = (int) currentTime.second / 60;
                currentTime.second = (int) currentTime.second % 60;
                onUpdate.Invoke(currentTime);
            }

            @Override
            public void onFinish() {
                long zero = 0;
                onFinish.Invoke();
            }
        };
    }
}
