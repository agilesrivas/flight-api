package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateWrapper{

    private String name;
    private String iataCode;
    private CountryWrapper countryWrapper;

    public StateWrapper(State state) {
        this.name = state.getName();
        this.iataCode = state.getIataCode();
        this.countryWrapper = new CountryWrapper(state.getCountry());
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", country=" + countryWrapper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof StateWrapper)) return false;

        StateWrapper stateWrapper = (StateWrapper) o;
        return this.name.equals(stateWrapper.getName()) && this.iataCode.equals(stateWrapper.getIataCode()) && this.countryWrapper.equals(stateWrapper.getCountryWrapper());
    }

    @Override
    public int hashCode() {
        int hash = 12;

        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.countryWrapper == null) ? 0 : this.countryWrapper.hashCode());

        return hash;
    }

}
