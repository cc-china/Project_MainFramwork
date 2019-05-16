package com.grirms.crm.crm_module.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.base.BaseActivity;
import com.grirms.crm.crm_module.adapter.AdapterExpandableListView_Filtrate;
import com.grirms.crm.crm_module.adapter.ContactsManagementAdapter;
import com.grirms.crm.crm_module.model.ChildrenInfo;
import com.grirms.crm.crm_module.model.ContactsModel;
import com.grirms.crm.crm_module.model.GroupInfo;
import com.grirms.crm.view.MyRadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019\4\29 0029.
 */

public class ContactsManagementActivity extends BaseActivity implements View.OnClickListener {
    private MyRadioButton mrb_sort, mrb_filtrate, mrb_map;
    private ImageView iv_add;

    @Override
    protected int setContentView() {
        return R.layout.activity_customer_management;
    }

    @Override
    protected void onCreate(View view) {
        setData();
        initView();
    }

    List<ContactsModel> mList = new ArrayList<>();

    private void setData() {
        for (int i = 0; i < 10; i++) {
            ContactsModel model = new ContactsModel();
            model.setName("老张");
            model.setCompanyName("华夏麒麟公司");
            model.setIdentity("采购经理");
            mList.add(model);
        }

        //set filtrate(when onclick RadioButton) for ExpandableListView data
        for (int i = 0; i < 10; i++) {
            GroupInfo groupInfo = new GroupInfo();
            List<ChildrenInfo> mChildrenList = new ArrayList<>();
            groupInfo.setGroupID(i + "");
            groupInfo.setGroupName(i + "_组名称");
            groupInfo.setchildIsHasRemainData(true);
            groupInfo.setShow_more_data(false);
            mGroupInfoList.add(groupInfo);
            for (int j = 0; j < 6; j++) {
                ChildrenInfo childrenInfo = new ChildrenInfo();
                childrenInfo.setGroupID(i + "");
                childrenInfo.setChildID(j + "");
                childrenInfo.setChildName(j + "_孩子名称");
                childrenInfo.setClickInfo(false);
                mChildrenList.add(childrenInfo);
            }
            mChildInfoList.put(groupInfo.getGroupID(), mChildrenList);
        }
    }

    private void initView() {
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("联系人管理");
        tv_right.setVisibility(View.GONE);
        iv_select.setVisibility(View.VISIBLE);
        iv_editor.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_select.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        iv_select.setLayoutParams(layoutParams);
        mrb_sort = findViewById(R.id.mrb_sort);
        mrb_filtrate = findViewById(R.id.mrb_filtrate);
        mrb_map = findViewById(R.id.mrb_map);
        mrb_map.setVisibility(View.GONE);

        iv_add = findViewById(R.id.iv_add);
        mrb_filtrate.setOnClickListener(this);
        mrb_sort.setOnClickListener(this);
        mrb_map.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        iv_select.setOnClickListener(this);
        iv_editor.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        tv_right.setOnClickListener(this);

        RecyclerView recycleView = findViewById(R.id.recycleView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(manager);
        recycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        ContactsManagementAdapter contactsManagementAdapter = new ContactsManagementAdapter(this, mList);
        recycleView.setAdapter(contactsManagementAdapter);
        contactsManagementAdapter.setOnItemClickListener(new ContactsManagementAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(ContactsManagementActivity.this,
                        ContactsDetailsActivity.class);
                intent.putExtra("ContactsModel",mList.get(position));
                startActivity(intent);
            }
        });
    }

    List<GroupInfo> mGroupInfoList = new ArrayList<>();
    Map<String, List<ChildrenInfo>> mChildInfoList = new HashMap<>();

    private void pop_Up_Window_filtrate() {
        final PopupWindow window = new PopupWindow(getApplicationContext());
        window.setBackgroundDrawable(new BitmapDrawable());
        final View view = View.inflate(getApplicationContext(), R.layout.pop_filtrate_customer_management, null);
        window.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(view);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.showAtLocation(getWindow().getDecorView(), Gravity.START, 0, 0);
        View view1 = view.findViewById(R.id.view);
        View tv_reset = view.findViewById(R.id.tv_reset);
        ExpandableListView expandableListView = view.findViewById(R.id.expandableListView);
        assigmentForExpandableListView_Filtrate(expandableListView, tv_reset);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

    }

    private void assigmentForExpandableListView_Filtrate(ExpandableListView listView, View tv_reset) {
        final AdapterExpandableListView_Filtrate adapter = new AdapterExpandableListView_Filtrate(this, mGroupInfoList, mChildInfoList);
        listView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mGroupInfoList.size(); i++) {
                    List<ChildrenInfo> childrenInfoList = mChildInfoList.get(mGroupInfoList.get(i).getGroupID());
                    for (int j = 0; j < childrenInfoList.size(); j++) {
                        childrenInfoList.get(j).setClickInfo(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private boolean[] sortArrays = {false, false, false};

    private void pop_Up_Window_sort() {
        final PopupWindow window = new PopupWindow(getApplicationContext());
        window.setBackgroundDrawable(new BitmapDrawable());
        final View view = View.inflate(getApplicationContext(), R.layout.pop_sort_customer_management, null);
        final TextView tv_initial = view.findViewById(R.id.tv_initial);
        final TextView tv_change_time = view.findViewById(R.id.tv_change_time);
        final TextView tv_create_time = view.findViewById(R.id.tv_create_time);
        tv_initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortArrays[0] = true;
                sortArrays[1] = false;
                sortArrays[2] = false;
                tv_initial.setTextColor(getResources().getColor(R.color.color4E80F5));
                tv_change_time.setTextColor(getResources().getColor(R.color.color333333));
                tv_create_time.setTextColor(getResources().getColor(R.color.color333333));
            }
        });
        tv_change_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortArrays[0] = false;
                sortArrays[1] = true;
                sortArrays[2] = false;
                tv_initial.setTextColor(getResources().getColor(R.color.color333333));
                tv_change_time.setTextColor(getResources().getColor(R.color.color4E80F5));
                tv_create_time.setTextColor(getResources().getColor(R.color.color333333));
            }
        });
        tv_create_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortArrays[0] = false;
                sortArrays[1] = false;
                sortArrays[2] = true;
                tv_initial.setTextColor(getResources().getColor(R.color.color333333));
                tv_change_time.setTextColor(getResources().getColor(R.color.color333333));
                tv_create_time.setTextColor(getResources().getColor(R.color.color4E80F5));
            }
        });
        tv_initial.setTextColor(sortArrays[0] ? getResources().getColor(R.color.color4E80F5) : getResources().getColor(R.color.color333333));
        tv_change_time.setTextColor(sortArrays[1] ? getResources().getColor(R.color.color4E80F5) : getResources().getColor(R.color.color333333));
        tv_create_time.setTextColor(sortArrays[2] ? getResources().getColor(R.color.color4E80F5) : getResources().getColor(R.color.color333333));
        View view1 = view.findViewById(R.id.view);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        window.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(view);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        window.showAsDropDown(this.mrb_sort);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.mrb_filtrate:
                pop_Up_Window_filtrate();
                break;
            case R.id.mrb_sort:
                pop_Up_Window_sort();
                break;
            case R.id.iv_select:
                startActivity(new Intent(ContactsManagementActivity.this, SearchHistoryActivity.class));
                break;
            case R.id.iv_add:
                startActivity(new Intent(ContactsManagementActivity.this, CreateContactsActivity.class));
                break;
        }
    }

}
