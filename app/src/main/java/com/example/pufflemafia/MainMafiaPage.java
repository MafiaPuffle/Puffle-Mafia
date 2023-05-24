package com.example.pufflemafia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMafiaPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        add_new(this);


        //Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();
    }


    //Start The Night Button
    private void configureStartTheNightButton() {
        Button StartTheNightButton = (Button) findViewById(R.id.StartTheNightButton);
        StartTheNightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMafiaPage.this, NightActions.class));
            }
        });
    }

    //Back Button
    private void configureDayBacktoChooseYourCharactersButton(){
        Button DayBacktoChooseYourCharacters = (Button) findViewById(R.id.DayBacktoChooseYourCharactersButton);
        DayBacktoChooseYourCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static void add_new(final Activity activity)
    {
        final LinearLayout linearLayoutForm = (LinearLayout) activity.findViewById(R.id.AliveCharacterBox);
        final LinearLayout newView = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.character_ui_night_layout, null);
        newView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        linearLayoutForm.addView(newView);
    }
}