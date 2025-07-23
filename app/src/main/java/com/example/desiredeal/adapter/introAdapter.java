package com.example.desiredeal.adapter;



import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.desiredeal.R;

//public class introAdapter extends RecyclerView.ViewHolder {
//
//    private int[] bannerImages = {R.drawable.welcome_screen_1, R.drawable.welcome_screen_2, R.drawable.welcome_screen_3};
//
//    @NonNull
//    @Override
//    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_slider1, parent, false);
//        return new IntroViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull IntroViewHolder holder, int position) {
//        holder.bannerImage.setImageResource(bannerImages[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return bannerImages.length;
//    }
//
//    public static class IntroViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView bannerImage;
//
//        public IntroViewHolder(@NonNull View itemView) {
//            super(itemView);
//            bannerImage = itemView.findViewById(R.id.welcome1);
//        }
//    }
//}
