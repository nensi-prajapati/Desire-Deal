package com.example.desiredeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.activity.WishlistActivity;
import com.example.desiredeal.model.ProductForuModel;
import com.example.AppPreference;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private List<ProductForuModel> wishlist;
    private WishlistActivity wishlistActivity;
    private AppPreference appPreference;
    private boolean wishlisted;

    public WishlistAdapter(List<ProductForuModel> wishlist, WishlistActivity wishlistActivity) {
        this.wishlist = wishlist;
        this.wishlistActivity = wishlistActivity;
        appPreference = new AppPreference(wishlistActivity);
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your view holder layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        ProductForuModel product = wishlist.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(product);
                }
            }
        });

        holder.imageView.setImageResource(product.getImage());
        holder.nameTextView.setText(String.valueOf(product.getName()));

        boolean isWishlisted = product.isWishlisted();
        if (isWishlisted){
            holder.img2.setImageResource(R.drawable.ic_wihlist_full_foreground);
        }
        else {
            holder.img2.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
        }

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isWishlisted = !product.isWishlisted();
                product.setWishlisted(isWishlisted);

                if (!isWishlisted) {
                    holder.img2.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
                    // Remove the product from the wishlist in the AppPreference
                    appPreference.removeFromWishlist(product);
                    // Notify the adapter that the data has changed
                    notifyDataSetChanged();
                }

            }
        });
        // ...
    }

    @Override
    public int getItemCount() {
        return wishlist.size();
    }

    static class WishlistViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        ImageView imageView,img2;
        // ...

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your view holder's views

            imageView = itemView.findViewById(R.id.specialityCategoryImageView);
            nameTextView = itemView.findViewById(R.id.specialityCategoryNameTextView);
            img2 = itemView.findViewById(R.id.wishlist);
        }
    }

    public void setData(List<ProductForuModel> wishlist) {
        this.wishlist = wishlist;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(ProductForuModel productForuModel);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // Add observer to AppPreference to update the RecyclerView when the wishlist changes
        appPreference.getWishlistObservable().observe(wishlistActivity, updatedWishlist -> {
            setData(updatedWishlist);
        });
    }
}
