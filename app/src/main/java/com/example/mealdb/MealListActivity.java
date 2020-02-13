package com.example.mealdb;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mealdb.adapters.MealRecyclerAdapter;
import com.example.mealdb.adapters.OnMealListener;
import com.example.mealdb.model.Meal;
import com.example.mealdb.requests.MealApi;
import com.example.mealdb.requests.ServiceGenerator;
import com.example.mealdb.requests.responses.MealSearchResponse;
import com.example.mealdb.viewmodels.MealListViewModel;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealListActivity extends BaseActivity implements OnMealListener {

    private static final String TAG = "MealListActivity";

    private MealListViewModel mMealListViewModel;
    private RecyclerView mRecyclerView;
    private MealRecyclerAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        Log.d(TAG, "onCreate: called");

        mRecyclerView = findViewById(R.id.meal_list);

        // init view model
        mMealListViewModel = new ViewModelProvider(this).get(MealListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        searchMealsApi("chicken");

    }

    private void subscribeObservers() {
        mMealListViewModel.getMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                for(Meal meal : meals) {
                    if(meal != null){
                        Log.d(TAG, "onChanged: " + meal.getStrMeal());
                        mAdapter.setmMeals(meals);
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new MealRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void searchMealsApi(String query) {
        mMealListViewModel.searchMealsApi(query);
    }

    private void testRetrofitRequest() {
        MealApi mealApi = ServiceGenerator.getMealApi();

        Call<MealSearchResponse> responseCall = mealApi
                .searchMeal("chicken");

        responseCall.enqueue(new Callback<MealSearchResponse>() {
            @Override
            public void onResponse(Call<MealSearchResponse> call, Response<MealSearchResponse> response) {
                Log.d(TAG, "onResponse: server response : " + response.toString());
                if(response.code() == 200) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                    List<Meal> meals = new ArrayList<>(response.body().getMeals());

                    for(Meal meal : meals) {
                        Log.d(TAG, "onResponse: " + meal.getStrMeal());

                        for(Field field : meal.getClass().getDeclaredFields()) {
                            int modifiers = field.getModifiers();
                            field.setAccessible(true);
                            if(Modifier.isProtected(modifiers)){
                                try {
                                    Log.d(TAG, "Ingredient: " + field.get(meal));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    try {
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MealSearchResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onMealClick(int position) {

    }

    @Override
    public void onCategoryClick(int position) {

    }
}
