package com.utn.tssi.tp5.ApiRestVuelos.model;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static sun.misc.VM.getState;

@Getter
@Setter
>>>>>>> gianfrancostabile_Branch
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
=======
    @Override
    public String toString() {
        String to = "", stateString = "null";

        if(this.state != null)
            stateString = this.state.toString();

        to = this.name + " (" + this.iataCode + ") - " + stateString;
>>>>>>> gianfrancostabile_Branch

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof City)) return false;

        City city = (City) o;
<<<<<<< HEAD
        return getId() == city.getId() &&
                getName().equals(city.getName()) &&
                getIataCode().equals(city.getIataCode()) &&
                getState().equals(city.getState());
=======
        return this.id == city.getId() && this.name.equals(city.getName()) && this.iataCode.equals(city.getIataCode()) && this.state.equals(city.getState());
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 13;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIataCode());
        hash = 31 * hash + getState().hashCode();
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.state == null) ? 0 : this.state.hashCode());
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}
