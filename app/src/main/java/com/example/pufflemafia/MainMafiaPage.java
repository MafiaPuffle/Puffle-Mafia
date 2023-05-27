package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class MainMafiaPage extends AppCompatActivity implements IListener<Boolean> {

    private RecyclerView allAliveRecycleView;
    private RecyclerView allDeadRecycleView;
    private RecyclerView.LayoutManager allAliveLayoutManager;
    private RecyclerView.LayoutManager allDeadLayoutManager;
    private PlayerDayUIAdaptor allAlivePlayerDayUIAdaptor;
    private PlayerDayUIAdaptor allDeadPlayerDayUIAdaptor;
    private Vector<Player> allAlivePlayers;
    private Vector<Player> allDeadPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        PlayerManager.onPlayerKillOrRevive.AddListener(this);
        PlayerManager.onPlayerDataUpdated.AddListener(this);
        GameManager.onStartDay.AddListener(this);

        //GameManager.StartNewGame(AppManager.gameSetup);

        allAlivePlayers = PlayerManager.getAllAlive();
        allDeadPlayers = PlayerManager.getAllDead();

        allAliveRecycleView = findViewById(R.id.AllAliveRecycleView);
        allDeadRecycleView = findViewById(R.id.AllDeadRecycleView);
        allAliveLayoutManager = new LinearLayoutManager(this);
        allDeadLayoutManager = new LinearLayoutManager(this);
        allAlivePlayerDayUIAdaptor = new PlayerDayUIAdaptor(allAlivePlayers, this, PlayerManager.PlayerMangerListType.ALIVE);
        allDeadPlayerDayUIAdaptor = new PlayerDayUIAdaptor(allDeadPlayers, this, PlayerManager.PlayerMangerListType.DEAD);


        allAliveRecycleView.setAdapter(allAlivePlayerDayUIAdaptor);
        allDeadRecycleView.setAdapter(allDeadPlayerDayUIAdaptor);
        allAliveRecycleView.setLayoutManager(allAliveLayoutManager);
        allDeadRecycleView.setLayoutManager(allDeadLayoutManager);

        PlayerManager.sortAllAliveByTokens();
        PlayerManager.sortAllDeadByTokens();

        //Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();
    }

    @Override
    protected void onDestroy() {
        PlayerManager.onPlayerKillOrRevive.RemoveListener(this);
        PlayerManager.onPlayerDataUpdated.RemoveListener(this);
        GameManager.onStartDay.AddListener(this);

        super.onDestroy();
    }

    private void Refresh(){
        allAlivePlayerDayUIAdaptor.notifyDataSetChanged();
        allDeadPlayerDayUIAdaptor.notifyDataSetChanged();
    }

    //Start The Night Button
    private void configureStartTheNightButton() {
        Button StartTheNightButton = (Button) findViewById(R.id.StartTheNightButton);
        StartTheNightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMafiaPage.this, NightActions.class));
            }
        });
    }

    //Back Button
    private void configureDayBacktoChooseYourCharactersButton(){
        Button DayBacktoChooseYourCharacters = (Button) findViewById(R.id.DayBacktoChooseYourCharactersButton);
        DayBacktoChooseYourCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void Response() {
        Refresh();
    }

    @Override
    public void Response(Boolean aBoolean) {
        Refresh();
    }
}