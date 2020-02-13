package com.example.mealdb.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealdb.R;

public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, category;
    AppCompatImageView image;
    OnMealListener onMealListener;

    public MealViewHolder(@NonNull View itemView, OnMealListener onMealListener) {
        super(itemView);
        title = itemView.findViewById(R.id.meal_title);
        category = itemView.findViewById(R.id.meal_category);
        image = itemView.findViewById(R.id.meal_image);
        this.onMealListener = onMealListener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onMealListener.onMealClick(getAdapterPosition());
    }
}
