package com.example.pufflemafia.app.data;

import androidx.annotation.NonNull;

public class Token {
    enum TokenTypes {CLEAR_ON_NIGHT, CLEAR_ON_DEATH, CLEAR_NEVER}

    private String name;
    public String getName(){return name;}

    private int imageResource;
    public int getImageResource() {
        return imageResource;
    }
    private TokenTypes type;
    public TokenTypes getType(){ return type; }

    public Token(String name, int imageResource, TokenTypes type){
        this.name = name;
        this.imageResource = imageResource;
        this.type = type;
    }

    public Token(@NonNull Token other){
        this.name = other.getName();
        this.imageResource = other.getImageResource();
        this.type = other.getType();
    }

    public void Copy(@NonNull Token other){
        this.name = other.getName();
        this.imageResource = other.getImageResource();
        this.type = other.getType();
    }
}
