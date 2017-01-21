package com.sandeepmaikhuri.apps.restaurantlocator.presentation.presentors.base;

/**
 * Created by Sandeep on 7/12/16.
 */
public interface FetchFoodCategoryIdPresentor
{
    interface View
    {
        void fetchingRestaurants();

        void onFoodIdFetched(String foodId);

        void errorInFetchingFoodId();
    }

    interface FoodIdCommunicator
    {
        void onTaskStarted();

        void onTaskCompleted(String foodId);

        void onError();
    }

    void fetchFoodId();
}
