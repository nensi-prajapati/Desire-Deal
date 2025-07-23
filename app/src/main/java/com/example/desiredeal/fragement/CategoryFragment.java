package com.example.desiredeal.fragement;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desiredeal.R;
import com.example.desiredeal.adapter.ProductforuAdapter;
import com.example.desiredeal.adapter.SectionCategoryAdapter;
import com.example.desiredeal.utils.DataModel;

import java.util.ArrayList;
import java.util.Arrays;

/////////////////for category section viewpager (women,men,kids...) //////////////
public class CategoryFragment extends Fragment {
    public RecyclerView SectionCategoryRecyclerView;
    public SectionCategoryAdapter sectionCategoryAdapter;
    public String categoryName = "";

    public CategoryFragment(String category) {
        this.categoryName = category;
    }
    //////////////////////////////////
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        if (!TextUtils.isEmpty(categoryName)) {

        }

        SectionCategoryRecyclerView = view.findViewById(R.id.SectionCategoryRecyclerView);

        sectionCategoryAdapter = new SectionCategoryAdapter(DataModel.getSectioncategories(categoryName),requireActivity());
        SectionCategoryRecyclerView.setAdapter(sectionCategoryAdapter);

        int GridLayoutManager = 2;
        SectionCategoryRecyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), GridLayoutManager, RecyclerView.VERTICAL, false));

        return view;
    }


}