package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.PossibleRoleUIAdaptor;
import com.example.pufflemafia.app.adapters.SelectedRoleUIAdaptor;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.events.IVoidEventListener;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.game.states.Setup;

import java.util.Set;
import java.util.Vector;

public class CharacterSelectScreen extends CustomAppCompatActivityWrapper {

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

    private IVoidEventListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select_screen);

        ColorStateList blueColorStateList = ColorStateList.valueOf(Color.BLUE);
        ColorStateList greenColorStateList = ColorStateList.valueOf(Color.GREEN);
        ColorStateList redColorStateList = ColorStateList.valueOf(Color.RED);

        Button button = findViewById(R.id.helpButton);
        button.setBackgroundTintList(blueColorStateList);

        Log.i("CharacterSelectScreen", "Starting CharacterSelectScreen");

        Setup.removeAllRoles();
        Setup.addRole(DataManager.getRole("mafia"));

        allRoles = DataManager.getAllRoles();
        // TODO add sorting by team back
        selectedRoles = Setup.getRoles();

        countTextView = findViewById(R.id.ChosenCharacterCountText);

        myListener = new IVoidEventListener() {
            @Override
            public void Response() {
                refresh();
            }
        };

        Setup.OnAddRole.AddListener(myListener);
        Setup.OnRemoveRole.AddListener(myListener);
        SoundManager.playSfx("Click");


        configureRecyclerViews();
        // Configure Buttons
        configureBackToStart();
        configureDoneChoosingCharactersButton();
        // TODO figure out if you will keep the count text view
//        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.numberOfRolesChosen());
        refreshStartGameButton();
        // TODO add help button back
//        configureHelpButton();
    }


    @Override
    protected void onDestroy() {
        Setup.OnAddRole.RemoveListener(myListener);
        Setup.OnRemoveRole.RemoveListener(myListener);
        super.onDestroy();
    }

    private void updateCountTextView(int numberOfPlayers, int numberOfRoles){
//        int difference = numberOfPlayers - numberOfRoles;
//
//        if (difference == 1 && !AppManager.gameSetup.mafiaHasBeenChosen()) {
//            countTextView.setText("Choose a Mafia");
//        }
//        else if(difference > 0){
//            countTextView.setText("Choose " + String.valueOf(difference) + " more roles");
//        }
//        else if(difference < 0){
//            countTextView.setText("Too Many Roles");
//        }
//        else if (difference == 0 && !AppManager.gameSetup.mafiaHasBeenChosen()){
//            countTextView.setText("Replace a role with a Mafia");
//        }
//        else {
//            countTextView.setText("Ready!");
//        }
    }

    private void refresh(){
        allRoles = DataManager.getAllRoles();
        // TODO add sorting by team back
        selectedRoles = Setup.getRoles();

        allRolesUIAdaptor.notifyDataSetChanged();
        selectedRolesUIAdaptor.notifyDataSetChanged();
//        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.numberOfRolesChosen());
        refreshStartGameButton();
    }

    private void refreshStartGameButton(){
        Button startGameButton = findViewById(R.id.DoneChoosingCharactersButton);
//        if(AppManager.gameSetup.checkIfIsValid()){
//            startGameButton.setVisibility(View.VISIBLE);
//        }
//        else{
//            startGameButton.setVisibility(View.GONE);
//        }
    }

    private void configureRecyclerViews(){
        // initialize adaptors
        Log.d("CharacterSelect","Setting up adaptors");
        allRolesUIAdaptor = new PossibleRoleUIAdaptor(allRoles, this);
        selectedRolesUIAdaptor = new SelectedRoleUIAdaptor(selectedRoles, this);
        Log.d("CharacterSelect","Adaptors are setup");

        // initialize layoutManagers
        Log.d("CharacterSelect","Setting up layoutManagers");
        allRolesLayoutManager = new GridLayoutManager(this, 5);
        selectedLayoutManager = new GridLayoutManager(this, 5);
        Log.d("CharacterSelect","layoutManagers are setup");

        // initialize recyclerViews
        Log.d("CharacterSelect","Setting up recyclerViews");
        allRolesRecyclerView = findViewById(R.id.AllCharactersRecyclerView);
        selectedRolesRecyclerView = findViewById(R.id.ChosenCharactersRecyclerView);
        Log.d("CharacterSelect","recyclerViews are setup");

        // configure allRolesRecyclerView
        Log.d("CharacterSelect","Configuring allRolesRecyclerView");
        allRolesRecyclerView.setLayoutManager(allRolesLayoutManager);
        allRolesRecyclerView.setAdapter(allRolesUIAdaptor);
        Log.d("CharacterSelect","allRolesRecyclerView configured");

        // configure selectedRolesRecyclerView
        Log.d("CharacterSelect","Configuring selectedRolesRecyclerView");
        selectedRolesRecyclerView.setLayoutManager(selectedLayoutManager);
        selectedRolesRecyclerView.setAdapter(selectedRolesUIAdaptor);
        Log.d("CharacterSelect","selectedRolesRecyclerView configured");
    }

    private void configureHelpButton(){
//        Button helpButton = findViewById(R.id.helpButton);
//
//        Vector<ViewToPointTo> allViewsToPointTo = new Vector<ViewToPointTo>();
//
//        allViewsToPointTo.add(new ViewToPointTo(allRolesRecyclerView, 0,"Tap to choose role", ViewToPointTo.ViewClickType.NORMAL));
//        allViewsToPointTo.add(new ViewToPointTo(selectedRolesRecyclerView, 1, "Tap to remove role", ViewToPointTo.ViewClickType.NORMAL));
////        allViewsToPointTo.add(new ViewToPointTo(allRolesRecyclerView, 2));
//
//        HelpPromptManager.InitializeHelpPopups(this,this,helpButton, allViewsToPointTo);
    }

    private void configureDoneChoosingCharactersButton() {
        Button DoneChoosingCharactersButton = findViewById(R.id.DoneChoosingCharactersButton);
        DoneChoosingCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
//                if(AppManager.gameSetup.checkIfIsValid()){
//                    GameManager.StartNewGame(AppManager.gameSetup);
//                    startActivity(new Intent(CharacterSelectScreen.this, MainMafiaPage.class));
//                }
                Setup.SetUpGame();
                // TODO go to day screen
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
                Setup.removeAllRoles();
                finish();
            }
        });
    }
}