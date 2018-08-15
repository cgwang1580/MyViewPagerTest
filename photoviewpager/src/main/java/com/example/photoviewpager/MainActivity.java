package com.example.photoviewpager;

import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPhotoViewPager;
    int[] mPhotoId = {R.drawable.basketball_1, R.drawable.basketball_2, R.drawable.basketball_3};
    private PhotoPagerAdapter mPhotoPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        mPhotoPagerAdapter = new PhotoPagerAdapter (mPhotoId);
        mPhotoViewPager = findViewById (R.id.view_pager);

        mPhotoViewPager.setPageMargin (40);
        mPhotoViewPager.setOffscreenPageLimit (3);
        mPhotoViewPager.setAdapter (mPhotoPagerAdapter);

        AlphaPageTransformer alphaPageTransformer = new AlphaPageTransformer ();
        mPhotoViewPager.setPageTransformer (false, alphaPageTransformer);

    }

    public class AlphaPageTransformer implements ViewPager.PageTransformer{

        /*public static final float DEFAULT_MIN_ALPHA = 0.5f;
        public float mMinAlpha = DEFAULT_MIN_ALPHA;*/

        public static final float DEFAULT_MAX_ROTATE = 15.0f;
        public float mMaxRotate = DEFAULT_MAX_ROTATE;

        public AlphaPageTransformer(){

        }

        @Override
        public void transformPage(@NonNull View view, float v) {

            if(v <= -1){
                view.setRotation (-1 * mMaxRotate);
                view.setPivotX (view.getWidth ());
                view.setPivotY (view.getHeight ());
            }else if(v <= 0){
                //v: 0 to -1 position: (0.5 to 0) * getWidth()
                view.setPivotX (view.getWidth () * (-0.5f * v + 0.5f));
                view.setPivotY (view.getHeight ());
                view.setRotation (v * mMaxRotate);
            }else if (v <= 1){
                //v: 0 to 1 position: (0.5 to 1) * getWidth() linear function
                view.setPivotX (view.getWidth () * (-0.5f * v + 0.5f));
                view.setPivotY (view.getHeight ());
                view.setRotation (v * mMaxRotate);
            }else{
                view.setRotation (mMaxRotate);
                view.setPivotX (0);
                view.setPivotY (view.getHeight ());
            }

            /*if(v <= -1){
                view.setAlpha (DEFAULT_MIN_ALPHA);
            }else if(v <= 0){
                mMinAlpha = DEFAULT_MIN_ALPHA + (1 + v) * DEFAULT_MIN_ALPHA;
                view.setAlpha (mMinAlpha);
            }else if(v <= 1){
                mMinAlpha = DEFAULT_MIN_ALPHA + (1 - v) * DEFAULT_MIN_ALPHA;
                view.setAlpha (mMinAlpha);
            }else{
                view.setAlpha (DEFAULT_MIN_ALPHA);
            }*/
        }

    }

    public class PhotoPagerAdapter extends PagerAdapter{

        private int[] photos;

        public PhotoPagerAdapter(int[] photo){
            photos = photo;
        }

        @Override
        public int getCount() {
            return photos.length;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView (MainActivity.this);
            imageView.setImageResource (photos[position]);
            container.addView (imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView ((View)object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }
    }
}
