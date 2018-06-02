package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Airports")
@NoArgsConstructor
public class Airport implements ValidationInterface<Airport>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "iata", nullable = false, unique = true)
    private String iataCode;

    @Column(name = "name_Airport", nullable = false, unique = true)
    private String name;

    @JoinColumn(name = "id_City", nullable = false)
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
        return "{" +
                "iataCode='" + iataCode + '\'' +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
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

    public boolean validateNullEmpty() {

        boolean bool = true;

        if(id >= 0 && name != null && !(name.trim().equals("")) && iataCode != null && !(iataCode.trim().equals("")) && city != null && !(city.validateNullEmpty()) && latitude >= 0 && longitude >= 0) {
            bool = false;
        }

        return bool;
    }
}
