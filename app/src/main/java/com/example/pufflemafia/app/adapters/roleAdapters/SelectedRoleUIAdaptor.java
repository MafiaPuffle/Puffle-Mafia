package com.example.pufflemafia.app.adapters.roleAdapters;

import android.content.Context;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.states.Setup;

import java.util.Vector;

public class SelectedRoleUIAdaptor extends SelectableRoleUIAdaptor {

    public SelectedRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
    }

    @Override
    protected void onItemClick(Role role){
        super.onItemClick(role);
        Setup.removeRole(role);
    }
}
