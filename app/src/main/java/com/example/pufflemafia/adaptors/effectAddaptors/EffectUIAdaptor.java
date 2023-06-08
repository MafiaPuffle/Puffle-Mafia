package com.example.pufflemafia.adaptors.effectAddaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;

import java.util.Vector;

public class EffectUIAdaptor extends RecyclerView.Adapter<EffectUIAdaptor.ViewHolder> {

    private Vector<String> localDataSet;
    protected Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private TextView effectDescriptionTextView;
        private View ownView;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            effectDescriptionTextView = (TextView) view.findViewById(R.id.EffectDescription);
            ownView = view;
        }

        public TextView getEffectDescriptionTextView(){return effectDescriptionTextView;}

        public View getOwnView(){return ownView;}

    }

    public EffectUIAdaptor(Vector<String> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.effect_ui, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String description = localDataSet.get(position);

        viewHolder.getEffectDescriptionTextView().setText(description);

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
