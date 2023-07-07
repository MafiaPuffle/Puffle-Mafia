package com.example.pufflemafia.app.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pufflemafia.app.adapters.playerAdapters.PlayerDayUIAdaptor;

public class RecyclerRowMoverCallBack extends ItemTouchHelper.Callback{
    private RecyclerViewRowTouchHelperContract touchHelperContract;

    public RecyclerRowMoverCallBack(RecyclerViewRowTouchHelperContract touchHelperContract){
        this.touchHelperContract = touchHelperContract;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return makeMovementFlags(dragFlag,0);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        this.touchHelperContract.onRowMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return false;
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE)
        {
            if(viewHolder instanceof PlayerDayUIAdaptor.ViewHolder){
                PlayerDayUIAdaptor.ViewHolder myViewHolder = (PlayerDayUIAdaptor.ViewHolder)viewHolder;
                touchHelperContract.onRowSelected(myViewHolder);
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        if(viewHolder instanceof PlayerDayUIAdaptor.ViewHolder){
            PlayerDayUIAdaptor.ViewHolder myViewHolder = (PlayerDayUIAdaptor.ViewHolder)viewHolder;
            touchHelperContract.onRowClear(myViewHolder);
        }
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    public interface RecyclerViewRowTouchHelperContract{
        void onRowMoved(int from,int to);
        void onRowSelected(PlayerDayUIAdaptor.ViewHolder myViewHolder);
        void onRowClear(PlayerDayUIAdaptor.ViewHolder myViewHolder);
    }
}