package com.example.pufflemafia.app.data;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.pufflemafia.app.Event;

public class Timer {

    private CountDownTimer countDownTimer;
    private long currentTime;
    public Event<Boolean> onFinish;
    public Event<Long> onUpdate;

    public void Start(){
        countDownTimer.start();
        Log.d("Timer","Starting a timer");
    }

    public void Pause(){
        countDownTimer.cancel();
    }

    public void Stop(){
        countDownTimer.cancel();
    }

    public Timer(int totalMinuets, int totalSeconds){

        onFinish = new Event<Boolean>();
        onUpdate = new Event<Long>();

        countDownTimer = new CountDownTimer((totalMinuets * 60000) + (totalSeconds * 1000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = millisUntilFinished / 1000;
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
