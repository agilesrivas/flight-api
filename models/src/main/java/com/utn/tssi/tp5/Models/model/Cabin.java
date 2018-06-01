package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Cabins")
@NoArgsConstructor
public class Cabin {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "type_Cabin", nullable = false, unique = true)
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
<<<<<<< HEAD
        return "{" +
=======
<<<<<<< HEAD
        return "Cabin{" +
=======
        return "{" +
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
                "name='" + name + '\'' +
                ", priceKm=" + priceKm +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Cabin)) return false;
        Cabin cabin = (Cabin) o;
        return this.id == cabin.getId() && this.name.equals(cabin.getName()) && this.priceKm == cabin.getPriceKm();
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
