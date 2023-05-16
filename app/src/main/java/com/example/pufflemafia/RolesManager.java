package com.example.pufflemafia;

import java.util.Dictionary;

public class RolesManager {
    private static Dictionary<String, Role> allRoles;
    public static Role getRole(String name) { return allRoles.get(name); }
    public static boolean DoesRoleExist(String name)
    {
        return allRoles.get(name) != null;
    }

    public RolesManager(){
        this.Initialize();
    }

    private void Initialize(){
        //TODO add all mafia roles here
        Role mafia = new Role(2.0f,
                                Role.Alliances.EVIL,
                                Role.Teams.MAFIA,
                                Power.PowerType.CONTINOUS,
                                "kill",
                                    "mafia");
        allRoles.put("Mafia", mafia);

        Role townsFolk = new Role(0.0f,
                Role.Alliances.GOOD,
                Role.Teams.TOWN,
                Power.PowerType.PASSIVE,
                "hug",
                "Towns Folk");
        allRoles.put("Towns Folk", townsFolk);
    }
}
