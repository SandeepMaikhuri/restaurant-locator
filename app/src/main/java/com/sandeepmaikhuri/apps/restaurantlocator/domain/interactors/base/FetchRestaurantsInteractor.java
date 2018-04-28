package com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors;

/**
 * Created by Sandeep on 15/12/16.
 */
public interface FetchRestaurantsInteractor
{
    interface CallBack
    {
        void onRestaurantsFetched();
        void onError();
    }

    void fetchRestaurants();
}
