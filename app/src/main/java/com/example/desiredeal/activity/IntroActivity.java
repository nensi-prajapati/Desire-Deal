package com.example.desiredeal.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.desiredeal.R;
import com.example.desiredeal.adapter.SliderAdapter;
import com.example.desiredeal.model.Slideritem;

import java.util.ArrayList;
import java.util.List;


public class IntroActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    private SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);


        SharedPreferences pref = getSharedPreferences("intro", MODE_PRIVATE);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView img = findViewById(R.id.play);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(IntroActivity.this,Welcome.class);
                startActivity(intent);
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();
                onBackPressed();
            }
        });


        // get references to your ImageViews
        ImageView ind1 = findViewById(R.id.ind1);
        ImageView ind2 = findViewById(R.id.ind2);
        ImageView ind3 = findViewById(R.id.ind3);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView ind4 = findViewById(R.id.ind4);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView ind5 = findViewById(R.id.ind5);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView ind6 = findViewById(R.id.ind6);

// create a ViewPager2.OnPageChangeCallback()
        ViewPager2.OnPageChangeCallback onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                // change the color of ImageViews based on selected page index
                switch (position) {
                    case 0:
                        ind1.setImageResource(R.drawable.selected);
                        ind2.setImageResource(R.drawable.unselected);
                        ind3.setImageResource(R.drawable.unselected);
                        ind4.setImageResource(R.drawable.unselected);
                        ind5.setImageResource(R.drawable.unselected);
                        ind6.setImageResource(R.drawable.unselected);
                        break;
                    case 1:
                        ind1.setImageResource(R.drawable.unselected);
                        ind2.setImageResource(R.drawable.selected);
                        ind3.setImageResource(R.drawable.unselected);
                        ind4.setImageResource(R.drawable.unselected);
                        ind5.setImageResource(R.drawable.unselected);
                        ind6.setImageResource(R.drawable.unselected);
                        break;
                    case 2:
                        ind1.setImageResource(R.drawable.unselected);
                        ind2.setImageResource(R.drawable.unselected);
                        ind3.setImageResource(R.drawable.selected);
                        ind4.setImageResource(R.drawable.unselected);
                        ind5.setImageResource(R.drawable.unselected);
                        ind6.setImageResource(R.drawable.unselected);
                        break;
                    case 3:
                        ind1.setImageResource(R.drawable.unselected);
                        ind2.setImageResource(R.drawable.unselected);
                        ind3.setImageResource(R.drawable.unselected);
                        ind4.setImageResource(R.drawable.selected);
                        ind5.setImageResource(R.drawable.unselected);
                        ind6.setImageResource(R.drawable.unselected);
                        break;
                    case 4:
                        ind1.setImageResource(R.drawable.unselected);
                        ind2.setImageResource(R.drawable.unselected);
                        ind3.setImageResource(R.drawable.unselected);
                        ind4.setImageResource(R.drawable.unselected);
                        ind5.setImageResource(R.drawable.selected);
                        ind6.setImageResource(R.drawable.unselected);
                        break;
                    case 5:
                        ind1.setImageResource(R.drawable.unselected);
                        ind2.setImageResource(R.drawable.unselected);
                        ind3.setImageResource(R.drawable.unselected);
                        ind4.setImageResource(R.drawable.unselected);
                        ind5.setImageResource(R.drawable.unselected);
                        ind6.setImageResource(R.drawable.selected);
                        break;
                }
            }
        };

// set the onPageChangeCallback to your ViewPager2
        ViewPager2 viewPager = findViewById(R.id.viewPagerImageSlider);
        viewPager.registerOnPageChangeCallback(onPageChangeCallback);


        viewPager2 = findViewById(R.id.viewPagerImageSlider);

        List<Slideritem> slideritems = new ArrayList<>();
        slideritems.add(new Slideritem(R.drawable.slider3));
        slideritems.add(new Slideritem(R.drawable.slider2));
        slideritems.add(new Slideritem(R.drawable.slider1));
        slideritems.add(new Slideritem(R.drawable.slider5));
        slideritems.add(new Slideritem(R.drawable.eyeshadow));
        slideritems.add(new Slideritem(R.drawable.watche));

        viewPager2.setAdapter(new SliderAdapter(slideritems,viewPager2));


        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f + 0.15f*r);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable,3000);
            }
        });


    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
    }
