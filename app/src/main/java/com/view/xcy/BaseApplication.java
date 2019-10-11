package com.view.xcy;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

}
