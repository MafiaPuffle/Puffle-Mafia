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
        LoadAllRoles();
    }

    private static void InitializeAllDictionaries(){
        allTokens = new HashMap<String, Token>();
        allPowers = new HashMap<String, Power>();
        allRoles = new HashMap<String, Role>();
    }

//    private static void InitializeAllTokens(){
//        allTokens = new HashMap<String, Token>();
//
//        Token alien = new Token(
//                "Alien",
//                "Infected by the Alien and will contribute to the victory condition of the Alien",
//                R.drawable.alien_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
//        allTokens.put(alien.getName(), alien);
//
//        Token baker = new Token(
//                "Baker",
//                "Bread given by the Baker will keep this character alive if a famine occurs",
//                R.drawable.puffle_o_croissant, Token.TokenTypes.CLEAR_NEVER, 2);
//        allTokens.put(baker.getName(), baker);
//
//        Token cupid = new Token(
//                "Cupid",
//                "This player is linked to all other characters with the Cupid token, all role actions will be applied to all Cupid token players",
//                R.drawable.cupid_puffle, Token.TokenTypes.CLEAR_NEVER, 2);
//        allTokens.put(cupid.getName(), cupid);
//
//        Token corruptcop = new Token(
//                "Corrupt Cop",
//                "This player could not vote this round",
//                R.drawable.corrupt_cop, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(corruptcop.getName(), corruptcop);
//
//
//        Token cyborg = new Token(
//                "Cyborg",
//                "This player had their role copied by the Cyborg",
//                R.drawable.cyborg_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
//        allTokens.put(cyborg.getName(), cyborg);
//
//        Token dentist = new Token(
//                "Dentist",
//                "Player cannot speak for the rest of this day",
//                R.drawable.dentist_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(dentist.getName(), dentist);
//
//        Token detective = new Token(
//                "Detective",
//                "This player had their alliance revealed to the Detective",
//                R.drawable.detective_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(detective.getName(), detective);
//
//        Token doctor = new Token(
//                "Doctor",
//                "This player will not die by the ONLY the Mafia",
//                R.drawable.doctor_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(doctor.getName(), doctor);
//
////        Token theFather = new Token(
////                "The Father",
////                "will give the player a funny feeling",
////                R.drawable.the_father_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
////        allTokens.put(theFather.getName(), theFather);
////
////        Token holySpirit = new Token(
////                "Holy Spirit",
////                "will give the player a funny feeling",
////                R.drawable.holy_spirit_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
////        allTokens.put(holySpirit.getName(), holySpirit);
//
//        Token jackOfAllTrades = new Token(
//                "J.O.A.T.",
//                "This player had the Jack-Of-All-Trades use their role on them",
//                R.drawable.jack_of_all_trades, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(jackOfAllTrades.getName(), jackOfAllTrades);
//
//        Token jailKeeper = new Token(
//                "Jailkeeper",
//                "This player cannot use their ability this night or during the day",
//                R.drawable.jailkeeper_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(jailKeeper.getName(), jailKeeper);
//
//        Token lawyer = new Token(
//                "Lawyer",
//                "This player will not die by ONLY the town vote",
//                R.drawable.lawyer_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(lawyer.getName(), lawyer);
//
////        Token lovers = new Token(
////                "Lovers",
////                "will give the player a funny feeling",
////                R.drawable.lover_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
////        allTokens.put(lovers.getName(), lovers);
//
//        Token mafia = new Token(
//                "Mafia",
//                "This player dies by the Mafia",
//                R.drawable.mafia_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(mafia.getName(), mafia);
//
//        Token mafiaRival = new Token(
//                "Mafia Rival",
//                "This player dies by the Rival Mafia",
//                R.drawable.rival_puffle, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(mafiaRival.getName(), mafiaRival);
//
//        Token necromancer = new Token(
//                "Necromancer",
//                "This player had their role taken by the Necromancer",
//                R.drawable.necromancer, Token.TokenTypes.CLEAR_NEVER, 1);
//        allTokens.put(necromancer.getName(), necromancer);
//
////        Token satan = new Token(
////                "Satan",
////                "will give the player a funny feeling",
////                R.drawable.satan, Token.TokenTypes.CLEAR_NEVER, 1);
////        allTokens.put(satan.getName(), satan);
//
//        Token terrorist = new Token(
//                "Terrorist",
//                "This player dies when the Terrorist dies",
//                R.drawable.terrorist_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
//        allTokens.put(terrorist.getName(), terrorist);
//
//        Token tracker = new Token(
//                "Tracker",
//                "This player has been tracked by the Tracker",
//                R.drawable.tracker, Token.TokenTypes.CLEAR_ON_NIGHT, 1);
//        allTokens.put(tracker.getName(), tracker);
//
////        Token witness = new Token(
////                "Witness",
////                "will give the player a funny feeling",
////                R.drawable.witness_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
////        allTokens.put(witness.getName(), witness);
//
//        Token wizard = new Token(
//                "Wizard",
//                "This player had their role switched with the Wizard",
//                R.drawable.wizard_puffle, Token.TokenTypes.CLEAR_NEVER, 1);
//        allTokens.put(wizard.getName(), wizard);
//
//    }
//
//    private static void InitializeAllPowers(){
//        allPowers = new HashMap<String, Power>();
//
//        Power alien = new Power("Alien",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO INFECT",
//                GetToken("Alien"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(alien.getName(), alien);
//
//        Power apprentice = new Power("Apprentice",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(apprentice.getName(), apprentice);
//
//        Power baker = new Power("Baker",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO GIVE BREAD TO",
//                GetToken("Baker"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(baker.getName(), baker);
//
//        Power bribedjudge = new Power("Bribed Judge",
//                Power.PowerType.ACTIVE,
//                "During the next day vote, would you like to decide the final vote decision", Power.PowerPromptType.YES_OR_NO);
//        allPowers.put(bribedjudge.getName(), bribedjudge);
//
//        Power civilian = new Power("Civilian",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(civilian.getName(), civilian);
//
//        Power cupid = new Power(
//                "Cupid",
//                Power.PowerType.FIRSTNIGHT,
//                "WHICH TWO PLAYERS WOULD YOU LIKE TO FALL IN LOVE",
//                GetToken("Cupid"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(cupid.getName(), cupid);
//
//        Power corruptcop = new Power("Corrupt Cop",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO KEEP FROM VOTING THE NEXT DAY",
//                GetToken("Corrupt Cop"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(corruptcop.getName(), corruptcop);
//
//        Power cyborg = new Power("Cyborg",
//                Power.PowerType.FIRSTNIGHT,
//                "WHO WOULD YOU LIKE TO COPY",
//                GetToken("Cyborg"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(cyborg.getName(), cyborg);
//
//        Power dentist = new Power("Dentist",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO SILENCE",
//                GetToken("Dentist"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(dentist.getName(), dentist);
//
//        Power detective = new Power(
//                "Detective",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO KNOW ABOUT",
//                GetToken("Detective"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(detective.getName(), detective);
//
//        Power doctor = new Power(
//                "Doctor",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO SAVE",
//                GetToken("Doctor"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(doctor.getName(), doctor);
//
//        Power doggie = new Power(
//                "Doggie",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(doggie.getName(), doggie);
//
//        Power theFather = new Power("The Father",
//                Power.PowerType.ONETIMEUSE,
//                "WOULD YOU LIKE TO BRING BACK TO LIFE EVERYONE WHO DIES THIS ROUND", Power.PowerPromptType.YES_OR_NO);
//        allPowers.put(theFather.getName(), theFather);
//
//        Power godFather = new Power("GodFather",
//                Power.PowerType.PASSIVE,
//                "WHO WOULD YOU LIKE TO MURDER", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(godFather.getName(), godFather);
//
//        Power holySpirit = new Power("Holy Spirit",
//                Power.PowerType.ONETIMEUSE,
//                "WOULD YOU LIKE TO DOUBLE ALL GOOD ROLE'S POWERS", Power.PowerPromptType.YES_OR_NO);
//        allPowers.put(holySpirit.getName(), holySpirit);
//
//        Power insomniac = new Power("Insomniac",
//                Power.PowerType.CONTINOUS,
//                "THESE ARE THE PLAYERS THAT USED THEIR ABILITY ON YOU THIS NIGHT.", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(insomniac.getName(), insomniac);
//
//        Power jackOfAllTrades = new Power("J.O.A.T.",
//                Power.PowerType.CONTINOUS,
//                "WOULD YOU LIKE TO USE DOCTOR, DETECTIVE, DENTIST",
//                GetToken("J.O.A.T."), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(jackOfAllTrades.getName(), jackOfAllTrades);
//
//        Power jailKeeper = new Power("Jailkeeper",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO LOCK OUT OF THEIR ABILITY",
//                GetToken("Jailkeeper"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(jailKeeper.getName(), jailKeeper);
//
//        Power jesus = new Power("Jesus",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(jesus.getName(), jesus);
//
//        Power lawyer = new Power("Lawyer",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO SAVE FROM VOTE EXECTUION",
//                GetToken("Lawyer"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(lawyer.getName(), lawyer);
//
//        Power lovers = new Power(
//                "Lovers",
//                Power.PowerType.FIRSTNIGHT,
//                "Lovers wake up and look at each other, you are linked", Power.PowerPromptType.NOTHING);
//        allPowers.put(lovers.getName(), lovers);
//
//        Power mafia = new Power(
//                "Mafia",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO MURDER",
//                GetToken("Mafia"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(mafia.getName(), mafia);
//
//        Power rivalMafia = new Power("Mafia Rival",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO MURDER",
//                GetToken("Mafia Rival"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(rivalMafia.getName(), rivalMafia);
//
//        Power mole = new Power(
//                "Mole",
//                Power.PowerType.FIRSTNIGHT,
//                "Mole wake up, Mafia member raise your hands to reveal your identity", Power.PowerPromptType.NOTHING);
//        allPowers.put(mole.getName(), mole);
//
//        Power necromancer = new Power("Necromancer",
//                Power.PowerType.CONTINOUS,
//                "Which dead player's role would you like to become?",
//                GetToken("Necromancer"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(necromancer.getName(), necromancer);
//
//        Power president = new Power(
//                "President",
//                Power.PowerType.SELFACTIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(president.getName(), president);
//
//        Power satan = new Power("Satan",
//                Power.PowerType.ONETIMEUSE,
//                "WOULD YOU LIKE TO DOUBLE ALL EVIL ROLE'S POWERS", Power.PowerPromptType.YES_OR_NO);
//        allPowers.put(satan.getName(), satan);
//
//        Power shotgungranny = new Power("Shotgun Granny",
//                Power.PowerType.ONETIMEUSE,
//                "Would you like to go on alert. When on alert, any player that uses their ability on Shotgun Granny will die.", Power.PowerPromptType.YES_OR_NO);
//        allPowers.put(shotgungranny.getName(), shotgungranny);
//
//        Power terrorist = new Power(
//                "Terrorist",
//                Power.PowerType.FIRSTNIGHT,
//                "WHO WOULD YOU LIKE TO PLANT A BOMB ON",
//                GetToken("Terrorist"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(terrorist.getName(), terrorist);
//
//        Power tracker = new Power(
//                "Tracker",
//                Power.PowerType.CONTINOUS,
//                "WHO WOULD YOU LIKE TO SEE WHO THEY USED THEIR ABILITY ON",
//                GetToken("Tracker"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(tracker.getName(), tracker);
//
//        Power veteran = new Power("Veteran",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(veteran.getName(), veteran);
//
//        Power villageIdiot = new Power(
//                "Village Idiot",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(villageIdiot.getName(), villageIdiot);
//
//        Power witness = new Power("Witness",
//                Power.PowerType.ONETIMEUSE,
//                "DID YOU WITNESS A MURDER", Power.PowerPromptType.YES_OR_NO);
//        allPowers.put(witness.getName(), witness);
//
//        Power wizard = new Power("Wizard",
//                Power.PowerType.ONETIMEUSE,
//                "WHO WOULD YOU LIKE TO SWITCH WITH",
//                GetToken("Wizard"), Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(wizard.getName(), wizard);
//
//        Power zombieGood = new Power("Zombie Good",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(zombieGood.getName(), zombieGood);
//
//        Power zombieBad = new Power("Zombie Evil",
//                Power.PowerType.PASSIVE,
//                "", Power.PowerPromptType.ALL_PLAYERS);
//        allPowers.put(zombieBad.getName(), zombieBad);
//    }
//
//    private static void InitializeAllRoles(){
//        allRoles = new HashMap<String, Role>();
//
//        Role alien = new Role("Alien",
//                R.drawable.alien_puffle,
//                3,
//                Role.Alliances.EVIL,
//                Role.Teams.SELF,
//                GetPower("Alien"),
//                "Infect one player at night. If half the players become Aliens, the Alien wins. (Does not win with the Civilians or Mafia)\n" +
//                        "" +
//                        "The Alien takes the form of a Puffle and slowly infects the town players, turning them into Aliens. Players do not know they are Aliens. Once half the players are Aliens, they can take over the town and win. If the head Alien is dead, then infected players return to normal.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(alien.getName(), alien);
//
//        Role apprentice = new Role("Apprentice",
//                R.drawable.apprentice,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Apprentice"),
//                "Announce that the Apprentice is using a Wizard spell and activate one Wizard spell. Only one Wizard spell can be used per game",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(apprentice.getName(), apprentice);
//
//        Role baker = new Role("Baker",
//                R.drawable.baker_puffle,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Baker"),
//                "Bake 2 Croissants and give them out to any player(s) during the day. If the Baker dies, players without Croissants die after 3 nights (Maximum).\n" +
//                        "" +
//                        "Everyone needs FOOD. The Baker's yummy pastries keep the town alive. If the Baker dies, a famine will spread and players without Croissants will die in 3 nights (1 night for every 5 players)\n",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(baker.getName(), baker);
//
//        Role bribedjudge = new Role("Bribed Judge",
//                R.drawable.bribed_judge,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Bribed Judge"),
//                "For one day only, the Bribed Judge will be able to decide wether a person dies or live during the vote. They choose to activate it during the night and it will go off during the day vote",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(bribedjudge.getName(), bribedjudge);
//
//        Role civilian = new Role("Civilian",
//                R.drawable.civilian_puffles,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Civilian"),
//                "Civilians have no special abilities.\n" +
//                        "" +
//                        "Civilian will vote people out at the beginning of the next day. \n" +
//                        "" +
//                        "Boring life as a Civilian. At least your get to execute a player every night!",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                3);
//        allRoles.put(civilian.getName(), civilian);
//
//        Role corruptcop = new Role("Corrupt Cop",
//                R.drawable.corrupt_cop,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Corrupt Cop"),
//                "Choose one player and that player cannot vote the next day. This does not stop the President.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(corruptcop.getName(), corruptcop);
//
//        Role cupid = new Role("Cupid",
//                R.drawable.cupid_puffle,
//                .5f,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Cupid"),
//                "Links two players together on the first night\n" +
//                        "" +
//                        "Choose two players to link together. All effects of other players will affect both players that are linked together (If one player dies, they both die. If one player is saved, both are saved.) The Match-Maker of Heaven!",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(cupid.getName(), cupid);
//
//        Role cyborg = new Role("Cyborg",
//                R.drawable.cyborg_puffle,
//                0,
//                Role.Alliances.NEUTRAL,
//                Role.Teams.NEUTRAL,
//                GetPower("Cyborg"),
//                "At the start of the game, choose one player to copy their ability and become Good or Evil based on that player.\n" +
//                        "" +
//                        "Cyborgs can become anyone they want to with the power of technology. Choosing a player will transform the Cyborg into that player's ability as well.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(cyborg.getName(), cyborg);
//
//        Role dentist = new Role("Dentist",
//                R.drawable.dentist_puffle,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Dentist"),
//                "Choose one player to silence for the day\n" +
//                        "" +
//                        "The Dentist does daily dentist appointments with town players. When a player gets their teeth done, they cannot speak for an entire day. ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(dentist.getName(), dentist);
//
//        Role detective = new Role("Detective",
//                R.drawable.detective_puffle,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Detective"),
//                "Discovers the alliance of one player every night\n" +
//                        "" +
//                        "Being the genius that they are, choose one player and the Playmaster will give a thumbs up if the person is Good or a thumbs down if they are Evil (Possibly Mafia). ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(detective.getName(), detective);
//
//        Role doctor = new Role("Doctor",
//                R.drawable.doctor_puffle,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Doctor"),
//                "Save one player every night (self-included) from death by the Mafia \n" +
//                        "" +
//                        "All Doctors must save one player unanimously. The oath the Doctors took will keep the injuries to a minimum.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(doctor.getName(), doctor);
//
//        Role doggie = new Role("Doggie",
//                R.drawable.doggie_puffle,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Doggie"),
//                "Doggie must receive 1 extra vote to be voted out \n" +
//                        "" +
//                        "The Doggie is loved by the town. It must receive a majority vote plus 1 (if 11 people are voting, 7 guilty votes are required) because most people do not want the Doggie to die. ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(doggie.getName(), doggie);
//
//        Role theFather = new Role("The Father",
//                R.drawable.the_father_puffle,
//                5,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("The Father"),
//                "Everybody that died this round will come back to life\n" +
//                        "" +
//                        "He chooses to activate it at the beginning of the night and it activates at the end of the day. The Father has all the power of space, time, and matter. All players that die this round will come back to life because The Father loves the world.  ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(theFather.getName(), theFather);
//
//        Role Godfather = new Role("GodFather",
//                R.drawable.godfather_puffle,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("GodFather"),
//                "Kill one player every night with the other members of the Mafia. Will be a thumbs up when discovered by the Detective \n" +
//                        "" +
//                        "All Mafia and Godfather members must kill one player unanimously. Being the Head of the Mafia, he often avoids detection by the authorities. ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(Godfather.getName(), Godfather);
//
//        Role holySpirit = new Role("Holy Spirit",
//                R.drawable.holy_spirit_puffle,
//                0.75f,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Holy Spirit"),
//                "All Good players this round will have their ability activated twice\n" +
//                        "" +
//                        "The Holy Spirit dwells within all the believers and empowers them. This allows all Good players to use their abilities twice this round. Players with one-time use abilities cannot use their powers again and are unaffected by the Holy Spirit.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(holySpirit.getName(), holySpirit);
//
//        Role insomniac = new Role("Insomniac",
//                R.drawable.insomniac,
//                4.6f,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Insomniac"),
//                "Insomniac will know the players that used their ability at night on them. Their roles will not be revealed.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(insomniac.getName(), insomniac);
//
//        Role jackOfAllTrades = new Role("J.O.A.T.",
//                R.drawable.jack_of_all_trades,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("J.O.A.T."),
//                "Can use one skill of the Town players. You cannot use the same skill until all skills are used\n" +
//                        "" +
//                        "The Jack-of-All-Trades a master of none, but still better than a master of one. Choose one skill from a list of skills. (Detective, Doctor, Lawyer, Dentist)",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(jackOfAllTrades.getName(), jackOfAllTrades);
//
//        Role jailkeeper = new Role("Jailkeeper",
//                R.drawable.jailkeeper_puffle,
//                1.9f,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Jailkeeper"),
//                "The Jailkeeper chooses one player every night and they cannot activate their ability during the night or day\n" +
//                        "" +
//                        "The Jailkeeper will keep any player in jail because he is scared of the Mafia. Anyone that the Jailkeeper chooses, cannot activate their ability for the night or day",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(jailkeeper.getName(), jailkeeper);
//
//        Role jesus = new Role("Jesus",
//                R.drawable.jesus_puffle,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Jesus"),
//                "Jesus comes back to the game after death, after 3 nights (maximum)\n" +
//                        "" +
//                        "Jesus is the Son of God and will return after 3 nights (1 night for every 5 players) after being killed by the Mafia or Civilians.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(jesus.getName(), jesus);
//
//        Role lawyer = new Role("Lawyer",
//                R.drawable.lawyer_puffle,
//                3,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Lawyer"),
//                "Save one player every day (self-included) from death by the player's vote, chosen at night\n" +
//                        "" +
//                        "The master of the Law, the Lawyer can defend anyone is a court of law and save anyone they choose from a vote execution.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(lawyer.getName(), lawyer);
//
//        Role lovers = new Role("Lovers",
//                R.drawable.lover_puffle,
//                0.25f,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Lovers"),
//                "Lovers are linked to each other\n" +
//                        "" +
//                        "All effects of other players will affect both players that are linked together (If one player dies, they both die. If one player is saved, both are saved.) Till death do us part am I right?!",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                2,
//                2);
//        allRoles.put(lovers.getName(), lovers);
//
//        Role mafia = new Role("Mafia",
//                R.drawable.mafia_puffle,
//                2,
//                Role.Alliances.EVIL,
//                Role.Teams.MAFIA,
//                GetPower("Mafia"),
//                "Kill one player every night with the other members of the Mafia. \n" +
//                        "" +
//                        "All Mafia members must kill one player unanimously. What turned these once-good Civilians into murderers, the world will never know.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                2);
//        allRoles.put(mafia.getName(), mafia);
//
//        Role mafiaRival = new Role("Mafia Rival",
//                R.drawable.rival_puffle,
//                2.25f,
//                Role.Alliances.EVIL,
//                Role.Teams.RIVAL_MAFIA,
//                GetPower("Mafia Rival"),
//                "Kill one player every night with the other members of the Rival Mafia. \n" +
//                        "" +
//                        "All Rival Mafia members must kill one player unanimously. What turned these once-good Civilians into murders, the world will never know.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                2);
//        allRoles.put(mafiaRival.getName(), mafiaRival);
//
//        Role mole = new Role("Mole",
//                R.drawable.mole,
//                0.25f,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Mole"),
//                "Have the Mafia members raise their hands to reveal their identities to the Mole",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(mole.getName(), mole);
//
//        Role necromancer = new Role("Necromancer",
//                R.drawable.necromancer,
//                4.25f,
//                Role.Alliances.GOOD,
//                Role.Teams.SELF,
//                GetPower("Necromancer"),
//                "Choose one dead player, Necromancer becomes that role. If they had their one time use ability activated already, they cannot use it again",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(necromancer.getName(), necromancer);
//
//        Role president = new Role("President",
//                R.drawable.president_puffle,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("President"),
//                "Stand up and announce that you are the President and execute one player during the day at ANYTIME \n" +
//                        "" +
//                        "President can veto any vote on a player and execute any player of their choice during the day (not night).",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(president.getName(), president);
//
//        Role satan = new Role("Satan",
//                R.drawable.satan,
//                0.75f,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Satan"),
//                "All Evil players this round will have their ability activated twice\n" +
//                        "" +
//                        "Satan causes evil and confusion in the world. This allows all Evil players to use their abilities twice this round. Players with one-time use abilities cannot use their powers again and are unaffected by the Satan.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(satan.getName(), satan);
//
//        Role shotgungranny = new Role("Shotgun Granny",
//                R.drawable.grandma_puffle,
//                1.91f,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Shotgun Granny"),
//                "Go on alert. When on alert, any player that uses their ability on Shotgun Granny will die. Only 2 uses (maximum) per game",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(shotgungranny.getName(), shotgungranny);
//
//        Role terrorist = new Role("Terrorist",
//                R.drawable.terrorist_puffle,
//                0.5f,
//                Role.Alliances.EVIL,
//                Role.Teams.MAFIA,
//                GetPower("Terrorist"),
//                "Choose one player to die when the Terrorist dies\n" +
//                        "" +
//                        "Choose one player to attach a bomb too. That player dies when the Terrorist dies. The Terrorist is highered by the Mafia but their identities are hidden from each other. The Terrorist does not vote with the Mafia but wins with them. ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(terrorist.getName(), terrorist);
//
//        Role tracker = new Role("Tracker",
//                R.drawable.tracker,
//                4.5f,
//                Role.Alliances.GOOD,
//                Role.Teams.MAFIA,
//                GetPower("Tracker"),
//                "Track one player and see who they used their ability on at night.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(tracker.getName(), tracker);
//
//        Role veteran = new Role("Veteran",
//                R.drawable.veteran_puffle,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Veteran"),
//                "When the Veteran is chosen to die by the Mafia for the first time, one random Mafia member dies instead\n" +
//                        "" +
//                        "The Veteran is a master of combat and self-defense. Once he's fought off one Mafia member, the Veteran is injured and cannot defend himself anymore. The civilians find out that the Veteran has defended themselves but does not know who it is. !",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(veteran.getName(), veteran);
//
//        Role villageIdiot = new Role("Village Idiot",
//                R.drawable.village_idiot_puffle,
//                -1,
//                Role.Alliances.EVIL,
//                Role.Teams.SELF,
//                GetPower("Village Idiot"),
//                "The Village Idiot wins if they are voted out by the players at the end of the day. \n" +
//                        "" +
//                        "If the players choose the Village Idiot to die during the day, the Village Idiot wins. They do not win if they die by any other means. They do not win with the Mafia or the Civilians. Why do idiots suck?!",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(villageIdiot.getName(), villageIdiot);
//
//        Role witness = new Role("Witness",
//                R.drawable.witness_puffle,
//                2.5f,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Witness"),
//                "For one night, the witness can look up to see who the Mafia are\n" +
//                        "" +
//                        "For one night only, the Witness can look up to see the Mafia. If the Mafia catches the Witness, the Witness dies that round as well. Being a little child doesn't help that much.",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(witness.getName(), witness);
//
//        Role wizard = new Role("Wizard",
//                R.drawable.wizard_puffle,
//                6,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Wizard"),
//                "At the beginning of a single night, choose one person to switch roles with. The Playmaster will switch cards with the Wizard and the other player will know there has been a switch.\n" +
//                        "" +
//                        "CANNOT ACTIVATE THE SPELLS. "
//                        +"[Switch Ability]\n" +
//                        "You are an untrained Wizard\n" +
//                        "Due to your lack of skill as a Wizard Announce that you are using your Spell anytime during the day.\n" +
//                        "CAN ACTIVATE SPELLS",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(wizard.getName(), wizard);
//
//        Role zombieGood = new Role("Zombie Good",
//                R.drawable.good_zombie_puffle,
//                -1,
//                Role.Alliances.GOOD,
//                Role.Teams.TOWN,
//                GetPower("Zombie Good"),
//                "The Good Zombie not allowed to talk only grunting is allowed. You are not allowed to use your arms. You win with the Mafia.\\n\" +\n" +
//                        "" +
//                        "The Good Zombie the dead Godfather of the Mafia. Being brought back to life does not mean your body is still intact. As a Zombie, your tongue has decayed and you cannot talk but only make grunting sounds. Your limbs have also decayed so you cannot move your arms. \");\n",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(zombieGood.getName(), zombieGood);
//
//        Role zombieEvil = new Role("Zombie Evil",
//                R.drawable.evil_zombie_puffle,
//                -1,
//                Role.Alliances.EVIL,
//                Role.Teams.MAFIA,
//                GetPower("Zombie Evil"),
//                "The Evil Zombie not allowed to talk only grunting is allowed. You are not allowed to use your arms. You win with the Mafia.\n" +
//                        "" +
//                        "The Evil Zombie the dead Godfather of the Mafia. Being brought back to life does not mean your body is still intact. As a Zombie, your tongue has decayed and you cannot talk but only make grunting sounds. Your limbs have also decayed so you cannot move your arms. ",
//                "Wins if the player had a good time!",
//                "Did you know puffles are fluffy?",
//                1,
//                1);
//        allRoles.put(zombieEvil.getName(), zombieEvil);
//    }

    private static void LoadAllRoles()
    {
        InitializeAllDictionaries();

        LoadRole("Alien",
                "Infect one player at night",
                "If half of the players become infected",
                "I'm not a fan of crop circles",
                "Player has been infected by the alien",
                "WHO DO YOU WANNA INFECT?",
                3,
                1,
                1,
                1,
                R.drawable.alien_puffle,
                R.drawable.alien_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.SELF);

        LoadRole("Apprentice",
               "Announce that the Apprentice is using a Wizard spell and activate one Wizard spell. Only one Wizard spell can be used per game",
               "Wins if the player had a good time!",
               "Did you know puffles are fluffy?",
               "",
               -1,
               1,
               1,
               R.drawable.apprentice,
               Power.PowerType.PASSIVE,
               Power.PowerPromptType.ALL_PLAYERS,
               Role.Alliances.GOOD,
               Role.Teams.TOWN);


        LoadRole("Baker",
                "Give bread to players at night.  If the Baker dies everyone without bread dies in 3 nights",
                "Wins if the Mafia are voted out",
                "I bake break for everyone",
                "Players with this will not die once the baker dies",
                "WHO DO YOU WANNA GIVE BREAD TO?",
                3,
                1,
                1,
                2,
                R.drawable.baker_puffle,
                R.drawable.puffle_o_croissant,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Bribed Judge",
               "For one day only, the Bribed Judge will be able to decide wether a person dies or live during the vote. They choose to activate it during the night and it will go off during the day vote",
               "Wins if the player had a good time!",
               "Did you know puffles are fluffy?",
               "During the next day vote, would you like to decide the final vote decision",
               3,
               1,
               1,
               R.drawable.bribed_judge,
               Power.PowerType.ONETIMEUSE,
               Power.PowerPromptType.YES_OR_NO,
               Role.Alliances.GOOD,
               Role.Teams.MAFIA);

        LoadRole("Civilian",
                "Has no special ability",
                "Wins if the Mafia are voted out",
                "I hope the town stays peaceful",
                "NOTHING",
                1,
                1,
                2,
                R.drawable.civilian_puffles,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Corrupt Cop",
               "Choose one player and that player cannot vote the next day. This does not stop the President.",
               "Wins if the player had a good time!",
               "Did you know puffles are fluffy?",
               "This player could not vote this round",
               "WHO WOULD YOU LIKE TO KEEP FROM VOTING THE NEXT DAY",
               3,
               1,
               1,
               1,
               R.drawable.corrupt_cop,
               R.drawable.corrupt_cop,
               Token.TokenTypes.CLEAR_ON_NIGHT,
               Power.PowerType.CONTINOUS,
               Power.PowerPromptType.ALL_PLAYERS,
               Role.Alliances.GOOD,
               Role.Teams.MAFIA);


        LoadRole("Cupid",
                "Links two players together, whatever happens to one happens to the other",
                "Wins if the Mafia are voted out",
                "I just love bring people together",
                "This player is liked to anther player with this same token,  whatever happens to one happens to the other",
                "WHO DO YOU WANNA LINK?",
                .5f,
                1,
                1,
                2,
                R.drawable.cupid_puffle,
                R.drawable.cupid_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Cyborg",
                "At the start of the game choose one player, you are now the same role as them for the rest of the game",
                "You win the same way whatever role you copy does",
                "Its amazing what computers can do these days!",
                "The cyborg has copied the role of this player",
                "WHO DO YOU WANNA COPY?",
                0,
                1,
                1,
                1,
                R.drawable.cyborg_puffle,
                R.drawable.cyborg_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Dentist",
                "Choose one player to mute for the day, that player can't speak for the next day",
                "You win if the Mafia gets voted out",
                "I'm the best dentist in the world, never hear a customer complain",
                "This player has been muted and cannot talk for the day",
                "WHO DO YOU WANNA MUTE?",
                3,
                1,
                1,
                1,
                R.drawable.dentist_puffle,
                R.drawable.dentist_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Detective",
                "Discovers the alliance of one player each night",
                "Wins if the Mafia are voted out",
                "Never had a case I couldn't solve, except that one time.  But that doesn't count.",
                "The detective has learned this players alliance",
                "WHO DO YOU WANNA KNOW ABOUT?",
                3,
                1,
                1,
                1,
                R.drawable.detective_puffle,
                R.drawable.detective_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Doctor",
                "Protect one player from the Mafia each night",
                "Wins if the Mafia are voted out",
                "Never liked blood, but somebody's gotta do this job.",
                "This player has been protected from the mafia.  Protected players are not killed by Mafia.",
                "WHO DO YOU WANNA SAVE?",
                3,
                1,
                1,
                1,
                R.drawable.doctor_puffle,
                R.drawable.doctor_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Doggie",
                "Needs one extra vote to be voted out",
                "Wins if the Mafia are voted out",
                "Bark! Bark! *wags-tail*",
                "prompt",
                -1,
                1,
                1,
                R.drawable.doggie_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("The Father",
                "Everybody that died this round will come back to life",
                "Wins if the Mafia are voted out",
                "Let there be life!",
                "DO YOU WANNA BRING EVERYONE BACK THIS DAY?",
                5,
                1,
                1,
                R.drawable.the_father_puffle,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("GodFather",
                "Kills players with the Mafia.  However the appear as a thumbs up to the detective.",
                "Wins if the Mafia equal half or more of the living players",
                "I'm gonna make him an offer he can't refuse",
                "prompt",
                -1,
                1,
                1,
                R.drawable.godfather_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA);

        LoadRole("Holy Spirit",
                "All Good players can use their abilities twice this round",
                "Wins if the Mafia are voted out",
                "You can do it!",
                "DO YOU WANNA DOUBLE ALL GOOD POWERS?",
                0.75f,
                1,
                1,
                R.drawable.holy_spirit_puffle,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Insomniac",
               "THESE ARE THE PLAYERS THAT USED THEIR ABILITY ON YOU THIS NIGHT",
               "Wins if the player had a good time!",
               "Did you know puffles are fluffy?",
               "THESE ARE THE PLAYERS THAT USED THEIR ABILITY ON YOU THIS NIGHT.",
               4.6f,
               1,
               1,
               R.drawable.insomniac,
               Power.PowerType.CONTINOUS,
               Power.PowerPromptType.ALL_PLAYERS,
               Role.Alliances.GOOD,
               Role.Teams.MAFIA);


        LoadRole("J.O.A.T.",
                "Can use one skill of the Town players each night.  You cannot use the same skill again until you have gone through each skill",
                "Wins if the Mafia get voted out",
                "I like to try a little of everything",
                "The J.O.A.T. has used one of their abilities on this player",
                "WHAT DO YOU WANNA DO?",
                3,
                1,
                1,
                1,
                R.drawable.jack_of_all_trades,
                R.drawable.jack_of_all_trades,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Jailkeeper",
                "Choose one player each night.  That player cannot activate their ability for the night or day.",
                "Wins if the Mafia equal half of the alive players",
                "flavor",
                "This player can't use their ability for the night or day",
                "WHO DO YOU WANNA LOCKOUT?",
                1.9f,
                1,
                1,
                1,
                R.drawable.jailkeeper_puffle,
                R.drawable.jailkeeper_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA);

        LoadRole("Jesus",
                "Comes back to life after he dies",
                "Wins if the Mafia get voted out",
                "I am the way the truth and the life",
                "prompt",
                -1,
                1,
                1,
                R.drawable.jesus_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Lawyer",
                "Save one player from being voted out",
                "Wins if the Mafia equal half of the living players",
                "I am the law",
                "Is protected from being voted out",
                "WHO DO YOU WANNA SAVE FROM BEING VOTED OUT?",
                3,
                1,
                1,
                1,
                R.drawable.lawyer_puffle,
                R.drawable.lawyer_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA);

        LoadRole("Lovers",
                "Lovers are linked to each other, whatever happens to one happens to the other",
                "Wins if the Mafia are voted out",
                "Romeo and Juliet got nothing on us",
                "WAKE UP, YOU ARE LINKED FOR THE REST OF THE GAME",
                0.25f,
                2,
                2,
                R.drawable.lover_puffle,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Mafia",
                "Kill one player every night",
                "Wins if the Mafia equal half of the living players",
                "We own this town",
                "This player will be killed",
                "WHO DO YOU WANNA KILL?",
                2,
                1,
                3,
                1,
                R.drawable.mafia_puffle,
                R.drawable.mafia_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Mafia Rival",
                "Kill one player every night",
                "Wins if the Mafia Rival players equal half of the living players",
                "Gun Gang for life!",
                "This player will be killed",
                "WHO DO YOU WANNA KILL?",
                2.25f,
                1,
                2,
                1,
                R.drawable.rival_puffle,
                R.drawable.rival_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.RIVAL_MAFIA);

        LoadRole("Mole",
               "Have the Mafia members raise their hands to reveal their identities to the Mole",
               "Wins if the player had a good time!",
               "Did you know puffles are fluffy?",
               "Mole wake up, Mafia member raise your hands to reveal your identity",
               0.25f,
               1,
               1,
               R.drawable.mole,
               Power.PowerType.FIRSTNIGHT,
               Power.PowerPromptType.NOTHING,
               Role.Alliances.GOOD,
               Role.Teams.MAFIA);

        LoadRole("Necromancer",
               "Choose one dead player, Necromancer becomes that role. If they had their one time use ability activated already, they cannot use it again",
               "Wins if the player had a good time!",
               "Did you know puffles are fluffy?",
               "This player had their role taken by the Necromancer",
               "Which dead player's role would you like to become?",
               4.25f,
               1,
               1,
               1,
               R.drawable.necromancer,
               R.drawable.necromancer,
               Token.TokenTypes.CLEAR_NEVER,
               Power.PowerType.ONETIMEUSE,
               Power.PowerPromptType.ALL_PLAYERS,
               Role.Alliances.GOOD,
               Role.Teams.SELF);


        LoadRole("President",
                "Once per game the President can veto one vote and vote out another player instead.",
                "Wins if the Mafia are voted out",
                "AMERICA!",
                "prompt",
                1,
                1,
                1,
                R.drawable.president_puffle,
                Power.PowerType.SELFACTIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Satan",
                "Once per game, lets all Evil players active their ability twice that round",
                "Wins if the Mafia equal half of the living players",
                "You can't do it",
                "DO YOU WANNA DOUBLE ALL EVIL POWERS?",
                0.75f,
                1,
                1,
                R.drawable.satan,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Terrorist",
                "Plants a bomb on one player, if they die the bomb goes off, killing the player its attached to",
                "Wins if the Mafia equal half of the living players",
                "Viva la Revolution",
                "This player will die if the Terrorist dies",
                "WHO DO YOU WANNA PLANT A BOMB ON?",
                0.5f,
                1,
                1,
                1,
                R.drawable.terrorist_puffle,
                R.drawable.terrorist_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Tracker",
                "Track on player and see who they used their ability on at night.",
                "Wins if the Mafia get voted out",
                "The nose knows",
                "This player is being tacked by the tracker.  The tracker knows who they used their ability on the previous night.",
                "WHO DO YOU WANNA TRACK?",
                4.5f,
                1,
                1,
                1,
                R.drawable.tracker,
                R.drawable.tracker,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA);

        LoadRole("Veteran",
                "If the Mafia tries to kill the Veteran the Mafia are killed instead",
                "Wins if the Mafia are voted out",
                "War, war never changes",
                "prompt",
                -1,
                1,
                1,
                R.drawable.veteran_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Village Idiot",
                "Wins if they are voted out during the day.",
                "Wins if they are voted out during the day",
                "What happens if I stick my hand in this blender?",
                "prompt",
                -1,
                1,
                1,
                R.drawable.village_idiot_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.EVIL,
                Role.Teams.SELF);

        LoadRole("Witness",
                "For one night they can try to peek and see who the Mafia is.",
                "Wins if the Mafia get voted out",
                "*stares into the middle distance*",
                "DID YOU WITNESS ANYTHING?",
                2.5f,
                1,
                1,
                R.drawable.witness_puffle,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Wizard",
                "Switch roles with one player",
                "Wins if the Mafia are voted out",
                "Abracadabra",
                "This player got their role switched with the wizard",
                "WHO DO YOU WANNA SWITCH ROLES WITH?",
                6,
                1,
                1,
                1,
                R.drawable.wizard_puffle,
                R.drawable.wizard_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Zombie Good",
                "Zombies cannot talk or raise their arms.  They can only grunt.",
                "Wins if the Mafia get voted out.",
                "Grrr...",
                "prompt",
                -1,
                1,
                1,
                R.drawable.good_zombie_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Zombie Evil",
                "Zombies cannot talk or raise their arms.  They can only grunt.",
                "Wins if the Mafia equal half of the alive players",
                "Brains....",
                "prompt",
                -1,
                1,
                1,
                R.drawable.alien_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);


    }

    private static void LoadRole(String name,
                                 String roleDescription,
                                 String winConditionDescription,
                                 String flavorText,
                                 String tokenDescription,
                                 String promptAtNight,
                                 float rolePriority,
                                 int minimumAllowedPerGame,
                                 int maximumAllowedPerGame,
                                 int normalMaximumTokensAllowedPerNight,
                                 int roleImage,
                                 int tokenImage,
                                 Token.TokenTypes tokenClearType,
                                 Power.PowerType powerType,
                                 Power.PowerPromptType promptType,
                                 Role.Alliances alliance,
                                 Role.Teams team){
        Token token = new Token(
                name,
                tokenDescription,
                tokenImage,
                tokenClearType,
                normalMaximumTokensAllowedPerNight);
        allTokens.put(token.getName(), token);

        Power power = new Power(
                name,
                powerType,
                promptAtNight,
                GetToken(name),
                promptType);
        allPowers.put(power.getName(), power);

        Role role = new Role(
                name,
                roleImage,
                rolePriority,
                alliance,
                team,
                GetPower(name),
                roleDescription,
                winConditionDescription,
                flavorText,
                minimumAllowedPerGame,
                maximumAllowedPerGame);
        allRoles.put(role.getName(), role);
    }

    private static void LoadRole(String name,
                                 String roleDescription,
                                 String winConditionDescription,
                                 String flavorText,
                                 String promptAtNight,
                                 float rolePriority,
                                 int minimumAllowedPerGame,
                                 int maximumAllowedPerGame,
                                 int roleImage,
                                 Power.PowerType powerType,
                                 Power.PowerPromptType promptType,
                                 Role.Alliances alliance,
                                 Role.Teams team){

        Power power = new Power(
                name,
                powerType,
                promptAtNight,
                promptType);
        allPowers.put(power.getName(), power);

        Role role = new Role(
                name,
                roleImage,
                rolePriority,
                alliance,
                team,
                GetPower(name),
                roleDescription,
                winConditionDescription,
                flavorText,
                minimumAllowedPerGame,
                maximumAllowedPerGame);
        allRoles.put(role.getName(), role);
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
