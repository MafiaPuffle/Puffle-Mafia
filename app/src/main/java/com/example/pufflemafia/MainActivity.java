package com.example.pufflemafia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.game.GameManager;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;
    private boolean isMusicPlaying = true;
    private MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BackgroundMusicManager.start(this, R.raw.mystery);

        // Setting Up the App data
        AppManager.setup();

        clickSound = MediaPlayer.create(this, R.raw.click_sound);
        configureStart();
        configureRoles();
        configureInstructions();
        configureQRCode();

        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setChecked(true);
        updateToggleButtonBackground();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMusicPlaying = isChecked;

                if (isMusicPlaying) {
                    BackgroundMusicManager.start(MainActivity.this, R.raw.mystery);
                } else {
                    BackgroundMusicManager.stop();
                }

                updateToggleButtonBackground();
            }
        });
    }

    private void configureStart() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                startActivity(new Intent(MainActivity.this, Start.class));
            }
        });
    }

    private void configureRoles() {
        Button rolesButton = findViewById(R.id.RolesButton);
        rolesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                startActivity(new Intent(MainActivity.this, RolesScreen.class));
            }
        });
    }

    private void configureQRCode() {
        Button qrCodeButton = findViewById(R.id.QRCodeButton);
        qrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                startActivity(new Intent(MainActivity.this, QRCode.class));
            }
        });
    }

    private void configureInstructions() {
        Button instructionsButton = findViewById(R.id.InstructionsButton);
        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                startActivity(new Intent(MainActivity.this, Instructions.class));
            }
        });
    }

    private void updateToggleButtonBackground() {
        int backgroundResId = isMusicPlaying ? R.drawable.green_rectangle : R.drawable.red_rectangle;
        toggleButton.setBackgroundResource(backgroundResId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BackgroundMusicManager.stop();
        clickSound.release();
    }
}
