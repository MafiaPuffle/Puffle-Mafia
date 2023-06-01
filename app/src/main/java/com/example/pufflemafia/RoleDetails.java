package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.game.SoundManager;

public class RoleDetails extends CustomAppCompatActivityWrapper {

    private Intent intent;

    private ImageView selectedRoleImageView;
    private TextView selectedRoleNameTextView;
    private TextView selectedRoleDescriptionTextView;
    private String roleName;
    private Integer roleImageResourceId;
    private String roleDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_details);

        intent = getIntent();

        selectedRoleImageView = findViewById(R.id.RoleImage);
        selectedRoleNameTextView = findViewById(R.id.RoleName);
        selectedRoleDescriptionTextView = findViewById(R.id.AbilityEffect);

        roleName = intent.getStringExtra("name");
        roleImageResourceId = intent.getIntExtra("imageResourceId",0);
        roleDescription = intent.getStringExtra("description");

        selectedRoleNameTextView.setText(roleName);
        selectedRoleImageView.setBackgroundResource(roleImageResourceId);
        selectedRoleImageView.setImageResource(0);
        selectedRoleDescriptionTextView.setText(roleDescription);

        configureBackToMainMenu();
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