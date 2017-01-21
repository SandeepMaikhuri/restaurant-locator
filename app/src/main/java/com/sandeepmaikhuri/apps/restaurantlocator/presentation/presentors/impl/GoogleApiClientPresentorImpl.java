package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl;

import android.content.Context;
import android.os.Bundle;

import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.MainThread;
import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.RLExecutor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.GoogleApiClientPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.NetworkStatus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by Sandeep on 6/12/16.
 */
public class GoogleApiClientPresentorImpl implements GoogleApiClientPresentor
{
    private RLExecutor executor;
    private MainThread mainThread;
    private Context oContext;
    private GoogleApiClient mGoogleApiClient;
    private GoogleApiClientPresentor.View view;
    private NetworkStatus networkStatus;

    public GoogleApiClientPresentorImpl(RLExecutor executor, MainThread mainThread, Context oContext,
                                        GoogleApiClient mGoogleApiClient, GoogleApiClientPresentor.View view, NetworkStatus networkStatus)
    {
        this.executor = executor;
        this.mainThread = mainThread;
        this.oContext = oContext;
        this.mGoogleApiClient = mGoogleApiClient;
        this.view = view;
        this.networkStatus = networkStatus;
    }

    @Override
    public void connectGoogleApiClient()
    {
        if (networkStatus.isInternetOn())
        {
            mGoogleApiClient = new GoogleApiClient.Builder(oContext)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            mGoogleApiClient.connect();
        }
        else
        {
            view.onGoogleApiClientConnectionSuspended(100);
        }
    }

    @Override
    public void onConnected(Bundle bundle)
    {
        view.onGoogleApiClientConnected(bundle, mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        view.onGoogleApiClientConnectionSuspended(i);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult)
    {
        view.onGoogleApiClientConnectionFailed(connectionResult);
    }
}
