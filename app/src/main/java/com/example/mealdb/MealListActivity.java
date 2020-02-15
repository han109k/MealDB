package com.example.mealdb;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mealdb.adapters.MealRecyclerAdapter;
import com.example.mealdb.adapters.OnMealListener;
import com.example.mealdb.model.Meal;
import com.example.mealdb.requests.MealApi;
import com.example.mealdb.requests.ServiceGenerator;
import com.example.mealdb.requests.responses.MealSearchResponse;
import com.example.mealdb.utils.Constants;
import com.example.mealdb.utils.VerticalSpacingItemDecorator;
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
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private MealRecyclerAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.category){
            displaySearchCategories();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        Log.d(TAG, "onCreate: called");

        mRecyclerView = findViewById(R.id.meal_list);
        mSearchView = findViewById(R.id.search_view);

        // init view model
        mMealListViewModel = new ViewModelProvider(this).get(MealListViewModel.class);

        initRecyclerView();
        subscribeObservers();
        initSearchView();
        if(!mMealListViewModel.isViewingMeals()) {
            displaySearchCategories();
        }
        setSupportActionBar(findViewById(R.id.toolbar));
    }

    private void subscribeObservers() {
        mMealListViewModel.getMeals().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(meals != null) {
                    mMealListViewModel.setIsPerformingQuery(false);
                }
                mAdapter.setMeals(meals);
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new MealRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(20);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initSearchView() {
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mAdapter.displayLoading();
                mMealListViewModel.searchMealsApi(query);
                mSearchView.clearFocus();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onMealClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {
        mAdapter.displayLoading();
        mMealListViewModel.searchMealsApi(category);
        mSearchView.clearFocus();
    }

    private void displaySearchCategories() {
        mMealListViewModel.setIsViewingMeals(false);
        mAdapter.displaySearchCategories();
    }

    @Override
    public void onBackPressed() {
        if(mMealListViewModel.onBackPressed()) {
            Log.d(TAG, "onBackPressed: pressed");
            super.onBackPressed();
        } else {
            Log.d(TAG, "onBackPressed: pressed");
            displaySearchCategories();
        }
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
}
