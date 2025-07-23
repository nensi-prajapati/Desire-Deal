package com.example.desiredeal.activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.AppPreference;
import com.example.desiredeal.adapter.CategoriesAdapter;
import com.example.desiredeal.R;
import com.example.desiredeal.adapter.FeaturedProductAdapter;
import com.example.desiredeal.adapter.ProductforuAdapter;
import com.example.desiredeal.adapter.SliderAdapterBanner;
import com.example.desiredeal.adapter.SpecialityCategoriesAdapter;
import com.example.desiredeal.model.Category;
import com.example.desiredeal.model.MyAccountRecyclerModel;
import com.example.desiredeal.model.ProductForuModel;
import com.example.desiredeal.model.UserDetailModel;
import com.example.desiredeal.utils.DataModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.litepal.LitePal;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mCategoriesRecyclerView;
    private CategoriesAdapter mCategoriesAdapter;
    private RecyclerView mSpecialityCategoriesRecyclerView;
    private SpecialityCategoriesAdapter mSpecialityCategoriesAdapter;
    private RecyclerView FeaturedProductRecyclerView;
    private FeaturedProductAdapter featuredProductAdapter;
    private RecyclerView ProductforyouRecyclerview;
    private ProductforuAdapter productforuAdapter;
    private ProductForuModel productforu;
    private List<UserDetailModel> Detaillist;
    ImageView wishlistImageView;
    ImageView Userimage;
    TextView username, useremail;
    UserDetailModel userDetailModel;

    private boolean loggedOut = false;

    private MainActivity context;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        wishlistImageView = findViewById(R.id.wishlist);

//
        //
        if (wishlistImageView != null) {
            wishlistImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isWishlisted = !productforu.isWishlisted();
                    productforu.setWishlisted(isWishlisted);

                    // Update the wishlist image based on the new status
                    if (isWishlisted) {
                        wishlistImageView.setImageResource(R.drawable.ic_wihlist_full_foreground);
                    } else {
                        wishlistImageView.setImageResource(R.drawable.ic_wishlist_unfull_foreground);
                    }

                    // Update the wishlist status of the product model in the app preference
                    AppPreference appPreference = new AppPreference(MainActivity.this);
                    appPreference.addToWishlist(productforu);

                    // Send a broadcast to notify the detailed activity about the wishlist update
                    Intent intent = new Intent("wishlist_updated");
                    intent.putExtra("productName", productforu.getName());
                    intent.putExtra("isWishlisted", isWishlisted);
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                }
            });
        }


        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });



        mCategoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        mCategoriesAdapter = new CategoriesAdapter(DataModel.getAllCategories());
        mCategoriesRecyclerView.setAdapter(mCategoriesAdapter);
        mCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        mCategoriesAdapter.setOnItemClickListener(new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                Intent intent = new Intent(MainActivity.this, categories.class);
                startActivity(intent);
            }
        });

        mSpecialityCategoriesRecyclerView = findViewById(R.id.specialityCategoriesRecyclerView);
        productforuAdapter = new ProductforuAdapter(DataModel.Specialcategory(), this);
        mSpecialityCategoriesRecyclerView.setAdapter(productforuAdapter);
        mSpecialityCategoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        productforuAdapter.setOnItemClickListener(new ProductforuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductForuModel productforu) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("name", productforu.getName());
                intent.putExtra("image", productforu.getImage());
                intent.putExtra("speciality_data", productforu);
                startActivity(intent);
            }
        });

        FeaturedProductRecyclerView = findViewById(R.id.FeaturedProductRecyclerView);
        productforuAdapter = new ProductforuAdapter(DataModel.featuredProductModels(), this);
        FeaturedProductRecyclerView.setAdapter(productforuAdapter);
        FeaturedProductRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        productforuAdapter.setOnItemClickListener(new ProductforuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductForuModel productforu) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("name", productforu.getName());
                intent.putExtra("image", productforu.getImage());
                intent.putExtra("Featured_data", productforu);
                startActivity(intent);
            }
        });

        ProductforyouRecyclerview = findViewById(R.id.ProductforyouRecyclerview);
        productforuAdapter = new ProductforuAdapter(DataModel.productForuUser(), this);
        ProductforyouRecyclerview.setAdapter(productforuAdapter);
        int GridLayoutManager = 2;
        ProductforyouRecyclerview.setLayoutManager(new GridLayoutManager(this, GridLayoutManager, RecyclerView.VERTICAL, false));

        productforuAdapter.setOnItemClickListener(new ProductforuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ProductForuModel productforu) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("name", productforu.getName());
                intent.putExtra("image", productforu.getImage());
                intent.putExtra("Product_for_u", productforu);
                startActivity(intent);
            }

        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) CardView cardView = findViewById(R.id.womenfashion);

        ImageView addToCartImageView = findViewById(R.id.addtocart);
        addToCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddToCart.class);
                startActivity(intent);
            }
        });

        LinearLayout myorders = findViewById(R.id.myorders);
        myorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the home activity here
                Intent intent = new Intent(MainActivity.this, MyOrders.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }

        });

        LinearLayout HomeLayout = findViewById(R.id.ll_home);
        HomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the home activity here
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        LinearLayout myCategoryLayout = findViewById(R.id.Categories);
        myCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the home activity here
                Intent intent = new Intent(MainActivity.this, categories.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }

        });

        LinearLayout mywishlistLayout = findViewById(R.id.Wishlist);
        mywishlistLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the home activity here
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        LinearLayout myOrdersLayout = findViewById(R.id.Account);
        myOrdersLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the home activity here
                Intent intent = new Intent(MainActivity.this, MyAccount.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        LinearLayout HelpcenterLayout = findViewById(R.id.Help);
        HelpcenterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the home activity here
                Intent intent = new Intent(MainActivity.this, HelpCenter.class);
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        LinearLayout LogoutLayout = findViewById(R.id.Logout);
        LogoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the loggedOut flag to true
                loggedOut = true;

                // Save the logout status in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("logout", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("loggedOut", loggedOut);
                editor.apply();

                // Clear user-related data and redirect to login screen
                clearUserData();
                clearSignupData();

                // Update the login status in SharedPreferences
                SharedPreferences loginPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor loginEditor = loginPreferences.edit();
                loginEditor.putBoolean("isLoggedIn", false);
                loginEditor.apply();

                // Redirect to login screen
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }



//        private void clearSignupData() {
//                SharedPreferences signupPreferences = getSharedPreferences("signup", MODE_PRIVATE);
//                SharedPreferences.Editor signupEditor = signupPreferences.edit();
//                signupEditor.clear();
//                signupEditor.apply();
//            }
//
//
//            private void clearUserData() {
//                // Clear user-related data, such as preferences, database records, etc.
//                // Example: Clear the stored username and password
//                SharedPreferences preferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.clear();  // Clear all stored preferences
//                editor.apply();
//            }
        });

        username = findViewById(R.id.NameofUser);
        Userimage = findViewById(R.id.ImageofUser);


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            String email = currentUser.getEmail();
            TextView emailTextView = findViewById(R.id.EmailUser);
            emailTextView.setText(email);
            emailTextView.setEnabled(false); // disable editing
        }

        UserDetailModel userDetailModel = LitePal.findFirst(UserDetailModel.class);


        if (userDetailModel != null) {
            username.setText(userDetailModel.getName());
            Glide.with(this)
                    .load(userDetailModel.getImagepath())
                    .into(Userimage);
        }
        /////////////////// banner Slider for offer home ///////////////////

        ViewPager viewPager = findViewById(R.id.view_pager);
        SliderAdapterBanner sliderAdapterBanner = new SliderAdapterBanner();
        viewPager.setAdapter(sliderAdapterBanner);

        /////////////////// banner Slider for offer home ///////////////////

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.pink));
        }


    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if the user is logged out
        SharedPreferences preferences = getSharedPreferences("logout", MODE_PRIVATE);
        boolean loggedOut = preferences.getBoolean("loggedOut", false);

        if (loggedOut) {
            // Clear user-related data and redirect to login screen
            clearUserData();
            clearSignupData();

            // Update the login status in SharedPreference
            SharedPreferences loginPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor loginEditor = loginPreferences.edit();
            loginEditor.putBoolean("isLoggedIn", true);
            loginEditor.apply();


            // Redirect to login screen
//            Intent intent = new Intent(MainActivity.this, Welcome.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
        }
    }

    private void clearUserData() {
        // Clear user-related data, such as preferences, database records, etc.
        // Example: Clear the stored username and password
        SharedPreferences preferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();  // Clear all stored preferences
        editor.apply();
    }

    private void clearSignupData() {
        SharedPreferences signupPreferences = getSharedPreferences("signup", MODE_PRIVATE);
        SharedPreferences.Editor signupEditor = signupPreferences.edit();
        signupEditor.clear();
        signupEditor.apply();
    }


}