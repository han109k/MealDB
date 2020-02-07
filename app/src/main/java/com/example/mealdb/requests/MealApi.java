package com.example.mealdb.requests;

import com.example.mealdb.requests.responses.MealResponse;
import com.example.mealdb.requests.responses.MealSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi {

    /*
        Example search request
        https:// www.themealdb.com/api/json/v1/1/search.php?s=chicken
     */
    @GET("search.php")
    Call<MealSearchResponse> searchMeal(
            @Query("s") String query
    );

    /*
        Example lookup request for specific meal by id
        https:// www.themealdb.com/api/json/v1/1/lookup.php?i=52772
     */
    @GET("lookup.php")
    Call<MealResponse> getMeal (
            @Query("i") String meal_id
    );
}
