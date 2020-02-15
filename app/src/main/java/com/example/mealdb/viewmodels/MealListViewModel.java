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
    private boolean mIsPerformingQuery;

    public MealListViewModel() {
        mMealRepository = MealRepository.getInstance();
        mIsPerformingQuery = false;
    }

    public LiveData<List<Meal>> getMeals() {
        return mMealRepository.getMeals();
    }

    public void searchMealsApi(String query) {
        mIsViewingMeals = true;
        mIsPerformingQuery = true;
        mMealRepository.searchMealsApi(query);
    }

    public boolean isViewingMeals() {
        return mIsViewingMeals;
    }

    public void setIsViewingMeals(boolean isViewingMeals){
        mIsViewingMeals = isViewingMeals;
    }

    public boolean isPerformingQuery() {
        return mIsPerformingQuery;
    }

    public void setIsPerformingQuery(boolean isPerformingQuery) {
        mIsPerformingQuery = isPerformingQuery;
    }

    public boolean onBackPressed() {
        if(mIsPerformingQuery) {
            // cancel
            mMealRepository.cancelRequest();
        }
        if(mIsViewingMeals) {
            mIsViewingMeals = false;
            return false;
        }
        return true;
    }
}
