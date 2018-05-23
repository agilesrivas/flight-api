package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String isoCode;

    public Country(long id, String name, String isoCode) {
        this.id = id;
        this.name = name;
        this.isoCode = isoCode;
    }

    public Country(String name, String isoCode) {

        this.name = name;
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        String to = "";
        to = this.name + " (" + this.isoCode + ")";

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Country)) return false;

        Country country = (Country) o;
        return this.id == country.getId() && this.name.equals(country.getName()) && this.isoCode.equals(country.getIsoCode());
    }

    @Override
    public int hashCode() {
        int hash = 11;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.isoCode == null) ? 0 : this.isoCode.hashCode());

        return hash;
    }
}
