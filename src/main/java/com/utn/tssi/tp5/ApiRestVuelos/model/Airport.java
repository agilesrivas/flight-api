package com.utn.tssi.tp5.ApiRestVuelos.model;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
>>>>>>> gianfrancostabile_Branch
public class Airport {

    @Id
    @GeneratedValue
    private long id;
<<<<<<< HEAD
    private String name;
    private String iataCode;
=======
    private String iataCode;
    private String name;
>>>>>>> gianfrancostabile_Branch
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

<<<<<<< HEAD
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

=======
    @Override
    public String toString() {
        String to = "", cityString = "null";

        if (this.city != null)
            cityString = this.city.toString();

        to = this.name + " (" + this.iataCode + ") - " + this.city.toString();

        return to;
    }
	
>>>>>>> gianfrancostabile_Branch
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Airport)) return false;

        Airport airport = (Airport) o;
<<<<<<< HEAD
        return getId() == airport.getId() &&
                getName().equals(airport.getName()) &&
                getIataCode().equals(airport.getIataCode()) &&
                getCity().equals(airport.getCity()) &&
                getLatitude() == airport.getLatitude() &&
                getLongitude() == airport.getLongitude();
=======
        return this.id == airport.getId() && this.name.equals(airport.getName()) && this.iataCode.equals(airport.getIataCode()) && this.city.equals(airport.getCity()) && this.latitude == airport.getLatitude() && this.longitude == airport.getLongitude();
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 14;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIataCode());
        hash = 31 * hash + getCity().hashCode();
        hash = 31 * hash + (int) getLatitude();
        hash = 31 * hash + (int) getLongitude();
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.city == null) ? 0 : this.city.hashCode());
        hash = 31 * hash + (int) this.latitude;
        hash = 31 * hash + (int) this.longitude;
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}
