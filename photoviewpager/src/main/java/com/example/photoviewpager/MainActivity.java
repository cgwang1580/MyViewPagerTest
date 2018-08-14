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
        mPhotoViewPager.setAdapter (mPhotoPagerAdapter);

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
