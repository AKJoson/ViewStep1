package com.view.xcy.fragments;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.view.xcy.R;
import com.view.xcy.view.MyCanvasAndPaint;

public class DrawArcFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentContain = inflater.inflate(R.layout.fragment_draw_arc, container, false);
        final MyCanvasAndPaint canvasAndPaint = fragmentContain.findViewById(R.id.canvas_paint);
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100).setDuration(4000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float values = (Float) animation.getAnimatedValue();
                canvasAndPaint.startCanvas(values);
            }
        });
        valueAnimator.start();
        return fragmentContain;
    }
}
