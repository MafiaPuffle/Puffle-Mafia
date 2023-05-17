package com.example.pufflemafia;

import java.util.Collections;


// Handles all the game logic
// Should be the root program for all game logic
public class GameManager {
    // Game State Properties
    enum GameState {MainMenu, RoleSelection, Day, Night}
    private static GameState currentState;
    public static GameState getCurrentState(){ return currentState; }

    // Night Properties
    private static int nightNumber;
    public static int getNightNumber(){ return nightNumber; }
    private Player currentPlayerActiveAtNight;
    public Player getCurrentPlayerActiveAtNight(){ return currentPlayerActiveAtNight; }

    // Winning Team Properties
    private Role.Teams winningTeam;
    public Role.Teams getWinningTeam(){return winningTeam;}

    // Managers
    public RolesManager rolesManager;
    public PlayerManager playerManager;

    // Events
    public Event<Integer> onStartDay;
    public Event<Integer> onStartNight;
    public Event<Role.Teams> onGameWon;

    // Initializes the managers for the game
    public GameManager(){
        this.rolesManager = new RolesManager();
        this.playerManager = new PlayerManager();
        currentState = GameState.MainMenu;
        this.winningTeam = Role.Teams.TOWN;
        currentPlayerActiveAtNight = new Player();
        this.onStartDay = new Event<Integer>();
        this.onStartNight = new Event<Integer>();
        this.onGameWon = new Event<Role.Teams>();
        this.currentPlayerActiveAtNight = new Player();
    }

    // Sets the current state to Role Selection
    public void GoToRoleSelectScreen(){
        currentState = GameState.RoleSelection;
    }

    // Once we have selected available roles and number of mafia we use this to set up a new game
    public void StartNewGame(int numberOfPlayers, int numberOfMafia){

        // Resets the night number
        nightNumber = 0;

        // Adds a bunch of empty players to the playerManager
        this.playerManager.NewGame(numberOfPlayers);

        // Mixes up the selected roles, so we get randomized roles
        Collections.shuffle(this.rolesManager.selectedRoles);

        // Assigns the roles to the players
        for(int i = 0; i < numberOfPlayers; ++i){
            Player player = this.playerManager.allAlive.get(i);
            if(i < numberOfMafia){
                // set player role as mafia
                player.setRole("Mafia");
            }
            else{
                // set player role as a selected role
                player.setRole(this.rolesManager.selectedRoles.get(i - numberOfMafia));
            }
            this.playerManager.allAlive.setElementAt(player, i);
        }

        currentState = GameState.Day;
    }

    // handle going from day to night
    public void StartNight(){

        nightNumber++;

        currentState = GameState.Night;
        this.onStartNight.Invoke();

        this.playerManager.StartNight();
    }

    // if we are in a night this updates currentActivePlayerAtNight
    //      or sets it to day if no more players are left
    public void GoToNextEventAtNight(){
        if(currentState != GameState.Night) return;

        currentPlayerActiveAtNight = playerManager.GetNextPlayerForNight();

        if (currentPlayerActiveAtNight == null) StartDay();
    }

    // handles logic for going to day
    //      Is called by GoToNextEventAtNight() when all players have gone
    public void StartDay(){

        currentState = GameState.Day;
        this.onStartDay.Invoke();

        CheckForWinningTeam();
    }

    // Handles killing a player
    //      Use this instead of the KillPlayer function on PlayerManager to handle CheckingForWinningTeam
    public void KillPlayer(Player player){
        this.playerManager.KillPlayer(player);
        CheckForWinningTeam();
    }

    // handles logic for seeing if a team has won
    //      Is called by StartDay()
    //      Triggers the event onTeamWon with the winning team
    private void CheckForWinningTeam(){
        // TODO: find out and write win logic for the following teams: SELF, RIVAL_MAFIA, NEUTRAL

        int numberOfMafiaAlive = 0;
        int numberOfTownAlive = 0;
        int numberOfSelfAlive = 0;
        int numberOfRivalAlive = 0;
        int numberOfNeutralAlive = 0;

        // Finds out how many of each team is alive
        for(int i = 0; i < playerManager.allAlive.size(); ++i){
            Role role = new Role();
            role.Copy(this.playerManager.allAlive.get(i).getRole());

            switch (role.getTeam()){
                case TOWN:
                    numberOfTownAlive++;
                    break;
                case MAFIA:
                    numberOfMafiaAlive++;
                    break;
                case SELF:
                    numberOfSelfAlive++;
                    break;
                case RIVAL_MAFIA:
                    numberOfRivalAlive++;
                    break;
                case NEUTRAL:
                    numberOfNeutralAlive++;
                    break;
            }
        }

        /*
        System.out.print("Number members of each team left:\n" +
                "Mafia " + numberOfMafiaAlive + "\n" +
                "Town " + numberOfTownAlive + "\n" +
                "Self " + numberOfSelfAlive + "\n" +
                "Rival " + numberOfRivalAlive + "\n" +
                "Neutral " + numberOfNeutralAlive + "\n");
         */

        // Town: are all MAFIA dead
        if(numberOfMafiaAlive == 0){
            this.winningTeam = Role.Teams.TOWN;
            this.onGameWon.Invoke(this.winningTeam);
            return;
        }


        // MAFIA: are they equal to 50% or more of the alive players
        else if(numberOfMafiaAlive >= (numberOfTownAlive + numberOfSelfAlive + numberOfNeutralAlive)){
            this.winningTeam = Role.Teams.MAFIA;
            this.onGameWon.Invoke(this.winningTeam);
            return;
        }

        // SELF: ???

        // RIVAL_MAFIA: ???

        // NEUTRAL: ???
    }
}
