package com.example.mealdb.requests;

import com.example.mealdb.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder =
            new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static MealApi mealApi = retrofit.create(MealApi.class);

    public static MealApi getMealApi() {
        return mealApi;
    }

}
