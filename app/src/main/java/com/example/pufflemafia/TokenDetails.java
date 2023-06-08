package com.example.pufflemafia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pufflemafia.app.CustomAppCompatActivityWrapper;
import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.SoundManager;

public class TokenDetails extends CustomAppCompatActivityWrapper {

    private Intent intent;

    private ImageView tokenImageView;
    private TextView tokenNameTextView;
    private TextView tokenDescriptionTextView;
    private Token token;
    private String tokenName;
    private int tokenImageResource;
    private String tokenDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_details);

        intent = getIntent();

        tokenNameTextView = findViewById(R.id.TokenRoleName);
        tokenImageView = findViewById(R.id.TokenRoleImage);
        tokenDescriptionTextView = findViewById(R.id.TokenAbilityEffect);

        tokenName = intent.getStringExtra("name");
        token = DataManager.GetToken(tokenName);
        tokenImageResource = token.getImageResource();
        tokenDescription = token.getDescription();

        tokenNameTextView.setText(tokenName);
        tokenDescriptionTextView.setText(tokenDescription);
//        tokenImageView.setBackgroundResource(tokenImageResource);
        tokenImageView.setImageResource(tokenImageResource);

        Log.d("TokenDetails", "Token Description: " + tokenDescription);

        configureBackButton();
    }

    private void configureBackButton(){
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
