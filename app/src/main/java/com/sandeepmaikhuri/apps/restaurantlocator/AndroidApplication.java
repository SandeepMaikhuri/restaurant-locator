package com.sandeepmaikhuri.apps.restaurantlocator;

import android.app.Application;
import android.text.TextUtils;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.di.ApplicationComponent;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.di.ApplicationModule;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.di.DaggerApplicationComponent;

/**
 * Created by Sandeep on 29/12/16.
 */
public class AndroidApplication extends Application
{
    private static final String TAG = AndroidApplication.class.getSimpleName();
    private static AndroidApplication applicationInstance;

    ApplicationComponent applicationComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        applicationInstance = this;

        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

    }

    public ApplicationComponent getApplicationComponent()
    {
        return applicationComponent;
    }

    public static synchronized AndroidApplication getApplicationInstance()
    {
        return applicationInstance;
    }
}
