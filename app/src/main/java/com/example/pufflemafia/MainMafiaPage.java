package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.PlayerDayUIAdaptor;
import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class MainMafiaPage extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PlayerDayUIAdaptor playerDayUIAdaptor;
    private Vector<Player> allAlivePlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_mafia_page);

        GameManager.StartNewGame(AppManager.gameSetup);

        allAlivePlayers = PlayerManager.allAlive;

        mRecyclerView = findViewById(R.id.TestRecycleView);
        mLayoutManager = new LinearLayoutManager(this);
        playerDayUIAdaptor = new PlayerDayUIAdaptor(allAlivePlayers);


        mRecyclerView.setAdapter(playerDayUIAdaptor);
        mRecyclerView.setLayoutManager(mLayoutManager);


        //Configure Button
        configureDayBacktoChooseYourCharactersButton();
        configureStartTheNightButton();
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
}