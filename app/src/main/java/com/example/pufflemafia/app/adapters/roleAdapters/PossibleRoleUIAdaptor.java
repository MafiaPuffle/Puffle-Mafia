package com.example.pufflemafia.app.adapters.roleAdapters;

import android.content.Context;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.events.Event;
import com.example.pufflemafia.app.game.states.Setup;

import java.util.Vector;

public class PossibleRoleUIAdaptor extends SelectableRoleUIAdaptor {

    public Event<Boolean> onRoleWasAdded;
    public PossibleRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
        onRoleWasAdded = new Event<Boolean>();
    }


    @Override
    protected void onItemClick(Role role){
        super.onItemClick(role);
        //TODO have add role
        Setup.addRole(role);
        onRoleWasAdded.Invoke(true);
    }
}
