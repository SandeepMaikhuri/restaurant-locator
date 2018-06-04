package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ports;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;

public interface NetworkPort {

    interface OnRestaurantsFetched{
        void onRestaurantsFetched(ApiResponse response);
    }

    void fetchRestaurantsApiCall(double latitude, double longitude);
}