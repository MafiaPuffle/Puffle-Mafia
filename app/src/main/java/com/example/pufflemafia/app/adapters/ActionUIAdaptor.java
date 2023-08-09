package com.example.pufflemafia.app.adapters;

import android.content.Context;
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

import java.util.Vector;

public class ActionUIAdaptor extends RecyclerView.Adapter<ActionUIAdaptor.ViewHolder> {

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

    public ActionUIAdaptor(Vector<Action> dataSet, Context context) {
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
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
