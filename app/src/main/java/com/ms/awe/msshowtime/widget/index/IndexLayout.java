package com.ms.awe.msshowtime.widget.index;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ms.awe.msshowtime.utils.ScreenUtils;

/**
 * Created By Musi
 * on 2019/2/25
 * 绘制圆
 */
public class IndexLayout extends FrameLayout {

    private int desiredWidth;           //默认的高
    private int desiredHeight;          //默认的宽
    private int circleColor = 0xaaa1a3a6;
    private int circleTextColor = Color.WHITE;      //绘制圆内文字颜色
    private int circleTextSize = 80;
    private int indexBarWidth = ScreenUtils.dpToPx(30);

    private Paint mPaint;               //绘制圆
    private Paint mTxtPaint;            //绘制文字

    private float circleRadius = 100;
    private float yAxis;                //文字y轴方向的基线
    private float touchYpivot;
    private float indexBarHeightRatio = 1;

    private boolean isDrawByTouch = false;
    private boolean isShowCircle;

    private String indexName = "";

    private IndexBar indexBar;


    public IndexLayout(@NonNull Context context) {
        super(context, null);
    }

    public IndexLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public IndexLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        desiredWidth = wm.getDefaultDisplay().getWidth();
        desiredHeight = wm.getDefaultDisplay().getHeight();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(circleColor);

        mTxtPaint = new Paint();
        mTxtPaint.setAntiAlias(true);
        mTxtPaint.setTextSize(circleTextSize);
        mTxtPaint.setColor(circleTextColor);
        mTxtPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mTxtPaint.getFontMetrics();
        float total = -fontMetrics.ascent + fontMetrics.descent;
        yAxis = total / 2 - fontMetrics.descent;

        indexBar = new IndexBar(getContext());
        addView(indexBar);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureSize(1, desiredWidth, widthMeasureSpec),
                measureSize(0, desiredHeight, heightMeasureSpec));
    }

    public int measureSize(int specType, int contentSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(specSize, contentSize);
                if (specType == 1) {
                    result += getPaddingLeft() + getPaddingRight();
                } else {
                    result += getPaddingTop() + getPaddingBottom();
                }
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                result = contentSize;
                break;
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int childNum = getChildCount();
        if (childNum < 1)
            return;
        View childView = getChildAt(0);
        float topPadding = (1 - indexBarHeightRatio) / 2;
        childView.layout(getWidth() - indexBarWidth, (int) (getHeight() * topPadding), getWidth(),
                (int) (getHeight() * (topPadding + indexBarHeightRatio)));  //放在右边
    }

    /**
     * @param touchYpivot 要绘制的圆的Y坐标
     * @param indexName   要绘制的indexName
     */
    public void drawCircle(float touchYpivot, String indexName) {
        this.touchYpivot = touchYpivot;
        this.indexName = indexName;
        isShowCircle = true;
        invalidate();
    }

    private int duration = 1000;
    private int WHAT_DISMISS = 0x101;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isShowCircle = false;
            invalidate();
        }
    };

    public void dismissCircle() {
        mHandler.removeMessages(WHAT_DISMISS);
        mHandler.sendEmptyMessageDelayed(WHAT_DISMISS, duration);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isShowCircle) {
            if (isDrawByTouch) {
                canvas.drawCircle(getWidth() / 2, touchYpivot + getHeight() * (1 - indexBarHeightRatio) / 2, circleRadius, mPaint);
                canvas.drawText(indexName, getWidth() / 2, touchYpivot + yAxis + getHeight() * (1 - indexBarHeightRatio) / 2, mTxtPaint);
            } else {
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, circleRadius, mPaint);
                canvas.drawText(indexName, getWidth() / 2, getHeight() / 2 + yAxis, mTxtPaint);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(WHAT_DISMISS);
    }

    //设置圆的半径
    public void setCircleRadius(float circleRadius) {
        this.circleRadius = circleRadius;
    }

    //设置圆显示时长
    public void setCircleDuration(int duration) {
        this.duration = duration;
    }

    //设置圆的颜色
    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
        mPaint.setColor(circleColor);
    }

    //设置圆内文字颜色
    public void setCircleTextColor(int textColor) {
        this.circleTextColor = textColor;
        mTxtPaint.setColor(circleTextColor);
    }

    //设置圆内文字字体大小
    public void setCirCleTextSize(int cirCleTextSize) {
        this.circleTextSize = cirCleTextSize;
        mTxtPaint.setTextSize(cirCleTextSize);

        Paint.FontMetrics fontMetrics = mTxtPaint.getFontMetrics();
        float total = -fontMetrics.ascent + fontMetrics.descent;
        yAxis = total / 2 - fontMetrics.descent;
    }

    public void setIndexBarWidth(int width) {
        this.indexBarWidth = width;
    }

    //绘制圆的中心点为手指触碰y轴
    public void setDrawByTouch(boolean drawByTouch) {
        isDrawByTouch = drawByTouch;
    }

    //indexBar 高度占父容器的比率，默认1
    public void setIndexBarHeightRatio(float indexBarHeightRatio) {
        this.indexBarHeightRatio = indexBarHeightRatio;
    }

    public IndexBar getIndexBar() {
        return indexBar;
    }

    private int dp2px(float dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
