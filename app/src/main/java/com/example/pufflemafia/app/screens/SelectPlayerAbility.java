package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.ActionUIAdaptor;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.UUID;
import java.util.Vector;

public class SelectPlayerAbility extends CustomAppCompatActivityWrapper {
    private UUID ID;
    private Player player;

    private Vector<Action> playerAbilities;

    private ActionUIAdaptor adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player_abilites);

        getDataFromIntent();

        ConfigureRecyclerViews();

        ConfigureBackButton();
    }

    private void getDataFromIntent(){

        Intent intent = getIntent();
        ID = UUID.fromString(intent.getStringExtra("playerID"));
        player = PlayerManager.getPlayerByID(ID);

        playerAbilities = player.getRole().getActions();
    }

    private void ConfigureRecyclerViews(){
        adaptor = new ActionUIAdaptor(playerAbilities, this);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.PlayerAbilities);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void ConfigureBackButton(){
        Button reviveButton = findViewById(R.id.BackButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }
}
