package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;

public class CharacterSelectScreen extends AppCompatActivity {

    private int buttonCount = 0;

    private TextView countTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select_screen);

        Log.i("CharacterSelectScreen", "Starting CharacterSelectScreen");

        GridLayout allRolesCharacterBox = findViewById(R.id.CharacterBox);

        GridLayout ChosenCharacterBox = findViewById(R.id.ChosenCharacterBox);

        countTextView = findViewById(R.id.ChosenCharacterCountText);

        for(int i = 0; i < allRolesCharacterBox.getChildCount(); i++) {
            ImageButton child = (ImageButton) allRolesCharacterBox.getChildAt(i);
            //String roleAsString = child.getContentDescription().toString();
            //Role role = DataManager.GetRole(roleAsString);

            // do stuff with child view
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addImageToGrid(ChosenCharacterBox, R.drawable.alien_puffle);
                }
            });



            //Log.i("CharacterSelectScreen", roleAsString + " should be setup");
        }

        //Configure Buttons
        configureBackToStart();
        configureDoneChoosingCharactersButton();
        //configureNonActiveCharacter();
        //ImageButton ChosenCharacter = findViewById(R.id.NonActiveCharacter);
    }

    private void addImageToGrid(GridLayout chosenCharacterBox, int drawableId) {
        ImageButton imageButton = new ImageButton(this);
        imageButton.setBackgroundResource(drawableId); // Set the image as the background
        imageButton.setImageResource(0); // Remove the image source

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        imageButton.setLayoutParams(params);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosenCharacterBox.removeView(v); // Remove the clicked button from the layout
                buttonCount--; // Decrement the counter
                countTextView.setText(String.valueOf(buttonCount)); // Update the count in the TextView
            }
        });

        chosenCharacterBox.addView(imageButton);
        buttonCount++; // Increment the counter
        countTextView.setText(String.valueOf(buttonCount)); // Update the count in the TextView
    }

    private void configureNonActiveCharacter() {
        ImageButton NonActiveCharacterButton = (ImageButton) findViewById(R.id.NonActiveCharacter);
        NonActiveCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharacterSelectScreen.this, HowManyCharacters.class));
            }
        });
    }


    private void configureDoneChoosingCharactersButton() {
        Button DoneChoosingCharactersButton = (Button) findViewById(R.id.DoneChoosingCharactersButton);
        DoneChoosingCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CharacterSelectScreen.this, MainMafiaPage.class));
            }
        });
    }

    //Back Button
    private void configureBackToStart(){
        Button BackToStartButton = (Button) findViewById(R.id.BackToStartButton);
        BackToStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}