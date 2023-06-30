package com.example.pufflemafia.app.data;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.Listener;

public class TimerManager {
    private static Timer currentTimer;

    public static Timer getCurrentTimer() {
        return currentTimer;
    }

    private static TimerManager instance;

    public static Boolean isTimerGoing;

    public static Event<Boolean> onFinish;
    public static Event<Time> onUpdate;

    private static boolean eventsHaveBeenSetUp;

    public static void SetUpEvents(){
        currentTimer.onFinish.AddListener(new IListener<Boolean>() {
            @Override
            public void Response() {
                Log.d("TimerManager", "Timer Done!");
                CustomAppCompatActivityWrapper.instance.makeTimerNotification();
                onFinish.Invoke();
            }

            @Override
            public void Response(Boolean aBoolean) {

            }
        });

//        currentTimer.onUpdate.AddListener(new IListener<Time>() {
//            @Override
//            public void Response() {
//
//            }
//
//            @Override
//            public void Response(Time time) {
//                Log.d("TimeManager",time.minute + ": " + time.second);
//                onUpdate.Invoke(time);
//            }
//        });

        eventsHaveBeenSetUp = true;
    }

    public static void setCurrentTimer(Timer timer) {
        if (isTimerGoing == true) {
            if (currentTimer != null) {
                currentTimer.Stop();
                isTimerGoing = false;
                
                currentTimer.onFinish.RemoveAllListeners();
                currentTimer.onUpdate.RemoveAllListeners();
            }
        }

        currentTimer = timer;
        Log.d("TimerManager", "Set a new timer");
//        if(!eventsHaveBeenSetUp){
//            SetUpEvents();
//        }
        currentTimer.onFinish.AddListener(new IListener<Boolean>() {
            @Override
            public void Response() {
                Log.d("TimerManager", "Timer Done!");
                CustomAppCompatActivityWrapper.instance.makeTimerNotification();
                onFinish.Invoke();
            }

            @Override
            public void Response(Boolean aBoolean) {

            }
        });

        currentTimer.onUpdate.AddListener(new IListener<Time>() {
            @Override
            public void Response() {

            }

            @Override
            public void Response(Time time) {
                Log.d("TimeManager",time.minute + ": " + time.second);
                onUpdate.Invoke(time);
            }
        });
    }

    public static void Play(){
        if(currentTimer == null) {
            Log.d("TimerManager","Not starting TimerManager.play() because currentTimer == null");
            return;
        }
        if(isTimerGoing == true) {
            Log.d("TimerManager","Not staring TimerManager.play() because the current timer is already going");
            return;
        }

        Log.d("TimerManager","Starting a timer");
        currentTimer.Start();
        isTimerGoing = true;
    }

    public static void Resume(){
        if(currentTimer == null) return;
        if(isTimerGoing == true) return;

        currentTimer.Resume();
        isTimerGoing = true;
    }

    public static void Pause(){
        if(currentTimer == null) return;
        if(isTimerGoing == false) return;

        currentTimer.Pause();
        isTimerGoing = false;
    }

    public static void Stop(){
        if(currentTimer == null) return;
        if(isTimerGoing == false) return;

        currentTimer.Stop();
        isTimerGoing = false;
    }

    public static void Clear(){
        if(currentTimer == null) return;
        if(isTimerGoing == false) return;

        currentTimer.Clear();
        isTimerGoing = false;
    }

    public static void Restart(){
        if(currentTimer == null) return;
        if(isTimerGoing == false) return;

        currentTimer.Restart();
        isTimerGoing = true;
    }

    public TimerManager(){
        instance = this;
        isTimerGoing = false;

        onFinish = new Event<Boolean>();
        onUpdate = new Event<Time>();

        eventsHaveBeenSetUp = false;
    }
}
