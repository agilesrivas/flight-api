package com.utn.tssi.tp5.ApiRestVuelos.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Airport {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String iataCode;
    private City city;
    private float latitude;
    private float longitude;

    public Airport(long id, String name, String iataCode, City city, float latitude, float longitude) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Airport(String name, String iataCode, City city, float latitude, float longitude) {
        this.name = name;
        this.iataCode = iataCode;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        String to = "";
        to = getName() + " (" + getIataCode() + ") - " + getCity().toString();

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Airport)) return false;

        Airport airport = (Airport) o;
        return getId() == airport.getId() &&
                getName().equals(airport.getName()) &&
                getIataCode().equals(airport.getIataCode()) &&
                getCity().equals(airport.getCity()) &&
                getLatitude() == airport.getLatitude() &&
                getLongitude() == airport.getLongitude();
    }

    @Override
    public int hashCode() {
        int hash = 14;

        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIataCode());
        hash = 31 * hash + getCity().hashCode();
        hash = 31 * hash + (int) getLatitude();
        hash = 31 * hash + (int) getLongitude();

        return hash;
    }
}
