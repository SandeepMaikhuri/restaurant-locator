package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.convertor.ResponseConvertor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FilteredVenuesPresentor;

import java.util.ArrayList;

/**
 * Created by Sandeep on 17/12/16.
 */
public class FilteredVenuesPresentorImpl implements FilteredVenuesPresentor
{
    @Override
    public Response filterVenues(Response response, ArrayList<String> categoriesList)
    {
        return ResponseConvertor.filteredResponse(response, categoriesList);
    }

    @Override
    public GetVenuesResponse filterVenues(GetVenuesResponse venuesResponse, ArrayList<String> categoriesList)
    {
        return ResponseConvertor.filteredResponse(venuesResponse, categoriesList);
    }
}
