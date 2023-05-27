package com.example.pufflemafia.adaptors;

import android.content.Context;

import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.Event;
import com.example.pufflemafia.app.data.Role;

import java.util.Vector;

public class ChangingRoleUIAdaptor extends SelectableRoleUIAdaptor{
    public Event<Role> onSelectRole;

    public ChangingRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
        onSelectRole = new Event<Role>();
    }

    @Override
    protected void onItemClick(Role role){
        onSelectRole.Invoke(role);
    }
}
