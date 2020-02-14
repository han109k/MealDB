package com.example.mealdb.adapters;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealdb.R;
import com.example.mealdb.model.Meal;
import com.example.mealdb.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MealRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int MEAL_TYPE = 1;
    private static final int LOADING_TYPE = 2;
    private static final int CATEGORY_TYPE = 3;

    private List<Meal> mMeals;
    private OnMealListener mOnMealListener;

    public MealRecyclerAdapter(OnMealListener mOnMealListener) {
        this.mOnMealListener = mOnMealListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        switch (viewType) {
            case MEAL_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_meal_list_item, parent, false);
                return new MealViewHolder(view, mOnMealListener);
            case LOADING_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_list_item, parent, false);
                return new LoadingViewHolder(view);
            case CATEGORY_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_list_item, parent, false);
                return new CategoryViewHolder(view, mOnMealListener);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_meal_list_item, parent, false);
                return new MealViewHolder(view, mOnMealListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        if(itemViewType == MEAL_TYPE) {

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mMeals.get(position).getStrMealThumb())
                    .into(((MealViewHolder)holder).image);

            ((MealViewHolder)holder).title.setText(mMeals.get(position).getStrMeal());
            ((MealViewHolder)holder).category.setText(mMeals.get(position).getStrCategory());

        } else if(itemViewType == CATEGORY_TYPE) {

            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Uri path = Uri.parse("android.resource://com.example.mealdb/drawable/" + mMeals.get(position).getStrMealThumb());
            Glide.with(holder.itemView.getContext())
                    .setDefaultRequestOptions(requestOptions)
                    .load(path)
                    .apply(RequestOptions.circleCropTransform())
                    .into(((CategoryViewHolder)holder).categoryImage);

            ((CategoryViewHolder)holder).categoryTitle.setText(mMeals.get(position).getStrMeal());

        } else {
            //
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mMeals.get(position).getIdMeal() != null){
            if(mMeals.get(position).getIdMeal().equals("-1")) {
                return CATEGORY_TYPE;
            }
        }
        if(mMeals.get(position).getStrMeal().equals("LOADING")){
            return LOADING_TYPE;
        } else {
            return MEAL_TYPE;
        }
    }

    public void displayLoading() {
        if(!isLoading()) {
            Meal meal = new Meal();
            meal.setStrMeal("LOADING");
            //
            List<Meal> loadingList = new ArrayList<>();
            loadingList.add(meal);
            mMeals = loadingList;
            notifyDataSetChanged();
        }
    }

    private boolean isLoading() {
        if(mMeals != null) {
            if (mMeals.size() > 0) {
                if (mMeals.get(mMeals.size() - 1).getStrMeal().equals("LOADING")) {
                    return true;
                }
            }
        }
        return false;
    }

    public void displaySearchCategories() {
        List<Meal> categories = new ArrayList<>();
        for(int i = 0; i < Constants.DEFAULT_SEARCH_CATEGORIES.length; i++){
            Meal meal = new Meal();
            meal.setStrMeal(Constants.DEFAULT_SEARCH_CATEGORIES[i]);
            meal.setStrMealThumb(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]);
            meal.setIdMeal("-1");
            categories.add(meal);
        }
        mMeals = categories;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mMeals != null) {
            return mMeals.size();
        }
        return 0;
    }

    public void setMeals(List<Meal> meals){
        mMeals = meals;
        notifyDataSetChanged();
    }
}
