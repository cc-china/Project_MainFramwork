package com.grirms.crm.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by Administrator on 2019\4\28 0028.
 */

public class SizeTransitionUtils {
    public static float sp2px(Context context, float sp){
        float dimension = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return dimension;
    }
}
