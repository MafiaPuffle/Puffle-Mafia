package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Token;

import java.util.Vector;

public class AddTokenScreen extends AppCompatActivity {

    private GridLayout gridLayout;
    private Token newToken;
    private Vector<Token> allTokens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_token_screen);

        allTokens = DataManager.GetAllTokens();

        gridLayout = findViewById(R.id.AddingTokenBox);

        for (Token token: allTokens) {
            addImageButtonToGrid(gridLayout, token.getImageResource());
        }

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