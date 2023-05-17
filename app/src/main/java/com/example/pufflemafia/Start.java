package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {
 // puffle are awesome and love donuts
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button ChooseYourCharacters = findViewById(R.id.ChooseCharactersButton);
        ChooseYourCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this, CharacterSelectScreen.class);
                startActivity(intent);
            }
        });

    }
}