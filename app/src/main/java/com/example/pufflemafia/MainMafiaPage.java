package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMafiaPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        //Sets up the Game Manager
        //GameManager.StartNewGame(AppManager.gameSetup);

        LinearLayout allAliveLayout = findViewById(R.id.AliveCharacterBox);

        ConstraintLayout playerDayUIConstraintLayout = (ConstraintLayout) this.getLayoutInflater().inflate(R.layout.character_ui_day_layout, null);

        View testView = this.getLayoutInflater().inflate(R.layout.character_ui_day_layout,null);

        View wantThisView = testView.findViewById(R.id.CharacterUIINBox);

        //LinearLayout playerDayUILinearLayout = playerDayUIConstraintLayout.findViewById(R.id.CharacterUIINBox);
        //playerDayUILinearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));


        //allAliveLayout.addView(wantThisView);


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

}