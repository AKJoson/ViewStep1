package com.view.xcy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

import com.view.xcy.Utils.Utils;

public class MyTextView extends AppCompatTextView {

    private final String TAG = MyTextView.class.getSimpleName();

    private Paint mTextPaint;
    private Paint mNormalPaint;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextPaint = new Paint();
        mNormalPaint = new Paint();
        mTextPaint.setTextSize(Utils.dp2px(28));
        mTextPaint.setTextSize(Color.parseColor("#000000"));
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mNormalPaint.setTextSize(Utils.dp2px(28));
        mNormalPaint.setTextSize(Color.parseColor("#00ffff"));
        mNormalPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        Log.e(TAG, "Set the MyTextView width is 40 dp");
        int height = Utils.dp2px(40);
        setMeasuredDimension(width, height);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // get the baseline is a Correctly way!
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        float baseLine = dy + getHeight() / 2.0f;
        canvas.drawText("Sam,I love you!", 0.0f, baseLine, mTextPaint);
        //use getHeight()/2 is a wrong way!
        canvas.drawText("Sam,I love you!", mTextPaint.measureText("Sam,I love you!"), getHeight() / 2.0f, mNormalPaint);
    }
}
