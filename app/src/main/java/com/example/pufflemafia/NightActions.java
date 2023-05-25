package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class NightActions extends AppCompatActivity {

    private Vector<Player> allAlivePlayers;
    private Role currentActiveRoleAtNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_actions);

        Refresh();

        //Configure Buttons
        configureBacktoLastActionButton();
    }

    private void Refresh(){
        allAlivePlayers = PlayerManager.allAlive;
        currentActiveRoleAtNight = GameManager.getCurrentRoleActiveAtNight();
        //TODO: Set up night actions screen
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