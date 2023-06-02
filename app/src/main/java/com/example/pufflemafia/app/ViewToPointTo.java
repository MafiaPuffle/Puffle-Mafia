package com.example.pufflemafia.app;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.playerAdaptors.PlayerDayUIAdaptor;

public class ViewToPointTo {
    public enum ViewToPointToType {NORMAL, RECYCLE_VIEW, SOMETHING_IN_RECYCLER_VIEW}
    public enum ViewToPointToFlags {DAY_NAME_AND_ROLE_LINEARLAYOUT, DAY_ROLE_BUTTON, DAY_TOKEN_HOLDER, DAY_KILL_OR_REVIVE_BUTTON}
    private ViewToPointToType type;
    private ViewToPointToFlags flag;
    private View view;
    private RecyclerView recyclerView;
    private int index;
    private String prompt;
    public String getPrompt(){return prompt;}

    public View getPointsTo(){
        if(type == ViewToPointTo.ViewToPointToType.NORMAL){
            return view;
        }
        if(type == ViewToPointToType.RECYCLE_VIEW){
            return recyclerView.getLayoutManager().findViewByPosition(index);
        }
        else {
            PlayerDayUIAdaptor.ViewHolder viewHolder = (PlayerDayUIAdaptor.ViewHolder) recyclerView.findViewHolderForAdapterPosition(index);
            switch (flag){
                case DAY_KILL_OR_REVIVE_BUTTON:
                    return viewHolder.getKillOrReviveButton();
                case DAY_ROLE_BUTTON:
                    return viewHolder.getRoleButton();
                case DAY_NAME_AND_ROLE_LINEARLAYOUT:
                    return viewHolder.getPlayerAndRoleLinearLayout();
                case DAY_TOKEN_HOLDER:
                    return viewHolder.getTokenHolder();
                default:
                    return null;
            }
        }
    }

    public ViewToPointTo (View view, String prompt){
        this.type = ViewToPointTo.ViewToPointToType.NORMAL;
        this.view = view;
        this.prompt = prompt;
    }

    public ViewToPointTo(RecyclerView recyclerView, int index, String prompt){
        this.type = ViewToPointTo.ViewToPointToType.RECYCLE_VIEW;
        this.recyclerView = recyclerView;
        this.index = index;
        this.prompt = prompt;
    }

    public ViewToPointTo(RecyclerView recyclerView, int index, ViewToPointToFlags flag, String prompt){
        this.type = ViewToPointToType.SOMETHING_IN_RECYCLER_VIEW;
        this.recyclerView = recyclerView;
        this.index = index;
        this.flag = flag;
        this.prompt = prompt;
    }
}
