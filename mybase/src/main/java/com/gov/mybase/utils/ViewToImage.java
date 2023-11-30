package com.gov.mybase.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.ScrollView;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * ===================================
 *
 * @PackageName ：com.gov.mybase.utils
 * @Description ：TODO
 * @Author ：fengxiaochuan
 * @Date ：2023/7/24 16:56
 * @Version ：1.0
 * ===================================
 */
public class ViewToImage {
    /**
     * 获取scrollview的截屏
     */
    public static Bitmap scrollViewScreenShot(ConstraintLayout scrollView) {
        int h = 0;
        Bitmap bitmap = null;
//        for (int i = 0; i < scrollView.getChildCount(); i++) {
//            h += scrollView.getChildAt(i).getHeight();
////            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
//        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), scrollView.getMeasuredHeight(), Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }
}
