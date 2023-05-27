package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pufflemafia.adaptors.PlayerNightUIAdaptor;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class NightActions extends AppCompatActivity {

    private Vector<Player> allAlivePlayers;
    private Role currentActiveRoleAtNight;

    private TextView activeRoleTextView;
    private TextView nightActionTitle;
    private ImageButton activeRoleImageButton;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PlayerNightUIAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_actions);

        allAlivePlayers = PlayerManager.getAllAlive();

        activeRoleTextView = findViewById(R.id.ActiveRoleUITextView);
        nightActionTitle = findViewById(R.id.NightActionTitleText);
        activeRoleImageButton = findViewById(R.id.ActiveRoleUIImage);

        recyclerView = findViewById(R.id.AllAlivePlayersForThisNightRecycleView);
        layoutManager = new LinearLayoutManager(this);
        adaptor = new PlayerNightUIAdaptor(allAlivePlayers, this);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(layoutManager);

        PlayerManager.sortAllAliveByTokens();

        GameManager.StartNight();

        Refresh();

        //Configure Buttons
        configureToNextActionButton();
        configureBacktoLastActionButton();
    }

    private void Refresh(){
        allAlivePlayers = PlayerManager.getAllAlive();
        currentActiveRoleAtNight = GameManager.getCurrentRoleActiveAtNight();
        if(currentActiveRoleAtNight == null){
            PlayerManager.sortAllAliveByTokens();
            finish();
            return;
        }

        activeRoleImageButton.setBackgroundResource(currentActiveRoleAtNight.getImageResource());
        activeRoleImageButton.setImageResource(0);

        activeRoleTextView.setText(currentActiveRoleAtNight.getName());

        nightActionTitle.setText("WHO WOULD YOU LIKE TO " + currentActiveRoleAtNight.getPower().getPrompt());
    }

    //Next Button
    private void configureToNextActionButton(){
        Button ToNextActionButton = (Button) findViewById(R.id.ToNextActionButton);
        ToNextActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.GoToNextEventAtNight();
                Refresh();
            }
        });
    }

    //Back Button
    private void configureBacktoLastActionButton(){
        Button BacktoLastActionButton = (Button) findViewById(R.id.BacktoLastActionButton);
        BacktoLastActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameManager.GoToPreviousEventAtNight();
                Refresh();
            }
        });
    }

}