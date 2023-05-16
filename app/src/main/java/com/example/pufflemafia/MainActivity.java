package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare button variables
        Button Start = findViewById(R.id.StartButton);
        Button Roles = findViewById(R.id.RolesButton);

        // Set onClickListener's for each button
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Start.class);
                startActivity(intent);
            }
        });
        Roles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Roles.class);
                startActivity(intent);
            }
        });

    }
}