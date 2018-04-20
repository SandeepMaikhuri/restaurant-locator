package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters;

import android.location.Location;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.LocationPresenter;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Sandeep on 16/12/16.
 * Presenter class for Location related stuff
 */
public class LocationPresenterImpl implements LocationPresenter, LocationListener {
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private LocationPresenter.OnLocationDelegationTaskDone delegationTaskDone;

    public LocationPresenterImpl(GoogleMap mMap, GoogleApiClient mGoogleApiClient,
                                 LocationRequest mLocationRequest,
                                 LocationPresenter.OnLocationDelegationTaskDone delegationTaskDone) {
        this.mMap = mMap;
        this.mGoogleApiClient = mGoogleApiClient;
        this.mLocationRequest = mLocationRequest;
        this.delegationTaskDone = delegationTaskDone;
    }

    @Override
    public void locateUser() {
        mMap.setMyLocationEnabled(true);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        delegationTaskDone.onLocationFetched(location);

        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }
}
