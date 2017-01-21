package com.sandeepmaikhuri.apps.restaurantlocator.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;


public class RLSharedPreferences
{
    private SharedPreferences sharedPreferences;
    private Editor editor;

    public RLSharedPreferences(Context context)
    {
        this.sharedPreferences = context.getSharedPreferences(AppConstants.RL_SHARED_PREFS, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public String getFoodCategoryId()
    {
        return sharedPreferences.getString("food_category_id", "");
    }

    public void setFoodCategoryId(String foodCategoryId)
    {
        editor.putString("food_category_id", foodCategoryId);
        editor.commit();
    }

    public void setLat(double lat)
    {
        editor.putString("lat", Double.toString(lat));
        editor.commit();
    }

    public String getLat()
    {
        return sharedPreferences.getString("lat", "");
    }

    public void setLong(double lon)
    {
        editor.putString("lon", Double.toString(lon));
        editor.commit();
    }

    public String getLong()
    {
        return sharedPreferences.getString("lon", "");
    }
}
