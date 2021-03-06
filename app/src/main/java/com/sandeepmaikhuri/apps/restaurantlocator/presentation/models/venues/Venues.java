package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.venues;

import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.BeenHere;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Category;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Contact;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.HereNow;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Location;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Specials;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.Stats;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.VenueChain;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.models.VenuePage;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sandeep on 27/12/16.
 */
public class Venues
{
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("contact")
    private Contact contact;

    @SerializedName("location")
    private Location location;

    private List<Category> categories = null;

    @SerializedName("verified")
    private boolean verified;

    private Stats stats;

    @SerializedName("url")
    private String url;

    @SerializedName("allowMenuUrlEdit")
    private boolean allowMenuUrlEdit;

    private BeenHere beenHere;

    private Specials specials;

    private HereNow hereNow;

    @SerializedName("referralId")
    private String referralId;

    private List<VenueChain> venueChains = null;

    @SerializedName("hasPerk")
    private boolean hasPerk;

    private VenuePage venuePage;

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

    public Contact getContact()
    {
        return contact;
    }

    public void setContact(Contact contact)
    {
        this.contact = contact;
    }

    public Location getLocation()
    {
        return location;
    }

    public void setLocation(Location location)
    {
        this.location = location;
    }

    public List<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }

    public boolean isVerified()
    {
        return verified;
    }

    public void setVerified(boolean verified)
    {
        this.verified = verified;
    }

    public Stats getStats()
    {
        return stats;
    }

    public void setStats(Stats stats)
    {
        this.stats = stats;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public boolean isAllowMenuUrlEdit()
    {
        return allowMenuUrlEdit;
    }

    public void setAllowMenuUrlEdit(boolean allowMenuUrlEdit)
    {
        this.allowMenuUrlEdit = allowMenuUrlEdit;
    }

    public BeenHere getBeenHere()
    {
        return beenHere;
    }

    public void setBeenHere(BeenHere beenHere)
    {
        this.beenHere = beenHere;
    }

    public Specials getSpecials()
    {
        return specials;
    }

    public void setSpecials(Specials specials)
    {
        this.specials = specials;
    }

    public HereNow getHereNow()
    {
        return hereNow;
    }

    public void setHereNow(HereNow hereNow)
    {
        this.hereNow = hereNow;
    }

    public String getReferralId()
    {
        return referralId;
    }

    public void setReferralId(String referralId)
    {
        this.referralId = referralId;
    }

    public List<VenueChain> getVenueChains()
    {
        return venueChains;
    }

    public void setVenueChains(List<VenueChain> venueChains)
    {
        this.venueChains = venueChains;
    }

    public boolean isHasPerk()
    {
        return hasPerk;
    }

    public void setHasPerk(boolean hasPerk)
    {
        this.hasPerk = hasPerk;
    }

    public VenuePage getVenuePage()
    {
        return venuePage;
    }

    public void setVenuePage(VenuePage venuePage)
    {
        this.venuePage = venuePage;
    }
}
