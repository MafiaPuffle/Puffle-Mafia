package com.example.pufflemafia.app.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.ViewToPointTo;

import java.util.Vector;

public class HelpPromptManager {


    private static PopupWindow promptPopUp;
    private static PopupWindow pointerPopup;
    private static ImageButton XButton;
    private static TextView promptText;
    private static Button NextButton;
    private static Button BackButton;
    private static Button PointerButton;
    private static Vector<ViewToPointTo> viewsToPointTo;
    private static int viewsToPointToIndex;

    public static void DisplayHelp(){}

    private static void NextHelp(){
        viewsToPointToIndex++;
        if(viewsToPointToIndex == viewsToPointTo.size()){
            CloseHelp();
            return;
        }

        Refresh();
    }

    private static void BackHelp(){
        viewsToPointToIndex--;
        if(viewsToPointToIndex < 0){
            CloseHelp();
            return;
        }

        Refresh();
    }

    private static void CloseHelp(){
        if(promptPopUp.isShowing()){
            promptPopUp.dismiss();
        }
        if(pointerPopup.isShowing()){
            pointerPopup.dismiss();
        }
        viewsToPointToIndex = 0;
    }

    private static void Refresh(){
        if(viewsToPointToIndex == viewsToPointTo.size()){
            CloseHelp();
            return;
        }

        if(promptPopUp.isShowing()){
            promptPopUp.dismiss();
        }

        if(pointerPopup.isShowing()){
            pointerPopup.dismiss();
        }

        Handler h =new Handler() ;
        h.postDelayed(new Runnable() {
            public void run() {
                try{
                    promptPopUp.showAtLocation(viewsToPointTo.get(0).getPointsTo(), Gravity.CENTER, 0, 0);
                    pointerPopup.showAsDropDown(viewsToPointTo.get(viewsToPointToIndex).getPointsTo(), 0, -100, Gravity.CENTER);

                    promptText.setText(viewsToPointTo.get(viewsToPointToIndex).getPrompt());
                }
                catch (Exception e){
                    NextHelp();
                }
            }

        }, 1);

//        promptPopUp.showAtLocation(viewsToPointTo.get(0).getPointsTo(), Gravity.CENTER, 0, 0);
//        pointerPopup.showAsDropDown(viewsToPointTo.get(viewsToPointToIndex).getPointsTo(), 0, -100, Gravity.CENTER);
    }

    public static void InitializeHelpPopups(Activity activity, Context context, Button helpButton, Vector<ViewToPointTo> allViewsToPointTo){

        viewsToPointTo = allViewsToPointTo;
        viewsToPointToIndex = 0;

        InitializePromptPopup(activity, context);
        InitializePointerPopup(activity, context);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Refresh();
            }
        });
    }

    private static void InitializePromptPopup(Activity activity, Context context){
        // Initializes the promptPopup window
        promptPopUp = new PopupWindow(context);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View viewToAdd = layoutInflater.inflate(R.layout.help_button_ui, activity.findViewById(R.id.help_button_ui));
        promptPopUp.setContentView(viewToAdd);
        promptPopUp.setBackgroundDrawable(AppCompatResources.getDrawable(context,R.drawable.grey_rectangle));
        promptPopUp.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        promptPopUp.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        promptPopUp.setTouchable(true);
        promptPopUp.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Return false to indicate that touch events should pass through the PopupWindow
                return false;
            }
        });

        // Stores the buttons from the promptPopup window
        XButton = viewToAdd.findViewById(R.id.Xbutton);
        promptText = viewToAdd.findViewById(R.id.HelpInstructions);
        NextButton = viewToAdd.findViewById(R.id.helpnext);
        BackButton = viewToAdd.findViewById(R.id.helpback);

        // Sets onClickListeners for all buttons
        XButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CloseHelp();
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextHelp();
            }
        });

        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackHelp();
            }
        });
    }

    private static void InitializePointerPopup(Activity activity, Context context) {
        // Initializes the promptPopup window
        pointerPopup = new PopupWindow(context);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View viewToAdd = layoutInflater.inflate(R.layout.help_pointer_ui, activity.findViewById(R.id.help_pointer_ui));
        pointerPopup.setContentView(viewToAdd);
        Drawable background = new ColorDrawable(android.graphics.Color.TRANSPARENT);
        pointerPopup.setBackgroundDrawable(background);
        pointerPopup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        pointerPopup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        pointerPopup.setTouchable(true);

        pointerPopup.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Return false to indicate that touch events should pass through the PopupWindow
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    viewsToPointTo.get(viewsToPointToIndex).getPointsTo().callOnClick();
                    NextHelp();
                }
                return false;
            }
        });
    }

}
