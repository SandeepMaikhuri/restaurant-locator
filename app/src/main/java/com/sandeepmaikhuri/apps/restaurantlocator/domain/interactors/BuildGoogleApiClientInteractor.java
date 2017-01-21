package com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors;

/**
 * Created by Sandeep on 6/12/16.
 */
public interface BuildGoogleApiClientInteractor
{
    interface Callback
    {
        void onGoogleApiClientConnected();
    }
}
