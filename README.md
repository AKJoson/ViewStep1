## 这都2019了，还不把Canvas和Paint牵出来加点特效？？
### Canvas 和 Paint 
- Canvas主要方法有：
    - 这些方法，传入的位置，单位都是 px
    - canvas.drawCircle(x,y,radius,Paint)
    - canvas.drawArc(RectF,startAngle,sweepAngle,useCenter,Paint)

- Paint加特效的方法主要有：
    -  mStrokePaint.setColor(Color.RED);
    - //设置绘制的模式 FILL->填充，STROKE->描边（只要边，不要心，自己脑补画出的是啥“轮廓”） FILL_STROKE->填充和描边，这个需要设置上setStrokeWidth,才能加上特效，不设置，默认是FILL的。
    -  mStrokePaint.setStyle(Paint.Style.STROKE); // 仅仅描绘边
    - mCiclePaint.setStyle(Paint.Style.FILL);//画○的进行填充
    - mStrokePaint.setStrokeCap(Paint.Cap.ROUND);//转弯处，来不来一个漂移
    - mStrokePaint.setStrokeWidth(10);//描边的宽度
    - mStrokePaint.setAntiAlias(true);//加不加抗锯齿特效

### tips1:经过测试，Paint的描边，是外描边，而不是内描边；
### tips2:那么，在上面的Dcanvas.drawCircle(x,y...),我们引入了坐标。非常棒，隔壁老殷就问了，wtf，这个坐标是相对与什么的？现场小殷就回答了：先对于View的左上角，向右-->x轴正轴，向下--->y轴正轴。
### 弱鸡的小殷同学一开始是这么滴：
        <com.*****.MyView
        android:layout_width=50dp;
        android:layout_height=50dp;
        ></>
### 可以看到，自定义的View的宽高，给的是50dp,然后，在onDraw(Canvas canvas)中，小殷同学做出了骇人听闻的事：
        @Override
        public void onDraw(Canvas canvas){
            canvas.drawCircle(1080/2,1080/2,20,mPaint);
        }
### 然后小殷同学还在那看啊看，咦，我的自定义圆啦？你这系统给我画到外星上去啦？？
### 后来，小殷同学发现，这个自定义View的宽高只是50，根据相对于View的左上角，那能画出来，有鬼罢嘞～～～～        
---
### tips3:经过上面的折腾，似乎发现了一个问题，画这些东西，总是要传入一个Paint的画笔，原来这个Paint存储的是共有信息，而我们在canvas.draw***（....,Paint）的时候，需要单独传入的是独有信息，像圆：圆心，半径；圆弧：起始角度，结束角度这些，就叫做独有信息。
