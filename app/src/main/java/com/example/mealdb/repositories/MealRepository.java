package com.example.mealdb.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealdb.model.Meal;

import java.util.List;

public class MealRepository {

    private MutableLiveData<List<Meal>> mMeals;

    private static MealRepository instance;

    public static MealRepository getInstance() {
        if(instance == null) {
            instance = new MealRepository();
        }
        return instance;
    }

    private MealRepository() {
        mMeals = new MutableLiveData<>();
    }

    public LiveData<List<Meal>> getMeals() {
        return mMeals;
    }
}
