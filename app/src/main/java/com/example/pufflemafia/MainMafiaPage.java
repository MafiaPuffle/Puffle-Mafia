package com.example.pufflemafia;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageButton;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.playerAdaptors.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.game.GameManager;
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
    private Button nextButton1;
    private Button nextButton2;

    private Button nextButton3;
    private Button nextButton4;

    private ImageView fingerImageView;
    private int currentItemIndex = 0;

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);
        SetupPopupWindow(R.layout.activity_main_mafia_page);

        ColorStateList blueColorStateList = ColorStateList.valueOf(Color.BLUE);
        ColorStateList greenColorStateList = ColorStateList.valueOf(Color.GREEN);
        ColorStateList redColorStateList = ColorStateList.valueOf(Color.RED);

        Button button = findViewById(R.id.helpButton);
        button.setBackgroundTintList(blueColorStateList);
//        Button button1 = findViewById(R.id.nextButton1);
//        button.setBackgroundTintList(greenColorStateList);
//        Button button2 = findViewById(R.id.nextButton2);
//        button.setBackgroundTintList(greenColorStateList);
//        Button button3 = findViewById(R.id.nextButton3);
//        button.setBackgroundTintList(greenColorStateList);
//        Button button4 = findViewById(R.id.nextButton4);
//        button.setBackgroundTintList(redColorStateList);

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

        // Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();

        // Initialize buttons
        helpButton = findViewById(R.id.helpButton);
        nextButton1 = findViewById(R.id.nextButton1);
        nextButton2 = findViewById(R.id.nextButton2);
        nextButton3 = findViewById(R.id.nextButton3);
        nextButton4 = findViewById(R.id.nextButton4);

        // Set initial visibility
        nextButton1.setVisibility(View.GONE);
        nextButton2.setVisibility(View.GONE);
        nextButton3.setVisibility(View.GONE);
        nextButton4.setVisibility(View.GONE);

        // Set onClickListener for helpButton
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartHelp();
//                helpButton.setVisibility(View.GONE);
//                nextButton1.setVisibility(View.VISIBLE);
//                fingerImageView.setVisibility(View.VISIBLE);
//                moveFingerToItem();
            }
        });

        // Set onClickListener for nextButton1
        nextButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton1.setVisibility(View.GONE);
                nextButton2.setVisibility(View.VISIBLE);
                currentItemIndex++;
                moveFingerToItem();
            }
        });

        // Set onClickListener for nextButton2
        nextButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton2.setVisibility(View.GONE);
                nextButton3.setVisibility(View.VISIBLE);
                currentItemIndex++;
                moveFingerToItem();
            }
        });

        nextButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton3.setVisibility(View.GONE);
                nextButton4.setVisibility(View.VISIBLE);
                currentItemIndex++;
                moveFingerToItem();
            }
        });

        nextButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton4.setVisibility(View.GONE);
                helpButton.setVisibility(View.VISIBLE);
                currentItemIndex = 0;
                fingerImageView.setVisibility(View.INVISIBLE);
            }
        });

        fingerImageView = new ImageView(this);
        fingerImageView.setImageResource(R.drawable.finger);
        fingerImageView.setVisibility(View.INVISIBLE);
        addContentView(fingerImageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }

    private void showFinger() {
        if (currentItemIndex == allAlivePlayerDayUIAdaptor.getItemCount()) {
            currentItemIndex = 0;
        }
        fingerImageView.setVisibility(View.VISIBLE);
        moveFingerToItem();
    }

    private void moveFingerToItem() {
        RecyclerView recyclerView = findViewById(R.id.AllAliveRecycleView);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        View targetItem = layoutManager.findViewByPosition(0);

        if (targetItem != null) {
            ViewGroup characterUIINBox = targetItem.findViewById(R.id.CharacterUIINBox);

            if (currentItemIndex == 0) {
                // Point to the first ImageButton
                ImageButton firstItemButton = characterUIINBox.findViewById(R.id.KillOrReviveButton);
                int[] targetLocation = new int[2];
                firstItemButton.getLocationOnScreen(targetLocation);

                int targetX = targetLocation[0] + firstItemButton.getWidth() / 2;
                int targetY = targetLocation[1] + firstItemButton.getHeight() / 2;

                fingerImageView.animate()
                        .x(targetX - fingerImageView.getWidth() / 2)
                        .y(targetY - fingerImageView.getHeight() / 2)
                        .setDuration(500)
                        .start();
            } else if (currentItemIndex == 1) {
                // Point to the second ImageButton
                ImageButton secondItemButton = characterUIINBox.findViewById(R.id.RoleUIButton);
                int[] targetLocation = new int[2];
                secondItemButton.getLocationOnScreen(targetLocation);

                int targetX = targetLocation[0] + secondItemButton.getWidth() / 2;
                int targetY = targetLocation[1] + secondItemButton.getHeight() / 2;

                fingerImageView.animate()
                        .x(targetX - fingerImageView.getWidth() / 2)
                        .y(targetY - fingerImageView.getHeight() / 2)
                        .setDuration(500)
                        .start();
            } else if (currentItemIndex == 2) {
                // Point to the second LinearLayout
                ViewGroup characterUITextsBox = characterUIINBox.findViewById(R.id.CharacterUITextsBox);
                int[] targetLocation = new int[2];
                characterUITextsBox.getLocationOnScreen(targetLocation);

                int targetX = targetLocation[0] + characterUITextsBox.getWidth() / 2;
                int targetY = targetLocation[1] + characterUITextsBox.getHeight() / 2;

                fingerImageView.animate()
                        .x(targetX - fingerImageView.getWidth() / 2)
                        .y(targetY - fingerImageView.getHeight() / 2)
                        .setDuration(500)
                        .start();
            } else if (currentItemIndex == 3) {
                // Point to the second LinearLayout
                ViewGroup tokenUIBox = characterUIINBox.findViewById(R.id.TokenUIBox);
                int[] targetLocation = new int[2];
                tokenUIBox.getLocationOnScreen(targetLocation);

                int targetX = targetLocation[0] + tokenUIBox.getWidth() / 2;
                int targetY = targetLocation[1] + tokenUIBox.getHeight() / 2;

                fingerImageView.animate()
                        .x(targetX - fingerImageView.getWidth() / 2)
                        .y(targetY - fingerImageView.getHeight() / 2)
                        .setDuration(500)
                        .start();
            }
        }
    }




    @Override
    protected void onDestroy() {
        PlayerManager.onPlayerKillOrRevive.RemoveListener(this);
        PlayerManager.onPlayerDataUpdated.RemoveListener(this);
        GameManager.onStartDay.AddListener(this);
        super.onDestroy();
    }

    private void Refresh() {
        allAlivePlayerDayUIAdaptor.notifyDataSetChanged();
        allDeadPlayerDayUIAdaptor.notifyDataSetChanged();
    }

    // Start The Night Button
    private void configureStartTheNightButton() {
        Button startTheNightButton = findViewById(R.id.StartTheNightButton);
        startTheNightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
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
