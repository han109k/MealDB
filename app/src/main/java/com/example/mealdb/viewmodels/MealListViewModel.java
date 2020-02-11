package com.example.mealdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mealdb.model.Meal;

import java.util.List;

public class MealListViewModel extends ViewModel {

    private MutableLiveData<List<Meal>> mMeals = new MutableLiveData<>();

    public MealListViewModel() {
    }

    public LiveData<List<Meal>> getMeals() {
        return mMeals;
    }
}
