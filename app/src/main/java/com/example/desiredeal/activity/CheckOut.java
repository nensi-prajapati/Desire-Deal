package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desiredeal.R;
import com.example.desiredeal.adapter.AddToCartAdapter;
import com.example.desiredeal.adapter.MyAccountRecyclerAdapter;
import com.example.desiredeal.model.MyAccountRecyclerModel;
import com.example.desiredeal.model.ProductForuModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CheckOut extends AppCompatActivity implements PaymentResultListener {

    private AddToCartAdapter addToCartAdapter;
    private double totalPrice = 0;
    TextView Total_checkout_price, Payment_now;

    private RecyclerView checkout_cart;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        Total_checkout_price = findViewById(R.id.Total_checkout_price);
        Payment_now = findViewById(R.id.Payment_now);
        MaterialToolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_left_foreground);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ProductForuModel> ProductList = LitePal.findAll(ProductForuModel.class);

        ArrayList<Integer> productIds = getIntent().getIntegerArrayListExtra("product_ids");
        List<ProductForuModel> checkoutProductList = LitePal.findAll(ProductForuModel.class);
        List<ProductForuModel> filteredProductList = new ArrayList<>();

        for (ProductForuModel product : checkoutProductList) {
            if (productIds.contains(product.getId())) {
                filteredProductList.add(product);
            }
        }

        checkout_cart = findViewById(R.id.checkout_cart);
        addToCartAdapter = new AddToCartAdapter((ArrayList<ProductForuModel>) checkoutProductList, false, new AddToCartAdapter.OnCartRemoveClickListener() {
            @Override
            public void onRemoveClick(int position, double currentPrice) {
                if (!checkoutProductList.isEmpty() && position < checkoutProductList.size()) {
                    ProductForuModel removedProduct = checkoutProductList.remove(position);
                    addToCartAdapter.notifyItemRemoved(position);
                    addToCartAdapter.notifyItemRangeChanged(position, checkoutProductList.size() - position);
                    toolbar.setTitle("Cart (" + checkoutProductList.size() + ")");

                    totalPrice -= currentPrice;

                    TextView total_checkout_price = findViewById(R.id.Total_checkout_price);
                    total_checkout_price.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                    Payment_now.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                    if (isProductInCart(removedProduct)) {
                        LitePal.delete(ProductForuModel.class, removedProduct.getId());
                    }
                }
            }

            private boolean isProductInCart(ProductForuModel product) {
                for (ProductForuModel cartProduct : checkoutProductList) {
                    if (cartProduct.getId() == product.getId()) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void onadditem(int position, TextView quantity) {
                ProductForuModel product = checkoutProductList.get(position); // Obtain the product instance
                int count = product.getCount();
                double price = Double.parseDouble(product.getPrice());
                count += 1;
                quantity.setText(String.valueOf(count));
                product.setCount(count);

                // Calculate the new price
                double newPrice = price * count;

                // Update the total price
                totalPrice += price;
                TextView total_checkout_price = findViewById(R.id.Total_checkout_price);
                total_checkout_price.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                Payment_now.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));
                product.save();

                // Pass the updated quantity back to the AddToCartActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("position", position);
                resultIntent.putExtra("quantity", count);
                setResult(RESULT_OK, resultIntent);
            }

            @Override
            public void onremiveitem(int position, TextView quantity) {
                ProductForuModel product = checkoutProductList.get(position); // Obtain the product instance
                int count = product.getCount();
                double price = Double.parseDouble(product.getPrice());

                if (count > 0) {
                    count -= 1;
                    quantity.setText(String.valueOf(count));
                    product.setCount(count);
                    double newPrice = price * count;

                    totalPrice -= price;
                    TextView total_checkout_price = findViewById(R.id.Total_checkout_price);
                    total_checkout_price.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                    Payment_now.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                    product.save();

                    // Pass the updated quantity back to the AddToCartActivity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("position", position);
                    resultIntent.putExtra("quantity", count);
                    setResult(RESULT_OK, resultIntent);
                }
            }


        });

        checkout_cart.setAdapter(addToCartAdapter);
        checkout_cart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Intent intent = getIntent();
        int productId = intent.getIntExtra("product_id", 0);
        String productName = intent.getStringExtra("product_name");
        double productPrice = intent.getDoubleExtra("product_price", 0.0);

        for (ProductForuModel product : ProductList) {
            totalPrice += Double.parseDouble(product.getPrice()) * product.getCount();
        }

        TextView total = findViewById(R.id.Total_checkout_price);
        total.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));
        Payment_now.setText("PAY ₹" + totalPrice + " & CHECKOUT");

        List<MyAccountRecyclerModel> addressList = LitePal.findAll(MyAccountRecyclerModel.class);

        RecyclerView checkout_address = findViewById(R.id.checkout_address);
        boolean mShowCheckbox = false;
        MyAccountRecyclerAdapter myAccountRecyclerAdapter = new MyAccountRecyclerAdapter((ArrayList<MyAccountRecyclerModel>) addressList, mShowCheckbox);
        checkout_address.setAdapter(myAccountRecyclerAdapter);
        checkout_address.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Payment_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment(totalPrice);
            }

            private void startPayment(double amount) {
                Checkout checkout = new Checkout();
                checkout.setKeyID("rzp_test_WHmelmdzVR6cYF");
                checkout.setImage(R.drawable.img_3);

                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", "DesireDeal");
                    jsonObject.put("Description", "Deal With Desire");
                    jsonObject.put("name", "DesireDeal");
                    jsonObject.put("description", "Deal With Desire");
                    jsonObject.put("theme.color", "#e82e5f");
                    jsonObject.put("currency", "INR");
                    jsonObject.put("prefill.email", "nensiprajapati99@gmail.com");
                    jsonObject.put("amount", amount * 100);
                    JSONObject retryObj = new JSONObject();
                    retryObj.put("enabled", true);
                    retryObj.put("max_count", 4);

                    jsonObject.put("retry", retryObj);

                    checkout.open(CheckOut.this, jsonObject);
                } catch (Exception e) {
                    Toast.makeText(CheckOut.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(CheckOut.this, R.color.pink));
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

    @Override
    public void onPaymentSuccess(String s) {
        LitePal.deleteAll(ProductForuModel.class);
        Toast.makeText(this, "Payment successful!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, OrderPlaced.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment error: " + s, Toast.LENGTH_SHORT).show();
    }
}

