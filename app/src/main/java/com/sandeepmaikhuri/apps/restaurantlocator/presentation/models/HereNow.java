
package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models;

import java.io.Serializable;
import java.util.List;

public class HereNow implements Serializable
{

    private int count;
    private String summary;
    private List<Object> groups = null;

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public List<Object> getGroups()
    {
        return groups;
    }

    public void setGroups(List<Object> groups)
    {
        this.groups = groups;
    }

}
