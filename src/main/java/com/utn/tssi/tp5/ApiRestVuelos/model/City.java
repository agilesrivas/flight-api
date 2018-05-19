package com.utn.tssi.tp5.ApiRestVuelos.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String to = "";
        to = getName() + " (" + getIataCode() + ") - " + getState().toString();

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof City)) return false;

        City city = (City) o;
        return getId() == city.getId() &&
                getName().equals(city.getName()) &&
                getIataCode().equals(city.getIataCode()) &&
                getState().equals(city.getState());
    }

    @Override
    public int hashCode() {
        int hash = 13;

        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIataCode());
        hash = 31 * hash + getState().hashCode();

        return hash;
    }
}
