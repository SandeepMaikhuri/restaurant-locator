package com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base;

/**
 * Created by Sandeep on 15/12/16.
 */
public interface FetchRestaurantsInteracter {

    void fetchRestaurants(double latitude, double longitude);
}