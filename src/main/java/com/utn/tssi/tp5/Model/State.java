package com.utn.tssi.tp5.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class State {

    @Id @GeneratedValue private long id;
    private String name;
    private String iataCode;
    @Autowired private Country country;

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

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof State)) return false;

        State state = (State) o;
        return getId() == state.getId() &&
                getName().equals(state.getName()) &&
                getIataCode().equals(state.getIataCode()) &&
                getCountry().equals(state.getCountry());
    }

    @Override
    public int hashCode() {
        int hash = 12;

        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIataCode());
        hash = 31 * hash + getCountry().hashCode();

        return hash;
    }
}
