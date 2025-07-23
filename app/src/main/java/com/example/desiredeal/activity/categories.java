package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.desiredeal.R;
import com.example.desiredeal.adapter.CategoryPagerAdapter;
import com.google.android.material.tabs.TabLayout;


public class categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager= findViewById(R.id.viewpagecat);

        tabLayout.addTab(tabLayout.newTab().setText("All Categories"));
        tabLayout.addTab(tabLayout.newTab().setText("Women"));
        tabLayout.addTab(tabLayout.newTab().setText("Men"));
        tabLayout.addTab(tabLayout.newTab().setText("Kids"));
        tabLayout.addTab(tabLayout.newTab().setText("Accessories"));
        tabLayout.addTab(tabLayout.newTab().setText("Electronic"));
        tabLayout.addTab(tabLayout.newTab().setText("Beauty"));
        tabLayout.addTab(tabLayout.newTab().setText("Shoes"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager.setAdapter(new CategoryPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        ImageView back = findViewById(R.id.back_icon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView addToCartImageView = findViewById(R.id.addtocart);
        addToCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(categories.this, AddToCart.class);
                startActivity(intent);

            }
        });



    }
    }



