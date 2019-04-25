package com.grirms.crm;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017\11\13 0013.
 */

public class MainPageAdapter extends FragmentPagerAdapter {
    private Activity ctx;
    private String[] mTitleTab;
    private List<Fragment> mFragments;

    public MainPageAdapter(FragmentManager fm, Activity activity, String[] mTitles, List<Fragment> mFragments) {
        super(fm);
        this.ctx = activity;
        this.mTitleTab = mTitles;
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTitleTab.length;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitleTab[position];
//    }

}
