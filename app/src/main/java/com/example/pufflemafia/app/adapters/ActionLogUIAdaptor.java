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
import com.example.pufflemafia.app.data.actions.ActionLog;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.screens.PromptScreen;

import java.util.UUID;
import java.util.Vector;

public class ActionLogUIAdaptor extends RecyclerView.Adapter<ActionLogUIAdaptor.ViewHolder> {
    private Vector<ActionLog> localDataSet;
    protected Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private LinearLayout logLinearLayout;
        private ImageView roleImageView;
        private TextView logTextView;
        private View ownView;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            logLinearLayout = (LinearLayout) view.findViewById(R.id.ActionUIINBox);
            logTextView = (TextView) view.findViewById(R.id.LogText);
            ownView = view;
        }

        public LinearLayout getLogLinearLayout() {
            return logLinearLayout;
        }

        public TextView getLogTextView() {
            return logTextView;
        }

        public View getOwnView() {
            return ownView;
        }

    }

    public ActionLogUIAdaptor(Vector<ActionLog> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.action_log_ui, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //TODO: update the view with the data in the localDataSet at the given position
        ActionLog log = localDataSet.get(position);

        viewHolder.getLogTextView().setText(log.read());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
