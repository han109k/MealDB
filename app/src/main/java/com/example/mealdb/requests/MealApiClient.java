package com.example.mealdb.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mealdb.AppExecutors;
import com.example.mealdb.model.Meal;
import com.example.mealdb.requests.responses.MealResponse;
import com.example.mealdb.requests.responses.MealSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.mealdb.utils.Constants.NETWORK_TIMEOUT;

public class MealApiClient {

    private static final String TAG = "MealApiClient";

    private static MealApiClient instance;
    private MutableLiveData<List<Meal>> mMeals;
    private MutableLiveData<List<Meal>> mMeal;
    private RetrieveMealsRunnable mRetrieveMealsRunnable;
    private RetrieveMealRunnable mRetrieveMealRunnable;
    private MutableLiveData<Boolean> mMealRequestTimeout = new MutableLiveData<>();

    public static MealApiClient getInstance() {
        if (instance == null) {
            return new MealApiClient();
        }
        return instance;
    }

    private MealApiClient() {
        mMeal = new MutableLiveData<>();
        mMeals = new MutableLiveData<>();
    }

    public LiveData<List<Meal>> getMeals() {
        return mMeals;
    }

    public LiveData<List<Meal>> getMeal() {
        return mMeal;
    }

    public LiveData<Boolean> isMealRequestTimesOut() {
        return mMealRequestTimeout;
    }

    public void searchMealsApi(String query) {
        if(mRetrieveMealsRunnable != null){
            mRetrieveMealsRunnable = null;
        }

        mRetrieveMealsRunnable = new RetrieveMealsRunnable(query);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveMealsRunnable);

        mMealRequestTimeout.postValue(false);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                mMealRequestTimeout.postValue(true);
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveMealsRunnable implements Runnable {

        private String query;
        private boolean cancelRequest;

        public RetrieveMealsRunnable(String query) {
            this.query = query;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMeals(query).execute();

                if(cancelRequest)
                    return;

                if(response.code() == 200) {    // html status code (200)
                    List<Meal> list = new ArrayList<>(((MealSearchResponse)response.body()).getMeals());
                    mMeals.postValue(list);
                } else {
                    String error = response.errorBody().toString();
                    Log.e(TAG, "run: " + error );
                    mMeals.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMeals.postValue(null);
            }
        }

        // make call on search query through REST
        private Call<MealSearchResponse> getMeals(String query) {
            return ServiceGenerator.getMealApi().searchMeal(
                    query
            );
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: search request cancelled");
            cancelRequest = true;
        }
    }

    public void searchMealById(String mealId) {
        if(mRetrieveMealRunnable != null) {
            mRetrieveMealRunnable = null;
        }

        mRetrieveMealRunnable = new RetrieveMealRunnable(mealId);
        final Future handler = AppExecutors.getInstance().networkIO().submit(mRetrieveMealRunnable);

        mMealRequestTimeout.setValue(false);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                mMealRequestTimeout.postValue(true);
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveMealRunnable implements Runnable {

        private String mealId;
        private boolean cancelRequest;

        public RetrieveMealRunnable(String mealId) {
            this.mealId = mealId;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getMeal(mealId).execute();

                if(cancelRequest)
                    return;

                if(response.code() == 200) {
                    List<Meal> meal = new ArrayList<>(((MealResponse)response.body()).getMeal());
                    mMeal.postValue(meal);
                } else {
                    String error = response.errorBody().string();
                    Log.e(TAG, "run: " + error);
                    mMeal.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mMeal.postValue(null);
            }
        }

        private Call<MealResponse> getMeal(String mealId) {
            return ServiceGenerator.getMealApi().getMeal(
                    mealId
            );
        }

        private void cancelRequest() {
            Log.d(TAG, "cancelRequest: look up request cancelled");
            cancelRequest = true;
        }
    }

    public void cancelRequest() {
        if(mRetrieveMealsRunnable != null) {
            mRetrieveMealsRunnable.cancelRequest();
        }
        if(mRetrieveMealRunnable != null) {
            mRetrieveMealRunnable.cancelRequest();
        }
    }
}
