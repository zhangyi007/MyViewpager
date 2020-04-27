package com.zyyl.myviewpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private int img[]=new int[]{R.mipmap.test1,R.mipmap.test2,R.mipmap.test3,R.mipmap.test1,R.mipmap.test2,R.mipmap.test3};

    private ViewPager viewPager2;
    private Handler handler=new Handler();
    private int page=1;
    private int page2=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager=findViewById(R.id.viewpager);
        viewPager2=findViewById(R.id.viewpager2);
        setViewPager();
        setViewPager2();
        handler.postDelayed(runnable,3000);
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (page==img.length-1){
                page=0;
            }else {
                page++;
            }
            viewpager.setCurrentItem(page);

            if (page2==img.length-1){
                page2=0;
            }else {
                page2++;
            }
            viewPager2.setCurrentItem(page2);
            handler.postDelayed(runnable,3000);
        }
    };

    private void setViewPager2() {
        //设置每个item和左右的间距
        viewPager2.setPageMargin(dip2px(8));
        viewPager2.setAdapter(new WelcomAdapter());
        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page2=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //回弹效果
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedScroller scroller = new FixedScroller(MainActivity.this,
                    new OvershootInterpolator());
            scroller.setmDuration(600);
            field.set(viewPager2, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setViewPager() {
        viewpager.setAdapter(new WelcomAdapter());
        viewpager.setCurrentItem(1);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedScroller scroller = new FixedScroller(MainActivity.this,
                    new OvershootInterpolator());
            scroller.setmDuration(600);
            field.set(viewpager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dp转px
     * @param dpValue dp值
     * @return
     */
    public  int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    private class WelcomAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view=View.inflate(MainActivity.this,R.layout.items,null);
            ((ImageView)view.findViewById(R.id.img)).setImageResource(img[position]);
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
