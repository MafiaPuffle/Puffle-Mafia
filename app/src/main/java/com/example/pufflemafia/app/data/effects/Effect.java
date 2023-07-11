package com.example.pufflemafia.app.data.effects;

import android.util.Log;

public class Effect {

    public enum EffectClearType {ON_NIGHT, NEVER}

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

    private EffectClearType clearType;
    public EffectClearType getClearType() {
        return clearType;
    }
    public void setClearType(EffectClearType clearType) {
        this.clearType = clearType;
    }

    public Effect(String name, Integer imageResource){
        setName(name);
        setImageResource(imageResource);
        setStartingEffect(false);
        setClearType(EffectClearType.ON_NIGHT);
    }

    public Effect(String name, Integer imageResource, boolean isStartingEffect){
        setName(name);
        setImageResource(imageResource);
        setStartingEffect(isStartingEffect);
        setClearType(EffectClearType.ON_NIGHT);
    }

    public void PrintSummary(){
        System.out.print(name + ", ");
    }

    public void LogSummary(){
        Log.d("CustomTestingListener", name + ", ");
    }
}
