package com.grirms.crm.crm_module.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.grirms.crm.MainPageAdapter;
import com.grirms.crm.R;
import com.grirms.crm.base.BaseActivity;
import com.grirms.crm.crm_module.adapter.ContactsManagementAdapter;
import com.grirms.crm.crm_module.fragment.ContactsBusinessOpportunitiesFragment;
import com.grirms.crm.crm_module.fragment.ContactsDetailsFragment;
import com.grirms.crm.crm_module.model.ContactsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019\5\5 0005.
 */

public class ContactsDetailsActivity extends BaseActivity implements View.OnClickListener {

    private ContactsModel contactsModel;
    private String TAG = "ContactsDetailsActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //level one page string
    private String[] mTitles = new String[2];
    //fragment list
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected int setContentView() {
        return R.layout.activity_contacts_details;
//        return R.layout.test;
    }
    @Override
    protected void onCreate(View view) {
        getIntentData();
    }

    private void initTitleView() {
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText(contactsModel.getName());
        tv_right.setVisibility(View.GONE);
        iv_select.setVisibility(View.VISIBLE);
        iv_editor.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) iv_select.getLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        iv_select.setLayoutParams(layoutParams);
        iv_back.setOnClickListener(this);
        iv_select.setOnClickListener(this);
        iv_editor.setOnClickListener(this);
    }

    public void getIntentData() {
        contactsModel = (ContactsModel) getIntent().getSerializableExtra("ContactsModel");
        if (contactsModel == null) {
            Log.e(TAG, "Error : ContactsModel = null");
            finish();
        } else {
            initTitleView();
            initView();
        }
    }

    private void initView() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        initData();
    }

    private void initData() {
        //new tab_name
        mTitles[0] = getString(R.string.tl_crm_contacts_details_TabLayout_content_1);
        mTitles[1] = getString(R.string.tl_crm_contacts_details_TabLayout_content_2);
//        mTitles[2] = getString(R.string.tl_crm_contacts_details_TabLayout_content_3);
//        mTitles[3] = getString(R.string.tl_crm_contacts_details_TabLayout_content_4);
//        mTitles[4] = getString(R.string.tl_crm_contacts_details_TabLayout_content_5);

        //fragment
        ContactsDetailsFragment contactsDetailsFragment = new ContactsDetailsFragment();
        ContactsBusinessOpportunitiesFragment contactsBusinessOpportunitiesFragment = new ContactsBusinessOpportunitiesFragment();
        mFragments.add(contactsDetailsFragment);
        mFragments.add(contactsBusinessOpportunitiesFragment);
//        mFragments.add(contactsDetailsFragment);
//        mFragments.add(contactsDetailsFragment);
//        mFragments.add(contactsDetailsFragment);
        //add tab
        for (int i = 0; i < mTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), this, mTitles, mFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = LayoutInflater.from(this).inflate(R.layout.custom_tab_1, tabLayout, false);
            TextView tvName = view.findViewById(R.id.tv_name);
            tvName.setText(mTitles[i]);
            //setting tab（customer tabItem）
            assert tab != null;
            tab.setCustomView(view);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_select:
                startActivity(new Intent(ContactsDetailsActivity.this, SearchHistoryActivity.class));
                break;
        }
    }
}
