package com.example.pufflemafia.app.data.effects;

import android.util.Log;

public class Effect {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private Integer imageResource;
    public void setImageResource(Integer imageResource) {
        this.imageResource = imageResource;
    }
    public Integer getImageResource() {
        return imageResource;
    }

    private boolean isStartingEffect;
    public void setStartingEffect(boolean startingEffect) {
        isStartingEffect = startingEffect;
    }
    public boolean isStartingEffect() {
        return isStartingEffect;
    }

    public Effect(String name, Integer imageResource){
        setName(name);
        setImageResource(imageResource);
        setStartingEffect(false);
    }

    public Effect(String name, Integer imageResource, boolean isStartingEffect){
        setName(name);
        setImageResource(imageResource);
        setStartingEffect(isStartingEffect);
    }

    public void PrintSummary(){
        System.out.print(name + ", ");
    }

    public void LogSummary(){
        Log.d("CustomTestingListener", name + ", ");
    }
}
