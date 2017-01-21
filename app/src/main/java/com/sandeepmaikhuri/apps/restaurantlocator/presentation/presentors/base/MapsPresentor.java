package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Sandeep on 6/12/16.
 */
public interface MapsPresentor extends OnMapReadyCallback
{
    interface View extends Presenter
    {
        void onMapsInitialized(GoogleMap googleMap);
    }

    void initializeMap(SupportMapFragment mapFragment);
}
