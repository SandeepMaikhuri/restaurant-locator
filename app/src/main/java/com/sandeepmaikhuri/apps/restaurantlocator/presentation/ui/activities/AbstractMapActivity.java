package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.sandeepmaikhuri.apps.restarauntlocator.R;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.MapsActivityPresenterImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.MapsActivityPresenter;

/**
 * Created by sandeep on 5/4/18.
 * <p>
 * A generic class which abstracts all the generic map and location related tasks,
 * keeing in mind the "Separation of concerns" and
 * thus avoiding overbloating of a single class.
 */

public abstract class AbstractMapActivity extends BaseActivity implements MapsActivityPresenter.View {
    private MapsActivityPresenter mapsActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initVaraibles();
        initMap();
    }

    protected void initVaraibles() {
        mapsActivityPresenter = new MapsActivityPresenterImpl(this, this);
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapsActivityPresenter.loadMap(mapFragment);
    }

    @Override
    public void vOnMapLoaded() {
        mapsActivityPresenter.connectApiClient();
    }

    @Override
    public void vOnClientConnected() {
        mapsActivityPresenter.locateCurrentPosition();
    }

    @Override
    public void vOnConnectionFailed() {}

    @Override
    public void vOnConnectionSuspended(int i) {
        if (i == 100) {
            Toast.makeText(context, context.getResources().getString(R.string.err_connectivity), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, context.getResources().getString(R.string.err_g_connection), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void vOnCurrentPositionLocated(Location location) {
        locateRestaurants();
    }

    private void locateRestaurants() {
        mapsActivityPresenter.fetchRestaurants();
    }

    @Override
    public void vOnRestaurantsFetched(ApiResponse venues) {
        mapsActivityPresenter.addRestaurantMarkers();
    }
}
