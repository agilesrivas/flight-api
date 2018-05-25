package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Airports")
public class Airport {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "iata", nullable = false, unique = true)
    private String iataCode;

    @Column(name = "name_Airport", nullable = false, unique = true)
    private String name;

    @Column(name = "id_City", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
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

    @Override
    public String toString() {
        String to = "", cityString = "null";

        if (this.city != null)
            cityString = this.city.toString();

        to = this.name + " (" + this.iataCode + ") - " + this.city.toString();

        return to;
    }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Airport)) return false;

        Airport airport = (Airport) o;
        return this.id == airport.getId() && this.name.equals(airport.getName()) && this.iataCode.equals(airport.getIataCode()) && this.city.equals(airport.getCity()) && this.latitude == airport.getLatitude() && this.longitude == airport.getLongitude();
    }

    @Override
    public int hashCode() {
        int hash = 14;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.city == null) ? 0 : this.city.hashCode());
        hash = 31 * hash + (int) this.latitude;
        hash = 31 * hash + (int) this.longitude;

        return hash;
    }
}