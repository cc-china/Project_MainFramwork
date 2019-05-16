package com.grirms.crm.crm_module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.activity.ContactsManagementActivity;
import com.grirms.crm.crm_module.model.ContactsModel;

import java.util.List;

/**
 * Created by Administrator on 2019\4\29 0029.
 */

public class ContactsManagementAdapter extends RecyclerView.Adapter<ContactsManagementAdapter.ViewHolder> {
    private final Context ctx;
    private final List<ContactsModel> mList;
    private OnItemClickListener onItemClickListener;

    public ContactsManagementAdapter(Context context, List<ContactsModel> mList) {
        this.ctx = context;
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_contacts_management, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv_name.setText(mList.get(position).getName());
        holder.tv_identity.setText(mList.get(position).getIdentity());
        holder.tv_company_name.setText(mList.get(position).getCompanyName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_identity;
        TextView tv_company_name;
        ImageView iv_phone;

        ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_identity = itemView.findViewById(R.id.tv_identity);
            tv_company_name = itemView.findViewById(R.id.tv_company_name);
            iv_phone = itemView.findViewById(R.id.iv_phone);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
