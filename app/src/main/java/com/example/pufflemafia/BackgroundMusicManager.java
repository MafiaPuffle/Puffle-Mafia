package com.example.pufflemafia;
import android.content.Context;
import android.media.MediaPlayer;
public class BackgroundMusicManager {


    private static MediaPlayer mediaPlayer;

    public static void start(Context context, int musicResId) {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, musicResId);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }

    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
