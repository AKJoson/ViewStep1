package com.view.xcy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class MyCanvasAndPaint extends View {
    private Paint mStrokePaint; //仅仅负责描边
    private Paint mCiclePaint; //负责填充
    private int widthPixels;
    private int heightPixels;
    private float value;
    private RectF rectF;

    public MyCanvasAndPaint(Context context) {
        this(context, null);
    }

    public MyCanvasAndPaint(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCanvasAndPaint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        heightPixels = context.getResources().getDisplayMetrics().heightPixels;

        mStrokePaint = new Paint();
        mCiclePaint = new Paint();
        //本命年就来一个红色吧
        mStrokePaint.setColor(Color.RED);
        mCiclePaint.setColor(Color.BLUE);
        // 仅仅描绘边
        mStrokePaint.setStyle(Paint.Style.STROKE);
        //画○的进行填充
        mCiclePaint.setStyle(Paint.Style.FILL);
        //转弯处，来不来一个漂移
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        //描边的宽度
        mStrokePaint.setStrokeWidth(10);
        //加不加抗锯齿特效
        mStrokePaint.setAntiAlias(true);
        mCiclePaint.setAntiAlias(true);
        rectF = new RectF(widthPixels / 4, widthPixels / 4,widthPixels-widthPixels/4,widthPixels-widthPixels/4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //   canvas.drawCircle(30,30,100,mStrokePaint);
        canvas.drawArc(rectF,0, (float) (360*0.01*value),false,mStrokePaint);
        canvas.drawArc(rectF,0, (float) (360*0.01*value),true,mCiclePaint);
//        canvas.drawCircle(widthPixels / 2, heightPixels / 2, 190, mCiclePaint);
//        canvas.drawCircle(widthPixels / 2, heightPixels / 2, 200, mStrokePaint);
        //   Rect rect = new Rect(widthPixels/4,heightPixels/4,widthPixels-widthPixels/4,heightPixels-heightPixels/4);
        //   canvas.drawRect(rect,mStrokePaint);
    }

    public void startCanvas(float value){
        this.value = value;
        //请求重绘
        invalidate();
    }
}
