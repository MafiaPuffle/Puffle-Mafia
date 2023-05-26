package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class ChangeCharacterScreen extends AppCompatActivity {

    private Intent intent;

    private PlayerManager.PlayerMangerListType listType;
    private Integer position;
    private Vector<Role> allRoles;
    private Role newRole;
    private GridLayout gridLayout;
    private ImageView currentRoleImageView;
    private ImageView newRoleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_character_screen);

        allRoles = DataManager.GetAllRoles();
        intent = getIntent();

        newRole = new Role();

        listType = PlayerManager.PlayerMangerListType.ALIVE;

        gridLayout = findViewById(R.id.EditAllCharacterBox);

        currentRoleImageView = findViewById(R.id.CurrentRole);
        newRoleImageView = findViewById(R.id.NewRole);

        currentRoleImageView.setBackgroundResource(intent.getIntExtra("currentRoleImageResource", 0));
        currentRoleImageView.setImageResource(0);

        newRoleImageView.setBackgroundResource(intent.getIntExtra("currentRoleImageResource", 0));
        newRoleImageView.setImageResource(0);

        position = intent.getIntExtra("position",0);

        for(Role role: allRoles){
            ImageButton imageButton = addImageButtonToGrid(gridLayout, role.getImageResource());
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newRole = role;
                    newRoleImageView.setBackgroundResource(newRole.getImageResource());
                    newRoleImageView.setImageResource(0);
                }
            });
        }

        configureNextButton();
        configureBackToMainMenu();

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

    private void configureNextButton(){
        Button NextButton = (Button) findViewById(R.id.DoneChangingCharactersButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newRole.getName() != "PuffleName")PlayerManager.EditPlayerRole(listType, position, newRole);
                PlayerManager.LogSummary();
                finish();
            }
        });
    }

    private void configureBackToMainMenu(){
        Button BackToMainMafiaPageButton = (Button) findViewById(R.id.BackToMainMafiaPageButton);
        BackToMainMafiaPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




}