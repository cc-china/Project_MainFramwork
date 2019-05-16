package com.grirms.crm.crm_module.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.activity.CustomerManagementActivity;
import com.grirms.crm.crm_module.model.CustomerManagementModel;
import com.grirms.crm.crm_module.view.ViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2019\4\26 0026.
 */

public class CustomerManagementAdapter extends RecyclerView.Adapter<CustomerManagementAdapter.ViewHolder> {
    private final CustomerManagementActivity ctx;
    private List<CustomerManagementModel> mList;
    private final int COMMON_DATA_STATE = 0;
    private final int MAP_DATA_STATE = 1;

    public CustomerManagementAdapter(CustomerManagementActivity activity) {
        this.ctx = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == COMMON_DATA_STATE) {
            view = LayoutInflater.from(ctx).inflate(R.layout.item_customer_management, null);
        } else if (viewType == MAP_DATA_STATE) {
            view = LayoutInflater.from(ctx).inflate(R.layout.view_mapviews, null);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mList.get(position).isShowMap()) {
            ctx.onMapCreate(holder.mapView);
        } else {
            switch (mList.get(position).getStates()) {
                case "待审核":
                    holder.tv_states.setText("待审核");
                    holder.tv_states.setBackground(ctx.getResources().getDrawable(R.drawable.shape_corner_color4e80f5));
                    break;
                case "未提交":
                    holder.tv_states.setText("未提交");
                    holder.tv_states.setBackground(ctx.getResources().getDrawable(R.drawable.shape_corner_colorff9b26));
                    break;
                case "被驳回":
                    holder.tv_states.setText("被驳回");
                    holder.tv_states.setBackground(ctx.getResources().getDrawable(R.drawable.shape_corner_colorfa6470));
                    break;
                case "已通过":
                    holder.tv_states.setText("已通过");
                    holder.tv_states.setBackground(ctx.getResources().getDrawable(R.drawable.shape_corner_color00ca8d));
                    break;
            }
            if (mList.get(position).isShowAttention()) {
                holder.cb_check.setVisibility(View.VISIBLE);
            } else {
                holder.cb_check.setVisibility(View.GONE);
            }
            holder.tv_company_name.setText(mList.get(position).getName());
            holder.tv_customer_type.setText(String.format(ctx.getResources().getString(R.string.string_empty), "客户类型：", mList.get(position).getType()));
            holder.tv_customer_level.setText(String.format(ctx.getResources().getString(R.string.string_empty), "账期客户级别：", mList.get(position).getLevel()));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).isShowMap()) {
            return MAP_DATA_STATE;
        } else {
            return COMMON_DATA_STATE;
        }
    }

    public void setData(List<CustomerManagementModel> data) {
        this.mList = data;
        notifyDataSetChanged();
    }

    public void changeData(CustomerManagementModel model) {
        if (model.isShowMap()) {
            this.mList.add(0, model);
            notifyDataSetChanged();
        } else {
            this.mList.remove(0);
            notifyDataSetChanged();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_states;
        TextView tv_company_name;
        TextView tv_customer_type;
        TextView tv_customer_level;
        CheckBox cb_check;
        com.amap.api.maps.MapView mapView;

        ViewHolder(View itemView) {
            super(itemView);
            tv_states = itemView.findViewById(R.id.tv_states);
            tv_company_name = itemView.findViewById(R.id.tv_company_name);
            tv_customer_type = itemView.findViewById(R.id.tv_customer_type);
            tv_customer_level = itemView.findViewById(R.id.tv_customer_level);
            mapView = itemView.findViewById(R.id.mapView);
            cb_check = itemView.findViewById(R.id.cb_check);
        }
    }
}
