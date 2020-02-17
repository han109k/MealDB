package com.example.mealdb;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mealdb.model.Meal;
import com.example.mealdb.viewmodels.MealViewModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MealActivity extends BaseActivity {

    private static final String TAG = "MealActivity";

    private AppCompatImageView mMealImage;
    private TextView mMealTitle;
    private Button mYoutube;
    private LinearLayout mMealIngredients, mMealInstructions;
    private ScrollView mScrollView;

    private MealViewModel mMealViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        mMealImage = findViewById(R.id.meal_image);
        mMealTitle = findViewById(R.id.meal_title);
        mYoutube = findViewById(R.id.youtube);
        mMealIngredients = findViewById(R.id.ingredients_container);
        mMealInstructions = findViewById(R.id.instructions_container);
        mScrollView = findViewById(R.id.parent);

        mMealViewModel = new ViewModelProvider(this).get(MealViewModel.class);

        showProgressBar(true);
        subscribeObservers();
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("meal")) {
            Meal meal = getIntent().getParcelableExtra("meal");
            Log.d(TAG, "getIncomingIntent: " + meal.getStrMeal());
            mMealViewModel.searchMealById(meal.getIdMeal());
            Log.d(TAG, "getIncomingIntent: " + meal.getIdMeal());
        }
    }

    private void subscribeObservers() {
        mMealViewModel.getMeal().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meals) {
                if(meals != null) {
                    if(meals.get(0).getIdMeal().equals(mMealViewModel.getMealId())){
                        setMealProperties(meals.get(0));
                        mMealViewModel.setRetrievedMeal(true);
                    }
                } else {
                    Log.d(TAG, "onChanged: meal = null");
                }
            }
        });

        mMealViewModel.isMealRequestTimedOut().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean && !mMealViewModel.isMealRetrieved()) {
                    Log.d(TAG, "onChanged: timed out...");
                }
            }
        });
    }

    private void setMealProperties(Meal meal) {
        if(meal != null) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(meal.getStrMealThumb())
                    .into(mMealImage);

            // Instructions
            mMealTitle.setText(meal.getStrMeal());
            mMealInstructions.removeAllViews();
            TextView instructions = new TextView(this);
            instructions.setText(meal.getStrInstructions());
            instructions.setTextSize(15f);
            instructions.setTextColor(Color.parseColor("#fafafa"));
            instructions.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            mMealInstructions.addView(instructions);

            // Ingredients
            mMealIngredients.removeAllViews();
            List<String> ingredients = new ArrayList<>();
            List<String> measures = new ArrayList<>();

            int i = 0;
            for(Field field : meal.getClass().getDeclaredFields()) {
                int modifiers = field.getModifiers();
                field.setAccessible(true);
                if (Modifier.isProtected(modifiers) && i < 20) {
                    try {
                        i++;
                        if(field.get(meal) != null && !field.get(meal).equals("")){
                            String ff = (String) field.get(meal);
                            ingredients.add(ff);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (Modifier.isProtected(modifiers) && i > 19) {
                    try {
                        if(field.get(meal) != null && !field.get(meal).equals("")){
                            String ff = (String) field.get(meal);
                            measures.add(ff);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


            for(int j = 0; j < ingredients.size(); j++) {
                TextView ingredient = new TextView(this);
                ingredient.setText(String.format(measures.get(j) + " " + ingredients.get(j)));
                ingredient.setTextSize(15f);
                ingredient.setTextColor(Color.parseColor("#fafafa"));
                ingredient.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                mMealIngredients.addView(ingredient);
            }
        }

        showParent();
        showProgressBar(false);
    }

    private void showParent(){
        mScrollView.setVisibility(View.VISIBLE);
    }
}
