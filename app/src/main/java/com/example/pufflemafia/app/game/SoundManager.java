package com.example.pufflemafia.app.game;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.AppMinimizedWatcher;
import com.example.pufflemafia.app.events.IEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SoundManager {

    private static Map<String, MediaPlayer> songs;
    private static Map<String, MediaPlayer> sfxSounds;

    private static Timer fadeInTimer;
    private static Timer fadeOutTimer;

    private static float musicVolume;
    private static float sfxVolume;

    public static boolean isMusicMuted(){
        if(musicVolume == 0) return true;
        else return false;
    }

    public static boolean isSFXMuted(){
        if(sfxVolume == 0) return true;
        else return false;
    }

    private static SoundManager instance;

    private SoundManager(){
    }

    public static void initialize(Context context){
        fadeInTimer = new Timer();
        fadeOutTimer = new Timer();
        initializeSongs(context);
        initializeSFX(context);

        musicVolume = 1;
        sfxVolume = 1;

        instance = new SoundManager();
        AppMinimizedWatcher.onAppMinimize.AddListener(new IEventListener<Boolean>() {
            @Override
            public void Response(Boolean aBoolean) {
                if(aBoolean){
                    // we minimized
                    onAppMinimize();
                }
                else{
                    // we maximized
                    onAppMaximize();
                }
            }
        });
    }

    private static void initializeSongs(Context context){
        songs = new HashMap<String, MediaPlayer>();
        songs.put("Mystery",MediaPlayer.create(context, R.raw.mystery));
    }

    private static void initializeSFX(Context context){
        sfxSounds = new HashMap<String, MediaPlayer>();
        sfxSounds.put("Click",MediaPlayer.create(context, R.raw.click_sound));
    }

    public static void setMusicVolume(float volume){
        if(volume > 1.0) volume = 1.0f;
        if(volume < 0) volume = 0;
        musicVolume = volume;
        updateAllMusicVolume(musicVolume);
    }

    private static void updateAllMusicVolume(float volume){
        for (Map.Entry<String, MediaPlayer> entry: songs.entrySet()) {
            entry.getValue().setVolume(volume,volume);
        }
    }

    public static void muteMusic(){
        setMusicVolume(0);

        fadeInTimer.cancel();
        fadeOutTimer.cancel();
    }

    public static void setSfxVolume(float volume){
        if(volume > 1.0) volume = 1.0f;
        if(volume < 0) volume = 0;
        sfxVolume = volume;
        updateAllSfxVolume(sfxVolume);
    }

    private static void updateAllSfxVolume(float volume){
        for (Map.Entry<String, MediaPlayer> entry: sfxSounds.entrySet()) {
            entry.getValue().setVolume(volume,volume);
        }
    }

    public static void muteSFX(){
        setSfxVolume(0);

        fadeInTimer.cancel();
        fadeOutTimer.cancel();
    }

    public static void playSfx(String name){
        if(sfxSounds.containsKey(name)){
            sfxSounds.get(name).start();
        }
        else {
            Log.w("SoundManager","Attempted to play sfx sound that does not exist: " + name);
        }
    }

    public static void playSong(String name){
        if(songs.containsKey(name)){
            MediaPlayer mediaPlayer = songs.get(name);
            mediaPlayer.setLooping(true);
            fadeIn(mediaPlayer, 1, 4000, 100);
        }
        else {
            Log.w("SoundManager","Attempted to play song that does not exist: " + name);
        }
    }

    public static void stopSong(String name){
        if(songs.containsKey(name)){
            MediaPlayer mediaPlayer = songs.get(name);
            if(mediaPlayer.isPlaying()){
                fadeOut(mediaPlayer, 1, 3000, 100);
            }
        }
        else {
            Log.w("SoundManager","Attempted to play song that does not exist: " + name);
        }
    }

    public static void stopSongSuddenly(String name){
        if(songs.containsKey(name)){
            MediaPlayer mediaPlayer = songs.get(name);
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
        }
        else {
            Log.w("SoundManager","Attempted to play song that does not exist: " + name);
        }
    }

    private static void fadeIn(MediaPlayer mediaPlayer, float maxVolume, int fadeDuration, int fadeInterval){

        mediaPlayer.setVolume(0, 0); // Start with volume set to 0

        // If not already playing, start playing
        if(!mediaPlayer.isPlaying()) mediaPlayer.start();


        // Gradually increase the volume in small steps over time
        TimerTask timerTask = new TimerTask() {
            private float volume = 0; // Initial volume level

            @Override
            public void run() {
                float delta = maxVolume / (fadeDuration / fadeInterval);
                volume += delta;

                if (volume >= maxVolume) {
                    volume = maxVolume;
                    mediaPlayer.setVolume(volume, volume); // Set final volume
                    fadeInTimer.cancel();
                } else {
                    mediaPlayer.setVolume(volume, volume); // Set current volume
                }
            }
        };

        fadeInTimer.schedule(timerTask, fadeInterval, fadeInterval);

    }

    private static void fadeOut(MediaPlayer mediaPlayer, float initialVolume, int fadeDuration, int fadeInterval){

        // Gradually decrease the volume in small steps over time
        TimerTask timerTask = new TimerTask() {
            private float volume = initialVolume; // Initial volume level

            @Override
            public void run() {
                float delta = initialVolume / (fadeDuration / fadeInterval);
                volume -= delta;

                if (volume <= 0) {
                    volume = 0;
                    mediaPlayer.setVolume(volume, volume); // Set final volume
                    mediaPlayer.stop(); // Stop playback after fade-out
                    fadeOutTimer.cancel();
                } else {
                    mediaPlayer.setVolume(volume, volume); // Set current volume
                }
            }
        };

        fadeOutTimer.schedule(timerTask, fadeInterval, fadeInterval);

    }

    private static void onAppMinimize(){
        updateAllSfxVolume(0);
        updateAllMusicVolume(0);

        fadeOutTimer.cancel();
        fadeInTimer.cancel();
    }

    private static void onAppMaximize(){
        updateAllSfxVolume(sfxVolume);
        updateAllMusicVolume(musicVolume);
    }
}
