package com.ms.awe.msshowtime.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.ms.awe.msshowtime.R;

public class RoundProgressBarWithProgress extends HorizontalProgressbarWithProgress {
    //编写常量为控件初始默认值
    private int mRadius = dp2px(30);
    private int mMaxPaintWidth;

    /**
     * 一个参数的构造方法调用两个参数的构造方法
     * 两个参数的构造方法调用三个参数的构造方法
     * 初始化定义到三个参数的构造方法中去
     */
    public RoundProgressBarWithProgress(Context context) {
        this(context, null);
    }

    public RoundProgressBarWithProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mReachHeight = (int) (mUnReachHeight * 2.5f);

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.RoundProgressBarWithProgress);
        mRadius = (int) ta.getDimension(
                R.styleable.RoundProgressBarWithProgress_radius, mRadius
        );

        ta.recycle();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mMaxPaintWidth = Math.max(mReachHeight, mUnReachHeight);
        //默认四个padding一致
        int expect = mRadius * 2 + mMaxPaintWidth + getPaddingLeft() + getPaddingRight();

        int width = resolveSize(expect, widthMeasureSpec);
        int height = resolveSize(expect, heightMeasureSpec);

        int readWidth = Math.min(width, height);
        mRadius = (readWidth - getPaddingLeft() - getPaddingRight() - mMaxPaintWidth) / 2;

        setMeasuredDimension(readWidth, readWidth);
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        String text = getProgress() + "%";
        float textWidth = mPaint.measureText(text);
        float textHeight = (mPaint.descent() + mPaint.ascent() / 2);
        mPaint.setTextSize(30);

        canvas.save();

        canvas.translate(getPaddingLeft() + mMaxPaintWidth / 2, getPaddingTop() + mMaxPaintWidth / 2);
        mPaint.setStyle(Paint.Style.STROKE);

        //draw unreach bar(画圆)
        mPaint.setColor(mUnReachColor);
        mPaint.setStrokeWidth(mUnReachHeight);
        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);

        //draw reach bar(画弧)
        mPaint.setColor(mReachColor);
        mPaint.setStrokeWidth(mReachHeight);
        float sweepAngle = getProgress() * 1.0f / getMax() * 360;
        canvas.drawArc(new RectF(0,0,mRadius*2,mRadius*2),
                -90,sweepAngle,false,mPaint);

        //draw text
        mPaint.setColor(mTextColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(text ,mRadius -textWidth/2,mRadius-textHeight,mPaint);

        canvas.restore();
    }
}
