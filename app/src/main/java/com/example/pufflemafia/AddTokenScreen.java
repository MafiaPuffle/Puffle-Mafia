package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class AddTokenScreen extends AppCompatActivity {

    private Intent intent;
    private GridLayout gridLayout;
    private LinearLayout linearLayout;
    private Token newToken;
    private Vector<Token> allTokens;
    private Vector<Token> selectedTokens;
    private PlayerManager.PlayerMangerListType listType;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token_screen);

        intent = getIntent();

        position = intent.getIntExtra("position",0);
        listType = (PlayerManager.PlayerMangerListType) intent.getSerializableExtra("ListType");

        selectedTokens = new Vector<Token>();

        allTokens = DataManager.GetAllTokens();
        if(listType == PlayerManager.PlayerMangerListType.ALIVE){
            for (int i = 0; i < PlayerManager.allAlive.get(position).getAllTokensOnPlayer().size(); i++) {
                selectedTokens.add(PlayerManager.allAlive.get(position).getTokenOnPlayer(i));
            }
        }
        else {
            for (int i = 0; i < PlayerManager.allDead.get(position).getAllTokensOnPlayer().size(); i++) {
                selectedTokens.add(PlayerManager.allDead.get(position).getTokenOnPlayer(i));
            }
        }

        gridLayout = findViewById(R.id.AddingTokenBox);
        linearLayout = findViewById(R.id.AddTokenBox);

        for (Token token: selectedTokens){
            ImageButton imageButton = addImageButtonToLinearLayout(linearLayout, token.getImageResource());
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    linearLayout.removeView(view);
                    selectedTokens.remove(token);
                }
            });
        }

        for (Token token: allTokens) {
            ImageButton imageButton = addImageButtonToGrid(gridLayout, token.getImageResource());
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageButton imageButton1 = addImageButtonToLinearLayout(linearLayout, token.getImageResource());
                    selectedTokens.add(token);

                    imageButton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            linearLayout.removeView(view);
                            selectedTokens.remove(token);
                        }
                    });
                }
            });
        }

        configureNextButton();
        configureBackToMainMenu();
    }

    private ImageButton addImageButtonToGrid(GridLayout gridLayout, int drawableId) {
        Log.d("CharacterSelectScreen", "Adding image button to grid");
        ImageButton imageButton = new ImageButton(this);
        imageButton.setBackgroundResource(drawableId); // Set the image as the background
        imageButton.setImageResource(0); // Remove the image source

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        imageButton.setLayoutParams(params);
        gridLayout.addView(imageButton);

        return imageButton;
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    private ImageButton addImageButtonToLinearLayout(LinearLayout linearLayout, int drawableId){
        ImageButton imageButton = new ImageButton(this);
        imageButton.setBackgroundResource(drawableId);
        imageButton.setImageResource(0);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(30), dpToPx(30));
        imageButton.setLayoutParams(params);

        linearLayout.addView(imageButton);

        return imageButton;
    }


    private void configureNextButton(){
        Button NextButton = (Button) findViewById(R.id.DoneChangingCharactersButton);
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddTokenScreen", "" + selectedTokens.size());
                PlayerManager.RemovePlayerAllToken(listType, position);
                for (int i = 0; i < selectedTokens.size(); i++) {
                    PlayerManager.AddTokenToPlayer(listType, position, selectedTokens.get(i));
                }
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
                finish();
            }
        });
    }

}