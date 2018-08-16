package com.example.viewpageropensource;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<View>mViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mViewList = new ArrayList<> ();
        View view1 = getLayoutInflater ().inflate (R.layout.pager_view1, null, false);
        View view2 = getLayoutInflater ().inflate (R.layout.pager_view2, null, false);
        View view3 = getLayoutInflater ().inflate (R.layout.pager_view3, null, false);
        View view4 = getLayoutInflater ().inflate (R.layout.pager_view4, null, false);
        View view5 = getLayoutInflater ().inflate (R.layout.pager_view5, null, false);

        mViewList.add (view1);
        mViewList.add (view2);
        mViewList.add (view3);
        mViewList.add (view4);
        mViewList.add (view5);

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter (mViewList);
        ViewPager mViewPager = findViewById (R.id.view_pager);
        mViewPager.setAdapter (myPagerAdapter);
        mViewPager.setPageTransformer (true, new DepthPageTransformer ());
    }

    public class MyPagerAdapter extends PagerAdapter{

        private List<View> viewList;

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = viewList.get (position);
            container.addView (view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView (viewList.get (position));
        }

        @Override
        public int getCount() {
            return viewList.size ();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        public MyPagerAdapter(List<View> list){
            viewList = list;
        }


    }

}
