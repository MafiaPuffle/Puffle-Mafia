package com.example.pufflemafia.app.adapters;

import static com.example.pufflemafia.app.CustomAppCompatActivityWrapper.instance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.screens.PromptScreen;
import com.example.pufflemafia.app.screens.SelectPlayerAbility;

import java.util.UUID;
import java.util.Vector;

public class ActionUIAdaptor extends RecyclerView.Adapter<ActionUIAdaptor.ViewHolder> {

    Player player;
    private Vector<Action> localDataSet;
    protected Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private LinearLayout actionLinearLayout;
        private ImageView roleImageView;
        private TextView actionNameTextView;
        private View ownView;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            actionLinearLayout = (LinearLayout) view.findViewById(R.id.ActionUIINBox);
            actionNameTextView = (TextView) view.findViewById(R.id.Action_Name);
            ownView = view;
        }

        public LinearLayout getActionLinearLayout() {
            return actionLinearLayout;
        }

        public TextView getActionNameTextView() {
            return actionNameTextView;
        }

        public View getOwnView() {
            return ownView;
        }

    }

    public ActionUIAdaptor(UUID playerID, Vector<Action> dataSet, Context context) {
        player = PlayerManager.getPlayerByID(playerID);
        localDataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ability_ui, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //TODO: update the view with the data in the localDataSet at the given position
        Action action = localDataSet.get(position);

        viewHolder.getActionNameTextView().setText(action.getName());

        viewHolder.getActionLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                Intent intent = new Intent(instance, PromptScreen.class);
                PromptsManager.Start(player,action);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
