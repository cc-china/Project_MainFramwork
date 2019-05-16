package com.grirms.crm.crm_module.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grirms.crm.R;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public class ViewUtils {
    public static View setExpandableListView_ChildView(Context ctx,int spanCount) {
        int spacing = ctx.getResources().getDimensionPixelSize(R.dimen.sp_15);//每一个矩形的间距
        boolean includeEdge = true;//如果设置成false那边缘地带就没有间距s
        //设置每个item间距

        MyRecycleView recyclerView = new MyRecycleView(ctx);
        recyclerView.setId(R.id.expandableListView_recycleView);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        GridLayoutManager manager = new GridLayoutManager(ctx, spanCount);
        recyclerView.setLayoutManager(manager);
        return recyclerView;
    }

    public static View setRecycleView_ChildView(Context ctx) {
        TextView textView = new TextView(ctx);
        textView.setId(R.id.recycleView_TextView);
        textView.setBackground(ctx.getResources().getDrawable(R.drawable.shape_corner_colorf4f4f4));
        textView.setTextSize(ctx.getResources().getDimensionPixelSize(R.dimen.sp_4));
        textView.setTextColor(ctx.getResources().getColor(R.color.color333333));
        textView.setPadding((int) ctx.getResources().getDimension(R.dimen.dp_8),
                (int) ctx.getResources().getDimension(R.dimen.dp_8),
                (int) ctx.getResources().getDimension(R.dimen.dp_8),
                (int) ctx.getResources().getDimension(R.dimen.dp_8));
//        textView.setMaxEms(5);
        textView.setMaxLines(5);
        textView.setLines(1);
        //textView.setTypeface(Typeface.DEFAULT_BOLD); 设置黑体
        textView.setEllipsize(TextUtils.TruncateAt.END);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMarginStart(45);
        textView.setLayoutParams(params);
        return textView;
    }

    public static View setMapView(Context ctx) {
        RelativeLayout layout = new RelativeLayout(ctx);
        ViewGroup.MarginLayoutParams relativeLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(relativeLayoutParams);

        com.amap.api.maps.MapView mapView = new com.amap.api.maps.MapView(ctx);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ctx.getResources().getDimensionPixelSize(R.dimen.dp_179));
        mapView.setLayoutParams(params);
        mapView.setId(R.id.mapView);
        layout.addView(mapView);

        EditText editText = new EditText(ctx);
        RelativeLayout.MarginLayoutParams editTextParams = new RelativeLayout.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editTextParams.setMargins(ctx.getResources().getDimensionPixelSize(R.dimen.dp_15),
                ctx.getResources().getDimensionPixelSize(R.dimen.dp_12),
                ctx.getResources().getDimensionPixelSize(R.dimen.dp_15), 0);
        editText.setLayoutParams(editTextParams);
        editText.setPadding(ctx.getResources().getDimensionPixelSize(R.dimen.dp_8),
                ctx.getResources().getDimensionPixelSize(R.dimen.dp_5),
                ctx.getResources().getDimensionPixelSize(R.dimen.dp_5),
                ctx.getResources().getDimensionPixelSize(R.dimen.dp_8));
        editText.setBackground(ctx.getResources().getDrawable(R.drawable.shape_corner_left_right_colorffffff));
        editText.setHint("搜索客户名称、联系人、电话");
        editText.setHintTextColor(ctx.getResources().getColor(R.color.color999999));
        editText.setTextSize(ctx.getResources().getDimensionPixelSize(R.dimen.sp_5));
        Drawable drawable = ctx.getResources().getDrawable(R.mipmap.select_gray);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        editText.setCompoundDrawables(drawable, null, null, null);
        editText.setCompoundDrawablePadding(ctx.getResources().getDimensionPixelSize(R.dimen.dp_8));
        layout.addView(editText);

        return layout;
    }


    public static View setTabLayout_ChildView(Context ctx) {
        TextView textView = new TextView(ctx);
        textView.setId(R.id.tabLayout_TextView);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);
        textView.setTextColor(ctx.getResources().getColor(R.color.selector_text_color_condition_choose));
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
