package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl;

import android.util.Log;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.RequestFactory;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchFoodCategoryIdPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.repository.AddFoodIdRepository;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;

/**
 * Created by Sandeep on 7/12/16.
 */
public class FetchFoodCategoryIdPresentorImpl implements FetchFoodCategoryIdPresentor, FetchFoodCategoryIdPresentor.FoodIdCommunicator
{
    private double lat;
    private double lon;
    private FetchFoodCategoryIdPresentor.View view;
    private AddFoodIdRepository addFoodIdRepository;

    public FetchFoodCategoryIdPresentorImpl(double lat, double lon, FetchFoodCategoryIdPresentor.View view, AddFoodIdRepository addFoodIdRepository)
    {
        this.lat = lat;
        this.lon = lon;
        this.view = view;
        this.addFoodIdRepository = addFoodIdRepository;
    }

    @Override
    public void fetchFoodId()
    {
        StringBuilder url = new StringBuilder(AppConstants.BASE_URL);

        // https://api.foursquare.com/v2/venues/search?client_id=CLIENT_ID&client_secret=CLIENT_SECRET&v=20130815%20&ll=40.7463956,-73.9852992

        url.append("categories?=");
        url.append("client_id=" + AppConstants.CLIENT_ID);
        url.append("&client_secret=" + AppConstants.CLIENT_SECRET);
        url.append("&v=20130815%20&ll=" + lat + "," + lon);
        url.append("&q=restaurant");

        Log.e("url", url.toString());

        RequestFactory requestFactory = new RequestFactory(url.toString(), AppConstants.FOOD_ID_WEB_SERVICE, this);
        requestFactory.create();
        requestFactory.apiCall();
    }

    @Override
    public void onTaskStarted()
    {
        view.fetchingRestaurants();
    }

    @Override
    public void onTaskCompleted(String foodId)
    {
        if (!foodId.contentEquals("failed") && !foodId.contentEquals("error"))
        {
            addFoodIdRepository.addFoodId(foodId);
        }
        view.onFoodIdFetched(foodId);
    }

    @Override
    public void onError()
    {
        view.errorInFetchingFoodId();
    }
}
