package com.example.pufflemafia.data;

import com.example.pufflemafia.R;

import java.util.HashMap;
import java.util.Map;
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

        Token cupid = new Token(
                "Cupid",
                R.drawable.cupid_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(cupid.getName(), cupid);

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

        Token mafia = new Token(
                "Mafia",
                R.drawable.mafia_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT);
        allTokens.put(mafia.getName(), mafia);

        Token terrorist = new Token(
                "Terrorist",
                R.drawable.terrorist_puffle,
                Token.TokenTypes.CLEAR_NEVER);
        allTokens.put(terrorist.getName(), terrorist);
    }

    private static void InitializeAllPowers(){
        allPowers = new HashMap<String, Power>();

        Power cupid = new Power(
                "Cupid",
                Power.PowerType.FIRSTNIGHT,
                "link",
                GetToken("Cupid"));
        allPowers.put(cupid.getName(), cupid);

        Power detective = new Power(
                "Detective",
                Power.PowerType.CONTINOUS,
                "know about",
                GetToken("Detective"));
        allPowers.put(detective.getName(), detective);

        Power doctor = new Power(
                "Doctor",
                Power.PowerType.CONTINOUS,
                "heal",
                GetToken("Doctor"));
        allPowers.put(doctor.getName(), doctor);

        Power doggie = new Power(
                "doggie",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(doggie.getName(), doggie);

        Power lovers = new Power(
                "Lovers",
                Power.PowerType.FIRSTNIGHT,
                "");
        allPowers.put(lovers.getName(), lovers);

        Power mafia = new Power(
                "Mafia",
                Power.PowerType.CONTINOUS,
                "kill",
                GetToken("Mafia"));
        allPowers.put(mafia.getName(), mafia);

        Power president = new Power(
                "President",
                Power.PowerType.SELFACTIVE,
                "");
        allPowers.put(president.getName(), president);

        Power terrorist = new Power(
                "Terrorist",
                Power.PowerType.FIRSTNIGHT,
                "plant a bomb on",
                GetToken("Terrorist"));
        allPowers.put(terrorist.getName(), terrorist);

        Power villageIdiot = new Power(
                "Village Idiot",
                Power.PowerType.PASSIVE,
                "");
        allPowers.put(villageIdiot.getName(), villageIdiot);
    }

    private static void InitializeAllRoles(){
        allRoles = new HashMap<String, Role>();

        Role cupid = new Role("Cupid",
                R.drawable.cupid_puffle,
                0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Cupid"));
        allRoles.put(cupid.getName(), cupid);

        Role detective = new Role("detective",
                R.drawable.detective_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("detective"));
        allRoles.put(detective.getName(), detective);

        Role doctor = new Role("Doctor",
                R.drawable.doctor_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Doctor"));
        allRoles.put(doctor.getName(), doctor);

        Role doggie = new Role("Doggie",
                R.drawable.doggie_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Doggie"));
        allRoles.put(doggie.getName(), doggie);

        Role lovers = new Role("Lovers",
                R.drawable.lover_puffle,
                0,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Lovers"));
        allRoles.put(lovers.getName(), lovers);

        Role mafia = new Role("Mafia",
                R.drawable.mafia_puffle,
                2,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Mafia"));
        allRoles.put(mafia.getName(), mafia);

        Role president = new Role("President",
                R.drawable.president_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("President"));
        allRoles.put(president.getName(), president);

        Role terrorist = new Role("Terrorist",
                R.drawable.terrorist_puffle,
                0,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Terrorist"));
        allRoles.put(terrorist.getName(), terrorist);

        Role villageIdiot = new Role("Village Idiot",
                R.drawable.village_idiot_puffle,
                -1,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                GetPower("Village Idiot"));
        allRoles.put(villageIdiot.getName(), villageIdiot);
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
}
