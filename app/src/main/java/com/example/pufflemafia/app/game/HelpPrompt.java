package com.example.pufflemafia.app.game;

public class HelpPrompt {
    private int pointsTo;
    public int getPointsTo(){return pointsTo;}
    private String prompt;
    public String getPrompt(){return prompt;}

    public HelpPrompt(int pointsTo, String prompt){
        this.pointsTo = pointsTo;
        this.prompt = prompt;
    }

}
