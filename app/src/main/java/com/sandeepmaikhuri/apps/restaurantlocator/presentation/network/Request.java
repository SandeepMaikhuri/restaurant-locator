package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;

import rx.Observable;

/**
 * Created by Sandeep on 11/1/17.
 */

public interface Request
{
    Observable<String> getFoodIdObservable();

    Observable<GetVenuesResponse> getVenuesResponse();
}
