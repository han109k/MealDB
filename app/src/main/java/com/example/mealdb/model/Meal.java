package com.example.mealdb.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class Meal implements Parcelable {

    private String idMeal;
    private String strMeal;
    private String strCategory;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;    // thumbnail image
    private String strYoutube;
    // Ingredients
    protected String strIngredient1, strIngredient2, strIngredient3, strIngredient4,  strIngredient5,
            strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
            strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
            strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20;
    private ArrayList<Object> ingredientList;
    // Measures
    protected String srtMeasure1, srtMeasure2, srtMeasure3, srtMeasure4, srtMeasure5, srtMeasure6,
            srtMeasure7, srtMeasure8, srtMeasure9, srtMeasure10, srtMeasure11, srtMeasure12,
            srtMeasure13, srtMeasure14, srtMeasure15, srtMeasure16, srtMeasure17, srtMeasure18,
            srtMeasure19, srtMeasure20;
    private ArrayList<Object> measureList;

    public Meal() {
    }

    public Meal(String idMeal, String strMeal, String strCategory, String strArea, String strInstructions, String strMealThumb,
                String strYoutube, String strIngredient1, String strIngredient2, String strIngredient3, String strIngredient4,
                String strIngredient5, String strIngredient6, String strIngredient7, String strIngredient8, String strIngredient9,
                String strIngredient10, String strIngredient11, String strIngredient12, String strIngredient13,
                String strIngredient14, String strIngredient15, String strIngredient16, String strIngredient17,
                String strIngredient18, String strIngredient19, String strIngredient20, String srtMeasure1, String srtMeasure2,
                String srtMeasure3, String srtMeasure4, String srtMeasure5, String srtMeasure6, String srtMeasure7,
                String srtMeasure8, String srtMeasure9, String srtMeasure10, String srtMeasure11, String srtMeasure12,
                String srtMeasure13, String srtMeasure14, String srtMeasure15, String srtMeasure16, String srtMeasure17,
                String srtMeasure18, String srtMeasure19, String srtMeasure20) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
        this.strYoutube = strYoutube;
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
        this.strIngredient4 = strIngredient4;
        this.strIngredient5 = strIngredient5;
        this.strIngredient6 = strIngredient6;
        this.strIngredient7 = strIngredient7;
        this.strIngredient8 = strIngredient8;
        this.strIngredient9 = strIngredient9;
        this.strIngredient10 = strIngredient10;
        this.strIngredient11 = strIngredient11;
        this.strIngredient12 = strIngredient12;
        this.strIngredient13 = strIngredient13;
        this.strIngredient14 = strIngredient14;
        this.strIngredient15 = strIngredient15;
        this.strIngredient16 = strIngredient16;
        this.strIngredient17 = strIngredient17;
        this.strIngredient18 = strIngredient18;
        this.strIngredient19 = strIngredient19;
        this.strIngredient20 = strIngredient20;
        this.srtMeasure1 = srtMeasure1;
        this.srtMeasure2 = srtMeasure2;
        this.srtMeasure3 = srtMeasure3;
        this.srtMeasure4 = srtMeasure4;
        this.srtMeasure5 = srtMeasure5;
        this.srtMeasure6 = srtMeasure6;
        this.srtMeasure7 = srtMeasure7;
        this.srtMeasure8 = srtMeasure8;
        this.srtMeasure9 = srtMeasure9;
        this.srtMeasure10 = srtMeasure10;
        this.srtMeasure11 = srtMeasure11;
        this.srtMeasure12 = srtMeasure12;
        this.srtMeasure13 = srtMeasure13;
        this.srtMeasure14 = srtMeasure14;
        this.srtMeasure15 = srtMeasure15;
        this.srtMeasure16 = srtMeasure16;
        this.srtMeasure17 = srtMeasure17;
        this.srtMeasure18 = srtMeasure18;
        this.srtMeasure19 = srtMeasure19;
        this.srtMeasure20 = srtMeasure20;
    }

    protected Meal(Parcel in) {
        idMeal = in.readString();
        strMeal = in.readString();
        strCategory = in.readString();
        strArea = in.readString();
        strInstructions = in.readString();
        strMealThumb = in.readString();
        strYoutube = in.readString();
        strIngredient1 = in.readString();
        strIngredient2 = in.readString();
        strIngredient3 = in.readString();
        strIngredient4 = in.readString();
        strIngredient5 = in.readString();
        strIngredient6 = in.readString();
        strIngredient7 = in.readString();
        strIngredient8 = in.readString();
        strIngredient9 = in.readString();
        strIngredient10 = in.readString();
        strIngredient11 = in.readString();
        strIngredient12 = in.readString();
        strIngredient13 = in.readString();
        strIngredient14 = in.readString();
        strIngredient15 = in.readString();
        strIngredient16 = in.readString();
        strIngredient17 = in.readString();
        strIngredient18 = in.readString();
        strIngredient19 = in.readString();
        strIngredient20 = in.readString();
        srtMeasure1 = in.readString();
        srtMeasure2 = in.readString();
        srtMeasure3 = in.readString();
        srtMeasure4 = in.readString();
        srtMeasure5 = in.readString();
        srtMeasure6 = in.readString();
        srtMeasure7 = in.readString();
        srtMeasure8 = in.readString();
        srtMeasure9 = in.readString();
        srtMeasure10 = in.readString();
        srtMeasure11 = in.readString();
        srtMeasure12 = in.readString();
        srtMeasure13 = in.readString();
        srtMeasure14 = in.readString();
        srtMeasure15 = in.readString();
        srtMeasure16 = in.readString();
        srtMeasure17 = in.readString();
        srtMeasure18 = in.readString();
        srtMeasure19 = in.readString();
        srtMeasure20 = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public void setAll() {
        ingredientList = new ArrayList<>();
        measureList = new ArrayList<>();
        Class<?> mealClass = getClass();
        int i = 0;

        for(Field field : mealClass.getDeclaredFields()){
            field.setAccessible(true);
            int modifiers = field.getModifiers();
            if(Modifier.isProtected(modifiers) && (i < 20)) {
                try {
                    i++;
                    ingredientList.add(field.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else if(Modifier.isProtected(modifiers) && (i > 19)) {
                try {
                    i++;
                    measureList.add(field.get(this));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ArrayList<Object> getIngredientList() {
        return ingredientList;
    }

    public ArrayList<Object> getMeasureList() {
        return measureList;
    }

    @Override
    public String toString() {
        return "Meal {" +
                "idMeal='" + idMeal + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strIngredient1='" + strIngredient1 + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idMeal);
        dest.writeString(strMeal);
        dest.writeString(strCategory);
        dest.writeString(strArea);
        dest.writeString(strInstructions);
        dest.writeString(strMealThumb);
        dest.writeString(strYoutube);
        dest.writeString(strIngredient1);
        dest.writeString(strIngredient2);
        dest.writeString(strIngredient3);
        dest.writeString(strIngredient4);
        dest.writeString(strIngredient5);
        dest.writeString(strIngredient6);
        dest.writeString(strIngredient7);
        dest.writeString(strIngredient8);
        dest.writeString(strIngredient9);
        dest.writeString(strIngredient10);
        dest.writeString(strIngredient11);
        dest.writeString(strIngredient12);
        dest.writeString(strIngredient13);
        dest.writeString(strIngredient14);
        dest.writeString(strIngredient15);
        dest.writeString(strIngredient16);
        dest.writeString(strIngredient17);
        dest.writeString(strIngredient18);
        dest.writeString(strIngredient19);
        dest.writeString(strIngredient20);
        dest.writeString(srtMeasure1);
        dest.writeString(srtMeasure2);
        dest.writeString(srtMeasure3);
        dest.writeString(srtMeasure4);
        dest.writeString(srtMeasure5);
        dest.writeString(srtMeasure6);
        dest.writeString(srtMeasure7);
        dest.writeString(srtMeasure8);
        dest.writeString(srtMeasure9);
        dest.writeString(srtMeasure10);
        dest.writeString(srtMeasure11);
        dest.writeString(srtMeasure12);
        dest.writeString(srtMeasure13);
        dest.writeString(srtMeasure14);
        dest.writeString(srtMeasure15);
        dest.writeString(srtMeasure16);
        dest.writeString(srtMeasure17);
        dest.writeString(srtMeasure18);
        dest.writeString(srtMeasure19);
        dest.writeString(srtMeasure20);
    }
}
