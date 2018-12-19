package com.ms.awe.msshowtime.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Paint;

public class MeasureUtil {

    //返回指定笔和指定字符串的长度
    public static float getFontlength(Paint paint,String str){
        return paint.measureText(str);
    }

    //返回指定笔的文字高度
    public static float getFontHeight(Paint paint){
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    //返回指定笔离文字顶部的基准距离
    public static float getFontLeading(Paint paint){
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.leading - fm.ascent;
    }

    //处理图片
    public static Bitmap zoomImg(Bitmap bm,int newWidth,int newHeight){
        //获得图片的宽高
        int width = bm.getWidth();
        int height = bm.getHeight();
        //计算缩放比例
        float scaleWidth = ((float)newWidth) / width;
        float scaleHeight = ((float)newHeight)/height;
        //取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);
        //得到新图片
        Bitmap newbm = Bitmap.createBitmap(bm,0,0,width,height,matrix,true);
        return newbm;
    }
}
