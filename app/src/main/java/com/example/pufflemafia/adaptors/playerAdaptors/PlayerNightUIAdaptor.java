package com.example.pufflemafia.adaptors.playerAdaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.RoleDetails;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.GameManager;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class PlayerNightUIAdaptor extends RecyclerView.Adapter<PlayerNightUIAdaptor.ViewHolder> {

    public Vector<Player> localDataSet;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
        private final LinearLayout linearLayout;
        private final LinearLayout tokenHolder;
        private final ImageView thumbsUpOrDownImageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textView);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.CharacterUI);
            linearLayout = (LinearLayout) view.findViewById(R.id.CharacterUIINBox);
            tokenHolder = (LinearLayout) view.findViewById(R.id.TokensLinearLayout);
            thumbsUpOrDownImageView = (ImageView) view.findViewById(R.id.thumbsUpOrDownImage);
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
        public void setRoleNameViewColor(int colorResourceId, Context context){
            roleNameView.setTextColor(context.getResources().getColor(colorResourceId, context.getTheme()));
        }

        public ImageButton getRoleButton(){
            return roleButton;
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public ImageView getThumbsUpOrDownImageView(){
            return thumbsUpOrDownImageView;
        }

        public void setThumbsUpOrDownImageView(int imageResourceId){
            //thumbsUpOrDownImageView.setBackgroundResource(imageResourceId);
            thumbsUpOrDownImageView.setImageResource(imageResourceId);
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

    public PlayerNightUIAdaptor(Vector<Player> dataSet, Context context){
        localDataSet = dataSet;
        this.context = context;
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

        Role.Teams team = role.getTeam();
        switch (team){
            case TOWN:
                viewHolder.setRoleNameViewColor(R.color.white, context);
                break;
            case MAFIA:
                viewHolder.setRoleNameViewColor(R.color.red, context);
                break;
            case RIVAL_MAFIA:
                viewHolder.setRoleNameViewColor(R.color.red, context);
                break;
            case SELF:
                viewHolder.setRoleNameViewColor(R.color.black, context);
                break;
            case NEUTRAL:
                viewHolder.setRoleNameViewColor(R.color.black, context);
                break;
        }

        viewHolder.getRoleButton().setBackgroundResource(role.getImageResource());
        viewHolder.getRoleButton().setImageResource(0);
        viewHolder.getRoleButton().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(context, RoleDetails.class);
                intent.putExtra("name", role.getName());
                intent.putExtra("imageResourceId", role.getImageResource());
                intent.putExtra("description", role.getDescription());
                intent.putExtra("winCondition", role.getWinCondition());
                intent.putExtra("team", role.getTeam());
                intent.putExtra("alliance", role.getAlliance());
                context.startActivity(intent);
                return false;
            }
        });
        viewHolder.getLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                PlayerManager.UseAbilityOnPlayer(GameManager.getCurrentRoleActiveAtNight(), player);
                notifyDataSetChanged();
                PlayerManager.LogSummary();
            }
        });

        viewHolder.removeAllTokens();
        for(Token token: player.getAllTokensOnPlayer()){
            viewHolder.addToken(token.getImageResource());
        }

        Role.Alliances alliance = role.getAlliance();
        switch (alliance){
            case GOOD:
                viewHolder.setThumbsUpOrDownImageView(R.drawable.thumbs_up);
                break;
            case EVIL:
                viewHolder.setThumbsUpOrDownImageView(R.drawable.thumbs_down);
                break;
            case NEUTRAL:
                viewHolder.setThumbsUpOrDownImageView(R.drawable.fist_sideways);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
