package com.example;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desiredeal.model.ProductForuModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppPreference {

    private ProductForuModel productForuModel;
    private Context context;

    public AppPreference(Context context) {
        this.context = context;
    }

    public void addToWishlist(ProductForuModel productForuModel) {
        // Retrieve the current wishlist from SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("wishlist", Context.MODE_PRIVATE);
        String wishlistJson = sharedPreferences.getString("wishlistJson", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductForuModel>>() {}.getType();
        List<ProductForuModel> wishlist = gson.fromJson(wishlistJson, type);
        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }
        // Add the selected product to the wishlist
        wishlist.add(productForuModel);

        // Set the wishlist image clicked flag to true
        sharedPreferences.edit().putBoolean("wishlist_image_clicked", true).apply();

        // Save the updated wishlist to SharedPreferences
        String updatedWishlistJson = gson.toJson(wishlist);
        sharedPreferences.edit().putString("wishlistJson", updatedWishlistJson).apply();

        // Show a toast message
        Toast.makeText(context, "Added to wishlist", Toast.LENGTH_SHORT).show();
    }


    public List<ProductForuModel> getWishlist() {
        // Retrieve the current wishlist from SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("wishlist", Context.MODE_PRIVATE);
        String wishlistJson = sharedPreferences.getString("wishlistJson", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductForuModel>>() {}.getType();
        List<ProductForuModel> wishlist = gson.fromJson(wishlistJson, type);
        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }
        return wishlist;
    }

    public void saveWishlist(List<ProductForuModel> wishlist) {
        // Save the updated wishlist to SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("wishlist", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String updatedWishlistJson = gson.toJson(wishlist);
        sharedPreferences.edit().putString("wishlistJson", updatedWishlistJson).apply();
    }

    public void removeFromWishlist(ProductForuModel productForuModel) {
        List<ProductForuModel> wishlist = getWishlist();
        List<ProductForuModel> newWishlist = new ArrayList<>();

        for (ProductForuModel product : wishlist) {
            if (!product.getName().equals(productForuModel.getName())) {
                newWishlist.add(product);
            }
        }

        // Save the updated wishlist to SharedPreferences
        saveWishlist(newWishlist);

        // Show a toast message
        Toast.makeText(context, "Product removed from wishlist", Toast.LENGTH_SHORT).show();
    }

    public boolean isInWishlist(ProductForuModel productForuModel) {
        List<ProductForuModel> wishlist = getWishlist();
        for (ProductForuModel product : wishlist) {
            if (product.getName().equals(productForuModel.getName())) {
                return true;
            }
        }
        return false;
    }


    public MutableLiveData<List<ProductForuModel>> getWishlistObservable() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("wishlist", Context.MODE_PRIVATE);
        String wishlistJson = sharedPreferences.getString("wishlistJson", "");
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductForuModel>>() {}.getType();
        List<ProductForuModel> wishlist = gson.fromJson(wishlistJson, type);
        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }
        MutableLiveData<List<ProductForuModel>> wishlistLiveData = new MutableLiveData<>();
        wishlistLiveData.setValue(wishlist);
        return wishlistLiveData;
    }

}
