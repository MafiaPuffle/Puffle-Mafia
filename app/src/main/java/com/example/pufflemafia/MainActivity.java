package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.game.SoundManager;

public class MainActivity extends AppCompatActivity {

    private ToggleButton musicToggleButton;
    private ToggleButton sfxToggleButton;
    private boolean isMusicPlaying = true;
    private boolean isSFXPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting Up the App data
        AppManager.setup();

        // Setup SoundManager
        SoundManager.initialize(this);
        SoundManager.playSong("Mystery");

        configureStart();
        configureRoles();
        configureInstructions();
        configureQRCode();

        musicToggleButton = findViewById(R.id.toggleButton);
        musicToggleButton.setChecked(true);
        sfxToggleButton = findViewById(R.id.toggleButton2);
        sfxToggleButton.setChecked(true);

        musicToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMusicPlaying = isChecked;
                SoundManager.playSfx("Click");

                if(isMusicPlaying){
                    SoundManager.setMusicVolume(1.0f);
                }
                else{
                    SoundManager.muteMusic();
                }


                updateToggleButtonBackground();
            }
        });

        sfxToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isSFXPlaying = isChecked;
                SoundManager.playSfx("Click");

                if(isSFXPlaying){
                    SoundManager.setSfxVolume(1.0f);
                }
                else{
                    SoundManager.muteSFX();
                }


                updateToggleButtonBackground();
            }
        });


        updateToggleButtonBackground();
    }

    private void configureStart() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                startActivity(new Intent(MainActivity.this, Start.class));
            }
        });
    }

    private void configureRoles() {
        Button rolesButton = findViewById(R.id.RolesButton);
        rolesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                startActivity(new Intent(MainActivity.this, RolesScreen.class));
            }
        });
    }

    private void configureQRCode() {
        Button qrCodeButton = findViewById(R.id.QRCodeButton);
        qrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                startActivity(new Intent(MainActivity.this, QRCode.class));
            }
        });
    }

    private void configureInstructions() {
        Button instructionsButton = findViewById(R.id.InstructionsButton);
        instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                startActivity(new Intent(MainActivity.this, Instructions.class));
            }
        });
    }

    private void updateToggleButtonBackground() {
        int sfxBackgroundResId = isSFXPlaying ? R.drawable.green_rectangle : R.drawable.red_rectangle;
        int musicBackgroundResId = isMusicPlaying ? R.drawable.green_rectangle : R.drawable.red_rectangle;
        musicToggleButton.setBackgroundResource(musicBackgroundResId);
        sfxToggleButton.setBackgroundResource(sfxBackgroundResId);
    }

    @Override
    protected void onDestroy() {
        SoundManager.stopSongSuddenly("Mystery");
        super.onDestroy();
    }
}
