package com.example.mealdb.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealdb.model.Meal;
import com.example.mealdb.requests.MealApiClient;

import java.util.List;

public class MealRepository {

    private static MealRepository instance;
    private MealApiClient mMealApiClient;


    public static MealRepository getInstance() {
        if(instance == null) {
            instance = new MealRepository();
        }
        return instance;
    }

    private MealRepository() {
        mMealApiClient = MealApiClient.getInstance();
    }

    public LiveData<List<Meal>> getMeals() {
        return mMealApiClient.getMeals();
    }

    public void searchMealsApi(String query) {
        mMealApiClient.searchMealsApi(query);
    }
}
