package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static sun.misc.VM.getState;

@Getter
@Setter
public class City {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String iataCode;
    private State state;

    public City(long id, String name, String iataCode, State state) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
        this.state = state;
    }

    public City(String name, String iataCode, State state) {
        this.name = name;
        this.iataCode = iataCode;
        this.state = state;
    }

    @Override
    public String toString() {
        String to = "", stateString = "null";

        if(this.state != null)
            stateString = this.state.toString();

        to = this.name + " (" + this.iataCode + ") - " + stateString;

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof City)) return false;

        City city = (City) o;
        return this.id == city.getId() && this.name.equals(city.getName()) && this.iataCode.equals(city.getIataCode()) && this.state.equals(city.getState());
    }

    @Override
    public int hashCode() {
        int hash = 13;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.state == null) ? 0 : this.state.hashCode());

        return hash;
    }
}
