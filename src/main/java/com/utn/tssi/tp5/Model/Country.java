package com.utn.tssi.tp5.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Country {

    @Id @GeneratedValue private long id;
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
        return this.id == country.id &&
                this.name.equals(country.name) &&
                this.isoCode.equals(country.isoCode);
    }

    @Override
    public int hashCode() {
        int hash = 11;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + Integer.parseInt(this.name);
        hash = 31 * hash + Integer.parseInt(this.isoCode);

        return hash;
    }
}
