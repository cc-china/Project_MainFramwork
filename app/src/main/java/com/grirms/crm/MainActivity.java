package com.grirms.crm;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.grirms.crm.crm_module.fragment.CRMFragment;
import com.grirms.crm.my_module.fragment.MyFragment;
import com.grirms.crm.work_module.fragment.WorkFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_fragment;
    private TabLayout tl_tab;
    //level one page string
    private String[] mTitles = new String[3];
    //fragment list
    private List<Fragment> mFragments = new ArrayList<>();
    //Tab_icon---Icon selector
    private int[] mIconSelectorIds = {R.drawable.icon_work_selector
            , R.drawable.icon_work_selector
            , R.drawable.icon_work_selector};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
        initPageAdapter();
    }

    private void bindID() {
        vp_fragment = findViewById(R.id.vp_fragment);
        tl_tab = findViewById(R.id.tl_tab);
    }

    private void initPageAdapter() {
        //new tab_name
        mTitles[0] = getString(R.string.tl_work);
        mTitles[1] = getString(R.string.tl_CRM);
        mTitles[2] = getString(R.string.tl_MY);
        //fragment
        WorkFragment workFragment = new WorkFragment();
        CRMFragment crmFragment = new CRMFragment();
        MyFragment myFragment = new MyFragment();
        mFragments.add(workFragment);
        mFragments.add(crmFragment);
        mFragments.add(myFragment);
        //add tab
        for (int i = 0; i < mTitles.length; i++) {
            tl_tab.addTab(tl_tab.newTab());
        }
        MainPageAdapter adapter = new MainPageAdapter(getSupportFragmentManager(), this, mTitles, mFragments);
        vp_fragment.setAdapter(adapter);
        tl_tab.setupWithViewPager(vp_fragment);
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = tl_tab.getTabAt(i);
            View view = LayoutInflater.from(this).inflate(R.layout.custom_tab, tl_tab,false);
            TextView tvName = view.findViewById(R.id.tv_name);
            tvName.setText(mTitles[i]);
            ImageView ivIcon = view.findViewById(R.id.iv_icon);
            ivIcon.setImageResource(mIconSelectorIds[i]);
            //setting tab（customer tabItem）
            assert tab !=null;
            tab.setCustomView(view);
        }

    }
}
