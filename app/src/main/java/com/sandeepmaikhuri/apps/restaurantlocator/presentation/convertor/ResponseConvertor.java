package com.sandeepmaikhuri.apps.restaurantlocator.presentation.convertor;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Category;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Venue_;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.Venues;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 16/12/16.
 */
public class ResponseConvertor
{
    public static ArrayList<String> venuesToCategoriesList(GetVenuesResponse venuesResponse)
    {
        ArrayList<String> categoriesList = new ArrayList<>();

        if (venuesResponse != null)
        {
            if (venuesResponse.getResponse() != null)
            {
                if(venuesResponse.getResponse().venues != null)
                {
                    if (venuesResponse.getResponse().venues.length > 0)
                    {
                        Venues[] venues = venuesResponse.getResponse().venues;

                        for (int i = 0; i < venues.length; i++)
                        {
                            Venues venue_ = venues[i];

                            for (int j = 0; j < venue_.getCategories().size(); j++)
                            {
                                Category category = venue_.getCategories().get(j);

                                if(!categoriesList.contains(category.getName()))
                                {
                                    categoriesList.add(category.getName());
                                }
                            }
                        }
                    }
                }
            }
        }
        return categoriesList;
    }

    public static ArrayList<String> venuesToCategoriesList(Response response)
    {
        ArrayList<String> categoriesList = new ArrayList<>();

        for (int i = 0; i < response.getVenues().size(); i++)
        {
            Venue_ venue_ = response.getVenues().get(i);

            for (int j = 0; j < venue_.getCategories().size(); j++)
            {
                Category category = venue_.getCategories().get(j);

                if(!categoriesList.contains(category.getName()))
                {
                    categoriesList.add(category.getName());
                }
            }
        }

        return categoriesList;
    }

    public static GetVenuesResponse filteredResponse(GetVenuesResponse venuesResponse, ArrayList<String> categoriesList)
    {
        Venues [] venues = new Venues[20];

//        List<Venues> filtered_venue = new ArrayList<>();
        GetVenuesResponse filteredResponses = new GetVenuesResponse();

        for (int i = 0; i < venuesResponse.getResponse().venues.length; i++)
        {
            Venues venue = venuesResponse.getResponse().venues[i];

            for (int j = 0; j < venue.getCategories().size(); j++)
            {
                if (categoriesList.contains(venue.getCategories().get(j).getName()))
                {
                    venues[j] = venue;
//                    filtered_venue.add(venue);
                }
            }
        }

        com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.Response response = new com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.Response();

        response.setVenues(venues);

        filteredResponses.setResponse(response);
        return filteredResponses;
    }

    public static Response filteredResponse(Response response, ArrayList<String> categoriesList)
    {
        List<Venue_> filtered_venue = new ArrayList<>();
        Response filteredResponses = new Response();

        for (int i = 0; i < response.getVenues().size(); i++)
        {
            Venue_ venue_ = response.getVenues().get(i);

            for (int j = 0; j < venue_.getCategories().size(); j++)
            {
                if (categoriesList.contains(venue_.getCategories().get(j).getName()))
                {
                    filtered_venue.add(venue_);
                }
            }
        }
        filteredResponses.setVenues(filtered_venue);
        return filteredResponses;
    }
}
