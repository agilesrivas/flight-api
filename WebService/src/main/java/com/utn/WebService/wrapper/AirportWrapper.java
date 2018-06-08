package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Airport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirportWrapper {

    private String iataCode;
    private String name;
    private CityWrapper cityWrapper;
    private float latitude;
    private float longitude;

    public AirportWrapper(Airport airport) {
        this.name = airport.getName();
        this.iataCode = airport.getIataCode();
        this.cityWrapper = new CityWrapper(airport.getCity());
        this.latitude = airport.getLatitude();
        this.longitude = airport.getLongitude();
    }

    @Override
    public String toString() {
        return "{" +
                "iataCode='" + iataCode + '\'' +
                ", name='" + name + '\'' +
                ", city=" + cityWrapper +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AirportWrapper)) return false;

        AirportWrapper airportWrapper = (AirportWrapper) o;
        return this.name.equals(airportWrapper.getName()) && this.iataCode.equals(airportWrapper.getIataCode()) && this.cityWrapper.equals(airportWrapper.getCityWrapper()) && this.latitude == airportWrapper.getLatitude() && this.longitude == airportWrapper.getLongitude();
    }

    @Override
    public int hashCode() {
        int hash = 14;

        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.cityWrapper == null) ? 0 : this.cityWrapper.hashCode());
        hash = 31 * hash + (int) this.latitude;
        hash = 31 * hash + (int) this.longitude;

        return hash;
    }
}
