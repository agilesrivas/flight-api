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
=======
    @Override
    public String toString() {
        String to = "";
        to = this.name + " (" + this.isoCode + ")";
>>>>>>> gianfrancostabile_Branch

        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Country)) return false;

        Country country = (Country) o;
<<<<<<< HEAD
        return getId() == country.getId() &&
                getName().equals(country.getName()) &&
                getIsoCode().equals(country.getIsoCode());
=======
        return this.id == country.getId() && this.name.equals(country.getName()) && this.isoCode.equals(country.getIsoCode());
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 11;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + Integer.parseInt(getIsoCode());
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + ((this.isoCode == null) ? 0 : this.isoCode.hashCode());
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}
