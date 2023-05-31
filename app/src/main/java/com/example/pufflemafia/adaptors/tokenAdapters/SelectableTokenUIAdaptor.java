package com.example.pufflemafia.adaptors.tokenAdapters;

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
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.SoundManager;

import java.util.Vector;

public class SelectableTokenUIAdaptor extends RecyclerView.Adapter<SelectableTokenUIAdaptor.ViewHolder> {

    private Vector<Token> localDataSet;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private LinearLayout tokenLinearLayout;
        private ImageView tokenImageView;
        private TextView tokenNameTextView;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            tokenLinearLayout = (LinearLayout) view.findViewById(R.id.SelectableRoleLinearLayout);
            tokenImageView = (ImageView) view.findViewById(R.id.SelectableRoleImage);
            tokenNameTextView = (TextView) view.findViewById(R.id.SelectableRoleText);
        }

        public LinearLayout getTokenLinearLayout(){return tokenLinearLayout;}
        public ImageView getTokenImageView(){return tokenImageView;}
        public TextView getTokenNameTextView(){return tokenNameTextView;}


    }

    public SelectableTokenUIAdaptor(Vector<Token> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.selectable_role_ui, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //TODO: update the view with the data in the localDataSet at the given position
        Token token = localDataSet.get(position);

        viewHolder.getTokenLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundManager.playSfx("Click");
                onItemClick(token);
            }
        });

//        viewHolder.getTokenLinearLayout().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                SoundManager.playSfx("Click");
//                Intent intent = new Intent(context, TokenDetails.class);
//                intent.putExtra("name", token.getName());
//                intent.putExtra("imageResourceId", token.getImageResource());
//                intent.putExtra("description", token.getDescription());
//                context.startActivity(intent);
//                return true;
//            }
//        });

        viewHolder.getTokenImageView().setImageResource(token.getImageResource());
        viewHolder.getTokenNameTextView().setText(token.getName());
    }

    protected void onItemClick(Token token){
        //the children will do something with this
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
