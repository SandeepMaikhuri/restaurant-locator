package com.sandeepmaikhuri.apps.restaurantlocator.presentation.di;

import android.content.Context;
import android.content.res.Resources;

import com.sandeepmaikhuri.apps.restaurantlocator.AndroidApplication;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.CustomTypeface;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.LayoutCustomization;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Sandeep on 30/12/16.
 */

@Module
public class ApplicationModule
{
    AndroidApplication application;

    public ApplicationModule(AndroidApplication application)
    {
        this.application = application;
    }

    @Provides @Singleton
    public Context provideApplicationContext()
    {
        return this.application;
    }

    @Provides @Singleton
    public Resources provideResources()
    {
        return application.getResources();
    }

    @Provides @Singleton
    public LayoutCustomization provideLayoutCustomization()
    {
        return new LayoutCustomization(application.getResources().getDisplayMetrics());
    }

    @Provides @Singleton
    public CustomTypeface provideCustomTypeFace()
    {
        return new CustomTypeface(application);
    }
}