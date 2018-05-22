package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
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

    @Override
    public String toString() {
        String to = "", countryString = "null";

        if(this.country != null)
            countryString = this.country.toString();

        to = this.name + " (" + this.iataCode + ") - " + countryString;

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof State)) return false;

        State state = (State) o;
        return this.id == state.getId() && this.name.equals(state.getName()) && this.iataCode.equals(state.getIataCode()) && this.country.equals(state.getCountry());
    }

    @Override
    public int hashCode() {
        int hash = 12;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.country == null) ? 0 : this.country.hashCode());

        return hash;
    }
}
