package com.example.pufflemafia.app;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pufflemafia.R;
import com.example.pufflemafia.TimerScreen;
import com.example.pufflemafia.app.data.TimerManager;

public class CustomAppCompatActivityWrapper extends AppCompatActivity {
    private ScreenLifeCycleWatcher screenLifeCycleWatcher;
    private final String CHANNEL_ID = "default";
    private final String CHANNEL_NAME = "Timer";
    private final String CHANNEL_DESCRIPTION = "For when the timer goes off";
    private final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    public static CustomAppCompatActivityWrapper instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenLifeCycleWatcher = new ScreenLifeCycleWatcher();
        getLifecycle().addObserver(screenLifeCycleWatcher);
        screenLifeCycleWatcher.Setup();

        instance = this;

        String[] permissions = {Manifest.permission.VIBRATE, Manifest.permission.RECEIVE_BOOT_COMPLETED, Manifest.permission.POST_NOTIFICATIONS};
        int requestCode = 1;
        ActivityCompat.requestPermissions(this, permissions, requestCode);

    }

    public void InitializeHelpPopups(Activity activity, Context context, Button button, View rootView, View anchor){
        PopupWindow popupWindow = new PopupWindow(context);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View viewToAdd = layoutInflater.inflate(R.layout.help_button_ui, findViewById(R.id.help_button_ui));

        ImageButton XButton = viewToAdd.findViewById(R.id.Xbutton);
        Button NextButton = viewToAdd.findViewById(R.id.helpnext);
        Button BackButton = viewToAdd.findViewById(R.id.helpback);

        popupWindow.setContentView(viewToAdd);
        popupWindow.setBackgroundDrawable(AppCompatResources.getDrawable(this,R.drawable.grey_rectangle));
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
            }
        });

        XButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                popupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }

    public void makeKeyboardHidealbe(View rootView) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(rootView instanceof EditText)) {
            rootView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(CustomAppCompatActivityWrapper.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (rootView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) rootView).getChildCount(); i++) {
                View innerView = ((ViewGroup) rootView).getChildAt(i);
                makeKeyboardHidealbe(innerView);
            }
        }
    }

    public void makeTimerNotification(){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.mafia_puffle)
                .setContentTitle("Times up!")
                .setContentText("Lets get back to the game")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                // Set the intent that will fire when the user taps the notification
                .setAutoCancel(true);

        createNotificationChannel();


        try {
            Log.d("CustomAppCompActivityWrapper", "I heard that the timer is done");



            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(instance, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Log.d("CustomAppCompatActivityWrapper","Could not display notification because the permission was not enabled");
                return;
            }
            notificationManager.notify(NOTIFICATION_ID, builder.build());

            Log.d("CustomAppCompatActivityWrapper","A notification should have appeared");

            Intent intent = new Intent(instance, TimerScreen.class);
            startActivity(intent);
        }catch (Exception ignored){

        }
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(CHANNEL_DESCRIPTION);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
