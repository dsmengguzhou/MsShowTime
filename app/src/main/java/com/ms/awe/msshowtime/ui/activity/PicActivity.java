package com.ms.awe.msshowtime.ui.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.ms.awe.msshowtime.R;

public class PicActivity extends AppCompatActivity{

    private static final String TAG = PicActivity.class.getSimpleName();
    private Bitmap mCurrentBitmap;
    private ImageView firstImg;
    private ImageView secondImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        initView();

        loadOriginalSize(firstImg);
        testInBitmap(secondImg);
    }

    /**
     * 直接加载load sdcard里的图片
     * @param img
     */
    private void loadOriginalSize(ImageView img) {
        String sdcard = Environment.getExternalStorageDirectory().getPath();
        String filePath = sdcard + "11.jpg";

        mCurrentBitmap = BitmapFactory.decodeFile(filePath);
        img.setImageBitmap(mCurrentBitmap);
    }

    /**
     * 压缩图片
     */
    private void testPicOptimize(ImageView img){
        String sdcard = Environment.getExternalStorageDirectory().getPath();
        String filePath = sdcard + "11.jpg";

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath,options);

        int width = options.outWidth;
        options.inSampleSize = width / 200;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);
        img.setImageBitmap(bitmap);
    }

    /**
     * inBitmap的使用
     */
    private void testInBitmap(ImageView secondImg){
        String sdcard = Environment.getExternalStorageDirectory().getPath();
        String filePath = sdcard + "11.jpg";

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inBitmap = mCurrentBitmap;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);
        secondImg.setImageBitmap(bitmap);
    }

    private void initView() {
        firstImg = findViewById(R.id.iv_first_img);
        secondImg = findViewById(R.id.iv_second_img);
    }
}
