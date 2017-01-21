package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;

import java.util.ArrayList;

/**
 * Created by Sandeep on 17/12/16.
 */
public interface FilteredVenuesPresentor
{
    Response filterVenues(Response response, ArrayList<String> categoriesList);
    GetVenuesResponse filterVenues(GetVenuesResponse venuesResponse, ArrayList<String> categoriesList);
}
