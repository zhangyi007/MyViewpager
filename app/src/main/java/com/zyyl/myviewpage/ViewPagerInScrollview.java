package com.zyyl.myviewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class ViewPagerInScrollview extends ViewPager {
    public ViewPagerInScrollview(Context context) {
        super(context);
    }

    public ViewPagerInScrollview(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev){
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0){
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onTouchEvent(arg0);
    }
}
