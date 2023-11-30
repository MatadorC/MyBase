package com.gov.mybase.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @Description ：自定义ViewPage，支持侧滑翻页
 * @Author ：fengxiaochuan
 * @Date ：2021/10/29 10:44
 */
public class CustomViewPager extends ViewPager {

    private boolean isCanScroll = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 设置其是否能滑动换页
     *
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return isCanScroll && super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try{
            return isCanScroll && super.onTouchEvent(ev);
        }catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
