package com.example.desiredeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.model.ProductForuModel;

import java.util.List;

public class SpecialityCategoriesAdapter extends RecyclerView.Adapter<SpecialityCategoriesAdapter.SpecialityCategoryViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ProductForuModel productForuModel);

    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private List<ProductForuModel> productForuModels;

    public SpecialityCategoriesAdapter(List<ProductForuModel> productForuModels) {
        this.productForuModels = productForuModels;
    }

    @NonNull
    @Override
    public SpecialityCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new SpecialityCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialityCategoryViewHolder holder, int position) {
        ProductForuModel productforu = productForuModels.get(position);
        holder.bind(productforu);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(productforu);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return productForuModels.size();
    }

    public static class SpecialityCategoryViewHolder extends RecyclerView.ViewHolder {


        private ImageView mImageView;
        private TextView mNameTextView;
        private ImageView wishlist;

        public SpecialityCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.specialityCategoryImageView);
            mNameTextView = itemView.findViewById(R.id.specialityCategoryNameTextView);
            wishlist = itemView.findViewById(R.id.wishlist);
        }

        public void bind(ProductForuModel productforu) {
            mImageView.setImageResource(productforu.getImage());
            mNameTextView.setText(productforu.getName());
        }
    }}