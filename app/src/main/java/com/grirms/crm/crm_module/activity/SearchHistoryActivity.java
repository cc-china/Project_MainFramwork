package com.grirms.crm.crm_module.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.adapter.ChildRecycleViewAdapter;
import com.grirms.crm.crm_module.model.ChildrenInfo;
import com.grirms.crm.crm_module.view.GridSpacingItemDecoration;
import com.grirms.crm.crm_module.view.MyRecycleView;
import com.grirms.crm.crm_module.view.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019\4\29 0029.
 */

public class SearchHistoryActivity extends Activity implements View.OnClickListener {

    private ChildRecycleViewAdapter adapter;
    private TextView tv_show_more_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);
        setData();
        bindID();
    }

    List<ChildrenInfo> mList = new ArrayList<>();

    private void setData() {
        for (int i = 0; i < 6; i++) {
            ChildrenInfo info = new ChildrenInfo();
            info.setChildName("喜马拉雅");
            mList.add(info);
        }
    }

    private void bindID() {
        EditText et_search = findViewById(R.id.et_search);
        TextView tv_back = findViewById(R.id.tv_back);
        tv_back.setOnClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tv_show_more_data = findViewById(R.id.tv_show_more_data);
        tv_show_more_data.setOnClickListener(this);
        int spacing = getResources().getDimensionPixelSize(R.dimen.sp_15);//每一个矩形的间距
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, spacing, false));
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        adapter = new ChildRecycleViewAdapter(this, mList, 4);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_show_more_data:
                adapter.showAllDate(mList.size());
                tv_show_more_data.setVisibility(View.GONE);
                break;
        }
    }
}
