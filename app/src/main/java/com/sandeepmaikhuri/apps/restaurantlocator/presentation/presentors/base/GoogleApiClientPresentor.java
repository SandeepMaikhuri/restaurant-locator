package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Sandeep on 6/12/16.
 */
public interface GoogleApiClientPresentor extends GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    interface View
    {
        void onGoogleApiClientConnected(Bundle bundle, GoogleApiClient mGoogleApiClient);
        void onGoogleApiClientConnectionSuspended(int i);
        void onGoogleApiClientConnectionFailed(ConnectionResult connectionResult);
    }

    void connectGoogleApiClient();
}
