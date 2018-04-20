package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base;

import android.location.Location;

/**
 * Created by Sandeep on 16/12/16.
 */
public interface LocationPresenter {
    interface OnLocationDelegationTaskDone {
        void onLocationFetched(Location location);
    }

    void locateUser();
}
