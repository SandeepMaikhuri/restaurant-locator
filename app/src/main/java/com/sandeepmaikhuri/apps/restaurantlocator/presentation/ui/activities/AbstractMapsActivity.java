package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sandeepmaikhuri.apps.restarauntlocator.R;
import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.impl.ThreadExecutor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Venue_;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.Venues;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchFoodCategoryIdPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FetchRestaurantsPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.FilteredVenuesPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.GoogleApiClientPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.LocationPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base.MapsPresentor;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl.FetchFoodCategoryIdPresentorImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl.FilteredVenuesPresentorImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl.GoogleApiClientPresentorImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl.LocationPresentorImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.impl.MapsPresentorImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.repository.AddFoodIdRepository;
import com.sandeepmaikhuri.apps.restaurantlocator.repository.impl.AddFoodIdImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.threading.MainThreadImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.NetworkStatus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Sandeep on 6/12/16.
 *
 * A generic class which abstracts all the generic map and location related tasks,
 * keeing in mind the "Separation of concerns" and
 * thus avoiding overbloating of a single class.
 *
 */
public abstract class AbstractMapsActivity extends BaseActivity
        implements LocationPresentor.View, MapsPresentor.View, GoogleApiClientPresentor.View,
                   FetchFoodCategoryIdPresentor.View, FetchRestaurantsPresentor.View
{
    GoogleMap mMap;

    double latitude;
    double longitude;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    ProgressDialog dialog;
    Response listRestaurants;
    GetVenuesResponse venuesResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        MapsPresentor mapsPresentor = new MapsPresentorImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        mapsPresentor.initializeMap(mapFragment);
    }

    @Override
    public void onMapsInitialized(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        GoogleApiClientPresentorImpl mapsPresentor = new GoogleApiClientPresentorImpl(
                ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, mGoogleApiClient, this, new NetworkStatus(this));

        mapsPresentor.connectGoogleApiClient();
    }

    @Override
    public void onLocationFetched(Location location)
    {
        mLastLocation = location;
        if (mCurrLocationMarker != null)
        {
            mCurrLocationMarker.remove();
        }

        latitude = location.getLatitude();
        longitude = location.getLongitude();

        setUserMarker();

        locateRestaurants();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
        {
            if (data != null && data.getStringArrayListExtra(AppConstants.SELECTED_VALUES) != null)
            {
                ArrayList<String> selected_values = data.getStringArrayListExtra(AppConstants.SELECTED_VALUES);

//                String[] selected_values = data.getStringArrayExtra(AppConstants.SELECTED_VALUES);

                FilteredVenuesPresentor filteredVenuesPresentor = new FilteredVenuesPresentorImpl();
//                Response filteredResponse = filteredVenuesPresentor.filterVenues(listRestaurants, selected_values);
                GetVenuesResponse filteredResponse = filteredVenuesPresentor.filterVenues(venuesResponse, selected_values);

                setVenueMarkers(filteredResponse);
            }
        }
    }

    protected void setUserMarker()
    {
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(getResources().getString(R.string.current_position));
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.cp));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }

    @Override
    public void showProgress()
    {
    }

    @Override
    public void hideProgress()
    {

    }

    @Override
    public void showError(String message)
    {

    }

    void locateRestaurants()
    {
        mMap.clear();

        AddFoodIdRepository addFoodIdRepository = new AddFoodIdImpl(this);

        FetchFoodCategoryIdPresentorImpl fetchRestaurantsPresentor = new FetchFoodCategoryIdPresentorImpl(latitude, longitude, this, addFoodIdRepository);
        fetchRestaurantsPresentor.fetchFoodId();
    }

    @Override
    public void onGoogleApiClientConnected(Bundle bundle, GoogleApiClient mGoogleApiClient)
    {
        LocationPresentor locationPresentor = new LocationPresentorImpl(mMap, mGoogleApiClient, mLocationRequest, this);
        locationPresentor.locateUser();
    }

    @Override
    public void onGoogleApiClientConnectionSuspended(int i)
    {
        if(i == 100)
        {
            Toast.makeText(this, getResources().getString(R.string.err_connectivity), Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, getResources().getString(R.string.err_g_connection), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGoogleApiClientConnectionFailed(ConnectionResult connectionResult)
    {
        Toast.makeText(this, getResources().getString(R.string.err_retry), Toast.LENGTH_LONG).show();
    }

    void setVenueMarkers(final GetVenuesResponse venuesResponse)
    {
        mMap.clear();

        MarkerOptions markerOptions = new MarkerOptions();

        for (int i = 0; i < venuesResponse.getResponse().venues.length; i++)
        {
            final Venues venue = venuesResponse.getResponse().venues[i];

            if (venue != null)
            {
                double lat = venue.getLocation().getLat();
                double lng = venue.getLocation().getLng();

                LatLng latLng = new LatLng(lat, lng);

                markerOptions.position(latLng);
                markerOptions.title(venue.getName());
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.rest));
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
                {
                    @Override
                    public View getInfoWindow(Marker arg0)
                    {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker arg0)
                    {
                        View v = getLayoutInflater().inflate(R.layout.layout_marker, null);

                        for (int j = 0; j < venuesResponse.getResponse().venues.length; j++)
                        {
                            if (venuesResponse.getResponse().venues[j].getName().contentEquals(arg0.getTitle()))
                            {
                                TextView textVenueName = (TextView) v.findViewById(R.id.text_venue_name);
                                textVenueName.setText(venuesResponse.getResponse().venues[j].getName());

                                TextView textVenueCategory = (TextView) v.findViewById(R.id.text_category);

                                String categories = "Category : ";
                                for (int x = 0; x < venuesResponse.getResponse().venues[j].getCategories().size(); x++)
                                {
                                    categories = categories + venuesResponse.getResponse().venues[j].getCategories().get(x).getName() + " ";
                                }
                                textVenueCategory.setText(categories);

                                TextView textVenueAddress = (TextView) v.findViewById(R.id.text_address);

                                textVenueAddress.setText(venuesResponse.getResponse().venues[j].getLocation().getAddress());
                            }
                        }

                        return v;
                    }
                });
            }
        }
    }

    void setVenueMarkers(final Response response)
    {
        mMap.clear();

        MarkerOptions markerOptions = new MarkerOptions();

        for (int i = 0; i < response.getVenues().size(); i++)
        {
            final Venue_ venue_ = response.getVenues().get(i);
            double lat = venue_.getLocation().getLat();
            double lng = venue_.getLocation().getLng();

            LatLng latLng = new LatLng(lat, lng);

            markerOptions.position(latLng);
            markerOptions.title(venue_.getName());
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.rest));
            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter()
            {
                @Override
                public View getInfoWindow(Marker arg0)
                {
                    return null;
                }

                @Override
                public View getInfoContents(Marker arg0)
                {
                    View v = getLayoutInflater().inflate(R.layout.layout_marker, null);

                    for (int j = 0; j < response.getVenues().size(); j++)
                    {
                        if (response.getVenues().get(j).getName().contentEquals(arg0.getTitle()))
                        {
                            TextView textVenueName = (TextView) v.findViewById(R.id.text_venue_name);
                            textVenueName.setText(response.getVenues().get(j).getName());

                            TextView textVenueCategory = (TextView) v.findViewById(R.id.text_category);

                            String categories = "Category : ";
                            for (int x = 0; x < response.getVenues().get(j).getCategories().size(); x++)
                            {
                                categories = categories + response.getVenues().get(j).getCategories().get(x).getName() + " ";
                            }
                            textVenueCategory.setText(categories);

                            TextView textVenueAddress = (TextView) v.findViewById(R.id.text_address);

                            textVenueAddress.setText(response.getVenues().get(j).getLocation().getAddress());
                        }
                    }

                    return v;
                }
            });
        }
    }
}