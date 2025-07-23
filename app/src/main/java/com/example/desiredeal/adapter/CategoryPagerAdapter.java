package com.example.desiredeal.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.desiredeal.fragement.CategoryFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private static final String[] CATEGORIES = {"Women", "Men", "Kids", "Electronic", "Accessories", "Beauty", "Shoes"};

    public CategoryPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new CategoryFragment(CATEGORIES[position]);
    }

    @Override
    public int getCount() {
        return CATEGORIES.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return CATEGORIES[position];
    }
}

