package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.content.res.AppCompatResources;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.ViewToPointTo;
import com.example.pufflemafia.app.data.TimerManager;
import com.example.pufflemafia.app.game.HelpPromptManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class MainActivity extends CustomAppCompatActivityWrapper {

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
        configureBuildNumber();

        musicToggleButton = findViewById(R.id.toggleButton);
        musicToggleButton.setChecked(true);
        sfxToggleButton = findViewById(R.id.toggleButton2);
        sfxToggleButton.setChecked(true);

        musicToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isMusicPlaying = isChecked;
                SoundManager.playSfx("Click");

                if (isMusicPlaying) {
                    SoundManager.setMusicVolume(1.0f);
                } else {
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

                if (isSFXPlaying) {
                    SoundManager.setSfxVolume(1.0f);
                } else {
                    SoundManager.muteSFX();
                }


                updateToggleButtonBackground();
            }
        });


        updateToggleButtonBackground();
    }

    private void configureBuildNumber(){
        TextView buildNumberTextView = findViewById(R.id.BuildNumber);
        buildNumberTextView.setText(AppManager.buildVersion);
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

    private void makePopup(Button button) {
        PopupWindow popupWindow = new PopupWindow(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View viewToAdd = layoutInflater.inflate(R.layout.help_button_ui, findViewById(R.id.help_button_ui));

        popupWindow.setContentView(viewToAdd);
        popupWindow.setBackgroundDrawable(AppCompatResources.getDrawable(this,R.drawable.alien_puffle));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.showAtLocation(findViewById(R.id.PuffleMafia), Gravity.CENTER, 0, 0);
                popupWindow.update(0, 0, 500, 200);
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
