package com.view.xcy.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * just use ViewDragerHelper
 */
public class ViewDragerHelperView extends FrameLayout {
    private final String TAG = ViewDragerHelperView.class.getSimpleName();


    private ViewDragHelper viewDragHelper;

    public ViewDragerHelperView(Context context) {
        this(context, null);
    }

    public ViewDragerHelperView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragerHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        viewDragHelper = ViewDragHelper.create(this, callback);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            Log.e(TAG,i+""+view.toString());
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return top;
        }
    };

    /**
     * when the xml is parsed
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }
}
