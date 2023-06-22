package com.example.pufflemafia;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.pufflemafia.adaptors.roleAdaptors.ChangingRoleUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.events.IEventListener;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class ChangeCharacterScreen extends CustomAppCompatActivityWrapper implements IEventListener<Role> {

    private Intent intent;

    private PlayerManager.PlayerMangerListType listType;
    private Integer position;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_character_screen);

        allRoles = DataManager.GetAllRoles();
        intent = getIntent();

        newRole = new Role();

        listType = (PlayerManager.PlayerMangerListType) intent.getSerializableExtra("ListType");
        position = intent.getIntExtra("position",0);

        ConfigureCurrentRoleData();

        currentRoleImageView = findViewById(R.id.CurrentRole);
        newRoleImageView = findViewById(R.id.NewRole);

        currentRoleImageView.setBackgroundResource(intent.getIntExtra("currentRoleImageResource", 0));
        currentRoleImageView.setImageResource(0);
        currentRoleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            Role.Alliances alliance = (Role.Alliances) intent.getSerializableExtra("currentRoleAlliance");
            @Override
            public boolean onLongClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(ChangeCharacterScreen.this, RoleDetails.class);
                intent.putExtra("name", currentRoleName);
                intent.putExtra("imageResourceId", currentRoleImageResource);
                intent.putExtra("description", currentRoleDescription);
                intent.putExtra("winCondition", currentRoleWinCondition);
                intent.putExtra("team", currentRoleTeam);
                intent.putExtra("alliance", currentRoleAlliance);
                startActivity(intent);
                return false;
            }
        });

        newRoleImageView.setBackgroundResource(intent.getIntExtra("currentRoleImageResource", 0));
        newRoleImageView.setImageResource(0);
        newRoleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(ChangeCharacterScreen.this, RoleDetails.class);
                intent.putExtra("name", currentRoleName);
                intent.putExtra("imageResourceId", currentRoleImageResource);
                intent.putExtra("description", currentRoleDescription);
                intent.putExtra("winCondition", currentRoleWinCondition);
                intent.putExtra("team", currentRoleTeam);
                intent.putExtra("alliance", currentRoleAlliance);
                startActivity(intent);
                return false;
            }
        });

        configureRecyclerView();

        configureNextButton();
        configureBackToMainMenu();

    }

    private void ConfigureCurrentRoleData(){
        currentRoleName = intent.getStringExtra("currentRoleName");
        currentRoleImageResource = intent.getIntExtra("currentRoleImageResource",0);
        currentRoleDescription = intent.getStringExtra("currentRoleDescription");
        currentRoleWinCondition = intent.getStringExtra("currentRoleWinCondition");
        currentRoleTeam = (Role.Teams) intent.getSerializableExtra("currentRoleTeam");
        currentRoleAlliance = (Role.Alliances) intent.getSerializableExtra("currentRoleAlliance");
    }

    private void Refresh(){
        newRoleImageView.setBackgroundResource(newRole.getImageResource());
        newRoleImageView.setImageResource(0);

        newRoleImageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(ChangeCharacterScreen.this, RoleDetails.class);
                intent.putExtra("name", newRole.getName());
                intent.putExtra("imageResourceId", newRole.getImageResource());
                intent.putExtra("description", newRole.getDescription());
                intent.putExtra("winCondition", newRole.getWinCondition());
                intent.putExtra("team", newRole.getTeam());
                intent.putExtra("alliance", newRole.getAlliance());
                startActivity(intent);
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

        adaptor.onSelectRole.AddListener(this);
    }

    private void configureNextButton(){
        Button NextButton = (Button) findViewById(R.id.DoneChangingCharactersButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                if(newRole.getName() != "PuffleName")PlayerManager.EditPlayerRole(listType, position, newRole);
                PlayerManager.LogSummary();
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

    }

    @Override
    public void Response(Role role) {
        newRole = role;
        Refresh();
    }
}