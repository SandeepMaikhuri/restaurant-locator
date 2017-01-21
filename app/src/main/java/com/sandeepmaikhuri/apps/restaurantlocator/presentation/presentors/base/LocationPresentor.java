package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base;

import android.location.Location;

/**
 * Created by Sandeep on 16/12/16.
 */
public interface LocationPresentor
{
    interface View
    {
        void onLocationFetched(Location location);
    }

    void locateUser();
}
