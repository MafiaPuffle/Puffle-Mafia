package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.game.GameManager;

public class Start extends AppCompatActivity {
    private EditText nameEditText;
    private Button addNameButton;
    private GridView namesGridView;
    private ArrayList<String> namesList;
    private NamesAdapter namesAdapter;
    private TextView numberOfNamesTextView;
    private MediaPlayer clickSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        clickSound = MediaPlayer.create(this, R.raw.click_sound);

        // Configure Buttons
        configureBackToMainMenu();
        configureRandomCharactersButton();
        configureChooseCharactersButton();

        // Names GridView
        nameEditText = findViewById(R.id.nameEditText);
        addNameButton = findViewById(R.id.addNameButton);
        namesGridView = findViewById(R.id.namesGridView);
        numberOfNamesTextView = findViewById(R.id.NumberofNames);

        namesList = new ArrayList<>();
        namesAdapter = new NamesAdapter();
        namesGridView.setAdapter(namesAdapter);

        AppManager.gameSetup = new GameSetup();

        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
                addName();
            }
        });

        int numberOfNames = namesList.size();
        numberOfNamesTextView.setText("Names Entered: " + numberOfNames);
    }

    // Character Select Screen Button
    private void configureChooseCharactersButton() {
        Button chooseCharactersButton = findViewById(R.id.ChooseCharactersButton);
        chooseCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namesList.size() > 0){
                    clickSound.start();
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
                    clickSound.start();
                    AppManager.gameSetup.SetUpRandomGame(names);
                    GameManager.StartNewGame(AppManager.gameSetup);
                    startActivity(new Intent(Start.this, MainMafiaPage.class));
                }
            }
        });
    }

    // BACK BUTTON
    private void configureBackToMainMenu() {
        Button backToMainMenuButton = findViewById(R.id.BackToMainMenu);
        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound.start();
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
        }
    }

    private class NamesAdapter extends BaseAdapter {

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
                    clickSound.start();
                    AppManager.gameSetup.names.remove(position);
                    namesList.remove(position);
                    namesAdapter.notifyDataSetChanged();

                    int numberOfNames = namesList.size();
                    numberOfNamesTextView.setText("Names Entered: " + numberOfNames);
                }
            });

            return button;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clickSound.release();
    }
}
