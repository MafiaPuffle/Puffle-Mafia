package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Start extends AppCompatActivity {
 // SeekBar IDs
   SeekBar NumberOfCharactersSeekBar;
   TextView NumberOfPlayersText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        //SeekBar
        NumberOfCharactersSeekBar = findViewById(R.id.NumberOfCharactersSeekBar);
        NumberOfPlayersText = findViewById(R.id.NumberOfPlayersText);

        NumberOfCharactersSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //NumberOfPlayersText.setVisibility(View.VISIBLE);
                NumberOfPlayersText.setText(progress+" Players");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Configure Buttons
       configureBackToMainMenu();
       configureChooseCharactersButton();
    }


    //Character Select Screen Button
    private void configureChooseCharactersButton() {
        Button ChooseCharactersButtonButton = (Button) findViewById(R.id.ChooseCharactersButton);
        ChooseCharactersButtonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this, CharacterSelectScreen.class));
            }
        });
    }



    //BACK BUTTON
    private void configureBackToMainMenu(){
        Button BackToMainMenuButton = (Button) findViewById(R.id.BackToMainMenu);
        BackToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}