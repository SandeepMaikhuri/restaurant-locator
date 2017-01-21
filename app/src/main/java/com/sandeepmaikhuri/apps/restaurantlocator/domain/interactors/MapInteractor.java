package com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors;

import com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base.AbstractInteractor;
import com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base.Interactor;

/**
 * Created by Sandeep on 6/12/16.
 */
public interface MapInteractor extends Interactor
{
    interface Callback
    {
        void onMapInitialized();
    }
}
