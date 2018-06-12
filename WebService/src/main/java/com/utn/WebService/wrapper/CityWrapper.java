package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityWrapper {

    private String name;
    private String iataCode;
    private StateWrapper stateWrapper;

    public CityWrapper(City city) {
        if(city != null) {
            this.name = city.getName();
            this.iataCode = city.getIataCode();
            this.stateWrapper = new StateWrapper(city.getState());

        } else {
            this.stateWrapper = new StateWrapper(null);
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", state=" + stateWrapper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CityWrapper)) return false;

        CityWrapper cityWrapper = (CityWrapper) o;
        return this.name.equals(cityWrapper.getName()) && this.iataCode.equals(cityWrapper.getIataCode()) && this.stateWrapper.equals(cityWrapper.getStateWrapper());
    }

    @Override
    public int hashCode() {
        int hash = 13;

        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.iataCode == null) ? 0 : this.iataCode.hashCode());
        hash = 31 * hash + ((this.stateWrapper == null) ? 0 : this.stateWrapper.hashCode());

        return hash;
    }
}
