package com.example.pufflemafia.app.screens;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pufflemafia.R;
import com.example.pufflemafia.adaptors.roleAdaptors.AllRolesRoleUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;


public class RolesScreen extends CustomAppCompatActivityWrapper {

    private Vector<Role> allRoles;
    private RecyclerView allRolesRecyclerView;
    private RecyclerView.LayoutManager allRolesLayoutManager;
    private AllRolesRoleUIAdaptor allRolesRoleUIAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);

        configureRecyclerView();
        configureBackToMainMenu();
    }

    private void configureRecyclerView(){
        allRoles = DataManager.GetAllRoles();

        allRolesRecyclerView = findViewById(R.id.AllRolesRecyclerView);
        allRolesLayoutManager = new GridLayoutManager(this, 5);
        allRolesRoleUIAdaptor = new AllRolesRoleUIAdaptor(allRoles, this);

        allRolesRecyclerView.setAdapter(allRolesRoleUIAdaptor);
        allRolesRecyclerView.setLayoutManager(allRolesLayoutManager);
    }

    private void configureBackToMainMenu(){
        Button BackToMainMenuButton = (Button) findViewById(R.id.BackToMainMafiaScreen);
        BackToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }



}
