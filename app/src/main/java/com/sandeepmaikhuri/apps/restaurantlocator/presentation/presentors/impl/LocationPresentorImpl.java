package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl;

import android.location.Location;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.LocationPresentor;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Sandeep on 16/12/16.
 */
public class LocationPresentorImpl implements LocationPresentor, LocationListener
{
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LocationPresentor.View view;

    public LocationPresentorImpl(GoogleMap mMap, GoogleApiClient mGoogleApiClient, LocationRequest mLocationRequest, LocationPresentor.View view)
    {
        this.mMap = mMap;
        this.mGoogleApiClient = mGoogleApiClient;
        this.mLocationRequest = mLocationRequest;
        this.view = view;
    }

    @Override
    public void locateUser()
    {
        mMap.setMyLocationEnabled(true);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location)
    {
        view.onLocationFetched(location);

        if (mGoogleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }
}
