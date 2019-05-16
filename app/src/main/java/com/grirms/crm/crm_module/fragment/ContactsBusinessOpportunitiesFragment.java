package com.grirms.crm.crm_module.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.crm_module.adapter.ContactsManagementAdapter;
import com.grirms.crm.crm_module.model.ContactsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019\5\5 0005.
 */

public class ContactsBusinessOpportunitiesFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        bindID(view);
        return view;
    }
    List<ContactsModel> mList = new ArrayList<>();

    private void setData() {
        for (int i = 0; i < 100; i++) {
            ContactsModel model = new ContactsModel();
            model.setName("老张");
            model.setCompanyName("华夏麒麟公司");
            model.setIdentity("采购经理");
            mList.add(model);
        }
    }
    private void bindID(View view) {
        setData();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new ContactsManagementAdapter(getActivity(), mList));
    }
}
