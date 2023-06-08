package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.IListener;
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.SoundManager;

public class Start extends CustomAppCompatActivityWrapper implements IListener<Boolean> {
    private EditText nameEditText;
    private GridView namesGridView;
    private ArrayList<String> namesList;
    private NamesAdapter namesAdapter;
    private TextView numberOfNamesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        makeKeyboardHidealbe(findViewById(R.id.rootConstraintLayout));

        // Names GridView
        nameEditText = findViewById(R.id.nameEditText);
        namesGridView = findViewById(R.id.namesGridView);
        numberOfNamesTextView = findViewById(R.id.NumberofNames);

        namesList = new ArrayList<>();
        namesAdapter = new NamesAdapter();
        namesAdapter.onDataChanged.AddListener(this);
        namesGridView.setAdapter(namesAdapter);

        AppManager.gameSetup = new GameSetup();

        int numberOfNames = namesList.size();
        numberOfNamesTextView.setText("Names Entered: " + numberOfNames);

        // Configure Buttons
        configureBackToMainMenu();
        configureRandomCharactersButton();
        configureChooseCharactersButton();
        Refresh();

        nameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                Log.d("Start", "onEditorAction() called");
                SoundManager.playSfx("Click");
                addName();
                return true;
            }
        });

    }

    private void Refresh(){
        Button chooseCharactersButton = findViewById(R.id.ChooseCharactersButton);
        Button randomCharactersButton = findViewById(R.id.RandomCharactersButton);
        if(namesList.size() > 0){
            chooseCharactersButton.setVisibility(View.VISIBLE);
            randomCharactersButton.setVisibility(View.VISIBLE);
        }
        else {
            chooseCharactersButton.setVisibility(View.GONE);
            randomCharactersButton.setVisibility(View.GONE);
        }
    }

    // Character Select Screen Button
    private void configureChooseCharactersButton() {
        Button chooseCharactersButton = findViewById(R.id.ChooseCharactersButton);
        chooseCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namesList.size() > 0){
                    SoundManager.playSfx("Click");
                    startActivity(new Intent(Start.this, CharacterSelectScreen.class));
                }
            }
        });
    }

    private void configureRandomCharactersButton() {
        Button randomCharactersButton = findViewById(R.id.RandomCharactersButton);
        randomCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namesList.size() > 0){
                    Vector<String> names = new Vector<String>(namesList);
                    SoundManager.playSfx("Click");
                    AppManager.gameSetup.SetUpRandomGame(names);
                    GameManager.StartNewGame(AppManager.gameSetup);
                    startActivity(new Intent(Start.this, MainMafiaPage.class));
                }
            }
        });
    }

    // BACK BUTTON
    private void configureBackToMainMenu() {
        Button backToMainMenuButton = findViewById(R.id.BackToMainMafiaScreen);
        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }

    private void addName() {
        String name = nameEditText.getText().toString().trim();
        if (!name.isEmpty()) {
            namesList.add(name);
            AppManager.gameSetup.names.add(name);
            nameEditText.setText(""); // Clear the input field
            namesAdapter.notifyDataSetChanged();

            int numberOfNames = namesList.size();
            numberOfNamesTextView.setText("Names Entered: " + numberOfNames);
            Refresh();
        }
    }

    @Override
    public void Response() {
        Refresh();
    }

    @Override
    public void Response(Boolean aBoolean) {

    }

    private class NamesAdapter extends BaseAdapter {

        public Event<Boolean> onDataChanged;

        public NamesAdapter(){
            super();
            onDataChanged = new Event<Boolean>();
        }

        @Override
        public int getCount() {
            return namesList.size();
        }

        @Override
        public Object getItem(int position) {
            return namesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = new Button(Start.this);
                button.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                button.setPadding(8, 8, 8, 8);
            } else {
                button = (Button) convertView;
            }

            final String name = namesList.get(position);
            button.setText(name);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SoundManager.playSfx("Click");
                    AppManager.gameSetup.names.remove(position);
                    namesList.remove(position);
                    namesAdapter.notifyDataSetChanged();
                    onDataChanged.Invoke();

                    int numberOfNames = namesList.size();
                    numberOfNamesTextView.setText("Names Entered: " + numberOfNames);
                }
            });

            return button;
        }
    }

    @Override
    protected void onDestroy() {
        namesAdapter.onDataChanged.RemoveListener(this);
        super.onDestroy();
    }
}
