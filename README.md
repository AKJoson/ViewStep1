## 这都2019了，还不吧Canvas和Paint牵出来加点特效？？
### Canvas 和 Paint 
- Canvas主要方法有：
    - canvas.drawCircle()
    - canvas.drawArc()

- Paint加特效的方法主要有：
    -  mStrokePaint.setColor(Color.RED);
    -    mCiclePaint.setColor(Color.BLUE); 
    -  mStrokePaint.setStyle(Paint.Style.STROKE); // 仅仅描绘边
    - mCiclePaint.setStyle(Paint.Style.FILL);//画○的进行填充
    - mStrokePaint.setStrokeCap(Paint.Cap.ROUND);//转弯处，来不来一个漂移
    - mStrokePaint.setStrokeWidth(10);//描边的宽度
    - mStrokePaint.setAntiAlias(true);//加不加抗锯齿特效