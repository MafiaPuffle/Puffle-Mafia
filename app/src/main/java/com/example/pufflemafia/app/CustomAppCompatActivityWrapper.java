package com.example.pufflemafia.app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
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
import com.example.pufflemafia.app.data.TimerManager;

public class CustomAppCompatActivityWrapper extends AppCompatActivity {
    private ScreenLifeCycleWatcher screenLifeCycleWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenLifeCycleWatcher = new ScreenLifeCycleWatcher();
        getLifecycle().addObserver(screenLifeCycleWatcher);
        screenLifeCycleWatcher.Setup();

        CustomAppCompatActivityWrapper instance = this;

        try{
            TimerManager.onFinish.AddListener(new IListener<Boolean>() {
                @Override
                public void Response() {
                    // Build the notification
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(instance)
                            .setSmallIcon(R.drawable.mafia_puffle)
                            .setContentTitle("Times Up!")
                            .setContentText("Your timer finished")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    // Show the notification
                    int notificationId = 1; // An ID unique to this notification
                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(instance);
                    if (ActivityCompat.checkSelfPermission(instance, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    notificationManager.notify(notificationId, builder.build());
                }

                @Override
                public void Response(Boolean aBoolean) {

                }
            });
        }catch (Exception ignored){

        }

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
}
