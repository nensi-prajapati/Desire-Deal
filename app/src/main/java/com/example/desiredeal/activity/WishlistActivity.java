package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.AppPreference;
import com.example.desiredeal.R;
import com.example.desiredeal.adapter.ProductforuAdapter;
import com.example.desiredeal.adapter.WishlistAdapter;
import com.example.desiredeal.model.ProductForuModel;
import com.example.desiredeal.utils.DataModel;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ProductForuModel> wishlist;
    private AppPreference appPreference;
    ArrayList<Integer> relevantimagelist;

    private ProductForuModel productforu;
    Intent intent;
    private ProductforuAdapter productforuAdapter;

    private RecyclerView WishlistRecyclerView;

    private WishlistAdapter wishlistAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);


        MaterialToolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        // Set the back arrow color to white
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_foreground);

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        appPreference = new AppPreference(this);

        recyclerView = findViewById(R.id.WishlistRecyclerView);
        int gridLayoutManager = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, gridLayoutManager, RecyclerView.VERTICAL, false));

        // Retrieve the wishlist from SharedPreferences
        //wishlist = appPreference.getWishlist();

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlist, this);
        recyclerView.setAdapter(wishlistAdapter);

        wishlistAdapter.setOnItemClickListener(new WishlistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductForuModel productforu) {
                Intent intent = new Intent(WishlistActivity.this, DetailedActivity.class);
                intent.putExtra("name", productforu.getName());
                intent.putExtra("image", productforu.getImage());
                intent.putExtra("Product_for_u",productforu);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(WishlistActivity.this,R.color.pink));
        }

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