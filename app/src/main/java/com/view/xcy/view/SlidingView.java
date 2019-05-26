package com.view.xcy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
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
    private GestureDetector mGestureDetector;

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
        mGestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                //快速滑动
                if (isContent){
                    if (velocityX>0){
                        openMenu();
                        return true;
                    }
                }else{
                    if (velocityX<0) {
                        openContent();
                        return true;
                    }
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });
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
        ViewGroup.LayoutParams contentParams = contentView.getLayoutParams();
        contentParams.width = screenWidth;
        contentParams.height = heightPixels;
        contentView.setLayoutParams(contentParams);
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

        //menuView.setTranslationX(menuPercent);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        openContent();
    }
    private boolean isContent = false;
    private void openMenu() {
        isContent = false;
        smoothScrollTo(0, 0);
    }

    private void openContent() {
        isContent = true;
        smoothScrollTo(menuWidth, 0);
    }
    private boolean isIntercept = false;
    //为menu打开状态的时候，点击右侧content 打开content
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            if (ev.getX() > menuWidth && !isContent){
                isIntercept  = true;
                openContent();
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isIntercept){
            isIntercept = false;
            return true;
        }
        if (mGestureDetector.onTouchEvent(ev)){
            //快速滑动执行了，下面就不要执行了
            return true;
        }
        // 如果不这样操作，那么ScrollView滑动到中间，menuView和contentView个显示一半，不是很奇怪的效果吗？？
        if (ev.getAction() == MotionEvent.ACTION_UP){
                if (getScrollX() > screenWidth/2){
                    openContent();
                }else{
                    openMenu();
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    private int dp2px(int dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
