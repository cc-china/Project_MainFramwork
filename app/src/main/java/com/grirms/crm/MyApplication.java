package com.grirms.crm;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.grirms.crm.utils.RequestPermissionUtils;

/**
 * Created by Administrator on 2019\4\24 0024.
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        RequestPermissionUtils.requestPermissions(activity);
//        RequestPermissionUtils.checkPermissions(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
