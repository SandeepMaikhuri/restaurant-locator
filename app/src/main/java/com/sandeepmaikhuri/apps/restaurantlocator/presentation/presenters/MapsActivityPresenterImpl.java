package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sandeepmaikhuri.apps.restarauntlocator.R;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.LocationPresenter;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Venue;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.FetchRestaurantsPresenter;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.MapsActivityPresenter;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.NetworkStatus;

import java.util.List;

/**
 * Created by sandeep on 4/4/18.
 * Presenter implementation for Maps Activity
 */

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class MapsActivityPresenterImpl implements MapsActivityPresenter, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationPresenter.OnLocationDelegationTaskDone,
        MapsActivityPresenter.OnDelegationTaskDone{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Context context;
    private View mapsView;
    private Marker mCurrLocationMarker;
    private Location mLastLocation;
    private double latitude;
    private double longitude;
    private ApiResponse apiResponse;

    public MapsActivityPresenterImpl(Context context, MapsActivityPresenter.View mapsView) {
        this.context = context;
        this.mapsView = mapsView;
    }

    //region Client Connection
    public void connectApiClient() {
        if (new NetworkStatus(context).isInternetOn()) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();

            mGoogleApiClient.connect();
        } else {
            onConnectionSuspended(100);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mapsView.vOnConnectionFailed();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mapsView.vOnClientConnected();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mapsView.vOnConnectionSuspended(i);
    }

    //endregion

    //region Location Stuff
    @Override
    public void locateCurrentPosition() {
        LocationPresenter locationPresenter = new LocationPresenterImpl(mMap, mGoogleApiClient, null, this);
        locationPresenter.locateUser();
    }

    @Override
    public void onLocationFetched(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        mapsView.vOnCurrentPositionLocated(location);
    }
    //endregion

    //region Map initiation
    @Override
    public void loadMap(SupportMapFragment mapFragment) {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mapsView.vOnMapLoaded();
    }
    //endregion

    //region Markers stuff
    @Override
    public void setCurrentPositionMarker(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(context.getResources().getString(R.string.current_position));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.cp));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

    @Override
    public void addRestaurantMarkers() {
        MarkerOptions markerOptions = new MarkerOptions();

        final List<Venue> venues = apiResponse.getResponse().getVenues();

        for (int i = 0; i < venues.size(); i++) {
            final Venue venue = venues.get(i);

            if (venue != null) {
                double lat = venue.getLocation().getLat();
                double lng = venue.getLocation().getLng();

                LatLng latLng = new LatLng(lat, lng);

                markerOptions.position(latLng);
                markerOptions.title(venue.getName());
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.rest));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public android.view.View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    @Override
                    public android.view.View getInfoContents(Marker arg0) {
                        @SuppressLint("InflateParams") android.view.View v =
                                ((Activity)context).getLayoutInflater().inflate(R.layout.layout_marker, null);

                        for (int j = 0; j < venues.size(); j++) {
                            if (venues.get(j).getName().contentEquals(arg0.getTitle())) {
                                TextView textVenueName = (TextView) v.findViewById(R.id.text_venue_name);
                                textVenueName.setText(venues.get(j).getName());

                                TextView textVenueCategory = (TextView) v.findViewById(R.id.text_category);

                                StringBuilder categories = new StringBuilder("Category : ");
                                for (int x = 0; x < venues.get(j).getCategories().size(); x++) {
                                    categories.append(venues.get(j).getCategories().get(x).getName()).append(" ");
                                }
                                textVenueCategory.setText(categories.toString());

                                TextView textVenueAddress = (TextView) v.findViewById(R.id.text_address);

                                textVenueAddress.setText(venues.get(j).getLocation().getAddress());
                            }
                        }

                        return v;
                    }
                });
            }
        }
    }

    @Override
    public void removeCurrentPositionMarker() {

    }
    //endregion

    @Override
    public void fetchRestaurants() {
        mMap.clear();

        FetchRestaurantsPresenter fetchRestaurantsPresenter = new FetchRestaurantsPresenterImpl(this);
        fetchRestaurantsPresenter.fetchRestaurants(latitude, longitude);
    }

    @Override
    public void onRestaurantsFetched(ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
        mapsView.vOnRestaurantsFetched(apiResponse);
    }
}