package com.sandeepmaikhuri.apps.restaurantlocator.network;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;


/**
 * Created by sandeep on 22/3/18.
 */

interface ApiService {

    @GET("search")
    Call<ApiResponse> fetchCatagoryId(@QueryMap Map<String, String> data);
}
