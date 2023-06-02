package com.example.pufflemafia.app;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class ViewToPointTo {
    public enum ViewToPointToType {NORMAL, RECYCLE_VIEW}
    private ViewToPointTo.ViewToPointToType type;
    private View view;
    private RecyclerView recyclerView;
    private int index;
    private String prompt;
    public String getPrompt(){return prompt;}

    public View getPointsTo(){
        if(type == ViewToPointTo.ViewToPointToType.NORMAL){
            return view;
        }
        else{
            return recyclerView.getLayoutManager().findViewByPosition(index);
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
}
