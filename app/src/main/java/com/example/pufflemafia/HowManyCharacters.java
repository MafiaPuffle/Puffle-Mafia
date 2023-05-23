package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class HowManyCharacters extends AppCompatActivity {
    // SeekBar IDs
    SeekBar HowManyForSpecificCharacterSeekbar;
    TextView HowManyForSpecificCharacterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_characters);

        //SeekBar
        HowManyForSpecificCharacterSeekbar = findViewById(R.id.HowManyForSpecificCharacterSeekbar);
        HowManyForSpecificCharacterText = findViewById(R.id.HowManyForSpecificCharacterText);

        HowManyForSpecificCharacterSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //NumberOfPlayersText.setVisibility(View.VISIBLE);
                HowManyForSpecificCharacterText.setText(progress+" Players");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Configure Buttons
        configureHowManyNextToChooseYourCharacters();
        configureHowManyBackToChooseYourCharacters();

    }



    //Character Select Screen Button
    private void configureHowManyNextToChooseYourCharacters() {
        Button HowManyNextToChooseYourCharacters = (Button) findViewById(R.id.HowManyNextToChooseYourCharacters);
        HowManyNextToChooseYourCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HowManyCharacters.this, CharacterSelectScreen.class));
            }
        });
    }


        //Back Button
    private void configureHowManyBackToChooseYourCharacters(){
        Button HowManyBackToChooseYourCharactersButton = (Button) findViewById(R.id.HowManyBackToChooseYourCharacters);
        HowManyBackToChooseYourCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}