package com.example.desiredeal.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desiredeal.R;
import com.example.desiredeal.adapter.AddToCartAdapter;
import com.example.desiredeal.model.ProductForuModel;
import com.google.android.material.appbar.MaterialToolbar;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddToCart extends AppCompatActivity {
    private RecyclerView cartRecyclerView;
    private AddToCartAdapter addToCartAdapter;

    private MaterialToolbar toolbar;
    private ImageView close;
    private List<ProductForuModel> productList;

    private double totalPrice = 0;
    private TextView totalPriceTextView;
    private static final int REQUEST_CHECKOUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        cartRecyclerView = findViewById(R.id.CartRecyclerView);
        totalPriceTextView = findViewById(R.id.total_price);

        productList = new ArrayList<>(LitePal.findAll(ProductForuModel.class));

        addToCartAdapter = new AddToCartAdapter((ArrayList<ProductForuModel>) productList, true,
                new AddToCartAdapter.OnCartRemoveClickListener() {
                    @Override
                    public void onRemoveClick(int position, double currentPrice) {
                        // Check if the list is empty before removing an item
                        if (!productList.isEmpty() && position < productList.size()) {
                            ProductForuModel removedProduct = productList.remove(position);
                            addToCartAdapter.notifyItemRemoved(position);
                            addToCartAdapter.notifyItemRangeChanged(position, productList.size() - position);
                            toolbar.setTitle("Cart (" + productList.size() + ")");

                            // Subtract the price of the removed product from the total price
                            double price = Double.parseDouble(removedProduct.getPrice());
                            int quantity = removedProduct.getCount();
                            totalPrice -= price * quantity;

                            // Update the total price on the screen
                            totalPriceTextView.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                            // Delete the removed product from the database
                            removedProduct.delete();
                        }
                    }


                    @Override
                    public void onadditem(int position, TextView quantity) {
                        int count = productList.get(position).getCount();
                        double price = Double.parseDouble(productList.get(position).getPrice());

                        count += 1;
                        quantity.setText(String.valueOf(count));
                        productList.get(position).setCount(count);

                        // Calculate the new price
                        double newPrice = price * count;

                        // Update the total price
                        totalPrice += price;
                        totalPriceTextView.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                        productList.get(position).save();
                    }

                    @Override
                    public void onremiveitem(int position, TextView quantity) {
                        int count = productList.get(position).getCount();
                        double price = Double.parseDouble(productList.get(position).getPrice());

                        if (count > 0) {
                            count -= 1;
                            quantity.setText(String.valueOf(count));
                            productList.get(position).setCount(count);

                            // Calculate the new price
                            double newPrice = price * count;

                            // Update the total price
                            totalPrice -= price;
                            totalPriceTextView.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

                            productList.get(position).save();
                        }
                    }
                });

        cartRecyclerView.setAdapter(addToCartAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Cart (" + productList.size() + ")");

        close = findViewById(R.id.close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        for (ProductForuModel product : productList) {
            totalPrice += Double.parseDouble(product.getPrice()) * product.getCount();
        }

        totalPriceTextView.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(AddToCart.this, R.color.pink));
        }

        TextView checkout = findViewById(R.id.CheckOut);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> productIds = new ArrayList<>();
                for (ProductForuModel product : productList) {
                    productIds.add(product.getId());
                }
                Intent intent = new Intent(AddToCart.this, CheckOut.class);
                intent.putIntegerArrayListExtra("product_ids", productIds);
                startActivityForResult(intent, REQUEST_CHECKOUT);
            }
        });
    }

    private double calculateTotalPrice(List<ProductForuModel> productList) {
        double totalPrice = 0;
        for (ProductForuModel product : productList) {
            double price = Double.parseDouble(product.getPrice());
            int quantity = product.getCount();
            totalPrice += price * quantity;
        }
        return totalPrice;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHECKOUT && resultCode == RESULT_OK) {
            if (data != null) {
                int position = data.getIntExtra("position", -1);
                int updatedQuantity = data.getIntExtra("quantity", -1);

                if (position != -1 && updatedQuantity != -1) {
                    ProductForuModel product = productList.get(position);
                    product.setCount(updatedQuantity);
                    addToCartAdapter.notifyItemChanged(position);

                    // Recalculate the total price
                    totalPrice = calculateTotalPrice(productList);
                    totalPriceTextView.setText("Total price: ₹" + String.format(Locale.getDefault(), "%.2f", totalPrice));
                }
            }
        }
    }
}

