
package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models;

import java.io.Serializable;
import java.util.List;

public class Specials implements Serializable
{

    private int count;
    private List<Object> items = null;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public List<Object> getItems()
    {
        return items;
    }

    public void setItems(List<Object> items)
    {
        this.items = items;
    }

}
