package com.example.pufflemafia.app;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.adaptors.playerAdaptors.PlayerDayUIAdaptor;
import com.example.pufflemafia.adaptors.playerAdaptors.PlayerNightUIAdaptor;

public class ViewToPointTo {
    public enum ViewClickType {NONE, NORMAL, LONG}
    public enum ViewToPointToType {NORMAL, RECYCLE_VIEW, SOMETHING_IN_RECYCLER_VIEW}
    public enum ViewToPointToFlags {DAY_NAME_AND_ROLE_LINEARLAYOUT, DAY_ROLE_BUTTON, DAY_TOKEN_HOLDER, DAY_KILL_OR_REVIVE_BUTTON, NIGHT}
    private ViewClickType clickType;
    public ViewClickType getClickType () {return clickType;}
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
            try{
                PlayerDayUIAdaptor.ViewHolder viewHolder = (PlayerDayUIAdaptor.ViewHolder) recyclerView.findViewHolderForAdapterPosition(index);

                switch (flag) {
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
            }catch (Exception e){
                PlayerNightUIAdaptor.ViewHolder viewHolder = (PlayerNightUIAdaptor.ViewHolder) recyclerView.findViewHolderForAdapterPosition(index);

                return viewHolder.getLinearLayout();
            }
        }
    }

    public ViewToPointTo (View view, String prompt, ViewClickType clickType){
        this.type = ViewToPointTo.ViewToPointToType.NORMAL;
        this.view = view;
        this.prompt = prompt;
        this.clickType = clickType;
    }

    public ViewToPointTo(RecyclerView recyclerView, int index, String prompt, ViewClickType clickType){
        this.type = ViewToPointTo.ViewToPointToType.RECYCLE_VIEW;
        this.recyclerView = recyclerView;
        this.index = index;
        this.prompt = prompt;
        this.clickType = clickType;
    }

    public ViewToPointTo(RecyclerView recyclerView, int index, ViewToPointToFlags flag, String prompt, ViewClickType clickType){
        this.type = ViewToPointToType.SOMETHING_IN_RECYCLER_VIEW;
        this.recyclerView = recyclerView;
        this.index = index;
        this.flag = flag;
        this.prompt = prompt;
        this.clickType = clickType;
    }
}
