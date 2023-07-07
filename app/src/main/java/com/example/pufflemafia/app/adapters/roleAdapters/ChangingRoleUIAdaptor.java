package com.example.pufflemafia.app.adapters.roleAdapters;

import android.content.Context;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.events.Event;

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
