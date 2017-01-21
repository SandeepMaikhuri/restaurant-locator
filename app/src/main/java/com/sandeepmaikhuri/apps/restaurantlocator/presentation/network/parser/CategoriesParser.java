package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.parser;


import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.Categories;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetCategoryResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sandeep on 10/12/16.
 */
public class CategoriesParser
{
    public static String getFoodCategoryId(GetCategoryResponse categoryResponse)
    {
        if (categoryResponse != null)
        {
            if(categoryResponse.getResponse() != null)
            {
                if (categoryResponse.getResponse().getCategories() != null)
                {
                    Categories[] categories = categoryResponse.getResponse().getCategories();

                    if (categories.length > 0)
                    {
                        for (int i = 0; i < categories.length; i++)
                        {
                            if (!categories[i].getName().contentEquals("Food"))
                            {
                                continue;
                            }
                            else
                            {
                                return categories[i].getId();
                            }
                        }
                    }
                }
            }
        }
        return "failed";
    }

    public static String getFoodCategoryId(String jsonData)
    {
        JSONObject jsonObject;

        try
        {
            jsonObject = new JSONObject(jsonData);

            JSONObject response = jsonObject.getJSONObject("response");

            if(response != null)
            {
                JSONArray categories = response.getJSONArray("categories");

                if(categories != null && categories.length() > 0)
                {
                    for (int i = 0; i < categories.length(); i++)
                    {
                        if(!categories.getJSONObject(i).getString("name").contentEquals("Food"))
                        {
                            continue;
                        }
                        else
                        {
                            JSONObject food = categories.getJSONObject(i);

                            if (food.has("id"))
                            {
                                return food.getString("id");
                            }

                            break;
                        }
                    }
                }
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return "failed";
    }
}
