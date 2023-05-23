package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.game.GameManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting Up the App data
        AppManager.setup();


        Button Roles = findViewById(R.id.RolesButton);
        Roles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RolesScreen.class);

                startActivity(intent);
            }
        });

        configureStart();
        configureRoles();
    }


//Start Button
        private void configureStart() {
        Button StartButton = (Button) findViewById(R.id.StartButton);
        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Start.class));
                }
          });
        }

        private void configureRoles() {
        Button RolesButton = (Button) findViewById(R.id.RolesButton);
        RolesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RolesScreen.class));
                }
            });
        }

}