package com.example.pufflemafia.adaptors;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.AddTokenScreen;
import com.example.pufflemafia.ChangeCharacterScreen;
import com.example.pufflemafia.ChangeNameScreen;
import com.example.pufflemafia.R;
import com.example.pufflemafia.RoleDetails;
import com.example.pufflemafia.RolesScreen;
import com.example.pufflemafia.app.AppManager;
import com.example.pufflemafia.app.data.Role;
import com.example.pufflemafia.app.data.Token;
import com.example.pufflemafia.app.game.Player;
import com.example.pufflemafia.app.game.PlayerManager;

import java.util.Vector;

public class SelectableRoleUIAdaptor extends RecyclerView.Adapter<SelectableRoleUIAdaptor.ViewHolder> {

    private Vector<Role> localDataSet;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //private final TextView textView;
        private LinearLayout roleLinearLayout;
        private ImageView roleImageView;
        private TextView roleNameTextView;



        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            roleLinearLayout = (LinearLayout) view.findViewById(R.id.SelectableRoleLinearLayout);
            roleImageView = (ImageView) view.findViewById(R.id.SelectableRoleImage);
            roleNameTextView = (TextView) view.findViewById(R.id.SelectableRoleText);
        }

        public LinearLayout getRoleLinearLayout(){return roleLinearLayout;}
        public ImageView getRoleImageView(){return roleImageView;}
        public TextView getRoleNameTextView(){return roleNameTextView;}


    }

    public SelectableRoleUIAdaptor(Vector<Role> dataSet, Context context) {
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
        Role role = localDataSet.get(position);

        viewHolder.getRoleLinearLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(role);
            }
        });

        viewHolder.getRoleLinearLayout().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, RoleDetails.class);
                intent.putExtra("name", role.getName());
                intent.putExtra("imageResourceId", role.getImageResource());
                intent.putExtra("description", role.getDescription());
                context.startActivity(intent);
                return true;
            }
        });

        viewHolder.getRoleImageView().setImageResource(role.getImageResource());
        viewHolder.getRoleNameTextView().setText(role.getName());
    }

    protected void onItemClick(Role role){
        //the children will do something with this
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
