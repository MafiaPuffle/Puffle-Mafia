package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.playerAdapters.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.events.IVoidEventListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Collections;
import java.util.UUID;
import java.util.Vector;

public class OptionsScreen extends CustomAppCompatActivityWrapper {

    private Boolean isMusicPlaying;
    private Boolean isSFXPlaying;

    private ToggleButton musicToggleButton;
    private ToggleButton sfxToggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);

        OptionsScreen instance = this;

        configureButtons();
    }

    private void configureButtons() {
        LinearLayout timerButton = findViewById(R.id.TimerButton);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(instance, TimerScreen.class));
            }
        });

        LinearLayout restartGameButton = findViewById(R.id.RestartGameButton);
        restartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Vector<Player> players = PlayerManager.getAllAlivePlayers();
                players.addAll(PlayerManager.getAllDeadPlayers());

                Vector<String> names = new Vector<String>();
                Vector<Role> roles = new Vector<Role>();

                for (Player player: players) {
                    names.add(player.getName());
                    roles.add(player.getRole());
                }

//                Collections.shuffle(names);
                Collections.shuffle(roles);

                PlayerManager.removeAllPlayersFromGame();

                for (int i = 0; i < names.size(); i++) {
                    Player player = new Player(names.get(i), roles.get(i));
                    PlayerManager.addPlayerToGame(player);
                }

                finish();
            }
        });

        LinearLayout addPlayerButton = findViewById(R.id.AddPlayerButton);
        addPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfPlayers = PlayerManager.getNumberOfPlayers() + 1;
                Player player = new Player("Player " + numberOfPlayers, DataManager.getRandomRole());

                PlayerManager.addPlayerToGame(player);

                finish();
            }
        });

        LinearLayout allRolesButton = findViewById(R.id.AllRolesButton);
        allRolesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(instance, ShowAllRolesScreen.class));
            }
        });

        LinearLayout qrCodeButton = findViewById(R.id.QRCodeButton);
        qrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(instance, QRCodeScreen.class));
            }
        });

        configureToggleButtons();

        Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void configureToggleButtons(){
        isMusicPlaying = !SoundManager.isMusicMuted();
        isSFXPlaying = !SoundManager.isSFXMuted();

        musicToggleButton = findViewById(R.id.toggleButton);
        musicToggleButton.setChecked(isMusicPlaying);
        sfxToggleButton = findViewById(R.id.toggleButton2);
        sfxToggleButton.setChecked(isSFXPlaying);

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

    private void updateToggleButtonBackground() {
        int sfxBackgroundResId = isSFXPlaying ? R.drawable.green_rectangle : R.drawable.red_rectangle;
        int musicBackgroundResId = isMusicPlaying ? R.drawable.green_rectangle : R.drawable.red_rectangle;
        musicToggleButton.setBackgroundResource(musicBackgroundResId);
        sfxToggleButton.setBackgroundResource(sfxBackgroundResId);
    }

}