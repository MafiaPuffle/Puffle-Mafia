package com.example.pufflemafia.app.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.game.SoundManager;

public class QRCodeScreen extends CustomAppCompatActivityWrapper {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        configureBackButton();
    }

    private void configureBackButton() {
        Button startButton = findViewById(R.id.BackButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }
}
