package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Flights")
@NoArgsConstructor
public class Flight {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id_Route", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;

    @Column(name = "date_Flight")
    private String date;

    public Flight(long id, Route route, String date) {
        this.id = id;
        this.route = route;
        this.date = date;
    }

    public Flight(Route route, String date) {
        this.route = route;
        this.date = date;
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "{" +
=======
<<<<<<< HEAD
        return "Flight{" +
=======
        return "{" +
>>>>>>> 311b2c0941cf3d22be5443db63e3764af889b41b
>>>>>>> 533ee41a8f98fe21a11082b148ad0e10a168bba2
                "route=" + route +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Flight)) return false;

        Flight flight = (Flight) o;
        return this.id == flight.getId() && this.route.equals(flight.getRoute()) && this.date.equals(flight.getDate());
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (int) this.id;
        hash = 31 * hash + ((this.route == null) ? 0 : this.route.hashCode());
        hash = 31 * hash + ((this.date == null) ? 0 : this.date.hashCode());

        return hash;
    }
}
