package com.example.pufflemafia.app.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.playerAdapters.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class PlayerEditorScreen extends CustomAppCompatActivityWrapper {
    private Player player;
    private Vector<Player> allPlayersToEdit;

    private PlayerDayUIAdaptor adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_editor_ui);

        getDataFromIntent();

        ConfigureRecyclerViews();

        ConfigureReviveButton();
        ConfigureChangeRoleButton();
        ConfigureChangeNameButton();
        ConfigureUseAbilityButton();
        ConfigureUseAbilityButton();
        ConfigureEditEffectsButton();
        ConfigureBackButton();
    }

    private void getDataFromIntent(){

        Intent intent = getIntent();
        String name = intent.getStringExtra("playerName");
        Log.d("Custom_PlayerEditorScreen","player name is " + name);
        player = PlayerManager.getPlayerByName(name);
        Log.d("Custom_PlayerEditorScreen","player is " + player);

        allPlayersToEdit = new Vector<Player>();
        allPlayersToEdit.add(player);
    }

    private void ConfigureRecyclerViews(){
        adaptor = new PlayerDayUIAdaptor(allPlayersToEdit, this, PlayerManager.PlayerManagerListType.ALIVE);
        adaptor.setInteractable(false);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.CurrentPlayerRecyclerView);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void ConfigureReviveButton(){
        LinearLayout reviveButton = findViewById(R.id.ReviveButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
            }
        });
    }

    private void ConfigureChangeRoleButton(){
        LinearLayout reviveButton = findViewById(R.id.ChangeRoleButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
            }
        });
    }

    private void ConfigureChangeNameButton(){
        LinearLayout reviveButton = findViewById(R.id.ChangeNameButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
            }
        });
    }

    private void ConfigureUseAbilityButton(){
        LinearLayout reviveButton = findViewById(R.id.UseAbilityButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
            }
        });
    }

    private void ConfigureEditEffectsButton(){
        LinearLayout reviveButton = findViewById(R.id.EditEffectsButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
            }
        });
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