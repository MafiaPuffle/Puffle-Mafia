package com.example.pufflemafia.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.game.HelpPrompt;

public class CustomAppCompatActivityWrapper extends AppCompatActivity {

    private View helpPopupWindowRoot;
    private Button helpPopupNextButton;
    private Button helpPopupPreviousButton;
    private Button helpPopupCloseButton;
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

        //TODO sets up the popup window variables

        helpPopupWindowRoot.setVisibility(View.GONE);
    }

    public void StartHelp(){
        //TODO starts the help
        currentHelpPrompt = DataManager.GetHelpPrompt(currentScreen, currentPopupWindowIndex);
    }

    public void NextHelp(){
        //TODO next help
        currentPopupWindowIndex++;
        currentHelpPrompt = DataManager.GetHelpPrompt(currentScreen, currentPopupWindowIndex);
    }

    public void BackHelp(){
        //TODO back help
        currentPopupWindowIndex--;
        currentHelpPrompt = DataManager.GetHelpPrompt(currentScreen, currentPopupWindowIndex);
    }

    public void ExitHelp(){
        //TODO exit help
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

    public void animateMoveViewToTarget(View viewToAnimate, View targetToMoveTo){
        int[] targetLocation = new int[2];
        targetToMoveTo.getLocationOnScreen(targetLocation);

        float targetX = (targetLocation[0] + targetToMoveTo.getWidth() / 2) - viewToAnimate.getWidth() / 2;
        float targetY = (targetLocation[1] + targetToMoveTo.getHeight() / 2) - viewToAnimate.getHeight() / 2;

        viewToAnimate.animate().x(targetX).y(targetY).setDuration(500).start();
    }
}
