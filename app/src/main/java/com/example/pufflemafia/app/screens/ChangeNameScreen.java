package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ChangeNameScreen extends CustomAppCompatActivityWrapper {

    private Intent intent;
    private Player player;
    private String name;
    private int position;
//    private PlayerManager.PlayerMangerListType listType;
    private TextInputEditText editText;
    private TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name_screen);
        makeKeyboardHidealbe(findViewById(R.id.rootConstrainLayout));

        getDataFromIntent();
//        listType = (PlayerManager.PlayerMangerListType) intent.getSerializableExtra("ListType");
        editText = findViewById(R.id.NewPlayerName);
        textInputLayout = findViewById(R.id.NewNameTextImputLayout);

        //editText.setHint(name);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                editText.setHint(name);
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                SoundManager.playSfx("Click");
                setNewName();
                return true;
            }
        });

        textInputLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                editText.setHint(name);
            }
        });

        configureNextButton();
        configureBackToMainMenu();

        editText.requestFocus();
    }

    private void getDataFromIntent(){
        intent = getIntent();

        name = intent.getStringExtra("playerName");
        player = PlayerManager.getPlayerByName(name);
//        position = intent.getIntExtra("position",0);
    }

    private void configureNextButton(){
        Button BackToMainMafiaPageButton = (Button) findViewById(R.id.DoneChangingCharactersButton);
        BackToMainMafiaPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                setNewName();
            }
        });
    }

    private void configureBackToMainMenu(){
        Button BackToMainMafiaPageButton = (Button) findViewById(R.id.BackToMainMafiaPageButton);
        BackToMainMafiaPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }

    private void setNewName(){
        String newName = Objects.requireNonNull(editText.getText()).toString().trim();
        if(!newName.isEmpty()){
//            PlayerManager.EditPlayerName(listType, position, newName);
            player.setName(newName);
            PlayerManager.updatePlayerByName(name, player);
        }
        finish();
    }
}
