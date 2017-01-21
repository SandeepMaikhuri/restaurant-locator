package com.sandeepmaikhuri.apps.restaurantlocator.domain.interactors.base;


import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.MainThread;
import com.sandeepmaikhuri.apps.restaurantlocator.domain.executor.RLExecutor;

public abstract class AbstractInteractor implements Interactor
{

    protected RLExecutor mThreadExecutor;
    protected MainThread mMainThread;

    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;

    public AbstractInteractor(RLExecutor threadExecutor, MainThread mainThread)
    {
        mThreadExecutor = threadExecutor;
        mMainThread = mainThread;
    }

    public abstract void run();

    public void cancel()
    {
        mIsCanceled = true;
        mIsRunning = false;
    }

    public boolean isRunning()
    {
        return mIsRunning;
    }

    public void onFinished()
    {
        mIsRunning = false;
        mIsCanceled = false;
    }

    public void execute()
    {

        // mark this interactor as running
        this.mIsRunning = true;

        // start running this interactor in a background thread
        mThreadExecutor.execute(this);
    }

}
