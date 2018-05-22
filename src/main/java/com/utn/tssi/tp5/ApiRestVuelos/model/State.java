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
public class State {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String iataCode;
    private Country country;

    public State(long id, String name, String iataCode, Country country) {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
        this.country = country;
    }

    public State(String name, String iataCode, Country country) {

        this.name = name;
        this.iataCode = iataCode;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        String to = "";
        to = getName() + " (" + getIataCode() + ") - " + getCountry().toString();
=======
    @Override
    public String toString() {
        String to = "", countryString = "null";

        if(this.country != null)
            countryString = this.country.toString();

        to = this.name + " (" + this.iataCode + ") - " + countryString;
>>>>>>> gianfrancostabile_Branch

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof State)) return false;

        State state = (State) o;
<<<<<<< HEAD
        return getId() == state.getId() &&
                getName().equals(state.getName()) &&
                getIataCode().equals(state.getIataCode()) &&
                getCountry().equals(state.getCountry());
=======
        return this.id == state.getId() && this.name.equals(state.getName()) && this.iataCode.equals(state.getIataCode()) && this.country.equals(state.getCountry());
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 12;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIataCode());
        hash = 31 * hash + getCountry().hashCode();
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.country == null) ? 0 : this.country.hashCode());
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}
