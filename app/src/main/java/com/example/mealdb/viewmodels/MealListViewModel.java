package com.example.mealdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mealdb.model.Meal;
import com.example.mealdb.repositories.MealRepository;

import java.util.List;

public class MealListViewModel extends ViewModel {

    private MealRepository mMealRepository;
    private boolean mIsViewingMeals;

    public MealListViewModel() {
        mMealRepository = MealRepository.getInstance();
        mIsViewingMeals = false;
    }

    public LiveData<List<Meal>> getMeals() {
        return mMealRepository.getMeals();
    }

    public void searchMealsApi(String query) {
        mIsViewingMeals = true;
        mMealRepository.searchMealsApi(query);
    }

    public boolean isViewingMeals() {
        return mIsViewingMeals;
    }

    public void setIsViewingMeals(boolean isViewingMeals){
        mIsViewingMeals = isViewingMeals;
    }
}
