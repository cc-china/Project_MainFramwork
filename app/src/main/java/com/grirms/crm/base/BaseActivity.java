package com.grirms.crm.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.grirms.crm.R;
import com.grirms.crm.view.MyRadioButton;

/**
 * Created by Administrator on 2019\4\26 0026.
 * in the inheritance system,use setContentView() function replace onCreate() in the lifecycle
 *
 * @Warning in BaseActivity including the title bar of the xml layout,so Do Not add
 * title bar layout again
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected ImageView iv_back, iv_select, iv_editor;
    protected TextView tv_right, tv_title;
    protected Bundle savedInstanceState;
    private ViewStub vs_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.activity_base);
        bindID();
        vs_content.setLayoutResource(setContentView());
        vs_content.inflate();
        onCreate(LayoutInflater.from(this).inflate(R.layout.activity_base, null));
    }

    private void bindID() {
        iv_back = findViewById(R.id.iv_back);
        tv_title = findViewById(R.id.tv_title);
        tv_right = findViewById(R.id.tv_right);
        iv_select = findViewById(R.id.iv_select);
        iv_editor = findViewById(R.id.iv_editor);
        vs_content = findViewById(R.id.vs_content);
    }

    protected abstract int setContentView();

    protected abstract void onCreate(View view);

    protected int getBaseXMLLayout() {
        return R.layout.activity_base;
    }
}
