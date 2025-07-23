package com.example.desiredeal.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.AppPreference;
import com.example.desiredeal.R;
import com.example.desiredeal.adapter.DetailedpageAdapter;
import com.example.desiredeal.adapter.RelatedProductAdapter;
import com.example.desiredeal.model.ProductForuModel;
import com.example.desiredeal.utils.DataModel;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity {

    private RecyclerView detailrecyclerview;
    private DetailedpageAdapter detailedpageAdapter;
    private RecyclerView RelatedProductRecyclerview;
    private RelatedProductAdapter relatedProductAdapter;

    private ProductForuModel productforu;
    Intent intent;
    ArrayList<Integer> relevantimagelist;

    private AppPreference appPreference;

    private ImageView wishlistImageView;

    private BroadcastReceiver wishlistUpdateReceiver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        // Initialize the wishlist update receiver
        // Initialize the wishlist update receiver
        wishlistUpdateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("wishlist_updated")) {
                    String productName = intent.getStringExtra("productName");
                    boolean isWishlisted = intent.getBooleanExtra("isWishlisted", false);

                    // Do something with the updated wishlist status
                    Log.d("DetailedActivity", "Received wishlist update for product " + productName + ": " + isWishlisted);

                    // Update the wishlist image based on the new status
                    if (productName.equals(productforu.getName())) {
                        if (isWishlisted) {
                            wishlistImageView.setImageResource(R.drawable.ic_wihlist_full_foreground);
                        } else {
                            wishlistImageView.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
                        }

                        // Update the wishlist status of the product model
                        productforu.setWishlisted(isWishlisted);
                    }
                }
            }
        };


        TextView addtocart = findViewById(R.id.AddToCartButton);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productforu.save();
                startActivity(new Intent(DetailedActivity.this,AddToCart.class));

            }

        });


        ImageView addToCartImageView = findViewById(R.id.addtocart);
        addToCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedActivity.this, AddToCart.class);
                startActivity(intent);

            }
        });

//        TextView buy_now = findViewById(R.id.buy_now);
//        buy_now.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Create a new intent for the CheckOut activity
//                Intent intent = new Intent(DetailedActivity.this, CheckOut.class);
//                // Pass the selected product as an extra
//                intent.putExtra("product", productforu);
//                // Pass a flag indicating that it's a "Buy Now" scenario
//                intent.putExtra("isBuyNow", true);
//                startActivity(intent);
//            }
//
//        });


        intent = getIntent();
        productforu = (ProductForuModel) intent.getSerializableExtra("product");


        LocalBroadcastManager.getInstance(this)
                .registerReceiver(wishlistBroadcastReceiver, new IntentFilter("wishlist_updated"));


        appPreference = new AppPreference(this);

        wishlistImageView = findViewById(R.id.wishlist);


//        if (wishlistImageView != null) {
//            wishlistImageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//
//                    if (appPreference.isInWishlist(productforu)) {
//
//                        // If the item is already in the wishlist, remove it and update the icon
//                        appPreference.removeFromWishlist(productforu);
//                        wishlistImageView.setImageResource(R.drawable.ic_wihlist_unfull_foreground);
//                        Toast.makeText(DetailedActivity.this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
//                    } else {
//                        // If the item is not in the wishlist, add it and update the icon
//                        appPreference.addToWishlist(productforu);
//                        wishlistImageView.setImageResource(R.drawable.ic_wihlist_full_foreground);
//                        Toast.makeText(DetailedActivity.this, "Added to wishlist", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//            });
//
//        }


        wishlistImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isWishlisted = !productforu.isWishlisted();
                productforu.setWishlisted(isWishlisted);

                // Update the wishlist image based on the new status
                if (appPreference.isInWishlist(productforu)) {
                    // If the item is already in the wishlist, remove it and update the icon
                    appPreference.removeFromWishlist(productforu);
                    wishlistImageView.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
                    Toast.makeText(DetailedActivity.this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    wishlistImageView.setImageResource(R.drawable.ic_wihlist_full_foreground);
                    // Update the wishlist status of the product model in the app preference
                    appPreference.addToWishlist(productforu);

                    // Notify the adapter that the data has changed
                    Intent intent = new Intent("wishlist_updated");
                    intent.putExtra("productName", productforu.getName());
                    intent.putExtra("isWishlisted", isWishlisted);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

                }


            }
        });




        MaterialToolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        // Set the back arrow color to white
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_foreground);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView ProductName,ProductPrice,ProductDescription;

        ProductName = findViewById(R.id.Productname);
        ProductPrice = findViewById(R.id.price);
        ProductDescription = findViewById(R.id.description);

        intent = getIntent();
        if (intent.hasExtra("data")) {
            productforu = (ProductForuModel) intent.getSerializableExtra("data");
            relevantimagelist = productforu.getRelevantimagelist();
            ProductName.setText(productforu.getName());
            ProductPrice.setText(productforu.getPrice());
            ProductDescription.setText(productforu.getDescription());

        } else if (intent.hasExtra("speciality_data")) {
            productforu= (ProductForuModel) intent.getSerializableExtra("speciality_data");
            relevantimagelist = productforu.getRelevantimagelist();
            ProductName.setText(productforu.getName());
            ProductPrice.setText(productforu.getPrice());
            ProductDescription.setText(productforu.getDescription());
        }  else if (intent.hasExtra("Featured_data")) {
            productforu= (ProductForuModel) intent.getSerializableExtra("Featured_data");
            relevantimagelist = productforu.getRelevantimagelist();
            ProductName.setText(productforu.getName());
            ProductPrice.setText(productforu.getPrice());
            ProductDescription.setText(productforu.getDescription());

        }   else if (intent.hasExtra("Product_for_u")) {
            productforu = (ProductForuModel) intent.getSerializableExtra("Product_for_u");
            relevantimagelist = productforu.getRelevantimagelist();
            ProductName.setText(productforu.getName());
            ProductPrice.setText(productforu.getPrice());
            ProductDescription.setText(productforu.getDescription());
        }

        detailrecyclerview = findViewById(R.id.detailrecyclerview);
        detailedpageAdapter = new DetailedpageAdapter(relevantimagelist);
        detailrecyclerview.setAdapter(detailedpageAdapter);
        detailrecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        RelatedProductRecyclerview = findViewById(R.id.RelatedProductRecyclerview);
        relatedProductAdapter = new RelatedProductAdapter(DataModel.relatedProductModels());
        RelatedProductRecyclerview.setAdapter(relatedProductAdapter);
        RelatedProductRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



        ///////////////////////

        ////////////////////////

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(DetailedActivity.this, R.color.pink));
        }

    }

    private BroadcastReceiver wishlistBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String productName = intent.getStringExtra("productName");
            boolean isWishlisted = intent.getBooleanExtra("isWishlisted", false);

            if (productName.equals(productforu.getName())) {
                // Update the wishlist image based on the new status
                if (isWishlisted) {
                    wishlistImageView.setImageResource(R.drawable.ic_wihlist_full_foreground);
                } else {
                    wishlistImageView.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
                }

                // Update the wishlist status of the product model
                productforu.setWishlisted(isWishlisted);
            }
        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the broadcast receiver
        LocalBroadcastManager.getInstance(this).unregisterReceiver(wishlistBroadcastReceiver);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Read the wishlist status of the product from SharedPreferences and update the wishlist image
        boolean isWishlisted = appPreference.isInWishlist(productforu);
        if (isWishlisted) {
            wishlistImageView.setImageResource(R.drawable.ic_wihlist_full_foreground);
        } else {
            wishlistImageView.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
        }

        // Register the wishlist update receiver
        IntentFilter filter = new IntentFilter("wishlist_updated");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(wishlistUpdateReceiver, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Unregister the wishlist update receiver
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(wishlistUpdateReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
