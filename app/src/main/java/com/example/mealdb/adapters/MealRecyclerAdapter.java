package com.example.mealdb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealdb.R;
import com.example.mealdb.model.Meal;

import java.util.List;

public class MealRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Meal> mMeals;
    private OnMealListener mOnMealListener;

    public MealRecyclerAdapter(OnMealListener mOnMealListener) {
        this.mOnMealListener = mOnMealListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_meal_list_item, parent, false);
        return new MealViewHolder(view, mOnMealListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(holder.itemView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(mMeals.get(position).getStrMealThumb())
                .into(((MealViewHolder)holder).image);

        ((MealViewHolder)holder).title.setText(mMeals.get(position).getStrMeal());
        ((MealViewHolder)holder).category.setText(mMeals.get(position).getStrCategory());

    }

    @Override
    public int getItemCount() {
        if(mMeals != null) {
            return mMeals.size();
        }
        return 0;
    }

    public void setmMeals(List<Meal> meals){
        mMeals = meals;
        notifyDataSetChanged();
    }
}
