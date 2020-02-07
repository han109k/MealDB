package com.example.mealdb.requests.responses;

import com.example.mealdb.model.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealSearchResponse {

    @SerializedName("meals")
    @Expose()
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "MealSearchResponse{" +
                "meals=" + meals +
                '}';
    }
}
