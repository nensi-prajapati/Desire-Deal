package com.example.desiredeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.model.ProductForuModel;

import java.util.List;

public class BuyNowAdapter extends RecyclerView.Adapter<BuyNowAdapter.BuyNowViewHolder> {
    private List<ProductForuModel> products;

    public BuyNowAdapter(List<ProductForuModel> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public BuyNowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new BuyNowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BuyNowViewHolder holder, int position) {
        ProductForuModel product = products.get(position);
        // Bind the product data to the views in the ViewHolder
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class BuyNowViewHolder extends RecyclerView.ViewHolder {
        private TextView productName;

        public BuyNowViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.AddtoCartName);
        }

        public void bind(ProductForuModel product) {
            productName.setText(product.getName());

            // Bind other product data to the respective views
        }
    }
}

