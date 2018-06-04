package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;

public interface FetchRestaurantsPresenter extends BasePresenter {

    interface OnDelegationTaskDone {
        void onRestaurantsFetched(ApiResponse apiResponse);
    }

    void fetchRestaurants(double latitude, double longitude);
}
