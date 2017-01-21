package com.sandeepmaikhuri.apps.restaurantlocator.presentation.network.parser;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Category;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Contact;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Icon;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Location;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Response;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Stats;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Venue_;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues.GetVenuesResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 15/12/16.
 */
public class VenuesParser
{
    public static Response parseGetRestaurantsResponse(String jsonResponse)
    {
        JSONObject jsonObject;

        List<Venue_> venueList = new ArrayList<>();

        try
        {
            jsonObject = new JSONObject(jsonResponse);

            JSONObject json = jsonObject.getJSONObject("response");

            if (json != null)
            {
                JSONArray venues = json.getJSONArray("venues");

                if (venues != null && venues.length() > 0)
                {
                    for (int i = 0; i < venues.length(); i++)
                    {
                        JSONObject restaurant = venues.getJSONObject(i);

                        Venue_ venue_ = new Venue_();

                        venue_.setId(restaurant.getString("id"));
                        venue_.setName(restaurant.getString("name"));

                        if (restaurant.getJSONObject("contact").has("twitter"))
                        {
                            Contact contact = new Contact();

                            if (restaurant.getJSONObject("contact").has("twitter"))
                            {
                                contact.setTwitter(restaurant.getJSONObject("contact").getString("twitter"));
                                venue_.setContact(contact);
                            }
                        }

                        if (restaurant.has("location"))
                        {
                            Location location = new Location();

                            if (restaurant.getJSONObject("location").has("address"))
                            {
                                location.setAddress(restaurant.getJSONObject("location").getString("address"));
                            }
                            if (restaurant.getJSONObject("location").has("crossStreet"))
                            {
                                location.setCrossStreet(restaurant.getJSONObject("location").getString("crossStreet"));
                            }
                            if (restaurant.getJSONObject("location").has("lat"))
                            {
                                location.setLat(restaurant.getJSONObject("location").getDouble("lat"));
                            }
                            if (restaurant.getJSONObject("location").has("lng"))
                            {
                                location.setLng(restaurant.getJSONObject("location").getDouble("lng"));
                            }
                            if (restaurant.getJSONObject("location").has("cc"))
                            {
                                location.setCc(restaurant.getJSONObject("location").getString("cc"));
                            }
                            if (restaurant.getJSONObject("location").has("city"))
                            {
                                location.setCity(restaurant.getJSONObject("location").getString("city"));
                            }
                            if (restaurant.getJSONObject("location").has("state"))
                            {
                                location.setState(restaurant.getJSONObject("location").getString("state"));
                            }
                            if (restaurant.getJSONObject("location").has("country"))
                            {
                                location.setCountry(restaurant.getJSONObject("location").getString("country"));
                            }

                            venue_.setLocation(location);
                        }

                        if (restaurant.has("categories"))
                        {
                            List<Category> listCategory = new ArrayList<>();

                            JSONArray categoryArray = restaurant.getJSONArray("categories");

                            for (int j = 0; j < categoryArray.length(); j++)
                            {
                                Category category = new Category();

                                if (categoryArray.getJSONObject(j).has("id"))
                                {
                                    category.setId(categoryArray.getJSONObject(j).getString("id"));
                                }
                                if (categoryArray.getJSONObject(j).has("name"))
                                {
                                    category.setName(categoryArray.getJSONObject(j).getString("name"));
                                }
                                if (categoryArray.getJSONObject(j).has("pluralName"))
                                {
                                    category.setPluralName(categoryArray.getJSONObject(j).getString("pluralName"));
                                }
                                if (categoryArray.getJSONObject(j).has("shortName"))
                                {
                                    category.setShortName(categoryArray.getJSONObject(j).getString("shortName"));
                                }

                                if (categoryArray.getJSONObject(j).has("icon"))
                                {
                                    Icon icon = new Icon();

                                    if (categoryArray.getJSONObject(j).getJSONObject("icon").has("prefix"))
                                    {
                                        icon.setPrefix(categoryArray.getJSONObject(j).getJSONObject("icon").getString("prefix"));
                                    }

                                    if (categoryArray.getJSONObject(j).getJSONObject("icon").has("suffix"))
                                    {
                                        icon.setSuffix(categoryArray.getJSONObject(j).getJSONObject("icon").getString("suffix"));
                                    }
                                    category.setIcon(icon);
                                }

                                listCategory.add(category);
                            }

                            if (listCategory.size() > 0)
                            {
                                venue_.setCategories(listCategory);
                            }
                        }

                        if (restaurant.has("stats"))
                        {
                            Stats stats = new Stats();

                            if (restaurant.getJSONObject("stats").has("checkinsCount"))
                            {
                                stats.setCheckinsCount(restaurant.getJSONObject("stats").getInt("checkinsCount"));
                            }
                            if (restaurant.getJSONObject("stats").has("usersCount"))
                            {
                                stats.setUsersCount(restaurant.getJSONObject("stats").getInt("usersCount"));
                            }
                            if (restaurant.getJSONObject("stats").has("tipCount"))
                            {
                                stats.setTipCount(restaurant.getJSONObject("stats").getInt("tipCount"));
                            }
                            venue_.setStats(stats);
                        }

                        if (restaurant.has("url"))
                        {
                            venue_.setUrl(restaurant.getString("url"));
                        }

                        venueList.add(venue_);
                    }
                }

                Response response = new Response();
                response.setVenues(venueList);

                return response;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
