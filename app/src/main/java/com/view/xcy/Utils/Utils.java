package com.view.xcy.Utils;


import com.view.xcy.BaseApplication;

public class Utils {

    public static int dp2px(int dpValue) {
        final float scale = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(float pxValue) {
        float density = BaseApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5d);
    }

}
