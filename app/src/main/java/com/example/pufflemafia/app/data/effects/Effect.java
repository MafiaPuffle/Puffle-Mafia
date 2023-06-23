package com.example.pufflemafia.app.data.effects;

public class Effect {

    private boolean isStartingEffect;
    public void setStartingEffect(boolean startingEffect) {
        isStartingEffect = startingEffect;
    }
    public boolean isStartingEffect() {
        return isStartingEffect;
    }

    public Effect(){
        setStartingEffect(false);
    }

    public Effect(boolean isStartingEffect){
        setStartingEffect(isStartingEffect);
    }
}
