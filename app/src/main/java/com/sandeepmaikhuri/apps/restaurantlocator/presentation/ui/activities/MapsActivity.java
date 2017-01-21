package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sandeepmaikhuri.apps.restarauntlocator.R;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchRestaurantsPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl.FetchRestaurantsPresentorImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapsActivity extends AbstractMapsActivity
{
    @Bind(R.id.ic_filter) ImageView iconFilter;

    private String foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }

    @Override
    public void fetchingRestaurants()
    {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setTitle(getResources().getString(R.string.info_loading));
        dialog.setCancelable(false);
        dialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        dialog.show();
    }

    @Override
    public void onFoodIdFetched(String idFood)
    {
        this.foodId = idFood;
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                Log.e("getFoodIdObservable", foodId);

                FetchRestaurantsPresentor fetchRestaurantsPresentor = new FetchRestaurantsPresentorImpl(foodId, latitude, longitude, MapsActivity.this);
                fetchRestaurantsPresentor.fetchRestaurants();
            }
        });
    }

    @Override
    public void errorInFetchingFoodId()
    {
        Toast.makeText(context, getResources().getString(R.string.err_retry), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRestaurantsFetched(Response listRestaurants, ArrayList<String> categoriesList)
    {
        setVenueMarkers(listRestaurants);

        dialog.dismiss();

        setClickListeners(categoriesList);
    }

    @Override
    public void onRestaurantsFetched(GetVenuesResponse venuesResponse, ArrayList<String> categoriesList)
    {
        this.venuesResponse = venuesResponse;

        setVenueMarkers(venuesResponse);

        if (dialog != null && dialog.isShowing())
            dialog.dismiss();

        setClickListeners(categoriesList);
    }

    @Override
    public void errorInFetchingRestaurants()
    {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();

        Toast.makeText(context, getResources().getString(R.string.err_retry), Toast.LENGTH_LONG).show();
    }

    private void setClickListeners(final ArrayList<String> categoriesList)
    {
        findViewById(R.id.ic_filter).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), FilterPreferencesActivity.class);

                intent.putStringArrayListExtra(AppConstants.CATEGORIES_LIST, categoriesList);
                startActivityForResult(intent, 200);
            }
        });
    }
}
