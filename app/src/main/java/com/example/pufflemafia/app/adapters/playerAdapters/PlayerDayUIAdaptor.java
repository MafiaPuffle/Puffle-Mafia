package com.example.pufflemafia.app.adapters.playerAdapters;

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

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.adapters.RecyclerRowMoverCallBack;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.effects.Effect;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.screens.EditPlayerScreen;

import java.util.Collections;
import java.util.Vector;

public class PlayerDayUIAdaptor extends RecyclerView.Adapter<PlayerDayUIAdaptor.ViewHolder> implements RecyclerRowMoverCallBack.RecyclerViewRowTouchHelperContract {

    private Vector<Player> localDataSet;
    private Context context;
    private PlayerManager.PlayerManagerListType listType;
    private boolean isInteractable;
    public void setInteractable(boolean interactable) {
        isInteractable = interactable;
    }

    @Override
    public void onRowMoved(int from, int to) {
        if(from < to)
        {
            for(int i=from; i<to; i++)
            {
                Collections.swap(localDataSet,i,i+1);
            }
        }
        else
        {
            for(int i=from; i>to; i--)
            {
                Collections.swap(localDataSet,i,i-1);
            }
        }
        notifyItemMoved(from,to);
    }

    @Override
    public void onRowSelected(ViewHolder myViewHolder) {
        myViewHolder.getMainLayout().setBackgroundResource(R.drawable.purple_rectangle);
    }

    @Override
    public void onRowClear(ViewHolder myViewHolder) {
        myViewHolder.getMainLayout().setBackgroundResource(R.drawable.grey_rectangle);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private final LinearLayout mainLayout;
        private final LinearLayout playerAndRoleLinearLayout;
        private final TextView playerNameView;
        private final TextView roleNameView;
        private final ImageButton roleButton;
//        private final ImageButton killOrReviveButton;

        private final LinearLayout effectsHolder;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            //textView = (TextView) view.findViewById(R.id.textView);
            mainLayout = (LinearLayout) view.findViewById(R.id.CharacterUIINBox);
            playerAndRoleLinearLayout = (LinearLayout) view.findViewById(R.id.CharacterUITextsBox);
            playerNameView = (TextView) view.findViewById(R.id.CharacterUIName);
            roleNameView = (TextView) view.findViewById(R.id.CharacterUIRole);
            roleButton = (ImageButton) view.findViewById(R.id.RoleUIButton);
//            killOrReviveButton = (ImageButton) view.findViewById(R.id.KillOrReviveButton);
            effectsHolder = (LinearLayout) view.findViewById(R.id.TokenUIBox);
        }

        //public TextView getTextView() {
        //return textView;
        //}

        public LinearLayout getMainLayout(){
            return mainLayout;
        }
        public LinearLayout getPlayerAndRoleLinearLayout(){
            return playerAndRoleLinearLayout;
        }

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


        public LinearLayout getEffectsHolder(){
            return effectsHolder;
        }

        public void removeAllEffects(){
            effectsHolder.removeAllViewsInLayout();
        }


        private int dpToPx(int dp) {
            float density = itemView.getResources().getDisplayMetrics().density;
            return Math.round((float) dp * density);
        }

        public void addEffect(int imageResource){
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

    public PlayerDayUIAdaptor(Vector<Player> dataSet, Context context, PlayerManager.PlayerManagerListType listType){
        localDataSet = dataSet;
        this.context = context;
        this.listType = listType;
        isInteractable = true;
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



        viewHolder.getPlayerNameView().setText(player.getName());
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

        viewHolder.removeAllEffects();
        for(Effect effect: player.getEffects()){
            viewHolder.addEffect(effect.getImageResource());
        }

//        viewHolder.getMainLayout().setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                SoundManager.playSfx("Click");
//                Intent intent = new Intent(context, PlayerEditorScreen.class);
//                intent.putExtra("playerName", player.getName());
//                context.startActivity(intent);
//
//                return false;
//            }
//        });

        viewHolder.getMainLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isInteractable) return;
                SoundManager.playSfx("Click");
                Intent intent = new Intent(context, EditPlayerScreen.class);
                intent.putExtra("playerID", player.getID().toString());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}