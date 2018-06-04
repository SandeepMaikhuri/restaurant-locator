package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters;

import com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.FetchRestaurantsInteracterImpl;
import com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base.FetchRestaurantsInteracter;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.ApiResponse;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.FetchRestaurantsPresenter;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.presenters.base.MapsActivityPresenter;

public class FetchRestaurantsPresenterImpl implements FetchRestaurantsPresenter, FetchRestaurantsPresenter.OnDelegationTaskDone {

    private MapsActivityPresenter.OnDelegationTaskDone delegationTaskDone;

    FetchRestaurantsPresenterImpl(MapsActivityPresenter.OnDelegationTaskDone delegationTaskDone) {
        this.delegationTaskDone = delegationTaskDone;
    }

    @Override
    public void fetchRestaurants(double latitude, double longitude) {
        FetchRestaurantsInteracter fetchRestaurantsInteracter = new FetchRestaurantsInteracterImpl(this);
        fetchRestaurantsInteracter.fetchRestaurants(latitude, longitude);
    }

    @Override
    public void onRestaurantsFetched(ApiResponse apiResponse) {
        delegationTaskDone.onRestaurantsFetched(apiResponse);
    }
}