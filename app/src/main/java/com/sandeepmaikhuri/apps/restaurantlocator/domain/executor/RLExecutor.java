package com.sandeepmaikhuri.apps.restaurantlocator.domain.executor;


import com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base.AbstractInteractor;

public interface RLExecutor
{
    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractInteractor interactor);
}
