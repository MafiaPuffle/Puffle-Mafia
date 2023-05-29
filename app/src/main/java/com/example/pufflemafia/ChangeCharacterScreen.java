package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.pufflemafia.adaptors.ChangingRoleUIAdaptor;
import com.example.pufflemafia.adaptors.SelectableRoleUIAdaptor;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class ChangeCharacterScreen extends CustomAppCompatActivityWrapper implements IListener<Role> {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_character_screen);

        allRoles = DataManager.GetAllRoles();
        intent = getIntent();

        newRole = new Role();

        listType = (PlayerManager.PlayerMangerListType) intent.getSerializableExtra("ListType");
        position = intent.getIntExtra("position",0);



        currentRoleImageView = findViewById(R.id.CurrentRole);
        newRoleImageView = findViewById(R.id.NewRole);

        currentRoleImageView.setBackgroundResource(intent.getIntExtra("currentRoleImageResource", 0));
        currentRoleImageView.setImageResource(0);

        newRoleImageView.setBackgroundResource(intent.getIntExtra("currentRoleImageResource", 0));
        newRoleImageView.setImageResource(0);

        configureRecyclerView();

        configureNextButton();
        configureBackToMainMenu();

    }

    private void Refresh(){
        newRoleImageView.setBackgroundResource(newRole.getImageResource());
        newRoleImageView.setImageResource(0);
    }

    private void configureRecyclerView(){
        adaptor = new ChangingRoleUIAdaptor(allRoles, this);
        layoutManager = new GridLayoutManager(this, 7);
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