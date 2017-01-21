package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network;

import android.util.Log;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchFoodCategoryIdPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchRestaurantsPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sandeep on 11/1/17.
 */

public class RequestFactory
{
    private static final String TAG = RequestFactory.class.getSimpleName();

    private Request apiRequest;
    private String url;
    private String webService;
    private FetchFoodCategoryIdPresentor.FoodIdCommunicator foodIdCommunicator;
    private FetchRestaurantsPresentor.RestaurantsCommunicator restaurantsCommunicator;

    public RequestFactory(String url, String webService, FetchFoodCategoryIdPresentor.FoodIdCommunicator foodIdCommunicator)
    {
        this.url = url;
        this.webService = webService;
        this.foodIdCommunicator = foodIdCommunicator;
    }

    public RequestFactory(String url, String webService, FetchRestaurantsPresentor.RestaurantsCommunicator restaurantsCommunicator)
    {
        this.url = url;
        this.webService = webService;
        this.restaurantsCommunicator = restaurantsCommunicator;
    }

    public void create()
    {
        if (AppConstants.REQUEST_API.contentEquals("http"))
        {
            apiRequest = new HttpRequest(url);
        }
        else if (AppConstants.REQUEST_API.contentEquals("volley"))
        {
            // apiRequest = new VolleyRequest(url);
        }
        else if (AppConstants.REQUEST_API.contentEquals("retrofit"))
        {
            // apiRequest = new RetrofitRequest(url);
        }
    }

    public void apiCall()
    {
        if (webService.contentEquals(AppConstants.FOOD_ID_WEB_SERVICE))
        {
            foodIdCommunicator.onTaskStarted();

            apiRequest.getFoodIdObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<String>()
                    {
                        @Override
                        public void onCompleted()
                        {

                        }

                        @Override
                        public void onError(Throwable e)
                        {
                            Log.e(TAG, e.getMessage());
                            e.printStackTrace();
                            foodIdCommunicator.onError();
                        }

                        @Override
                        public void onNext(String s)
                        {
                            foodIdCommunicator.onTaskCompleted(s);
                        }
                    });
        }
        else if (webService.contentEquals(AppConstants.VENUES_WEB_SERVICE))
        {
            apiRequest.getVenuesResponse()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<GetVenuesResponse>()
                    {
                        @Override
                        public void onCompleted()
                        {

                        }

                        @Override
                        public void onError(Throwable e)
                        {
                            Log.e("venues error", e.getMessage());
                            e.printStackTrace();
                            restaurantsCommunicator.onError();
                        }

                        @Override
                        public void onNext(GetVenuesResponse getVenuesResponse)
                        {
                            restaurantsCommunicator.onTaskCompleted(getVenuesResponse);
                        }
                    });
        }
    }
}
