package com.example.pufflemafia.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.AddTokenScreen;
import com.example.pufflemafia.ChangeCharacterScreen;
import com.example.pufflemafia.ChangeNameScreen;
import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class PlayerDayUIAdaptor extends RecyclerView.Adapter<PlayerDayUIAdaptor.ViewHolder> {

    private Vector<Player> localDataSet;
    private Context context;
    private PlayerManager.PlayerMangerListType listType;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private final LinearLayout playerAndRoleLinearLayout;
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
        private final ImageButton killOrReviveButton;

        private final LinearLayout tokenHolder;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textView);
            playerAndRoleLinearLayout = (LinearLayout) view.findViewById(R.id.CharacterUITextsBox);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.RoleUIButton);
            killOrReviveButton = (ImageButton) view.findViewById(R.id.KillOrReviveButton);
            tokenHolder = (LinearLayout) view.findViewById(R.id.TokenUIBox);
        }

        //public TextView getTextView() {
            //return textView;
        //}

        public LinearLayout getPlayerAndRoleLinearLayout(){
            return playerAndRoleLinearLayout;
        }

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


        private int dpToPx(int dp) {
            float density = itemView.getResources().getDisplayMetrics().density;
            return Math.round((float) dp * density);
        }

        public void addToken(int imageResource){
            ImageButton imageButton = new ImageButton(itemView.getContext());
            imageButton.setBackgroundResource(imageResource);
            imageButton.setImageResource(0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(30), dpToPx(30));
            imageButton.setLayoutParams(params);
            imageButton.setClickable(false);
            imageButton.setFocusable(false);

            tokenHolder.addView(imageButton);
        }
    }

    public PlayerDayUIAdaptor(Vector<Player> dataSet, Context context, PlayerManager.PlayerMangerListType listType){
        localDataSet = dataSet;
        this.context = context;
        this.listType = listType;
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


        viewHolder.getPlayerAndRoleLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChangeNameScreen.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("name",player.name);
                intent.putExtra("ListType", listType);
                context.startActivity(intent);
            }
        });

        viewHolder.getPlayerNameView().setText(player.name);
        viewHolder.getRoleNameView().setText(player.getRole().getName());
        viewHolder.getRoleButton().setBackgroundResource(role.getImageResource());
        viewHolder.getRoleButton().setImageResource(0);
        viewHolder.getRoleButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChangeCharacterScreen.class);
                intent.putExtra("currentRoleImageResource", role.getImageResource());
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("ListType", listType);
                context.startActivity(intent);
            }
        });

        if(listType == PlayerManager.PlayerMangerListType.ALIVE){
            viewHolder.getKillOrReviveButton().setBackgroundResource(R.drawable.dead_button);
        }
        else {
            viewHolder.getKillOrReviveButton().setBackgroundResource(R.drawable.alive_button);
        }
        viewHolder.getKillOrReviveButton().setImageResource(0);
        viewHolder.getKillOrReviveButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listType == PlayerManager.PlayerMangerListType.ALIVE){
                    PlayerManager.KillPlayer(player);
                }
                else {
                    PlayerManager.RevivePlayer(player);
                }
                notifyDataSetChanged();
            }
        });

        viewHolder.removeAllTokens();
        for(Token token: player.getAllTokensOnPlayer()){
            viewHolder.addToken(token.getImageResource());
        }
        viewHolder.getTokenHolder().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddTokenScreen.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("ListType", listType);
                context.startActivity(intent);
            }
        });
        //TODO: update kill/revive button to show correct image
        //TODO: update all buttons to do stuff on click
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
