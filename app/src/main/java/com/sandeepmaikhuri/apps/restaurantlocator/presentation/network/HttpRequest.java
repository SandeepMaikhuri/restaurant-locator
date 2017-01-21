package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network;

import android.util.Log;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetCategoryResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.parser.CategoriesParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Sandeep on 11/1/17.
 */

public class HttpRequest implements Request
{
    String stringUrl;

    public HttpRequest(String url)
    {
        this.stringUrl = url;
    }

    public String getServiceResponse()
    {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;

        try
        {
            URL url = new URL(stringUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null)
            {
                sb.append(line);
            }

            data = sb.toString();
            Log.e("downloadUrl", data.toString());
            br.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                iStream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public Observable<String> getFoodIdObservable()
    {
        return Observable.defer(new Func0<Observable<String>>()
        {
            @Override
            public Observable<String> call()
            {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                Gson gson = gsonBuilder.create();

                GetCategoryResponse categoryResponse = gson.fromJson(getServiceResponse(), GetCategoryResponse.class);

                String foodId = CategoriesParser.getFoodCategoryId(categoryResponse);

                return Observable.just(foodId);
            }
        });
    }

    @Override
    public Observable<GetVenuesResponse> getVenuesResponse()
    {
        return Observable.defer(new Func0<Observable<GetVenuesResponse>>()
        {
            @Override
            public Observable<GetVenuesResponse> call()
            {
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                Gson gson = gsonBuilder.create();

                GetVenuesResponse venuesResponse = gson.fromJson(getServiceResponse(), GetVenuesResponse.class);

                return Observable.just(venuesResponse);
            }
        });
    }
}
