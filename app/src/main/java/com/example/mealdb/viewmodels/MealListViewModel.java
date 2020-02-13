package com.example.mealdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mealdb.model.Meal;
import com.example.mealdb.repositories.MealRepository;

import java.util.List;

public class MealListViewModel extends ViewModel {

    private MealRepository mMealRepository;

    public MealListViewModel() {
        mMealRepository = MealRepository.getInstance();
    }

    public LiveData<List<Meal>> getMeals() {
        return mMealRepository.getMeals();
    }

    public void searchMealsApi(String query) {
        mMealRepository.searchMealsApi(query);
    }
}
