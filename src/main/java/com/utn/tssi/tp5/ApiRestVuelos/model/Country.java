package com.utn.tssi.tp5.ApiRestVuelos.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public String toString() {
        String to = "";
        to = getName() + " (" + getIsoCode() + ")";

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Country)) return false;

        Country country = (Country) o;
        return getId() == country.getId() &&
                getName().equals(country.getName()) &&
                getIsoCode().equals(country.getIsoCode());
    }

    @Override
    public int hashCode() {
        int hash = 11;

        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIsoCode());

        return hash;
    }
}
