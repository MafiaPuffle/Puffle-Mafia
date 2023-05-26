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
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class PlayerNightUIAdaptor extends RecyclerView.Adapter<PlayerNightUIAdaptor.ViewHolder> {

    private Vector<Player> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
        private final LinearLayout linearLayout;
        private final LinearLayout tokenHolder;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textView);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.CharacterUI);
            linearLayout = (LinearLayout) view.findViewById(R.id.CharacterUIINBox);
            tokenHolder = (LinearLayout) view.findViewById(R.id.TokensLinearLayout);
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

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public void removeAllTokens(){
            tokenHolder.removeAllViewsInLayout();
        }

        private int dpToPx(int dp) {
            float density = itemView.getResources().getDisplayMetrics().density;
            return Math.round((float) dp * density);
        }

        public void addToken(int imageResource) {
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

    public PlayerNightUIAdaptor(Vector<Player> dataSet){
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.character_ui_night_layout, viewGroup, false);
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
        viewHolder.getLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PlayerManager.AddTokenToPlayer(PlayerManager.PlayerMangerListType.ALIVE,viewHolder.getAdapterPosition(), GameManager.getCurrentRoleActiveAtNight().getPower().getToken());
                PlayerManager.UseAbilityOnPlayer(GameManager.getCurrentRoleActiveAtNight(), PlayerManager.allAlive.elementAt(viewHolder.getAdapterPosition()));
                notifyDataSetChanged();
                PlayerManager.LogSummary();
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
