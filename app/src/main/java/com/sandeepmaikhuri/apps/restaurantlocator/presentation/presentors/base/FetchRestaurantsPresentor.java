package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;

import java.util.ArrayList;

/**
 * Created by Sandeep on 15/12/16.
 */
public interface FetchRestaurantsPresentor
{
    interface View
    {
        void onRestaurantsFetched(Response listRestaurants, ArrayList<String> strings);
        void onRestaurantsFetched(GetVenuesResponse venuesResponse, ArrayList<String> strings);
        void errorInFetchingRestaurants();
    }

    interface RestaurantsCommunicator
    {
        void onTaskCompleted(Response response);
        void onTaskCompleted(GetVenuesResponse response);
        void onError();
    }

    void fetchRestaurants();
}
