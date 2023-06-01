package com.example.pufflemafia.app.data;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.game.HelpPrompt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

public class DataManager {
    public static Map<String, Token> allTokens;
    public static Map<String, Power> allPowers;
    public static Map<String, Role> allRoles;
    public static Map<Integer, Vector<HelpPrompt>> allHelpPrompts;

    public DataManager(){
        InitializeAllDictionaries();
    }

    private static void InitializeAllDictionaries(){
        InitializeAllTokens();
        InitializeAllPowers();
        InitializeAllRoles();
        InitializeAllHelpPrompts();
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
        allPowers.put(theFather.getName(), theFather);

        Power godFather = new Power("GodFather",
                Power.PowerType.PASSIVE,
                "MURDER");
        allPowers.put(godFather.getName(), godFather);

        Power holySpirit = new Power("Holy Spirit",
                Power.PowerType.ONETIMEUSE,
                "DOUBLE GOOD POWERS");
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
                "Infect one player at night. If half the players become Aliens, the Alien wins. (Does not win with the Civilians or Mafia)\n" +
                        "" +
                        "The Alien takes the form of a Puffle and slowly infects the town players, turning them into Aliens. Players do not know they are Aliens. Once half the players are Aliens, they can take over the town and win. If the head Alien is dead, then infected players return to normal.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(alien.getName(), alien);

        Role baker = new Role("Baker",
                R.drawable.baker_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Baker"),
                "Bake 2 Croissants and give them out to any player(s) during the day. If the Baker dies, players without Croissants die after 3 nights (Maximum).\n" +
                        "" +
                        "Everyone needs FOOD. The Baker's yummy pastries keep the town alive. If the Baker dies, a famine will spread and players without Croissants will die in 3 nights (1 night for every 5 players)\n",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(baker.getName(), baker);

        Role civilian = new Role("Civilian",
                R.drawable.civilian_puffles,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Civilian"),
                "Civilians have no special abilities.\n" +
                        "" +
                        "Civilian will vote people out at the beginning of the next day. \n" +
                        "" +
                        "Boring life as a Civilian. At least your get to execute a player every night!",
                "Did you know puffles are fluffy?",
                1,
                3);
        allRoles.put(civilian.getName(), civilian);

        Role cupid = new Role("Cupid",
                R.drawable.cupid_puffle,
                .5f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Cupid"),
                "Links two players together on the first night\n" +
                        "" +
                        "Choose two players to link together. All effects of other players will affect both players that are linked together (If one player dies, they both die. If one player is saved, both are saved.) The Match-Maker of Heaven!",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(cupid.getName(), cupid);

        Role cyborg = new Role("Cyborg",
                R.drawable.cyborg_puffle,
                0,
                Role.Alliances.NEUTRAL,
                Role.Teams.NEUTRAL,
                GetPower("Cyborg"),
                "At the start of the game, choose one player to copy their ability and become Good or Evil based on that player.\n" +
                        "" +
                        "Cyborgs can become anyone they want to with the power of technology. Choosing a player will transform the Cyborg into that player's ability as well.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(cyborg.getName(), cyborg);

        Role dentist = new Role("Dentist",
                R.drawable.dentist_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Dentist"),
                "Choose one player to silence for the day\n" +
                        "" +
                        "The Dentist does daily dentist appointments with town players. When a player gets their teeth done, they cannot speak for an entire day. ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(dentist.getName(), dentist);

        Role detective = new Role("Detective",
                R.drawable.detective_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Detective"),
                "Discovers the alliance of one player every night\n" +
                        "" +
                        "Being the genius that they are, choose one player and the Playmaster will give a thumbs up if the person is Good or a thumbs down if they are Evil (Possibly Mafia). ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(detective.getName(), detective);

        Role doctor = new Role("Doctor",
                R.drawable.doctor_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Doctor"),
                "Save one player every night (self-included) from death by the Mafia \n" +
                        "" +
                        "All Doctors must save one player unanimously. The oath the Doctors took will keep the injuries to a minimum.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(doctor.getName(), doctor);

        Role doggie = new Role("Doggie",
                R.drawable.doggie_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Doggie"),
                "Doggie must receive 1 extra vote to be voted out \n" +
                        "" +
                        "The Doggie is loved by the town. It must receive a majority vote plus 1 (if 11 people are voting, 7 guilty votes are required) because most people do not want the Doggie to die. ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(doggie.getName(), doggie);

        Role theFather = new Role("The Father",
                R.drawable.the_father_puffle,
                4,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("The Father"),
                "Everybody that died this round will come back to life\n" +
                        "" +
                        "He chooses to activate it at the beginning of the night and it activates at the end of the day. The Father has all the power of space, time, and matter. All players that die this round will come back to life because The Father loves the world.  ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(theFather.getName(), theFather);

        Role Godfather = new Role("GodFather",
                R.drawable.godfather_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                GetPower("GodFather"),
                "Kill one player every night with the other members of the Mafia. Will be a thumbs up when discovered by the Detective \n" +
                        "" +
                        "All Mafia and Godfather members must kill one player unanimously. Being the Head of the Mafia, he often avoids detection by the authorities. ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(Godfather.getName(), Godfather);

        Role holySpirit = new Role("Holy Spirit",
                R.drawable.holy_spirit_puffle,
                0.75f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Holy Spirit"),
                "All Good players this round will have their ability activated twice\n" +
                        "" +
                        "The Holy Spirit dwells within all the believers and empowers them. This allows all Good players to use their abilities twice this round. Players with one-time use abilities cannot use their powers again and are unaffected by the Holy Spirit.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(holySpirit.getName(), holySpirit);

        Role jackOfAllTrades = new Role("J.O.A.T.",
                R.drawable.jack_of_all_trades,
                3,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("J.O.A.T."),
                "Can use one skill of the Town players. You cannot use the same skill until all skills are used\n" +
                        "" +
                        "The Jack-of-All-Trades a master of none, but still better than a master of one. Choose one skill from a list of skills. (Detective, Doctor, Lawyer, Dentist)",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(jackOfAllTrades.getName(), jackOfAllTrades);

        Role jailkeeper = new Role("Jailkeeper",
                R.drawable.jailkeeper_puffle,
                1.9f,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                GetPower("Jailkeeper"),
                "The Jailkeeper chooses one player every night and they cannot activate their ability during the night or day\n" +
                        "" +
                        "The Jailkeeper will keep any player in jail because he is scared of the Mafia. Anyone that the Jailkeeper chooses, cannot activate their ability for the night or day",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(jailkeeper.getName(), jailkeeper);

        Role jesus = new Role("Jesus",
                R.drawable.jesus_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Jesus"),
                "Jesus comes back to the game after death, after 3 nights (maximum)\n" +
                        "" +
                        "Jesus is the Son of God and will return after 3 nights (1 night for every 5 players) after being killed by the Mafia or Civilians.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(jesus.getName(), jesus);

        Role lawyer = new Role("Lawyer",
                R.drawable.lawyer_puffle,
                3,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA,
                GetPower("Lawyer"),
                "Save one player every day (self-included) from death by the player's vote, chosen at night\n" +
                        "" +
                        "The master of the Law, the Lawyer can defend anyone is a court of law and save anyone they choose from a vote execution.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(lawyer.getName(), lawyer);

        Role lovers = new Role("Lovers",
                R.drawable.lover_puffle,
                0.25f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Lovers"),
                "Lovers are linked to each other\n" +
                        "" +
                        "All effects of other players will affect both players that are linked together (If one player dies, they both die. If one player is saved, both are saved.) Till death do us part am I right?!",
                "Did you know puffles are fluffy?",
                2,
                2);
        allRoles.put(lovers.getName(), lovers);

        Role mafia = new Role("Mafia",
                R.drawable.mafia_puffle,
                2,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Mafia"),
                "Kill one player every night with the other members of the Mafia. \n" +
                        "" +
                        "All Mafia members must kill one player unanimously. What turned these once-good Civilians into murderers, the world will never know.",
                "Did you know puffles are fluffy?",
                1,
                2);
        allRoles.put(mafia.getName(), mafia);

        Role mafiaRival = new Role("Mafia Rival",
                R.drawable.rival_puffle,
                2.25f,
                Role.Alliances.EVIL,
                Role.Teams.RIVAL_MAFIA,
                GetPower("Mafia Rival"),
                "Kill one player every night with the other members of the Rival Mafia. \n" +
                        "" +
                        "All Rival Mafia members must kill one player unanimously. What turned these once-good Civilians into murders, the world will never know.",
                "Did you know puffles are fluffy?",
                1,
                2);
        allRoles.put(mafiaRival.getName(), mafiaRival);

        Role president = new Role("President",
                R.drawable.president_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("President"),
                "Stand up and announce that you are the President and execute one player during the day at ANYTIME \n" +
                        "" +
                        "President can veto any vote on a player and execute any player of their choice during the day (not night).",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(president.getName(), president);

        Role satan = new Role("Satan",
                R.drawable.satan,
                0.75f,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Satan"),
                "All Evil players this round will have their ability activated twice\n" +
                        "" +
                        "Satan causes evil and confusion in the world. This allows all Evil players to use their abilities twice this round. Players with one-time use abilities cannot use their powers again and are unaffected by the Satan.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(satan.getName(), satan);

        Role terrorist = new Role("Terrorist",
                R.drawable.terrorist_puffle,
                0.5f,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Terrorist"),
                "Choose one player to die when the Terrorist dies\n" +
                        "" +
                        "Choose one player to attach a bomb too. That player dies when the Terrorist dies. The Terrorist is highered by the Mafia but their identities are hidden from each other. The Terrorist does not vote with the Mafia but wins with them. ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(terrorist.getName(), terrorist);

        Role veteran = new Role("Veteran",
                R.drawable.veteran_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Veteran"),
                "When the Veteran is chosen to die by the Mafia for the first time, one random Mafia member dies instead\n" +
                        "" +
                        "The Veteran is a master of combat and self-defense. Once he's fought off one Mafia member, the Veteran is injured and cannot defend himself anymore. The civilians find out that the Veteran has defended themselves but does not know who it is. !",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(veteran.getName(), veteran);

        Role villageIdiot = new Role("Village Idiot",
                R.drawable.village_idiot_puffle,
                -1,
                Role.Alliances.EVIL,
                Role.Teams.SELF,
                GetPower("Village Idiot"),
                "The Village Idiot wins if they are voted out by the players at the end of the day. \n" +
                        "" +
                        "If the players choose the Village Idiot to die during the day, the Village Idiot wins. They do not win if they die by any other means. They do not win with the Mafia or the Civilians. Why do idiots suck?!",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(villageIdiot.getName(), villageIdiot);

        Role witness = new Role("Witness",
                R.drawable.witness_puffle,
                2.5f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Witness"),
                "For one night, the witness can look up to see who the Mafia are\n" +
                        "" +
                        "For one night only, the Witness can look up to see the Mafia. If the Mafia catches the Witness, the Witness dies that round as well. Being a little child doesn't help that much.",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(witness.getName(), witness);

        Role wizard = new Role("Wizard",
                R.drawable.wizard_puffle,
                4,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Wizard"),
                "At the beginning of a single night, choose one person to switch roles with. The Playmaster will switch cards with the Wizard and the other player will know there has been a switch.\n" +
                        "" +
                        "CANNOT ACTIVATE THE SPELLS. "
                        +"[Switch Ability]\n" +
                        "You are an untrained Wizard\n" +
                        "Due to your lack of skill as a Wizard Announce that you are using your Spell anytime during the day.\n" +
                        "CAN ACTIVATE SPELLS",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(wizard.getName(), wizard);

        Role zombieGood = new Role("Zombie Good",
                R.drawable.good_zombie_puffle,
                -1,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                GetPower("Zombie Good"),
                "The Good Zombie not allowed to talk only grunting is allowed. You are not allowed to use your arms. You win with the Mafia.\\n\" +\n" +
                        "" +
                        "The Good Zombie the dead Godfather of the Mafia. Being brought back to life does not mean your body is still intact. As a Zombie, your tongue has decayed and you cannot talk but only make grunting sounds. Your limbs have also decayed so you cannot move your arms. \");\n",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(zombieGood.getName(), zombieGood);

        Role zombieEvil = new Role("Zombie Evil",
                R.drawable.evil_zombie_puffle,
                -1,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA,
                GetPower("Zombie Evil"),
                "The Evil Zombie not allowed to talk only grunting is allowed. You are not allowed to use your arms. You win with the Mafia.\n" +
                        "" +
                        "The Evil Zombie the dead Godfather of the Mafia. Being brought back to life does not mean your body is still intact. As a Zombie, your tongue has decayed and you cannot talk but only make grunting sounds. Your limbs have also decayed so you cannot move your arms. ",
                "Did you know puffles are fluffy?",
                1,
                1);
        allRoles.put(zombieEvil.getName(), zombieEvil);
    }

    private static void InitializeAllHelpPrompts(){
        allHelpPrompts = new HashMap<Integer, Vector<HelpPrompt>>();

        Vector<HelpPrompt> promptsOnMainMafiaPage = new Vector<HelpPrompt>();

        HelpPrompt testPrompt = new HelpPrompt(
                R.id.DeadTitleBox,
                "I'm a prompt");
        promptsOnMainMafiaPage.add(testPrompt);

        HelpPrompt testPrompt2 = new HelpPrompt(
                R.id.BackButton,
                "This is another prompt");
        promptsOnMainMafiaPage.add(testPrompt2);

        allHelpPrompts.put(R.layout.activity_main_mafia_page, promptsOnMainMafiaPage);
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

    public static HelpPrompt GetHelpPrompt(int screenAppearsOn, int index){
        Vector<HelpPrompt> allPromptsOnScreen = allHelpPrompts.get(screenAppearsOn);
        if(index < allPromptsOnScreen.size()) {
            return allPromptsOnScreen.get(index);
        }
        else{
            return null;
        }
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
