package com.example.pufflemafia;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.GameSetup;
import com.example.pufflemafia.app.game.GameManager;

public class Start extends AppCompatActivity {
    private EditText nameEditText;
    private Button addNameButton;
    private GridView namesGridView;
    private ArrayList<String> namesList;
    private NamesAdapter namesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        // Configure Buttons
        configureBackToMainMenu();
        configureChooseCharactersButton();


        // Names GridView
        nameEditText = findViewById(R.id.nameEditText);
        addNameButton = findViewById(R.id.addNameButton);
        namesGridView = findViewById(R.id.namesGridView);


        namesList = new ArrayList<>();
        namesAdapter = new NamesAdapter();
        namesGridView.setAdapter(namesAdapter);

        AppManager.gameSetup = new GameSetup();

        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addName();
            }
        });
    }

    // Character Select Screen Button
    private void configureChooseCharactersButton() {
        Button chooseCharactersButton = findViewById(R.id.ChooseCharactersButton);
        chooseCharactersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Start.this, CharacterSelectScreen.class));
            }
        });
    }

    // BACK BUTTON
    private void configureBackToMainMenu() {
        Button backToMainMenuButton = findViewById(R.id.BackToMainMenu);
        backToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    AppManager.gameSetup.names.remove(position);
                    namesList.remove(position);
                    namesAdapter.notifyDataSetChanged();
                }
            });

            return button;
        }
    }
}
