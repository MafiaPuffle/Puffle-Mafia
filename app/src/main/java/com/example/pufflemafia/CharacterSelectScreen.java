package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.GameManager;

import java.util.Vector;

public class CharacterSelectScreen extends AppCompatActivity implements IListener<Boolean> {

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

        allRoles = DataManager.GetAllRoles();
        selectedRoles = AppManager.gameSetup.chosenRoles;

        countTextView = findViewById(R.id.ChosenCharacterCountText);

        configureRecyclerViews();

        AppManager.gameSetup.onDataUpdated.AddListener(this);

        //Configure Buttons
        configureBackToStart();
        configureDoneChoosingCharactersButton();
    }

    @Override
    protected void onDestroy() {
        AppManager.gameSetup.onDataUpdated.RemoveListener(this);
        super.onDestroy();
    }

    private void updateCountTextView(int numberOfPlayers, int numberOfRoles){
        int difference = numberOfPlayers - numberOfRoles;

        if(difference > 0){
            countTextView.setText(String.valueOf(difference));
        }
        else if(difference < 0){
            countTextView.setText("To Many Roles");
        }
        else if (difference == 0 && AppManager.gameSetup.checkIfIsValid() == false){
            countTextView.setText("Needs Mafia");
        }
        else {
            countTextView.setText("Ready!");
        }
    }

    private void refresh(){
        allRolesUIAdaptor.notifyDataSetChanged();
        selectedRolesUIAdaptor.notifyDataSetChanged();
        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.chosenRoles.size());
    }

    private void configureRecyclerViews(){
        // initialize adaptors
        allRolesUIAdaptor = new PossibleRoleUIAdaptor(allRoles, this);
        selectedRolesUIAdaptor = new SelectedRoleUIAdaptor(selectedRoles, this);

        // initialize layoutManagers
        allRolesLayoutManager = new GridLayoutManager(this, 7);
        selectedLayoutManager = new GridLayoutManager(this, 7);

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
        Button DoneChoosingCharactersButton = (Button) findViewById(R.id.DoneChoosingCharactersButton);
        DoneChoosingCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppManager.gameSetup.checkIfIsValid()){
                    GameManager.StartNewGame(AppManager.gameSetup);
                    startActivity(new Intent(CharacterSelectScreen.this, MainMafiaPage.class));
                }
            }
        });
    }

    //Back Button
    private void configureBackToStart(){
        Button BackToStartButton = (Button) findViewById(R.id.BackToStartButton);
        BackToStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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