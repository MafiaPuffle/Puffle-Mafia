package com.example.pufflemafia.adaptors;

import android.content.Context;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

public class PossibleRoleUIAdaptor extends SelectableRoleUIAdaptor{
    public PossibleRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
    }

    @Override
    protected void onItemClick(Role role){
        AppManager.gameSetup.addRole(role);
    }
}
