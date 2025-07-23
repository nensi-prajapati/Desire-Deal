package com.example.desiredeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.model.MyAccountRecyclerModel;

import java.util.ArrayList;

public class MyAccountRecyclerAdapter extends RecyclerView.Adapter<MyAccountRecyclerAdapter.MyViewHolder> {

    private ArrayList<MyAccountRecyclerModel> data;
    private static OnItemClickListener listener;
    private boolean mShowCheckbox;
    private ArrayList<Integer> mSelectedItems = new ArrayList<>();

    public MyAccountRecyclerAdapter(ArrayList<MyAccountRecyclerModel> data, boolean mShowCheckbox) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.addres_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MyAccountRecyclerModel model = data.get(position);
        holder.nameTextView.setText(model.getName());
        holder.address1TextView.setText(model.getAddres_first());
        holder.address2TextView.setText(model.getAddress_second());
        holder.phoneNumberTextView.setText(String.valueOf(model.getPhonenumber()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(model);
                }

            }
        });

        // Set the visibility of checkbox based on number of items in the adapter
        if (getItemCount() > 1) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }

        // Set the checked status of checkbox
        holder.checkBox.setChecked(mSelectedItems.contains(position));

        // Set click listener for checkbox
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked()) {
                    mSelectedItems.add(position);
                } else {
                    mSelectedItems.remove(Integer.valueOf(position));
                }

            }
        });

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRemoveClick(position);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView address1TextView;
        TextView address2TextView;
        TextView phoneNumberTextView;
        ImageView remove;
        CheckBox checkBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.user_name);
            address1TextView = itemView.findViewById(R.id.user_address);
            address2TextView = itemView.findViewById(R.id.user_address2);
            phoneNumberTextView = itemView.findViewById(R.id.phone_number);
            remove = itemView.findViewById(R.id.remove);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MyAccountRecyclerModel model);

        void onRemoveClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Method to show or hide the checkbox
    public void setShowCheckbox(boolean showCheckbox) {
        mShowCheckbox = showCheckbox;
        notifyDataSetChanged();
    }

}

