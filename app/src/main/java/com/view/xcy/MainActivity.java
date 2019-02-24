package com.view.xcy;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.view.xcy.view.MyCanvasAndPaint;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyCanvasAndPaint canvasAndPaint = findViewById(R.id.canvas_paint);
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100).setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float values = (Float) animation.getAnimatedValue();
                Log.e("TAG",values+"");
                canvasAndPaint.startCanvas(values);
            }
        });
        valueAnimator.start();
    }
}
