package com.example.pufflemafia.app.data;

import android.os.CountDownTimer;
import android.util.Log;

import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.Listener;

public class TimerManager {
    private static Timer currentTimer;
    private static TimerManager instance;

    public static Boolean isTimerGoing;

    public static Event<Long> onFinish;
    public static Event<Long> onUpdate;

    public static void setCurrentTimer(Timer timer){
        if(isTimerGoing = true){
            if (currentTimer != null){
                currentTimer.Stop();
                //TODO remove all listeners of old timer
            }
        }

        currentTimer = timer;
        currentTimer.onFinish.AddListener(new IListener<Boolean>() {
            @Override
            public void Response() {
                Log.d("TimerManager", "Timer Done!");
                onFinish.Invoke();
            }

            @Override
            public void Response(Boolean aBoolean) {

            }
        });
        currentTimer.onUpdate.AddListener(new IListener<Long>() {
            @Override
            public void Response() {

            }

            @Override
            public void Response(Long aLong) {
                Log.d("TimerManager", "Current time: " + aLong);
                onUpdate.Invoke(aLong);
            }
        });
    }

    public static void Play(){
        if(currentTimer == null) return;
        if(isTimerGoing = true) return;

        Log.d("TimerManager","Starting a timer");
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
        isTimerGoing = false;
    }
}
