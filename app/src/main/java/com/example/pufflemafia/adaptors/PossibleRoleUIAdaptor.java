package com.example.pufflemafia.adaptors;

import android.content.Context;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

public class PossibleRoleUIAdaptor extends SelectableRoleUIAdaptor{

    public Event<Boolean> onRoleWasAdded;
    public PossibleRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
        onRoleWasAdded = new Event<Boolean>();
    }


    @Override
    protected void onItemClick(Role role){
        super.onItemClick(role);
        if(AppManager.gameSetup.tryAddRole(role)){
            onRoleWasAdded.Invoke(true);
        }
        else {
            onRoleWasAdded.Invoke(false);
        }
    }
}
