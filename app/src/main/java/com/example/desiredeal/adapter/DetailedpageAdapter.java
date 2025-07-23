package com.example.desiredeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;

import java.util.ArrayList;

public class DetailedpageAdapter extends RecyclerView.Adapter<DetailedpageAdapter.DetailedPadeViewHolder> {

    ArrayList<Integer> relevantimagelist;

    public DetailedpageAdapter(ArrayList<Integer> relevantimagelist) {
        this.relevantimagelist = relevantimagelist;
    }

    @NonNull
    @Override
    public DetailedPadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new DetailedpageAdapter.DetailedPadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedPadeViewHolder holder, int position) {
        holder.bind(relevantimagelist.get(position));
    }

    @Override
    public int getItemCount() {
        return relevantimagelist.size();
    }

    public class DetailedPadeViewHolder extends RecyclerView.ViewHolder {

        private ImageView dImageview;
        public DetailedPadeViewHolder(@NonNull View itemView) {

            super(itemView);

            dImageview = itemView.findViewById(R.id.DetailedImageView);

        }

        public void bind(Integer img) {
            dImageview.setImageResource(img);
        }
    }
}
