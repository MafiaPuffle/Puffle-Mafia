package com.example.pufflemafia.app.adapters.playerAdapters;

import android.content.Context;
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
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class PlayerNightUIAdaptor extends RecyclerView.Adapter<PlayerNightUIAdaptor.ViewHolder> {

    public Vector<Player> localDataSet;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
        private final LinearLayout linearLayout;
        private final LinearLayout effectsHolder;
        private final ImageView thumbsUpOrDownImageView;

        public ViewHolder(View view) {
            super(view);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.CharacterUI);
            linearLayout = (LinearLayout) view.findViewById(R.id.CharacterUIINBox);
            effectsHolder = (LinearLayout) view.findViewById(R.id.TokensLinearLayout);
            thumbsUpOrDownImageView = (ImageView) view.findViewById(R.id.thumbsUpOrDownImage);
        }

        public TextView getPlayerNameView() {
            return playerNameView;
        }

        public TextView getRoleNameView() {
            return roleNameView;
        }

        public void setRoleNameViewColor(int colorResourceId, Context context) {
            roleNameView.setTextColor(context.getResources().getColor(colorResourceId, context.getTheme()));
        }

        public ImageButton getRoleButton() {
            return roleButton;
        }

        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public ImageView getThumbsUpOrDownImageView() {
            return thumbsUpOrDownImageView;
        }

        public LinearLayout getEffectsHolder() {
            return effectsHolder;
        }

        public void setThumbsUpOrDownImageView(int imageResourceId) {
            thumbsUpOrDownImageView.setImageResource(imageResourceId);
        }

        public void removeAllEffects() {
            effectsHolder.removeAllViewsInLayout();
        }

        private int dpToPx(int dp) {
            float density = itemView.getResources().getDisplayMetrics().density;
            return Math.round((float) dp * density);
        }

        public void addEffect(int imageResource) {
            ImageButton imageButton = new ImageButton(itemView.getContext());
            imageButton.setBackgroundResource(imageResource);
            imageButton.setImageResource(0);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dpToPx(30), dpToPx(30));
            imageButton.setLayoutParams(params);
            imageButton.setClickable(false);
            imageButton.setFocusable(false);

            effectsHolder.addView(imageButton);
        }

    }

    public PlayerNightUIAdaptor(Vector<Player> dataSet, Context context) {
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


        viewHolder.getPlayerNameView().setText(player.getName());
        viewHolder.getRoleNameView().setText(player.getRole().getName());

        Role.Teams team = role.getTeam();
        switch (team) {
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
        viewHolder.getLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                // TODO select player as target for action
                Vector<Player> targets = new Vector<Player>();
                targets.add(player);
                PlayerManager.prepAction(PromptsManager.currentPlayer, PromptsManager.currentAction, targets);
            }
        });

        Role.Alliances alliance = role.getAlliance();
        switch (alliance) {
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

        viewHolder.removeAllEffects();
        for(Effect effect: player.getEffects()){
            viewHolder.addEffect(effect.getImageResource());
        }

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
