package com.grirms.crm.crm_module.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.activity.BusinessManagementActivity;
import com.grirms.crm.crm_module.activity.ContactsManagementActivity;
import com.grirms.crm.crm_module.activity.CustomerManagementActivity;

/**
 * Created by Administrator on 2019\4\24 0024.
 */

public class CRMFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_crm, container, false);
        bindID(view);
//        startActivity(new Intent(getActivity(),CustomerManagementActivity.class));
//        startActivity(new Intent(getActivity(),ContactsManagementActivity.class));
        startActivity(new Intent(getActivity(),BusinessManagementActivity.class));
        return view;
    }

    private void bindID(View view) {
        ImageView iv_back = view.findViewById(R.id.iv_back);
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_right = view.findViewById(R.id.tv_right);
        ImageView iv_select = view.findViewById(R.id.iv_select);
        ImageView iv_editor = view.findViewById(R.id.iv_editor);
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("客户管理");
        iv_select.setVisibility(View.VISIBLE);
        iv_editor.setVisibility(View.VISIBLE);

    }
}
