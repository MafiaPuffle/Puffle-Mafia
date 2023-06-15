package com.example.pufflemafia;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pufflemafia.adaptors.playerAdaptors.PlayerNightUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.ViewToPointTo;
import com.example.pufflemafia.app.data.Power;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.ActiveRolesManager;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.HelpPromptManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class NightActions extends CustomAppCompatActivityWrapper implements IListener<Boolean> {

    private Vector<Player> allAlivePlayers;
    private Role currentActiveRoleAtNight;

    private TextView activeRoleTextView;
    private TextView nightActionTitle;
    private ImageButton activeRoleImageButton;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PlayerNightUIAdaptor adaptor;
    private LinearLayout YesOrNoHolder;
    private Button YesButton;
    private Button NoButton;
    public Button helpButton;
    private Power.PowerPromptType promptType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_actions);

        allAlivePlayers = PlayerManager.getAllAlive();

        activeRoleTextView = findViewById(R.id.ActiveRoleUITextView);
        nightActionTitle = findViewById(R.id.NightActionTitleText);
        activeRoleImageButton = findViewById(R.id.ActiveRoleUIImage);

        recyclerView = findViewById(R.id.AllAlivePlayersForThisNightRecycleView);
        layoutManager = new LinearLayoutManager(this);
        adaptor = new PlayerNightUIAdaptor(allAlivePlayers, this);

        YesOrNoHolder = findViewById(R.id.YesOrNoHolder);
        YesButton = findViewById(R.id.Yes_button);
        YesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onYes();
            }
        });
        NoButton = findViewById(R.id.No_button);
        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNo();
            }
        });

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(layoutManager);

        PlayerManager.sortAllAliveByTokens();

        GameManager.StartNight();

        Refresh();

        // Configure Buttons
        configureToNextActionButton();
        configureBacktoLastActionButton();
        ActiveRolesManager.onLookingAtLastRoleForTheNight.AddListener(this);
    }

    private void Refresh(){
        allAlivePlayers = PlayerManager.getAllAlive();
        currentActiveRoleAtNight = GameManager.getCurrentRoleActiveAtNight();
        if(currentActiveRoleAtNight == null){
            PlayerManager.sortAllAliveByTokens();
            finish();
            return;
        }

        promptType = currentActiveRoleAtNight.getPower().getPromptType();

        switch (currentActiveRoleAtNight.getPower().getPromptType()){
            case ALL_PLAYERS:
                recyclerView.setVisibility(View.VISIBLE);
                YesOrNoHolder.setVisibility(View.INVISIBLE);
                break;
            case YES_OR_NO:
                recyclerView.setVisibility(View.INVISIBLE);
                YesOrNoHolder.setVisibility(View.VISIBLE);
                break;
            case NOTHING:
                recyclerView.setVisibility(View.INVISIBLE);
                YesOrNoHolder.setVisibility(View.INVISIBLE);
                break;
        }

        RefreshActiveRoleImageButton();

        nightActionTitle.setText(currentActiveRoleAtNight.getPower().getPrompt());

        configrueHelpButton();
    }

    private void RefreshActiveRoleImageButton(){
        activeRoleImageButton.setBackgroundResource(currentActiveRoleAtNight.getImageResource());
        activeRoleImageButton.setImageResource(0);
        activeRoleImageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(NightActions.this, RoleDetails.class);
                intent.putExtra("name", currentActiveRoleAtNight.getName());
                intent.putExtra("imageResourceId", currentActiveRoleAtNight.getImageResource());
                intent.putExtra("description", currentActiveRoleAtNight.getDescription());
                intent.putExtra("winCondition", currentActiveRoleAtNight.getWinCondition());
                intent.putExtra("team", currentActiveRoleAtNight.getTeam());
                intent.putExtra("alliance", currentActiveRoleAtNight.getAlliance());
                startActivity(intent);
                return false;
            }
        });

        activeRoleTextView.setText(currentActiveRoleAtNight.getName());
    }

    // Next Button
    private void configureToNextActionButton(){
        Button ToNextActionButton = findViewById(R.id.ToNextActionButton);
        ToNextActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                GameManager.GoToNextEventAtNight();
                Refresh();
            }
        });
    }

    // Back Button
    private void configureBacktoLastActionButton(){
        Button BacktoLastActionButton = findViewById(R.id.BacktoLastActionButton);
        BacktoLastActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                GameManager.GoToPreviousEventAtNight();
                Button ToNextActionButton = findViewById(R.id.ToNextActionButton);
                ToNextActionButton.setText("NEXT");
                Refresh();
            }
        });
    }
    private void configrueHelpButton(){
        helpButton = findViewById(R.id.helpNightButton);
        Vector<ViewToPointTo> allViewsToPointTo = new Vector<ViewToPointTo>();

        switch (promptType){
            case ALL_PLAYERS:
                allViewsToPointTo.add(new ViewToPointTo(recyclerView, 0, "Tap to apply effect", ViewToPointTo.ViewClickType.NORMAL));
                break;
        }

        allViewsToPointTo.add(new ViewToPointTo(activeRoleImageButton, "Hold to see more info", ViewToPointTo.ViewClickType.LONG));

        HelpPromptManager.InitializeHelpPopups(this, this, helpButton, allViewsToPointTo);
    }

    private void onYes(){
        for (Player player: PlayerManager.getAllAlive()) {
            if(player.getRole().getName() == currentActiveRoleAtNight.getName()){
                player.getRole().getPower().usePower();
            }
        }

        SoundManager.playSfx("Click");
        GameManager.GoToNextEventAtNight();
        Refresh();
    }

    private void onNo(){
        SoundManager.playSfx("Click");
        GameManager.GoToNextEventAtNight();
        Refresh();
    }

    @Override
    protected void onDestroy() {
        ActiveRolesManager.onLookingAtLastRoleForTheNight.RemoveListener(this);
        super.onDestroy();
    }

    @Override
    public void Response() {

    }

    @Override
    public void Response(Boolean aBoolean) {
        Button ToNextActionButton = findViewById(R.id.ToNextActionButton);
        if(aBoolean){
            ToNextActionButton.setText("END NIGHT");
        }
        else {
            ToNextActionButton.setText("NEXT");
        }
    }
}
