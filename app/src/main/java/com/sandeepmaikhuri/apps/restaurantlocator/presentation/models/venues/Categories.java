package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandeep on 27/12/16.
 */
public class Categories
{
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
