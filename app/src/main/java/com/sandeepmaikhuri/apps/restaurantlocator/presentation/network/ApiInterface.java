package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {
   /* @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);          // @Path denotes if there has to be some dynamic data to be provided in the URL

    @GET("MobileService/GetWorkOrderList")  // the parameters contatins the path which is added to the base url
    Call<WOListResponse> getWoList(@Query("LoginId") int loginId, @Query("Timestamp") int timestamp);   // paraments contains the parameters added to get request after ? in the url


//    Call<UserProfileResponse> getUserInfo(@Header("Authorization") String authHeader, @Path(value = "user_id") String user_id);

    @POST("MobileService/UpdateTaskFromMobile/")
//    @FormUrlEncoded               // to be used when using @Field annotation for post request instead of @Body
    Call<CommonResponse> postTask(@Body Task task);             // inside call we provide the object of the data which will come in response and inside method with @body we provide the object of data to be sent
        // use @Body if custom object has to be passes, else use @Field if only a small number of fields have to be sent in post request
*/

}

