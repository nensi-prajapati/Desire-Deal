package com.example.desiredeal.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.desiredeal.R;

import java.util.List;

public class SliderAdapterBanner extends PagerAdapter {



        private int[] bannerImages = {R.drawable.offerslide1, R.drawable.offerslide2, R.drawable.offerslide3};
        private String[] bannerTitles = {"Happy Fashion Day", "Happy Fashion Day", "Happy Fashion Day"};

        private String[] bannerTitless2 = {"Tops & Tunic", "Tops & Tunic", "Tops & Tunic"};

        private String[] bannerTitless3 = {"Get 50% Off", "Get 50% Off", "Get 50% Off"};

        @Override
        public int getCount() {
            return bannerImages.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(container.getContext());
            View view = inflater.inflate(R.layout.slider_layout_banner, container, false);

            ImageView bannerImage = view.findViewById(R.id.banner_image);
            TextView bannerTitle = view.findViewById(R.id.banner_title);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView bannerTitle2 = view.findViewById(R.id.second_title);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView bannerTitle3 = view.findViewById(R.id.third_title);


            bannerImage.setImageResource(bannerImages[position]);
            bannerTitle.setText(bannerTitles[position]);
            bannerTitle2.setText(bannerTitless2[position]);
            bannerTitle3.setText(bannerTitless3[position]);

            container.addView(view);

            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }






