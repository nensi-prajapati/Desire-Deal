package com.example.desiredeal.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.model.RelatedProductModel;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.RelatedCategoryViewHolder> {


    private List<RelatedProductModel> relatedProductModels;

    public RelatedProductAdapter(List<RelatedProductModel> relatedProductModels) {
        this.relatedProductModels = relatedProductModels;
    }


    @NonNull
    @Override
    public RelatedProductAdapter.RelatedCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new RelatedCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductAdapter.RelatedCategoryViewHolder holder, int position) {

        RelatedProductModel relatedProductModel = relatedProductModels.get(position);
        holder.bind(relatedProductModel);
    }

    @Override
    public int getItemCount() {
        return relatedProductModels.size();
    }

    public class RelatedCategoryViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mNameTextView;
        public RelatedCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.specialityCategoryImageView);
            mNameTextView = itemView.findViewById(R.id.specialityCategoryNameTextView);
        }

        public void bind(RelatedProductModel relatedProductModel) {
            mImageView.setImageResource(relatedProductModel.getImage());
            mNameTextView.setText(relatedProductModel.getName());
        }
    }
}
