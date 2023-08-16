package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.util.Pair;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.ButtonAdaptor;
import com.example.pufflemafia.app.adapters.playerAdapters.PlayerNightUIAdaptor;
import com.example.pufflemafia.app.data.prompts.OptionsPrompt;
import com.example.pufflemafia.app.data.prompts.PlayerPrompt;
import com.example.pufflemafia.app.data.prompts.Prompt;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.events.IVoidEventListener;
import com.example.pufflemafia.app.game.ActionLogManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.ResolvingManager;

import java.util.Vector;

public class PromptScreen extends CustomAppCompatActivityWrapper {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private IEventListener<Prompt> refreshListener;
    private IVoidEventListener exitListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_actions);

        ConfigureRecyclerView();

        ConfigureListeners();

        ConfigureBackButton();
        ConfigureNextButton();

        PromptsManager.GetNextPrompt();
    }

    private void ConfigureRecyclerView(){
        recyclerView = findViewById(R.id.AllAlivePlayersForThisNightRecycleView);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
    }

    public void Refresh(Prompt prompt){
        Log.d("CustomPromptScreen", "Prompts have been updated with the following prompt: " + prompt);

        DisplayCurrentRole();
        DisplayPrompt(prompt);

        // Based on the current prompt type we update the adaptor and display

        if(prompt instanceof PlayerPrompt){
            DisplayPlayerPromptData((PlayerPrompt) prompt);
        } else if (prompt instanceof OptionsPrompt) {
            DisplayOptionsPromptData((OptionsPrompt) prompt);
        }
    }

    private void DisplayCurrentRole(){
        ImageButton roleImage = findViewById(R.id.ActiveRoleUIImage);
        roleImage.setImageResource(0);
        roleImage.setBackgroundResource(PromptsManager.currentPlayer.getRole().getImageResource());

        TextView roleTitle = findViewById(R.id.ActiveRoleUITextView);
        roleTitle.setText(PromptsManager.currentPlayer.getRole().getName());

        Log.d("CustomPromptScreen", "Prompt screen role tile has been updated with the following: " + PromptsManager.currentPlayer.getRole().getName());
    }

    private void DisplayPrompt(Prompt prompt){
        TextView promptTextView = findViewById(R.id.NightActionTitleText);
        promptTextView.setText(prompt.get_prompt());

        Log.d("CustomPromptScreen", "Prompt screen displayed prompt has been updated with the following: " + prompt.get_prompt());
    }

    private void DisplayPlayerPromptData(PlayerPrompt playerPrompt){
        // Gets all players
        Vector<Player> players = new Vector<Player>();
        players.addAll(PlayerManager.getAllAlivePlayers());
        players.addAll(PlayerManager.getAllDeadPlayers());

        Vector<PlayerPrompt.PlayerFilterType> filters = playerPrompt.getFilters();
        if(filters.contains(PlayerPrompt.PlayerFilterType.ALL_ALIVE)){
            players.removeAll(PlayerManager.getAllAlivePlayers());
        }
        if(filters.contains(PlayerPrompt.PlayerFilterType.ALL_DEAD)){
            players.removeAll(PlayerManager.getAllDeadPlayers());
        }
        if(filters.contains(PlayerPrompt.PlayerFilterType.SELF)){
            // TODO figure out how to filter out self
        }

        PlayerNightUIAdaptor adaptor = new PlayerNightUIAdaptor(players, this);
        recyclerView.setAdapter(adaptor);
    }

    private void DisplayOptionsPromptData(OptionsPrompt optionsPrompt){
        Vector<Pair<String, Prompt>> options = optionsPrompt.getOptions();

        ButtonAdaptor adaptor = new ButtonAdaptor(optionsPrompt, this);
        recyclerView.setAdapter(adaptor);
    }

    private void ConfigureListeners(){
        refreshListener = new IEventListener<Prompt>() {
            @Override
            public void Response(Prompt prompt) {
                Refresh(prompt);
            }
        };

        PromptsManager.OnUpdatePrompt.AddListener(refreshListener);

        exitListener = new IVoidEventListener() {
            @Override
            public void Response() {
                Exit();
            }
        };

        PromptsManager.OnEndAllPrompts.AddListener(exitListener);
    }

    private void ConfigureBackButton(){
        Button backButton = findViewById(R.id.BacktoLastActionButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackToLastAction();
            }
        });
    }

    private void ConfigureNextButton(){
        Button nextButton = findViewById(R.id.ToNextActionButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToNextAction();
            }
        });
    }

    public void BackToLastAction(){
        Exit();
    }

    public void ToNextAction(){
        PromptsManager.GetNextPrompt();
    }

    private void Exit(){
        if(GameManager.getCurrentGameState() == GameManager.gameState.NIGHT){
            GameManager.setCurrentGameState(GameManager.gameState.DAY);
        }

        ResolvingManager.resolveEndOfNightActions();

        ActionLogManager.Summary();
//        ActionLogManager.clearAllLogs();

        finish();
//        startActivity(new Intent(this, NightSummaryScreen.class));
    }

    @Override
    protected void onDestroy() {
        PromptsManager.OnUpdatePrompt.RemoveListener(refreshListener);
        PromptsManager.OnEndAllPrompts.RemoveListener(exitListener);
        super.onDestroy();
    }
}
