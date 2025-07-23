package com.example.desiredeal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.model.ProductForuModel;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.AddToCardViewHolder> {

    private List<ProductForuModel> mCategories;
    private OnCartRemoveClickListener listener;

    Boolean isAddToCart;

    private Context context;
    public AddToCartAdapter(ArrayList<ProductForuModel> mCategories,Boolean isAddToCart,OnCartRemoveClickListener listener) {
        this.mCategories = mCategories;
        this.isAddToCart = isAddToCart;
        this.listener = listener;

    }

    @NonNull
    @Override
    public AddToCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem, parent, false);
        return new AddToCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddToCardViewHolder holder, int position) {

        ProductForuModel productforu = mCategories.get(position);

        holder.Image.setImageResource(productforu.getImage());
        holder.Name.setText(productforu.getName());
        holder.Price.setText(productforu.getPrice());
        holder.quantity.setText(String.valueOf(productforu.getCount()));

        // update the price and quantity when the "add to cart" button is clicked
        holder.onadditem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onadditem(position,holder.quantity);
                }

            }
        });

        // update the price and quantity when the "remove from cart" button is clicked
        holder.onremiveitem.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (listener!=null){
                    listener.onremiveitem(position,holder.quantity);
                }

            }
        });

        holder.onRemoveClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the product at the specified position
                ProductForuModel removedProduct = mCategories.get(position);

                // Calculate the price of the removed product
                double currentPrice = Double.parseDouble(removedProduct.getPrice()) * removedProduct.getCount();

                // Remove the item from the list if it's not empty
                if (!mCategories.isEmpty()) {
                    mCategories.remove(position);
                    // Delete the item from the database if it is added to the cart
                    if (isAddToCart) {
                        LitePal.delete(ProductForuModel.class, removedProduct.getId());
                    }
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, mCategories.size());
                }

                // Notify the listener about the removal and pass the position and current price
                if (listener != null) {
                    listener.onRemoveClick(position, currentPrice);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }



    // interface to notify the activity when the price changes
    public interface OnCartRemoveClickListener {
        void onRemoveClick(int position, double currentPrice);
        void onadditem(int position, TextView quantity);
        void onremiveitem(int position, TextView quantity);
    }


    public class AddToCardViewHolder extends RecyclerView.ViewHolder {

        ImageView Image,onadditem,onremiveitem;
        TextView Name,Price,quantity,onRemoveClick;

        public AddToCardViewHolder(@NonNull View itemView) {
            super(itemView);
            Image = itemView.findViewById(R.id.AddtoCartImage);
            Name = itemView.findViewById(R.id.AddtoCartName);
            Price = itemView.findViewById(R.id.AddtoCartPrice);
            quantity = itemView.findViewById(R.id.quantity);
            onadditem = itemView.findViewById(R.id.addtocart);
            onremiveitem = itemView.findViewById(R.id.removetocart);
            onRemoveClick = itemView.findViewById(R.id.cart_remove);
        }

        public void updateQuantityAndPrice(int position, int newQuantity, double newPrice) {
            ProductForuModel product = mCategories.get(position);
            product.setCount(newQuantity);
            product.setPrice(String.valueOf(newPrice));
            notifyItemChanged(position);
        }

    }}
