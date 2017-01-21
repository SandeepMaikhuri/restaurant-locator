package com.sandeepmaikhuri.apps.restaurantlocator.repository.impl;

import android.content.Context;

import com.sandeepmaikhuri.apps.restaurantlocator.repository.GetFoodIdRepository;
import com.sandeepmaikhuri.apps.restaurantlocator.repository.RLSharedPreferences;

/**
 * Created by Sandeep on 15/12/16.
 */
public class GetFoodIdImpl implements GetFoodIdRepository
{
    Context oContext;
    RLSharedPreferences rlSharedPreferences;

    public GetFoodIdImpl(Context oContext)
    {
        this.oContext = oContext;
        rlSharedPreferences = new RLSharedPreferences(oContext);
    }

    @Override
    public String getFoodId()
    {
        return rlSharedPreferences.getFoodCategoryId();
    }
}
