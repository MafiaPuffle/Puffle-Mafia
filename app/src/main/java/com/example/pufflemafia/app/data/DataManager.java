package com.example.pufflemafia.app.data;

import com.example.pufflemafia.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class DataManager {
    public static Map<String, Token> allTokens;
    public static Map<String, Power> allPowers;
    public static Map<String, Role> allRoles;

    public DataManager(){
        InitializeAllDictionaries();
    }

    private static void InitializeAllDictionaries(){
        InitializeAllTokens();
        InitializeAllPowers();
        InitializeAllRoles();
    }

    private static void InitializeAllTokens(){
        allTokens = new HashMap<String, Token>();

        Token alien = new Token(
                "Alien",
                R.drawable.alien_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(alien.getName(), alien);

        Token baker = new Token(
                "Baker",
                R.drawable.puffle_o_croissant,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(baker.getName(), baker);

        Token cupid = new Token(
                "Cupid",
                R.drawable.cupid_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(cupid.getName(), cupid);

        Token cyborg = new Token(
                "Cyborg",
                R.drawable.cyborg_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(cyborg.getName(), cyborg);

        Token dentist = new Token(
                "Dentist",
                R.drawable.dentist_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(dentist.getName(), dentist);

        Token detective = new Token(
                "Detective",
                R.drawable.detective_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(detective.getName(), detective);

        Token doctor = new Token(
                "Doctor",
                R.drawable.doctor_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(doctor.getName(), doctor);

        Token theFather = new Token(
                "The Father",
                R.drawable.the_father_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(theFather.getName(), theFather);

        Token holySpirit = new Token(
                "Holy Spirit",
                R.drawable.holy_spirit_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(holySpirit.getName(), holySpirit);

        Token jackOfAllTrades = new Token(
                "J.O.A.T.",
                R.drawable.jack_of_all_trades,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(jackOfAllTrades.getName(), jackOfAllTrades);

        Token jailKeeper = new Token(
                "Jailkeeper",
                R.drawable.jailkeeper_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(jailKeeper.getName(), jailKeeper);

        Token lawyer = new Token(
                "Lawyer",
                R.drawable.lawyer_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(lawyer.getName(), lawyer);

        Token lovers = new Token(
                "Lovers",
                R.drawable.lover_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(lovers.getName(), lovers);

        Token mafia = new Token(
                "Mafia",
                R.drawable.mafia_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(mafia.getName(), mafia);

        Token mafiaRival = new Token(
                "Mafia Rival",
                R.drawable.rival_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(mafiaRival.getName(), mafiaRival);

        Token satan = new Token(
                "Satan",
                R.drawable.satan,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(satan.getName(), satan);

        Token terrorist = new Token(
                "Terrorist",
                R.drawable.terrorist_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(terrorist.getName(), terrorist);

        Token witness = new Token(
                "Witness",
                R.drawable.witness_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(witness.getName(), witness);

        Token wizard = new Token(
                "Wizard",
                R.drawable.wizard_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(wizard.getName(), wizard);

    }

    private static void InitializeAllPowers(){
        allPowers = new HashMap<String, Power>();

        Power alien = new Power("Alien",
                Power.PowerType.CONTINOUS,
                "INFECT",
                GetToken("Alien"));
        allPowers.put(alien.getName(), alien);

        Power baker = new Power("Baker",
                Power.PowerType.CONTINOUS,
                "GIVE BREAD TO",
                GetToken("Baker"));
        allPowers.put(baker.getName(), baker);

        Power civilian = new Power("Civilian",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(civilian.getName(), civilian);

        Power cupid = new Power(
                "Cupid",
                Power.PowerType.FIRSTNIGHT,
                "FALL IN LOVE",
                GetToken("Cupid"));
        allPowers.put(cupid.getName(), cupid);

        Power cyborg = new Power("Cyborg",
                Power.PowerType.FIRSTNIGHT,
                "COPY",
                GetToken("Cyborg"));
        allPowers.put(cyborg.getName(), cyborg);

        Power dentist = new Power("Dentist",
                Power.PowerType.CONTINOUS,
                "SILENCE",
                GetToken("Dentist"));
        allPowers.put(dentist.getName(), dentist);

        Power detective = new Power(
                "Detective",
                Power.PowerType.CONTINOUS,
                "KNOW ABOUT",
                GetToken("Detective"));
        allPowers.put(detective.getName(), detective);

        Power doctor = new Power(
                "Doctor",
                Power.PowerType.CONTINOUS,
                "SAVE",
                GetToken("Doctor"));
        allPowers.put(doctor.getName(), doctor);

        Power doggie = new Power(
                "Doggie",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(doggie.getName(), doggie);

        Power theFather = new Power("The Father",
                Power.PowerType.ONETIMEUSE,
                "");
//                GetToken("The Father"));
        allPowers.put(theFather.getName(), theFather);

        Power godFather = new Power("GodFather",
                Power.PowerType.PASSIVE,
                "MURDER");
        allPowers.put(godFather.getName(), godFather);

        Power holySpirit = new Power("Holy Spirit",
                Power.PowerType.ONETIMEUSE,
                "DOUBLE GOOD POWERS");
//                GetToken("Holy Spirit"));
        allPowers.put(holySpirit.getName(), holySpirit);

        Power jackOfAllTrades = new Power("J.O.A.T.",
                Power.PowerType.CONTINOUS,
                "ACTIVATE ON",
                GetToken("J.O.A.T."));
        allPowers.put(jackOfAllTrades.getName(), jackOfAllTrades);

        Power jailKeeper = new Power("Jailkeeper",
                Power.PowerType.CONTINOUS,
                "PUT IN JAIL",
                GetToken("Jailkeeper"));
        allPowers.put(jailKeeper.getName(), jailKeeper);

        Power jesus = new Power("Jesus",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(jesus.getName(), jesus);

        Power lawyer = new Power("Lawyer",
                Power.PowerType.CONTINOUS,
                "DEFEND",
                GetToken("Lawyer"));
        allPowers.put(lawyer.getName(), lawyer);

        Power lovers = new Power(
                "Lovers",
                Power.PowerType.FIRSTNIGHT,
                "");
        allPowers.put(lovers.getName(), lovers);

        Power mafia = new Power(
                "Mafia",
                Power.PowerType.CONTINOUS,
                "MURDER",
                GetToken("Mafia"));
        allPowers.put(mafia.getName(), mafia);

        Power rivalMafia = new Power("Mafia Rival",
                Power.PowerType.CONTINOUS,
                "MURDER",
                GetToken("Mafia Rival"));
        allPowers.put(rivalMafia.getName(), rivalMafia);

        Power president = new Power(
                "President",
                Power.PowerType.SELFACTIVE,
                "");
        allPowers.put(president.getName(), president);

        Power satan = new Power("Satan",
                Power.PowerType.ONETIMEUSE,
                "DOUBLE EVIL POWERS");
//                GetToken("Satan"));
        allPowers.put(satan.getName(), satan);

        Power terrorist = new Power(
                "Terrorist",
                Power.PowerType.FIRSTNIGHT,
                "PLANT A BOMB ON",
                GetToken("Terrorist"));
        allPowers.put(terrorist.getName(), terrorist);

        Power veteran = new Power("Veteran",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(veteran.getName(), veteran);

        Power villageIdiot = new Power(
                "Village Idiot",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(villageIdiot.getName(), villageIdiot);

        Power witness = new Power("Witness",
                Power.PowerType.ONETIMEUSE,
                "WITNESS",
                GetToken("Witness"));
        allPowers.put(witness.getName(), witness);

        Power wizard = new Power("Wizard",
                Power.PowerType.ONETIMEUSE,
                "SWITCH WITH",
                GetToken("Wizard"));
        allPowers.put(wizard.getName(), wizard);

//        Power wizardAfter = new Power("Wizard after Switch",
//                Power.PowerType.SELFACTIVE,
//                "");
//        allPowers.put(wizardAfter.getName(), wizardAfter);

        Power zombieGood = new Power("Zombie Good",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(zombieGood.getName(), zombieGood);

        Power zombieBad = new Power("Zombie Evil",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(zombieBad.getName(), zombieBad);
    }

    private static void InitializeAllRoles(){
        allRoles = new HashMap<String, Role>();

        Role alien = new Role("Alien",
                R.drawable.alien_puffle,
                3,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                GetPower("Alien"),
                "Does something amazing!");
        allRoles.put(alien.getName(), alien);

        Role baker = new Role("Baker",
                R.drawable.baker_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Baker"),
                "Does something amazing!");
        allRoles.put(baker.getName(), baker);

        Role civilian = new Role("Civilian",
                R.drawable.civilian_puffles,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Civilian"),
                "Does something amazing!");
        allRoles.put(civilian.getName(), civilian);

        Role cupid = new Role("Cupid",
                R.drawable.cupid_puffle,
                .5f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Cupid"),
                "Does something amazing!");
        allRoles.put(cupid.getName(), cupid);

        Role cyborg = new Role("Cyborg",
                R.drawable.cyborg_puffle,
                0,
                Role.Alliances.NEUTRAL,
                Role.Teams.NEUTRAL,
                GetPower("Cyborg"),
                "Does something amazing!");
        allRoles.put(cyborg.getName(), cyborg);

        Role dentist = new Role("Dentist",
                R.drawable.dentist_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Dentist"),
                "Does something amazing!");
        allRoles.put(dentist.getName(), dentist);

        Role detective = new Role("Detective",
                R.drawable.detective_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Detective"),
                "Does something amazing!");
        allRoles.put(detective.getName(), detective);

        Role doctor = new Role("Doctor",
                R.drawable.doctor_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Doctor"),
                "Does something amazing!");
        allRoles.put(doctor.getName(), doctor);

        Role doggie = new Role("Doggie",
                R.drawable.doggie_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Doggie"),
                "Does something amazing!");
        allRoles.put(doggie.getName(), doggie);

        Role theFather = new Role("The Father",
                R.drawable.the_father_puffle,
                4,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("The Father"),
                "Does something amazing!");
        allRoles.put(theFather.getName(), theFather);

        Role Godfather = new Role("GodFather",
                R.drawable.godfather_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                GetPower("GodFather"),
                "Does something amazing!");
        allRoles.put(Godfather.getName(), Godfather);

        Role holySpirit = new Role("Holy Spirit",
                R.drawable.holy_spirit_puffle,
                0.75f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Holy Spirit"),
                "Does something amazing!");
        allRoles.put(holySpirit.getName(), holySpirit);

        Role jackOfAllTrades = new Role("J.O.A.T.",
                R.drawable.jack_of_all_trades,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("J.O.A.T."),
                "Does something amazing!");
        allRoles.put(jackOfAllTrades.getName(), jackOfAllTrades);

        Role jailkeeper = new Role("Jailkeeper",
                R.drawable.jailkeeper_puffle,
                1.9f,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                GetPower("Jailkeeper"),
                "Does something amazing!");
        allRoles.put(jailkeeper.getName(), jailkeeper);

        Role jesus = new Role("Jesus",
                R.drawable.jesus_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Jesus"),
                "Does something amazing!");
        allRoles.put(jesus.getName(), jesus);

        Role lawyer = new Role("Lawyer",
                R.drawable.lawyer_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                GetPower("Lawyer"),
                "Does something amazing!");
        allRoles.put(lawyer.getName(), lawyer);

        Role lovers = new Role("Lovers",
                R.drawable.lover_puffle,
                0.25f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Lovers"),
                "Does something amazing!");
        allRoles.put(lovers.getName(), lovers);

        Role mafia = new Role("Mafia",
                R.drawable.mafia_puffle,
                2,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Mafia"),
                "Does something amazing!");
        allRoles.put(mafia.getName(), mafia);

        Role mafiaRival = new Role("Mafia Rival",
                R.drawable.rival_puffle,
                2.25f,
                Role.Alliances.EVIL,
                Role.Teams.RIVAL_MAFIA,
                GetPower("Mafia Rival"),
                "Does something amazing!");
        allRoles.put(mafiaRival.getName(), mafiaRival);

        Role president = new Role("President",
                R.drawable.president_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("President"),
                "Does something amazing!");
        allRoles.put(president.getName(), president);

        Role satan = new Role("Satan",
                R.drawable.satan,
                0.75f,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Satan"),
                "Does something amazing!");
        allRoles.put(satan.getName(), satan);

        Role terrorist = new Role("Terrorist",
                R.drawable.terrorist_puffle,
                0.5f,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Terrorist"),
                "Does something amazing!");
        allRoles.put(terrorist.getName(), terrorist);

        Role veteran = new Role("Veteran",
                R.drawable.veteran_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Veteran"),
                "Does something amazing!");
        allRoles.put(veteran.getName(), veteran);

        Role villageIdiot = new Role("Village Idiot",
                R.drawable.village_idiot_puffle,
                -1,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                GetPower("Village Idiot"),
                "Does something amazing!");
        allRoles.put(villageIdiot.getName(), villageIdiot);

        Role witness = new Role("Witness",
                R.drawable.witness_puffle,
                2.5f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Witness"),
                "Does something amazing!");
        allRoles.put(witness.getName(), witness);

        Role wizard = new Role("Wizard",
                R.drawable.wizard_puffle,
                4,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Wizard"),
                "Does something amazing!");
        allRoles.put(wizard.getName(), wizard);
        

        Role zombieGood = new Role("Zombie Good",
                R.drawable.good_zombie_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Zombie Good"),
                "Does something amazing!");
        allRoles.put(zombieGood.getName(), zombieGood);

        Role zombieEvil = new Role("Zombie Evil",
                R.drawable.evil_zombie_puffle,
                -1,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Zombie Evil"),
                "Does something amazing!");
        allRoles.put(zombieEvil.getName(), zombieEvil);
    }


    public static Token GetToken(String name){
        return allTokens.get(name);
    }
    public static Vector<Token> GetAllTokens(){
        Vector<Token> output = new Vector<Token>();
        
        for (Map.Entry<String,Token> entry: allTokens.entrySet()) {
            output.add(entry.getValue());
        }
        
        return output;
    }

    public static Power GetPower(String name){
        return allPowers.get(name);
    }
    public static Vector<Power> GetAllPowers(){
        Vector<Power> output = new Vector<Power>();

        for (Map.Entry<String,Power> entry: allPowers.entrySet()) {
            output.add(entry.getValue());
        }

        return output;
    }
    
    public static Role GetRole(String name){
        return allRoles.get(name);
    }
    public static Vector<Role> GetAllRoles(){
        Vector<Role> output = new Vector<Role>();

        for (Map.Entry<String,Role> entry: allRoles.entrySet()) {
            output.add(entry.getValue());
        }

        return output;
    }
    public static Role GetRandomRole(){
        Random generator = new Random();
        Object[] values = allRoles.values().toArray();
        Role randomRole = (Role) values[generator.nextInt(values.length)];
        return randomRole;
    }

    public static void PrintSummary(){

        System.out.print("\nAll Roles:\n");
        for (Map.Entry<String,Role> entry: allRoles.entrySet()) {
            entry.getValue().PrintSummary("    ");
            System.out.print("\n");
        }

        System.out.print("\nAll Powers:\n");
        for (Map.Entry<String,Power> entry: allPowers.entrySet()) {
            entry.getValue().PrintSummary("    ");
            System.out.print("\n");
        }

        System.out.print("\nAll Tokens:\n");
        for (Map.Entry<String,Token> entry: allTokens.entrySet()) {
            entry.getValue().PrintSummary("    ");
            System.out.print("\n");
        }
    }

    public static void PrintDetatiled(){

        System.out.print("\nAll Roles:\n");
        for (Map.Entry<String,Role> entry: allRoles.entrySet()) {
            entry.getValue().PrintDetailed("    ");
            System.out.print("\n");
        }

        System.out.print("\nAll Powers:\n");
        for (Map.Entry<String,Power> entry: allPowers.entrySet()) {
            entry.getValue().PrintDetailed("    ");
            System.out.print("\n");
        }

        System.out.print("\nAll Tokens:\n");
        for (Map.Entry<String,Token> entry: allTokens.entrySet()) {
            entry.getValue().PrintDetailed("    ");
            System.out.print("\n");
        }
    }
}
