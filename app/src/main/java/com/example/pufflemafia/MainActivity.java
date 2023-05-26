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
        BackgroundMusicManager.start(this, R.raw.mystery);

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

//    @Override
//    protected void onResume() {
//        super.onResume();
//        BackgroundMusicManager.stop();}
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        BackgroundMusicManager.stop();}
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        BackgroundMusicManager.stop();}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BackgroundMusicManager.stop();
    }


}