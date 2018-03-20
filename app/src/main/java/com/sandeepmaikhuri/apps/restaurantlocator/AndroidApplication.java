package com.sandeepmaikhuri.apps.restaurantlocator;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
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
    private RequestQueue mRequestQueue;

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

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req)
    {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag)
    {
        if (mRequestQueue != null)
        {
            mRequestQueue.cancelAll(tag);
        }
    }
}
