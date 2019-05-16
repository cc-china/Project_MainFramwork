package com.grirms.crm.crm_module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.model.ChildrenInfo;
import com.grirms.crm.crm_module.view.ViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public class ChildRecycleViewAdapter extends RecyclerView.Adapter<ChildRecycleViewAdapter.ViewHolder> {
    private final Context ctx;
    private final List<ChildrenInfo> mList;
    private int itemCount;
    private OnClickListener onClickListener;

    public ChildRecycleViewAdapter(Context ctx, List<ChildrenInfo> child, int itemCount) {
        this.ctx = ctx;
        this.mList = child;
        this.itemCount = itemCount;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(ViewUtils.setRecycleView_ChildView(ctx));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mList.get(position).getClickInfo())
            holder.recycleView_TextView.setTextColor(ctx.getResources().getColor(R.color.color4E80F5));
        holder.recycleView_TextView.setText(mList.get(position).getChildName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null)
                    onClickListener.onClick(holder.getAdapterPosition());
                mList.get(holder.getAdapterPosition()).setClickInfo(true);
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size() <= itemCount ? mList.size() : itemCount;
    }

    public void showAllDate(int itemCount) {
        this.itemCount = itemCount;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView recycleView_TextView;

        public ViewHolder(View itemView) {
            super(itemView);
            recycleView_TextView = itemView.findViewById(R.id.recycleView_TextView);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    interface OnClickListener {
        void onClick(int position);
    }
}
