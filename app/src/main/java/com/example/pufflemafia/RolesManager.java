package com.example.pufflemafia;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

// Handles all data and logic surrounding roles
public class RolesManager {
    // Stores all possible roles in the game
    private static Map<String, Role> allRoles;
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
        allRoles = new HashMap<String, Role>();
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
                "Alien",
                R.drawable.alien_puffle);
        allRoles.put(alien.getName(), alien);

        Role baker = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "give bread to",
                "Baker",
                R.drawable.baker_puffle);
        allRoles.put(baker.getName(), baker);

        Role cupid = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.FISTNIGHT,
                "link",
                "Cupid",
                R.drawable.cupid_puffle);
        allRoles.put(cupid.getName(), cupid);

        Role cyborg = new Role(0,
                Role.Alliances.NEUTRAL,
                Role.Teams.NEUTRAL,
                Power.PowerType.FISTNIGHT,
                "copy",
                "Cyborg",
                R.drawable.cyborg_puffle);
        allRoles.put(cyborg.getName(), cyborg);

        Role dentist = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "silence",
                "Dentist",
                R.drawable.dentist_puffle);
        allRoles.put(dentist.getName(), dentist);

        Role detective = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "know about",
                "Detective",
                R.drawable.dentist_puffle);
        allRoles.put(detective.getName(), detective);

        Role doctor = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "heal",
                "Doctor",
                R.drawable.doctor_puffle);
        allRoles.put(doctor.getName(), doctor);

        Role doggie = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Doggie",
                R.drawable.doggie_puffle);
        allRoles.put(doggie.getName(), doggie);

        Role theFather = new Role(1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "???",
                "The Father",
                R.drawable.the_father_puffle);
        allRoles.put(theFather.getName(), theFather);

        Role godfather = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                Power.PowerType.PASSIVE,
                "???",
                "Godfather",
                R.drawable.godfather_puffle);
        allRoles.put(godfather.getName(), godfather);

        Role holySpirit = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "???",
                "Holy Spirit",
                R.drawable.holy_spirit_puffle);
        allRoles.put(holySpirit.getName(), holySpirit);

        Role jackOfAllTrades = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.CONTINOUS,
                "???",
                "Jack-of-All-Trades",
                R.drawable.jack_of_all_trades);
        allRoles.put(jackOfAllTrades.getName(), jackOfAllTrades);

        Role jailKeeper = new Role(1.1f,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                Power.PowerType.CONTINOUS,
                "???",
                "Jailkeeper",
                R.drawable.jailkeeper_puffle);
        allRoles.put(jailKeeper.getName(), jailKeeper);

        Role jesus = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Jesus",
                R.drawable.jesus_puffle);
        allRoles.put(jesus.getName(), jesus);

        Role lawyer = new Role(3,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                Power.PowerType.CONTINOUS,
                "???",
                "Lawyer",
                R.drawable.lawyer_puffle);
        allRoles.put(lawyer.getName(), lawyer);

        Role lovers = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.FISTNIGHT,
                "???",
                "Lovers",
                R.drawable.lover_puffle);
        allRoles.put(lovers.getName(), lovers);

        Role mafia = new Role(2,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.CONTINOUS,
                "kill",
                "Mafia",
                R.drawable.mafia_puffle);
        allRoles.put(mafia.getName(), mafia);

        Role mafiaRival = new Role(2.1f,
                Role.Alliances.EVIL,
                Role.Teams.RIVAL_MAFIA,
                Power.PowerType.CONTINOUS,
                "???",
                "Mafia Rival",
                R.drawable.rival_puffle);
        allRoles.put(mafiaRival.getName(), mafiaRival);

        Role president = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.SELFACTIVE,
                "???",
                "President",
                R.drawable.president_puffle);
        allRoles.put(president.getName(), president);

        Role satan = new Role(0,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.ACTIVE,
                "???",
                "Satan",
                R.drawable.satan);
        allRoles.put(satan.getName(), satan);

        Role terrorist = new Role(0,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.FISTNIGHT,
                "plant a bomb on",
                "Terrorist",
                R.drawable.terrorist_puffle);
        allRoles.put(terrorist.getName(), terrorist);

        Role veteran = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Veteran",
                R.drawable.veteran_puffle);
        allRoles.put(veteran.getName(), veteran);

        Role villageIdiot = new Role(-1,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                Power.PowerType.PASSIVE,
                "???",
                "Village Idiot",
                R.drawable.village_idiot_puffle);
        allRoles.put(villageIdiot.getName(), villageIdiot);

        Role witness = new Role(2.1f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "did you see anything",
                "Witness",
                R.drawable.witness_puffle);
        allRoles.put(witness.getName(), witness);

        Role wizzard = new Role(4,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.ACTIVE,
                "switch with",
                "Wizard",
                R.drawable.wizard_puffle);
        allRoles.put(wizzard.getName(), wizzard);

        Role wizzardAfterSwitch = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.SELFACTIVE,
                "???",
                "Wizzard (After Switch)",
                R.drawable.wizard_puffle);
        allRoles.put(wizzardAfterSwitch.getName(), wizzardAfterSwitch);

        Role zombieGood = new Role(-1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "???",
                "Zombie Good",
                R.drawable.good_zombie_puffle);
        allRoles.put(zombieGood.getName(), zombieGood);

        Role zombieEvil = new Role(-1,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                Power.PowerType.PASSIVE,
                "???",
                "Zombie Evil",
                R.drawable.evil_zombie_puffle);
        allRoles.put(zombieEvil.getName(), zombieEvil);

        Role townsFolk = new Role(0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "hug",
                "Towns Folk",
                R.drawable.alien_puffle);
        allRoles.put(townsFolk.getName(), townsFolk);
    }
}
