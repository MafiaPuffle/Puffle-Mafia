package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.effectAddaptors.EffectUIAdaptor;
import com.example.pufflemafia.adaptors.tokenAdapters.PossibleTokenUIAdaptor;
import com.example.pufflemafia.adaptors.tokenAdapters.SelectedTokenUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class AddTokenScreen extends CustomAppCompatActivityWrapper implements IListener<Boolean> {

    private Intent intent;
    private int playerPosition;
    private Vector<Token> allTokens;
    private Vector<Token> selectedTokens;
    private Vector<String> allEffects;
    private PlayerManager.PlayerMangerListType listType;
    private RecyclerView allTokensRecyclerView;
    private RecyclerView selectedTokensRecyclerView;
    private RecyclerView effectsRecyclerView;
    private RecyclerView.LayoutManager allTokensLayoutManger;
    private RecyclerView.LayoutManager selectedTokensLayoutManger;
    private RecyclerView.LayoutManager effectsLayoutManager;
    private PossibleTokenUIAdaptor allTokensAdaptor;
    private SelectedTokenUIAdaptor selectedTokensAdaptor;
    private EffectUIAdaptor effectUIAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token_screen);

        selectedTokens = new Vector<Token>();
        allTokens = DataManager.GetAllTokens();
        allEffects = new Vector<String>();

        getDataFromIntent();
        refreshSelectedTokenData();
        refreshEffects();
        setupRecyclerView();

        configureNextButton();
        configureBackToMainMenu();

        PlayerManager.onPlayerDataUpdated.AddListener(this);
    }

    @Override
    protected void onDestroy(){
        PlayerManager.onPlayerDataUpdated.RemoveListener(this);
        super.onDestroy();
    }

    private void getDataFromIntent(){
        intent = getIntent();
        playerPosition = intent.getIntExtra("position",0);
        listType = (PlayerManager.PlayerMangerListType) intent.getSerializableExtra("ListType");
    }
    private void refreshSelectedTokenData(){
        selectedTokens.clear();
        if(listType == PlayerManager.PlayerMangerListType.ALIVE){
            for (int i = 0; i < PlayerManager.getAlivePlayerAt(playerPosition).getAllTokensOnPlayer().size(); i++) {
                selectedTokens.add(PlayerManager.getAlivePlayerAt(playerPosition).getTokenOnPlayer(i));
            }
        }
        else {
            for (int i = 0; i < PlayerManager.getDeadPlayerAt(playerPosition).getAllTokensOnPlayer().size(); i++) {
                selectedTokens.add(PlayerManager.getDeadPlayerAt(playerPosition).getTokenOnPlayer(i));
            }
        }
    }
    private void setupRecyclerView(){
        allTokensRecyclerView = findViewById(R.id.AllTokensRecyclerView);
        selectedTokensRecyclerView = findViewById(R.id.SelectedTokensRecyclerView);
        effectsRecyclerView = findViewById(R.id.TokenEffectsRecyclerView);

        allTokensLayoutManger = new GridLayoutManager(this, 5);
        selectedTokensLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        effectsLayoutManager = new LinearLayoutManager(this);

        allTokensAdaptor = new PossibleTokenUIAdaptor(allTokens, this, listType, playerPosition);
        selectedTokensAdaptor = new SelectedTokenUIAdaptor(selectedTokens, this, listType, playerPosition);
        effectUIAdaptor = new EffectUIAdaptor(allEffects, this);

        allTokensRecyclerView.setLayoutManager(allTokensLayoutManger);
        selectedTokensRecyclerView.setLayoutManager(selectedTokensLayoutManger);
        effectsRecyclerView.setLayoutManager(effectsLayoutManager);

        allTokensRecyclerView.setAdapter(allTokensAdaptor);
        selectedTokensRecyclerView.setAdapter(selectedTokensAdaptor);
        effectsRecyclerView.setAdapter(effectUIAdaptor);
    }

    private void refreshEffects(){
        allEffects.clear();

        for (Token token: selectedTokens) {
            allEffects.add(token.getDescription());
        }
    }

    private void configureNextButton(){
        Button NextButton = (Button) findViewById(R.id.DoneChangingCharactersButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }

    private void configureBackToMainMenu(){
        Button BackToMainMafiaPageButton = (Button) findViewById(R.id.BackToMainMafiaPageButton);
        BackToMainMafiaPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }

    @Override
    public void Response() {
        refreshSelectedTokenData();
        refreshEffects();
        selectedTokensAdaptor.notifyDataSetChanged();
        effectUIAdaptor.notifyDataSetChanged();
    }

    @Override
    public void Response(Boolean aBoolean) {

    }
}