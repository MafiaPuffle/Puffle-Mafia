package com.example.pufflemafia.app.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.adapters.ActionLogUIAdaptor;
import com.example.pufflemafia.app.data.actions.ActionLog;
import com.example.pufflemafia.app.game.ActionLogManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.game.states.Night;

import java.util.Vector;

public class NightSummaryScreen extends CustomAppCompatActivityWrapper {
    private Vector<ActionLog> logs;

    private ActionLogUIAdaptor DontTell_adaptor;
    private ActionLogUIAdaptor TellAll_adaptor;
    private RecyclerView.LayoutManager DontTell_layoutManager;
    private RecyclerView.LayoutManager TellAll_layoutManager;
    private RecyclerView DontTell_recyclerView;
    private RecyclerView TellAll_recyclerView;

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
        Vector<String> tellAllFilterTags = new Vector<String>();
        tellAllFilterTags.add("TELL_ALL");


        DontTell_adaptor = new ActionLogUIAdaptor(logs, this);
        TellAll_adaptor = new ActionLogUIAdaptor(logs, tellAllFilterTags, this);

        DontTell_layoutManager = new LinearLayoutManager(this);
        TellAll_layoutManager = new LinearLayoutManager(this);

        DontTell_recyclerView = findViewById(R.id.SummaryOfAllNightActions);
        TellAll_recyclerView = findViewById(R.id.SummaryToTellPlayers);

        DontTell_recyclerView.setAdapter(DontTell_adaptor);
        DontTell_recyclerView.setLayoutManager(DontTell_layoutManager);

        TellAll_recyclerView.setAdapter(TellAll_adaptor);
        TellAll_recyclerView.setLayoutManager(TellAll_layoutManager);
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
