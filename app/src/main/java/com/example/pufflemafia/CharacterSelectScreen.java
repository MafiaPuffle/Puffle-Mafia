package com.example.pufflemafia;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;
import android.view.ViewGroup;



import com.example.pufflemafia.adaptors.PossibleRoleUIAdaptor;
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

    private ImageView fingerImageView;

    private AnimationDrawable fingerAnimation;

    private int[] itemIds = {R.id.nextButton, R.id.nextButton2}; // Replace with the actual IDs of the items you want to tap
    private int currentItemIndex = 0;

    private Button helpButton;
    private Button nextButton;
    private Button nextButton2;

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

        AppManager.gameSetup.removeAllRoles();
        AppManager.gameSetup.addRole(DataManager.GetRole("Mafia"));

        allRoles = DataManager.GetAllRoles();
        selectedRoles = AppManager.gameSetup.getChosenRoles();

        countTextView = findViewById(R.id.ChosenCharacterCountText);

        configureRecyclerViews();

        AppManager.gameSetup.onDataUpdated.AddListener(this);
        SoundManager.playSfx("Click");

        // Configure Buttons
        configureBackToStart();
        configureDoneChoosingCharactersButton();
        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.numberOfRolesChosen());
        refreshStartGameButton();

// Initialize buttons
        helpButton = findViewById(R.id.helpButton);
        nextButton = findViewById(R.id.nextButton);
        nextButton2 = findViewById(R.id.nextButton2);


        // Set initial visibility
        nextButton.setVisibility(View.GONE);
        nextButton2.setVisibility(View.GONE);


// Set onClickListener for helpButton
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpButton.setVisibility(View.GONE);
                nextButton.setVisibility(View.VISIBLE);
                fingerImageView.setVisibility(View.VISIBLE);
                currentItemIndex = 0;
                moveFingerToItem();
            }
        });

// Set onClickListener for nextButton1
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton.setVisibility(View.GONE);
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
        if (currentItemIndex == itemIds.length) {
            currentItemIndex = 0;
        }
        fingerImageView.setVisibility(View.VISIBLE);
        moveFingerToItem();
    }


    private void moveFingerToItem() {
        RecyclerView recyclerView;
        if (currentItemIndex == 0) {
            recyclerView = findViewById(R.id.AllCharactersRecyclerView);
        } else {
            recyclerView = findViewById(R.id.ChosenCharactersRecyclerView);
        }

        View targetItem = recyclerView.getChildAt(0);
        if (targetItem != null) {
            int[] targetLocation = new int[2];
            targetItem.getLocationOnScreen(targetLocation);

            int targetX = targetLocation[0] + targetItem.getWidth() / 2;
            int targetY = targetLocation[1] + targetItem.getHeight() / 2;

            fingerImageView.animate()
                    .x(targetX - fingerImageView.getWidth() / 2)
                    .y(targetY - fingerImageView.getHeight() / 2)
                    .setDuration(500)
                    .start();
        }
    }


    @Override
    protected void onDestroy() {
        AppManager.gameSetup.onDataUpdated.RemoveListener(this);
        super.onDestroy();
    }

    private void updateCountTextView(int numberOfPlayers, int numberOfRoles){
        int difference = numberOfPlayers - numberOfRoles;

        if (difference == 1 && !AppManager.gameSetup.mafiaHasBeenChosen()) {
            countTextView.setText("Choose a Mafia");
        }
        else if(difference > 0){
            countTextView.setText("Choose " + String.valueOf(difference) + " more roles");
        }
        else if(difference < 0){
            countTextView.setText("Too Many Roles");
        }
        else if (difference == 0 && !AppManager.gameSetup.mafiaHasBeenChosen()){
            countTextView.setText("Replace a role with a Mafia");
        }
        else {
            countTextView.setText("Ready!");
        }
    }

    private void refresh(){
        allRolesUIAdaptor.notifyDataSetChanged();
        selectedRolesUIAdaptor.notifyDataSetChanged();
        updateCountTextView(AppManager.gameSetup.numberOfPlayers(), AppManager.gameSetup.numberOfRolesChosen());
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
                AppManager.gameSetup.removeAllRoles();
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
