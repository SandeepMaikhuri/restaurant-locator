
package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models;


import java.io.Serializable;

public class BeenHere implements Serializable
{

    private int lastCheckinExpiredAt;

    public int getLastCheckinExpiredAt()
    {
        return lastCheckinExpiredAt;
    }

    public void setLastCheckinExpiredAt(int lastCheckinExpiredAt)
    {
        this.lastCheckinExpiredAt = lastCheckinExpiredAt;
    }

}
