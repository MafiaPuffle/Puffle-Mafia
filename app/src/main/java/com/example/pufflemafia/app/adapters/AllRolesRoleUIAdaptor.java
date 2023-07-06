package com.example.pufflemafia.app.adapters;

import android.content.Context;
import android.content.Intent;

import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class AllRolesRoleUIAdaptor extends SelectableRoleUIAdaptor{
    public AllRolesRoleUIAdaptor(Vector<Role> dataSet, Context context) {
        super(dataSet, context);
    }

    @Override
    protected void onItemClick(Role role){
        super.onItemClick(role);
//        SoundManager.playSfx("Click");
//        Intent intent = new Intent(context, RoleDetails.class);
//        intent.putExtra("name", role.getName());
//        intent.putExtra("imageResourceId", role.getImageResource());
//        intent.putExtra("description", role.getDescription());
//        intent.putExtra("winCondition", role.getWinCondition());
//        intent.putExtra("team", role.getTeam());
//        intent.putExtra("alliance", role.getAlliance());
//        context.startActivity(intent);
    }
}