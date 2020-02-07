package com.example.mealdb.requests.responses;

import com.example.mealdb.model.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MealResponse {

    @SerializedName("meals")
    @Expose()
    private Meal meal;

    public Meal getMeal(){
        return meal;
    }

    @Override
    public String toString() {
        return "MealResponse{" +
                "meal=" + meal +
                '}';
    }
}
