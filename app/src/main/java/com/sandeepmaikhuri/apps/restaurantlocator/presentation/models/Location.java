
package com.sandeepmaikhuri.apps.restaurantlocator.presentation.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("lat")
    @Expose
    private float lat;
    @SerializedName("lng")
    @Expose
    private float lng;
    @SerializedName("labeledLatLngs")
    @Expose
    private List<LabeledLatLng> labeledLatLngs = null;
    @SerializedName("distance")
    @Expose
    private int distance;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("formattedAddress")
    @Expose
    private List<String> formattedAddress = null;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("crossStreet")
    @Expose
    private String crossStreet;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public List<LabeledLatLng> getLabeledLatLngs() {
        return labeledLatLngs;
    }

    public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
