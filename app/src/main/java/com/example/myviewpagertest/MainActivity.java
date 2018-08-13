package com.example.myviewpagertest;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerTabStrip mPagerTabStrip;

    private List<View> mViewList;
    private List<String> mTitleList;

    private View view1;
    private View view2;
    private View view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        initialView();
    }

    public void initialView(){

        view1 = getLayoutInflater ().inflate (R.layout.layout_view1,  null, false);
        view2 = getLayoutInflater ().inflate (R.layout.layout_view2,  null, false);
        view3 = getLayoutInflater ().inflate (R.layout.layout_view3,  null, false);

        mViewList = new ArrayList<> ();
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);

        mTitleList = new ArrayList<> ();
        mTitleList.add ("Title1");
        mTitleList.add ("Title2");
        mTitleList.add ("Title3");

        mPagerTabStrip = findViewById (R.id.pager_tap);
        mPagerTabStrip.setBackgroundColor (this.getResources ().getColor (R.color.colorAccent));
        mPagerTabStrip.setDrawFullUnderline (false);
        mPagerTabStrip.setTabIndicatorColor (this.getResources ().getColor (R.color.colorPrimary));

        mViewPager = findViewById (R.id.view_pager);
        SimplePagerAdapter mSimplePagerAdapter = new SimplePagerAdapter (mViewList);
        mSimplePagerAdapter.setTitleList (mTitleList);
        mViewPager.setAdapter (mSimplePagerAdapter);
    }

    public class SimplePagerAdapter extends PagerAdapter{

        private List<View>viewList;

        private List<String>titleList;

        public List<String> getTitleList() {
            return titleList;
        }

        public void setTitleList(List<String> titleList) {
            this.titleList = titleList;
        }

        public SimplePagerAdapter(List<View> viewList){
            this.viewList = viewList;
        }

        /***
         *
         * This function must be override
         * @return
         */
        @Override
        public int getCount() {
            return viewList.size ();
        }

        /***
         *
         * This function must be override
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView (mViewList.get (position));
            return viewList.get (position);
        }

        /***
         *
         * This function must be override
         * @return
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView (viewList.get(position));
        }

        /***
         *
         * This function must be override
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get (position);
        }
    }
}
