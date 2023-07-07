package com.example.pufflemafia.app.screens;

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

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.events.IVoidEventListener;
import com.example.pufflemafia.app.events.VoidEvent;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.game.states.Setup;

import java.util.ArrayList;
import java.util.Set;
import java.util.Vector;

public class EnterNamesScreen extends CustomAppCompatActivityWrapper {
    private EditText nameEditText;
    private GridView namesGridView;
    private Vector<String> namesList;
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

        namesList = Setup.getNames();
        namesAdapter = new NamesAdapter();
        namesAdapter.onDataChanged.AddListener(new IVoidEventListener() {
            @Override
            public void Response() {
                namesAdapter.notifyDataSetChanged();
                Refresh();
            }
        });
        namesGridView.setAdapter(namesAdapter);

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

        Button addNameButton = findViewById(R.id.addNameButton);
        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });

    }

    private void Refresh(){
        Button chooseCharactersButton = findViewById(R.id.ChooseCharactersButton);
        Button randomCharactersButton = findViewById(R.id.RandomCharactersButton);

        namesList = Setup.getNames();

        int numberOfNames = namesList.size();
        numberOfNamesTextView.setText("Names Entered: " + numberOfNames);

        if(numberOfNames > 0){
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
                    startActivity(new Intent(EnterNamesScreen.this, CharacterSelectScreen.class));
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
                    Setup.SetUpGame();
//                    startActivity(new Intent(EnterNamesScreen.this, MainMafiaPage.class));
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
            Setup.addName(name);
            nameEditText.setText(""); // Clear the input field
            namesAdapter.onDataChanged.Invoke();

            Refresh();
        }
    }

    private class NamesAdapter extends BaseAdapter {

        public VoidEvent onDataChanged;

        public NamesAdapter(){
            super();
            onDataChanged = new VoidEvent();
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
                button = new Button(EnterNamesScreen.this);
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
                    Setup.removeName(name);
                    onDataChanged.Invoke();

                }
            });

            return button;
        }
    }

    @Override
    protected void onDestroy() {
        namesAdapter.onDataChanged.RemoveAllListeners();
        super.onDestroy();
    }
}
