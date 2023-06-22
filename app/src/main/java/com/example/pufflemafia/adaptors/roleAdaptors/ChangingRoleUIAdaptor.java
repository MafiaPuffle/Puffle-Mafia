package com.example.pufflemafia.adaptors.roleAdaptors;

import android.content.Context;

import com.example.pufflemafia.app.events.Event;
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
        super.onItemClick(role);
        onSelectRole.Invoke(role);
    }
}
