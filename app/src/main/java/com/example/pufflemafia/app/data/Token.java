package com.example.pufflemafia.app.data;

import androidx.annotation.NonNull;

public class Token {
    public enum TokenTypes {CLEAR_ON_NIGHT, CLEAR_ON_DEATH, CLEAR_NEVER}

    private String name;
    public String getName(){return name;}
    private String description;
    public String getDescription(){return description;}

    private int imageResource;
    public int getImageResource() {
        return imageResource;
    }
    private TokenTypes type;
    public TokenTypes getType(){ return type; }
    private int maxUsableAtNight;

    public int getMaxUsableAtNight() {
        return maxUsableAtNight;
    }

    public Token(String name, String description, int imageResource, TokenTypes type, int maxUsableAtNight){
        this.name = name;
        this.description = getDescription();
        this.imageResource = imageResource;
        this.type = type;
        this.maxUsableAtNight = maxUsableAtNight;
    }

    public Token(@NonNull Token other){
        this.name = other.getName();
        this.description = other.getDescription();
        this.imageResource = other.getImageResource();
        this.type = other.getType();
        this.maxUsableAtNight = other.getMaxUsableAtNight();
    }

    public void Copy(@NonNull Token other){
        this.name = other.getName();
        this.description = other.getDescription();
        this.imageResource = other.getImageResource();
        this.type = other.getType();
        this.maxUsableAtNight = other.getMaxUsableAtNight();
    }

    public void PrintSummary(){
        System.out.print("Token: " + this.name + "\n");
    }

    public void PrintSummary(String spacer){
        System.out.print(spacer + "Token: " + this.name + "\n");
    }

    public void PrintDetailed(){
        System.out.print("Token\n");
        System.out.print("    Name: " + this.name + "\n"
                        + "     ImageResource: " + this.imageResource + "\n"
                        + "     Type: " + this.type + "\n"
                        + "     Max Usable At Night: " + this.getMaxUsableAtNight());
    }

    public void PrintDetailed(String spacer){
        System.out.print(spacer + "Token\n");
        System.out.print(spacer + "    Name: " + this.name + "\n"
                + spacer + "    ImageResource: " + this.imageResource + "\n"
                + spacer + "    Type: " + this.type + "\n"
                + spacer + "     Max Usable At Night: " + this.getMaxUsableAtNight());
    }
}
