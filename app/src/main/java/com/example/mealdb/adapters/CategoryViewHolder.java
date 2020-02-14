package com.example.mealdb.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealdb.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    OnMealListener listener;
    ImageView categoryImage;
    TextView categoryTitle;

    public CategoryViewHolder(@NonNull View itemView, OnMealListener onMealListener) {
        super(itemView);

        listener = onMealListener;
        categoryImage = itemView.findViewById(R.id.category_image);
        categoryTitle = itemView.findViewById(R.id.category_title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onCategoryClick(categoryTitle.getText().toString());
    }
}
