package com.grirms.crm.crm_module.activity;

import android.view.View;

import com.grirms.crm.R;
import com.grirms.crm.base.BaseActivity;

/**
 * Created by Administrator on 2019\4\30 0030.
 *
 */

public class CreateCustomerActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int setContentView() {
        return R.layout.activity_create_customer;
    }

    @Override
    protected void onCreate(View view) {
        initView();
    }

    private void initView() {
        iv_back.setVisibility(View.VISIBLE);
        tv_title.setText("新建客户");
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("保存");
        iv_select.setVisibility(View.GONE);
        iv_editor.setVisibility(View.GONE);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right:

                break;
        }
    }
}
