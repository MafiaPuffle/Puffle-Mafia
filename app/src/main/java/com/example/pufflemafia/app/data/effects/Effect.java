package com.example.pufflemafia.app.data.effects;

public class Effect {

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private boolean isStartingEffect;
    public void setStartingEffect(boolean startingEffect) {
        isStartingEffect = startingEffect;
    }
    public boolean isStartingEffect() {
        return isStartingEffect;
    }

    public Effect(String name){
        setName(name);
        setStartingEffect(false);
    }

    public Effect(String name, boolean isStartingEffect){
        setName(name);
        setStartingEffect(isStartingEffect);
    }

    public void PrintSummary(){
        System.out.print(name + ", ");
    }
}
