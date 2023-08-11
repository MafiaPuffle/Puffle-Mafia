package com.example.pufflemafia.app.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.ActionLogUIAdaptor;
import com.example.pufflemafia.app.adapters.ActionUIAdaptor;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.actions.ActionLog;
import com.example.pufflemafia.app.game.ActionLogManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.UUID;
import java.util.Vector;

public class NightSummaryScreen extends CustomAppCompatActivityWrapper {
    private Vector<ActionLog> logs;

    private ActionLogUIAdaptor adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_summary);

        getData();

        ConfigureRecyclerViews();

        ConfigureBackButton();
    }

    private void getData(){
        logs = ActionLogManager.getLogs();
    }

    private void ConfigureRecyclerViews(){
        adaptor = new ActionLogUIAdaptor(logs, this);

        layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.SummaryOfNightActions);

        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void ConfigureBackButton(){
        Button reviveButton = findViewById(R.id.BackButton);
        reviveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }
}
