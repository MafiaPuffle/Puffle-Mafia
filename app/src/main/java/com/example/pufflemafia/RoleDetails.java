package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.SoundManager;

public class RoleDetails extends CustomAppCompatActivityWrapper {

    private Intent intent;

    private ImageView selectedRoleImageView;
    private ImageView selectedRoleAllianceImageView;
    private TextView selectedRoleNameTextView;
    private TextView selectedRoleDescriptionTextView;
    private TextView selectedRoleWinConditionTextView;
    private TextView selectedRoleTeamTextView;
    private String roleName;
    private Integer roleImageResourceId;
    private String roleDescription;
    private String roleWinCondition;
    private Role.Teams roleTeam;
    private Role.Alliances roleAlliance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_details);

        intent = getIntent();

        selectedRoleImageView = findViewById(R.id.RoleImage);
        selectedRoleAllianceImageView = findViewById(R.id.GoodorEvil);

        selectedRoleNameTextView = findViewById(R.id.RoleName);
        selectedRoleDescriptionTextView = findViewById(R.id.AbilityEffect);
        selectedRoleWinConditionTextView = findViewById(R.id.WinConditionEffect);
        selectedRoleTeamTextView = findViewById(R.id.TeamTextView);

        roleImageResourceId = intent.getIntExtra("imageResourceId",0);

        roleName = intent.getStringExtra("name");
        roleDescription = intent.getStringExtra("description");
        roleWinCondition = intent.getStringExtra("winCondition");

        roleTeam = (Role.Teams) intent.getSerializableExtra("team");
        roleAlliance = (Role.Alliances) intent.getSerializableExtra("alliance");

        selectedRoleImageView.setBackgroundResource(roleImageResourceId);
        selectedRoleImageView.setImageResource(0);
        switch (roleAlliance){
            case GOOD:
                selectedRoleAllianceImageView.setImageResource(R.drawable.thumbs_up);
                break;
            case EVIL:
                selectedRoleAllianceImageView.setImageResource(R.drawable.thumbs_down);
                break;
            case NEUTRAL:
                selectedRoleAllianceImageView.setImageResource(R.drawable.fist_sideways);
                break;
        }
//        selectedRoleAllianceImageView.setImageResource(0);

        selectedRoleNameTextView.setText(roleName);
        selectedRoleDescriptionTextView.setText(roleDescription);
        selectedRoleWinConditionTextView.setText(roleWinCondition);
        switch (roleTeam){
            case TOWN:
                configureTeamInfo("TOWN", R.drawable.green_rectangle);
                break;
            case MAFIA:
                configureTeamInfo("MAFIA", R.drawable.red_rectangle);
                break;
            case RIVAL_MAFIA:
                configureTeamInfo("RIVAL MAFIA", R.drawable.red_rectangle);
                break;
            case NEUTRAL:
                configureTeamInfo("NEUTRAL", R.drawable.black_rectangle);
                break;
            case SELF:
                configureTeamInfo("SELF", R.drawable.black_rectangle);
                break;
        }

        configureBackToMainMenu();
    }

    private void configureTeamInfo(String teamName, int BackgroundID){
        TextView RoleName = findViewById(R.id.RoleName);
        TextView RoleAbility = findViewById(R.id.AbilityEffect);
        TextView WinCondition = findViewById(R.id.WinConditionEffect);
        TextView RoleTeam = findViewById(R.id.TeamTextView);

        RoleName.setBackgroundResource(BackgroundID);
        RoleAbility.setBackgroundResource(BackgroundID);
        WinCondition.setBackgroundResource(BackgroundID);
        RoleTeam.setBackgroundResource(BackgroundID);

        RoleTeam.setText(teamName);
    }

    private void configureBackToMainMenu(){
        Button BackToMainMenuButton = (Button) findViewById(R.id.BackToMainMenu);
        BackToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }

}