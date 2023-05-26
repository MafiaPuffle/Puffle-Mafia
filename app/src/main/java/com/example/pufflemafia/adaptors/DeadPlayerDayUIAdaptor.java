package com.example.pufflemafia.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class DeadPlayerDayUIAdaptor extends RecyclerView.Adapter<DeadPlayerDayUIAdaptor.ViewHolder> {

    private Vector<Player> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
        private final ImageButton killOrReviveButton;

        private final LinearLayout tokenHolder;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textView);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.RoleUIButton);
            killOrReviveButton = (ImageButton) view.findViewById(R.id.KillOrReviveButton);
            tokenHolder = (LinearLayout) view.findViewById(R.id.TokenUIBox);
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

        public LinearLayout getTokenHolder(){
            return tokenHolder;
        }

        public void removeAllTokens(){
            tokenHolder.removeAllViewsInLayout();
        }

        public void addToken(int imageResource){
            ImageButton imageButton = new ImageButton(itemView.getContext());
            imageButton.setBackgroundResource(imageResource);
            imageButton.setImageResource(0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60,60);
            imageButton.setLayoutParams(params);

            tokenHolder.addView(imageButton);
        }
    }

    public DeadPlayerDayUIAdaptor(Vector<Player> dataSet){
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
        viewHolder.getKillOrReviveButton().setBackgroundResource(R.drawable.alive_button);
        viewHolder.getKillOrReviveButton().setImageResource(0);
        viewHolder.getKillOrReviveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayerManager.RevivePlayer(player);
                notifyDataSetChanged();
            }
        });

        viewHolder.removeAllTokens();
        for(Token token: player.getAllTokensOnPlayer()){
            viewHolder.addToken(token.getImageResource());
        }
        //TODO: update kill/revive button to show correct image
        //TODO: update all buttons to do stuff on click
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
