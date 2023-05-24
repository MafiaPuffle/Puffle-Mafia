package com.example.pufflemafia.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.game.Player;

import java.util.Vector;

public class PlayerDayUIAdaptor extends RecyclerView.Adapter<PlayerDayUIAdaptor.ViewHolder> {

    private Vector<Player> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
        private final ImageButton killOrReviveButton;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textView);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.RoleUIButton);
            killOrReviveButton = (ImageButton) view.findViewById(R.id.KillOrReviveButton);
        }

        //public TextView getTextView() {
            //return textView;
        //}

        public TextView getPlayerNameView(){
            return playerNameView;
        }

        public TextView getRoleNameView(){
            return roleNameView;
        }

        public ImageButton getRoleButton(){
            return roleButton;
        }

        public ImageButton getKillOrReviveButton(){
            return killOrReviveButton;
        }
    }

    public PlayerDayUIAdaptor (Vector<Player> dataSet){
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_ui_day_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //TODO: update the view with the data in the localDataSet at the given position
        Player player = localDataSet.get(position);
        Role role = player.getRole();


        viewHolder.getPlayerNameView().setText(player.name);
        viewHolder.getRoleNameView().setText(player.getRole().getName());
        viewHolder.getRoleButton().setBackgroundResource(role.getImageResource());
        viewHolder.getRoleButton().setImageResource(0);
        //TODO: update kill/revive button to show correct image
        //TODO: update all buttons to do stuff on click
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
