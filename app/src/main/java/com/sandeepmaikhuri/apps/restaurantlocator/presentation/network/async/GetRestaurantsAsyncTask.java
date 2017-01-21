package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.async;

import android.os.AsyncTask;
import android.util.Log;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.networkcall.DownloadUrl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchRestaurantsPresentor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Sandeep on 15/12/16.
 */
public class GetRestaurantsAsyncTask extends AsyncTask<Object,Void,String >
{
    FetchRestaurantsPresentor.RestaurantsCommunicator callBack;

    public GetRestaurantsAsyncTask(FetchRestaurantsPresentor.RestaurantsCommunicator callBack)
    {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(Object[] params)
    {
        String apiResponse = "";

        try
        {
            String url = (String) params[0];
            DownloadUrl downloadUrl = new DownloadUrl();
            apiResponse = downloadUrl.readUrl(url);

            Log.e("Restaurants : ", apiResponse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            apiResponse = "error";
        }
        return apiResponse;
    }

    @Override
    protected void onPostExecute(String response)
    {
        super.onPostExecute(response);

        if (response.contentEquals("error"))
        {
            callBack.onError();
        }
        else
        {

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("M/d/yy hh:mm a");
            Gson gson = gsonBuilder.create();

            GetVenuesResponse venuesResponse = gson.fromJson(response, GetVenuesResponse.class);

//            com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response response1 = VenuesParser.parseGetRestaurantsResponse(response);

            if (venuesResponse == null || venuesResponse.getResponse() == null || venuesResponse.getResponse().venues == null)
            {
                callBack.onError();
            }
            else
            {
                callBack.onTaskCompleted(venuesResponse);
//                callBack.onTaskCompleted(response1);
            }
        }
    }
}
