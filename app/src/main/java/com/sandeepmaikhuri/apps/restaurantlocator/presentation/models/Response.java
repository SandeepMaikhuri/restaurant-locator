
package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable
{

    private List<Venue_> venues = null;
    private boolean confident;

    public List<Venue_> getVenues()
    {
        return venues;
    }

    public void setVenues(List<Venue_> venues)
    {
        this.venues = venues;
    }

    public boolean isConfident()
    {
        return confident;
    }

    public void setConfident(boolean confident)
    {
        this.confident = confident;
    }

}