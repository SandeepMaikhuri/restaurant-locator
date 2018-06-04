package com.sandeepmaikhuri.apps.restaurantlocator.network;

import android.util.ArrayMap;
import android.util.Log;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.ports.NetworkPort;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkAdapter implements NetworkPort {

    private OnRestaurantsFetched onRestaurantsFetched;

    public NetworkAdapter(NetworkPort.OnRestaurantsFetched onRestaurantsFetched){
        this.onRestaurantsFetched = onRestaurantsFetched;
    }

    @Override
    public void fetchRestaurantsApiCall(double latitude, double longitude) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.foursquare.com/v2/venues/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Map<String, String> data = new ArrayMap<>();
        data.put("query", "restaurant");
        data.put("client_id", AppConstants.CLIENT_ID);
        data.put("client_secret", AppConstants.CLIENT_SECRET);
        data.put("v", "20130815");
        data.put("ll", latitude + "," + longitude);

        Call<ApiResponse> call = service.fetchCatagoryId(data);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("response : ", response.message());
                onRestaurantsFetched.onRestaurantsFetched(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("response : ", t.getLocalizedMessage());
            }
        });
    }
}
