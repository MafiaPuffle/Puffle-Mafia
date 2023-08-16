package com.example.pufflemafia.app.adapters;

import static com.example.pufflemafia.app.CustomAppCompatActivityWrapper.instance;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.R;
import com.example.pufflemafia.app.data.actions.Action;
import com.example.pufflemafia.app.data.prompts.OptionsPrompt;
import com.example.pufflemafia.app.game.PlayerManager;
import com.example.pufflemafia.app.game.PromptsManager;
import com.example.pufflemafia.app.game.SoundManager;
import com.example.pufflemafia.app.screens.PromptScreen;

import java.util.UUID;
import java.util.Vector;

public class ButtonAdaptor extends RecyclerView.Adapter<ButtonAdaptor.ViewHolder> {

    OptionsPrompt optionsPrompt;
    protected Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private LinearLayout layout;
        private Button button;


        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            layout = (LinearLayout) view.findViewById(R.id.ButtonLayout);
            button = (Button) view.findViewById(R.id.Button);
        }

        public LinearLayout getLayout() {
            return layout;
        }

        public TextView getButton() {
            return button;
        }

    }

    public ButtonAdaptor(OptionsPrompt optionsPrompt, Context context) {
        this.optionsPrompt = optionsPrompt;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.button_ui, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String optionName = optionsPrompt.getOptions().get(position).first;
        int index = position;

        viewHolder.getButton().setText(optionName);
        viewHolder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionsPrompt.chooseOption(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionsPrompt.numberOfOptions();
    }
}
