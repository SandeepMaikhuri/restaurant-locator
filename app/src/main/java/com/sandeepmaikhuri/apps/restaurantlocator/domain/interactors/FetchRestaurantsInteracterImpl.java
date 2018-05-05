package com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors;

import com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base.FetchRestaurantsInteracter;
import com.sandeepmaikhuri.apps.restaurantlocator.network.NetworkAdapter;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.ports.NetworkPort;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.FetchRestaurantsPresenterImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.FetchRestaurantsPresenter;

public class FetchRestaurantsInteracterImpl implements FetchRestaurantsInteracter {

    private FetchRestaurantsPresenter.OnDelegationTaskDone onDelegationTaskDone;

    public FetchRestaurantsInteracterImpl(FetchRestaurantsPresenterImpl.OnDelegationTaskDone onDelegationTaskDone) {
        this.onDelegationTaskDone = onDelegationTaskDone;
    }

    @Override
    public void fetchRestaurants(double latitude, double longitude) {

        NetworkPort networkPort = new NetworkAdapter(new NetworkPort.OnRestaurantsFetched() {
            @Override
            public void onRestaurantsFetched(ApiResponse response) {
                onDelegationTaskDone.onRestaurantsFetched(response);
            }
        });

        networkPort.fetchRestaurantsApiCall(latitude, longitude);
    }
}