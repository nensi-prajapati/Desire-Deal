package com.example.desiredeal.adapter;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.AppPreference;
import com.example.desiredeal.R;
import com.example.desiredeal.activity.DetailedActivity;
import com.example.desiredeal.model.ProductForuModel;

import java.util.ArrayList;
import java.util.List;

public class SectionCategoryAdapter extends RecyclerView.Adapter<SectionCategoryAdapter.SectioncatViewHolder> {

    private List<ProductForuModel> productForuModels;
    private ArrayList<ProductForuModel> sectioncategories;
    private AppPreference appPreference;
    Context context;
    String name;

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

    public SectionCategoryAdapter(List<ProductForuModel> productForuModels,Context context) {
        this.productForuModels = productForuModels;
        this.appPreference = new AppPreference(context);
        this.context = context;
    }

    @NonNull
    @Override
    public SectioncatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_speciality, parent, false);
        return new SectioncatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectioncatViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductForuModel productforu = productForuModels.get(position);
        name = productforu.getName();
        holder.bind(productforu);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                 intent.putExtra("data",productForuModels.get(position));
                intent.putExtra("Related_data",productForuModels.get(position));
                context.startActivity(intent);

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

    public void setData(ArrayList<ProductForuModel> sectioncategories) {
        this.sectioncategories = sectioncategories;
    }

    public class SectioncatViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mNameTextView;
        private CardView cardView;
        private ImageView wishlistImage;
        public SectioncatViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.specialityCategoryImageView);
            mNameTextView = itemView.findViewById(R.id.specialityCategoryNameTextView);
            cardView = itemView.findViewById(R.id.itemforcategory);
            wishlistImage = itemView.findViewById(R.id.wishlist);
        }
        public void bind(ProductForuModel productforu) {

            mImageView.setImageResource(productforu.getImage());
            mNameTextView.setText(productforu.getName());

        }
    }
}
