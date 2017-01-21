package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl;

import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.MainThread;
import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.RLExecutor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.MapsPresentor;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Sandeep on 6/12/16.
 */
public class MapsPresentorImpl implements MapsPresentor
{
    private RLExecutor executor;
    private MainThread mainThread;
    private MapsPresentor.View view;

    public MapsPresentorImpl(RLExecutor executor, MainThread mainThread, MapsPresentor.View view)
    {
        this.executor = executor;
        this.mainThread = mainThread;
        this.view = view;
    }

    @Override
    public void initializeMap(SupportMapFragment mapFragment)
    {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        view.onMapsInitialized(googleMap);
    }
}
