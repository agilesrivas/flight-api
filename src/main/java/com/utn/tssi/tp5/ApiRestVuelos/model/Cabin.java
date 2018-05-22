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

    public double getPriceKm() {
        return priceKm;
    }

    public void setPriceKm(double priceKm) {
        this.priceKm = priceKm;
    }

    @Override
    public String toString() {
        return getName() + " - $" + getPriceKm() + "/km.";
=======

    @Override
    public String toString() {
        return this.name + " - $" + this.priceKm + "/km";
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Cabin)) return false;
        Cabin cabin = (Cabin) o;
<<<<<<< HEAD
        return getId() == cabin.getId() &&
                getName().equals(cabin.getName()) &&
                getPriceKm() == cabin.getPriceKm();
=======
        return this.id == cabin.getId() && this.name.equals(cabin.getName()) && this.priceKm == cabin.getPriceKm();
>>>>>>> gianfrancostabile_Branch
    }

    @Override
    public int hashCode() {
        int hash = 16;

<<<<<<< HEAD
        hash = 31 * hash + (int) getId();
        hash = 31 * hash + Integer.parseInt(getName());
        hash = 31 * hash + (int) getPriceKm();
=======
        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.name == null) ? 0 : this.name.hashCode());
        hash = 31 * hash + (int) this.priceKm;
>>>>>>> gianfrancostabile_Branch

        return hash;
    }
}
