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

    private static void LoadAllRoles()
    {
        InitializeAllDictionaries();

        LoadRole("Alien",
                "Infect one player at night. If half the players become Aliens, the Alien wins.",
                "If half of the players become infected (not including self)",
                "I'm not a fan of crop circles",
                "Player has been infected by the alien",
                "ALIEN WAKE UP, WHO DO YOU WANNA INFECT?",
                3,
                1,
                1,
                1,
                R.drawable.alien_puffle,
                R.drawable.alien_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.SELF);

        LoadRole("Apprentice",
               "Stand up ANYTIME during the day and announce that you are using one RANDOM Spell.",
               "Wins if the Mafia are voted out",
               "Bawk Bawk Baaaaaaawk",
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
                "Give bread to players at night.  If the Baker dies everyone without bread dies in 3 nights (1 night for every 5 players)",
                "Wins if the Mafia are voted out",
                "Bread is the giver of life",
                "Players with this will not die once the Baker dies",
                "BAKER WAKE UP, WHO DO YOU WANNA GIVE BREAD TO?",
                3,
                1,
                1,
                2,
                R.drawable.baker_puffle,
                R.drawable.puffle_o_croissant,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);


        LoadRole("Bribed Judge",
                "Once per game, at night choose one player, they will die during the next trial (even if another player or no player is on trial).",
                "Wins if the Mafia equal half of the alive players",
                "Even justice has a price",
                "Players with this will die during the vote no matter what.",
                "JUDGE WAKE UP, who would you like to execute during the next day vote?",
                2.1f,
                1,
                1,
                1,
                R.drawable.bribed_judge,
                R.drawable.bribed_judge,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Civilian",
                "Has no special ability",
                "Wins if the Mafia are voted out",
                "I wish had an ability",
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
               "Wins if the Mafia equal half of the alive players",
               "You have the right to remain silent",
               "This player could not vote this round",
               "COP WAKE UP, WHO WOULD YOU LIKE TO KEEP FROM VOTING THE NEXT DAY",
                2.1f,
               1,
               1,
               1,
               R.drawable.corrupt_cop,
               R.drawable.corrupt_cop,
               Token.TokenTypes.CLEAR_ON_NIGHT,
               Power.PowerType.CONTINOUS,
               Power.PowerPromptType.ALL_Alive_PLAYERS,
               Role.Alliances.EVIL,
               Role.Teams.MAFIA);

        LoadRole("Cup Bearer",
               "At the start of the game choose one player; if they die you die instead.",
               "Wins if the Mafia are voted out",
               "For the President!",
               "Cup Bearer will die in the place of this player",
               "Cup Bearer wake up, who would you like to die for?",
                .9f,
               1,
               1,
               1,
               R.drawable.cup_bearer,
               R.drawable.cup_bearer,
               Token.TokenTypes.CLEAR_NEVER,
               Power.PowerType.FIRSTNIGHT,
               Power.PowerPromptType.ALL_PLAYERS,
               Role.Alliances.GOOD,
               Role.Teams.TOWN);


        LoadRole("Cupid",
                "Links two players together, whatever happens to one happens to the other",
                "Wins if the Mafia are voted out",
                "I just love bringing people together",
                "This player is linked to anther player with this same token,  whatever happens to one, happens to the other",
                "CUPID WAKE UP, WHO DO YOU WANNA LINK TOGETHER?",
                .5f,
                1,
                1,
                2,
                R.drawable.cupid_puffle,
                R.drawable.cupid_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Cyborg",
                "At the start of the game choose one player, you are now the same role as them for the rest of the game",
                "You win the same way whatever role that was copied does",
                "Its amazing what computers can do these days!",
                "The cyborg has copied the role of this player",
                "CYBORG WAKE UP, WHOSE ROLE DO YOU WANNA COPY?",
                0.0001f,
                1,
                1,
                1,
                R.drawable.cyborg_puffle,
                R.drawable.cyborg_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.NEUTRAL,
                Role.Teams.TOWN);

        LoadRole("Dentist",
                "Choose one player to mute for the day, that player can't speak for the next day",
                "Wins if the Mafia equal half of the alive players",
                "I'm the best dentist in the world, never hear a customer complain",
                "This player has been muted and cannot talk for the day",
                "DENTIST WAKE UP, WHO DO YOU WANNA MUTE?",
                2.1f,
                1,
                1,
                1,
                R.drawable.dentist_puffle,
                R.drawable.dentist_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.MAFIA);

        LoadRole("Detective",
                "Discovers the alliance of one player each night",
                "Wins if the Mafia are voted out",
                "Never had a case I couldn't solve, except that one time.  But that doesn't count.",
                "The detective has learned this players alliance",
                "DETECTIVE WAKE UP, WHOSE ALLIANCE DO YOU WANNA KNOW ABOUT?",
                3,
                1,
                1,
                1,
                R.drawable.detective_puffle,
                R.drawable.detective_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Doctor",
                "Protect one player from the Mafia each night",
                "Wins if the Mafia are voted out",
                "This town is always so bloody",
                "This player has been protected from the mafia.  Protected players are not killed by Mafia.",
                "DOCTOR WAKE UP, WHO DO YOU WANNA SAVE FROM THE MAFIA?",
                3,
                1,
                1,
                1,
                R.drawable.doctor_puffle,
                R.drawable.doctor_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Doggie",
                "Doggie cannot die. Cannot vote. Does not count towards the count for the win condition of Mafia.\n",
                "Wins if the Mafia are voted out",
                "Bark! Bark! *wags-tail*",
                "",
                -1,
                1,
                1,
                R.drawable.doggie_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Farmer",
               "I like to plant spice crops for the town.",
               "Wins if the Mafia equal half of the alive players",
               "I like to plant spice crops for the town.",
               "You have the HOT POTATO!",
               "Farmer wake up, who would you like to give the hot potato to or, kill the player with the hot potato?",
                2.1f,
               1,
               1,
               1,
               R.drawable.farmer_puffle,
               R.drawable.potato,
               Token.TokenTypes.CLEAR_ON_NIGHT,
               Power.PowerType.CONTINOUS,
               Power.PowerPromptType.ALL_Alive_PLAYERS,
               Role.Alliances.EVIL,
               Role.Teams.MAFIA);


        LoadRole("The Father",
                "Everybody that died this round will come back to life",
                "Wins if the Mafia are voted out",
                "Let there be life!",
                "THE FATHER WAKE UP, DO YOU WANNA BRING EVERYONE BACK TO LIFE TODAY?",
                5,
                1,
                1,
                R.drawable.the_father_puffle,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Gambler",
               "Become one random role from the Extra Role Deck.\n",
               "win",
               "I'll let chance decide my fate!",
               "Gambler wake up, would you like to choose a random role from the Extra Role Deck?",
               1,
               1,
               1,
               R.drawable.gambler,
               Power.PowerType.ONETIMEUSE,
               Power.PowerPromptType.ALL_Alive_PLAYERS,
               Role.Alliances.NEUTRAL,
               Role.Teams.SELF);


        LoadRole("GodFather",
                "Kills players with the Mafia.  However the appear as a thumbs up to the detective.",
                "Wins if the Mafia equal half or more of the living players",
                "I'm gonna make him an offer he can't refuse",
                "",
                -1,
                1,
                1,
                R.drawable.godfather_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Hitman",
                "At the start of the game, choose one player, if they are voted out, you win. If target is dead by other means, you become Village Idiot.\n",
                "Wins if they vote out their target or if they are voted out as Village Idiot",
                "Target Aquired",
                "This player is targeted by the Hitman",
                "HITMAN WAKE UP, WHO DO YOU WANT TO TARGET?",
                .55f,
                1,
                1,
                1,
                R.drawable.hitman,
                R.drawable.hitman,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.SELF);
        
        LoadRole("Holy Spirit",
                "All Town players can use their abilities twice this round",
                "Wins if the Mafia are voted out",
                "The Father and I are one",
                "HOLY SPIRIT WAKE UP, DO YOU WANNA DOUBLE ALL TOWN POWERS?",
                0.75f,
                1,
                1,
                R.drawable.holy_spirit_puffle,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Insomniac",
               "Once per night, open your eyes during another players role call.",
               "Wins if the Mafia equal half of the alive players",
               "Hello ... Whose there?!",
               "",
               -1,
               1,
               1,
               R.drawable.insomniac,
               Power.PowerType.PASSIVE,
               Power.PowerPromptType.ALL_PLAYERS,
               Role.Alliances.EVIL,
               Role.Teams.MAFIA);

        LoadRole("Inquisitor",
                "You can choose one player and guess their role: if you guess right they die or if you guess wrong you die.",
                "Wins if the Mafia equal half of the alive players",
                "Let me guess who you are!",
                "This player had their role guessed by the inquisitor",
                "Inquisitor wake up, would you like to guess a player's role, what is their role?",
                2.1f,
                1,
                1,
                1,
                R.drawable.inquisitor_puffle,
                R.drawable.inquisitor_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("J.O.A.T.",
                "Can use one skill of the Town players each night.  You cannot use the same skill again until you have gone through each skill",
                "Wins if the Mafia get voted out",
                "I like to try a little of everything",
                "The J.O.A.T. has used one of their abilities on this player",
                "J.O.A.T. WAKE UP, WHAT DO YOU WANNA DO?",
                3,
                1,
                1,
                1,
                R.drawable.jack_of_all_trades,
                R.drawable.jack_of_all_trades,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Jailkeeper",
                "Choose one player each night.  That player cannot activate their ability for the night or day.",
                "Wins if the Mafia equal half of the alive players",
                "Its just part of the job",
                "This player can't use their ability for the night or day",
                "JAILKEEPER WAKE UP, WHO DO YOU WANT TO LOCKOUT FROM THEIR ROLE?",
                2.1f,
                1,
                1,
                1,
                R.drawable.jailkeeper_puffle,
                R.drawable.jailkeeper_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Jesus",
                "Comes back to life after he dies",
                "Wins if the Mafia get voted out",
                "I am the way the truth and the life",
                "",
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
                "LAWYER WAKE UP, WHO DO YOU WANNA SAVE FROM BEING VOTED OUT? You cannot choose the same player twice.",
                2.1f,
                1,
                1,
                1,
                R.drawable.lawyer_puffle,
                R.drawable.lawyer_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Lovers",
                "Lovers are linked to each other, whatever happens to one happens to the other",
                "Wins if the Mafia are voted out",
                "Romeo and Juliet got nothing on us",
                "LOVERS WAKE UP, YOU ARE LINKED FOR THE REST OF THE GAME",
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
                "TEAM MAFIA WAKE UP, WHO DO YOU WANNA KILL?",
                2,
                1,
                3,
                1,
                R.drawable.mafia_puffle,
                R.drawable.mafia_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Mafia Rival",
                "Kill one player every night",
                "Wins if the Mafia Rival players equal half of the living players and all Mafia members are dead",
                "Gun Gang for life!",
                "This player will be killed",
                "RIVAL MAFIA WAKE UP, WHO DO YOU WANNA KILL?",
                2.25f,
                1,
                2,
                1,
                R.drawable.rival_puffle,
                R.drawable.rival_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.RIVAL_MAFIA);

        LoadRole("Mole",
               "Choose one role from the Extra Role Deck. You are a thumbs up when looked at by Detective",
               "Wins if the Mafia equal half of the alive players",
               "Im the Mafia's biggest fan",
               "Mole wake up, Mafia member raise your hands to reveal your identity",
               0.25f,
               1,
               1,
               R.drawable.mole,
               Power.PowerType.FIRSTNIGHT,
               Power.PowerPromptType.NOTHING,
               Role.Alliances.EVIL,
               Role.Teams.MAFIA);

        LoadRole("Narrator",
               "The true hero of the game",
               "To enjoy the game and make everyone have fun!",
               "To enjoy the game and make everyone have fun!",
               "To enjoy the game and make everyone have fun!",
               0,
               1,
               1,
               R.drawable.game_master,
               Power.PowerType.PASSIVE,
               Power.PowerPromptType.NOTHING,
               Role.Alliances.NEUTRAL,
               Role.Teams.SELF);


        LoadRole("Necromancer",
               "Choose one dead player, Necromancer becomes that role. If they had their one time use ability activated already, they cannot use it again",
               "You win the same way whatever role that was copied does",
               "The dead shall rise!",
               "This player had their role taken by the Necromancer",
               "NECROMANCER WAKE UP, Which dead player's role would you like to become?",
               4.25f,
               1,
               1,
               1,
               R.drawable.necromancer,
               R.drawable.necromancer,
               Token.TokenTypes.CLEAR_NEVER,
               Power.PowerType.ONETIMEUSE,
               Power.PowerPromptType.ALL_DEAD_PLAYERS,
               Role.Alliances.GOOD,
               Role.Teams.SELF);

        LoadRole("Optometrist",
                "Choose one player, they must turn around or put a blindfold on during the day.\n",
                "Wins if the Mafia equal half of the alive players",
                "Open your eyes wide so I can see them!",
                "This player must turn around or wear a blindfold on during the day.",
                "Optometrist wake up, who would you like to blind?",
                2.1f,
                1,
                1,
                1,
                R.drawable.optometrist_puffle,
                R.drawable.optometrist_puffle,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Politician",
                "Choose one player, they will be forced to have the same vote as you (even if they do not vote).",
                "Wins if the Mafia equal half of the alive players",
                "HAHAHA vote for me and I will make your life better",
                "This player votes the same as the Politician",
                "POLITICIAN WAKE UP, WHOSE VOTE WOULD YOU LIKE TO STEAL?",
                2.1f,
                1,
                1,
                1,
                R.drawable.politian,
                R.drawable.politian,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("President",
                "Once per game the President can veto one vote and vote out another player instead.",
                "Wins if the Mafia are voted out",
                "AMERICA!",
                "",
                1,
                1,
                1,
                R.drawable.president_puffle,
                Power.PowerType.SELFACTIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Pyromaniac",
                "Choose one: cover one player in gasoline or kill all gasolined players. You win when you are the last one alive with Mafia.",
                "Wins if the Mafia equal half of the alive players",
                "The heat from the flame. What a pleasant feeling",
                "This player is covered in gasoline",
                "Pyromaniac wake up, who would you like to cover in gas or would you like to kill all gasolined players?",
                3,
                1,
                1,
                1,
                R.drawable.pyromaniac,
                R.drawable.pyromaniac,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.SELF);

        LoadRole("Satan",
                "Once per game, lets all Team Mafia players active their ability twice that round",
                "Wins if the Mafia equal half of the living players",
                "I will be like the most High",
                "SATAN WAKE UP, DO YOU WANNA DOUBLE ALL TEAM MAFIA POWERS?",
                0.75f,
                1,
                1,
                R.drawable.satan,
                Power.PowerType.ONETIMEUSE,
                Power.PowerPromptType.YES_OR_NO,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Shotgun Granny",
               "Decide if you will go on alert at night and kill anyone who uses their ability on you. Can only go on alert twice per game.\n",
               "Wins if the Mafia are voted out",
               "Blow em Up",
               "Shotgun Granny Wake up, DO YOU WANNA GO on alert at night and kill anyone who uses their ability on you.",
               1.01f,
               1,
               1,
               R.drawable.grandma_puffle,
               Power.PowerType.ONETIMEUSE,
               Power.PowerPromptType.YES_OR_NO,
               Role.Alliances.GOOD,
               Role.Teams.TOWN);
        
        
        LoadRole("Terrorist",
                "Plants a bomb on one player, if they die the bomb goes off, killing the player its attached to",
                "Wins if the Mafia equal half of the living players",
                "Viva la Revolution",
                "This player will die if the Terrorist dies",
                "TERRORIST WAKE UP, WHO DO YOU WANNA PLANT A BOMB ON?",
                0.5f,
                1,
                1,
                1,
                R.drawable.terrorist_puffle,
                R.drawable.terrorist_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.FIRSTNIGHT,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Tracker",
                "Choose one player, know their Role.",
                "Wins if the Mafia equal half of the alive players",
                "I know what you did",
                "This player is being tacked by the tracker.  The tracker knows who they are.",
                "TRACKER WAKE UP, WHO DO YOU WANNA TRACK?",
                2.1f,
                1,
                1,
                1,
                R.drawable.tracker,
                R.drawable.tracker,
                Token.TokenTypes.CLEAR_ON_NIGHT,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.EVIL,
                Role.Teams.MAFIA);

        LoadRole("Veteran",
                "If the Mafia tries to kill the Veteran the Mafia are killed instead",
                "Wins if the Mafia are voted out",
                "War, war never changes",
                "",
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
                "",
                -1,
                1,
                1,
                R.drawable.village_idiot_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.EVIL,
                Role.Teams.SELF);

        LoadRole("Warlock",
                "Stand up ANYTIME during the day and announce that you are choosing one Warlock Spell.",
                "Wins if the Mafia are voted out",
                "By the power vested in me I CAST THEE!",
                "",
                -1,
                1,
                1,
                R.drawable.warlock,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.ALL_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);


        LoadRole("Witness",
                "For one night they can try to peek and see who the Mafia is.",
                "Wins if the Mafia get voted out",
                "*stares into the middle distance*",
                "WTNESS WAKE UP, DID YOU WITNESS ANYTHING?",
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
                "WIZARD WAKE UP, WHO DO YOU WANNA SWITCH ROLES WITH?",
                6,
                1,
                1,
                1,
                R.drawable.wizard_puffle,
                R.drawable.wizard_puffle,
                Token.TokenTypes.CLEAR_NEVER,
                Power.PowerType.CONTINOUS,
                Power.PowerPromptType.ALL_Alive_PLAYERS,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Zombie Good",
                "You are not allowed to talk only grunting is allowed. You are not allowed to use your arms. Your original role is negated.",
                "Wins if the Mafia get voted out.",
                "Grrr...",
                "",
                -1,
                1,
                1,
                R.drawable.good_zombie_puffle,
                Power.PowerType.PASSIVE,
                Power.PowerPromptType.NOTHING,
                Role.Alliances.GOOD,
                Role.Teams.TOWN);

        LoadRole("Zombie Evil",
                "You are not allowed to talk only grunting is allowed. You are not allowed to use your arms. Your original role is negated.",
                "Wins if the Mafia equal half of the alive players",
                "Brains....",
                "",
                -1,
                1,
                1,
                R.drawable.evil_zombie_puffle,
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
