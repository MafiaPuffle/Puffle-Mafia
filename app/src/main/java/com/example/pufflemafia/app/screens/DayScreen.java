package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

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
import com.example.pufflemafia.app.game.GameManager;
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

    private Button helpButton;

    private IVoidEventListener refreshListener;
    private IEvent2Listener<Player, Result.KillType> playerKillTypeListener;
    private IEventListener<Player> playerReviveListener;

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

        playerReviveListener = new IEventListener<Player>() {
            @Override
            public void Response(Player player) {
                Refresh();
            }
        };

        configurePlayerManager();

        configureRecyclerViews();

        // TODO add sorting players by tokens
//        PlayerManager.sortAllAliveByTokens();
//        PlayerManager.sortAllDeadByTokens();

        // Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();
        configureTimerButton();

        // Initialize buttons
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            public void run() {
                configureHelpButton();
            }
        }, 10);
    }


    @Override
    protected void onDestroy() {
        PlayerManager.OnKillPlayer.RemoveListener(playerKillTypeListener);
        PlayerManager.OnRevivePlayer.RemoveListener(playerReviveListener);
        PlayerManager.OnPlayerDataUpdated.RemoveListener(refreshListener);
        PlayerManager.removeAllPlayersFromGame();
//        PlayerManager.onPlayerDataUpdated.RemoveListener(refreshListener);
//        GameManager.onStartDay.AddListener(refreshListener);
//        TimerManager.Clear();
        super.onDestroy();
    }

    private void Refresh() {
        allAlivePlayerDayUIAdaptor.notifyDataSetChanged();
        allDeadPlayerDayUIAdaptor.notifyDataSetChanged();

        RefreshTimerButtons();
    }

    private void RefreshTimerButtons() {
//        try {
//            if (TimerManager.isTimerGoing) {
//                timer.setVisibility(View.GONE);
//                timerButton.setVisibility(View.VISIBLE);
//            } else {
//                timer.setVisibility(View.VISIBLE);
//                timerButton.setVisibility(View.GONE);
//            }
//        } catch (Exception ignored) {
//
//        }
    }

    private void configurePlayerManager() {
        PlayerManager.OnKillPlayer.AddListener(playerKillTypeListener);
        PlayerManager.OnRevivePlayer.AddListener(playerReviveListener);
        PlayerManager.OnPlayerDataUpdated.AddListener(refreshListener);
//        PlayerManager.onPlayerDataUpdated.AddListener(refreshListener);
//        GameManager.onStartDay.AddListener(refreshListener);
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

    private void configureHelpButton() {
//        helpButton = findViewById(R.id.helpButton);
//        Vector<ViewToPointTo> allViewsToPointTo = new Vector<ViewToPointTo>();
//
//        allViewsToPointTo.add(new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_KILL_OR_REVIVE_BUTTON, "Tap to kill", ViewToPointTo.ViewClickType.NORMAL));
//        allViewsToPointTo.add(new ViewToPointTo(allDeadRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_KILL_OR_REVIVE_BUTTON, "Tap to revive", ViewToPointTo.ViewClickType.NORMAL));
//        allViewsToPointTo.add(new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_ROLE_BUTTON, "Tap to change role", ViewToPointTo.ViewClickType.NORMAL));
//        allViewsToPointTo.add(new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_NAME_AND_ROLE_LINEARLAYOUT, "Tap to change name", ViewToPointTo.ViewClickType.NORMAL));
//        allViewsToPointTo.add(new ViewToPointTo(allAliveRecycleView, 0, ViewToPointTo.ViewToPointToFlags.DAY_TOKEN_HOLDER, "Tap to change tokens", ViewToPointTo.ViewClickType.NORMAL));
//
//        HelpPromptManager.InitializeHelpPopups(this, this, helpButton, allViewsToPointTo);

    }

    private void configureTimerButton() {
        timerButton = findViewById(R.id.Timerbutton);
        timer = findViewById(R.id.Timer);

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
//                Intent intent = new Intent(MainMafiaPage.this, TimerScreen.class);
//                startActivity(intent);
            }
        });

        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
//                Intent intent = new Intent(MainMafiaPage.this, TimerScreen.class);
//                startActivity(intent);
            }
        });

//        TimerManager.onUpdate.AddListener(new IListener<Time>() {
//            @Override
//            public void Response() {
//
//            }
//
//            @Override
//            public void Response(Time time) {
//                timerButton.setText(time.minute + ":" + time.second);
//                RefreshTimerButtons();
//            }
//        });

        RefreshTimerButtons();
    }

    // Start The Night Button
    private void configureStartTheNightButton() {
        Button startTheNightButton = findViewById(R.id.StartTheNightButton);
        startTheNightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
//                TimerManager.Clear();
//                startActivity(new Intent(MainMafiaPage.this, NightActions.class));
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
//                TimerManager.Clear();
                finish();
            }
        });
    }

}
