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

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

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
        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), buttonCount);



        Vector<Role> allRoles = DataManager.GetAllRoles();
        Log.d("CharacterSelectScreen", "allRoles size = " + allRoles.size());

        for (Role role: allRoles) {
            ImageButton roleImageButton = addImageButtonToGrid(allRolesCharacterBox, role.getImageResource());


            roleImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageButton chosenRoleButton = addImageButtonToGrid(ChosenCharacterBox, role.getImageResource());
                    buttonCount++; // Increment the counter
                    AppManager.gameSetup.chosenRoles.add(role);
                    AppManager.gameSetup.LogSummary();
                    updateCountTextView(AppManager.gameSetup.numberOfPlayers(), buttonCount); // Update the count in the TextView

                    chosenRoleButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ChosenCharacterBox.removeView(v); // Remove the clicked button from the layout
                            buttonCount--; // Decrement the counter
                            AppManager.gameSetup.chosenRoles.remove(role);
                            AppManager.gameSetup.LogSummary();
                            updateCountTextView(AppManager.gameSetup.numberOfPlayers(), buttonCount); // Update the count in the TextView
                        }
                    });
                }
            });
        }

        //Configure Buttons
        configureBackToStart();
        configureDoneChoosingCharactersButton();
    }

    private void updateCountTextView(int numberOfPlayers, int numberOfRoles){
        int difference = numberOfPlayers - numberOfRoles;

        if(difference > 0){
            countTextView.setText(String.valueOf(difference));
        }
        else if(difference < 0){
            countTextView.setText("To Many Roles");
        }
        else if (difference == 0 && AppManager.gameSetup.checkIfIsValid() == false){
            countTextView.setText("Need one Mafia");
        }
        else {
            countTextView.setText("Ready!");
        }
    }

    private ImageButton addImageButtonToGrid(GridLayout gridLayout, int drawableId) {
        Log.d("CharacterSelectScreen", "Adding image button to grid");
        ImageButton imageButton = new ImageButton(this);
        imageButton.setBackgroundResource(drawableId); // Set the image as the background
        imageButton.setImageResource(0); // Remove the image source

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        imageButton.setLayoutParams(params);
        gridLayout.addView(imageButton);

        return imageButton;
    }


    private void configureDoneChoosingCharactersButton() {
        Button DoneChoosingCharactersButton = (Button) findViewById(R.id.DoneChoosingCharactersButton);
        DoneChoosingCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppManager.gameSetup.checkIfIsValid()){
                    startActivity(new Intent(CharacterSelectScreen.this, MainMafiaPage.class));
                }
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