package com.grirms.crm.crm_module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.model.ChildrenInfo;
import com.grirms.crm.crm_module.model.GroupInfo;
import com.grirms.crm.crm_module.view.ViewUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public class AdapterExpandableListView_Filtrate extends BaseExpandableListAdapter {

    private final Context ctx;
    private final List<GroupInfo> mGroupInfosLists;
    private final Map<String, List<ChildrenInfo>> mChildInfoLists;

    public AdapterExpandableListView_Filtrate(Context context, List<GroupInfo> mGroupInfoList, Map<String, List<ChildrenInfo>> mChildInfoList) {
        ctx = context;
        mGroupInfosLists = mGroupInfoList;
        mChildInfoLists = mChildInfoList;
    }

    @Override
    public int getGroupCount() {
        return mGroupInfosLists.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupID = mGroupInfosLists.get(groupPosition).getGroupID();
//        return mChildInfoLists.get(groupID).size();
        return 1;
    }

    @Override
    public GroupInfo getGroup(int groupPosition) {
        return mGroupInfosLists.get(groupPosition);
    }

    @Override
    public List<ChildrenInfo> getChild(int groupPosition, int childPosition) {
        String groupID = mGroupInfosLists.get(groupPosition).getGroupID();
        return mChildInfoLists.get(groupID);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_expandablelistview_group, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if (mGroupInfosLists.get(groupPosition).ischildIsHasRemainData()) {
            groupViewHolder.tv_child_isHas_remain.setVisibility(View.VISIBLE);
        } else {
            groupViewHolder.tv_child_isHas_remain.setVisibility(View.GONE);
        }
        groupViewHolder.tv_group_name.setText(getGroup(groupPosition).getGroupName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //空实现，否则group条目会关闭
            }
        });
        groupViewHolder.tv_child_isHas_remain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGroupInfosLists.get(groupPosition).isShow_more_data()) {
                    mGroupInfosLists.get(groupPosition).setShow_more_data(false);
                } else {
                    mGroupInfosLists.get(groupPosition).setShow_more_data(true);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = ViewUtils.setExpandableListView_ChildView(ctx, 3);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        ChildRecycleViewAdapter childRecycleViewAdapter;
        if (mGroupInfosLists.get(groupPosition).isShow_more_data()) {
            childRecycleViewAdapter = new ChildRecycleViewAdapter(ctx, getChild(groupPosition, childPosition), getChild(groupPosition, childPosition).size());
        } else {
            childRecycleViewAdapter = new ChildRecycleViewAdapter(ctx, getChild(groupPosition, childPosition), 3);
        }
        childViewHolder.expandableListView_recycleView.setAdapter(childRecycleViewAdapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        TextView tv_group_name;
        TextView tv_child_isHas_remain;

        GroupViewHolder(View view) {
            tv_group_name = view.findViewById(R.id.tv_group_name);
            tv_child_isHas_remain = view.findViewById(R.id.tv_child_isHas_remain);
        }
    }

    class ChildViewHolder {
        TextView recycleView_TextView;
        RecyclerView expandableListView_recycleView;

        ChildViewHolder(View view) {
            recycleView_TextView = view.findViewById(R.id.recycleView_TextView);
            expandableListView_recycleView = view.findViewById(R.id.expandableListView_recycleView);
        }
    }
}
