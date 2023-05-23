package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMafiaPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        //Button StartNight = findViewById(R.id.StartTheNightButton);
        //StartNight.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent(MainMafiaPage.this, NightActions.class);
        //        startActivity(intent);
        //    }
        //});

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