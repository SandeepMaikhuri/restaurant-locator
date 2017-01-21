package com.sandeepmaikhuri.apps.restaurantlocator.repository.impl;

import android.content.Context;

import com.sandeepmaikhuri.apps.restaurantlocator.repository.RLSharedPreferences;
import com.sandeepmaikhuri.apps.restaurantlocator.repository.AddFoodIdRepository;

/**
 * Created by Sandeep on 15/12/16.
 */
public class AddFoodIdImpl implements AddFoodIdRepository
{
    Context oContext;
    RLSharedPreferences rlSharedPreferences;

    public AddFoodIdImpl(Context oContext)
    {
        this.oContext = oContext;
        this.rlSharedPreferences = new RLSharedPreferences(oContext);
    }

    @Override
    public void addFoodId(String foodId)
    {
        rlSharedPreferences.setFoodCategoryId(foodId);
    }
}