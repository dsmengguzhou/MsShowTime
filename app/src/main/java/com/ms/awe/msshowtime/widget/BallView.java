package com.ms.awe.msshowtime.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created By Musi
 * on 2019/3/7
 */
public class BallView extends View {

    public float currentX = 40;
    public float currentY = 50;
    //定义并创建画笔
    Paint paint = new Paint();

    public BallView(Context context) {
        super(context, null);
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的颜色
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        //绘制一个圆
        canvas.drawCircle(currentX, currentY, 20, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //修改currentX、currentY两个属性
        currentX = event.getX();
        currentY = event.getY();
        //通知当前组件重绘自己
        invalidate();
        //返回true该处理方法已经处理该事件
        return true;
    }
}
