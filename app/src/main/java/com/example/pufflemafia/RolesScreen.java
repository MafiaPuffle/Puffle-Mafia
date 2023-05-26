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

import com.example.pufflemafia.app.data.DataManager;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;


public class RolesScreen extends AppCompatActivity {

    private Vector<Role> allRoles;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);

        gridLayout = findViewById(R.id.AddingTokenBox);

        allRoles = DataManager.GetAllRoles();

        for(Role role: allRoles){
            ImageButton imageButton = addImageButtonToGrid(gridLayout, role.getImageResource());

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RolesScreen.this, RoleDetails.class);
                    intent.putExtra("name", role.getName());
                    intent.putExtra("imageResourceId", role.getImageResource());
                    intent.putExtra("description", role.getDescription());
                    startActivity(intent);
                }
            });
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
        Button BackToMainMenuButton = (Button) findViewById(R.id.BackToMainMenu);
        BackToMainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}