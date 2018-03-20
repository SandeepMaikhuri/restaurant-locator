package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.di.ApplicationComponent;
import com.sandeepmaikhuri.apps.restaurantlocator.AndroidApplication;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.CustomTypeface;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.LayoutCustomization;

import javax.inject.Inject;

/**
 * Created by Sandeep on 4/1/17.
 */

public class BaseActivity extends AppCompatActivity
{
    @Inject LayoutCustomization oLayoutCustomization;
    @Inject Context context;
    @Inject CustomTypeface oCustomTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initializeInjectors();
    }

    protected void initializeInjectors()
    {
        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent()
    {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }
}
