package com.sandeepmaikhuri.apps.restaurantlocator.presentation;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.Categories;

/**
 * Created by Sandeep on 27/12/16.
 */
public class Response
{
    public Categories[] categories;

    public Categories[] getCategories()
    {
        return categories;
    }

    public void setCategories(Categories[] categories)
    {
        this.categories = categories;
    }
}
