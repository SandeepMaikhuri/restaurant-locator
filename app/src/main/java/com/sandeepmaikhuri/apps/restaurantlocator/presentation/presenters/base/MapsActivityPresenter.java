package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base;

import android.location.Location;

import com.google.android.gms.maps.SupportMapFragment;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;

/**
 * Created by sandeep on 4/4/18.
 * Presenter contract for Maps Activity.
 */

@SuppressWarnings("unused")
public interface MapsActivityPresenter extends BasePresenter {

    interface View {
        /**
         * Notifies on map loaded
         */
        void vOnMapLoaded();

        /**
         * Notifies when google api client is connected
         */
        void vOnClientConnected();

        /**
         * Notifies when google api client connection is failed
         */
        void vOnConnectionFailed();

        /**
         * Notifies when google api client connection is suspended
         *
         * @param i Connection callback value
         */
        void vOnConnectionSuspended(int i);

        /**
         * Notifies on current position located
         *
         * @param location Android gms location
         */
        void vOnCurrentPositionLocated(Location location);

        /**
         * Notofies on restaurants fetched
         *
         * @param apiResponse response from api
         */
        void vOnRestaurantsFetched(ApiResponse apiResponse);
    }

    interface OnDelegationTaskDone {
        /**
         * Notofies on restaurants fetched
         *
         * @param apiResponse response from api
         */
        void onRestaurantsFetched(ApiResponse apiResponse);
    }

    /**
     * Load the google map
     *
     * @param mapFragment Android support map fragment
     *                    Callback to onMapLoded()
     */
    void loadMap(SupportMapFragment mapFragment);

    /**
     * Connect google api client
     * Callback to vOnClientConnected()
     */
    void connectApiClient();

    /**
     * Locate current user position
     * Callback to vOnCurrentPositionLocated()
     */
    void locateCurrentPosition();

    /**
     * Set marker for user's current position
     *
     * @param latitude  User's current latitude
     * @param longitude User's current longitude
     */
    void setCurrentPositionMarker(double latitude, double longitude);

    /**
     * Remove User's current position
     */
    void removeCurrentPositionMarker();

    /**
     * Fetch list of restaurants
     * Callback to vOnRestaurantsFetched()
     */
    void fetchRestaurants();

    /**
     * Add markers on map for the fetched restaurants
     */
    void addRestaurantMarkers();
}