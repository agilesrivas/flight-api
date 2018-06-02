package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Countries")
@NoArgsConstructor
public class Country implements ValidationInterface<Country>{

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name_Country", nullable = false, unique = true)
    private String name;

    @Column(name = "iso", nullable = false, unique = true)
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
        return "{" +
                "name='" + name + '\'' +
                ", isoCode='" + isoCode + '\'' +
                '}';
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

    public boolean validateNullEmpty() {
        boolean bool = true;

        if(id >= 0 && name != null && !(name.trim().equals("")) && isoCode != null && !(isoCode.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }

    public boolean validateNullEmptyIdentifier() {
        boolean bool = true;

        if(isoCode != null && !(isoCode.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }
}
