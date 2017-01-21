package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.convertor.ResponseConvertor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.RequestFactory;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.async.GetRestaurantsAsyncTask;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchRestaurantsPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.AndroidApplication;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;

import org.json.JSONObject;

/**
 * Created by Sandeep on 15/12/16.
 */
public class FetchRestaurantsPresentorImpl implements FetchRestaurantsPresentor, FetchRestaurantsPresentor.RestaurantsCommunicator
{
    private String foodId;
    private double latitude;
    private double longitude;
    private FetchRestaurantsPresentor.View view;

    public FetchRestaurantsPresentorImpl(String foodId, double latitude, double longitude, FetchRestaurantsPresentor.View view)
    {
        this.foodId = foodId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.view = view;
    }

    @Override
    public void fetchRestaurants()
    {
        String url = AppConstants.BASE_URL + "search?" + "categoryId=" + foodId + "&client_id=" + AppConstants.CLIENT_ID +
                "&client_secret=" + AppConstants.CLIENT_SECRET + "&v=20130815%20&ll=" + latitude + "," + longitude;

//        makeVolleyRequest(url);

        RequestFactory requestFactory = new RequestFactory(url, AppConstants.VENUES_WEB_SERVICE, this);
        requestFactory.create();
        requestFactory.apiCall();

//        GetRestaurantsAsyncTask getRestaurantsAsyncTask = new GetRestaurantsAsyncTask(this);
//        getRestaurantsAsyncTask.execute(url);
    }

    private void makeVolleyRequest(String url)
    {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new com.android.volley.Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d("Response", response.toString());
                    }
                },
                new com.android.volley.Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d("Error", "Error: " + error.getMessage());
                    }
                });

        // Adding request to request queue
        AndroidApplication.getApplicationInstance().addToRequestQueue(jsonObjReq, "Request");

    }

    @Override
    public void onTaskCompleted(Response response)
    {
//        view.onRestaurantsFetched(response, ResponseConvertor.venuesToCategoriesList(response));
    }

    @Override
    public void onTaskCompleted(GetVenuesResponse venuesResponse)
    {
        view.onRestaurantsFetched(venuesResponse, ResponseConvertor.venuesToCategoriesList(venuesResponse));
    }

    @Override
    public void onError()
    {
        view.errorInFetchingRestaurants();
    }
}