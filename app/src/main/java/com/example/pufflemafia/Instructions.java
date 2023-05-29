package com.example.pufflemafia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pufflemafia.app.game.SoundManager;

public class Instructions extends AppCompatActivity {

    private ViewPager viewPager;
    private ImagePagerAdapter imagePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        viewPager = findViewById(R.id.viewPager);
        imagePagerAdapter = new ImagePagerAdapter(this);
        viewPager.setAdapter(imagePagerAdapter);
    }

    private void configureBackButton() {
        Button startButton = findViewById(R.id.BackButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundManager.playSfx("Click");
                finish();
            }
        });
    }
}
