package com.utn.tssi.tp5.ApiRestVuelos.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
public class Cabin {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double priceKm;

    public Cabin(long id, String name, double priceKm) {
        this.id = id;
        this.name = name;
        this.priceKm = priceKm;
    }

    public Cabin(String name, double priceKm) {
        this.name = name;
        this.priceKm = priceKm;
    }


    @Override
    public String toString() {
        return this.name + " - $" + this.priceKm + "/km";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Cabin)) return false;
        Cabin cabin = (Cabin) o;
        return this.id == cabin.getId() &&
                this.name.equals(cabin.getName()) &&
                this.priceKm == cabin.getPriceKm();
    }

    @Override
    public int hashCode() {
        int hash = 16;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + (int) this.priceKm;

        return hash;
    }
}
