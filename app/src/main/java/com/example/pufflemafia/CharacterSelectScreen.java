package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.pufflemafia.adaptors.PossibleRoleUIAdaptor;
import com.example.pufflemafia.adaptors.SelectableRoleUIAdaptor;
import com.example.pufflemafia.adaptors.SelectedRoleUIAdaptor;
import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class CharacterSelectScreen extends CustomAppCompatActivityWrapper implements IListener<Boolean> {

    private int buttonCount = 0;
    private TextView countTextView;
    private RecyclerView allRolesRecyclerView;
    private RecyclerView selectedRolesRecyclerView;
    private RecyclerView.LayoutManager allRolesLayoutManager;
    private RecyclerView.LayoutManager selectedLayoutManager;
    private PossibleRoleUIAdaptor allRolesUIAdaptor;
    private SelectedRoleUIAdaptor selectedRolesUIAdaptor;

    private Vector<Role> allRoles;
    private Vector<Role> selectedRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select_screen);

        Log.i("CharacterSelectScreen", "Starting CharacterSelectScreen");

        AppManager.gameSetup.chosenRoles.clear();
        AppManager.gameSetup.addRole(DataManager.GetRole("Mafia"));

        allRoles = DataManager.GetAllRoles();
        selectedRoles = AppManager.gameSetup.chosenRoles;

        countTextView = findViewById(R.id.ChosenCharacterCountText);

        configureRecyclerViews();

        AppManager.gameSetup.onDataUpdated.AddListener(this);
        SoundManager.playSfx("Click");

        // Configure Buttons
        configureBackToStart();
        configureDoneChoosingCharactersButton();
        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.chosenRoles.size());
        refreshStartGameButton();
    }

    @Override
    protected void onDestroy() {
        AppManager.gameSetup.onDataUpdated.RemoveListener(this);
        super.onDestroy();
    }

    private void updateCountTextView(int numberOfPlayers, int numberOfRoles){
        int difference = numberOfPlayers - numberOfRoles;

        if (difference == 1 && AppManager.gameSetup.mafiaHasBeenChosen() == false) {
            countTextView.setText("Choose a Mafia");
        }
        else if(difference > 0){
            countTextView.setText("Choose " + String.valueOf(difference) + " more roles");
        }
        else if(difference < 0){
            countTextView.setText("Too Many Roles");
        }
        else if (difference == 0 && AppManager.gameSetup.mafiaHasBeenChosen() == false){
            countTextView.setText("Replace a role with a Mafia");
        }
        else {
            countTextView.setText("Ready!");
        }
    }

    private void refresh(){
        allRolesUIAdaptor.notifyDataSetChanged();
        selectedRolesUIAdaptor.notifyDataSetChanged();
        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.chosenRoles.size());
        refreshStartGameButton();
    }

    private void refreshStartGameButton(){
        Button startGameButton = findViewById(R.id.DoneChoosingCharactersButton);
        if(AppManager.gameSetup.checkIfIsValid()){
            startGameButton.setVisibility(View.VISIBLE);
        }
        else{
            startGameButton.setVisibility(View.GONE);
        }
    }

    private void configureRecyclerViews(){
        // initialize adaptors
        allRolesUIAdaptor = new PossibleRoleUIAdaptor(allRoles, this);
        selectedRolesUIAdaptor = new SelectedRoleUIAdaptor(selectedRoles, this);

        // initialize layoutManagers
        allRolesLayoutManager = new GridLayoutManager(this, 5);
        selectedLayoutManager = new GridLayoutManager(this, 5);

        // initialize recyclerViews
        allRolesRecyclerView = findViewById(R.id.AllCharactersRecyclerView);
        selectedRolesRecyclerView = findViewById(R.id.ChosenCharactersRecyclerView);

        // configure allRolesRecyclerView
        allRolesRecyclerView.setLayoutManager(allRolesLayoutManager);
        allRolesRecyclerView.setAdapter(allRolesUIAdaptor);

        // configure selectedRolesRecyclerView
        selectedRolesRecyclerView.setLayoutManager(selectedLayoutManager);
        selectedRolesRecyclerView.setAdapter(selectedRolesUIAdaptor);
    }

    private void configureDoneChoosingCharactersButton() {
        Button DoneChoosingCharactersButton = findViewById(R.id.DoneChoosingCharactersButton);
        DoneChoosingCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                if(AppManager.gameSetup.checkIfIsValid()){
                    GameManager.StartNewGame(AppManager.gameSetup);
                    startActivity(new Intent(CharacterSelectScreen.this, MainMafiaPage.class));
                }
            }
        });
    }

    // Back Button
    private void configureBackToStart(){
        Button BackToStartButton = findViewById(R.id.BackToStartButton);
        BackToStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                AppManager.gameSetup.chosenRoles.clear();
                finish();
            }
        });
    }

    @Override
    public void Response() {
        refresh();
    }

    @Override
    public void Response(Boolean aBoolean) {
        refresh();
    }
}
