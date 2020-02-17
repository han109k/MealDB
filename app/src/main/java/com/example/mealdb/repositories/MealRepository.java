package com.example.mealdb.repositories;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.mealdb.model.Meal;
import com.example.mealdb.requests.MealApiClient;

import java.util.List;

public class MealRepository {

    private static MealRepository instance;
    private MealApiClient mMealApiClient;
    private MutableLiveData<Boolean> mIsQueryExhausted = new MutableLiveData<>();
    private MediatorLiveData<List<Meal>> mMeals = new MediatorLiveData<>();

    public static MealRepository getInstance() {
        if(instance == null) {
            instance = new MealRepository();
        }
        return instance;
    }

    private MealRepository() {
        mMealApiClient = MealApiClient.getInstance();
        initMediator();
    }

    private void initMediator() {
        LiveData<List<Meal>> mealListApiSource = mMealApiClient.getMeals();
        mMeals.addSource(mealListApiSource, new Observer<List<Meal>>() {
            @Override
            public void onChanged(@Nullable List<Meal> meals) {
                if(meals != null) {
                    mMeals.setValue(meals);
                    doneQuery(meals);
                } else {
                    doneQuery(null);
                }
            }
        });
    }

    private void doneQuery(List<Meal> list) {
        if(list != null){
            if(list.size() == 0) {
                mIsQueryExhausted.setValue(true);
            }
        } else {
            mIsQueryExhausted.setValue(true);
        }
    }

    public LiveData<Boolean> isQueryExhausted() {
        return mIsQueryExhausted;
    }

    public LiveData<List<Meal>> getMeals() {
        return mMeals;
    }

    public LiveData<List<Meal>> getMeal() {
        return mMealApiClient.getMeal();
    }

    public LiveData<Boolean> isMealRequestTimedOut() {
        return mMealApiClient.isMealRequestTimesOut();
    }

    public void searchMealsApi(String query) {
        mIsQueryExhausted.setValue(false);
        mMealApiClient.searchMealsApi(query);
    }

    public void searchMealById(String query) {
        mMealApiClient.searchMealById(query);
    }

    public void cancelRequest() {
        mMealApiClient.cancelRequest();
    }
}
