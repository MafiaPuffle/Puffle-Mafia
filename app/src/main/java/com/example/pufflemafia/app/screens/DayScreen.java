package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.RecyclerRowMoverCallBack;
import com.example.pufflemafia.app.adapters.playerAdapters.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.data.actions.result.Result;
import com.example.pufflemafia.app.events.IEvent2Listener;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.events.IVoidEventListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class DayScreen extends CustomAppCompatActivityWrapper {

    private RecyclerView allAliveRecycleView;
    private RecyclerView allDeadRecycleView;
    private RecyclerView.LayoutManager allAliveLayoutManager;
    private RecyclerView.LayoutManager allDeadLayoutManager;
    private PlayerDayUIAdaptor allAlivePlayerDayUIAdaptor;
    private PlayerDayUIAdaptor allDeadPlayerDayUIAdaptor;
    private Vector<Player> allAlivePlayers;
    private Vector<Player> allDeadPlayers;

    private IVoidEventListener refreshListener;
    private IEvent2Listener<Player, Result.KillType> playerKillTypeListener;
    private IEventListener<Player> playerListener;

    Button optionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        refreshListener = new IVoidEventListener() {
            @Override
            public void Response() {
                Refresh();
            }
        };

        playerKillTypeListener = new IEvent2Listener<Player, Result.KillType>() {
            @Override
            public void Response(Player player, Result.KillType killType) {
                Refresh();
            }
        };

        playerListener = new IEventListener<Player>() {
            @Override
            public void Response(Player player) {
                Refresh();
            }
        };

        configurePlayerManager();

        configureRecyclerViews();

        // TODO add sorting players by tokens

        // Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();
        configureOptionsButton();
    }


    @Override
    protected void onDestroy() {
        PlayerManager.OnKillPlayer.RemoveListener(playerKillTypeListener);
        PlayerManager.OnRevivePlayer.RemoveListener(playerListener);
        PlayerManager.OnAddPlayer.RemoveListener(playerListener);
        PlayerManager.OnPlayerDataUpdated.RemoveListener(refreshListener);
        PlayerManager.OnRemoveAllPlayers.RemoveListener(refreshListener);
        PlayerManager.removeAllPlayersFromGame();
        super.onDestroy();
    }

    private void Refresh() {
        allAlivePlayerDayUIAdaptor.notifyDataSetChanged();
        allDeadPlayerDayUIAdaptor.notifyDataSetChanged();
    }

    private void configurePlayerManager() {
        PlayerManager.OnKillPlayer.AddListener(playerKillTypeListener);
        PlayerManager.OnRevivePlayer.AddListener(playerListener);
        PlayerManager.OnAddPlayer.AddListener(playerListener);
        PlayerManager.OnPlayerDataUpdated.AddListener(refreshListener);
        PlayerManager.OnRemoveAllPlayers.AddListener(refreshListener);
    }

    private void configureRecyclerViews() {

        allAlivePlayers = PlayerManager.getAllAlivePlayers();
        allDeadPlayers = PlayerManager.getAllDeadPlayers();

        allAliveRecycleView = findViewById(R.id.AllAliveRecycleView);
        allDeadRecycleView = findViewById(R.id.AllDeadRecycleView);
        allAliveLayoutManager = new LinearLayoutManager(this);
        allDeadLayoutManager = new LinearLayoutManager(this);
        allAlivePlayerDayUIAdaptor = new PlayerDayUIAdaptor(allAlivePlayers, this, PlayerManager.PlayerManagerListType.ALIVE);
        allDeadPlayerDayUIAdaptor = new PlayerDayUIAdaptor(allDeadPlayers, this, PlayerManager.PlayerManagerListType.DEAD);

        ItemTouchHelper.Callback callback = new RecyclerRowMoverCallBack(allAlivePlayerDayUIAdaptor);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(allAliveRecycleView);

        allAliveRecycleView.setAdapter(allAlivePlayerDayUIAdaptor);
        allDeadRecycleView.setAdapter(allDeadPlayerDayUIAdaptor);
        allAliveRecycleView.setLayoutManager(allAliveLayoutManager);
        allDeadRecycleView.setLayoutManager(allDeadLayoutManager);
    }

    private void configureOptionsButton() {
        optionsButton = findViewById(R.id.OptionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(instance, OptionsScreen.class));
            }
        });
    }

    // Start The Night Button
    private void configureStartTheNightButton() {
        Button startTheNightButton = findViewById(R.id.StartTheNightButton);
        startTheNightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
            }
        });
    }

    // Back Button
    private void configureDayBacktoChooseYourCharactersButton() {
        Button dayBacktoChooseYourCharacters = findViewById(R.id.BackButton);
        dayBacktoChooseYourCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }

}
