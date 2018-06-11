package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryWrapper{

    private String name;
    private String isoCode;

    public CountryWrapper(Country country) {
        this.name = country.getName();
        this.isoCode = country.getIsoCode();
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof CountryWrapper)) return false;

        CountryWrapper countryWrapper = (CountryWrapper) o;
        return this.name.equals(countryWrapper.getName()) && this.isoCode.equals(countryWrapper.getIsoCode());
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.isoCode == null) ? 0 : this.isoCode.hashCode());

        return hash;
    }

}
