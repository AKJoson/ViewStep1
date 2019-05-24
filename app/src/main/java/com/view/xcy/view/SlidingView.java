package com.view.xcy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.view.xcy.R;

public class SlidingView extends HorizontalScrollView {

    private int edgeWidth;
    private int screenWidth;
    private int heightPixels;
    private int menuWidth;
    private ViewGroup menuView;
    private ViewGroup contentView;

    public SlidingView(Context context) {
        this(context, null);
    }

    public SlidingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.SlidingView);
        edgeWidth = typedArray.getDimensionPixelSize(R.styleable.SlidingView_menuWidth, dp2px(45));
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        heightPixels = getResources().getDisplayMetrics().heightPixels;
        menuWidth = screenWidth - edgeWidth;
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 1. set the View params
        ViewGroup container = (ViewGroup) getChildAt(0);
        if (container.getChildCount() != 2)
            throw new RuntimeException("oh darling, you can use two ChildView in this slidingView~~");
        menuView = (ViewGroup) container.getChildAt(0);
        ViewGroup.LayoutParams menuParams = menuView.getLayoutParams();
        menuParams.width = menuWidth;
        menuParams.height = heightPixels;
        menuView.setLayoutParams(menuParams);
        contentView = (ViewGroup) container.getChildAt(1);
        ViewGroup.LayoutParams contentrParams = contentView.getLayoutParams();
        contentrParams.width = screenWidth;
        contentrParams.height = heightPixels;
        contentView.setLayoutParams(contentrParams);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //2. the oldl ranger is 0 - menuWidth
        float rangePercent = 1.0f * oldl / menuWidth;
        float contentRange = 0.85f + rangePercent * 0.15f;
        contentView.setPivotX(0);
        contentView.setPivotY(heightPixels / 2);
        contentView.setScaleX(contentRange);
        contentView.setScaleY(contentRange);

        float menuPercent = 0.5f + rangePercent * 0.5f;

        menuView.setTranslationX(menuPercent);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        openContent();
    }

    private void openContent() {
        smoothScrollTo(menuWidth, 0);
    }

    //为menu打开状态的时候，点击右侧content 打开content
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    private void openMenu() {
        smoothScrollTo(0, 0);
    }

    private int dp2px(int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
