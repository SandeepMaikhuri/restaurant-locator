package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.async;

import android.os.AsyncTask;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetCategoryResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.networkcall.DownloadUrl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.parser.CategoriesParser;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchFoodCategoryIdPresentor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GetFoodCategoryIdAsycTask extends AsyncTask<Object, String, String>
{
    private String response;
    private String url;
    private FetchFoodCategoryIdPresentor.FoodIdCommunicator foodIdCommunicator;

    public GetFoodCategoryIdAsycTask(FetchFoodCategoryIdPresentor.FoodIdCommunicator foodIdCommunicator)
    {
        this.foodIdCommunicator = foodIdCommunicator;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

        foodIdCommunicator.onTaskStarted();
    }

    @Override
    protected String doInBackground(Object... params)
    {
        try
        {
            url = (String) params[0];
            DownloadUrl downloadUrl = new DownloadUrl();
            response = downloadUrl.readUrl(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            response = "error";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();

        GetCategoryResponse categoryResponse = gson.fromJson(response, GetCategoryResponse.class);

        String foodId = CategoriesParser.getFoodCategoryId(categoryResponse);
//         String getFoodIdObservable = CategoriesParser.getFoodCategoryId(response);

        if(response.contentEquals("error") || foodId.contentEquals("failed"))
        {
            foodIdCommunicator.onError();
        }
        else
        {
            foodIdCommunicator.onTaskCompleted(foodId);
        }
    }
}
