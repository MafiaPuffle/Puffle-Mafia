package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NightActions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_actions);

        //Configure Buttons
        configureBacktoLastActionButton();
    }

    //Back Button
    private void configureBacktoLastActionButton(){
        Button BacktoLastActionButton = (Button) findViewById(R.id.BacktoLastActionButton);
        BacktoLastActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}