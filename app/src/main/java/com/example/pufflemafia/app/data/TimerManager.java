package com.example.pufflemafia.app.data;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.IListener;

public class TimerManager implements IListener<Long> {
    private static Timer currentTimer;
    private static TimerManager instance;

    public static Boolean isTimerGoing;

    public static Event<Long> onFinish;
    public static Event<Long> onUpdate;

    public static void setCurrentTimer(Timer timer){
        if(isTimerGoing = true){
            if (currentTimer != null){
                currentTimer.Stop();
            }
        }

        currentTimer = timer;
        currentTimer.onFinish.AddListener(instance);
    }

    public static void Play(){
        if(currentTimer == null) return;
        if(isTimerGoing = true) return;

        currentTimer.Start();
        isTimerGoing = true;
    }

    public static void Pause(){
        if(currentTimer == null) return;
        if(isTimerGoing = false) return;

        currentTimer.Pause();
        isTimerGoing = false;
    }

    public static void Stop(){
        if(currentTimer == null) return;
        if(isTimerGoing = false) return;

        currentTimer.Stop();
        isTimerGoing = false;
    }

    public TimerManager(){
        instance = this;
    }

    @Override
    public void Response() {

    }

    @Override
    public void Response(Long value) {
        if(value != 0){
            onUpdate.Invoke(value);
        }
        else {
            onFinish.Invoke();
        }
    }
}
