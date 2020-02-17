package com.example.mealdb.requests.responses;

import com.example.mealdb.model.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {

    @SerializedName("meals")
    @Expose()
    private List<Meal> meal;

    public List<Meal> getMeal(){
        return meal;
    }

    @Override
    public String toString() {
        return "MealResponse{" +
                "meal=" + meal +
                '}';
    }
}
