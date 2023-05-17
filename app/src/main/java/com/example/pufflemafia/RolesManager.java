package com.example.pufflemafia;

import java.util.Dictionary;
import java.util.Vector;

// Handles all data and logic surrounding roles
public class RolesManager {
    // Stores all possible roles in the game
    private static Dictionary<String, Role> allRoles;
    public static Role getRoleFromAllRoles(String name) { return allRoles.get(name); }
    public static boolean DoesRoleExistInThisApp(String name)
    {
        return allRoles.get(name) != null;
    }

    // Stores all selected roles for the current game
    public Vector<Role> selectedRoles;

    // Selects a role to be used in the next game
    public void SelectRole(String name){
        selectedRoles.add(getRoleFromAllRoles(name));
    }

    public RolesManager(){
        this.LoadAllRoles();
    }

    // Loads all possible roles into the dictionary allRoles
    // Might want to store all the roles somewhere else later
    private void LoadAllRoles(){
        //all mafia roles here
        Role alien = new Role(3,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                Power.PowerType.CONTINOUS,
                "infect",
                "Alien");
        allRoles.put(alien.getName(), alien);

        Role baker = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "give bread to",
                "Baker");
        allRoles.put(baker.getName(), baker);

        Role cupid = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.FISTNIGHT,
                "link",
                "Cupid");
        allRoles.put(cupid.getName(), cupid);

        Role cyborg = new Role(0,
                Role.Alliances.NEUTRAL,
                Role.Teams.NEUTRAL,
                Power.PowerType.FISTNIGHT,
                "copy",
                "Cyborg");
        allRoles.put(cyborg.getName(), cyborg);

        Role dentist = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "silence",
                "Dentist");
        allRoles.put(dentist.getName(), dentist);

        Role detective = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "know about",
                "Detective");
        allRoles.put(detective.getName(), detective);

        Role doctor = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "heal",
                "Doctor");
        allRoles.put(doctor.getName(), doctor);

        Role doggie = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Doggie");
        allRoles.put(doggie.getName(), doggie);

        Role theFather = new Role(1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "???",
                "The Father");
        allRoles.put(theFather.getName(), theFather);

        Role godfather = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                Power.PowerType.PASSIVE,
                "???",
                "Godfather");
        allRoles.put(godfather.getName(), godfather);

        Role holySpirit = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "???",
                "Holy Spirit");
        allRoles.put(holySpirit.getName(), holySpirit);

        Role jackOfAllTrades = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "???",
                "Jack-of-All-Trades");
        allRoles.put(jackOfAllTrades.getName(), jackOfAllTrades);

        Role jailKeeper = new Role(1.1f,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                Power.PowerType.CONTINOUS,
                "???",
                "Jailkeeper");
        allRoles.put(jailKeeper.getName(), jailKeeper);

        Role jesus = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Jesus");
        allRoles.put(jesus.getName(), jesus);

        Role lawyer = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                Power.PowerType.CONTINOUS,
                "???",
                "Lawyer");
        allRoles.put(lawyer.getName(), lawyer);

        Role lovers = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.FISTNIGHT,
                "???",
                "Lovers");
        allRoles.put(lovers.getName(), lovers);

        Role mafia = new Role(2,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.CONTINOUS,
                "kill",
                "Mafia");
        allRoles.put(mafia.getName(), mafia);

        Role mafiaRival = new Role(2.1f,
                Role.Alliances.EVIL,
                Role.Teams.RIVAL_MAFIA,
                Power.PowerType.CONTINOUS,
                "???",
                "Mafia Rival");
        allRoles.put(mafiaRival.getName(), mafiaRival);

        Role president = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.SELFACTIVE,
                "???",
                "President");
        allRoles.put(president.getName(), president);

        Role satan = new Role(0,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.ACTIVE,
                "???",
                "Satan");
        allRoles.put(satan.getName(), satan);

        Role terrorist = new Role(0,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.FISTNIGHT,
                "plant a bomb on",
                "Terrorist");
        allRoles.put(terrorist.getName(), terrorist);

        Role veteran = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Veteran");
        allRoles.put(veteran.getName(), veteran);

        Role villageIdiot = new Role(-1,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                Power.PowerType.PASSIVE,
                "???",
                "Village Idiot");
        allRoles.put(villageIdiot.getName(), villageIdiot);

        Role witness = new Role(2.1f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "did you see anything",
                "Witness");
        allRoles.put(witness.getName(), witness);

        Role wizzard = new Role(4,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "switch with",
                "Wizard");
        allRoles.put(wizzard.getName(), wizzard);

        Role wizzardAfterSwitch = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.SELFACTIVE,
                "???",
                "Wizzard (After Switch)");
        allRoles.put(wizzardAfterSwitch.getName(), wizzardAfterSwitch);

        Role zombieGood = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Zombie Good");
        allRoles.put(zombieGood.getName(), zombieGood);

        Role zombieEvil = new Role(-1,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.PASSIVE,
                "???",
                "Zombie Evil");
        allRoles.put(zombieEvil.getName(), zombieEvil);

        Role townsFolk = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "hug",
                "Towns Folk");
        allRoles.put(townsFolk.getName(), townsFolk);
    }
}
