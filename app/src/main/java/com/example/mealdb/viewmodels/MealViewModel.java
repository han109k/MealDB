package com.example.mealdb.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mealdb.model.Meal;
import com.example.mealdb.repositories.MealRepository;

import java.util.List;

public class MealViewModel extends ViewModel {

    private MealRepository mMealRepository;
    private String mMealId;
    private boolean mIsMealRetrieved;

    public MealViewModel() {
        mMealRepository = MealRepository.getInstance();
        mIsMealRetrieved = false;
    }

    public LiveData<List<Meal>> getMeal() {
        return mMealRepository.getMeal();
    }

    public LiveData<Boolean> isMealRequestTimedOut() {
        return mMealRepository.isMealRequestTimedOut();
    }

    public String getMealId() {
        return mMealId;
    }

    public void searchMealById(String mealId) {
        mMealId = mealId;
        mMealRepository.searchMealById(mealId);
    }

    public void setRetrievedMeal(Boolean retrievedMeal) {
        mIsMealRetrieved = retrievedMeal;
    }

    public boolean isMealRetrieved() {
        return mIsMealRetrieved;
    }
}
