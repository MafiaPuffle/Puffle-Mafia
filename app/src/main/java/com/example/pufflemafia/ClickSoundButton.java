package com.example.pufflemafia;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

public class ClickSoundButton extends AppCompatButton implements View.OnClickListener {

    private MediaPlayer clickSound;

    public ClickSoundButton(Context context) {
        super(context);
        init();
    }

    public ClickSoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClickSoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        clickSound = MediaPlayer.create(getContext(), R.raw.click_sound);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        clickSound.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        clickSound.release();
    }
}
