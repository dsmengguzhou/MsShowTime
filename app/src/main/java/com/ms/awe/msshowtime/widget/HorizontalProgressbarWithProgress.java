package com.ms.awe.msshowtime.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.ms.awe.msshowtime.R;

public class HorizontalProgressbarWithProgress extends ProgressBar {
    //编写常量为控件初始默认值
    private static final int DEFAULT_TEXT_SIZE = 14;    //sp
    private static final int DEFAULT_TEXT_COLOR = 0xFFFC00D1;
    private static final int DEFAULT_COLOR_UNREACH = 0XFFD3D6DA;
    private static final int DEFAULT_HEIGHT_UNREACH = 2;    //sp,未进进度的线宽
    private static final int DEFAULT_COLOR_REACH = DEFAULT_TEXT_COLOR;
    private static final int DEFAULT_HEIGHT_REACH = 2;    //dp,已进进度的线宽
    private static final int DEFAULT_TEXT_OFFSET = 10;    //dp

    protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
    protected int mTextColor = DEFAULT_TEXT_COLOR;
    protected int mUnReachColor = DEFAULT_COLOR_UNREACH;
    protected int mUnReachHeight = dp2px(DEFAULT_HEIGHT_UNREACH);
    protected int mReachColor = DEFAULT_COLOR_REACH;
    protected int mReachHeight = dp2px(DEFAULT_HEIGHT_REACH);
    protected int mTextOffset = dp2px(DEFAULT_TEXT_OFFSET);

    protected Paint mPaint = new Paint(); //画笔

    //真实宽度，在onMeasure赋值，onDraw中对其进行使用;当前控件的宽度减去一个padding的值，剩下的一个真正的宽度
    protected int mRealWidth;

    /**
     * 一个参数构造方法
     */
    public HorizontalProgressbarWithProgress(Context context) {
        this(context, null);

    }

    /**
     * 两个参数构造方法
     */
    public HorizontalProgressbarWithProgress(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        //获取、初始化成员变量
        obtainStyledAttrs(attrs);

    }

    /**
     * 三个参数构造方法
     */
    public HorizontalProgressbarWithProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        obtainStyledAttrs(attrs);
    }

    /**
     * 获取自定义属性
     */
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs,
                R.styleable.HorizontalProgressbarWithProgress);

        mTextSize = (int) ta
                .getDimension(
                        R.styleable.HorizontalProgressbarWithProgress_progress_text_size,
                        mTextSize);

        mTextColor = ta
                .getColor(
                        R.styleable.HorizontalProgressbarWithProgress_progress_text_color,
                        mTextColor);

        mTextOffset = (int) ta
                .getDimension(
                        R.styleable.HorizontalProgressbarWithProgress_progress_text_offset,
                        mTextOffset);

        mUnReachColor = ta
                .getColor(
                        R.styleable.HorizontalProgressbarWithProgress_progress_unreach_color,
                        mUnReachColor);

        mUnReachHeight = (int) ta
                .getDimension(
                        R.styleable.HorizontalProgressbarWithProgress_progress_unreach_height,
                        mUnReachHeight);

        mReachColor = ta
                .getColor(
                        R.styleable.HorizontalProgressbarWithProgress_progress_reach_color,
                        mReachColor);

        mReachHeight = (int) ta
                .getDimension(
                        R.styleable.HorizontalProgressbarWithProgress_progress_reach_height,
                        mReachHeight);
        ta.recycle();

        mPaint.setTextSize(mTextSize);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthVal = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(widthVal, height);     //确定view的宽和高

        mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int measureHeight(int heightMeasureSpec) {
        //测量我们可以了解到它的模式，拿到它的大小，根据三种模式分不同的情况
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);

        if (mode == MeasureSpec.EXACTLY) {   //给的准确的值
            result = size;
        } else {
            int textHeight = (int) (mPaint.descent() - mPaint.ascent());
            result = getPaddingTop() + getPaddingBottom()       //上边距和下边距
                    + Math.max(Math.max(mReachHeight, mUnReachHeight),   //三者取最大值，进行比较得出最大值
                    Math.abs(textHeight));

            if (mode == MeasureSpec.AT_MOST) {       //测量值不能超过给定的size值
                result = Math.min(result, size);
            }
        }

        return result;
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);

        boolean noNeedUnRech = false;
        mPaint.setTextSize(mTextSize);

        //draw reach bar
        String text = getProgress() + "%";
        int textWidth = (int) mPaint.measureText(text);

        float radio = getProgress() * 1.0f / getMax();   //占进度条百分比
        float progressX = radio * mRealWidth;
        if (progressX + textWidth > mRealWidth){
            progressX = mRealWidth - textWidth;
            noNeedUnRech = true;
        }
        float endX = progressX - mTextOffset / 2;
        if (endX > 0) {
            mPaint.setColor(mReachColor);
            mPaint.setStrokeWidth(mReachHeight);
            canvas.drawLine(0, 0, endX, 0, mPaint);
        }

        //draw text
        mPaint.setColor(mTextColor);
        int y = (int) (-(mPaint.descent() + mPaint.ascent())/2);
        canvas.drawText(text,progressX,y,mPaint);

        //draw unreach bar
        if (!noNeedUnRech){
            float start = progressX + mTextOffset/2 + textWidth;
            mPaint.setColor(mUnReachColor);
            mPaint.setStrokeWidth(mUnReachHeight);
            canvas.drawLine(start,0,mRealWidth,0,mPaint);
        }

        canvas.restore();
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                getResources().getDisplayMetrics());
    }

    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal,
                getResources().getDisplayMetrics());
    }
}
