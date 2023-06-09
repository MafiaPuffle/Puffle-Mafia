package com.example.pufflemafia.adaptors.roleAdaptors;

import android.content.Context;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

public class SelectedRoleUIAdaptor extends SelectableRoleUIAdaptor{
    public SelectedRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
    }

    @Override
    protected void onItemClick(Role role){
        super.onItemClick(role);
        AppManager.gameSetup.removeRole(role);
    }
}
