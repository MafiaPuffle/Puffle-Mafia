package com.example.pufflemafia;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.playerAdaptors.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.ViewToPointTo;
import com.example.pufflemafia.app.data.Time;
import com.example.pufflemafia.app.data.TimerManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.HelpPromptManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class MainMafiaPage extends CustomAppCompatActivityWrapper implements IListener<Boolean> {

    private RecyclerView allAliveRecycleView;
    private RecyclerView allDeadRecycleView;
    private RecyclerView.LayoutManager allAliveLayoutManager;
    private RecyclerView.LayoutManager allDeadLayoutManager;
    private PlayerDayUIAdaptor allAlivePlayerDayUIAdaptor;
    private PlayerDayUIAdaptor allDeadPlayerDayUIAdaptor;
    private Vector<Player> allAlivePlayers;
    private Vector<Player> allDeadPlayers;

    private Button helpButton;

    Button timerButton;
    ImageButton timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        ColorStateList blueColorStateList = ColorStateList.valueOf(Color.BLUE);
        ColorStateList greenColorStateList = ColorStateList.valueOf(Color.GREEN);
        ColorStateList redColorStateList = ColorStateList.valueOf(Color.RED);

        Button button = findViewById(R.id.helpButton);
        button.setBackgroundTintList(blueColorStateList);

        configurePlayerManager();

        configureRecyclerViews();

        PlayerManager.sortAllAliveByTokens();
        PlayerManager.sortAllDeadByTokens();

        // Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();
        configureTimerButton();

        // Initialize buttons
        Handler h =new Handler() ;
        h.postDelayed(new Runnable() {
            public void run() {
                configureHelpButton();
            }
        },10);
    }




    @Override
    protected void onDestroy() {
        PlayerManager.onPlayerKillOrRevive.RemoveListener(this);
        PlayerManager.onPlayerDataUpdated.RemoveListener(this);
        GameManager.onStartDay.AddListener(this);
        TimerManager.Clear();
        super.onDestroy();
    }

    private void Refresh() {
        allAlivePlayerDayUIAdaptor.notifyDataSetChanged();
        allDeadPlayerDayUIAdaptor.notifyDataSetChanged();

        RefreshTimerButtons();
    }

    private void RefreshTimerButtons(){
        try{
            if(TimerManager.isTimerGoing){
                timer.setVisibility(View.GONE);
                timerButton.setVisibility(View.VISIBLE);
            }
            else{
                timer.setVisibility(View.VISIBLE);
                timerButton.setVisibility(View.GONE);
            }
        }catch (Exception ignored){

        }
    }

    private void configurePlayerManager(){
        PlayerManager.onPlayerKillOrRevive.AddListener(this);
        PlayerManager.onPlayerDataUpdated.AddListener(this);
        GameManager.onStartDay.AddListener(this);
    }

    private void configureRecyclerViews(){
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
    }

    private void configureHelpButton(){
        helpButton = findViewById(R.id.helpButton);
        Vector<ViewToPointTo> allViewsToPointTo = new Vector<ViewToPointTo>();

        allViewsToPointTo.add( new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_KILL_OR_REVIVE_BUTTON, "Tap to kill", ViewToPointTo.ViewClickType.NORMAL));
        allViewsToPointTo.add( new ViewToPointTo(allDeadRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_KILL_OR_REVIVE_BUTTON, "Tap to revive", ViewToPointTo.ViewClickType.NORMAL));
        allViewsToPointTo.add( new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_ROLE_BUTTON, "Tap to change role", ViewToPointTo.ViewClickType.NORMAL));
        allViewsToPointTo.add( new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_NAME_AND_ROLE_LINEARLAYOUT, "Tap to change name", ViewToPointTo.ViewClickType.NORMAL));
        allViewsToPointTo.add( new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_TOKEN_HOLDER, "Tap to change tokens", ViewToPointTo.ViewClickType.NORMAL));

        HelpPromptManager.InitializeHelpPopups(this, this, helpButton, allViewsToPointTo);

    }

    private void configureTimerButton(){
        timerButton = findViewById(R.id.Timerbutton);
        timer = findViewById(R.id.Timer);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(MainMafiaPage.this, TimerScreen.class);
                startActivity(intent);
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(MainMafiaPage.this, TimerScreen.class);
                startActivity(intent);
            }
        });

        TimerManager.onUpdate.AddListener(new IListener<Time>() {
            @Override
            public void Response() {

            }

            @Override
            public void Response(Time time) {
                timerButton.setText(time.minute + ":" + time.second);
                RefreshTimerButtons();
            }
        });

        RefreshTimerButtons();
    }

    // Start The Night Button
    private void configureStartTheNightButton() {
        Button startTheNightButton = findViewById(R.id.StartTheNightButton);
        startTheNightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                TimerManager.Clear();
                startActivity(new Intent(MainMafiaPage.this, NightActions.class));
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
                TimerManager.Clear();
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
