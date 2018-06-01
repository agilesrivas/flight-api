package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Prices")
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "fromDate", nullable = false)
    private String fromDate;

<<<<<<< HEAD
    @Column(name = "toDate")
=======
<<<<<<< HEAD
    @Column(name = "toDate", nullable = false)
=======
    @Column(name = "toDate")
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    private String toDate;

    @Column(name = "state_bool", nullable = false)
    private boolean state_bool;

    @JoinColumn(name = "id_Cabin", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Cabin cabin;

    public Price(long id, float price, String fromDate, String toDate, boolean state_bool, Cabin cabin) {
        this.id = id;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.state_bool = state_bool;
        this.cabin = cabin;
    }

    public Price(float price, String fromDate, String toDate, boolean state_bool, Cabin cabin) {
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.state_bool = state_bool;
        this.cabin = cabin;
    }

<<<<<<< HEAD
    @Override
    public String toString() {
        return "{" +
=======
<<<<<<< HEAD

    @Override
    public String toString() {
        return "Price{" +
=======
    @Override
    public String toString() {
        return "{" +
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
                "price=" + price +
                ", fromDate='" + fromDate + '\'' +
                ", toDate='" + toDate + '\'' +
                ", state_bool=" + state_bool +
                ", cabin=" + cabin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Price)) return false;

        Price price= (Price) o;
<<<<<<< HEAD
        return this.id == price.getId() && this.price == price.getPrice() && this.fromDate.equals(price.fromDate) && this.toDate.equals(price.toDate) && this.state_bool == price.isState_bool();
=======
<<<<<<< HEAD
        return this.id == price.getId() && this.price == price.getPrice() && this.fromDate.equals(price.fromDate) && this.toDate.equals(price.toDate) && this.state_bool == price.isState_bool() && this.cabin == price.getCabin();
=======
        return this.id == price.getId() && this.price == price.getPrice() && this.fromDate.equals(price.fromDate) && this.toDate.equals(price.toDate) && this.state_bool == price.isState_bool();
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
    }

    @Override
    public int hashCode() {
        int hash = 15;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + (int) this.price;
        hash = 31 * hash + ((this.fromDate == null) ? 0 : this.fromDate.hashCode());
        hash = 31 * hash + ((this.toDate == null) ? 0 : this.toDate.hashCode());
<<<<<<< HEAD
=======
<<<<<<< HEAD
        hash = 31 * hash + this.cabin.hashCode();
=======
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2

        return hash;
    }
}
