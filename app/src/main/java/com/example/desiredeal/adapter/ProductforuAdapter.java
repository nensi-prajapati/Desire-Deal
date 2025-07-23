package com.example.desiredeal.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPreference;
import com.example.desiredeal.R;
import com.example.desiredeal.model.ProductForuModel;

import java.util.List;

public class ProductforuAdapter extends RecyclerView.Adapter<ProductforuAdapter.ProductforuViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ProductForuModel productForuModel);
    }

    private ProductforuAdapter.OnItemClickListener listener;
    private List<ProductForuModel> productForuModels;
    private AppPreference appPreference;

    private boolean iswishlisted;


        private BroadcastReceiver wishlistBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String productName = intent.getStringExtra("productName");
            boolean isWishlisted = intent.getBooleanExtra("isWishlisted", false);

            // Find the product in the list and update its wishlist status
            for (int i = 0; i < productForuModels.size(); i++) {
                ProductForuModel productforu = productForuModels.get(i);
                if (productforu.getName().equals(productName)) {
                    productforu.setWishlisted(isWishlisted);
                    notifyItemChanged(i);
                    break;
                }
            }
        }
    };
    public ProductforuAdapter(List<ProductForuModel> productForuModels, Context context) {
        this.productForuModels = productForuModels;
        this.appPreference = new AppPreference(context);
        this.iswishlisted=false;
    }

    public void setOnItemClickListener(ProductforuAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductforuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new ProductforuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductforuViewHolder holder, int position) {

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

        LocalBroadcastManager.getInstance(holder.itemView.getContext())
                .registerReceiver(wishlistBroadcastReceiver, new IntentFilter("wishlist_updated"));

        holder.wishlistImage.setTag(productforu.getName());

        boolean isWishlisted = appPreference.isInWishlist(productforu);

        if (isWishlisted) {
            holder.wishlistImage.setImageResource(R.drawable.ic_wihlist_full_foreground);
        } else {
            holder.wishlistImage.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
        }

        holder.wishlistImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isWishlisted = !productforu.isWishlisted();
                productforu.setWishlisted(isWishlisted);

                if (isWishlisted) {
                    holder.wishlistImage.setImageResource(R.drawable.ic_wihlist_full_foreground);
                    // Update the wishlist status in the AppPreference
                    appPreference.addToWishlist(productforu);
                } else {
                    holder.wishlistImage.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
                    appPreference.removeFromWishlist(productforu);
                }

                // Notify the adapter that the data has changed
                notifyDataSetChanged();

            }
        });
    }



    @Override
    public int getItemCount() {
        return productForuModels.size();
    }

    public class ProductforuViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mNameTextView;
        private ImageView wishlistImage;

        public ProductforuViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.specialityCategoryImageView);
            mNameTextView = itemView.findViewById(R.id.specialityCategoryNameTextView);
            wishlistImage = itemView.findViewById(R.id.wishlist);
        }

        public void bind(ProductForuModel productforu) {
            mImageView.setImageResource(productforu.getImage());
            mNameTextView.setText(productforu.getName());
            wishlistImage.setImageResource(productforu.isWishlisted() ? R.drawable.ic_wihlist_full_foreground : R.drawable.ic_wishlist_unfull_foreground);
        }
    }
}
