package com.example.pufflemafia.app.data;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.pufflemafia.app.Event;

public class Timer {

    private CountDownTimer countDownTimer;
    private long currentTime;
    public Event<Long> onFinish;
    public Event<Long> onUpdate;

    public void Start(){
        countDownTimer.start();
    }

    public void Pause(){
        countDownTimer.cancel();
    }

    public void Stop(){
        countDownTimer.start();
    }

    public Timer(int totalMinuets, int totalSeconds){
        countDownTimer = new CountDownTimer((totalMinuets * 60000) + (totalSeconds * 1000), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime = millisUntilFinished / 1000;
                onUpdate.Invoke(currentTime);
            }

            @Override
            public void onFinish() {
                long zero = 0;
                onFinish.Invoke(zero);
            }
        };
    }
}
