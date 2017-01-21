package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues;

/**
 * Created by Sandeep on 27/12/16.
 */
public class Response
{
    public Venues[] venues;

    public Venues[] getVenues()
    {
        return venues;
    }

    public void setVenues(Venues[] venues)
    {
        this.venues = venues;
    }
}
