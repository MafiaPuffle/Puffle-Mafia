package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.roleAdapters.ChangingRoleUIAdaptor;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.UUID;
import java.util.Vector;

public class ChangeRoleScreen extends CustomAppCompatActivityWrapper {

    private Intent intent;

    private UUID ID;
    private Player player;
    private Role currentRole;
    private Vector<Role> allRoles;
    private Role newRole;
    private GridLayout gridLayout;
    private ImageView currentRoleImageView;
    private ImageView newRoleImageView;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ChangingRoleUIAdaptor adaptor;

    private String currentRoleName;
    private int currentRoleImageResource;
    private String currentRoleDescription;
    private String currentRoleWinCondition;
    private Role.Teams currentRoleTeam;
    private Role.Alliances currentRoleAlliance;

    private IEventListener<Role> refreshListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_character_screen);

        allRoles = DataManager.getAllRoles();
        newRole = null;

        getDataFromIntent();

        configureImageViews();

        configureRecyclerView();

        configureNextButton();
        configureBackToMainMenu();

    }

    private void getDataFromIntent(){
        intent = getIntent();
        ID = UUID.fromString(intent.getStringExtra("playerID"));
        player = PlayerManager.getPlayerByID(ID);
        currentRole = player.getRole();
    }

    private void configureImageViews(){
        currentRoleImageView = findViewById(R.id.CurrentRole);
        newRoleImageView = findViewById(R.id.NewRole);

        currentRoleImageView.setImageResource(0);
        currentRoleImageView.setBackgroundResource(currentRole.getImageResource());
        currentRoleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            Role.Alliances alliance = (Role.Alliances) intent.getSerializableExtra("currentRoleAlliance");
            @Override
            public boolean onLongClick(View view) {
//                SoundManager.playSfx("Click");
//                Intent intent = new Intent(ChangeCharacterScreen.this, RoleDetails.class);
//                intent.putExtra("name", currentRoleName);
//                intent.putExtra("imageResourceId", currentRoleImageResource);
//                intent.putExtra("description", currentRoleDescription);
//                intent.putExtra("winCondition", currentRoleWinCondition);
//                intent.putExtra("team", currentRoleTeam);
//                intent.putExtra("alliance", currentRoleAlliance);
//                startActivity(intent);
                return false;
            }
        });

        newRoleImageView.setImageResource(0);
        newRoleImageView.setBackgroundResource(currentRole.getImageResource());
        newRoleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                SoundManager.playSfx("Click");
//                Intent intent = new Intent(ChangeCharacterScreen.this, RoleDetails.class);
//                intent.putExtra("name", currentRoleName);
//                intent.putExtra("imageResourceId", currentRoleImageResource);
//                intent.putExtra("description", currentRoleDescription);
//                intent.putExtra("winCondition", currentRoleWinCondition);
//                intent.putExtra("team", currentRoleTeam);
//                intent.putExtra("alliance", currentRoleAlliance);
//                startActivity(intent);
                return false;
            }
        });
    }

    private void Refresh(){
        newRoleImageView.setBackgroundResource(newRole.getImageResource());
        newRoleImageView.setImageResource(0);

        newRoleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
//                SoundManager.playSfx("Click");
//                Intent intent = new Intent(ChangeCharacterScreen.this, RoleDetails.class);
//                intent.putExtra("name", newRole.getName());
//                intent.putExtra("imageResourceId", newRole.getImageResource());
//                intent.putExtra("description", newRole.getDescription());
//                intent.putExtra("winCondition", newRole.getWinCondition());
//                intent.putExtra("team", newRole.getTeam());
//                intent.putExtra("alliance", newRole.getAlliance());
//                startActivity(intent);
                return false;
            }
        });
    }

    private void configureRecyclerView(){
        adaptor = new ChangingRoleUIAdaptor(allRoles, this);
        layoutManager = new GridLayoutManager(this, 5);
        recyclerView = findViewById(R.id.AllRolesRecycleView);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptor);

        refreshListener = new IEventListener<Role>() {
            @Override
            public void Response(Role role) {
                newRole = role;
                Refresh();
            }
        };
        adaptor.onSelectRole.AddListener(refreshListener);
    }

    private void configureNextButton(){
        Button NextButton = (Button) findViewById(R.id.DoneChangingCharactersButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                if(newRole != null){
                    player.changeRole(newRole);
                    PlayerManager.updatePlayerByID(ID, player);
                }
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
    protected void onDestroy() {
        adaptor.onSelectRole.RemoveListener(refreshListener);
        super.onDestroy();
    }
    
}
