package com.sandeepmaikhuri.apps.restaurantlocator.presentation.di;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Sandeep on 30/12/16.
 */

@Component (modules = {ApplicationModule.class}) @Singleton
public interface ApplicationComponent
{
    void inject(BaseActivity baseActivity);
}
