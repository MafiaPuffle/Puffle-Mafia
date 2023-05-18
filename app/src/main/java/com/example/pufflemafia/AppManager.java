package com.example.pufflemafia;

import com.example.pufflemafia.data.DataManager;

public class AppManager {
    public GameManager gameManager;
    public DataManager dataManager;
    public ScreenManager screenManager;

    public AppManager(){this.Initialize();}

    private void Initialize(){
        this.gameManager = new GameManager();
        this.dataManager = new DataManager();
        this.screenManager = new ScreenManager();
    }
}
