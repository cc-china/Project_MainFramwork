package com.grirms.crm.crm_module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.model.BusinessModel;
import com.grirms.crm.crm_module.model.ContactsModel;

import java.util.List;

/**
 * Created by Administrator on 2019\4\29 0029.
 */

public class BusinessManagementAdapter extends RecyclerView.Adapter<BusinessManagementAdapter.ViewHolder> {
    private final Context ctx;
    private final List<BusinessModel> mList;
    private OnItemClickListener onItemClickListener;

    public BusinessManagementAdapter(Context context, List<BusinessModel> mList) {
        this.ctx = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(ctx).inflate(R.layout.header_item_business_management, null);
        } else {
            view = LayoutInflater.from(ctx).inflate(R.layout.item_contacts_management, null);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (position == 0) {

        } else {
            holder.tv_name.setText(mList.get(position).getItemName());
            holder.tv_states.setText(mList.get(position).getState());
            holder.tv_company_name.setText(mList.get(position).getCustomerCompanyName());
            holder.tv_estimated_sales_quota.setText(mList.get(position).getEstimatedSalesQuota());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_states;
        TextView tv_company_name;
        TextView tv_estimated_sales_quota;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_states = itemView.findViewById(R.id.tv_states);
            tv_company_name = itemView.findViewById(R.id.tv_company_name);
            tv_estimated_sales_quota = itemView.findViewById(R.id.tv_estimated_sales_quota);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
