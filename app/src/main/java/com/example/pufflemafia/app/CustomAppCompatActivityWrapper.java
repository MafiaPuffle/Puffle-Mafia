package com.example.pufflemafia.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.game.HelpPrompt;

public class CustomAppCompatActivityWrapper extends AppCompatActivity {

    private ImageView finger;
    private View helpPopupWindowRoot;
    private TextView helpPopupTextView;
    private Button helpPopupNextButton;
    private Button helpPopupPreviousButton;
    private ImageButton helpPopupCloseButton;
    private int currentScreen;
    private int currentPopupWindowIndex;
    private HelpPrompt currentHelpPrompt;
    private ScreenLifeCycleWatcher screenLifeCycleWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        screenLifeCycleWatcher = new ScreenLifeCycleWatcher();
        getLifecycle().addObserver(screenLifeCycleWatcher);
        screenLifeCycleWatcher.Setup();
    }

    public void SetupPopupWindow(int currentScreen){
        this.currentPopupWindowIndex = 0;
        this.currentScreen = currentScreen;

        helpPopupWindowRoot = findViewById(R.id.help_button_ui);
        finger = findViewById(R.id.finger);
        helpPopupTextView = findViewById(R.id.HelpInstructions);

        helpPopupNextButton = findViewById(R.id.helpnext);
        helpPopupNextButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    NextHelp();
                }
                return false;
            }
        });

        helpPopupPreviousButton = findViewById(R.id.helpback);
        helpPopupPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackHelp();
            }
        });

        helpPopupCloseButton = findViewById(R.id.Xbutton);
        helpPopupCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitHelp();
            }
        });
        animateMoveViewToTarget(helpPopupWindowRoot, getCenterOfScreen(), 0);
        helpPopupWindowRoot.setVisibility(View.INVISIBLE);
        finger.setVisibility(View.INVISIBLE);
    }

    private void RefreshPopUp(){
        helpPopupTextView.setText(currentHelpPrompt.getPrompt());
        animateMoveViewToTarget(finger, findViewById(currentHelpPrompt.getPointsTo()),0);
        animateMoveViewToTarget(helpPopupWindowRoot, getCenterOfScreen(),0);

        View view = findViewById(currentHelpPrompt.getPointsTo());
        view.setClickable(true);
        view.setFocusable(true);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    NextHelp();
                }
                return false;
            }
        });
    }

    public void StartHelp(){
        //TODO starts the help
        currentHelpPrompt = DataManager.GetHelpPrompt(currentScreen, currentPopupWindowIndex);
        helpPopupWindowRoot.setVisibility(View.VISIBLE);
        finger.setVisibility(View.VISIBLE);
        animateMoveViewToTarget(helpPopupWindowRoot, getCenterOfScreen(), 0);
        RefreshPopUp();
    }

    public void NextHelp(){
        //TODO next help
        currentPopupWindowIndex++;
        currentHelpPrompt = DataManager.GetHelpPrompt(currentScreen, currentPopupWindowIndex);
        if(currentHelpPrompt == null){
            ExitHelp();
            return;
        }
        RefreshPopUp();
    }

    public void BackHelp(){
        //TODO back help
        currentPopupWindowIndex--;
        if(currentPopupWindowIndex < 0){
            ExitHelp();
            return;
        }
        currentHelpPrompt = DataManager.GetHelpPrompt(currentScreen, currentPopupWindowIndex);
        RefreshPopUp();
    }

    public void ExitHelp(){
        //TODO exit help
        currentPopupWindowIndex = 0;
        helpPopupWindowRoot.setVisibility(View.INVISIBLE);
        finger.setVisibility(View.INVISIBLE);
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

    public void animateMoveViewToTarget(View viewToAnimate, View targetToMoveTo, long duration){
        int[] targetLocation = new int[2];
        targetToMoveTo.getLocationOnScreen(targetLocation);

        float targetX = (targetLocation[0] + targetToMoveTo.getWidth() / 2) - viewToAnimate.getWidth() / 2;
        float targetY = (targetLocation[1] + targetToMoveTo.getHeight() / 2) - viewToAnimate.getHeight() / 2;

        viewToAnimate.animate().x(targetX).y(targetY).setDuration(duration).start();
    }

    public void animateMoveViewToTarget(View viewToAnimate, int[] targetLocation, long duration){

        float targetX = targetLocation[0] - viewToAnimate.getWidth() / 2;
        float targetY = targetLocation[1] - viewToAnimate.getHeight() / 2;

        viewToAnimate.animate().x(targetX).y(targetY).setDuration(duration).start();
    }

    public int[] getCenterOfScreen(){
        int[] output = new int[2];

        // Get the window manager
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        // Create a DisplayMetrics object to hold the screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();

        // Get the screen dimensions
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        // Calculate the center coordinates
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        output[0] = centerX;
        output[1] = centerY;

        return output;
    }
}
